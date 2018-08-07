package com.xzty.cq.tover.businessmanagement.department_of_management.project_task.project_task_detail.model;

import com.google.gson.annotations.SerializedName;

public class GetMeetingDetail {

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

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public int getYears() {
        return Years;
    }

    public void setYears(int years) {
        Years = years;
    }

    public int getWeeks() {
        return Weeks;
    }

    public void setWeeks(int weeks) {
        Weeks = weeks;
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

    public String getMeet_Time() {
        return Meet_Time;
    }

    public void setMeet_Time(String meet_Time) {
        Meet_Time = meet_Time;
    }

    public String getMeet_Address() {
        return Meet_Address;
    }

    public void setMeet_Address(String meet_Address) {
        Meet_Address = meet_Address;
    }

    public String getMeet_Title() {
        return Meet_Title;
    }

    public void setMeet_Title(String meet_Title) {
        Meet_Title = meet_Title;
    }

    public String getPresenter() {
        return Presenter;
    }

    public void setPresenter(String presenter) {
        Presenter = presenter;
    }

    public String getNotekeeper() {
        return Notekeeper;
    }

    public void setNotekeeper(String notekeeper) {
        Notekeeper = notekeeper;
    }

    public String getConferee() {
        return Conferee;
    }

    public void setConferee(String conferee) {
        Conferee = conferee;
    }

    public String getAbsentee() {
        return Absentee;
    }

    public void setAbsentee(String absentee) {
        Absentee = absentee;
    }
}
