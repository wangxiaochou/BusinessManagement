package com.xzty.cq.tover.businessmanagement.projectmanagement.part.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.xzty.cq.tover.businessmanagement.R;
import com.xzty.cq.tover.businessmanagement.projectmanagement.part.model.use.AllModel;
import com.xzty.cq.tover.businessmanagement.projectmanagement.utils.DateUtil;

import java.text.DecimalFormat;
import java.util.List;

/**
 * author zzl
 * Created 2018/5/16.
 * explain 领用构件 中选择构件的adapter
 */

public class PartUseChooseAdapter extends BaseQuickAdapter<AllModel, BaseViewHolder> {
    private DecimalFormat df = new DecimalFormat("#######.#######");

    public PartUseChooseAdapter(int layoutResId, @Nullable List<AllModel> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, AllModel item) {
        helper.setText(R.id.item_tv_receiveparts_listno, helper.getPosition() + 1 + item.getPartPlace());
        helper.setChecked(R.id.item_cb_receiveparts_checkone, item.isCheck);
        helper.setText(R.id.item_tv_receiveparts_partname, item.getPartName());
        helper.setText(R.id.item_tv_receiveparts_partno, item.getPartNo());
        helper.setText(R.id.tv_space,item.getPartUnit());
        helper.setText(R.id.item_tv_receiveparts_applybatch, item.getApplyBatch() + "批");
        helper.setText(R.id.item_tv_receiveparts_outbatch, item.getOutBatch() + "批");
        helper.setText(R.id.item_tv_receiveparts_remaincount, df.format(item.getCollectDetailCount()));
        helper.setText(R.id.item_tv_receiveparts_unit, item.getPartUnit());
        helper.setText(R.id.item_tv_receiveparts_collecttime, DateUtil.dateToString(item.getCollectTime()));
    }
}
