package com.xzty.cq.tover.businessmanagement.projectmanagement.tool.model.pickdepartment;

import com.google.gson.annotations.SerializedName;

/**
 * author zzl
 * Created 2018/5/25.
 * explain
 */

public class RspDistributeOrderList {
    @SerializedName("TOOL_DIST_ID")
    private int toolDistId;
    @SerializedName("PROJECT_ID")
    private int projectId;
    @SerializedName("IS_COMFIRM")
    private int isComfirm;
    @SerializedName("IS_DELETE")
    private int isDelete;
    @SerializedName("TOOL_DIST_NO")
    private String toolDistNo;
    @SerializedName("TOOL_DIST_TIME")
    private String toolDistTime;
    @SerializedName("TOOL_DIST_BUYER_ID")
    private String toolDistBuyerId;
    @SerializedName("DELETE_TIME")
    private String deleteTime;
    @SerializedName("TOOL_DIST_ASSIGNER_ID")
    private String toolDistAssignerId;
    @SerializedName("TOOL_DIST_NOTE")
    private String toolDistNote;
    @SerializedName("BUYER_NAME")
    private String buyerName;
    @SerializedName("PROJECT_NAME")
    private String projectName;
    @SerializedName("ASSIGNER_NAME")
    private String assignerName;

    public int getToolDistId() {
        return toolDistId;
    }

    public void setToolDistId(int toolDistId) {
        this.toolDistId = toolDistId;
    }

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
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

    public String getToolDistNo() {
        return toolDistNo;
    }

    public void setToolDistNo(String toolDistNo) {
        this.toolDistNo = toolDistNo;
    }

    public String getToolDistTime() {
        return toolDistTime;
    }

    public void setToolDistTime(String toolDistTime) {
        this.toolDistTime = toolDistTime;
    }

    public String getToolDistBuyerId() {
        return toolDistBuyerId;
    }

    public void setToolDistBuyerId(String toolDistBuyerId) {
        this.toolDistBuyerId = toolDistBuyerId;
    }

    public String getDeleteTime() {
        return deleteTime;
    }

    public void setDeleteTime(String deleteTime) {
        this.deleteTime = deleteTime;
    }

    public String getToolDistAssignerId() {
        return toolDistAssignerId;
    }

    public void setToolDistAssignerId(String toolDistAssignerId) {
        this.toolDistAssignerId = toolDistAssignerId;
    }

    public String getToolDistNote() {
        return toolDistNote;
    }

    public void setToolDistNote(String toolDistNote) {
        this.toolDistNote = toolDistNote;
    }

    public String getBuyerName() {
        return buyerName;
    }

    public void setBuyerName(String buyerName) {
        this.buyerName = buyerName;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getAssignerName() {
        return assignerName;
    }

    public void setAssignerName(String assignerName) {
        this.assignerName = assignerName;
    }
}
