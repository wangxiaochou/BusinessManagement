package com.xzty.cq.tover.businessmanagement.projectmanagement.part.model;

import java.io.Serializable;

/**
 * Created by miao on 2017/12/8.
 */

public class Emp implements Serializable {


    private static final long serialVersionUID = -2953164518080955573L;

    private String eplyName;
    private String applyUserId;
    private String employId;

    public Emp() {
    }

    public Emp(String eplyName, String applyUserId) {
        this.eplyName = eplyName;
        this.applyUserId = applyUserId;
    }

    public Emp(String eplyName, String applyUserId, String employId) {
        this.eplyName = eplyName;
        this.applyUserId = applyUserId;
        this.employId = employId;
    }

    @Override
    public String toString() {
        return "Emp{" +
                "eplyName='" + eplyName + '\'' +
                ", applyUserId='" + applyUserId + '\'' +
                ", employId='" + employId + '\'' +
                '}';
    }

    public String getEplyName() {
        return eplyName;
    }

    public void setEplyName(String eplyName) {
        this.eplyName = eplyName;
    }

    public String getApplyUserId() {
        return applyUserId;
    }

    public void setApplyUserId(String applyUserId) {
        this.applyUserId = applyUserId;
    }

    public String getEmployId() {
        return employId;
    }

    public void setEmployId(String employId) {
        this.employId = employId;
    }
}
