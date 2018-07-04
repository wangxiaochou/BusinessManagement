package com.xzty.cq.tover.businessmanagement.projectmanagement.tool.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.xzty.cq.tover.businessmanagement.R;
import com.xzty.cq.tover.businessmanagement.projectmanagement.tool.model.pickdepartment.RspDistributeOrderList;

import java.util.List;

/**
 * author zzl
 * Created 2018/5/25.
 * explain
 */

public class ToolPDDistributeOrderListAdapter extends BaseQuickAdapter<RspDistributeOrderList, BaseViewHolder> {
    public ToolPDDistributeOrderListAdapter(int layoutResId, @Nullable List<RspDistributeOrderList> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, RspDistributeOrderList item) {
        helper.setText(R.id.tv_position, helper.getPosition() + 1 + "");
        helper.setText(R.id.tv_no, item.getToolDistNo());
        helper.setText(R.id.tv_time, item.getToolDistTime());
        helper.setText(R.id.tv_note, item.getToolDistNote());
        helper.setText(R.id.tv_buyer,item.getAssignerName()+"->"+item.getBuyerName());
        switch (item.getIsComfirm()) {
            case 0:
                helper.setText(R.id.tv_status, "未读");
                break;
            case 1:
                helper.setText(R.id.tv_status, "已读");
                break;
            case 2:
                helper.setText(R.id.tv_status, "未购完");
                break;
            case 3:
                helper.setText(R.id.tv_status, "已购完");
                break;
            default:
                helper.setText(R.id.tv_status, "状态异常");
                break;
        }
    }
}
