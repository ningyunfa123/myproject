package com.baidu.mybaidu.service.impl;

import com.baidu.mybaidu.dao.UserDao;
import com.baidu.mybaidu.service.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class Testimpl implements Test {
    @Autowired
    UserDao userDao;
    @Override
    public List<Map<String, Object>> Test() {
        //return userDao.queryALLUser();
        return null;
    }
}
