package com.xzty.cq.tover.businessmanagement.projectmanagement.tool.adapter;

import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.widget.EditText;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.xzty.cq.tover.businessmanagement.R;
import com.xzty.cq.tover.businessmanagement.projectmanagement.tool.model.projectmanage.RspToolApplyList;

import java.util.List;

/**
 * author zzl
 * Created 2018/6/4.
 * explain
 */

public class ToolApplyWriteInfoAdapter extends BaseQuickAdapter<RspToolApplyList, BaseViewHolder> {
    private MyTextWatcher textWatcher;

    public ToolApplyWriteInfoAdapter(int layoutResId, @Nullable List<RspToolApplyList> data,MyTextWatcher TextWatcherAdapter) {
        super(layoutResId, data);
        this.textWatcher = TextWatcherAdapter;
    }

    @Override
    protected void convert(final BaseViewHolder helper, final RspToolApplyList item) {
        helper.setChecked(R.id.cb_item, item.isChecked);
        helper.setText(R.id.tv_name, item.getToolName());
        helper.setText(R.id.et_number, item.getToolCount() + "");
        helper.setText(R.id.tv_type, TextUtils.isEmpty(item.getToolModelNumber())?"空":item.getToolModelNumber());
        helper.setText(R.id.tv_position, helper.getPosition() + 1 + "");
        if (TextUtils.isEmpty(item.getToolDepot())) {
            helper.setText(R.id.tv_depot, "新建");
        } else {
            switch (item.getToolDepot()) {
                case "1":
                    helper.setText(R.id.tv_depot, "项管部仓库");
                    break;
                case "2":
                    helper.setText(R.id.tv_depot, "采购部仓库");
                    break;
                default:
                    helper.setText(R.id.tv_depot, "异常,暂无仓库");
                    break;
            }
        }

        switch (item.getToolIsdamage()) {
            case 0:
                helper.setText(R.id.tv_isdamage, "完好");
                break;
            case 1:
                helper.setText(R.id.tv_isdamage, "维修");
                break;
            case 2:
                helper.setText(R.id.tv_isdamage, "损坏");
                break;
            case 3:
                helper.setText(R.id.tv_isdamage, "报废");
                break;
            default:
                helper.setText(R.id.tv_isdamage, item.getToolIsdamage());
                break;
        }
        final EditText et_number = helper.getView(R.id.et_number);
        helper.addOnClickListener(R.id.iv_minus);
        helper.addOnClickListener(R.id.iv_add);
        et_number.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
              /*  String num = et_number.getText().toString().trim();
                if(!TextUtils.isEmpty(num)){
                    item.setToolCount(Double.parseDouble(num));
                }*/
                textWatcher.after(item,et_number,helper.getAdapterPosition());
            }
        });
    }

    public interface MyTextWatcher{
        void after(RspToolApplyList item,EditText et_number,int position);
    }

}
