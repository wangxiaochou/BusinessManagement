package com.xzty.cq.tover.businessmanagement.projectmanagement.part.model.pick;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

/**
 * author zzl
 * Created 2018/5/11.
 * explain 采购单页面的数据
 */

public class RspPickOrder {
    @SerializedName("DIST_ASSIGNER_ID")
    private String distAssignerId;
    @SerializedName("DIST_ASSIGNER_NAME")
    private String distAssignerName;
    @SerializedName("DIST_BUYER_ID")
    private String distBuyerId;
    @SerializedName("DIST_ID")
    private String distId;
    @SerializedName("DIST_NO")
    private String distNo;
    @SerializedName("DIST_TIME")
    private String distTime;
    @SerializedName("EPLY_NAME")
    private String eplyName;
    @SerializedName("IS_COMFIRM")
    private int isComfirm;
    @SerializedName("IS_DELETE")
    private int isDelete;
    @SerializedName("PROJECT_ID")
    private int projectId;
    @SerializedName("PROJECT_NAME")
    private String projectName;
    @SerializedName("DELETE_TIME")
    private Date deleteTime;

    private String distNote;

    public String getDistAssignerId() {
        return distAssignerId;
    }

    public void setDistAssignerId(String distAssignerId) {
        this.distAssignerId = distAssignerId;
    }

    public String getDistAssignerName() {
        return distAssignerName;
    }

    public void setDistAssignerName(String distAssignerName) {
        this.distAssignerName = distAssignerName;
    }

    public String getDistBuyerId() {
        return distBuyerId;
    }

    public void setDistBuyerId(String distBuyerId) {
        this.distBuyerId = distBuyerId;
    }

    public String getDistId() {
        return distId;
    }

    public void setDistId(String distId) {
        this.distId = distId;
    }

    public String getDistNo() {
        return distNo;
    }

    public void setDistNo(String distNo) {
        this.distNo = distNo;
    }

    public String getDistTime() {
        return distTime;
    }

    public void setDistTime(String distTime) {
        this.distTime = distTime;
    }

    public String getEplyName() {
        return eplyName;
    }

    public void setEplyName(String eplyName) {
        this.eplyName = eplyName;
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

    public String getDistNote() {
        return distNote;
    }

    public void setDistNote(String distNote) {
        this.distNote = distNote;
    }
}
