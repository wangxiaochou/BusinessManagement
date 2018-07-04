package com.xzty.cq.tover.businessmanagement.projectmanagement.part.model.back;

import com.google.gson.annotations.SerializedName;

/**
 * author zzl
 * Created 2018/5/17.
 * explain
 */

public class RspChooseBack {
    @SerializedName("COLLECT_DETAIL_ID")
    private int collectDetailId;
    @SerializedName("IS_DELETE")
    private int isDelete;
    @SerializedName("PART_ID")
    private int partId;
    @SerializedName("PART_NAME")
    private String partName;
    @SerializedName("PART_NO")
    private String partNo;
    @SerializedName("PART_UNIT")
    private String partUnit;
    @SerializedName("PICK_BATCH")
    private int pickBatch;
    @SerializedName("PICK_CPUNT_ID")
    private int pickCpuntId;
    @SerializedName("PICK_DETAIL_COUNT")
    private int pickDetailCount;
    @SerializedName("PICK_DETAIL_ID")
    private int pickDetailId;
    @SerializedName("PICK_ID")
    private int pickId;
    @SerializedName("PICK_LATITUDE")
    private String pickLatitude;
    @SerializedName("PICK_LONGITUDE")
    private String pickLongitude;
    @SerializedName("PROJECT_ID")
    private int projectId;

    @SerializedName("PICK_TIME")
    private String pickTime;

    public int getCollectDetailId() {
        return collectDetailId;
    }

    public void setCollectDetailId(int collectDetailId) {
        this.collectDetailId = collectDetailId;
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

    public String getPartUnit() {
        return partUnit;
    }

    public void setPartUnit(String partUnit) {
        this.partUnit = partUnit;
    }

    public int getPickBatch() {
        return pickBatch;
    }

    public void setPickBatch(int pickBatch) {
        this.pickBatch = pickBatch;
    }

    public int getPickCpuntId() {
        return pickCpuntId;
    }

    public void setPickCpuntId(int pickCpuntId) {
        this.pickCpuntId = pickCpuntId;
    }

    public int getPickDetailCount() {
        return pickDetailCount;
    }

    public void setPickDetailCount(int pickDetailCount) {
        this.pickDetailCount = pickDetailCount;
    }

    public int getPickDetailId() {
        return pickDetailId;
    }

    public void setPickDetailId(int pickDetailId) {
        this.pickDetailId = pickDetailId;
    }

    public int getPickId() {
        return pickId;
    }

    public void setPickId(int pickId) {
        this.pickId = pickId;
    }

    public String getPickLatitude() {
        return pickLatitude;
    }

    public void setPickLatitude(String pickLatitude) {
        this.pickLatitude = pickLatitude;
    }

    public String getPickLongitude() {
        return pickLongitude;
    }

    public void setPickLongitude(String pickLongitude) {
        this.pickLongitude = pickLongitude;
    }

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    public String getPickTime() {
        return pickTime;
    }

    public void setPickTime(String pickTime) {
        this.pickTime = pickTime;
    }
}
