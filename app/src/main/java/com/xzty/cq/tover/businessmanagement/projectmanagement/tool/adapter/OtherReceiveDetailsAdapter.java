package com.xzty.cq.tover.businessmanagement.projectmanagement.tool.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.xzty.cq.tover.businessmanagement.R;
import com.xzty.cq.tover.businessmanagement.projectmanagement.tool.model.other.RspReceiveDetais;

import java.util.List;

/**
 * author zzl
 * Created 2018/5/26.
 * explain
 */

public class OtherReceiveDetailsAdapter  extends BaseQuickAdapter<RspReceiveDetais,BaseViewHolder>{
    public OtherReceiveDetailsAdapter(int layoutResId, @Nullable List<RspReceiveDetais> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, RspReceiveDetais item) {
        helper.setText(R.id.tv_number,"编号: "+item.getToolNumber());
        helper.setText(R.id.tv_name,"名称: "+item.getToolName());
        helper.setText(R.id.tv_model,"型号: "+item.getToolModelNumber());
        helper.setText(R.id.tv_brand,"品牌: "+item.getToolBrand());
        helper.setText(R.id.tv_power,"功率: "+item.getToolPower());
        helper.setText(R.id.tv_count,"数量: "+item.getToolCount());
        helper.setText(R.id.tv_department,"部门: "+item.getToolDepartment());
    }
}
