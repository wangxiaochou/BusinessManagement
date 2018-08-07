package com.xzty.cq.tover.businessmanagement.department_of_management.project_task.project_task_list.bean;

import com.google.gson.annotations.SerializedName;

/**
 * author wl
 * Created 2018/08/02
 * explain 项目任务列表javabean
 */

public class ProjectTaskList_List{

    @SerializedName("id")
    private int Id;

    @SerializedName("years")
    private int Years;

    @SerializedName("weeks")
    private int Weeks;

    @SerializedName("eply_id")
    private String Eply_Id;

    @SerializedName("eply_name")
    private String Eply_Name;

    @SerializedName("create_time")
    private String Create_Time;

    @SerializedName("meet_time")
    private String Meet_Time;

    @SerializedName("meet_address")
    private String Meet_Address;

    @SerializedName("meet_title")
    private String Meet_Title;

    @SerializedName("presenter")
    private String Presenter;

    @SerializedName("notekeeper")
    private String Notekeeper;

    @SerializedName("conferee")
    private String Conferee;

    @SerializedName("absentee")
    private String Absentee;


    public void setWeeks(int weeks) {
        Weeks = weeks;
    }

    public void setYears(int years) {
        Years = years;
    }

    public void setId(int id){
        this.Id = id;
    }

    public int getId(){
        return Id;
    }

    public void setMeet_Time(String meet_Time) {
        Meet_Time = meet_Time;
    }

    public String getMeet_Time(){
        return Meet_Time;
    }

    public void setMeet_Title(String meet_Title) {
        Meet_Title = meet_Title;
    }

    public String getMeet_Title() {
        return Meet_Title;
    }

    public void setMeet_Address(String meet_Address) {
        Meet_Address = meet_Address;
    }

    public String getMeet_Address() {
        return Meet_Address;
    }

    public int getWeeks(){
        return Weeks;
    }

    public int getYears(){
        return Years;
    }
}
