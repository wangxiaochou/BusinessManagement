package com.xzty.cq.tover.businessmanagement.projectmanagement.part.model.use;

import java.util.Date;

/**
 * author zzl
 * Created 2018/5/16.
 * explain
 */

public class AllModel {
    private static final long serialVersionUID = -6025285416164682689L;

    private Integer collectDetailId;
    private Integer collectId;
    private Integer outDetailId;
    private Integer partId;
    private Double collectDetailCount;
    private Byte isDelete;
    private Date deleteTime;
    private Byte feedbackState;
    private Integer projectId;
    private Date collectTime;
    private Double collectLongitude;
    private Double collectLatitude;
    private Integer distDetailId;
    private Integer outId;
    private Integer applyDetailId;
    private Integer applyId;
    private Byte applyBatch;
    private Byte outBatch;
    private String partNo;
    private String partName;
    private String partUnit;
    private String partPlace;
    public boolean isCheck;

    public AllModel() {
    }

    @Override
    public String toString() {
        return "PickPart{" +
                "collectDetailId=" + collectDetailId +
                ", collectId=" + collectId +
                ", outDetailId=" + outDetailId +
                ", partId=" + partId +
                ", collectDetailCount=" + collectDetailCount +
                ", isDelete=" + isDelete +
                ", deleteTime=" + deleteTime +
                ", feedbackState=" + feedbackState +
                ", projectId=" + projectId +
                ", collectTime=" + collectTime +
                ", collectLongitude=" + collectLongitude +
                ", collectLatitude=" + collectLatitude +
                ", distDetailId=" + distDetailId +
                ", outId=" + outId +
                ", applyDetailId=" + applyDetailId +
                ", applyId=" + applyId +
                ", applyBatch=" + applyBatch +
                ", outBatch=" + outBatch +
                ", partNo='" + partNo + '\'' +
                ", partName='" + partName + '\'' +
                ", partUnit='" + partUnit + '\'' +
                '}';
    }

    public Integer getCollectDetailId() {
        return collectDetailId;
    }

    public void setCollectDetailId(Integer collectDetailId) {
        this.collectDetailId = collectDetailId;
    }

    public Integer getCollectId() {
        return collectId;
    }

    public void setCollectId(Integer collectId) {
        this.collectId = collectId;
    }

    public Integer getOutDetailId() {
        return outDetailId;
    }

    public void setOutDetailId(Integer outDetailId) {
        this.outDetailId = outDetailId;
    }

    public Integer getPartId() {
        return partId;
    }

    public void setPartId(Integer partId) {
        this.partId = partId;
    }

    public Double getCollectDetailCount() {
        return collectDetailCount;
    }

    public void setCollectDetailCount(Double collectDetailCount) {
        this.collectDetailCount = collectDetailCount;
    }

    public Byte getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Byte isDelete) {
        this.isDelete = isDelete;
    }

    public Date getDeleteTime() {
        return deleteTime;
    }

    public void setDeleteTime(Date deleteTime) {
        this.deleteTime = deleteTime;
    }

    public Byte getFeedbackState() {
        return feedbackState;
    }

    public void setFeedbackState(Byte feedbackState) {
        this.feedbackState = feedbackState;
    }

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    public Date getCollectTime() {
        return collectTime;
    }

    public void setCollectTime(Date collectTime) {
        this.collectTime = collectTime;
    }

    public Double getCollectLongitude() {
        return collectLongitude;
    }

    public void setCollectLongitude(Double collectLongitude) {
        this.collectLongitude = collectLongitude;
    }

    public Double getCollectLatitude() {
        return collectLatitude;
    }

    public void setCollectLatitude(Double collectLatitude) {
        this.collectLatitude = collectLatitude;
    }

    public Integer getDistDetailId() {
        return distDetailId;
    }

    public void setDistDetailId(Integer distDetailId) {
        this.distDetailId = distDetailId;
    }

    public Integer getOutId() {
        return outId;
    }

    public void setOutId(Integer outId) {
        this.outId = outId;
    }

    public Integer getApplyDetailId() {
        return applyDetailId;
    }

    public void setApplyDetailId(Integer applyDetailId) {
        this.applyDetailId = applyDetailId;
    }

    public Integer getApplyId() {
        return applyId;
    }

    public void setApplyId(Integer applyId) {
        this.applyId = applyId;
    }

    public Byte getApplyBatch() {
        return applyBatch;
    }

    public void setApplyBatch(Byte applyBatch) {
        this.applyBatch = applyBatch;
    }

    public Byte getOutBatch() {
        return outBatch;
    }

    public void setOutBatch(Byte outBatch) {
        this.outBatch = outBatch;
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
