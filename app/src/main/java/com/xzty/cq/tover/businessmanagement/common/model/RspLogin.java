package com.xzty.cq.tover.businessmanagement.common.model;

import java.util.Set;

/**
 * author zzl
 * Created 2018/5/3.
 * explain 登录接口返回的参数
 */

public class RspLogin {
    private String employId;
    private Set<String> rules;//角色权限集合

    public RspLogin() {
    }
    public String getEmployId() {
        return employId;
    }

    public void setEmployId(String employId) {
        this.employId = employId;
    }

    public Set<String> getRules() {
        return rules;
    }

    public void setRules(Set<String> rules) {
        this.rules = rules;
    }
}
