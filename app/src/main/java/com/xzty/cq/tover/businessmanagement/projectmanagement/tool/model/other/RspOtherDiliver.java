package com.xzty.cq.tover.businessmanagement.projectmanagement.tool.model.other;

import com.google.gson.annotations.SerializedName;

/**
 * author zzl
 * Created 2018/5/25.
 * explain
 */

public class RspOtherDiliver {

    @SerializedName("TOOL_OUT_ID")
    private int toolOutId;

    @SerializedName("PROJECT_ID")
    private String PROJECT_ID;

    @SerializedName("IS_DELET")
    private int isDelet;

    @SerializedName("TOOL_DIST_ID")
    private int toolDistId;

    @SerializedName("TOOL_OUT_NO")
    private String toolOutNo;

    @SerializedName("TOOL_OUT_USER_ID")
    private String toolOutUserId;

    @SerializedName("TOOL_OUT_TIME")
    private String toolOutTime;

    @SerializedName("TOOL_OUT_NOTE")
    private String toolOutNote;

    @SerializedName("DELETE_TIME")
    private String deleteTime;

    @SerializedName("FIRM")
    private String firm;

    @SerializedName("CONTRACT_NUMBER")
    private String contractNumber;

    @SerializedName("EXPECT_OUT_TIME")
    private String expectOutTime;

    @SerializedName("PROJECT_NAME")
    private String projectName;

    @SerializedName("EplyName")
    private String eplyName;

    public int getToolOutId() {
        return toolOutId;
    }

    public void setToolOutId(int toolOutId) {
        this.toolOutId = toolOutId;
    }

    public String getPROJECT_ID() {
        return PROJECT_ID;
    }

    public void setPROJECT_ID(String PROJECT_ID) {
        this.PROJECT_ID = PROJECT_ID;
    }

    public int getIsDelet() {
        return isDelet;
    }

    public void setIsDelet(int isDelet) {
        this.isDelet = isDelet;
    }

    public int getToolDistId() {
        return toolDistId;
    }

    public void setToolDistId(int toolDistId) {
        this.toolDistId = toolDistId;
    }

    public String getToolOutNo() {
        return toolOutNo;
    }

    public void setToolOutNo(String toolOutNo) {
        this.toolOutNo = toolOutNo;
    }

    public String getToolOutUserId() {
        return toolOutUserId;
    }

    public void setToolOutUserId(String toolOutUserId) {
        this.toolOutUserId = toolOutUserId;
    }

    public String getToolOutTime() {
        return toolOutTime;
    }

    public void setToolOutTime(String toolOutTime) {
        this.toolOutTime = toolOutTime;
    }

    public String getToolOutNote() {
        return toolOutNote;
    }

    public void setToolOutNote(String toolOutNote) {
        this.toolOutNote = toolOutNote;
    }

    public String getDeleteTime() {
        return deleteTime;
    }

    public void setDeleteTime(String deleteTime) {
        this.deleteTime = deleteTime;
    }

    public String getFirm() {
        return firm;
    }

    public void setFirm(String firm) {
        this.firm = firm;
    }

    public String getContractNumber() {
        return contractNumber;
    }

    public void setContractNumber(String contractNumber) {
        this.contractNumber = contractNumber;
    }

    public String getExpectOutTime() {
        return expectOutTime;
    }

    public void setExpectOutTime(String expectOutTime) {
        this.expectOutTime = expectOutTime;
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
}
