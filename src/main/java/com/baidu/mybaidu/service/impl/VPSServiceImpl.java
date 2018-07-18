package com.baidu.mybaidu.service.impl;

import com.baidu.mybaidu.dao.VpsDao;
import com.baidu.mybaidu.dto.Vpsform;
import com.baidu.mybaidu.pojo.Vps;
import com.baidu.mybaidu.service.VPSService;
import org.apache.commons.lang3.StringUtils;


import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class VPSServiceImpl implements VPSService {

    private static final Logger logger = Logger.getLogger(VPSServiceImpl.class);
    @Autowired
    private VpsDao vpsDao;
    @Override
    public Boolean insert(Vpsform vpsform) {
        Vps vps = new Vps();
        if(StringUtils.isEmpty(vpsform.getIp()) || StringUtils.isEmpty(vpsform.getPort()) ||
                StringUtils.isEmpty(vpsform.getPassword())){
            return false;
        }
        vps.setIp(vpsform.getIp());
        vps.setPort(Integer.valueOf(vpsform.getPort()));
        vps.setPassword(vpsform.getPassword());
        vps.setCreate_time(System.currentTimeMillis());
        vps.setUpdate_time(System.currentTimeMillis());
        try{
            vpsDao.insert(vps);
        }catch (Exception e){
            logger.error("写数据库错误");
            return false;
        }
        return true;
    }

    @Override
    public List<Map<String, Object>> getByConds(Integer id, Integer port) {
        return vpsDao.getByConds(port,id);
    }

    @Override
    public Boolean update(Vpsform vpsform) {
        if(StringUtils.isEmpty(vpsform.getId())){
            return false;
        }
        Vps vps = new Vps();
        if(StringUtils.isNotEmpty(vpsform.getIp())){
            vps.setIp(vpsform.getIp());
        }
        vps.setId(Integer.valueOf(vpsform.getId()));
        if(StringUtils.isNotEmpty(vpsform.getPassword())){
            vps.setPassword(vpsform.getPassword());
        }
        if(StringUtils.isNotEmpty(vpsform.getPort())){
            vps.setPort(Integer.valueOf(vpsform.getPort()));
        }
        if(StringUtils.isNotEmpty(vpsform.getDeleteFlag())){
            vps.setDelete_flag(Integer.valueOf(vpsform.getDeleteFlag()));
        }
        if(StringUtils.isNotEmpty(vpsform.getStatus())){
            vps.setStatus(Integer.valueOf(vpsform.getStatus()));
        }
        vps.setUpdate_time(System.currentTimeMillis());
        vpsDao.update(vps);
        return true;
    }
}
