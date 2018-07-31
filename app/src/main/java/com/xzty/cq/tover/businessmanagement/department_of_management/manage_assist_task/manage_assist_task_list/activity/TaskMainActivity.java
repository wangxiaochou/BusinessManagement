package com.xzty.cq.tover.businessmanagement.department_of_management.manage_assist_task.manage_assist_task_list.activity;


import android.content.Intent;
import android.support.v7.widget.Toolbar;
import android.view.ContextMenu;
import android.view.View;
import android.widget.ImageView;

import com.xzty.cq.tover.businessmanagement.R;
import com.xzty.cq.tover.businessmanagement.common.factory.ActivityPresenter;
import com.xzty.cq.tover.businessmanagement.common.factory.BaseContract;
import com.xzty.cq.tover.businessmanagement.department_of_management.manage_assist_task.manage_assist_task_list.activity.ManageAssistTaskActivity;

import butterknife.BindView;

/**
 * author yq
 * date 2018/7/31
 *任务管理主页activity
 */
public class TaskMainActivity extends ActivityPresenter implements View.OnClickListener{

    @BindView(R.id.toolbar_task_main)
    Toolbar toolbar;

    @BindView(R.id.iv_proj_task)
    ImageView iv_proj_task;

    @BindView(R.id.iv_assist_task)
    ImageView iv_assist_task;

    @Override
    protected int getContentLayoutId() {
        return R.layout.activity_task_main;
    }

    @Override
    protected BaseContract.Presenter initPresenter() {
        return null;
    }

    @Override
    public void showError(String str) {

    }

    @Override
    protected void initWidget() {
        super.initWidget();
        initToolbar();
        iv_proj_task.setOnClickListener(this);
        iv_assist_task.setOnClickListener(this);
    }

    private void initToolbar() {
        setSupportActionBar(toolbar);
       //设置返回图标点击事件
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_proj_task:
                //TODO
                break;
            case R.id.iv_assist_task:
                startActivity(new Intent(this, ManageAssistTaskActivity.class));
                break;
        }
    }
}
