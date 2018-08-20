package com.xzty.cq.tover.businessmanagement.new_bottomnavigation.department_fragment;

import android.content.Intent;
import android.view.ContextMenu;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.xzty.cq.tover.businessmanagement.R;
import com.xzty.cq.tover.businessmanagement.common.app.BaseFragment;
import com.xzty.cq.tover.businessmanagement.department_of_management.manage_assist_task.manage_assist_task_list.activity.ManageAssistTaskActivity;
import com.xzty.cq.tover.businessmanagement.department_of_management.project_task.project_task_list.activity.ProjectTaskList_Activity;

import butterknife.BindView;

/**
 * author wl
 * Created 2018/08/20
 * explain 项管部管理Fragment
 */

public class Department_Fragment extends BaseFragment implements View.OnClickListener{

    @BindView(R.id.tv_toolbarTitle)
    TextView tv_toolbarTitle;

    @BindView(R.id.iv_proj_task)
    ImageView iv_proj_task;

    @BindView(R.id.iv_assist_task)
    ImageView iv_assist_task;

    @Override
    public int getContentLayout() {
        return R.layout.new_department_fragment;
    }

//    @Override
    public void showError(String str) {

    }

    @Override
    protected void initWidget(View root) {
        super.initWidget(root);
        iv_proj_task.setOnClickListener(this);
        iv_assist_task.setOnClickListener(this);

        tv_toolbarTitle.setText("项管部管理");
    }

//    @Override
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
                startActivity(new Intent(this.getActivity(), ProjectTaskList_Activity.class));
                break;
            case R.id.iv_assist_task:
                startActivity(new Intent(this.getActivity(), ManageAssistTaskActivity.class));
                break;
        }
    }
}
