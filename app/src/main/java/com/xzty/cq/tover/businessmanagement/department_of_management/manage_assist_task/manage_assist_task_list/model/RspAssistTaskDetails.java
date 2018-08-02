package com.xzty.cq.tover.businessmanagement.department_of_management.manage_assist_task.manage_assist_task_list.model;

import com.google.gson.annotations.SerializedName;
import com.xzty.cq.tover.businessmanagement.common.model.EmployeeModel;
import com.xzty.cq.tover.businessmanagement.projectmanagement.project.all.RspProjectListModel;

import java.util.List;

/**
 * author yq
 * date 2018/8/2
 * 协调任务模型类
 */
public class RspAssistTaskDetails {
    @SerializedName("id")
    private int id;
    @SerializedName("project_id")
    private int projectId;

    //协调内容
    @SerializedName("item_task")
    private String assistTask;

    //负责人
    @SerializedName("charge_person")
    private String chargePerson;

    //创建人ID
    @SerializedName("empl_id")
    private String emplId;

    //创建人姓名
    @SerializedName("empl_name")
    private String emplName;

    //是否完成
    @SerializedName("is_finish")
    private int isFinish;

    //预期完成时间
    @SerializedName("expect_time")
    private String expectTime;

    //实际完成时间
    @SerializedName("fact_time")
    private String factTime;

    //类型
    @SerializedName("type")
    private int type;

    //创建时间
    @SerializedName("creat_time")
    private String createTime;

    //是否删除
    @SerializedName("is_delete")
    private int isDelete;

 /*   //项目详情
    @SerializedName("item")
    private List<RspProjectListModel> project;

    //负责人
    @SerializedName("chargeperson")
    private List<EmployeeModel> employees;*/
    @SerializedName("item")
    private RspProjectListModel project;

    @SerializedName("chargeperson")
    private EmployeeModel employees;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    public String getAssistTask() {
        return assistTask;
    }

    public void setAssistTask(String assistTask) {
        this.assistTask = assistTask;
    }

    public String getChargePerson() {
        return chargePerson;
    }

    public void setChargePerson(String chargePerson) {
        this.chargePerson = chargePerson;
    }

    public String getEmplId() {
        return emplId;
    }

    public void setEmplId(String emplId) {
        this.emplId = emplId;
    }

    public String getEmplName() {
        return emplName;
    }

    public void setEmplName(String emplName) {
        this.emplName = emplName;
    }

    public int getIsFinish() {
        return isFinish;
    }

    public void setIsFinish(int isFinish) {
        this.isFinish = isFinish;
    }

    public String getExpectTime() {
        return expectTime;
    }

    public void setExpectTime(String expectTime) {
        this.expectTime = expectTime;
    }

    public String getFactTime() {
        return factTime;
    }

    public void setFactTime(String factTime) {
        this.factTime = factTime;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
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

    public RspProjectListModel getProject() {
        return project;
    }

    public void setProject(RspProjectListModel project) {
        this.project = project;
    }

    public EmployeeModel getEmployees() {
        return employees;
    }

    public void setEmployees(EmployeeModel employees) {
        this.employees = employees;
    }

    @Override
    public String toString() {
        return "RspAssistTaskDetails{" +
                "id=" + id +
                ", projectId=" + projectId +
                ", assistTask='" + assistTask + '\'' +
                ", chargePerson='" + chargePerson + '\'' +
                ", emplId='" + emplId + '\'' +
                ", emplName='" + emplName + '\'' +
                ", isFinish=" + isFinish +
                ", expectTime='" + expectTime + '\'' +
                ", factTime='" + factTime + '\'' +
                ", type=" + type +
                ", createTime='" + createTime + '\'' +
                ", isDelete=" + isDelete +
                ", project=" + project +
                ", employees=" + employees +
                '}';
    }
}
