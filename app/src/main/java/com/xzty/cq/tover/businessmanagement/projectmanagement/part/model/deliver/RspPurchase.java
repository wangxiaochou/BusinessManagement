package com.xzty.cq.tover.businessmanagement.projectmanagement.part.model.deliver;

import com.google.gson.annotations.SerializedName;

/**
 * author zzl
 * Created 2018/5/14.
 * explain 发货单数据
 */

public class RspPurchase {
    @SerializedName("CREATE_TIME")
    private String createTime;
    @SerializedName("DIST_ID")
    private int distId;
    @SerializedName("EPLY_NAME")
    private String eplyName;
    @SerializedName("IS_COMFIRM")
    private int isComfirm;
    @SerializedName("IS_DELETE")
    private int isDelete;
    @SerializedName("OUT_BATCH")
    private int outBatch;
    @SerializedName("OUT_ID")
    private int outId;
    @SerializedName("OUT_NO")
    private String outNo;
    @SerializedName("OUT_USER_ID")
    private String outUserId;
    @SerializedName("PROJECT_ID")
    private int projectId;
    @SerializedName("PROJECT_NAME")
    private String projectName;

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public int getDistId() {
        return distId;
    }

    public void setDistId(int distId) {
        this.distId = distId;
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

    public int getOutBatch() {
        return outBatch;
    }

    public void setOutBatch(int outBatch) {
        this.outBatch = outBatch;
    }

    public int getOutId() {
        return outId;
    }

    public void setOutId(int outId) {
        this.outId = outId;
    }

    public String getOutNo() {
        return outNo;
    }

    public void setOutNo(String outNo) {
        this.outNo = outNo;
    }

    public String getOutUserId() {
        return outUserId;
    }

    public void setOutUserId(String outUserId) {
        this.outUserId = outUserId;
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
