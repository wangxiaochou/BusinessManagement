package com.xzty.cq.tover.businessmanagement.projectmanagement.part.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.xzty.cq.tover.businessmanagement.R;
import com.xzty.cq.tover.businessmanagement.projectmanagement.part.model.deliver.DeliverDetails;

import java.text.DecimalFormat;
import java.util.List;

/**
 * author zzl
 * Created 2018/5/16.
 * explain 收货信息填写的Adapter
 */

public class PartReceiveWriteInfoAdapter extends BaseQuickAdapter<DeliverDetails,BaseViewHolder>{
    private DecimalFormat df = new DecimalFormat("#######.#######");
    public PartReceiveWriteInfoAdapter(int layoutResId, @Nullable List<DeliverDetails> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, DeliverDetails item) {
        helper.setText(R.id.item_tv_collectfillin_listno,helper.getPosition()+1+"");
        helper.setText(R.id.item_tv_collectfillin_partname,item.getPartName());
        helper.setText(R.id.item_tv_collectfillin_partno,item.getPartNo());
        helper.setText(R.id.item_et_collectfillin_needcount,(item.getResidualQuantity()==null||item.getResidualQuantity().equals(""))?"0":df.format(item.getResidualQuantity()));
        helper.setText(R.id.item_tv_collectfillin_unit,item.getPartUnit());
        helper.setText(R.id.item_tv_collectfillin_partbatch,item.getOutBatch()+"批");
        helper.addOnClickListener(R.id.item_iv_collectfillin_minus)
                .addOnClickListener(R.id.item_iv_collectfillin_add)
                .addOnClickListener(R.id.item_btn_collectfillin_checkone);
    }
}
