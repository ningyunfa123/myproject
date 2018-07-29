package com.baidu.mybaidu.dto;

import org.hibernate.validator.constraints.NotEmpty;

public class UserForm {
    @NotEmpty(message = "用户名不为空")
    private String userName;
    @NotEmpty(message = "密码不为空")
    private String password;
    private String trueName;

    public String getTrueName() {
        return trueName;
    }

    public void setTrueName(String trueName) {
        this.trueName = trueName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
