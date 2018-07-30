package com.xzty.cq.tover.businessmanagement.projectmanagement.task.activity;


import android.support.v7.widget.Toolbar;
import android.view.ContextMenu;
import android.view.View;

import com.xzty.cq.tover.businessmanagement.R;
import com.xzty.cq.tover.businessmanagement.common.factory.ActivityPresenter;
import com.xzty.cq.tover.businessmanagement.common.factory.BaseContract;
import com.xzty.cq.tover.businessmanagement.projectmanagement.task.view.MyToolbar;

import butterknife.BindView;

/**
 *
 */
public class TaskMainActivity extends ActivityPresenter{

    @BindView(R.id.toolbar_task_main)
    Toolbar toolbar;

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

}
