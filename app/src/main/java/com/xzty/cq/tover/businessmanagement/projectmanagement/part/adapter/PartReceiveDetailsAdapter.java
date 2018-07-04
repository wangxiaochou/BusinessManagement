package com.xzty.cq.tover.businessmanagement.projectmanagement.part.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.xzty.cq.tover.businessmanagement.R;
import com.xzty.cq.tover.businessmanagement.projectmanagement.part.model.deliver.DeliverDetails;
import com.xzty.cq.tover.businessmanagement.projectmanagement.utils.DateUtil;

import java.text.DecimalFormat;
import java.util.List;

/**
 * author zzl
 * Created 2018/5/15.
 * explain 发货详情
 */

public class PartReceiveDetailsAdapter extends BaseQuickAdapter<DeliverDetails, BaseViewHolder> {
    public static final int ITEM_TV_OUTFILLININFO_UNIT = R.id.item_tv_outfillininfo_unit;
    private DecimalFormat df = new DecimalFormat("#######.#######");

    public PartReceiveDetailsAdapter(int layoutResId, @Nullable List<DeliverDetails> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, DeliverDetails item) {
        helper.setText(R.id.tv_space, helper.getPosition() + 1 +item.getPartPlace());
        helper.setText(R.id.item_tv_outfillininfo_name, item.getPartName());
        helper.setText(R.id.item_tv_outfillininfo_partno, item.getPartNo());
        helper.setText(R.id.item_tv_outfillininfo_needcount, df.format(item.getApplyItemCount()));
        helper.setText(R.id.item_tv_outfillininfo_unit, item.getPartUnit());
        helper.setText(R.id.item_tv_outfillininfo_date, DateUtil.dateToString(item.getApplyItemExpettime()));
        helper.setText(R.id.item_tv_outfillininfo_batch, item.getOutBatch() + "批");
    }
}
