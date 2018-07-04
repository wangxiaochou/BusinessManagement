package com.xzty.cq.tover.businessmanagement.projectmanagement.part.model.back;

import java.util.Date;

/**
 * author zzl
 * Created 2018/5/17.
 * explain
 */

public class BackPart {

    private Integer pickCountId;
    private Integer pickDetailId;
    private Integer pickId;
    private Integer collectDetailId;
    private Integer partId;
    private Byte isDelete;
    private Date deleteTime;
    private Double pickDetailCount;
    private Integer pickBatch;
    private Integer projectId;
    private Double pickLongitude;
    private Double pickLatitude;
    private String partNo;
    private String partName;
    private String partUnit;
    private String pickTime;
    public boolean isCheck;
    private String partPlace;
    public BackPart() {
    }

    public Integer getPickCountId() {
        return pickCountId;
    }

    public void setPickCountId(Integer pickCountId) {
        this.pickCountId = pickCountId;
    }

    public Integer getPickDetailId() {
        return pickDetailId;
    }

    public void setPickDetailId(Integer pickDetailId) {
        this.pickDetailId = pickDetailId;
    }

    public Integer getPickId() {
        return pickId;
    }

    public void setPickId(Integer pickId) {
        this.pickId = pickId;
    }

    public Integer getCollectDetailId() {
        return collectDetailId;
    }

    public void setCollectDetailId(Integer collectDetailId) {
        this.collectDetailId = collectDetailId;
    }

    public Integer getPartId() {
        return partId;
    }

    public void setPartId(Integer partId) {
        this.partId = partId;
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

    public Double getPickDetailCount() {
        return pickDetailCount;
    }

    public void setPickDetailCount(Double pickDetailCount) {
        this.pickDetailCount = pickDetailCount;
    }

    public Integer getPickBatch() {
        return pickBatch;
    }

    public void setPickBatch(Integer pickBatch) {
        this.pickBatch = pickBatch;
    }

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    public Double getPickLongitude() {
        return pickLongitude;
    }

    public void setPickLongitude(Double pickLongitude) {
        this.pickLongitude = pickLongitude;
    }

    public Double getPickLatitude() {
        return pickLatitude;
    }

    public void setPickLatitude(Double pickLatitude) {
        this.pickLatitude = pickLatitude;
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

    public String getPickTime() {
        return pickTime;
    }

    public void setPickTime(String pickTime) {
        this.pickTime = pickTime;
    }

    public String getPartPlace() {
        return partPlace;
    }

    public void setPartPlace(String partPlace) {
        this.partPlace = partPlace;
    }
}
