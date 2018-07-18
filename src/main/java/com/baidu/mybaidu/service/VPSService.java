package com.baidu.mybaidu.service;

import com.baidu.mybaidu.dto.Vpsform;

import java.util.List;
import java.util.Map;

public interface VPSService {
    Boolean insert(Vpsform vpsform);
    List<Map<String,Object>> getByConds(Integer id,Integer port,Integer useStatus,Integer deleteFlag);
    Boolean update(Vpsform vpsform);
}
