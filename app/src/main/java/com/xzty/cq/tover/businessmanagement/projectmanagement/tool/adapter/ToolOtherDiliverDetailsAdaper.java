package com.xzty.cq.tover.businessmanagement.projectmanagement.tool.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.xzty.cq.tover.businessmanagement.R;
import com.xzty.cq.tover.businessmanagement.projectmanagement.tool.model.other.RspDiliverDetails;

import java.util.List;

/**
 * author zzl
 * Created 2018/5/25.
 * explain
 */

public class ToolOtherDiliverDetailsAdaper extends BaseQuickAdapter<RspDiliverDetails,BaseViewHolder> {
    public ToolOtherDiliverDetailsAdaper(int layoutResId, @Nullable List<RspDiliverDetails> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, RspDiliverDetails item) {
        helper.setText(R.id.tv_number,"编号"+item.getToolNumber());
        helper.setText(R.id.tv_name,"名称"+item.getToolName());
        helper.setText(R.id.tv_model,"型号:"+item.getToolModelNumber());
        helper.setText(R.id.tv_brand,"品牌:"+item.getToolBrand());
        helper.setText(R.id.tv_power,"功率:"+item.getToolPower());
        helper.setText(R.id.tv_count,"数量:"+item.getToolCount());
        switch (item.getOutDetailState()){
            case 0:
                helper.setText(R.id.tv_state,"状态:未收货");
                break;
            case 1:
                helper.setText(R.id.tv_state,"状态:已收货");
                break;
           default:
                helper.setText(R.id.tv_state,"状态:异常");
                break;
        }
    }
}
