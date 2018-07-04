package com.xzty.cq.tover.businessmanagement.projectmanagement.tool.model.other;

import com.google.gson.annotations.SerializedName;

/**
 * author zzl
 * Created 2018/5/28.
 * explain
 */

public class RspBackDetails {
    @SerializedName("TOOL_BACK_DETAIL_ID")
    private int toolBackDetailId;

    @SerializedName("TOOL_BACK_ID")
    private int toolBackId;

    @SerializedName("IS_PROBLEM")
    private int isProblem;

    @SerializedName("IS_DELETE")
    private int isDelete;

    @SerializedName("TOOL_COLLECT_DETAIL_ID")
    private int toolCollectDetailId;

    @SerializedName("TOOL_BACK_DETAIL_STATE")
    private int toolBackDetailState;

    @SerializedName("TOOL_BACK_DETAIL_COUNT")
    private double toolBackDetailCount;

    @SerializedName("TOOL_COUNT")
    private double toolCount;

    @SerializedName("DELETE_TIME")
    private String deleteTime;

    @SerializedName("TOOL_BACK_DETAIL_NOTE")
    private String toolBackDetailNote;

    @SerializedName("TOOL_NUMBER")
    private String toolNumber;

    @SerializedName("TOOL_ID")
    private String toolId;

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

    public int getToolBackDetailId() {
        return toolBackDetailId;
    }

    public void setToolBackDetailId(int toolBackDetailId) {
        this.toolBackDetailId = toolBackDetailId;
    }

    public int getToolBackId() {
        return toolBackId;
    }

    public void setToolBackId(int toolBackId) {
        this.toolBackId = toolBackId;
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

    public int getToolCollectDetailId() {
        return toolCollectDetailId;
    }

    public void setToolCollectDetailId(int toolCollectDetailId) {
        this.toolCollectDetailId = toolCollectDetailId;
    }

    public int getToolBackDetailState() {
        return toolBackDetailState;
    }

    public void setToolBackDetailState(int toolBackDetailState) {
        this.toolBackDetailState = toolBackDetailState;
    }

    public double getToolBackDetailCount() {
        return toolBackDetailCount;
    }

    public void setToolBackDetailCount(double toolBackDetailCount) {
        this.toolBackDetailCount = toolBackDetailCount;
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

    public String getToolBackDetailNote() {
        return toolBackDetailNote;
    }

    public void setToolBackDetailNote(String toolBackDetailNote) {
        this.toolBackDetailNote = toolBackDetailNote;
    }

    public String getToolNumber() {
        return toolNumber;
    }

    public void setToolNumber(String toolNumber) {
        this.toolNumber = toolNumber;
    }

    public String getToolId() {
        return toolId;
    }

    public void setToolId(String toolId) {
        this.toolId = toolId;
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
