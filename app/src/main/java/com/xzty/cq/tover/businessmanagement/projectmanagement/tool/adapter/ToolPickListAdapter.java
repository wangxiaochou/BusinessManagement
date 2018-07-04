package com.xzty.cq.tover.businessmanagement.projectmanagement.tool.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.xzty.cq.tover.businessmanagement.R;
import com.xzty.cq.tover.businessmanagement.projectmanagement.tool.model.pickdepartment.RspDistribute;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * author zzl
 * Created 2018/5/24.
 * explain
 */

public class ToolPickListAdapter extends BaseQuickAdapter<RspDistribute,BaseViewHolder>{
    public ToolPickListAdapter(int layoutResId, @Nullable List<RspDistribute> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, RspDistribute item) {
        if(item.isChecked){
            helper.setChecked(R.id.itbdbat_cb,true);
        }else{
            helper.setChecked(R.id.itbdbat_cb,false);
        }
        helper.setText(R.id.tv_username,"操作人: "+item.getToolDetailApplyToolName());
        helper.setText(R.id.tv_name,"名称: "+item.getToolDetailApplyToolName());
        helper.setText(R.id.tv_model,"型号: "+item.getToolDetailApplyToolModelNumber());
        helper.setText(R.id.tv_brand,"品牌: "+item.getToolDetailApplyToolBrand());
        helper.setText(R.id.tv_power,"功率: "+item.getToolDetailApplyToolPower());
        helper.setText(R.id.tv_count,"数量: "+item.getToolDetailApplyCount());
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date date = format.parse(item.getToolApplyTime());
            helper.setText(R.id.tv_time,"时间: "+format.format(date));
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }
}
