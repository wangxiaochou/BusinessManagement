package com.xzty.cq.tover.businessmanagement.department_of_management.manage_assist_task.manage_assist_task_detail.adapter;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.ColorSpace;
import android.support.annotation.Nullable;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.xzty.cq.tover.businessmanagement.R;
import com.xzty.cq.tover.businessmanagement.department_of_management.manage_assist_task.manage_assist_task_detail.model.RspAssistProgressDetails;
import com.xzty.cq.tover.businessmanagement.projectmanagement.utils.DateUtil;

import java.util.List;

public class ManageAssistProgressAdapter extends BaseQuickAdapter<RspAssistProgressDetails,BaseViewHolder> implements View.OnClickListener{

    private int dataSize;
    private int newestId;
    private int oldestId;
    public ManageAssistProgressAdapter(int layoutResId, @Nullable List<RspAssistProgressDetails> data) {
        super(layoutResId, data);
        getHeaderAndFooterId();
    }

    public void getHeaderAndFooterId(){
        dataSize = getData().size();
        if (dataSize == 0){
            newestId = -1;
            oldestId = -1;
        }else if (dataSize == 1){
            newestId = getData().get(0).getId();
            oldestId = -1;
        }
        else {
            newestId = getData().get(0).getId();
            oldestId = getData().get(dataSize-1).getId();
        }

    }
    @Override
    protected void convert(BaseViewHolder helper, RspAssistProgressDetails item) {
        ;
        helper.setText(R.id.tv_task_progress_date,item.getCreateTime().substring(0,10));
        helper.setText(R.id.tv_task_progress_content,item.getTrackContent());
        helper.setImageResource(R.id.iv_task_progress_typeimage,item.getType()==0?R.drawable.iv_task_progress_complet:R.drawable.iv_task_progress_overdue);

        //设置最新和最早进展样式
        if (item.getId() ==newestId){

            if (item.getType()!=1){
                helper.setImageResource(R.id.iv_task_progress_typeimage,R.drawable.iv_task_progress_newest);
            }
        }
        if (item.getId() ==oldestId){
            helper.setImageResource(R.id.iv_task_progress_typeimage,R.drawable.iv_task_assist_start_circle);
            helper.setBackgroundColor(R.id.view_task_progress_bottomline,mContext.getResources().getColor(R.color.white));
        }
        if (dataSize == 1){
            helper.setBackgroundColor(R.id.view_task_progress_bottomline,mContext.getResources().getColor(R.color.white));
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_task_progress_add:

        }
    }
}
