package com.xzty.cq.tover.businessmanagement.projectmanagement.tool.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.xzty.cq.tover.businessmanagement.R;
import com.xzty.cq.tover.businessmanagement.projectmanagement.tool.model.other.RspReceiveList;

import java.util.List;

/**
 * author zzl
 * Created 2018/5/26.
 * explain
 */

public class ToolOtherReceiveListAdapter extends BaseQuickAdapter<RspReceiveList,BaseViewHolder>{
    public ToolOtherReceiveListAdapter(int layoutResId, @Nullable List<RspReceiveList> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, RspReceiveList item) {
        helper.setText(R.id.tv_number,"编号: "+item.getToolCollectNo());
        helper.setText(R.id.tv_name,"操作人: "+item.getEplyName());
        helper.setText(R.id.tv_time,"收货时间: "+item.getToolCollectTime());
        helper.setText(R.id.tv_note,"备注: "+item.getToolCollectNote());
    }
}
