package com.xzty.cq.tover.businessmanagement.projectmanagement.part.adapter;

import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.EditText;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.xzty.cq.tover.businessmanagement.R;
import com.xzty.cq.tover.businessmanagement.projectmanagement.part.model.pick.RspPickList;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * author zzl
 * Created 2018/5/14.
 * explain 填写采购信息的adapter
 */

public class PartPickWriteInfoAdapter extends BaseQuickAdapter<RspPickList, BaseViewHolder> {
    public PartPickWriteInfoAdapter(int layoutResId, @Nullable List<RspPickList> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, final RspPickList item) {
        helper.setText(R.id.item_tv_distpartchoosefillinfo_partname, item.getPartName());
        helper.setText(R.id.item_tv_distpartchoosefillinfo_partno, item.getPartNo());
        helper.setText(R.id.item_tv_distpartchoosefillinfo_partcount, item.getApplyItemCount() + "");
        helper.setText(R.id.item_tv_distpartchoosefillinfo_unit1, item.getPartUnit());
        helper.setText(R.id.tv_space, item.getPartPlace());
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date date = format.parse(item.getApplyItemExpettime());
            helper.setText(R.id.item_tv_distpartchoosefillinfo_expettime, format.format(date));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        helper.setText(R.id.item_tv_distpartchoosefillinfo_partbatch, item.getApplyBatch() + "批");
        final EditText view = helper.getView(R.id.et_price);

        if (view.getTag() != null && view.getTag() instanceof TextWatcher) {
            view.removeTextChangedListener((TextWatcher) view.getTag());
        }

        TextWatcher watcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String price = view.getText().toString().toString();
                int num = 0;
                if (price.length() >= 4) {
                    String[] p = price.split("");
                    String link = "";
                    //从1开始是为了去除spili剪切多了一个[]，索引为0
                    for (int i = p.length - 1; i > 0; i--) {
                        if (p[i] != "") {
                            if (num != 0 && num % 3 == 0) {
                                link = p[i] + "," + link;
                            } else {
                                link = p[i] + link;
                            }
                            num++;
                        }
                    }
                    item.setUnitPrice(link);
                    Log.e("TAG", "link=" + link);
                } else {
                    item.setUnitPrice(price);
                }
            }
        };
        view.addTextChangedListener(watcher);
        view.setTag(watcher);
    }
}
