package com.xzty.cq.tover.businessmanagement.projectmanagement.tool.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.xzty.cq.tover.businessmanagement.R;
import com.xzty.cq.tover.businessmanagement.projectmanagement.tool.model.other.RspBackDetails;

import java.util.List;

/**
 * author zzl
 * Created 2018/5/28.
 * explain
 */

public class ToolOtherBackDetailsAdapter extends BaseQuickAdapter<RspBackDetails, BaseViewHolder> {
    public ToolOtherBackDetailsAdapter(int layoutResId, @Nullable List<RspBackDetails> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, RspBackDetails item) {
        helper.setText(R.id.tv_number, "编号: " + item.getToolNumber());
        helper.setText(R.id.tv_name, "名称: " + item.getToolName());
        helper.setText(R.id.tv_model, "型号: " + item.getToolModelNumber());
        helper.setText(R.id.tv_brand, "品牌: " + item.getToolBrand());
        helper.setText(R.id.tv_power, "功率: " + item.getToolPower());
        helper.setText(R.id.tv_count, "数量: " + item.getToolCount());
        helper.setText(R.id.tv_department, "部门: " + item.getToolDepartment());
        switch (item.getToolBackDetailState()) {
            case 0:
                helper.setText(R.id.tv_state,"状态: 未审核");
                break;
            case 1:
                helper.setText(R.id.tv_state,"状态: 完好");
                break;
            case 2:
                helper.setText(R.id.tv_state,"状态: 维修");
                break;
            case 3:
                helper.setText(R.id.tv_state,"状态: 报废");
                break;
            default:
                helper.setText(R.id.tv_state,"状态: 异常");
                break;
        }
    }
}
