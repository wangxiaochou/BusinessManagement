package com.xzty.cq.tover.businessmanagement.projectmanagement.tool.model.pickdepartment;

/**
 * author zzl
 * Created 2018/5/24.
 * explain
 */

public class RspUser {
    private String eply_num,
            eply_name;

    public RspUser(String eplyNum, String eplyName) {
        eply_num = eplyNum;
        eply_name = eplyName;
    }
    public String getEplyNum() {
        return eply_num;
    }

    public void setEplyNum(String eplyNum) {
        eply_num = eplyNum;
    }

    public String getEplyName() {
        return eply_name;
    }

    public void setEplyName(String eplyName) {
        eply_name = eplyName;
    }
}
