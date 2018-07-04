package com.xzty.cq.tover.businessmanagement.projectmanagement.part.model.apply;

import com.google.gson.annotations.SerializedName;

/**
 * author zzl
 * Created 2018/5/5.
 * explain 申请列表返回的参数
 */

public class RspApplyList {
    @SerializedName("APPLY_BATCH")
    private int applyBatch;
    @SerializedName("APPLY_ID")
    private int applyId;
    @SerializedName("APPLY_NO")
    private String applyNo;
    @SerializedName("APPLY_TIME")
    private String applyTime;
    @SerializedName("APPLY_USER_ID")
    private String applyUserId;
    @SerializedName("EPLY_NAME")
    private String eplyName;
    @SerializedName("IS_COMFIRM")
    private int isComfirm;
    @SerializedName("IS_DELETE")
    private int isDelete;
    @SerializedName("PROJECT_ID")
    private int projectId;
    @SerializedName("PROJECT_NAME")
    private String projectName;

    public int getApplyBatch() {
        return applyBatch;
    }

    public void setApplyBatch(int applyBatch) {
        this.applyBatch = applyBatch;
    }

    public int getApplyId() {
        return applyId;
    }

    public void setApplyId(int applyId) {
        this.applyId = applyId;
    }

    public String getApplyNo() {
        return applyNo;
    }

    public void setApplyNo(String applyNo) {
        this.applyNo = applyNo;
    }

    public String getApplyTime() {
        return applyTime;
    }

    public void setApplyTime(String applyTime) {
        this.applyTime = applyTime;
    }

    public String getApplyUserId() {
        return applyUserId;
    }

    public void setApplyUserId(String applyUserId) {
        this.applyUserId = applyUserId;
    }

    public String getEplyName() {
        return eplyName;
    }

    public void setEplyName(String eplyName) {
        this.eplyName = eplyName;
    }

    public int getIsComfirm() {
        return isComfirm;
    }

    public void setIsComfirm(int isComfirm) {
        this.isComfirm = isComfirm;
    }

    public int getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(int isDelete) {
        this.isDelete = isDelete;
    }

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }
}
