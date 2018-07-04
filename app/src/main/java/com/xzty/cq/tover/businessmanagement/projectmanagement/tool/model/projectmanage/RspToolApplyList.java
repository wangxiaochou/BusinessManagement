package com.xzty.cq.tover.businessmanagement.projectmanagement.tool.model.projectmanage;

import com.google.gson.annotations.SerializedName;

/**
 * author zzl
 * Created 2018/5/21.
 * explain 机具列表
 */

public class RspToolApplyList {
    @SerializedName("TOOL_ISDAMAGE")
    private int toolIsdamage;
    @SerializedName("TOOL_STATE")
    private int toolState;
    @SerializedName("TOOL_ISNUMBER")
    private int toolIsNumber;
    @SerializedName("TOOL_COUNT")
    private double toolCount;
    @SerializedName("TOOL_PRICE")
    private double toolPrice;
    @SerializedName("TOOL_NUMBER")
    private String toolNumber;
    @SerializedName("TOOL_DEPOT")
    private String toolDepot;
    @SerializedName("TOOL_NAME")
    private String toolName;
    @SerializedName("TOOL_MODEL_NUMBER")
    private String toolModelNumber;
    @SerializedName("TOOL_POWER")
    private String toolPower;
    @SerializedName("TOOL_BRAND")
    private String toolBrand;
    @SerializedName("TOOL_ID")
    private String toolId;
    @SerializedName("TOOL_CATEGORY")
    private String toolCategory;
    @SerializedName("TOOL_DEPARTMENT")
    private String toolDepartment;
    @SerializedName("DELETE_TIME")
    private String deleteTime;
    @SerializedName("TOOL_NOTE")
    private String toolNote;

    public boolean isChecked = false;

    public int getToolIsdamage() {
        return toolIsdamage;
    }

    public void setToolIsdamage(int toolIsdamage) {
        this.toolIsdamage = toolIsdamage;
    }

    public int getToolState() {
        return toolState;
    }

    public void setToolState(int toolState) {
        this.toolState = toolState;
    }

    public int getToolIsNumber() {
        return toolIsNumber;
    }

    public void setToolIsNumber(int toolIsNumber) {
        this.toolIsNumber = toolIsNumber;
    }

    public double getToolCount() {
        return toolCount;
    }

    public void setToolCount(double toolCount) {
        this.toolCount = toolCount;
    }

    public double getToolPrice() {
        return toolPrice;
    }

    public void setToolPrice(double toolPrice) {
        this.toolPrice = toolPrice;
    }

    public String getToolNumber() {
        return toolNumber;
    }

    public void setToolNumber(String toolNumber) {
        this.toolNumber = toolNumber;
    }

    public String getToolDepot() {
        return toolDepot;
    }

    public void setToolDepot(String toolDepot) {
        this.toolDepot = toolDepot;
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

    public String getToolPower() {
        return toolPower;
    }

    public void setToolPower(String toolPower) {
        this.toolPower = toolPower;
    }

    public String getToolBrand() {
        return toolBrand;
    }

    public void setToolBrand(String toolBrand) {
        this.toolBrand = toolBrand;
    }

    public String getToolId() {
        return toolId;
    }

    public void setToolId(String toolId) {
        this.toolId = toolId;
    }

    public String getToolCategory() {
        return toolCategory;
    }

    public void setToolCategory(String toolCategory) {
        this.toolCategory = toolCategory;
    }

    public String getToolDepartment() {
        return toolDepartment;
    }

    public void setToolDepartment(String toolDepartment) {
        this.toolDepartment = toolDepartment;
    }

    public String getDeleteTime() {
        return deleteTime;
    }

    public void setDeleteTime(String deleteTime) {
        this.deleteTime = deleteTime;
    }

    public String getToolNote() {
        return toolNote;
    }

    public void setToolNote(String toolNote) {
        this.toolNote = toolNote;
    }
}
