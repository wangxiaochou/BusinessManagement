package com.xzty.cq.tover.businessmanagement.projectmanagement.part.model.deliver;

import java.util.Date;

/**
 * author zzl
 * Created 2018/5/15.
 * explain
 */

public class DeliverDetails {

    private Integer outDetailId;
    private Integer outId;
    private Integer distDetailId;
    private Integer partId;
    private Byte isDelete;
    private Date deleteTime;
    private Byte outBatch;
    private Integer applyDetailId;
    private Double applyItemCount;
    private Date applyItemExpettime;
    private Byte partState;
    private String partNo;
    private String partUnit;
    private String partName;
    private Double collectDetailCount;
    private Double residualQuantity;
    public boolean isCheck;
    public int feedBackState = 0;
    private String partPlace;
    public Integer getOutDetailId() {
        return outDetailId;
    }

    public void setOutDetailId(Integer outDetailId) {
        this.outDetailId = outDetailId;
    }

    public Integer getOutId() {
        return outId;
    }

    public void setOutId(Integer outId) {
        this.outId = outId;
    }

    public Integer getDistDetailId() {
        return distDetailId;
    }

    public void setDistDetailId(Integer distDetailId) {
        this.distDetailId = distDetailId;
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

    public Byte getOutBatch() {
        return outBatch;
    }

    public void setOutBatch(Byte outBatch) {
        this.outBatch = outBatch;
    }

    public Integer getApplyDetailId() {
        return applyDetailId;
    }

    public void setApplyDetailId(Integer applyDetailId) {
        this.applyDetailId = applyDetailId;
    }

    public Double getApplyItemCount() {
        return applyItemCount;
    }

    public void setApplyItemCount(Double applyItemCount) {
        this.applyItemCount = applyItemCount;
    }

    public Date getApplyItemExpettime() {
        return applyItemExpettime;
    }

    public void setApplyItemExpettime(Date applyItemExpettime) {
        this.applyItemExpettime = applyItemExpettime;
    }

    public Byte getPartState() {
        return partState;
    }

    public void setPartState(Byte partState) {
        this.partState = partState;
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

    public String getPartName() {
        return partName;
    }

    public void setPartName(String partName) {
        this.partName = partName;
    }

    public Double getCollectDetailCount() {
        return collectDetailCount;
    }

    public void setCollectDetailCount(Double collectDetailCount) {
        this.collectDetailCount = collectDetailCount;
    }

    public Double getResidualQuantity() {
        return residualQuantity;
    }

    public void setResidualQuantity(Double residualQuantity) {
        this.residualQuantity = residualQuantity;
    }

    public String getPartPlace() {
        return partPlace;
    }

    public void setPartPlace(String partPlace) {
        this.partPlace = partPlace;
    }
}
