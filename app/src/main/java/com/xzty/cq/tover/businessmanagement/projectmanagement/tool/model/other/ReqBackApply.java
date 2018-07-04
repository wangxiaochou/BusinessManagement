package com.xzty.cq.tover.businessmanagement.projectmanagement.tool.model.other;

import java.util.List;

/**
 * author zzl
 * Created 2018/5/26.
 * explain
 */

public class ReqBackApply {

    private String note, eplyId;
    private String projectId;
    private List<Integer> detailIdList;

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getEplyId() {
        return eplyId;
    }

    public void setEplyId(String eplyId) {
        this.eplyId = eplyId;
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
