package com.xzty.cq.tover.businessmanagement.projectmanagement.part.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.xzty.cq.tover.businessmanagement.R;
import com.xzty.cq.tover.businessmanagement.projectmanagement.part.activity.pick.PartPickDetailsActivity;
import com.xzty.cq.tover.businessmanagement.projectmanagement.part.model.pick.RspPickList;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * author zzl
 * Created 2018/5/14.
 * explain 构件详情查看的adapter
 */

public class PartPickDetailsAdapter extends BaseQuickAdapter<RspPickList, BaseViewHolder> {
    public static SimpleDateFormat sDateFormat = new SimpleDateFormat("yyyy-MM-dd");
    public static DecimalFormat sDecimalFormat = new DecimalFormat("################.################");

    public PartPickDetailsAdapter(int layoutResId, @Nullable List<RspPickList> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, RspPickList item) {
        helper.setText(R.id.item_distorderdetial_listUnit,
                /*this.sDecimalFormat.format(*/item.getApplyItemCount() + item.getPartUnit());
        Date da = null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//小写的mm表示的是分钟
        try {
            da = sdf.parse(item.getApplyItemExpettime());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        helper.setText(R.id.item_distorderdetial_listExpetTime, sDateFormat.format(da));
        helper.setText(R.id.item_distorderdetial_listPartNo, item.getPartNo());
        helper.setText(R.id.tv_space, helper.getPosition() + 1 + item.getPartPlace());
        helper.setText(R.id.item_distorderdetial_listName, item.getPartName());
        helper.setText(R.id.item_distorderdetial_listBatch, item.getApplyBatch() + "批");
        if (PartPickDetailsActivity.isComfirm != 0) {
            helper.setGone(R.id.tv_price, true);
          helper.setText(R.id.tv_price,item.getUnitPrice());
        } else {
            helper.setGone(R.id.tv_price, false);
        }
    }
}
