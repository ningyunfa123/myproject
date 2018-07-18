package com.baidu.mybaidu.common.interceptor;

import com.baidu.mybaidu.pojo.User;

public class UserHolder {
    private static final ThreadLocal<User> currentThread = new ThreadLocal<>();
    public static User getUser(){
        return currentThread.get();
    }
    public static void setUser(User user){
        currentThread.set(user);
    }
    public static void remove(){
        currentThread.remove();
    }
}
