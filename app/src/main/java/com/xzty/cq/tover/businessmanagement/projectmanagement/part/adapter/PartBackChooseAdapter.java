package com.xzty.cq.tover.businessmanagement.projectmanagement.part.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.xzty.cq.tover.businessmanagement.R;
import com.xzty.cq.tover.businessmanagement.projectmanagement.part.model.back.BackPart;

import java.text.DecimalFormat;
import java.util.List;

/**
 * author zzl
 * Created 2018/5/17.
 * explain
 */

public class PartBackChooseAdapter extends BaseQuickAdapter<BackPart,BaseViewHolder>{
    private DecimalFormat df = new DecimalFormat("#######.#######");
    public PartBackChooseAdapter(int layoutResId, @Nullable List<BackPart> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, BackPart item) {
        helper.setText(R.id.item_tv_backnmain_listno,helper.getPosition()+1+"");
        helper.setChecked(R.id.item_cb_backnmain_checkone,item.isCheck);
        helper.setText(R.id.item_tv_backnmain_partname,item.getPartName());
        helper.setText(R.id.item_tv_backnmain_partno,item.getPartNo());
        helper.setText(R.id.item_tv_backnmain_pickbatch,item.getPickBatch()+"批");
        helper.setText(R.id.item_tv_backnmain_pickcount,df.format(item.getPickDetailCount()));
        helper.setText(R.id.item_tv_backnmain_unit,item.getPartUnit());
        helper.setText(R.id.tv_space,item.getPartPlace());
    }
}
