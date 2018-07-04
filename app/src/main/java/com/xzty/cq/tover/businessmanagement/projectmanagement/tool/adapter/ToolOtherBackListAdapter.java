package com.xzty.cq.tover.businessmanagement.projectmanagement.tool.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.xzty.cq.tover.businessmanagement.R;
import com.xzty.cq.tover.businessmanagement.projectmanagement.tool.model.other.RspBackList;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * author zzl
 * Created 2018/5/28.
 * explain
 */

public class ToolOtherBackListAdapter extends BaseQuickAdapter<RspBackList, BaseViewHolder> {
    public ToolOtherBackListAdapter(int layoutResId, @Nullable List<RspBackList> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, RspBackList item) {
        helper.setText(R.id.tv_number, "编号: "+item.getToolBackNo());
        helper.setText(R.id.tv_name,"操作人: "+item.getEplyName());
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date date = format.parse(item.getToolBackTime());
            helper.setText(R.id.tv_time,"时间: "+format.format(date));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        helper.setText(R.id.tv_note, item.getToolBackNote());
        switch (item.getIsComfirm()) {
            case 0:
                helper.setText(R.id.tv_state, "状态: "+"未确认");
                break;
            case 1:
                helper.setText(R.id.tv_state, "状态: "+"采购部未确认");
                break;
            case 2:
                helper.setText(R.id.tv_state, "状态: "+"采购部已确认");
                break;
            default:

                helper.setText(R.id.tv_state, "状态: "+"状态异常");
                break;
        }
    }
}
