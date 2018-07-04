package com.xzty.cq.tover.businessmanagement.projectmanagement.tool.model.other;

import com.google.gson.annotations.SerializedName;

/**
 * author zzl
 * Created 2018/5/26.
 * explain
 */

public class RspReceiveDetais {

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

    @SerializedName("DELETE_TIME")
    private String deleteTime;

    @SerializedName("TOOL_COLLECT_DETAIL_NOTE")
    private String toolCollectDetailNote;

    @SerializedName("TOOL_NUMBER")
    private String toolNumber;

    @SerializedName("TOOL_NAME")
    private String toolName;

    @SerializedName("TOOL_MODEL_NUMBER")
    private String toolModelNumber;

    @SerializedName("TOOL_ID")
    private String toolId;

    @SerializedName("TOOL_BRAND")
    private String toolBrand;

    @SerializedName("TOOL_POWER")
    private String toolPower;

    @SerializedName("TOOL_DEPOT")
    private String toolDepot;

    @SerializedName("TOOL_DEPARTMENT")
    private String toolDepartment;

    @SerializedName("TOOL_COLLECT_DETAIL_COUNT")
    private double toolCollectDetailCount;

    @SerializedName("TOOL_COUNT")
    private double toolCount;

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

    public String getToolNumber() {
        return toolNumber;
    }

    public void setToolNumber(String toolNumber) {
        this.toolNumber = toolNumber;
    }

    public String getToolModelNumber() {
        return toolModelNumber;
    }

    public void setToolModelNumber(String toolModelNumber) {
        this.toolModelNumber = toolModelNumber;
    }

    public String getToolId() {
        return toolId;
    }

    public void setToolId(String toolId) {
        this.toolId = toolId;
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

    public String getToolName() {
        return toolName;
    }

    public void setToolName(String toolName) {
        this.toolName = toolName;
    }
}
