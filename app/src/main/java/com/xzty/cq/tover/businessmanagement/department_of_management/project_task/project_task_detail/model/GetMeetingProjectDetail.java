package com.xzty.cq.tover.businessmanagement.department_of_management.project_task.project_task_detail.model;

import com.google.gson.annotations.SerializedName;

/**
 * author wl
 * Created 2018/08/08
 * explain 周例会详细项目和会议合并的bean
 */

public class GetMeetingProjectDetail {

    @SerializedName("meet")
    private GetMeetingDetail getMeetingDetail;
    @SerializedName("task")
    private GetMeetingProjectTaskDetail getMeetingProjectTaskDetail;

    public GetMeetingDetail getGetMeetingDetail() {
        return getMeetingDetail;
    }
    public void setGetMeetingDetail(GetMeetingDetail getMeetingDetail) {
        this.getMeetingDetail = getMeetingDetail;
    }

    public GetMeetingProjectTaskDetail getGetMeetingProjectTaskDetail() {
        return getMeetingProjectTaskDetail;
    }

    public void setGetMeetingProjectTaskDetail(GetMeetingProjectTaskDetail getMeetingProjectTaskDetail) {
        this.getMeetingProjectTaskDetail = getMeetingProjectTaskDetail;
    }
}
