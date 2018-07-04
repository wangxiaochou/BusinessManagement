package com.xzty.cq.tover.businessmanagement.projectmanagement.part.model.apply;

import java.util.Date;

/**
 * author zzl
 * Created 2018/5/18.
 * explain
 */

public class FixReqPartList {
    private Integer partId;

    private Integer projectId;

    private String partName;

    private String partNo;

    private String partSpec;

    private Double partThick;

    private String partStuff;

    private Double partWidth;

    private Double partSize;

    private Double partCount;

    private String partUnit;

    private Double partSingle;

    private Double partTotal;

    private Double singleArea;

    private Double totalArea;

    private String partShape;

    private String partPlace;

    private String partColor;

    private String partBrand;

    private String partSend;

    private String partBatch;

    private Double needCount;

    private String partBranch;

    private String partRange;

    private String partType;

    private String writer;

    private String duties;

    private String compilationDate;

    private String makeNo;

    private String intoTime;

    private String partRemark;

    private Byte partState;

    private Byte isDelete;

    private Date deleteTime;

    ///////////////额外字段//////////////////
    private Double hadApplyCount;  //已经申请过的量

    public boolean isCheck;   //是否被选中

    private String applyItemExpettime;

    public Integer getPartId() {
        return partId;
    }

    public void setPartId(Integer partId) {
        this.partId = partId;
    }

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
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

    public String getPartSpec() {
        return partSpec;
    }

    public void setPartSpec(String partSpec) {
        this.partSpec = partSpec;
    }

    public Double getPartThick() {
        return partThick;
    }

    public void setPartThick(Double partThick) {
        this.partThick = partThick;
    }

    public String getPartStuff() {
        return partStuff;
    }

    public void setPartStuff(String partStuff) {
        this.partStuff = partStuff;
    }

    public Double getPartWidth() {
        return partWidth;
    }

    public void setPartWidth(Double partWidth) {
        this.partWidth = partWidth;
    }

    public Double getPartSize() {
        return partSize;
    }

    public void setPartSize(Double partSize) {
        this.partSize = partSize;
    }

    public Double getPartCount() {
        return partCount;
    }

    public void setPartCount(Double partCount) {
        this.partCount = partCount;
    }

    public String getPartUnit() {
        return partUnit;
    }

    public void setPartUnit(String partUnit) {
        this.partUnit = partUnit;
    }

    public Double getPartSingle() {
        return partSingle;
    }

    public void setPartSingle(Double partSingle) {
        this.partSingle = partSingle;
    }

    public Double getPartTotal() {
        return partTotal;
    }

    public void setPartTotal(Double partTotal) {
        this.partTotal = partTotal;
    }

    public Double getSingleArea() {
        return singleArea;
    }

    public void setSingleArea(Double singleArea) {
        this.singleArea = singleArea;
    }

    public Double getTotalArea() {
        return totalArea;
    }

    public void setTotalArea(Double totalArea) {
        this.totalArea = totalArea;
    }

    public String getPartShape() {
        return partShape;
    }

    public void setPartShape(String partShape) {
        this.partShape = partShape;
    }

    public String getPartPlace() {
        return partPlace;
    }

    public void setPartPlace(String partPlace) {
        this.partPlace = partPlace;
    }

    public String getPartColor() {
        return partColor;
    }

    public void setPartColor(String partColor) {
        this.partColor = partColor;
    }

    public String getPartBrand() {
        return partBrand;
    }

    public void setPartBrand(String partBrand) {
        this.partBrand = partBrand;
    }

    public String getPartSend() {
        return partSend;
    }

    public void setPartSend(String partSend) {
        this.partSend = partSend;
    }

    public String getPartBatch() {
        return partBatch;
    }

    public void setPartBatch(String partBatch) {
        this.partBatch = partBatch;
    }

    public Double getNeedCount() {
        return needCount;
    }

    public void setNeedCount(Double needCount) {
        this.needCount = needCount;
    }

    public String getPartBranch() {
        return partBranch;
    }

    public void setPartBranch(String partBranch) {
        this.partBranch = partBranch;
    }

    public String getPartRange() {
        return partRange;
    }

    public void setPartRange(String partRange) {
        this.partRange = partRange;
    }

    public String getPartType() {
        return partType;
    }

    public void setPartType(String partType) {
        this.partType = partType;
    }

    public String getWriter() {
        return writer;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

    public String getDuties() {
        return duties;
    }

    public void setDuties(String duties) {
        this.duties = duties;
    }

    public String getCompilationDate() {
        return compilationDate;
    }

    public void setCompilationDate(String compilationDate) {
        this.compilationDate = compilationDate;
    }

    public String getMakeNo() {
        return makeNo;
    }

    public void setMakeNo(String makeNo) {
        this.makeNo = makeNo;
    }

    public String getIntoTime() {
        return intoTime;
    }

    public void setIntoTime(String intoTime) {
        this.intoTime = intoTime;
    }

    public String getPartRemark() {
        return partRemark;
    }

    public void setPartRemark(String partRemark) {
        this.partRemark = partRemark;
    }

    public Byte getPartState() {
        return partState;
    }

    public void setPartState(Byte partState) {
        this.partState = partState;
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

    public Double getHadApplyCount() {
        return hadApplyCount;
    }

    public void setHadApplyCount(Double hadApplyCount) {
        this.hadApplyCount = hadApplyCount;
    }

    public boolean isCheck() {
        return isCheck;
    }

    public void setCheck(boolean check) {
        isCheck = check;
    }

    public String getApplyItemExpettime() {
        return applyItemExpettime;
    }

    public void setApplyItemExpettime(String applyItemExpettime) {
        this.applyItemExpettime = applyItemExpettime;
    }
}
