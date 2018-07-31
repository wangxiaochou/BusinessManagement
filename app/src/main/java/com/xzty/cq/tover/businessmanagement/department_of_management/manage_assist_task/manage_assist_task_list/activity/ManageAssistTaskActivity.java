package com.xzty.cq.tover.businessmanagement.department_of_management.manage_assist_task.manage_assist_task_list.activity;

import android.support.v7.widget.Toolbar;
import android.view.View;

import com.xzty.cq.tover.businessmanagement.R;
import com.xzty.cq.tover.businessmanagement.common.factory.ActivityPresenter;
import com.xzty.cq.tover.businessmanagement.common.factory.BaseContract;

import butterknife.BindView;

/**
 * author yq
 * date 2018/7/31
 * 员工任务列表主页
 */
public class ManageAssistTaskActivity extends ActivityPresenter{

    @BindView(R.id.tb_task_assits)
    Toolbar toolbar;

    @Override
    protected BaseContract.Presenter initPresenter() {
        return null;
    }

    @Override
    protected int getContentLayoutId() {
        return R.layout.activity_manage_assist_task;
    }

    @Override
    protected void initWidget() {
        super.initWidget();
        initToolbar();
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
    public void showError(String str) {

    }

    @Override
    public void showLoading() {

    }
}
