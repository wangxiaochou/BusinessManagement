package com.xzty.cq.tover.businessmanagement.projectmanagement.tool.adapter;

import android.support.annotation.Nullable;
import android.text.TextUtils;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.xzty.cq.tover.businessmanagement.R;
import com.xzty.cq.tover.businessmanagement.projectmanagement.tool.model.projectmanage.RspToolApplyList;

import java.util.List;

/**
 * author zzl
 * Created 2018/5/21.
 * explain 机具申请的adapter
 */

public class ToolApplyInfoAdapter extends BaseQuickAdapter<RspToolApplyList, BaseViewHolder> {
    public ToolApplyInfoAdapter(int layoutResId, @Nullable List<RspToolApplyList> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, RspToolApplyList item) {
        helper.setChecked(R.id.cb_item, item.isChecked);
        helper.setText(R.id.tv_name, item.getToolName());
        helper.setText(R.id.tv_number, item.getToolCount() + "");
        helper.setText(R.id.tv_type, TextUtils.isEmpty(item.getToolModelNumber())?"空":item.getToolModelNumber());
        helper.setText(R.id.tv_position, helper.getPosition() + 1 + "");
        if(TextUtils.isEmpty(item.getToolDepot())){
            helper.setText(R.id.tv_depot, "新建");
        }else{
            switch (item.getToolDepot()) {
                case "1":
                    helper.setText(R.id.tv_depot, "项管部仓库");
                    break;
                case "2":
                    helper.setText(R.id.tv_depot, "采购部仓库");
                    break;
                default:
                    helper.setText(R.id.tv_depot, "异常,暂无仓库");
                    break;
            }
        }
        switch (item.getToolIsdamage()) {
            case 0:
                helper.setText(R.id.tv_isdamage, "完好");
                break;
            case 1:
                helper.setText(R.id.tv_isdamage, "维修");
                break;
            case 2:
                helper.setText(R.id.tv_isdamage, "损坏");
                break;
            case 3:
                helper.setText(R.id.tv_isdamage, "报废");
                break;
            default:
                helper.setText(R.id.tv_isdamage, item.getToolIsdamage());
                break;
        }
    }
}
