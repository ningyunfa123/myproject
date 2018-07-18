package com.baidu.mybaidu.service.impl;

import com.baidu.mybaidu.dao.VpsTypeDao;
import com.baidu.mybaidu.dto.VpsTypeForm;
import com.baidu.mybaidu.pojo.VpsType;
import com.baidu.mybaidu.service.VPSTypeService;
import org.apache.commons.lang3.StringUtils;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class VPSTypeServiceImpl implements VPSTypeService {

    private static final Logger logger = Logger.getLogger(VPSTypeServiceImpl.class);
    @Autowired
    VpsTypeDao vpsTypeDao;
    @Override
    public Boolean insert(VpsTypeForm vpsTypeForm) {
        if(StringUtils.isEmpty(vpsTypeForm.getAmount())){
            logger.error("amount不能为空！");
            return false;
        }
        VpsType vpsType = new VpsType();

        try {
            vpsType.setAmount(Integer.valueOf(vpsTypeForm.getAmount()));
            vpsType.setCreate_time(System.currentTimeMillis());
            vpsType.setUpdate_time(System.currentTimeMillis());
            vpsTypeDao.insert(vpsType);
        }catch(Exception e){
            e.printStackTrace();
            logger.error("些数据失败！");
            return false;
        }
        return true;
    }

    @Override
    public Boolean update(VpsTypeForm vpsTypeForm) {
        if(StringUtils.isEmpty(vpsTypeForm.getTypeId())){
            return false;
        }

        VpsType vpsType = new VpsType();
        try {
            vpsType.setType_id(Integer.valueOf(vpsTypeForm.getTypeId()));
            if (StringUtils.isNotEmpty(vpsTypeForm.getAmount())) {
                vpsType.setAmount(Integer.valueOf(vpsTypeForm.getAmount()));
            }
            if (StringUtils.isNotEmpty(vpsTypeForm.getStatus())) {
                vpsType.setStatus(Integer.valueOf(vpsTypeForm.getStatus()));
            }
            if (StringUtils.isNotEmpty(vpsTypeForm.getDeleteFlag())) {
                vpsType.setDelete_flag(Integer.valueOf(vpsTypeForm.getDeleteFlag()));
            }
            vpsTypeDao.update(vpsType);
        }catch (Exception e){
            e.printStackTrace();
            logger.error("更新vpsType失败");
            return false;
        }
        return true;
    }

    @Override
    public List<Map<String, Object>> getTypeByConds(Integer typeId, Integer amount) {

        return vpsTypeDao.getByConds(typeId,amount);
    }
}
