package com.xzty.cq.tover.businessmanagement.projectmanagement.part.model.pick;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

/**
 * author zzl
 * Created 2018/5/12.
 * explain 选择采购物品
 */

public class RspPickList {
    @SerializedName("APPLY_BATCH")
    private int applyBatch;
    @SerializedName("APPLY_DETAIL_ID")
    private int applyDetailId;
    @SerializedName("APPLY_ID")
    private int applyId;
    @SerializedName("APPLY_ITEM_COUNT")
    private double applyItemCount;
    @SerializedName("APPLY_ITEM_EXPETTIME")
    private String applyItemExpettime;
    @SerializedName("DIST_DETAIL_ID")
    private int distDetailId;
    @SerializedName("IS_DELETE")
    private int isDelete;
    @SerializedName("PART_ID")
    private int partId;
    @SerializedName("PART_NAME")
    private String partName;
    @SerializedName("PART_NO")
    private String partNo;
    @SerializedName("PART_STATE")
    private String partState;
    @SerializedName("PART_UNIT")
    private String partUnit;
    @SerializedName("PROJECT_ID")
    private int projectId;
    @SerializedName("UNIT_PRICE")
    //价格
    private String unitPrice;
    @SerializedName("PART_PLACE")
    private String partPlace;

    private int distId;

    private Date deleteTime;

    private String applyItemIncrper;

    public boolean isCheck;

    public int getApplyBatch() {
        return applyBatch;
    }

    public void setApplyBatch(int applyBatch) {
        this.applyBatch = applyBatch;
    }

    public int getApplyDetailId() {
        return applyDetailId;
    }

    public void setApplyDetailId(int applyDetailId) {
        this.applyDetailId = applyDetailId;
    }

    public int getApplyId() {
        return applyId;
    }

    public void setApplyId(int applyId) {
        this.applyId = applyId;
    }

    public double getApplyItemCount() {
        return applyItemCount;
    }

    public void setApplyItemCount(double applyItemCount) {
        this.applyItemCount = applyItemCount;
    }

    public String getApplyItemExpettime() {
        return applyItemExpettime;
    }

    public void setApplyItemExpettime(String applyItemExpettime) {
        this.applyItemExpettime = applyItemExpettime;
    }

    public int getDistDetailId() {
        return distDetailId;
    }

    public void setDistDetailId(int distDetailId) {
        this.distDetailId = distDetailId;
    }

    public int getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(int isDelete) {
        this.isDelete = isDelete;
    }

    public int getPartId() {
        return partId;
    }

    public void setPartId(int partId) {
        this.partId = partId;
    }

    public String getPartName() {
        return partName;
    }

    public void setPartName(String partName) {
        this.partName = partName;
    }

    public String getPartNo() {
        return partNo;
    }

    public void setPartNo(String partNo) {
        this.partNo = partNo;
    }

    public String getPartState() {
        return partState;
    }

    public void setPartState(String partState) {
        this.partState = partState;
    }

    public String getPartUnit() {
        return partUnit;
    }

    public void setPartUnit(String partUnit) {
        this.partUnit = partUnit;
    }

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    public int getDistId() {
        return distId;
    }

    public void setDistId(int distId) {
        this.distId = distId;
    }

    public Date getDeleteTime() {
        return deleteTime;
    }

    public void setDeleteTime(Date deleteTime) {
        this.deleteTime = deleteTime;
    }

    public String getApplyItemIncrper() {
        return applyItemIncrper;
    }

    public void setApplyItemIncrper(String applyItemIncrper) {
        this.applyItemIncrper = applyItemIncrper;
    }

    public String getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(String unitPrice) {
        this.unitPrice = unitPrice;
    }

    public String getPartPlace() {
        return partPlace;
    }

    public void setPartPlace(String partPlace) {
        this.partPlace = partPlace;
    }
}
