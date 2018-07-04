package com.xzty.cq.tover.businessmanagement.projectmanagement.tool.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.xzty.cq.tover.businessmanagement.R;
import com.xzty.cq.tover.businessmanagement.projectmanagement.tool.model.projectmanage.RspAffirmList;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * author zzl
 * Created 2018/5/23.
 * explain 申请单确认
 */

public class ToolApplyAffirmAdapter extends BaseQuickAdapter<RspAffirmList, BaseViewHolder> {
    public ToolApplyAffirmAdapter(int layoutResId, @Nullable List<RspAffirmList> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, RspAffirmList item) {
        helper.setText(R.id.item_tv_applycreate_orderNo,item.getToolApplyNo());
        helper.setText(R.id.item_tv_applycreate_listno,helper.getPosition()+1+"");
        helper.setText(R.id.item_tv_applycreate_orderapplyperson,item.getEplyName());
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date date = format.parse(item.getToolApplyTime());
            helper.setText(R.id.item_tv_applycreate_ordercreatetime,format.format(date));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        switch (item.getIsComfirm()) {
            case 0:
                helper.setText(R.id.tv_status, "项管部未确认");
                break;
            case 1:
                helper.setText(R.id.tv_status, "采购部未确认");
                break;
            case 2:
                helper.setText(R.id.tv_status, "采购部已确认");
                break;
            default:
                helper.setText(R.id.tv_status, "状态异常");
                break;
        }

    }
}
