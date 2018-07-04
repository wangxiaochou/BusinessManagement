package com.xzty.cq.tover.businessmanagement.projectmanagement.part.model.apply;

import com.google.gson.annotations.SerializedName;

/**
 * author zzl
 * Created 2018/5/10.
 * explain 申请详情返回的参数
 */

public class RspApplyDetails {
    @SerializedName("APPLY_BATCH")
    private int applyBatch;
    @SerializedName("APPLY_DETAIL_ID")
    private int applyDetailId;
    @SerializedName("APPLY_ID")
    private int applyId;
    @SerializedName("APPLY_ITEM_COUNT")
    private Double applyItemCount;
    @SerializedName("APPLY_ITEM_EXPETTIME")
    private String applyItemExpettime;
    @SerializedName("PART_ID")
    private int partId;
    @SerializedName("IS_DELETE")
    private String isDelete;
    @SerializedName("PART_STATE")
    private String partState;
    @SerializedName("PART_NO")
    private String partNo;
    @SerializedName("PART_NAME")
    private String partName;
    @SerializedName("PART_UNIT")
    private String partUnit;
    @SerializedName("PART_PLASE")
    private String partPlace;
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

    public Double getApplyItemCount() {
        return applyItemCount;
    }

    public void setApplyItemCount(Double applyItemCount) {
        this.applyItemCount = applyItemCount;
    }

    public String getApplyItemExpettime() {
        return applyItemExpettime;
    }

    public void setApplyItemExpettime(String applyItemExpettime) {
        this.applyItemExpettime = applyItemExpettime;
    }

    public int getPartId() {
        return partId;
    }

    public void setPartId(int partId) {
        this.partId = partId;
    }

    public String getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(String isDelete) {
        this.isDelete = isDelete;
    }

    public String getPartState() {
        return partState;
    }

    public void setPartState(String partState) {
        this.partState = partState;
    }

    public String getPartNo() {
        return partNo;
    }

    public void setPartNo(String partNo) {
        this.partNo = partNo;
    }

    public String getPartName() {
        return partName;
    }

    public void setPartName(String partName) {
        this.partName = partName;
    }

    public String getPartUnit() {
        return partUnit;
    }

    public void setPartUnit(String partUnit) {
        this.partUnit = partUnit;
    }

    public String getPartPlace() {
        return partPlace;
    }

    public void setPartPlace(String partPlace) {
        this.partPlace = partPlace;
    }
}
