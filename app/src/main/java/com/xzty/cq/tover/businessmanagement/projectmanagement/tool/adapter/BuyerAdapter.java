package com.xzty.cq.tover.businessmanagement.projectmanagement.tool.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.xzty.cq.tover.businessmanagement.R;
import com.xzty.cq.tover.businessmanagement.projectmanagement.tool.model.pickdepartment.RspUser;

import java.util.List;

/**
 * author zzl
 * Created 2018/5/24.
 * explain
 */

public class BuyerAdapter extends BaseAdapter{
    private List<RspUser> emplist;
    private Context context;

    public BuyerAdapter(List<RspUser> emplist, Context context) {
        this.emplist = emplist;
        this.context = context;
    }

    @Override
    public int getCount() {
        int ret = 0;
        if (emplist != null) {
            ret = emplist.size();
        }
        return ret;
    }

    @Override
    public Object getItem(int position) {
        return emplist.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v ;
        if (convertView != null) {
            v = convertView;
        }else{
            v = LayoutInflater.from(context).inflate(R.layout.item_applylist_person_spinner_adapter,parent,false);
        }

        BuyerAdapter.ViewHolder holder = (BuyerAdapter.ViewHolder) v.getTag();
        if (holder == null) {
            holder = new BuyerAdapter.ViewHolder();

            holder.item_tv_applylist_person = (TextView) v.findViewById(R.id.item_tv_applylist_person);
        }

        holder.item_tv_applylist_person.setText(emplist.get(position).getEplyName());

        //第四步，设置Tag，用于判断用户当前点击的哪一个列表项的按钮
        holder.item_tv_applylist_person.setTag(position);

        v.setTag(holder);
        return v;
    }

    private static class ViewHolder{
        private TextView item_tv_applylist_person; //序号
    }
}
