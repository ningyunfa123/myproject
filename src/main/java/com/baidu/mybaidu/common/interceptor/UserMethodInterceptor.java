package com.baidu.mybaidu.common.interceptor;

import com.baidu.mybaidu.dao.UserDao;
import com.baidu.mybaidu.pojo.User;
import com.baidu.mybaidu.service.UserService;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.apache.commons.lang3.StringUtils;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

public class UserMethodInterceptor implements MethodInterceptor {
    @Autowired
    private UserDao userDao;
    private static final Logger logger = Logger.getLogger(UserMethodInterceptor.class);
    @Override
    public Object invoke(MethodInvocation methodInvocation) throws Throwable {
        Object[] args = methodInvocation.getArguments();
        String userName=null;
        String password=null;
        User user1=null;
        try {
            for (Object arg : args) {
                Method useMethod = arg.getClass().getMethod("getUserName");
                userName = (String) useMethod.invoke(arg);
                if (StringUtils.isNotEmpty(userName)) {
                    break;
                }
            }
            for (Object arg : args) {
                Method useMethod = arg.getClass().getMethod("getPassword");
                password = (String) useMethod.invoke(arg);
                if (StringUtils.isNotEmpty(password)) {
                    break;
                }
            }
            User user = new User();
            user.setUserName(userName);
            user.setPassword(password);
            logger.error("testLogin");
            user1 = userDao.login(user);
        }catch (Exception e){

        }
        if(user1 != null) {
            UserHolder.setUser(user1);
        }
        Object obj = methodInvocation.proceed();
        return obj;
    }
}
