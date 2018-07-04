package com.xzty.cq.tover.businessmanagement.projectmanagement.part.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.xzty.cq.tover.businessmanagement.R;
import com.xzty.cq.tover.businessmanagement.projectmanagement.part.model.collect.RspCollect;
import com.xzty.cq.tover.businessmanagement.projectmanagement.utils.DateUtil;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * author zzl
 * Created 2018/5/10.
 * explain 申请汇总页面的adapter
 */

public class CollectAdapter extends BaseQuickAdapter<RspCollect, BaseViewHolder> {
    private DecimalFormat df = new DecimalFormat("#######.#######");

    public CollectAdapter(int layoutResId, @Nullable List<RspCollect> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, RspCollect item) {
        helper.setChecked(R.id.item_cb_applycollect_sigal, item.ischeck);
        helper.setText(R.id.item_tv_applycollect_partname, item.getPartName());
        helper.setText(R.id.item_tv_applycollect_partno, item.getPartNo());
        helper.setText(R.id.item_tv_applycollect_needcount, df.format(item.getApplyItemCount())+item.getPartUnit());
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date date = simpleDateFormat.parse(item.getApplyItemExpettime());
            helper.setText(R.id.item_tv_applycollect_expecttime, DateUtil.dateToString(date));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        helper.setText(R.id.item_tv_applycollect_applybatch, item.getApplyBatch() + "批");

    }
}
