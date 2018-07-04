package com.xzty.cq.tover.businessmanagement.projectmanagement.tool.model.projectmanage;

import com.google.gson.annotations.SerializedName;

/**
 * author zzl
 * Created 2018/5/23.
 * explain
 */

public class RspAffirmDetails {
    //公共部分
    @SerializedName("IS_DELETE")
    private int isDelete;
    @SerializedName("TOOL_APPLY_DETAIL_ID")
    private int toolApplyDetailId;
    @SerializedName("TOOL_APPLY_DETAIL_STATE")
    private int toolApplyDetailState;
    @SerializedName("TOOL_APPLY_ID")
    private int toolApplyId;

    //已有的机具部分
    @SerializedName("TOOL_BRAND")
    private String toolBrand;
    @SerializedName("TOOL_DEPARTMENT")
    private String toolDepartment;
    @SerializedName("TOOL_DEPOT")
    private String toolDepot;
    @SerializedName("TOOL_ID")
    private String toolId;
    @SerializedName("TOOL_MODEL_NUMBER")
    private String toolModelNumber;
    @SerializedName("TOOL_NAME")
    private String toolName;
    @SerializedName("TOOL_NUMBER")
    private String toolNumber;


    //新建机具部分
    @SerializedName("TOOL_DETAIL_APPLY_COUNT")
    private double toolDetailApplyCount;
    @SerializedName("TOOL_DETAIL_APPLY_TOOL_BRAND")
    private String toolDetailApplyToolBrand;
    @SerializedName("TOOL_DETAIL_APPLY_TOOL_MODEL_NUMBER")
    private String toolDetailApplyToolModelNumber;
    @SerializedName("TOOL_DETAIL_APPLY_TOOL_NAME")
    private String toolDetailApplyToolName;
    @SerializedName("TOOL_DETAIL_APPLY_TOOL_POWER")
    private String toolDetailApplyToolPower;

    //暂时无用
    @SerializedName("TOOL_DETAIL_APPLY_NOTE")
    private String toolDetailApplyNote;
    @SerializedName("DELETE_TIME")
    private String deleteTime;
    @SerializedName("TOOL_POWER")
    private String toolPower;

    //额外

    //记录是否被拒绝
    public boolean isRefuse = false;
    //记录是否被选中
    public boolean isChecked = false;
    public int getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(int isDelete) {
        this.isDelete = isDelete;
    }

    public int getToolApplyDetailId() {
        return toolApplyDetailId;
    }

    public void setToolApplyDetailId(int toolApplyDetailId) {
        this.toolApplyDetailId = toolApplyDetailId;
    }

    public int getToolApplyDetailState() {
        return toolApplyDetailState;
    }

    public void setToolApplyDetailState(int toolApplyDetailState) {
        this.toolApplyDetailState = toolApplyDetailState;
    }

    public int getToolApplyId() {
        return toolApplyId;
    }

    public void setToolApplyId(int toolApplyId) {
        this.toolApplyId = toolApplyId;
    }

    public String getToolBrand() {
        return toolBrand;
    }

    public void setToolBrand(String toolBrand) {
        this.toolBrand = toolBrand;
    }

    public String getToolDepartment() {
        return toolDepartment;
    }

    public void setToolDepartment(String toolDepartment) {
        this.toolDepartment = toolDepartment;
    }

    public String getToolDepot() {
        return toolDepot;
    }

    public void setToolDepot(String toolDepot) {
        this.toolDepot = toolDepot;
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

    public String getToolName() {
        return toolName;
    }

    public void setToolName(String toolName) {
        this.toolName = toolName;
    }

    public String getToolNumber() {
        return toolNumber;
    }

    public void setToolNumber(String toolNumber) {
        this.toolNumber = toolNumber;
    }

    public double getToolDetailApplyCount() {
        return toolDetailApplyCount;
    }

    public void setToolDetailApplyCount(double toolDetailApplyCount) {
        this.toolDetailApplyCount = toolDetailApplyCount;
    }

    public String getToolDetailApplyToolBrand() {
        return toolDetailApplyToolBrand;
    }

    public void setToolDetailApplyToolBrand(String toolDetailApplyToolBrand) {
        this.toolDetailApplyToolBrand = toolDetailApplyToolBrand;
    }

    public String getToolDetailApplyToolModelNumber() {
        return toolDetailApplyToolModelNumber;
    }

    public void setToolDetailApplyToolModelNumber(String toolDetailApplyToolModelNumber) {
        this.toolDetailApplyToolModelNumber = toolDetailApplyToolModelNumber;
    }

    public String getToolDetailApplyToolName() {
        return toolDetailApplyToolName;
    }

    public void setToolDetailApplyToolName(String toolDetailApplyToolName) {
        this.toolDetailApplyToolName = toolDetailApplyToolName;
    }

    public String getToolDetailApplyToolPower() {
        return toolDetailApplyToolPower;
    }

    public void setToolDetailApplyToolPower(String toolDetailApplyToolPower) {
        this.toolDetailApplyToolPower = toolDetailApplyToolPower;
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

    public String getToolPower() {
        return toolPower;
    }

    public void setToolPower(String toolPower) {
        this.toolPower = toolPower;
    }
}
