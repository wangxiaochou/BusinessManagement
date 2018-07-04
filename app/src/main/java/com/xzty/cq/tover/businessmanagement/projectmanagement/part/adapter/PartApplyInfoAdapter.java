package com.xzty.cq.tover.businessmanagement.projectmanagement.part.adapter;

import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.xzty.cq.tover.businessmanagement.R;
import com.xzty.cq.tover.businessmanagement.projectmanagement.part.model.apply.RspPartList;
import com.xzty.cq.tover.businessmanagement.projectmanagement.utils.DateUtil;

import java.util.List;

/**
 * author zzl
 * Created 2018/5/9.
 * explain 申请填写列表
 */

public class PartApplyInfoAdapter extends BaseQuickAdapter<RspPartList, BaseViewHolder> {
    private MyTextWatcher myTextWatcher;
    public PartApplyInfoAdapter(int layoutResId, @Nullable List<RspPartList> data,MyTextWatcher myTextWatcher) {
        super(layoutResId, data);
        this.myTextWatcher = myTextWatcher;
    }

    @Override
    protected void convert(final BaseViewHolder helper, final RspPartList item) {
        helper.setChecked(R.id.item_cb_chooseset_part, item.isCheck).addOnClickListener(R.id.item_cb_chooseset_part);
        helper.setText(R.id.item_tv_chooseset_listno, helper.getPosition() + 1 + "");
        helper.setText(R.id.item_tv_chooseset_partname, item.getPartName());
        helper.setText(R.id.item_tv_chooseset_partno, item.getPartNo());
        //已被隐藏
       // helper.setText(R.id.item_tv_chooseset_partcount, item.getPartCount() + item.getPartUnit());
        helper.setText(R.id.item_tv_chooseset_itemdatestring, DateUtil.dateToString(item.getApplyItemExpettime() == null ? "空" : item.getApplyItemExpettime()));
        if (item.getApplyItemExpettime() != null) {
            helper.setGone(R.id.item_iv_chooseset_itemdate, false);
            helper.setGone(R.id.item_tv_chooseset_itemdatestring, true);
        } else {
            helper.setGone(R.id.item_iv_chooseset_itemdate, true);
            helper.setGone(R.id.item_tv_chooseset_itemdatestring, false);
        }
        helper.addOnClickListener(R.id.item_iv_chooseset_minus);
        helper.addOnClickListener(R.id.item_iv_chooseset_add);
        helper.addOnClickListener(R.id.item_iv_chooseset_itemdate);
        final EditText et_needcount = helper.getView(R.id.et_needcount);
        if(et_needcount.getTag()!=null && et_needcount.getTag() instanceof TextWatcher){
            et_needcount.removeTextChangedListener((TextWatcher) et_needcount.getTag());
        }
        helper.setText(R.id.et_needcount, item.getNeedCount() + "");
        TextWatcher watcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                myTextWatcher.after(item,helper.getAdapterPosition(),et_needcount);
            }
        };
        et_needcount.addTextChangedListener(watcher);
        et_needcount.setTag(watcher);
    }

    public interface MyTextWatcher{
        void after(RspPartList rspPartList,int position,EditText editText);
    }
}
