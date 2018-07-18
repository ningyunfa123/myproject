package com.baidu.mybaidu.service.impl;

import com.baidu.mybaidu.dto.ApplyVpsDto;
import com.baidu.mybaidu.dto.VpsRecordForm;
import com.baidu.mybaidu.dto.Vpsform;
import com.baidu.mybaidu.service.ApplyVpsService;
import com.baidu.mybaidu.service.VPSRecordeService;
import com.baidu.mybaidu.service.VPSService;
import com.baidu.mybaidu.service.VPSTypeService;
import com.baidu.mybaidu.utils.ShellUtils;
import com.sun.org.apache.xpath.internal.operations.Bool;
import org.apache.commons.lang3.StringUtils;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ApplyVpsServiceImpl implements ApplyVpsService {
    private static final Logger logger = Logger.getLogger(ApplyVpsServiceImpl.class);
    @Value("${certcode}")
    private String certCode;
    @Autowired
    VPSTypeService vpsTypeService;
    @Autowired
    VPSService vpsService;
    @Autowired
    VPSRecordeService vpsRecordeService;
    @Override
    public Map<String,Object> applyVps(ApplyVpsDto applyVpsDto) throws Exception {
        Integer monthAmount;
        Map<String,Object> returnResult = new HashMap<>();
        //用户名校验
        if(StringUtils.isEmpty(applyVpsDto.getUserName())){
            logger.error("userNme为空");
            throw new Exception("userName为空");
        }
        //验证码校验
        String[] certCodeList = certCode.split(",");
        Boolean existCertCode = false;
        for(String singleCertCode: certCodeList){
            if(singleCertCode.equals(applyVpsDto.getCertCode())){
                existCertCode =true;
                break;
            }
        }
        if(!existCertCode){
            returnResult.put("errno","-2");
            returnResult.put("msg","验证码不存在");
            return returnResult;
        }
        //获取套餐流量
        try{
            Map<String ,Object> vpsType = getVpsType(applyVpsDto);
            if(!CollectionUtils.isEmpty(vpsType)){
                monthAmount = (Integer) vpsType.get("amount");
                applyVpsDto.setMonthAmount(monthAmount);
            }
        }catch (Exception e){
            returnResult.put("errno","-2");
            returnResult.put("msg","该套餐不存在");
            return returnResult;
        }
        //查vps表获取port信息
        Map<String,Object> vps = getVps(null,null);
        if(!CollectionUtils.isEmpty(vps)){
            if(vps.get("ip") != null && vps.get("port") != null && vps.get("password")!= null){
                applyVpsDto.setIp(vps.get("ip").toString());
                applyVpsDto.setPort((Integer) vps.get("port"));
                applyVpsDto.setPassword(vps.get("password").toString());
            }else{
                returnResult.put("errno","-2");
                returnResult.put("msg","vps表ip与port信息不全");
                return returnResult;
            }

        }else{
            returnResult.put("errno","-2");
            returnResult.put("msg","获取vps信息为空");
            return returnResult;
        }
        //执行shell
        //todo执行shell
        Boolean shellSuccess;
        try{
            shellSuccess = execShellToRestrict(applyVpsDto);
        }catch (Exception e){
            returnResult.put("errno","-2");
            returnResult.put("msg","系统错误，请联系管理员处理");
            return returnResult;
        }
        if(shellSuccess){
            //新建vpsrecorde数据插入表中
            VpsRecordForm vpsRecordForm = new VpsRecordForm();
            Boolean insertResult;
            Boolean updateResult;
            try {
                Integer useTime = Integer.valueOf(applyVpsDto.getUseTime()) * 30;
                vpsRecordForm.setUseTime(useTime.toString());
                vpsRecordForm.setVpsId(vps.get("id").toString());
                vpsRecordForm.setUserName(applyVpsDto.getUserName());
                Integer useAmount = Integer.valueOf(applyVpsDto.getUseTime()) * applyVpsDto.getMonthAmount();
                vpsRecordForm.setUseAmount(useAmount.toString());
                vpsRecordForm.setType(applyVpsDto.getVpsType());
                vpsRecordForm.setPort(applyVpsDto.getPort().toString());
                vpsRecordForm.setIp(applyVpsDto.getIp());
                vpsRecordForm.setCertCode(applyVpsDto.getCertCode());
                vpsRecordForm.setPassword(applyVpsDto.getPassword());
                insertResult = vpsRecordeService.insert(vpsRecordForm);
                Vpsform vpsform = new Vpsform();
                vpsform.setStatus("2");
                vpsform.setId(vps.get("id").toString());
                updateResult = vpsService.update(vpsform);
            }catch (Exception e){
                returnResult.put("errno","-2");
                returnResult.put("msg","数据库异常，请联系管理员手动处理");
                return returnResult;
            }
            if(insertResult && updateResult){
                returnResult.put("errno","0");
                returnResult.put("msg","操作成功");
                Map<String,Object> data = new HashMap<>();
                data.put("port",applyVpsDto.getPort());
                data.put("password",applyVpsDto.getPassword());
                returnResult.put("data",data);
                return returnResult;
            }else{
                returnResult.put("errno","-2");
                returnResult.put("msg","操作失败");
                return returnResult;
            }
        }else{
            returnResult.put("errno","-2");
            returnResult.put("msg","系统错误，请联系管理员");
            return returnResult;
        }

    }
    private Map<String,Object> getVpsType(ApplyVpsDto applyVpsDto) throws Exception {
        if(StringUtils.isEmpty(applyVpsDto.getVpsType())){
            logger.error("套餐类型参数为空");
            throw new Exception("typeId为空");
        }
        Integer typeId = Integer.valueOf(applyVpsDto.getVpsType());
        List<Map<String,Object>> dbResult = vpsTypeService.getTypeByConds(typeId,null);
        return CollectionUtils.isEmpty(dbResult)? null:dbResult.get(0);
    }
    private Map<String,Object> getVps(Integer id,Integer port){
        List<Map<String,Object>> dbResult = vpsService.getByConds(id,port);
        return CollectionUtils.isEmpty(dbResult)? null:dbResult.get(0);
    }
    private Boolean execShellToRestrict(ApplyVpsDto applyVpsDto) throws Exception {
        if(StringUtils.isEmpty(applyVpsDto.getIp()) || applyVpsDto.getPort() == null){
            throw new Exception("执行shell必要参数为空");
        }
        try {
            String portRestrictFilePath = this.getClass().getClassLoader().getResource("/shell/portrestrict.sh").getPath();
            String amountRestrictFilePath = this.getClass().getClassLoader().getResource("/shell/amountrestrict.sh").getPath();
            String port = applyVpsDto.getPort().toString();
            Integer amount = Integer.valueOf(applyVpsDto.getUseTime())*applyVpsDto.getMonthAmount();
            Long transAmount = Long.valueOf(amount)*1000000;
            String stringTransAmount = transAmount.toString();
            String[] portRestrictParam = new String[]{port};
            String[] amountRestrictParam = new String[]{port,stringTransAmount};
            ShellUtils.execShell(portRestrictFilePath,portRestrictParam);
            ShellUtils.execShell(amountRestrictFilePath,amountRestrictParam);
        }catch (Exception e){
            e.printStackTrace();
            throw e;
        }
        return true;
    }
}
