package com.xzty.cq.tover.businessmanagement.projectmanagement.part.adapter;

import android.support.annotation.Nullable;
import android.widget.Button;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.xzty.cq.tover.businessmanagement.R;
import com.xzty.cq.tover.businessmanagement.projectmanagement.part.model.deliver.DeliverOrder;

import java.util.List;

/**
 * author zzl
 * Created 2018/5/15.
 * explain 收货单的adapter
 */

public class ReceiveOrderAdapter extends BaseQuickAdapter<DeliverOrder, BaseViewHolder> {
    public ReceiveOrderAdapter(int layoutResId, @Nullable List<DeliverOrder> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, DeliverOrder item) {
        helper.setText(R.id.item_tv_collectorder_listno, helper.getPosition() + 1 + "");
        helper.setText(R.id.item_tv_collectorder_outtime, item.getOutNo());
        helper.setText(R.id.item_tv_collectorder_outername, item.getEplyName());
        helper.setText(R.id.item_tv_collectorder_firm, item.getFirm() == null ? "空" : item.getFirm());
        helper.addOnClickListener(R.id.item_btn_collectorder_collect);
        if (item.getIsComfirm() == 1) {
            //未收货
            helper.setGone(R.id.item_tv_collectorder_state, false);
            helper.setGone(R.id.item_btn_collectorder_collect, true);
            helper.setText(R.id.item_btn_collectorder_collect, "收货");
            Button view = helper.getView(R.id.item_btn_collectorder_collect);
            view.setTextSize(12);
        } else if (item.getIsComfirm() == 2) {
            //已收货
            helper.setGone(R.id.item_tv_collectorder_state, true);
            helper.setGone(R.id.item_btn_collectorder_collect, false);
        } else if (item.getIsComfirm() == 3) {
            //部分收货
            helper.setGone(R.id.item_tv_collectorder_state, false);
            helper.setGone(R.id.item_btn_collectorder_collect, true);
            helper.setText(R.id.item_btn_collectorder_collect, "继续收货");
            Button view = helper.getView(R.id.item_btn_collectorder_collect);
            view.setTextSize(8);
        }
    }
}
