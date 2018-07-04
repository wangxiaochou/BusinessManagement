package com.xzty.cq.tover.businessmanagement.projectmanagement.tool.model.pickdepartment;

import java.util.List;

/**
 * author zzl
 * Created 2018/5/25.
 * explain
 */

public class ReqDiliverOrder {
    private String projectId;
    private String note,
            employId,
            firm,
            contractNumber,
            expectOutTime;
    private List<OutToolId> outList;

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getEmployId() {
        return employId;
    }

    public void setEmployId(String employId) {
        this.employId = employId;
    }

    public String getFirm() {
        return firm;
    }

    public void setFirm(String firm) {
        this.firm = firm;
    }

    public String getContractNumber() {
        return contractNumber;
    }

    public void setContractNumber(String contractNumber) {
        this.contractNumber = contractNumber;
    }

    public String getExpectOutTime() {
        return expectOutTime;
    }

    public void setExpectOutTime(String expectOutTime) {
        this.expectOutTime = expectOutTime;
    }

    public List<OutToolId> getOutList() {
        return outList;
    }

    public void setOutList(List<OutToolId> outList) {
        this.outList = outList;
    }
}
