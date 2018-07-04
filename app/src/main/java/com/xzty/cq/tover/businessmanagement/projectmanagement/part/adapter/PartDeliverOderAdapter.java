package com.xzty.cq.tover.businessmanagement.projectmanagement.part.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.xzty.cq.tover.businessmanagement.R;
import com.xzty.cq.tover.businessmanagement.projectmanagement.part.model.deliver.DeliverOrder;

import java.util.List;

/**
 * author zzl
 * Created 2018/5/14.
 * explain 发货单的adapter
 */

public class PartDeliverOderAdapter extends BaseQuickAdapter<DeliverOrder,BaseViewHolder>{

    public PartDeliverOderAdapter(int layoutResId, @Nullable List<DeliverOrder> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, DeliverOrder item) {
        helper.setText(R.id.item_buyorder_listno,helper.getPosition()+1+"");
        helper.setText(R.id.item_buyorder_outno,item.getOutNo());
        helper.setText(R.id.item_buyorder_buyername,item.getEplyName());
        helper.setText(R.id.item_buyorder_firm,item.getFirm()==null?"空":item.getFirm());
        helper.setText(R.id.item_buyorder_buyername,item.getEplyName());
        if(item.getIsComfirm()==0){
            helper.setGone(R.id.item_buyorder_delive,true);
            helper.setGone(R.id.item_tv_buyorder_haddelive,false);
        }else{
            helper.setGone(R.id.item_buyorder_delive,false);
            helper.setGone(R.id.item_tv_buyorder_haddelive,true);
        }
        helper.addOnClickListener(R.id.item_buyorder_delive);
    }
}
