package com.xzty.cq.tover.businessmanagement.common.main.activity;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.xzty.cq.tover.businessmanagement.R;
import com.xzty.cq.tover.businessmanagement.common.factory.ActivityPresenter;
import com.xzty.cq.tover.businessmanagement.common.factory.BaseContract;
import com.xzty.cq.tover.businessmanagement.projectmanagement.project.AllManageItem;
import com.xzty.cq.tover.businessmanagement.department_of_management.manage_assist_task.manage_assist_task_list.activity.TaskMainActivity;

import butterknife.BindView;

public class ManageActivity extends ActivityPresenter implements View.OnClickListener{

    @BindView(R.id.tv_toolbarTitle)
    TextView tv_toolbarTitle;

    @BindView(R.id.btn_manage)
    Button btn_manage;

    @BindView(R.id.btn_task)
    Button btn_task;


    @Override
    public int getContentLayoutId() {
        return R.layout.manage_fragment;
    }

    @Override
    protected void initWidget() {
        super.initWidget();
        tv_toolbarTitle.setText("管理");
        btn_manage.setOnClickListener(this);
        btn_task.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_manage:
                startActivity(new Intent(this,AllManageItem.class));
                break;
            case R.id.btn_task:
                startActivity(new Intent(this, TaskMainActivity.class));
                break;
        }
    }

    @Override
    protected BaseContract.Presenter initPresenter() {
        return null;
    }

    @Override
    public void showError(String str) {

    }

    @Override
    public void showLoading() {

    }
}
