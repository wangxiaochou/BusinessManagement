package com.xzty.cq.tover.businessmanagement.projectmanagement.tool.model.pickdepartment;

import com.google.gson.annotations.SerializedName;

/**
 * author zzl
 * Created 2018/5/29.
 * explain
 */

public class RspDistributeOrderDetails {
    @SerializedName("TOOL_DIST_DETAIL_ID")
    private int toolDistDetailId;

    @SerializedName("TOOL_DIST_ID")
    private int toolDistId;

    @SerializedName("TOOL_APPLY_DETAIL_ID")
    private int toolApplyDetailId;

    @SerializedName("IS_DELETE")
    private int isDelete;

    @SerializedName("TOOL_ISOUT")
    private int toolIsout;

    @SerializedName("disable")
    private int disable;//是否入库

    @SerializedName("TOOL_DETAIL_APPLY_COUNT")
    private double toolDetailApplyCount;

    @SerializedName("putInStorage")
    private double putInStorage;

    @SerializedName("TOOL_DETAIL_APPLY_TOOL_MODEL_NUMBER")
    private String toolDetailApplyToolModelNumber;

    @SerializedName("TOOL_DETAIL_APPLY_TOOL_NAME")
    private String toolDetailApplyToolName;

    @SerializedName("TOOL_DETAIL_APPLY_TOOL_BRAND")
    private String toolDetailApplyToolBrand;

    @SerializedName("TOOL_ID")
    private String toolId;

    @SerializedName("DELETE_TIME")
    private String deleteTime;

    @SerializedName("TOOL_DETAIL_APPLY_TOOL_POWER")
    private String toolDetailApplyToolPower;

    public boolean isChecked = false;

    public int getToolDistDetailId() {
        return toolDistDetailId;
    }

    public void setToolDistDetailId(int toolDistDetailId) {
        this.toolDistDetailId = toolDistDetailId;
    }

    public int getToolDistId() {
        return toolDistId;
    }

    public void setToolDistId(int toolDistId) {
        this.toolDistId = toolDistId;
    }

    public int getToolApplyDetailId() {
        return toolApplyDetailId;
    }

    public void setToolApplyDetailId(int toolApplyDetailId) {
        this.toolApplyDetailId = toolApplyDetailId;
    }

    public int getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(int isDelete) {
        this.isDelete = isDelete;
    }

    public int getToolIsout() {
        return toolIsout;
    }

    public void setToolIsout(int toolIsout) {
        this.toolIsout = toolIsout;
    }

    public int getDisable() {
        return disable;
    }

    public void setDisable(int disable) {
        this.disable = disable;
    }

    public double getToolDetailApplyCount() {
        return toolDetailApplyCount;
    }

    public void setToolDetailApplyCount(double toolDetailApplyCount) {
        this.toolDetailApplyCount = toolDetailApplyCount;
    }

    public double getPutInStorage() {
        return putInStorage;
    }

    public void setPutInStorage(double putInStorage) {
        this.putInStorage = putInStorage;
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

    public String getToolDetailApplyToolPower() {
        return toolDetailApplyToolPower;
    }

    public void setToolDetailApplyToolPower(String toolDetailApplyToolPower) {
        this.toolDetailApplyToolPower = toolDetailApplyToolPower;
    }

    public String getToolDetailApplyToolName() {
        return toolDetailApplyToolName;
    }

    public void setToolDetailApplyToolName(String toolDetailApplyToolName) {
        this.toolDetailApplyToolName = toolDetailApplyToolName;
    }
}
