package com.xzty.cq.tover.businessmanagement.projectmanagement.part.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.xzty.cq.tover.businessmanagement.R;
import com.xzty.cq.tover.businessmanagement.projectmanagement.part.model.use.AllModel;

import java.text.DecimalFormat;
import java.util.List;

/**
 * author zzl
 * Created 2018/5/17.
 * explain
 */

public class PartUseWriteInfoAdapter extends BaseQuickAdapter<AllModel, BaseViewHolder> {
    private DecimalFormat df = new DecimalFormat("#######.#######");

    public PartUseWriteInfoAdapter(int layoutResId, @Nullable List<AllModel> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, AllModel item) {
        helper.setText(R.id.item_tv_pickpartcountset_listno, helper.getPosition() + 1 + item.getPartPlace());
        helper.setText(R.id.item_tv_pickpartcountset_partname, item.getPartName());
        helper.setText(R.id.item_tv_pickpartcountset_partno, item.getPartNo());
        helper.setText(R.id.item_tv_pickpartcountset_applybatch, item.getApplyBatch() + "批");
        helper.setText(R.id.item_tv_pickpartcountset_applybatch, item.getOutBatch() + "批");
        helper.setText(R.id.item_tv_pickpartcountset_needcount,(item.getCollectDetailCount()==null||item.getCollectDetailCount().equals(""))?"0":df.format(item.getCollectDetailCount()));
        helper.setText(R.id.item_tv_pickpartcountset_unit, item.getPartUnit());
        helper.addOnClickListener(R.id.item_iv_pickpartcountset_minus);
        helper.addOnClickListener(R.id.item_iv_pickpartcountset_add);
    }
}
