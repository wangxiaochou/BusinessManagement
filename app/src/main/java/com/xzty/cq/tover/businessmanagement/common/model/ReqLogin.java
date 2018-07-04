package com.xzty.cq.tover.businessmanagement.common.model;

/**
 * author zzl
 * Created 2018/5/3.
 * explain 登录接口需要的请求参数
 */

public class ReqLogin {
    private String username;
    private String password;

    public ReqLogin() {
    }

    public ReqLogin(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
