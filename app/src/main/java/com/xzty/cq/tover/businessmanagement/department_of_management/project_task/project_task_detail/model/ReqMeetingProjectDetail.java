package com.xzty.cq.tover.businessmanagement.department_of_management.project_task.project_task_detail.model;

/**
 * author wl
 * Created 2018/08/08
 * explain 周例会详细post请求的三个参数的bean
 */

public class ReqMeetingProjectDetail {

    private int weeks,years;
    private String project_id;

    public ReqMeetingProjectDetail() {
    }

    public ReqMeetingProjectDetail(int weeks, int years, String projectId) {
        this.weeks = weeks;
        this.years = years;
        this.project_id = projectId;
    }

    public int getWeeks() {
        return weeks;
    }
    public void setWeeks(int weeks) {
        this.weeks = weeks;
    }

    public int getYears() {
        return years;
    }
    public void setYears(int years) {
        this.years = years;
    }

    public String getProjectId() {
        return project_id;
    }
    public void setProjectId(String projectId) {
        this.project_id = projectId;
    }
}
