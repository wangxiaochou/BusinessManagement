package com.xzty.cq.tover.businessmanagement.new_mainlist.view;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.xzty.cq.tover.businessmanagement.R;
import com.xzty.cq.tover.businessmanagement.new_mainlist.model.NewRspProjectListModel;

import java.util.List;

/**
 * author wl
 * Created 2018/08/20
 * explain 新项目列表recyclerview的adapter文件
 */

public class NewPartProjactListAdapter extends BaseQuickAdapter<NewRspProjectListModel, BaseViewHolder> {

    public NewPartProjactListAdapter(int layoutResId, @Nullable List<NewRspProjectListModel> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, NewRspProjectListModel item) {
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
