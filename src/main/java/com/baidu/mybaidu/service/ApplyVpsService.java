package com.baidu.mybaidu.service;

import com.baidu.mybaidu.dto.ApplyVpsDto;

import java.util.Map;

public interface ApplyVpsService {
    Map<String,Object> applyVps(ApplyVpsDto applyVpsDto) throws Exception;
}
