package com.xzty.cq.tover.businessmanagement.projectmanagement.part.model.apply;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by liuchuandeo on 2017/12/8.
 */

public class ProjectApplyDetail implements Serializable {
    private Integer applyDetailId;
    private Integer applyId;
    private Double applyItemCount;
    private Date applyItemExpetTime;
    private Double applyIncrper;
    private String applyItemNote;
    private Integer partId;
    private Byte isDelete;
    private Date deleteTime;
    private Byte partState;
    private String partNo;
    private String partName;
    private String applyBatch;
    private String partUnit;

    @Override
    public String toString() {
        return "ProjectApplyDetail{"+
                "applyDetailId='" + applyDetailId + '\'' +
                ", applyId='" + applyId + '\'' +
                ", applyItemCount='" + applyItemCount + '\'' +
                ", applyItemExpetTime='" + applyItemExpetTime + '\'' +
                ", applyIncrper='" + applyIncrper + '\'' +
                ", applyItemNote='" + applyItemNote + '\'' +
                ", partId='" + partId + '\'' +
                ", isDelete='" + isDelete + '\'' +
                ", deleteTime='" + deleteTime + '\'' +
                ", partNo='" + partNo + '\'' +
                ", partName='" + partName + '\'' +
                ", applyBatch='" + applyBatch + '\'' +
                ", partUnit='" + partUnit + '\'' +
                ", partState='" + partState +
                "}";
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

    public Double getApplyItemCount() {
        return applyItemCount;
    }

    public void setApplyItemCount(Double applyItemCount) {
        this.applyItemCount = applyItemCount;
    }

    public Date getApplyItemExpetTime() {
        return applyItemExpetTime;
    }

    public void setApplyItemExpetTime(Date applyItemExpetTime) {
        this.applyItemExpetTime = applyItemExpetTime;
    }

    public Double getApplyIncrper() {
        return applyIncrper;
    }

    public void setApplyIncrper(Double applyIncrper) {
        this.applyIncrper = applyIncrper;
    }

    public String getApplyItemNote() {
        return applyItemNote;
    }

    public void setApplyItemNote(String applyItemNote) {
        this.applyItemNote = applyItemNote;
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

    public String getPartName() {
        return partName;
    }

    public void setPartName(String partName) {
        this.partName = partName;
    }

    public String getApplyBatch() {
        return applyBatch;
    }

    public void setApplyBatch(String applyBatch) {
        this.applyBatch = applyBatch;
    }

    public String getPartUnit() {
        return partUnit;
    }

    public void setPartUnit(String partUnit) {
        this.partUnit = partUnit;
    }
}
