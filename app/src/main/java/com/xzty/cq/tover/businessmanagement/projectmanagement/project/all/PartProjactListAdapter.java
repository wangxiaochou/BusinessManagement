package com.xzty.cq.tover.businessmanagement.projectmanagement.project.all;

import android.support.annotation.Nullable;
import android.util.Log;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.xzty.cq.tover.businessmanagement.R;

import java.util.List;

/**
 * author zzl
 * Created 2018/5/4.
 * explain 工程列表的adapter
 */

public class PartProjactListAdapter extends BaseQuickAdapter<RspProjectListModel, BaseViewHolder> {
    public PartProjactListAdapter(int layoutResId, @Nullable List<RspProjectListModel> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, RspProjectListModel item) {
        helper.setText(R.id.tv_projectName, item.getProjectName());
        helper.setText(R.id.tv_projectLeader, item.getProjectLeader());
        helper.setText(R.id.tv_projectImDepartment, item.getImplementDepartment());
        helper.setText(R.id.tv_projectBuildTime, item.getBuildTime());
        helper.setText(R.id.tv_projectAddress, item.getProjectAddress());
        switch (item.getIsBuild()) {
            case 0:
                helper.setText(R.id.tv_projectIsBuild, "待开工");
                break;
            case 1:
                helper.setText(R.id.tv_projectIsBuild, "在建");
                break;
            case 2:
                helper.setText(R.id.tv_projectIsBuild, "完工");
                break;
        }
    }
}
