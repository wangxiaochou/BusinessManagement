package com.xzty.cq.tover.businessmanagement.projectmanagement.part.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.xzty.cq.tover.businessmanagement.R;
import com.xzty.cq.tover.businessmanagement.projectmanagement.part.model.apply.RspApplyDetails;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * author zzl
 * Created 2018/5/10.
 * explain 申请详情的adapter
 */

public class ApplyDetailsAdapter extends BaseQuickAdapter<RspApplyDetails, BaseViewHolder> {
    public static SimpleDateFormat sDateFormat = new SimpleDateFormat("yyyy-MM-dd");
    public static DecimalFormat sDecimalFormat = new DecimalFormat("################.################");

    public ApplyDetailsAdapter(int layoutResId, @Nullable List<RspApplyDetails> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, RspApplyDetails item) {
        helper.setText(R.id.item_applyorderdetial_listNo, helper.getPosition() + 1 + "");
        helper.setText(R.id.item_applyorderdetial_listName, item.getPartName());
        helper.setText(R.id.item_applyorderdetial_listPartNo, item.getPartNo());
        helper.setText(R.id.item_applyorderdetial_listBatch, item.getApplyBatch() + "批");
        helper.setText(R.id.tv_space,item.getPartPlace());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            helper.setText(R.id.item_applyorderdetial_listExpetTime,sDateFormat.format(sdf.parse(item.getApplyItemExpettime())));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        helper.setText(R.id.item_applyorderdetial_listUnit, sDecimalFormat.format(item.getApplyItemCount()) + item.getPartUnit());
    }
}
