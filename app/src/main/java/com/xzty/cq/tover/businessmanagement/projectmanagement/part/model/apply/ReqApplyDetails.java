package com.xzty.cq.tover.businessmanagement.projectmanagement.part.model.apply;

import java.util.Date;

/**
 * author zzl
 * Created 2018/5/10.
 * explain 申请详情
 */

public class ReqApplyDetails {
    private Integer applyId;
    private String applyNo;
    private Date applyTime;
    private String applyUserId;
    private String applyNote;
    private Integer applyBatch;
    private Integer projectId;
    private Byte isComfirm=0;
    private Byte isDelete;
    private Date deleteTime;
    private String eplyName;
    public Integer getApplyId() {
        return applyId;
    }

    public void setApplyId(Integer applyId) {
        this.applyId = applyId;
    }

    public String getApplyNo() {
        return applyNo;
    }

    public void setApplyNo(String applyNo) {
        this.applyNo = applyNo;
    }

    public Date getApplyTime() {
        return applyTime;
    }

    public void setApplyTime(Date applyTime) {
        this.applyTime = applyTime;
    }

    public String getApplyUserId() {
        return applyUserId;
    }

    public void setApplyUserId(String applyUserId) {
        this.applyUserId = applyUserId;
    }

    public String getApplyNote() {
        return applyNote;
    }

    public void setApplyNote(String applyNote) {
        this.applyNote = applyNote;
    }

    public Integer getApplyBatch() {
        return applyBatch;
    }

    public void setApplyBatch(Integer applyBatch) {
        this.applyBatch = applyBatch;
    }

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    public Byte getIsComfirm() {
        return isComfirm;
    }

    public void setIsComfirm(Byte isComfirm) {
        this.isComfirm = isComfirm;
    }

    public Byte getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Byte isDelete) {
        this.isDelete = isDelete;
    }

    public Date getDeleteTime() {
        return deleteTime;
    }

    public void setDeleteTime(Date deleteTime) {
        this.deleteTime = deleteTime;
    }

    public String getEplyName() {
        return eplyName;
    }

    public void setEplyName(String eplyName) {
        this.eplyName = eplyName;
    }
}
