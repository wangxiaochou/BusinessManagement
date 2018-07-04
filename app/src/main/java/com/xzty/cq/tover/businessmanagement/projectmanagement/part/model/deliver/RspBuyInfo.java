package com.xzty.cq.tover.businessmanagement.projectmanagement.part.model.deliver;

import com.google.gson.annotations.SerializedName;

/**
 * author zzl
 * Created 2018/5/15.
 * explain
 */

public class RspBuyInfo {
    @SerializedName("APPLY_DETAIL_ID")
    private int applyDetailId;
    @SerializedName("APPLY_ITEM_COUNT")
    private int applyItemCount;
    @SerializedName("APPLY_ITEM_EXPETTIME")
    private String applyItemExpettime;
    @SerializedName("DIST_DETAIL_ID")
    private int distDetaiId;
    @SerializedName("IS_DELETE")
    private int isDelete;
    @SerializedName("OUT_BATCH")
    private int outBatch;
    @SerializedName("OUT_DETAIL_ID")
    private int outDetailId;
    @SerializedName("OUT_ID")
    private int outId;
    @SerializedName("PART_ID")
    private String partId;
    @SerializedName("PART_NAME")
    private String partName;
    @SerializedName("PART_NO")
    private String partNo;
    @SerializedName("PART_UNIT")
    private String partUnit;

    public int getApplyDetailId() {
        return applyDetailId;
    }

    public void setApplyDetailId(int applyDetailId) {
        this.applyDetailId = applyDetailId;
    }

    public int getApplyItemCount() {
        return applyItemCount;
    }

    public void setApplyItemCount(int applyItemCount) {
        this.applyItemCount = applyItemCount;
    }

    public String getApplyItemExpettime() {
        return applyItemExpettime;
    }

    public void setApplyItemExpettime(String applyItemExpettime) {
        this.applyItemExpettime = applyItemExpettime;
    }

    public int getDistDetaiId() {
        return distDetaiId;
    }

    public void setDistDetaiId(int distDetaiId) {
        this.distDetaiId = distDetaiId;
    }

    public int getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(int isDelete) {
        this.isDelete = isDelete;
    }

    public int getOutBatch() {
        return outBatch;
    }

    public void setOutBatch(int outBatch) {
        this.outBatch = outBatch;
    }

    public int getOutDetailId() {
        return outDetailId;
    }

    public void setOutDetailId(int outDetailId) {
        this.outDetailId = outDetailId;
    }

    public int getOutId() {
        return outId;
    }

    public void setOutId(int outId) {
        this.outId = outId;
    }

    public String getPartId() {
        return partId;
    }

    public void setPartId(String partId) {
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

    public String getPartUnit() {
        return partUnit;
    }

    public void setPartUnit(String partUnit) {
        this.partUnit = partUnit;
    }
}
