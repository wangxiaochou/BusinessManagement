package com.xzty.cq.tover.businessmanagement.projectmanagement.tool.model.projectmanage;

import com.google.gson.annotations.SerializedName;

/**
 * author zzl
 * Created 2018/5/23.
 * explain 确认申请列表
 */

public class RspAffirmList {
    @SerializedName("TOOL_APPLY_ID")
    private int toolApplyId;
    @SerializedName("PROJECT_ID")
    private int projectId;
    @SerializedName("IS_COMFIRM")
    private int isComfirm;
    @SerializedName("IS_DELETE")
    private int isDelete;
    @SerializedName("TOOL_APPLY_NO")
    private String toolApplyNo;
    @SerializedName("TOOL_APPLY_TIME")
    private String toolApplyTime;
    @SerializedName("TOOL_APPLY_USER_ID")
    private String toolApplyUserId;
    @SerializedName("TOOL_APPLY_NOTE")
    private String toolApplyNote;
    @SerializedName("DELETE_TIME")
    private String deleteTime;
    @SerializedName("PROJECT_NAME")
    private String projectName;
    @SerializedName("EplyName")
    private String eplyName;


    public int getToolApplyId() {
        return toolApplyId;
    }

    public void setToolApplyId(int toolApplyId) {
        this.toolApplyId = toolApplyId;
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

    public String getToolApplyNo() {
        return toolApplyNo;
    }

    public void setToolApplyNo(String toolApplyNo) {
        this.toolApplyNo = toolApplyNo;
    }

    public String getToolApplyTime() {
        return toolApplyTime;
    }

    public void setToolApplyTime(String toolApplyTime) {
        this.toolApplyTime = toolApplyTime;
    }

    public String getToolApplyUserId() {
        return toolApplyUserId;
    }

    public void setToolApplyUserId(String toolApplyUserId) {
        this.toolApplyUserId = toolApplyUserId;
    }

    public String getToolApplyNote() {
        return toolApplyNote;
    }

    public void setToolApplyNote(String toolApplyNote) {
        this.toolApplyNote = toolApplyNote;
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
