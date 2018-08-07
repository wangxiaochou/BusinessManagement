package com.xzty.cq.tover.businessmanagement.common.model;

import com.google.gson.annotations.SerializedName;

/**
 * @Author yq
 * @Date 2018/8/2
 * @Explain 员工模型
 */
public class EmployeeModel {

    //员工编号
    @SerializedName("eply_num")
    private String eplyNum;

    //员工姓名
    @SerializedName("eply_name")
    private String eplyName;

    public String getEplyNum() {
        return eplyNum;
    }

    public void setEplyNum(String eplyNum) {
        this.eplyNum = eplyNum;
    }

    public String getEplyName() {
        return eplyName;
    }

    public void setEplyName(String eplyName) {
        this.eplyName = eplyName;
    }

    @Override
    public String toString() {
        return "EmployeeModel{" +
                "eplyNum='" + eplyNum + '\'' +
                ", eplyName='" + eplyName + '\'' +
                '}';
    }
}
