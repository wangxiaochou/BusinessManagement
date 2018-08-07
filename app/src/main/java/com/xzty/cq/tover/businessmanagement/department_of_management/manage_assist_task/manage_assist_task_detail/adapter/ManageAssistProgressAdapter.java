package com.xzty.cq.tover.businessmanagement.department_of_management.manage_assist_task.manage_assist_task_detail.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.xzty.cq.tover.businessmanagement.R;
import com.xzty.cq.tover.businessmanagement.department_of_management.manage_assist_task.manage_assist_task_detail.model.RspAssistProgressDetails;
import com.xzty.cq.tover.businessmanagement.projectmanagement.utils.DateUtil;

import java.util.List;

public class ManageAssistProgressAdapter extends BaseQuickAdapter<RspAssistProgressDetails,BaseViewHolder>{

    public ManageAssistProgressAdapter(int layoutResId, @Nullable List<RspAssistProgressDetails> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, RspAssistProgressDetails item) {
        DateUtil.strToDate(item.getExpectTime());
        helper.setText(R.id.tv_task_progress_date,item.getExpectTime());
        helper.setText(R.id.tv_task_progress_content,item.getTrackContent());
    }



}
