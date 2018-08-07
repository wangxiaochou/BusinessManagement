package com.xzty.cq.tover.businessmanagement.department_of_management.project_task.project_task_detail.model;

import com.google.gson.annotations.SerializedName;

public class GetMeetingProjectTaskDetail {

    @SerializedName("id")
    private int TaskId;
    @SerializedName("project_id")
    private String ProjectId;
    @SerializedName("years")
    private int Years;
    @SerializedName("weeks")
    private int weeks;
    @SerializedName("week_task")
    private String Week_Task;
    @SerializedName("next_week_task")
    private String Next_Week_Task;
    @SerializedName("mediate_matters")
    private String Mediate_Matters;
    @SerializedName("fund_thing")
    private String Fund_Thing;
    @SerializedName("analyse_error")
    private String Analyse_Error;
    @SerializedName("eply_id")
    private String Eply_Id;
    @SerializedName("eply_name")
    private String Eply_Name;
    @SerializedName("create_time")
    private String Create_Time;
    @SerializedName("old_week_task")
    private String Old_Week_Task;

    public int getTaskId() {
        return TaskId;
    }
    public void setTaskId(int taskId) {
        TaskId = taskId;
    }

    public int getYears() {
        return Years;
    }
    public void setYears(int years) {
        Years = years;
    }

    public String getProjectId() {
        return ProjectId;
    }
    public void setProjectId(String projectId) {
        ProjectId = projectId;
    }

    public int getWeeks() {
        return weeks;
    }
    public void setWeeks(int weeks) {
        this.weeks = weeks;
    }

    public String getWeek_Task() {
        return Week_Task;
    }
    public void setWeek_Task(String week_Task) {
        Week_Task = week_Task;
    }

    public String getNext_Week_Task() {
        return Next_Week_Task;
    }
    public void setNext_Week_Task(String next_Week_Task) {
        Next_Week_Task = next_Week_Task;
    }

    public String getMediate_Matters() {
        return Mediate_Matters;
    }
    public void setMediate_Matters(String mediate_Matters) {
        Mediate_Matters = mediate_Matters;
    }

    public String getFund_Thing() {
        return Fund_Thing;
    }
    public void setFund_Thing(String fund_Thing) {
        Fund_Thing = fund_Thing;
    }

    public String getAnalyse_Error() {
        return Analyse_Error;
    }
    public void setAnalyse_Error(String analyse_Error) {
        Analyse_Error = analyse_Error;
    }

    public String getEply_Id() {
        return Eply_Id;
    }
    public void setEply_Id(String eply_Id) {
        Eply_Id = eply_Id;
    }

    public String getEply_Name() {
        return Eply_Name;
    }
    public void setEply_Name(String eply_Name) {
        Eply_Name = eply_Name;
    }

    public String getCreate_Time() {
        return Create_Time;
    }
    public void setCreate_Time(String create_Time) {
        Create_Time = create_Time;
    }

    public String getOld_Week_Task() {
        return Old_Week_Task;
    }
    public void setOld_Week_Task(String old_Week_Task) {
        Old_Week_Task = old_Week_Task;
    }
}
