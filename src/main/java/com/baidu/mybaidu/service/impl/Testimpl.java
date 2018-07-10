package com.baidu.mybaidu.service.impl;

import com.baidu.mybaidu.dao.UserDao;
import com.baidu.mybaidu.service.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class Testimpl implements Test {

    @Value("${test}")
    private String tr1;
    @Autowired
    UserDao userDao;
    @Override
    public List<Map<String, Object>> Test() {
        List result = userDao.queryAllUser();
        Map<String,Object> map = new HashMap<>();
        map.put("test",tr1);
        result.add(map);
        return result;

    }
}
