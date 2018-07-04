package com.xzty.cq.tover.businessmanagement.projectmanagement.tool.model.pickdepartment;

import com.google.gson.annotations.SerializedName;

/**
 * author zzl
 * Created 2018/5/24.
 * explain
 */

public class RspDistribute {
    @SerializedName("TOOL_APPLY_DETAIL_ID")
    private int toolApplyDetailId;
    @SerializedName("TOOL_APPLY_ID")
    private int toolApplyId;
    @SerializedName("TOOL_APPLY_DETAIL_STATE")
    private int toolApplyDetailState;
    @SerializedName("IS_DELETE")
    private int isDelete;
    @SerializedName("PROJECT_ID")
    private int projectId;

    @SerializedName("TOOL_DETAIL_APPLY_COUNT")
    private double toolDetailApplyCount;

    @SerializedName("TOOL_DETAIL_APPLY_NOTE")
    private String toolDetailApplyNote;
    @SerializedName("DELETE_TIME")
    private String deleteTime;
    @SerializedName("TOOL_DETAIL_APPLY_TOOL_NAME")
    private String toolDetailApplyToolName;
    @SerializedName("TOOL_DETAIL_APPLY_TOOL_MODEL_NUMBER")
    private String toolDetailApplyToolModelNumber;
    @SerializedName("TOOL_DETAIL_APPLY_TOOL_BRAND")
    private String toolDetailApplyToolBrand;
    @SerializedName("TOOL_DETAIL_APPLY_TOOL_POWER")
    private String toolDetailApplyToolPower;
    @SerializedName("TOOL_APPLY_TIME")
    private String toolApplyTime;
    @SerializedName("TOOL_ID")
    private String toolId;
    @SerializedName("TOOL_APPLY_USER_ID")
    private String toolApplyUserId;
    @SerializedName("PROJECT_NAME")
    private String projectName;
    @SerializedName("EplyName")
    private String eplyName;

    public boolean isChecked = false;

    public int getToolApplyDetailId() {
        return toolApplyDetailId;
    }

    public void setToolApplyDetailId(int toolApplyDetailId) {
        this.toolApplyDetailId = toolApplyDetailId;
    }

    public int getToolApplyId() {
        return toolApplyId;
    }

    public void setToolApplyId(int toolApplyId) {
        this.toolApplyId = toolApplyId;
    }

    public int getToolApplyDetailState() {
        return toolApplyDetailState;
    }

    public void setToolApplyDetailState(int toolApplyDetailState) {
        this.toolApplyDetailState = toolApplyDetailState;
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

    public double getToolDetailApplyCount() {
        return toolDetailApplyCount;
    }

    public void setToolDetailApplyCount(double toolDetailApplyCount) {
        this.toolDetailApplyCount = toolDetailApplyCount;
    }

    public String getToolDetailApplyNote() {
        return toolDetailApplyNote;
    }

    public void setToolDetailApplyNote(String toolDetailApplyNote) {
        this.toolDetailApplyNote = toolDetailApplyNote;
    }

    public String getDeleteTime() {
        return deleteTime;
    }

    public void setDeleteTime(String deleteTime) {
        this.deleteTime = deleteTime;
    }

    public String getToolDetailApplyToolName() {
        return toolDetailApplyToolName;
    }

    public void setToolDetailApplyToolName(String toolDetailApplyToolName) {
        this.toolDetailApplyToolName = toolDetailApplyToolName;
    }

    public String getToolDetailApplyToolModelNumber() {
        return toolDetailApplyToolModelNumber;
    }

    public void setToolDetailApplyToolModelNumber(String toolDetailApplyToolModelNumber) {
        this.toolDetailApplyToolModelNumber = toolDetailApplyToolModelNumber;
    }

    public String getToolDetailApplyToolBrand() {
        return toolDetailApplyToolBrand;
    }

    public void setToolDetailApplyToolBrand(String toolDetailApplyToolBrand) {
        this.toolDetailApplyToolBrand = toolDetailApplyToolBrand;
    }

    public String getToolDetailApplyToolPower() {
        return toolDetailApplyToolPower;
    }

    public void setToolDetailApplyToolPower(String toolDetailApplyToolPower) {
        this.toolDetailApplyToolPower = toolDetailApplyToolPower;
    }

    public String getToolApplyTime() {
        return toolApplyTime;
    }

    public void setToolApplyTime(String toolApplyTime) {
        this.toolApplyTime = toolApplyTime;
    }

    public String getToolId() {
        return toolId;
    }

    public void setToolId(String toolId) {
        this.toolId = toolId;
    }

    public String getToolApplyUserId() {
        return toolApplyUserId;
    }

    public void setToolApplyUserId(String toolApplyUserId) {
        this.toolApplyUserId = toolApplyUserId;
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
