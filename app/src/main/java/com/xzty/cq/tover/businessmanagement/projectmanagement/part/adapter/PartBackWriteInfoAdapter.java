package com.xzty.cq.tover.businessmanagement.projectmanagement.part.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.xzty.cq.tover.businessmanagement.R;
import com.xzty.cq.tover.businessmanagement.projectmanagement.part.model.back.BackPart;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * author zzl
 * Created 2018/5/17.
 * explain
 */

public class PartBackWriteInfoAdapter extends BaseQuickAdapter<BackPart,BaseViewHolder> {
    private DecimalFormat df = new DecimalFormat("#######.#######");
    public PartBackWriteInfoAdapter(int layoutResId, @Nullable List<BackPart> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, BackPart item) {
        helper.setText(R.id.tv_space,helper.getPosition()+1+item.getPartPlace());
        helper.setText(R.id.item_tv_backpartcountset_partname,item.getPartName());
        helper.setText(R.id.item_tv_backpartcountset_partno,item.getPartNo());
        helper.setText(R.id.item_tv_backpartcountset_pickbatch,item.getPickBatch()+"批");
        helper.setText(R.id.item_et_backpartcountset_needcount,(item.getPickDetailCount()==null||item.getPickDetailCount().equals(""))?"0":df.format(item.getPickDetailCount()));
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date date = format.parse(item.getPickTime());
            helper.setText(R.id.item_tv_backpartcountset_picktime,format.format(date));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        helper.setText(R.id.item_tv_backpartcountset_unit,item.getPartUnit());
        helper.addOnClickListener(R.id.item_iv_backpartcountset_add);
        helper.addOnClickListener(R.id.item_iv_backpartcountset_minus);
    }
}
