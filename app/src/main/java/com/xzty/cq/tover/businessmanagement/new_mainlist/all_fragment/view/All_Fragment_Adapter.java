package com.xzty.cq.tover.businessmanagement.new_mainlist.all_fragment.view;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.xzty.cq.tover.businessmanagement.R;
import com.xzty.cq.tover.businessmanagement.new_mainlist.model.NewRspProjectListModel;

import java.util.List;

/**
 * author wl
 * Created 2018/08/21
 * explain 首页新项目列表All_Fragment_Adapter
 */

public class All_Fragment_Adapter extends BaseQuickAdapter<NewRspProjectListModel, BaseViewHolder> {

    public All_Fragment_Adapter(int layoutResId, @Nullable List<NewRspProjectListModel> data) {
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
