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
 * explain 选择实际到场的adapter
 */

public class PartReceiveChooseAdapter extends BaseQuickAdapter<DeliverDetails,BaseViewHolder>{
    private DecimalFormat df = new DecimalFormat("#######.#######");
    public PartReceiveChooseAdapter(int layoutResId, @Nullable List<DeliverDetails> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, DeliverDetails item) {
        helper.setText(R.id.tv_plase,item.getPartPlace());
        helper.setChecked(R.id.item_cb_collectinfo_checkone,item.isCheck);
        helper.setText(R.id.item_tv_collectinfo_listno,helper.getPosition()+1+"");
        helper.setText(R.id.item_collectinfo_partname,item.getPartName());
        helper.setText(R.id.item_tv_collectinfo_partno,item.getPartNo());
        helper.setText(R.id.item_tv_collectinfo_needcount,df.format(item.getResidualQuantity()));
        helper.setText(R.id.item_tv_collectinfo_unit,item.getPartUnit());
        helper.setText(R.id.item_tv_collectinfo_batch,(item.getOutBatch() == null)?"空":item.getOutBatch()+"批");
        helper.setText(R.id.item_tv_collectinfo_arrivaltime, DateUtil.dateToString(item.getApplyItemExpettime()));
    }
}
