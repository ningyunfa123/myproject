package com.baidu.mybaidu.service;

import com.baidu.mybaidu.dto.VpsTypeForm;

import java.util.List;
import java.util.Map;

public interface VPSTypeService {
    Boolean insert(VpsTypeForm vpsTypeForm);
    Boolean update(VpsTypeForm vpsTypeForm);
    List<Map<String,Object>> getTypeByConds(Integer typeId,Integer amount);
}
