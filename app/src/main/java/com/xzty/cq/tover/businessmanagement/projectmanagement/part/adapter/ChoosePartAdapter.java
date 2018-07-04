package com.xzty.cq.tover.businessmanagement.projectmanagement.part.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.xzty.cq.tover.businessmanagement.R;
import com.xzty.cq.tover.businessmanagement.projectmanagement.part.model.apply.RspPartList;

import java.util.List;

/**
 * author zzl
 * Created 2018/5/7.
 * explain 选择构件的adapter
 */

public class ChoosePartAdapter extends BaseQuickAdapter<RspPartList, BaseViewHolder> {
    public ChoosePartAdapter(int layoutResId, @Nullable List<RspPartList> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, RspPartList item) {
        helper.setChecked(R.id.item_cb_choose_part, item.isCheck);
        helper.setText(R.id.tv_serial,helper.getPosition()+1+"");
        helper.setText(R.id.item_tv_choose_partname, item.getPartName());
        helper.setText(R.id.item_tv_choose_partno, item.getPartNo());
        helper.setText(R.id.tv_space,item.getPartPlace());
        helper.setText(R.id.item_tv_choose_partcount, item.getPartCount() + item.getPartUnit());
        helper.setText(R.id.item_tv_choose_partbatch, (item.getPartBatch() == null
                || item.getPartBatch().equals("")) ? "空" : item.getPartBatch());
    }
}
