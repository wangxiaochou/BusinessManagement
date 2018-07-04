package com.xzty.cq.tover.businessmanagement.projectmanagement.project;

import android.content.Intent;
import android.view.View;
import android.widget.LinearLayout;

import com.xzty.cq.tover.businessmanagement.R;
import com.xzty.cq.tover.businessmanagement.common.MyApplication;
import com.xzty.cq.tover.businessmanagement.common.app.BaseActivity;
import com.xzty.cq.tover.businessmanagement.projectmanagement.part.activity.PartFunctionActivity;
import com.xzty.cq.tover.businessmanagement.projectmanagement.project.all.ProjectListActivity;
import com.xzty.cq.tover.businessmanagement.projectmanagement.tool.activity.ToolFunctionActivity;

import butterknife.BindView;

/**
 * author zzl
 * Created 2018/5/5
 * explain 所有管理项目
 */
public class AllManageItem extends BaseActivity implements View.OnClickListener {
    @BindView(R.id.ll_part)
    LinearLayout ll_part;
    @BindView(R.id.ll_tools)
    LinearLayout ll_tools;

    @Override
    protected int getContentLayoutId() {
        return R.layout.activity_all_manage_item;
    }

    @Override
    protected void initWidget() {
        super.initWidget();
        ll_part.setOnClickListener(this);
        ll_tools.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_part:
                finish();
                MyApplication.getInstance().finishAssignActivity(ProjectListActivity.class);
                startActivity(new Intent(this, PartFunctionActivity.class));
                break;
            case R.id.ll_tools:
                finish();
                MyApplication.getInstance().finishAssignActivity(ProjectListActivity.class);
                startActivity(new Intent(this, ToolFunctionActivity.class));
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        MyApplication.getInstance().removeActivity(this);
    }
}
