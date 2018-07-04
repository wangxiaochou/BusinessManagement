package com.xzty.cq.tover.businessmanagement.projectmanagement.tool.model.back;

import com.google.gson.annotations.SerializedName;

/**
 * author zzl
 * Created 2018/5/28.
 * explain
 */

public class RspAuditList {
    @SerializedName("TOOL_BACK_ID")
    private int toolBackId;

    @SerializedName("PROJECT_ID")
    private int projectId;

    @SerializedName("IS_DELETE")
    private int isDelete;

    @SerializedName("IS_COMFIRM")
    private int isComfirm;

    @SerializedName("TOOL_BACK_NO")
    private String toolBackNo;

    @SerializedName("TOOL_BACK_USER_ID")
    private String toolBackUserId;

    @SerializedName("TOOL_BACK_TIME")
    private String toolBackTime;

    @SerializedName("TOOL_BACK_NOTE")
    private String toolBackNote;

    @SerializedName("DELETE_TIME")
    private String deleteTime;

    @SerializedName("PROJECT_NAME")
    private String projectName;

    @SerializedName("EplyName")
    private String eplyName;

    public int getToolBackId() {
        return toolBackId;
    }

    public void setToolBackId(int toolBackId) {
        this.toolBackId = toolBackId;
    }

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    public int getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(int isDelete) {
        this.isDelete = isDelete;
    }

    public int getIsComfirm() {
        return isComfirm;
    }

    public void setIsComfirm(int isComfirm) {
        this.isComfirm = isComfirm;
    }

    public String getToolBackNo() {
        return toolBackNo;
    }

    public void setToolBackNo(String toolBackNo) {
        this.toolBackNo = toolBackNo;
    }

    public String getToolBackUserId() {
        return toolBackUserId;
    }

    public void setToolBackUserId(String toolBackUserId) {
        this.toolBackUserId = toolBackUserId;
    }

    public String getToolBackTime() {
        return toolBackTime;
    }

    public void setToolBackTime(String toolBackTime) {
        this.toolBackTime = toolBackTime;
    }

    public String getToolBackNote() {
        return toolBackNote;
    }

    public void setToolBackNote(String toolBackNote) {
        this.toolBackNote = toolBackNote;
    }

    public String getDeleteTime() {
        return deleteTime;
    }

    public void setDeleteTime(String deleteTime) {
        this.deleteTime = deleteTime;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getEplyName() {
        return eplyName;
    }

    public void setEplyName(String eplyName) {
        this.eplyName = eplyName;
    }
}
