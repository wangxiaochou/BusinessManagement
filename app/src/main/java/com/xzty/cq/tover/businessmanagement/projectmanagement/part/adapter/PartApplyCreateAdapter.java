package com.xzty.cq.tover.businessmanagement.projectmanagement.part.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.xzty.cq.tover.businessmanagement.R;
import com.xzty.cq.tover.businessmanagement.common.MyApplication;
import com.xzty.cq.tover.businessmanagement.projectmanagement.part.model.apply.RspApplyList;
import com.xzty.cq.tover.businessmanagement.projectmanagement.utils.DateUtil;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * author zzl
 * Created 2018/5/7.
 * explain 申请列表的adapter
 */

public class PartApplyCreateAdapter extends BaseQuickAdapter<RspApplyList, BaseViewHolder> {
    DateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
    public PartApplyCreateAdapter(int layoutResId, @Nullable List<RspApplyList> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, RspApplyList item) {
        helper.setText(R.id.item_tv_applycreate_listno, String.valueOf(helper.getPosition() + 1));
        helper.setText(R.id.item_tv_applycreate_orderNo, item.getApplyNo());
        helper.setText(R.id.item_tv_applycreate_orderapplyperson, item.getEplyName());
        try {
            helper.setText(R.id.item_tv_applycreate_ordercreatetime,DateUtil.dateToString(format1.parse(item.getApplyTime())));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        helper.addOnClickListener(R.id.item_tv_applycreate_hadapply);
        if (item.getIsComfirm() == 0) {
            //未确认
            helper.setImageResource(R.id.item_iv_applycreate_orderstate, R.drawable.fork);
            helper.setText(R.id.item_tv_applycreate_hadapply, "未确认");
            helper.setTextColor(R.id.item_tv_applycreate_hadapply, MyApplication.getInstance().getResources().getColor(R.color.item_text_no));
        } else {
            helper.setImageResource(R.id.item_iv_applycreate_orderstate, R.drawable.right);
            helper.setText(R.id.item_tv_applycreate_hadapply, "已确认");
            helper.setTextColor(R.id.item_tv_applycreate_hadapply, MyApplication.getInstance().getResources().getColor(R.color.item_text_ok));
        }
    }
}
