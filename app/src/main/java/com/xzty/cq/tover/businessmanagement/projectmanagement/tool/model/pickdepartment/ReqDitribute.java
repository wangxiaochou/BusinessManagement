package com.xzty.cq.tover.businessmanagement.projectmanagement.tool.model.pickdepartment;

import java.util.List;

/**
 * author zzl
 * Created 2018/5/29.
 * explain
 */

public class ReqDitribute {
    private String buyerEplyNum, note, eplyNum;
    private String projectId;
    private List<Integer> detailIdList;

    public String getBuyerEplyNum() {
        return buyerEplyNum;
    }

    public void setBuyerEplyNum(String buyerEplyNum) {
        this.buyerEplyNum = buyerEplyNum;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getEplyNum() {
        return eplyNum;
    }

    public void setEplyNum(String eplyNum) {
        this.eplyNum = eplyNum;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public List<Integer> getDetailIdList() {
        return detailIdList;
    }

    public void setDetailIdList(List<Integer> detailIdList) {
        this.detailIdList = detailIdList;
    }
}
