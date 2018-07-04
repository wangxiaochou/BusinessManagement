package com.xzty.cq.tover.businessmanagement.projectmanagement.tool.adapter;

import android.graphics.Color;
import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.xzty.cq.tover.businessmanagement.R;
import com.xzty.cq.tover.businessmanagement.projectmanagement.tool.model.pickdepartment.RspDistributeOrderDetails;

import java.util.List;

/**
 * author zzl
 * Created 2018/5/29.
 * explain
 */

public class ToolPDDistributeDetailsAdapter extends BaseQuickAdapter<RspDistributeOrderDetails, BaseViewHolder> {
    public ToolPDDistributeDetailsAdapter(int layoutResId, @Nullable List<RspDistributeOrderDetails> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, RspDistributeOrderDetails item) {
        helper.setText(R.id.tv_name, "名称: " + item.getToolDetailApplyToolName());
        helper.setText(R.id.tv_model, "型号: " + item.getToolDetailApplyToolModelNumber());
        helper.setText(R.id.tv_brand, "品牌: " + item.getToolDetailApplyToolBrand());
        helper.setText(R.id.tv_power, "功率: " + item.getToolDetailApplyToolPower());
        helper.setText(R.id.tv_count, "数量: " + item.getToolDetailApplyCount());
        switch (item.getToolIsout()) {
            case 0:
                helper.setText(R.id.tv_state, "状态: 未发货");
                break;

            case 1:
                helper.setText(R.id.tv_state, "状态: 已发货");
                break;

            default:
                helper.setText(R.id.tv_state, "状态: 异常");
                break;
        }
        helper.setText(R.id.tv_storage, "已入库数: " + item.getPutInStorage());
        if (item.getDisable()==0) {
            helper.setBackgroundColor(R.id.itbdbacd_ll, Color.RED);
        } else {
            helper.setBackgroundColor(R.id.itbdbacd_ll, Color.TRANSPARENT);
        }
        if(item.isChecked){
            helper.setChecked(R.id.cb,true);
        }else{
            helper.setChecked(R.id.cb,false);
        }
    }
}
