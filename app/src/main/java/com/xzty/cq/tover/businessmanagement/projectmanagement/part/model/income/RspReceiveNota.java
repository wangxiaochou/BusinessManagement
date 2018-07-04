package com.xzty.cq.tover.businessmanagement.projectmanagement.part.model.income;

import com.google.gson.annotations.SerializedName;

/**
 * author zzl
 * Created 2018/5/15.
 * explain 收货单的数据
 */

public class RspReceiveNota {
    @SerializedName("CAR_NO")
    private String carNo;
    @SerializedName("CAR_SIZE")
    private int carSize;
    @SerializedName("CONTRACT_NO")
    private String contractNo;
    @SerializedName("CREATE_TIME")
    private String createTime;
    @SerializedName("DIST_ID")
    private int distId;
    @SerializedName("EPLY_NAME")
    private String eplyName;
    @SerializedName("EXPECT_OUT_TIME")
    private String expectOutTime;
    @SerializedName("FIRM")
    private String firm;
    @SerializedName("IS_COMFIRM")
    private int isComfirm;
    @SerializedName("IS_DELETE")
    private int isDelete;
    @SerializedName("OUT_BATCH")
    private int outBatch;
    @SerializedName("OUT_ID")
    private String outId;
    @SerializedName("OUT_NO")
    private String outNo;
    @SerializedName("OUT_NOTE")
    private String outNote;
    @SerializedName("OUT_PIC_PATH")
    private String outPicPath;
    @SerializedName("OUT_TIME")
    private String outTime;
    @SerializedName("OUT_USER_ID")
    private String outUserId;
    @SerializedName("PROJECT_ID")
    private int projectId;
    @SerializedName("PROJECT_NAME")
    private String projectName;
    @SerializedName("SENDER")
    private String sender;
    @SerializedName("SENDER_PHONE")
    private String senderPhone;
    @SerializedName("SEND_NUMBER")
    private String sendNumber;

    public String getCarNo() {
        return carNo;
    }

    public void setCarNo(String carNo) {
        this.carNo = carNo;
    }

    public int getCarSize() {
        return carSize;
    }

    public void setCarSize(int carSize) {
        this.carSize = carSize;
    }

    public String getContractNo() {
        return contractNo;
    }

    public void setContractNo(String contractNo) {
        this.contractNo = contractNo;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public int getDistId() {
        return distId;
    }

    public void setDistId(int distId) {
        this.distId = distId;
    }

    public String getEplyName() {
        return eplyName;
    }

    public void setEplyName(String eplyName) {
        this.eplyName = eplyName;
    }

    public String getExpectOutTime() {
        return expectOutTime;
    }

    public void setExpectOutTime(String expectOutTime) {
        this.expectOutTime = expectOutTime;
    }

    public String getFirm() {
        return firm;
    }

    public void setFirm(String firm) {
        this.firm = firm;
    }

    public int getIsComfirm() {
        return isComfirm;
    }

    public void setIsComfirm(int isComfirm) {
        this.isComfirm = isComfirm;
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

    public String getOutId() {
        return outId;
    }

    public void setOutId(String outId) {
        this.outId = outId;
    }

    public String getOutNo() {
        return outNo;
    }

    public void setOutNo(String outNo) {
        this.outNo = outNo;
    }

    public String getOutNote() {
        return outNote;
    }

    public void setOutNote(String outNote) {
        this.outNote = outNote;
    }

    public String getOutPicPath() {
        return outPicPath;
    }

    public void setOutPicPath(String outPicPath) {
        this.outPicPath = outPicPath;
    }

    public String getOutTime() {
        return outTime;
    }

    public void setOutTime(String outTime) {
        this.outTime = outTime;
    }

    public String getOutUserId() {
        return outUserId;
    }

    public void setOutUserId(String outUserId) {
        this.outUserId = outUserId;
    }

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getSenderPhone() {
        return senderPhone;
    }

    public void setSenderPhone(String senderPhone) {
        this.senderPhone = senderPhone;
    }

    public String getSendNumber() {
        return sendNumber;
    }

    public void setSendNumber(String sendNumber) {
        this.sendNumber = sendNumber;
    }
}
