package com.xzty.cq.tover.businessmanagement.projectmanagement.part.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.xzty.cq.tover.businessmanagement.R;
import com.xzty.cq.tover.businessmanagement.projectmanagement.part.model.pick.RspPickOrder;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * author zzl
 * Created 2018/5/11.
 * explain 采购单的adapter
 */

public class PartPickOrderAdapter extends BaseQuickAdapter<RspPickOrder, BaseViewHolder> {
    public PartPickOrderAdapter(int layoutResId, @Nullable List<RspPickOrder> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, RspPickOrder item) {
        helper.setText(R.id.item_tv_distorder_listno, helper.getPosition() + 1 + "");
        helper.setText(R.id.item_tv_distorder_distno, item.getDistNo());
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date date = formatter.parse(item.getDistTime());
            helper.setText(R.id.item_tv_distorder_expettime,formatter.format(date));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        helper.setText(R.id.item_tv_distorder_assigner, item.getDistAssignerName());
        helper.setText(R.id.item_tv_distorder_buyer, item.getEplyName());
        helper.addOnClickListener(R.id.item_btn_distorder_work);

        if (item.getIsComfirm() == 0) {  //未确认
            helper.setImageResource(R.id.item_iv_distorder_state, R.drawable.ic_clear_red_500_24dp);
            helper.setGone(R.id.item_btn_distorder_work, true);
            helper.setText(R.id.item_btn_distorder_work, "确认");
            helper.setGone(R.id.item_tv_distorder_hadbuy, false);
        } else if (item.getIsComfirm() == 1) {  //确认
            helper.setImageResource(R.id.item_iv_distorder_state, R.drawable.ic_done_green_500_24dp);
            helper.setGone(R.id.item_btn_distorder_work, true);
            helper.setText(R.id.item_btn_distorder_work, "采购");
            helper.setGone(R.id.item_tv_distorder_hadbuy, false);
        } else if (item.getIsComfirm() == 2) {  //确认
            helper.setImageResource(R.id.item_iv_distorder_state, R.drawable.ic_done_green_500_24dp);
            helper.setGone(R.id.item_btn_distorder_work, true);
            helper.setText(R.id.item_btn_distorder_work, "部分采购");
            helper.setGone(R.id.item_tv_distorder_hadbuy, false);
        } else {
            helper.setImageResource(R.id.item_iv_distorder_state, R.drawable.ic_done_all_green_500_24dp);
            helper.setGone(R.id.item_btn_distorder_work, false);
            helper.setGone(R.id.item_tv_distorder_hadbuy, true);
        }
    }
}
