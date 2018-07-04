package com.xzty.cq.tover.businessmanagement.projectmanagement.tool.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.xzty.cq.tover.businessmanagement.R;
import com.xzty.cq.tover.businessmanagement.projectmanagement.tool.model.other.RspOtherDiliver;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * author zzl
 * Created 2018/5/25.
 * explain
 */

public class ToolOtherDiliverOrderAdapter extends BaseQuickAdapter<RspOtherDiliver, BaseViewHolder> {
    public ToolOtherDiliverOrderAdapter(int layoutResId, @Nullable List<RspOtherDiliver> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, RspOtherDiliver item) {
        helper.setText(R.id.itoo_tv_no, "编号" + item.getToolOutNo());
        helper.setText(R.id.itoo_tv_name, "操作人:" + item.getEplyName());
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            date = format.parse(item.getToolOutTime());
            helper.setText(R.id.itoo_tv_time,"发货时间"+format.format(date));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        helper.setText(R.id.itoo_tv_number, "合同编号:" + item.getContractNumber());
        helper.setText(R.id.itoo_tv_firm, "厂商:" + item.getFirm());
        Date date2 = null;
        try {
            date2 = format.parse(item.getToolOutTime());
            helper.setText(R.id.itoo_tv_eTime, "预计发货时间:" + format.format(date2));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        helper.setText(R.id.itoo_tv_note, "备注:" + item.getToolOutNote());
    }
}
