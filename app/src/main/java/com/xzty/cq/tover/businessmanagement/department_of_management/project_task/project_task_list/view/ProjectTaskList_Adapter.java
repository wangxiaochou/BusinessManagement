package com.xzty.cq.tover.businessmanagement.department_of_management.project_task.project_task_list.view;

/**
 * author wl
 * Created 2018/08/02
 * explain 项目任务列表RecyclerView的Adapter
 */


import android.support.annotation.Nullable;
import android.util.Log;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.xzty.cq.tover.businessmanagement.R;
import com.xzty.cq.tover.businessmanagement.department_of_management.project_task.project_task_list.bean.ProjectTaskList_List;

import java.util.List;


public class ProjectTaskList_Adapter extends BaseQuickAdapter<ProjectTaskList_List,BaseViewHolder>{

    public ProjectTaskList_Adapter(int layoutResId, List<ProjectTaskList_List> data){
        super(layoutResId,data);
    }

    @Override
    protected void convert(BaseViewHolder helper, ProjectTaskList_List item) {
        helper.setText(R.id.main_title_item,item.getMeet_Time());
        helper.setText(R.id.subtitle_item,"项管部"+ item.getMeet_Title() + "会议");
        helper.setText(R.id.thirdtitle_item,item.getMeet_Address());
    }
}