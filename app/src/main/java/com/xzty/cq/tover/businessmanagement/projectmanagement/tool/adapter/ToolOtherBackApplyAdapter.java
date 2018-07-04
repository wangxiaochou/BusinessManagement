package com.xzty.cq.tover.businessmanagement.projectmanagement.tool.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.xzty.cq.tover.businessmanagement.R;
import com.xzty.cq.tover.businessmanagement.projectmanagement.tool.model.other.RspBackApply;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * author zzl
 * Created 2018/5/26.
 * explain
 */

public class ToolOtherBackApplyAdapter extends BaseQuickAdapter<RspBackApply,BaseViewHolder>{
    public ToolOtherBackApplyAdapter(int layoutResId, @Nullable List<RspBackApply> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, RspBackApply item) {
        if(item.isChecked){
            helper.setChecked(R.id.cb,true);
        }else{
            helper.setChecked(R.id.cb,false);
        }
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date date = format.parse(item.getToolCollectTime());
            helper.setText(R.id.tv_time,"时间: "+format.format(date));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        helper.setText(R.id.tv_number,"编号: "+item.getToolNumber());
        helper.setText(R.id.tv_name,"名称: "+item.getToolName());
        helper.setText(R.id.tv_taker,"操作人: "+item.getEplyName());
        helper.setText(R.id.tv_model,"型号: "+item.getToolModelNumber());
        helper.setText(R.id.tv_brand,"品牌: "+item.getToolBrand());
        helper.setText(R.id.tv_power,"功率: "+item.getToolPower());
        helper.setText(R.id.tv_count,"数量: "+item.getToolCount());
        helper.setText(R.id.tv_note,"备注: "+item.getToolCollectDetailNote());
    }
}
