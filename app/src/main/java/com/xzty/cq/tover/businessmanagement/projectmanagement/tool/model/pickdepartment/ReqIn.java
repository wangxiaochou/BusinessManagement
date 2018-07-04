package com.xzty.cq.tover.businessmanagement.projectmanagement.tool.model.pickdepartment;

import java.util.List;

/**
 * author zzl
 * Created 2018/5/29.
 * explain
 */

public class ReqIn {
    private int distId;
    private List<Integer> distDetailIdList;

    public int getDistId() {
        return distId;
    }

    public void setDistId(int distId) {
        this.distId = distId;
    }

    public List<Integer> getDistDetailIdList() {
        return distDetailIdList;
    }

    public void setDistDetailIdList(List<Integer> distDetailIdList) {
        this.distDetailIdList = distDetailIdList;
    }
}
