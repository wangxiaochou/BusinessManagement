package com.xzty.cq.tover.businessmanagement.projectmanagement.part.model.deliver;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * author zzl
 * Created 2018/5/14.
 * explain
 */

public class DeliverOrder implements Serializable{

    private Integer outId;
    private Date createTime;
    private String outNo;
    private Byte outBatch;
    private Integer projectId;
    private String outUserId;
    private Integer distId;
    private Date outTime;
    private String firm;
    private String contractNo;
    private Date expectOutTime;
    private String outNote;
    private Byte isComfirm;
    private Byte isDelete;
    private Date deleteTime;
    private String sender;
    private String senderPhone;
    private String carNo;
    private String carSize;
    private String sendNumber;
    private String projectName;
    private String eplyName;
    private String outPicPath;
    private List<String> pathList;

    public Integer getOutId() {
        return outId;
    }

    public void setOutId(Integer outId) {
        this.outId = outId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getOutNo() {
        return outNo;
    }

    public void setOutNo(String outNo) {
        this.outNo = outNo;
    }

    public Byte getOutBatch() {
        return outBatch;
    }

    public void setOutBatch(Byte outBatch) {
        this.outBatch = outBatch;
    }

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    public String getOutUserId() {
        return outUserId;
    }

    public void setOutUserId(String outUserId) {
        this.outUserId = outUserId;
    }

    public Integer getDistId() {
        return distId;
    }

    public void setDistId(Integer distId) {
        this.distId = distId;
    }

    public Date getOutTime() {
        return outTime;
    }

    public void setOutTime(Date outTime) {
        this.outTime = outTime;
    }

    public String getFirm() {
        return firm;
    }

    public void setFirm(String firm) {
        this.firm = firm;
    }

    public String getContractNo() {
        return contractNo;
    }

    public void setContractNo(String contractNo) {
        this.contractNo = contractNo;
    }

    public Date getExpectOutTime() {
        return expectOutTime;
    }

    public void setExpectOutTime(Date expectOutTime) {
        this.expectOutTime = expectOutTime;
    }

    public String getOutNote() {
        return outNote;
    }

    public void setOutNote(String outNote) {
        this.outNote = outNote;
    }

    public Byte getIsComfirm() {
        return isComfirm;
    }

    public void setIsComfirm(Byte isComfirm) {
        this.isComfirm = isComfirm;
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

    public String getCarNo() {
        return carNo;
    }

    public void setCarNo(String carNo) {
        this.carNo = carNo;
    }

    public String getCarSize() {
        return carSize;
    }

    public void setCarSize(String carSize) {
        this.carSize = carSize;
    }

    public String getSendNumber() {
        return sendNumber;
    }

    public void setSendNumber(String sendNumber) {
        this.sendNumber = sendNumber;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getEplyName() {
        return eplyName;
    }

    public void setEplyName(String eplyName) {
        this.eplyName = eplyName;
    }

    public String getOutPicPath() {
        return outPicPath;
    }

    public void setOutPicPath(String outPicPath) {
        this.outPicPath = outPicPath;
    }

    public List<String> getPathList() {
        return pathList;
    }

    public void setPathList(List<String> pathList) {
        this.pathList = pathList;
    }
}
