package com.xzty.cq.tover.businessmanagement.projectmanagement.part.model.use;

import com.google.gson.annotations.SerializedName;

/**
 * author zzl
 * Created 2018/5/16.
 * explain 领用构件的model
 */

public class RspUseChoose {
    @SerializedName("APPLY_BATCH")
    private int applyBatch;
    @SerializedName("APPLY_DETAIL_ID")
    private int applyDetailId;
    @SerializedName("APPLY_ID")
    private int applyId;
    @SerializedName("COLLECT_DETAIL_COUNT")
    private int collectDetailCount;
    @SerializedName("COLLECT_DETAIL_ID")
    private int collectDetailId;
    @SerializedName("COLLECT_ID")
    private int collectId;
    @SerializedName("COLLECT_LATITUDE")
    private String collectLatitude;
    @SerializedName("COLLECT_LONGITUDE")
    private String collectLongitude;
    @SerializedName("COLLECT_TIME")
    private String collectTime;
    @SerializedName("DIST_DETAIL_ID")
    private int distDetailId;
    @SerializedName("FEEDBACK_STATE")
    private int feedbackState;
    @SerializedName("IS_DELETE")
    private int isDelete;
    @SerializedName("OUT_BATCH")
    private int outBatch;
    @SerializedName("OUT_DETAIL_ID")
    private int outDetailId;
    @SerializedName("OUT_ID")
    private int outId;
    @SerializedName("PART_ID")
    private int partId;
    @SerializedName("PART_NAME")
    private String partName;
    @SerializedName("PART_NO")
    private String partNo;
    @SerializedName("PART_UNIT")
    private String partUnit;
    @SerializedName("PROJECT_ID")
    private String projectId;
    @SerializedName("TOTAL_DETAIL_ID")
    private String totalDetailId;

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

    public int getCollectDetailCount() {
        return collectDetailCount;
    }

    public void setCollectDetailCount(int collectDetailCount) {
        this.collectDetailCount = collectDetailCount;
    }

    public int getCollectDetailId() {
        return collectDetailId;
    }

    public void setCollectDetailId(int collectDetailId) {
        this.collectDetailId = collectDetailId;
    }

    public int getCollectId() {
        return collectId;
    }

    public void setCollectId(int collectId) {
        this.collectId = collectId;
    }

    public String getCollectLatitude() {
        return collectLatitude;
    }

    public void setCollectLatitude(String collectLatitude) {
        this.collectLatitude = collectLatitude;
    }

    public String getCollectLongitude() {
        return collectLongitude;
    }

    public void setCollectLongitude(String collectLongitude) {
        this.collectLongitude = collectLongitude;
    }

    public String getCollectTime() {
        return collectTime;
    }

    public void setCollectTime(String collectTime) {
        this.collectTime = collectTime;
    }

    public int getDistDetailId() {
        return distDetailId;
    }

    public void setDistDetailId(int distDetailId) {
        this.distDetailId = distDetailId;
    }

    public int getFeedbackState() {
        return feedbackState;
    }

    public void setFeedbackState(int feedbackState) {
        this.feedbackState = feedbackState;
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

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getTotalDetailId() {
        return totalDetailId;
    }

    public void setTotalDetailId(String totalDetailId) {
        this.totalDetailId = totalDetailId;
    }
}
