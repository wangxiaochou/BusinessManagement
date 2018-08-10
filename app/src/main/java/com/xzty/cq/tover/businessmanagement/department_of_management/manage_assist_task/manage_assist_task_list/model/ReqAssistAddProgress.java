package com.xzty.cq.tover.businessmanagement.department_of_management.manage_assist_task.manage_assist_task_list.model;

import com.google.gson.annotations.SerializedName;

/**
 * author yq
 * date 2018/8/9
 * 添加协调任务进展的请求实体类
 */
public class ReqAssistAddProgress {
    //协调任务id
    @SerializedName("work_id")
    private int work_id;

    //协调任务进展内容
    @SerializedName("track_content")
    private String track_content;

    //期望完成时间
    @SerializedName("expect_time")
    private String expect_time;

    //创建人ID
    @SerializedName("eply_id")
    private String eply_id;

    //创建人姓名
    @SerializedName("eply_name")
    private String eply_name;

    //进展内容类型
    private int type;

    public ReqAssistAddProgress() {
    }

    public ReqAssistAddProgress(int work_id, String eply_id, String eply_name) {
        this.work_id = work_id;
        this.eply_id = eply_id;
        this.eply_name = eply_name;
    }

    public int getWork_id() {
        return work_id;
    }

    public void setWork_id(int work_id) {
        this.work_id = work_id;
    }

    public String getTrack_content() {
        return track_content;
    }

    public void setTrack_content(String track_content) {
        this.track_content = track_content;
    }

    public String getExpect_time() {
        return expect_time;
    }

    public void setExpect_time(String expect_time) {
        this.expect_time = expect_time;
    }

    public String getEply_id() {
        return eply_id;
    }

    public void setEply_id(String eply_id) {
        this.eply_id = eply_id;
    }

    public String getEply_name() {
        return eply_name;
    }

    public void setEply_name(String eply_name) {
        this.eply_name = eply_name;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "ReqAssistAddProgress{" +
                "work_id=" + work_id +
                ", track_content='" + track_content + '\'' +
                ", expect_time='" + expect_time + '\'' +
                ", eply_id='" + eply_id + '\'' +
                ", eply_name='" + eply_name + '\'' +
                ", type=" + type +
                '}';
    }
}
