package com.baidu.mybaidu.pojo;


import net.sf.json.JSONObject;

public class Shadowsocks {
    private String server;
    private String local_address;
    private Integer local_port;
    private JSONObject port_password;
    private Integer timeout;
    private  String method;
    private Boolean fast_open;

    public String getServer() {
        return server;
    }

    public void setServer(String server) {
        this.server = server;
    }

    public String getLocal_address() {
        return local_address;
    }

    public void setLocal_address(String local_address) {
        this.local_address = local_address;
    }

    public Integer getLocal_port() {
        return local_port;
    }

    public void setLocal_port(Integer local_port) {
        this.local_port = local_port;
    }

    public JSONObject getPort_password() {
        return port_password;
    }

    public void setPort_password(JSONObject port_password) {
        this.port_password = port_password;
    }

    public Integer getTimeout() {
        return timeout;
    }

    public void setTimeout(Integer timeout) {
        this.timeout = timeout;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public Boolean getFast_open() {
        return fast_open;
    }

    public void setFast_open(Boolean fast_open) {
        this.fast_open = fast_open;
    }
}
