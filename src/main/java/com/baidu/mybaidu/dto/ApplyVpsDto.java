package com.baidu.mybaidu.dto;

import com.baidu.mybaidu.utils.sign.SignParam;
import org.hibernate.validator.constraints.NotEmpty;

public class ApplyVpsDto {
    @SignParam
    @NotEmpty(message = "certCode is required")
    private String certCode;
    @SignParam
    @NotEmpty(message = "vpsType is required")
    private String vpsType;
    @SignParam
    @NotEmpty(message = "useTime is required")
    private String useTime;
    private String password;
    private Integer monthAmount;
    private String userName;
    private String ip;
    private Integer port;
    @NotEmpty
    private String sign;

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUseTime() {
        return useTime;
    }

    public void setUseTime(String useTime) {
        this.useTime = useTime;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public Integer getMonthAmount() {
        return monthAmount;
    }

    public void setMonthAmount(Integer monthAmount) {
        this.monthAmount = monthAmount;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getCertCode() {
        return certCode;
    }

    public void setCertCode(String certCode) {
        this.certCode = certCode;
    }

    public String getVpsType() {
        return vpsType;
    }

    public void setVpsType(String vpsType) {
        this.vpsType = vpsType;
    }
}
