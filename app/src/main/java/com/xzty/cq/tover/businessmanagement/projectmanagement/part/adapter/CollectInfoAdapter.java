package com.xzty.cq.tover.businessmanagement.projectmanagement.part.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.xzty.cq.tover.businessmanagement.R;
import com.xzty.cq.tover.businessmanagement.projectmanagement.part.model.collect.RspCollect;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * author zzl
 * Created 2018/5/11.
 * explain 分配信息的adapter
 */

public class CollectInfoAdapter extends BaseQuickAdapter<RspCollect, BaseViewHolder> {
    private DecimalFormat df = new DecimalFormat("#######.#######");

    public CollectInfoAdapter(int layoutResId, @Nullable List<RspCollect> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, RspCollect item) {
        helper.setText(R.id.tv_space, helper.getPosition() + 1 + item.getPartPlace());
        helper.setText(R.id.item_tv_distinfofillin_partname, item.getPartName());
        helper.setText(R.id.item_tv_distinfofillin_partno, item.getPartNo());
        helper.setText(R.id.item_tv_distinfofillin_needcount, df.format(item.getApplyItemCount()));
        helper.setText(R.id.item_tv_distinfofillin_unit, item.getPartUnit());
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date date = format.parse(item.getApplyItemExpettime());
            helper.setText(R.id.item_tv_distinfofillin_expettime,format.format(date));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        helper.setText(R.id.item_tv_distinfofillin_applybatch, item.getApplyBatch() + "批");
    }
}
