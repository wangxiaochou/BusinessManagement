package com.xzty.cq.tover.businessmanagement.common.main.frg;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.xzty.cq.tover.businessmanagement.R;
import com.xzty.cq.tover.businessmanagement.common.app.BaseFragment;
import com.xzty.cq.tover.businessmanagement.projectmanagement.project.all.ProjectListActivity;

import butterknife.BindView;

/**
 * author zzl
 * Created 2018/5/2.
 * explain 项目管理
 */

public class ManageFragment extends BaseFragment implements View.OnClickListener {
    @BindView(R.id.tv_toolbarTitle)
    TextView tv_toolbarTitle;

    @BindView(R.id.btn_manage)
    Button btn_manage;

    @BindView(R.id.btn_task)
    Button btn_proj_dep;


    @Override
    public int getContentLayout() {
        return R.layout.manage_fragment;
    }

    @Override
    protected void initWidget(View root) {
        super.initWidget(root);
        tv_toolbarTitle.setText("管理");
        btn_manage.setOnClickListener(this);
        btn_proj_dep.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_manage:
                startActivity(new Intent(getActivity(),ProjectListActivity.class));
                break;
        }
    }
}
