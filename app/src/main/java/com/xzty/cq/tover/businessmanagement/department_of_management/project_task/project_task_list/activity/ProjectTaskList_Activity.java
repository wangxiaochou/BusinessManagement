package com.xzty.cq.tover.businessmanagement.department_of_management.project_task.project_task_list.activity;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.xzty.cq.tover.businessmanagement.R;
import com.xzty.cq.tover.businessmanagement.common.factory.ActivityPresenter;
import com.xzty.cq.tover.businessmanagement.department_of_management.project_task.project_task_detail.activity.MeetingProjectDetail_Activity;
import com.xzty.cq.tover.businessmanagement.department_of_management.project_task.project_task_list.bean.ProjectTaskList_List;
import com.xzty.cq.tover.businessmanagement.department_of_management.project_task.project_task_list.contrat.ProjectTaskList_Contract;
import com.xzty.cq.tover.businessmanagement.department_of_management.project_task.project_task_list.presenter.ProjectTaskList_Presenter;
import com.xzty.cq.tover.businessmanagement.department_of_management.project_task.project_task_list.view.ProjectTaskList_Adapter;


import java.util.List;

import butterknife.BindView;

/**
 * author wl
 * Created 2018/07/31
 * explain 项目任务列表Activity文件
 */


public class ProjectTaskList_Activity extends ActivityPresenter<ProjectTaskList_Contract.Presenter>
        implements ProjectTaskList_Contract.View,BaseQuickAdapter.OnItemClickListener{

    @BindView(R.id.project_task_list_toolbar)
    Toolbar toolbar_project_task_list;

    @BindView(R.id.rv_projecttasklist)
    RecyclerView rlv_project_task_list;

    private List<ProjectTaskList_List> projecttask_list ;

    @Override
    protected int getContentLayoutId() {
        return R.layout.project_task_list_activity;
    }

    @Override
    protected void initData() {
        super.initData();
        int id = getIntent().getIntExtra("id",1);
        mPresenter.getProjectTaskList(id);
    }

    @Override
    protected void initWidget() {
        super.initWidget();
        initToolbar();
    }

    private void initToolbar() {
        setSupportActionBar(toolbar_project_task_list);
        //设置返回图标点击事件
        toolbar_project_task_list.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void setAdapter(){
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        rlv_project_task_list.setLayoutManager(layoutManager);
        ProjectTaskList_Adapter adapter = new ProjectTaskList_Adapter(R.layout.project_task_list_item,projecttask_list);
        rlv_project_task_list.setAdapter(adapter);
        adapter.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        Intent intent = new Intent(this, MeetingProjectDetail_Activity.class);
        intent.putExtra("meet_time",projecttask_list.get(position).getMeet_Time());
        intent.putExtra("weeks",projecttask_list.get(position).getWeeks());
        intent.putExtra("years",projecttask_list.get(position).getYears());
        startActivity(intent);
    }

    @Override
    public void showLoading() {
    }

    //@Override
    public void success(List<ProjectTaskList_List> list) {
        projecttask_list = list;
        setAdapter();
    }

    @Override
    protected ProjectTaskList_Contract.Presenter initPresenter() {
        return new ProjectTaskList_Presenter(this);
    }

    @Override
    public void showError(String str) {
        Toast.makeText(this,str,Toast.LENGTH_SHORT).show();
    }

}


