package com.xzty.cq.tover.businessmanagement.projectmanagement.part.model.apply;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.Date;

/**
 * author zzl
 * Created 2018/5/7.
 * explain 构件表的Model
 */

public class RspPartList implements Serializable {
    @SerializedName("COMPILATION_DATE")
    private String compilationDate;
    @SerializedName("DUTIES")
    private String duties;
    @SerializedName("INTO_TIME")
    private String intoTime;
    @SerializedName("IS_DELETE")
    private int isDelete;
    @SerializedName("MAKE_NO")
    private String makeNo;
    @SerializedName("NEED_COUNT")
    private double needCount;
    @SerializedName("PART_BATCH")
    private String partBatch;
    @SerializedName("PART_BRANCH")
    private String partBranch;
    @SerializedName("PART_ID")
    private int partId;
    @SerializedName("PART_NAME")
    private String partName;
    @SerializedName("PART_NO")
    private String partNo;
    @SerializedName("PART_PLACE")
    private String partPlace;
    @SerializedName("PART_RANGE")
    private String partRange;
    @SerializedName("PART_REMARK")
    private String partRemark;
    @SerializedName("PART_SEND")
    private String partSend;
    @SerializedName("PART_SINGLE")
    private double partSingle;
    @SerializedName("PART_SIZE")
    private int partSize;
    @SerializedName("PART_SPEC")
    private String partSpec;
    @SerializedName("PART_STATE")
    private int partState;
    @SerializedName("PART_STUFF")
    private String partStuff;
    @SerializedName("PART_TOTAL")
    private double partTotal;
    @SerializedName("PART_TYPE")
    private String partType;
    @SerializedName("PROJECT_ID")
    private int projectId;
    @SerializedName("WRITER")
    private String writer;
    @SerializedName("PART_THICK")
    private double partThick;
    @SerializedName("PART_WIDTH")
    private double partWidth;
    @SerializedName("PART_COUNT")
    private double partCount;
    @SerializedName("PART_UNIT")
    private String partUnit;
    @SerializedName("SINGLE_AREA")
    private double singleArea;
    @SerializedName("TOTAL_AREA")
    private double totalArea;
    @SerializedName("PART_SHAPE")
    private String partShape;
    @SerializedName("PART_COLOR")
    private String partColor;
    @SerializedName("PART_BRAND")
    private String partBrand;
    @SerializedName("DELETE_TIME")
    private String deleteTime;
    @SerializedName("PART_NOTE")
    private String partNote;
    //额外数据
    public boolean isCheck;

    private Date applyItemExpettime;

    //已经申请过的量
    private Double hadApplyCount;

    public String getCompilationDate() {
        return compilationDate;
    }

    public void setCompilationDate(String compilationDate) {
        this.compilationDate = compilationDate;
    }

    public String getDuties() {
        return duties;
    }

    public void setDuties(String duties) {
        this.duties = duties;
    }

    public String getIntoTime() {
        return intoTime;
    }

    public void setIntoTime(String intoTime) {
        this.intoTime = intoTime;
    }

    public int getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(int isDelete) {
        this.isDelete = isDelete;
    }

    public String getMakeNo() {
        return makeNo;
    }

    public void setMakeNo(String makeNo) {
        this.makeNo = makeNo;
    }

    public double getNeedCount() {
        return needCount;
    }

    public void setNeedCount(double needCount) {
        this.needCount = needCount;
    }

    public String getPartBatch() {
        return partBatch;
    }

    public void setPartBatch(String partBatch) {
        this.partBatch = partBatch;
    }

    public String getPartBranch() {
        return partBranch;
    }

    public void setPartBranch(String partBranch) {
        this.partBranch = partBranch;
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

    public String getPartPlace() {
        return partPlace;
    }

    public void setPartPlace(String partPlace) {
        this.partPlace = partPlace;
    }

    public String getPartRange() {
        return partRange;
    }

    public void setPartRange(String partRange) {
        this.partRange = partRange;
    }

    public String getPartRemark() {
        return partRemark;
    }

    public void setPartRemark(String partRemark) {
        this.partRemark = partRemark;
    }

    public String getPartSend() {
        return partSend;
    }

    public void setPartSend(String partSend) {
        this.partSend = partSend;
    }

    public double getPartSingle() {
        return partSingle;
    }

    public void setPartSingle(double partSingle) {
        this.partSingle = partSingle;
    }

    public int getPartSize() {
        return partSize;
    }

    public void setPartSize(int partSize) {
        this.partSize = partSize;
    }

    public String getPartSpec() {
        return partSpec;
    }

    public void setPartSpec(String partSpec) {
        this.partSpec = partSpec;
    }

    public int getPartState() {
        return partState;
    }

    public void setPartState(int partState) {
        this.partState = partState;
    }

    public String getPartStuff() {
        return partStuff;
    }

    public void setPartStuff(String partStuff) {
        this.partStuff = partStuff;
    }

    public double getPartTotal() {
        return partTotal;
    }

    public void setPartTotal(double partTotal) {
        this.partTotal = partTotal;
    }

    public String getPartType() {
        return partType;
    }

    public void setPartType(String partType) {
        this.partType = partType;
    }

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    public String getWriter() {
        return writer;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

    public double getPartThick() {
        return partThick;
    }

    public void setPartThick(double partThick) {
        this.partThick = partThick;
    }

    public double getPartWidth() {
        return partWidth;
    }

    public void setPartWidth(double partWidth) {
        this.partWidth = partWidth;
    }

    public double getPartCount() {
        return partCount;
    }

    public void setPartCount(double partCount) {
        this.partCount = partCount;
    }

    public String getPartUnit() {
        return partUnit;
    }

    public void setPartUnit(String partUnit) {
        this.partUnit = partUnit;
    }

    public double getSingleArea() {
        return singleArea;
    }

    public void setSingleArea(double singleArea) {
        this.singleArea = singleArea;
    }

    public double getTotalArea() {
        return totalArea;
    }

    public void setTotalArea(double totalArea) {
        this.totalArea = totalArea;
    }

    public String getPartShape() {
        return partShape;
    }

    public void setPartShape(String partShape) {
        this.partShape = partShape;
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

    public String getDeleteTime() {
        return deleteTime;
    }

    public void setDeleteTime(String deleteTime) {
        this.deleteTime = deleteTime;
    }

    public String getPartNote() {
        return partNote;
    }

    public void setPartNote(String partNote) {
        this.partNote = partNote;
    }

    public Date getApplyItemExpettime() {
        return applyItemExpettime;
    }

    public void setApplyItemExpettime(Date applyItemExpettime) {
        this.applyItemExpettime = applyItemExpettime;
    }

    public Double getHadApplyCount() {
        return hadApplyCount;
    }

    public void setHadApplyCount(Double hadApplyCount) {
        this.hadApplyCount = hadApplyCount;
    }
}
