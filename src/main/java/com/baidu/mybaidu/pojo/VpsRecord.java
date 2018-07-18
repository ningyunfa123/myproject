package com.baidu.mybaidu.pojo;

public class VpsRecord {
    private Integer id;
    private Integer port;
    private String ip;
    private String password;
    private String user_name;
    private Integer status;
    private Integer use_time;
    private Integer type;
    private Integer delete_flag;
    private Long create_time;
    private Long update_time;
    private Integer vps_id;
    private Integer use_amount;
    private String cert_code;

    public Integer getUse_amount() {
        return use_amount;
    }

    public void setUse_amount(Integer use_amount) {
        this.use_amount = use_amount;
    }

    public String getCert_code() {
        return cert_code;
    }

    public void setCert_code(String cert_code) {
        this.cert_code = cert_code;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getUse_time() {
        return use_time;
    }

    public void setUse_time(Integer use_time) {
        this.use_time = use_time;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getDelete_flag() {
        return delete_flag;
    }

    public void setDelete_flag(Integer delete_flag) {
        this.delete_flag = delete_flag;
    }

    public Long getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Long create_time) {
        this.create_time = create_time;
    }

    public Long getUpdate_time() {
        return update_time;
    }

    public void setUpdate_time(Long update_time) {
        this.update_time = update_time;
    }

    public Integer getVps_id() {
        return vps_id;
    }

    public void setVps_id(Integer vps_id) {
        this.vps_id = vps_id;
    }
}
