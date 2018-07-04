package com.xzty.cq.tover.businessmanagement.projectmanagement.tool.model.back;

import java.util.List;

/**
 * author zzl
 * Created 2018/5/28.
 * explain
 */

public class ReqAuditDetails {
    private int toolBackId;
    private List<Integer> backDetailIdList;

    public int getToolBackId() {
        return toolBackId;
    }

    public void setToolBackId(int toolBackId) {
        this.toolBackId = toolBackId;
    }

    public List<Integer> getBackDetailIdList() {
        return backDetailIdList;
    }

    public void setBackDetailIdList(List<Integer> backDetailIdList) {
        this.backDetailIdList = backDetailIdList;
    }
}
