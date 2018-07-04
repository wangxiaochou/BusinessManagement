package com.xzty.cq.tover.businessmanagement.projectmanagement.part.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.xzty.cq.tover.businessmanagement.R;
import com.xzty.cq.tover.businessmanagement.projectmanagement.part.model.pick.RspPickList;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * author zzl
 * Created 2018/5/12.
 * explain 选择采购物品的adapter
 */

public class PartPickChooseAdapter extends BaseQuickAdapter<RspPickList,BaseViewHolder>{
    private DecimalFormat df = new DecimalFormat("#######.#######");
    public PartPickChooseAdapter(int layoutResId, @Nullable List<RspPickList> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, RspPickList item) {
        helper.setChecked(R.id.item_cb_distpartchoose_part,item.isCheck);
        helper.setText(R.id.item_tv_distpartchoose_partname,item.getPartName());
        helper.setText(R.id.item_tv_distpartchoose_partno,item.getPartNo());
        helper.setText(R.id.item_tv_distpartchoose_partcount,df.format(item.getApplyItemCount()));
        helper.setText(R.id.item_tv_distpartchoose_unit1,item.getPartUnit());
        helper.setText(R.id.tv_space,item.getPartPlace());
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date date = format.parse(item.getApplyItemExpettime());

            helper.setText(R.id.item_tv_distpartchoose_expettime,format.format(date));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        helper.setText(R.id.item_tv_distpartchoose_partbatch,item.getApplyBatch()+"批");
    }
}
