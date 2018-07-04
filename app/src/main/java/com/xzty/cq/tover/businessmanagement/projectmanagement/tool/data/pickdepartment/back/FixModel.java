package com.xzty.cq.tover.businessmanagement.projectmanagement.tool.data.pickdepartment.back;

import com.xzty.cq.tover.businessmanagement.projectmanagement.tool.model.back.RspAuditDetails;

import java.util.List;

/**
 * author zzl
 * Created 2018/6/13.
 * explain
 */

public class FixModel {
    private List<RspAuditDetails> list;

    private String chooseString;

    public List<RspAuditDetails> getList() {
        return list;
    }

    public void setList(List<RspAuditDetails> list) {
        this.list = list;
    }

    public String getChooseString() {
        return chooseString;
    }

    public void setChooseString(String chooseString) {
        this.chooseString = chooseString;
    }
}
