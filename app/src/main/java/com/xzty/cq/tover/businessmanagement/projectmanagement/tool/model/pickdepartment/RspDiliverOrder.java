package com.xzty.cq.tover.businessmanagement.projectmanagement.tool.model.pickdepartment;

import com.google.gson.annotations.SerializedName;

/**
 * author zzl
 * Created 2018/5/24.
 * explain
 */

public class RspDiliverOrder {
    @SerializedName("TOOL_APPLY_DETAIL_ID")
    private int toolApplyDetailId;
    @SerializedName("TOOL_APPLY_ID")
    private int toolApplyId;
    @SerializedName("TOOL_APPLY_DETAIL_STATE")
    private int toolApplyDetailState;
    @SerializedName("IS_DELETE")
    private int isDelete;
    @SerializedName("TOOL_DETAIL_ID")
    private int toolDetailId;
    @SerializedName("PROJECT_ID")
    private int projectId;
    @SerializedName("TOOL_COUNT")
    private double toolCount;
    @SerializedName("TOOL_DETAIL_APPLY_COUNT")
    private double toolDetailApplyCount;
    @SerializedName("TOOL_DETAIL_APPLY_NOTE")
    private String toolDetailApplyNote;
    @SerializedName("DELETE_TIME")
    private String deleteTime;
    @SerializedName("TOOL_ID")
    private String toolId;
    @SerializedName("TOOL_DETAIL_APPLY_TOOL_NAME")
    private String toolDetailApplyToolName;
    @SerializedName("TOOL_DETAIL_APPLY_TOOL_MODEL_NUMBER")
    private String toolDetailApplyToolModelNumber;
    @SerializedName("TOOL_DETAIL_APPLY_TOOL_BRAND")
    private String toolDetailApplyToolBrand;
    @SerializedName("TOOL_DETAIL_APPLY_TOOL_POWER")
    private String toolDetailApplyToolPower;
    @SerializedName("TOOL_APPLY_USER_ID")
    private String toolApplyUserId;
    @SerializedName("TOOL_APPLY_TIME")
    private String toolApplyTime;
    @SerializedName("EplyName")
    private String eplyName;
    @SerializedName("PROJECT_NAME")
    private String projectName;
    @SerializedName("TOOL_NUMBER")
    private String toolNumber;
    @SerializedName("TOOL_NAME")
    private String toolName;
    @SerializedName("TOOL_MODEL_NUMBER")
    private String toolModelNumber;
    @SerializedName("TOOL_BRAND")
    private String toolBrand;
    @SerializedName("TOOL_POWER")
    private String toolPower;
    @SerializedName("TOOL_DEPOT")
    private String toolDepot;
    @SerializedName("TOOL_DEPARTMENT")
    private String toolDepartment;

    public boolean isChecke = false;

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

    public int getToolDetailId() {
        return toolDetailId;
    }

    public void setToolDetailId(int toolDetailId) {
        this.toolDetailId = toolDetailId;
    }

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    public double getToolCount() {
        return toolCount;
    }

    public void setToolCount(double toolCount) {
        this.toolCount = toolCount;
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

    public String getToolId() {
        return toolId;
    }

    public void setToolId(String toolId) {
        this.toolId = toolId;
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

    public String getToolApplyUserId() {
        return toolApplyUserId;
    }

    public void setToolApplyUserId(String toolApplyUserId) {
        this.toolApplyUserId = toolApplyUserId;
    }

    public String getToolApplyTime() {
        return toolApplyTime;
    }

    public void setToolApplyTime(String toolApplyTime) {
        this.toolApplyTime = toolApplyTime;
    }

    public String getEplyName() {
        return eplyName;
    }

    public void setEplyName(String eplyName) {
        this.eplyName = eplyName;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getToolNumber() {
        return toolNumber;
    }

    public void setToolNumber(String toolNumber) {
        this.toolNumber = toolNumber;
    }

    public String getToolName() {
        return toolName;
    }

    public void setToolName(String toolName) {
        this.toolName = toolName;
    }

    public String getToolModelNumber() {
        return toolModelNumber;
    }

    public void setToolModelNumber(String toolModelNumber) {
        this.toolModelNumber = toolModelNumber;
    }

    public String getToolBrand() {
        return toolBrand;
    }

    public void setToolBrand(String toolBrand) {
        this.toolBrand = toolBrand;
    }

    public String getToolPower() {
        return toolPower;
    }

    public void setToolPower(String toolPower) {
        this.toolPower = toolPower;
    }

    public String getToolDepot() {
        return toolDepot;
    }

    public void setToolDepot(String toolDepot) {
        this.toolDepot = toolDepot;
    }

    public String getToolDepartment() {
        return toolDepartment;
    }

    public void setToolDepartment(String toolDepartment) {
        this.toolDepartment = toolDepartment;
    }
}
