package com.xzty.cq.tover.businessmanagement.department_of_management.project_task.project_task_list.presenter;

import android.util.Log;

import com.xzty.cq.tover.businessmanagement.common.data.DataSourse;
import com.xzty.cq.tover.businessmanagement.common.factory.BasePresenter;

import com.xzty.cq.tover.businessmanagement.department_of_management.project_task.project_task_list.bean.ProjectTaskList_List;
import com.xzty.cq.tover.businessmanagement.department_of_management.project_task.project_task_list.contrat.ProjectTaskList_Contract;

import java.util.ArrayList;
import java.util.List;


public class ProjectTaskList_Presenter extends BasePresenter<ProjectTaskList_Contract.View>
        implements ProjectTaskList_Contract.Presenter,DataSourse.Callback<List<ProjectTaskList_List>>{

    public ProjectTaskList_Presenter(ProjectTaskList_Contract.View view){
        super(view);
    }

    @Override
    public void getProjectTaskList(int id) {
        ProjectTaskList_Helper.getProjectTaskList(id,this);
    }

    @Override
    public void onDataLoaded(List<ProjectTaskList_List> mlist) {
        ProjectTaskList_Contract.View view = getView();
        view.success(mlist);
    }

    @Override
    public void onDataNotAvailable(String strRes) {
        ProjectTaskList_Contract.View view = getView();
        view.showError(strRes);
    }

    }

