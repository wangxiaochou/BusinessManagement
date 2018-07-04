package com.xzty.cq.tover.businessmanagement.projectmanagement.tool.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.xzty.cq.tover.businessmanagement.R;
import com.xzty.cq.tover.businessmanagement.projectmanagement.tool.model.pickdepartment.RspDiliverOrder;

import java.util.List;

/**
 * author zzl
 * Created 2018/5/25.
 * explain
 */

public class ToolPDDiliverAdapter extends BaseQuickAdapter<RspDiliverOrder, BaseViewHolder> {
    public ToolPDDiliverAdapter(int layoutResId, @Nullable List<RspDiliverOrder> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, RspDiliverOrder item) {
        helper.setChecked(R.id.itmdo_cb, item.isChecke);
        helper.setText(R.id.itmdo_tv_eply, item.getEplyName());
        helper.setText(R.id.itmdo_tv_time, item.getToolApplyTime());
        if(item.getToolDetailApplyToolName()==null){
            helper.setText(R.id.itmdo_tv_name, item.getToolName());
            helper.setText(R.id.tv_type, "型号:"+item.getToolModelNumber());
            helper.setText(R.id.tv_brand, "品牌:" +item.getToolBrand());
            helper.setText(R.id.tv_power,"功率:" + item.getToolPower());
            helper.setText(R.id.tv_count, "数量:" +item.getToolCount() + "");
            switch (item.getToolDepot()) {
                case "1":
                    helper.setText(R.id.tv_depot, "仓库:项管部仓库");
                    break;
                case "2":
                    helper.setText(R.id.tv_depot, "仓库:采购部仓库");
                    break;
                default:
                    helper.setText(R.id.tv_depot, "仓库:"+item.getToolDepot());
                    break;
            }
        }else{
            helper.setText(R.id.itmdo_tv_name, item.getToolDetailApplyToolName());
            helper.setText(R.id.tv_type, "型号:"+item.getToolDetailApplyToolModelNumber());
            helper.setText(R.id.tv_brand,"品牌:" + item.getToolDetailApplyToolBrand());
            helper.setText(R.id.tv_power, "功率:" +item.getToolDetailApplyToolPower());
            helper.setText(R.id.tv_count, "数量:"+item.getToolCount()+"");
            helper.setText(R.id.tv_depot, "仓库");
        }
        switch (item.getToolApplyDetailState()){
            case 0:
                helper.setText(R.id.tv_status,"状态:默认");
                break;
            case 1:
                helper.setText(R.id.tv_status,"状态:不同意");
                break;
            case 2:
                helper.setText(R.id.tv_status,"状态：待发货并移交采购部");
                break;
            case 3:
                helper.setText(R.id.tv_status,"状态:采购部分配");
                break;
            case 4:
                helper.setText(R.id.tv_status,"状态:采购部采购");
                break;
            case 5:
                helper.setText(R.id.tv_status,"状态:待发货");
                break;
            case 6:
                helper.setText(R.id.tv_status,"状态:已发货");
                break;
            case 7:
                helper.setText(R.id.tv_status,"状态:项目收货");
                break;
            default:
                helper.setText(R.id.tv_status,"状态:异常");
                break;
        }
    }
}
