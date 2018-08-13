package com.xzty.cq.tover.businessmanagement.department_of_management.manage_assist_task.manage_assist_task_detail.model;

import com.google.gson.annotations.SerializedName;

/**
 * @Author yq
 * @Date 2018/8/3
 * 协调任务进展详情类
 */
public class RspAssistProgressDetails {
    private int id;

    //协调任务id
    @SerializedName("work_id")
    private int assistWorkId;

    //协调任务进展内容
    @SerializedName("track_content")
    private String trackContent;

    //期望完成时间
    @SerializedName("expect_time")
    private String expectTime;

    //记录人id
    @SerializedName("eply_id")
    private String eplyId;

    //记录人姓名
    @SerializedName("eply_name")
    private String eplyName;

    //协调任务进展创建时间
    @SerializedName("create_time")
    private String createTime;

    //是否删除
    @SerializedName("is_delete")
    private int isDelete;

    //进展类型(0:正常，1：逾期)
    @SerializedName("type")
    private int type;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAssistWorkId() {
        return assistWorkId;
    }

    public void setAssistWorkId(int assistWorkId) {
        this.assistWorkId = assistWorkId;
    }

    public String getTrackContent() {
        return trackContent;
    }

    public void setTrackContent(String trackContent) {
        this.trackContent = trackContent;
    }

    public String getExpectTime() {
        return expectTime;
    }

    public void setExpectTime(String expectTime) {
        this.expectTime = expectTime;
    }

    public String getEplyId() {
        return eplyId;
    }

    public void setEplyId(String eplyId) {
        this.eplyId = eplyId;
    }

    public String getEplyName() {
        return eplyName;
    }

    public void setEplyName(String eplyName) {
        this.eplyName = eplyName;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public int getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(int isDelete) {
        this.isDelete = isDelete;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "RspAssistProgressDetails{" +
                "id=" + id +
                ", assistWorkId=" + assistWorkId +
                ", trackContent='" + trackContent + '\'' +
                ", expectTime='" + expectTime + '\'' +
                ", eplyId='" + eplyId + '\'' +
                ", eplyName='" + eplyName + '\'' +
                ", createTime='" + createTime + '\'' +
                ", isDelete=" + isDelete +
                ", type=" + type +
                '}';
    }
}
