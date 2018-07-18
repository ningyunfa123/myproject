package com.baidu.mybaidu.service.impl;

import com.baidu.mybaidu.dao.VpsRecordDao;
import com.baidu.mybaidu.dto.VpsRecordForm;
import com.baidu.mybaidu.pojo.VpsRecord;
import com.baidu.mybaidu.service.VPSRecordeService;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class VPSRecordServiceImpl implements VPSRecordeService {
    @Autowired
    private VpsRecordDao vpsRecordDao;
    private static final Logger logger = Logger.getLogger(VPSRecordServiceImpl.class);
    @Override
    public Boolean insert(VpsRecordForm vpsRecordForm) {
        if(StringUtils.isEmpty(vpsRecordForm.getCertCode()) || StringUtils.isEmpty(vpsRecordForm.getUseAmount()) || StringUtils.isEmpty(vpsRecordForm.getUseTime())
                || StringUtils.isEmpty(vpsRecordForm.getUserName()) || StringUtils.isEmpty(vpsRecordForm.getPort()) || StringUtils.isEmpty(vpsRecordForm.getType())
                || StringUtils.isEmpty(vpsRecordForm.getVpsId()) || StringUtils.isEmpty(vpsRecordForm.getPassword())){
            logger.error("必要参数为空！");
            return false;
        }
        VpsRecord vpsRecord = new VpsRecord();
        vpsRecord.setUse_amount(Integer.valueOf(vpsRecordForm.getUseAmount()));
        vpsRecord.setVps_id(Integer.valueOf(vpsRecordForm.getVpsId()));
        vpsRecord.setUser_name(vpsRecordForm.getUserName());
        vpsRecord.setUse_time(Integer.valueOf(vpsRecordForm.getUseTime()));
        vpsRecord.setType(Integer.valueOf(vpsRecordForm.getType()));
        vpsRecord.setPort(Integer.valueOf(vpsRecordForm.getPort()));
        vpsRecord.setCert_code(vpsRecordForm.getCertCode());
        vpsRecord.setPassword(vpsRecordForm.getPassword());
        if(StringUtils.isNotEmpty(vpsRecordForm.getIp())){
            vpsRecord.setIp(vpsRecordForm.getIp());
        }else
            vpsRecord.setIp("127.0.0.1");
        vpsRecord.setCreate_time(System.currentTimeMillis());
        vpsRecord.setUpdate_time(System.currentTimeMillis());
        try{
            vpsRecordDao.insert(vpsRecord);
        }catch (Exception e){
            e.printStackTrace();
            logger.error("写数据库异常");
            return false;
        }
        return true;
    }

    @Override
    public Boolean update(VpsRecordForm vpsRecordForm) {
        if(StringUtils.isEmpty(vpsRecordForm.getId())){
            System.out.println("id为空");
            logger.error("主键id为空！");
            return false;
        }
        VpsRecord vpsRecord = new VpsRecord();
        try {
            vpsRecord.setId(Integer.valueOf(vpsRecordForm.getId()));
            if (StringUtils.isNotEmpty(vpsRecordForm.getPort()))
                vpsRecord.setPort(Integer.valueOf(vpsRecordForm.getPort()));
            if (StringUtils.isNotEmpty(vpsRecordForm.getIp()))
                vpsRecord.setIp(vpsRecordForm.getIp());
            if (StringUtils.isNotEmpty(vpsRecordForm.getPassword()))
                vpsRecord.setPassword(vpsRecordForm.getPassword());
            if (StringUtils.isNotEmpty(vpsRecordForm.getUserName()))
                vpsRecord.setUser_name(vpsRecordForm.getUserName());
            if (StringUtils.isNotEmpty(vpsRecordForm.getStatus()))
                vpsRecord.setStatus(Integer.valueOf(vpsRecordForm.getStatus()));
            if (StringUtils.isNotEmpty(vpsRecordForm.getUseTime()))
                vpsRecord.setUse_time(Integer.valueOf(vpsRecordForm.getUseTime()));
            if (StringUtils.isNotEmpty(vpsRecordForm.getType()))
                vpsRecord.setType(Integer.valueOf(vpsRecordForm.getType()));
            if (StringUtils.isNotEmpty(vpsRecordForm.getDeleteFlag()))
                vpsRecord.setDelete_flag(Integer.valueOf(vpsRecordForm.getDeleteFlag()));
            vpsRecord.setUpdate_time(System.currentTimeMillis());
            if (StringUtils.isNotEmpty(vpsRecordForm.getVpsId()))
                vpsRecord.setVps_id(Integer.valueOf(vpsRecordForm.getVpsId()));
            if (StringUtils.isNotEmpty(vpsRecordForm.getUseAmount()))
                vpsRecord.setUse_amount(Integer.valueOf(vpsRecordForm.getUseAmount()));
            if (StringUtils.isNotEmpty(vpsRecordForm.getCertCode()))
                vpsRecord.setCert_code(vpsRecordForm.getCertCode());
            vpsRecordDao.update(vpsRecord);
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("写数据库失败");
            logger.error("更新数据库失败！");
            return false;
        }
        return true;
    }

    @Override
    public List<Map<String, Object>> getByConds(Integer id, Integer port, String certCode, String userName) {
        return vpsRecordDao.getByConds(id,port,certCode,userName);
    }
}
