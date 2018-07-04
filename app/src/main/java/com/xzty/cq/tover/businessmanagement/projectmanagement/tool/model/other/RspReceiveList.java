package com.xzty.cq.tover.businessmanagement.projectmanagement.tool.model.other;

import com.google.gson.annotations.SerializedName;

/**
 * author zzl
 * Created 2018/5/26.
 * explain
 */

public class RspReceiveList {

    @SerializedName("TOOL_COLLECT_ID")
    private int toolCollectId;

    @SerializedName("PROJECT_ID")
    private int projectId;

    @SerializedName("IS_DELETE")
    private int isDelete;

    @SerializedName("TOOL_COLLECT_NO")
    private String toolCollectNo;

    @SerializedName("TOOL_COLLECT_USER_ID")
    private String toolCollectUserId;

    @SerializedName("TOOL_COLLECT_TIME")
    private String toolCollectTime;

    @SerializedName("TOOL_COLLECT_NOTE")
    private String toolCollectNote;

    @SerializedName("DELETE_TIME")
    private String deleteTime;

    @SerializedName("PROJECT_NAME")
    private String projectName;

    @SerializedName("EplyName")
    private String eplyName;

    public int getToolCollectId() {
        return toolCollectId;
    }

    public void setToolCollectId(int toolCollectId) {
        this.toolCollectId = toolCollectId;
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

    public String getToolCollectNo() {
        return toolCollectNo;
    }

    public void setToolCollectNo(String toolCollectNo) {
        this.toolCollectNo = toolCollectNo;
    }

    public String getToolCollectUserId() {
        return toolCollectUserId;
    }

    public void setToolCollectUserId(String toolCollectUserId) {
        this.toolCollectUserId = toolCollectUserId;
    }

    public String getToolCollectTime() {
        return toolCollectTime;
    }

    public void setToolCollectTime(String toolCollectTime) {
        this.toolCollectTime = toolCollectTime;
    }

    public String getToolCollectNote() {
        return toolCollectNote;
    }

    public void setToolCollectNote(String toolCollectNote) {
        this.toolCollectNote = toolCollectNote;
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
