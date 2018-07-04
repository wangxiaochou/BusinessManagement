package com.xzty.cq.tover.businessmanagement.projectmanagement.tool.adapter;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.text.TextUtils;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.xzty.cq.tover.businessmanagement.R;
import com.xzty.cq.tover.businessmanagement.projectmanagement.tool.model.projectmanage.RspAffirmDetails;

import java.util.List;

/**
 * author zzl
 * Created 2018/5/23.
 * explain
 */

public class ToolApplyDetailsAdapter extends BaseQuickAdapter<RspAffirmDetails, BaseViewHolder> {
    public ToolApplyDetailsAdapter(int layoutResId, @Nullable List<RspAffirmDetails> data) {
        super(layoutResId, data);
    }

    @SuppressLint("ResourceAsColor")
    @Override
    protected void convert(BaseViewHolder helper, RspAffirmDetails item) {

        String toolDetailApplyToolName = item.getToolDetailApplyToolName();
        helper.setText(R.id.tv_count,"数量: "+ (item.getToolDetailApplyCount() + ""));
        if (TextUtils.isEmpty(toolDetailApplyToolName)) {
            helper.setText(R.id.tv_model, "型号: "+(item.getToolModelNumber() == "" ? "空" : item.getToolModelNumber()));
            helper.setText(R.id.tv_name,"名称: "+ (item.getToolName() == "" ? "空" : item.getToolName()));
            helper.setText(R.id.tv_power, "功率: "+(item.getToolPower()==""?"空":item.getToolPower()));
            helper.setText(R.id.tv_number, "编号: "+(item.getToolNumber() + ""));
            helper.setText(R.id.tv_brand,"品牌: "+ (item.getToolBrand()));
            if(!TextUtils.isEmpty(item.getToolDepot())){
                switch (item.getToolDepot()){
                    case "1":
                        helper.setText(R.id.tv_depot, "仓库: 项管部仓库");
                        break;
                    case "2":
                        helper.setText(R.id.tv_depot, "仓库: 采购部仓库");
                        break;
                    default:
                        helper.setText(R.id.tv_depot, "仓库: "+item.getToolDepot());
                        break;

                }
            }
            helper.setText(R.id.tv_department,"部门: "+ (item.getToolDepartment() == "" ? "空" : item.getToolDepartment()));
        } else {
            helper.setText(R.id.tv_model,"型号: "+ (item.getToolDetailApplyToolModelNumber() == "" ? "空" : item.getToolDetailApplyToolModelNumber()));
            helper.setText(R.id.tv_name,"名称: "+ (item.getToolDetailApplyToolName() == "" ? "空" : item.getToolDetailApplyToolName()));
                 helper.setText(R.id.tv_power,"功率: "+ (item.getToolDetailApplyToolPower()==""?"空":item.getToolDetailApplyToolPower()));
            helper.setText(R.id.tv_number,"编号: 新建项" );
            helper.setText(R.id.tv_department,"部门: "+ (item.getToolDepartment() == "" ? "空" : item.getToolDepartment()));
            helper.setText(R.id.tv_brand,"品牌: "+ (item.getToolDetailApplyToolBrand()));
        }

        if (item.isChecked) {
            helper.setChecked(R.id.itmdacd_cb, true);
        } else {
            helper.setChecked(R.id.itmdacd_cb, false);
        }

        if(item.isRefuse){
               helper.setBackgroundColor(R.id.itmdacd_ll_info,Color.RED) ;
        }else{
            helper.setBackgroundColor(R.id.itmdacd_ll_info, Color.TRANSPARENT) ;
        }

    }
}
