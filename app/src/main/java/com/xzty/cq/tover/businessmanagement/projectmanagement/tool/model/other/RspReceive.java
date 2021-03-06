package com.xzty.cq.tover.businessmanagement.projectmanagement.tool.model.other;

import com.google.gson.annotations.SerializedName;

/**
 * author zzl
 * Created 2018/5/26.
 * explain
 */

public class RspReceive {
    @SerializedName("TOOL_OUT_DETAIL_ID")
    private int toolOutDetailId;

    @SerializedName("TOOL_OUT_ID")
    private int toolOutId;

    @SerializedName("TOOL_APPLY_DETAIL_ID")
    private int toolApplyDetailId;

    @SerializedName("TOOL_DIST_DETAIL_ID")
    private int toolDistDetailId;

    @SerializedName("IS_DELETE")
    private int isDelete;

    @SerializedName("OUT_DETAIL_STATE")
    private int outDetailState;

    @SerializedName("PROJECT_ID")
    private int projectId;

    @SerializedName("TOOL_ID")
    private String toolId;

    @SerializedName("DELETE_TIME")
    private String deleteTime;

    @SerializedName("TOOL_OUT_USER_ID")
    private String toolOutUserId;

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

    @SerializedName("PROJECT_NAME")
    private String projectName;

    @SerializedName("ITEM_NUMBER")
    private String itemNumber;

    @SerializedName("EplyName")
    private String eplyName;

    @SerializedName("TOOL_COUNT")
    private double toolCount;

    //额外
    public boolean isChecked = false;

    public int getToolOutDetailId() {
        return toolOutDetailId;
    }

    public void setToolOutDetailId(int toolOutDetailId) {
        this.toolOutDetailId = toolOutDetailId;
    }

    public int getToolOutId() {
        return toolOutId;
    }

    public void setToolOutId(int toolOutId) {
        this.toolOutId = toolOutId;
    }

    public int getToolApplyDetailId() {
        return toolApplyDetailId;
    }

    public void setToolApplyDetailId(int toolApplyDetailId) {
        this.toolApplyDetailId = toolApplyDetailId;
    }

    public int getToolDistDetailId() {
        return toolDistDetailId;
    }

    public void setToolDistDetailId(int toolDistDetailId) {
        this.toolDistDetailId = toolDistDetailId;
    }

    public int getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(int isDelete) {
        this.isDelete = isDelete;
    }

    public int getOutDetailState() {
        return outDetailState;
    }

    public void setOutDetailState(int outDetailState) {
        this.outDetailState = outDetailState;
    }

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    public String getToolId() {
        return toolId;
    }

    public void setToolId(String toolId) {
        this.toolId = toolId;
    }

    public String getDeleteTime() {
        return deleteTime;
    }

    public void setDeleteTime(String deleteTime) {
        this.deleteTime = deleteTime;
    }

    public String getToolOutUserId() {
        return toolOutUserId;
    }

    public void setToolOutUserId(String toolOutUserId) {
        this.toolOutUserId = toolOutUserId;
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

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getItemNumber() {
        return itemNumber;
    }

    public void setItemNumber(String itemNumber) {
        this.itemNumber = itemNumber;
    }

    public String getEplyName() {
        return eplyName;
    }

    public void setEplyName(String eplyName) {
        this.eplyName = eplyName;
    }

    public double getToolCount() {
        return toolCount;
    }

    public void setToolCount(double toolCount) {
        this.toolCount = toolCount;
    }
}
