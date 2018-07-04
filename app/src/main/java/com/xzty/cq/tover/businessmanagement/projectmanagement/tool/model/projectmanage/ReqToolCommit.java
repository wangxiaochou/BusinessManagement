package com.xzty.cq.tover.businessmanagement.projectmanagement.tool.model.projectmanage;

import java.util.List;

/**
 * author zzl
 * Created 2018/5/24.
 * explain
 */

public class ReqToolCommit {
    private int applyId;
    private List<Integer> checkedDetailIdList;

    public int getApplyId() {
        return applyId;
    }

    public void setApplyId(int applyId) {
        this.applyId = applyId;
    }

    public List<Integer> getCheckedDetailIdList() {
        return checkedDetailIdList;
    }

    public void setCheckedDetailIdList(List<Integer> checkedDetailIdList) {
        this.checkedDetailIdList = checkedDetailIdList;
    }
}
