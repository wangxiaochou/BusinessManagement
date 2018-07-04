package com.xzty.cq.tover.businessmanagement.projectmanagement.tool.model.pickdepartment;

/**
 * author zzl
 * Created 2018/5/24.
 * explain
 */

public class RspUser {
    private String EplyNum,
            EplyName;

    public RspUser(String eplyNum, String eplyName) {
        EplyNum = eplyNum;
        EplyName = eplyName;
    }
    public String getEplyNum() {
        return EplyNum;
    }

    public void setEplyNum(String eplyNum) {
        EplyNum = eplyNum;
    }

    public String getEplyName() {
        return EplyName;
    }

    public void setEplyName(String eplyName) {
        EplyName = eplyName;
    }
}
