package com.baidu.mybaidu.dao;

import com.baidu.mybaidu.pojo.Vps;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface VpsDao {
    void insert(Vps vps);
    List<Map<String,Object>> getByConds(@Param("port") Integer port,@Param("id") Integer id,@Param("use_status")Integer use_status,
                                        @Param("delete_flag") Integer deleteFlag);
    void update(Vps vps);
}
