package com.xzty.cq.tover.businessmanagement.projectmanagement.tool.model.projectmanage;

import com.google.gson.annotations.SerializedName;

/**
 * author zzl
 * Created 2018/5/21.
 * explain
 */

public class ReqNewCreateTool {
    @SerializedName("TOOL_COUNT")
    private double toolCount;

    @SerializedName("TOOL_NAME")
    private String toolName;

    @SerializedName("TOOL_MODEL_NUMBER")
    private String toolModelNumber;

    @SerializedName("TOOL_BRAND")
    private String toolBrand;

    @SerializedName("TOOL_POWER")
    private String toolPower;


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
}
