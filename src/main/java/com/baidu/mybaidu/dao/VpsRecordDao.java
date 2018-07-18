package com.baidu.mybaidu.dao;

import com.baidu.mybaidu.pojo.VpsRecord;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface VpsRecordDao {
    List<Map<String,Object>> getByConds(@Param("id") Integer id,@Param("port") Integer port,@Param("cert_code")
            String cert_code,@Param("user_name") String user_name);
    void update(VpsRecord vpsRecord);
    void insert(VpsRecord vpsRecord);
}
