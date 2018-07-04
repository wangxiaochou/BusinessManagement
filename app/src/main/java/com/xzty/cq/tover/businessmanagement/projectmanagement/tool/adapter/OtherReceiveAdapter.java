package com.xzty.cq.tover.businessmanagement.projectmanagement.tool.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.xzty.cq.tover.businessmanagement.R;
import com.xzty.cq.tover.businessmanagement.projectmanagement.tool.model.other.RspReceive;

import java.util.List;

/**
 * author zzl
 * Created 2018/5/26.
 * explain
 */

public class OtherReceiveAdapter extends BaseQuickAdapter<RspReceive,BaseViewHolder> {
    public OtherReceiveAdapter(int layoutResId, @Nullable List<RspReceive> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, RspReceive item) {
        if(item.isChecked){
            helper.setChecked(R.id.checkbox,true);
        }else{
            helper.setChecked(R.id.checkbox,false);
        }
        helper.setText(R.id.tv_number,"编号:"+item.getToolNumber());
        helper.setText(R.id.tv_user,"操作人:"+item.getEplyName());
        helper.setText(R.id.tv_name,"名称:"+item.getToolName());
        helper.setText(R.id.tv_model,"型号:"+item.getToolModelNumber());
        helper.setText(R.id.tv_power,"功率:"+item.getToolPower());
        helper.setText(R.id.tv_brand,"品牌:"+item.getToolBrand());
        helper.setText(R.id.tv_count,"数量:"+item.getToolCount());
    }
}
