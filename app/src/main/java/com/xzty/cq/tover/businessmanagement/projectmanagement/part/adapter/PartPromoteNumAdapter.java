package com.xzty.cq.tover.businessmanagement.projectmanagement.part.adapter;

import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.xzty.cq.tover.businessmanagement.R;
import com.xzty.cq.tover.businessmanagement.projectmanagement.part.model.apply.RspPartList;

import java.util.List;

/**
 * author zzl
 * Created 2018/6/6.
 * explain
 */

public class PartPromoteNumAdapter extends BaseQuickAdapter<RspPartList, BaseViewHolder> {
    private TextWatcherListener myTextWatcher;

    public PartPromoteNumAdapter(int layoutResId, @Nullable List<RspPartList> data, TextWatcherListener myTextWatcher) {
        super(layoutResId, data);
        this.myTextWatcher = myTextWatcher;
    }

    @Override
    protected void convert(final BaseViewHolder helper, final RspPartList item) {
        helper.setText(R.id.tv_no, helper.getPosition() + 1 + "");
        helper.setChecked(R.id.check_choose, item.isCheck);
        helper.setText(R.id.tv_space,item.getPartPlace());
        helper.setText(R.id.tv_name, item.getPartName());
        helper.setText(R.id.tv_num, item.getPartNo());
        helper.setText(R.id.tv_count, item.getPartCount() + "");
        if (item.getApplyItemExpettime() != null) {
            helper.setGone(R.id.chooseset_itemdate, false);
            helper.setGone(R.id.tv_time, true);
        } else {
            helper.setGone(R.id.chooseset_itemdate, true);
            helper.setGone(R.id.tv_time, false);
        }
        helper.addOnClickListener(R.id.item_iv_chooseset_minus);
        helper.addOnClickListener(R.id.item_iv_chooseset_add);
        helper.addOnClickListener(R.id.chooseset_itemdate);
        final EditText editText = helper.getView(R.id.et_needcount);
        //解决TextWatcher监听复用数据出现混乱的问题
        if(editText.getTag()!=null && editText.getTag() instanceof TextWatcher){
            editText.removeTextChangedListener((TextWatcher) editText.getTag());
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
                myTextWatcher.after(item,helper.getAdapterPosition(),editText);
            }
        };
        editText.addTextChangedListener(watcher);
        editText.setTag(watcher );
    }

    public interface TextWatcherListener {
        void after(RspPartList rspPartList, int position, EditText editText);
    }

}
