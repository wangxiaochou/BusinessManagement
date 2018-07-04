package com.xzty.cq.tover.businessmanagement.projectmanagement.tool.model.other;

import com.google.gson.annotations.SerializedName;

/**
 * author zzl
 * Created 2018/5/26.
 * explain
 */

public class RspBackApply {

    @SerializedName("TOOL_COLLECT_DETAIL_ID")
    private int toolCollectDetailId;

    @SerializedName("TOOL_COLLECT_ID")
    private int toolCollectId;

    @SerializedName("IS_PROBLEM")
    private int isProblem;


    @SerializedName("IS_DELETE")
    private int isDelete;

    @SerializedName("TOOL_OUT_DETAIL_ID")
    private int toolOutDetailId;

    @SerializedName("IS_BACKDEPOT")
    private int isBackdepot;

    @SerializedName("PROJECT_ID")
    private int projectId;


    @SerializedName("TOOL_COLLECT_DETAIL_COUNT")
    private double toolCollectDetailCount;

    @SerializedName("TOOL_COUNT")
    private double toolCount;

    @SerializedName("DELETE_TIME")
    private String deleteTime;

    @SerializedName("TOOL_COLLECT_DETAIL_NOTE")
    private String toolCollectDetailNote;

    @SerializedName("TOOL_COLLECT_USER_ID")
    private String toolCollectUserId;

    @SerializedName("TOOL_COLLECT_TIME")
    private String toolCollectTime;

    @SerializedName("TOOL_NUMBER")
    private String toolNumber;

    @SerializedName("TOOL_NAME")
    private String toolName;

    @SerializedName("TOOL_ID")
    private String toolId;

    @SerializedName("TOOL_MODEL_NUMBER")
    private String toolModelNumber;

    @SerializedName("TOOL_BRAND")
    private String toolBrand;

    @SerializedName("TOOL_POWER")
    private String toolPower;

    @SerializedName("PROJECT_NAME")
    private String projectName;

    @SerializedName("EplyName")
    private String eplyName;

    //额外
    public boolean isChecked = false;

    public int getToolCollectDetailId() {
        return toolCollectDetailId;
    }

    public void setToolCollectDetailId(int toolCollectDetailId) {
        this.toolCollectDetailId = toolCollectDetailId;
    }

    public int getToolCollectId() {
        return toolCollectId;
    }

    public void setToolCollectId(int toolCollectId) {
        this.toolCollectId = toolCollectId;
    }

    public int getIsProblem() {
        return isProblem;
    }

    public void setIsProblem(int isProblem) {
        this.isProblem = isProblem;
    }

    public int getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(int isDelete) {
        this.isDelete = isDelete;
    }

    public int getToolOutDetailId() {
        return toolOutDetailId;
    }

    public void setToolOutDetailId(int toolOutDetailId) {
        this.toolOutDetailId = toolOutDetailId;
    }

    public int getIsBackdepot() {
        return isBackdepot;
    }

    public void setIsBackdepot(int isBackdepot) {
        this.isBackdepot = isBackdepot;
    }

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    public double getToolCollectDetailCount() {
        return toolCollectDetailCount;
    }

    public void setToolCollectDetailCount(double toolCollectDetailCount) {
        this.toolCollectDetailCount = toolCollectDetailCount;
    }

    public double getToolCount() {
        return toolCount;
    }

    public void setToolCount(double toolCount) {
        this.toolCount = toolCount;
    }

    public String getDeleteTime() {
        return deleteTime;
    }

    public void setDeleteTime(String deleteTime) {
        this.deleteTime = deleteTime;
    }

    public String getToolCollectDetailNote() {
        return toolCollectDetailNote;
    }

    public void setToolCollectDetailNote(String toolCollectDetailNote) {
        this.toolCollectDetailNote = toolCollectDetailNote;
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

    public String getToolId() {
        return toolId;
    }

    public void setToolId(String toolId) {
        this.toolId = toolId;
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
