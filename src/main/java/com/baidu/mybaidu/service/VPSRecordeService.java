package com.baidu.mybaidu.service;

import com.baidu.mybaidu.dto.VpsRecordForm;

import java.util.List;
import java.util.Map;

public interface VPSRecordeService {
    Boolean insert(VpsRecordForm vpsRecordForm);
    Boolean update(VpsRecordForm vpsRecordForm);
    List<Map<String,Object>> getByConds(Integer id,Integer port,String certCode,String userName);
}
