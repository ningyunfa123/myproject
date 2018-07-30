package com.baidu.mybaidu.service.impl;

import com.baidu.mybaidu.dto.ApplyVpsDto;
import com.baidu.mybaidu.dto.VpsRecordForm;
import com.baidu.mybaidu.dto.Vpsform;
import com.baidu.mybaidu.service.ApplyVpsService;
import com.baidu.mybaidu.service.VPSRecordeService;
import com.baidu.mybaidu.service.VPSService;
import com.baidu.mybaidu.service.VPSTypeService;
import com.baidu.mybaidu.utils.FileUtils;
import com.baidu.mybaidu.utils.ShellUtils;
import org.apache.commons.lang3.StringUtils;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ApplyVpsServiceImpl implements ApplyVpsService {
    private static final Logger logger = Logger.getLogger(ApplyVpsServiceImpl.class);
    @Value("${menu1}")
    private String certCode1FilePath;
    @Value("${menu2}")
    private String certCode2FilePath;
    @Value("${menu3}")
    private String certCode3FilePath;
    @Value("${amount_menu1}")
    private Integer menu1Amount;
    @Value("${amount_menu2}")
    private Integer menu2Amount;
    @Value("${amount_menu3}")
    private Integer menu3Amount;
    @Autowired
    VPSTypeService vpsTypeService;
    @Autowired
    VPSService vpsService;
    @Autowired
    VPSRecordeService vpsRecordeService;
    @Override
    public Map<String,Object> applyVps(ApplyVpsDto applyVpsDto) throws Exception {
        Map<String,Object> returnResult = new HashMap<>();
        //用户名校验
        if(StringUtils.isEmpty(applyVpsDto.getUserName())){
            logger.error("userNme为空");
            throw new Exception("userName为空");
        }
        //验证码校验
        String[] certCodeList;// = getCertCodeArray(certCodeFilePath);
        if(applyVpsDto.getVpsType().equals("1")){
            certCodeList = getCertCodeArray(certCode1FilePath);
        }else if(applyVpsDto.getVpsType().equals("2")){
            certCodeList = getCertCodeArray(certCode2FilePath);
        }else{
            certCodeList = getCertCodeArray(certCode3FilePath);
        }
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
        //校验验证码是否已使用
        VpsRecordForm vpsRecordForm1 = new VpsRecordForm();
        vpsRecordForm1.setPassword(applyVpsDto.getCertCode());
        List<Map<String,Object>> dbVpsRecord = vpsRecordeService.getByConds(null,null,applyVpsDto.getCertCode(),null);
        if(!CollectionUtils.isEmpty(dbVpsRecord)){
            if(dbVpsRecord.get(0).get("user_name").toString().equals(applyVpsDto.getUserName())){
                returnResult.put("errno","0");
                returnResult.put("msg","success");
                Map<String,Object> data = new HashMap<>();
                data.put("port",dbVpsRecord.get(0).get("port"));
                data.put("password",dbVpsRecord.get(0).get("password"));
                returnResult.put("data",data);
                return returnResult;
            }else{
                returnResult.put("errno","-2");
                returnResult.put("msg","验证码已使用");
                return returnResult;
            }
        }
        //获取套餐流量
        switch (applyVpsDto.getVpsType()) {
            case "1":
                applyVpsDto.setMonthAmount(menu1Amount);
                break;
            case "2":
                applyVpsDto.setMonthAmount(menu2Amount);
                break;
            case "3":
                applyVpsDto.setMonthAmount(menu3Amount);
                break;
            default:
                logger.fatal("vpsType不存在。");
                throw new Exception("该套餐不存在。");
        }
        //查vps表获取port信息
        Map<String,Object> vps = getVps(null,null,1,0);
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
            returnResult.put("msg","vps账号已售罄，请联系管理员退款");
            return returnResult;
        }
        //执行shell
        //todo执行shell
        Boolean shellSuccess;
        try{
            shellSuccess = execShellToRestrictPort(applyVpsDto);
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
    private Map<String,Object> getVps(Integer id,Integer port,Integer useStatus,Integer deleteFlag){
        List<Map<String,Object>> dbResult = vpsService.getByConds(id,port,useStatus,deleteFlag);
        return CollectionUtils.isEmpty(dbResult)? null:dbResult.get(0);
    }
    private Boolean execShellToRestrictPort(ApplyVpsDto applyVpsDto) throws Exception {
        if(StringUtils.isEmpty(applyVpsDto.getIp()) || applyVpsDto.getPort() == null){
            throw new Exception("执行shell必要参数为空");
        }
        try {
            String portRestrictFilePath = this.getClass().getClassLoader().getResource("/shell/portrestrict.sh").getPath();
            logger.fatal("portRestrictFilePath"+portRestrictFilePath);
            String port = applyVpsDto.getPort().toString();
            ShellUtils.execShell(portRestrictFilePath,port);
        }catch (Exception e){
            logger.fatal("用户ss帐号申请shell执行出错");
            //暂不处理
            e.printStackTrace();
            throw e;
        }
        return true;
    }
    private String[] getCertCodeArray(String path) throws IOException {
        return FileUtils.getFileContents(path).split(",");
    }
}
