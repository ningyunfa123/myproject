package com.baidu.mybaidu.dao;

import com.baidu.mybaidu.pojo.VpsType;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface VpsTypeDao {
    void insert(VpsType vpsType);
    void update(VpsType vpsType);
    List<Map<String,Object>> getByConds(@Param("type_id") Integer type_id,@Param("amount") Integer amount);
}
