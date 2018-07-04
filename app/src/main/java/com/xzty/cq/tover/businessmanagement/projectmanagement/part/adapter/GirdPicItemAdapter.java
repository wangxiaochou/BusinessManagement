package com.xzty.cq.tover.businessmanagement.projectmanagement.part.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.xzty.cq.tover.businessmanagement.R;

import java.util.ArrayList;
import java.util.List;



/**
 * Created by miao on 2017/12/18.
 */

public class GirdPicItemAdapter extends BaseAdapter {

    private List<Bitmap> picsBitmapList = new ArrayList<>();
    private Context context;

    public GirdPicItemAdapter(List<Bitmap> picsBitmapList, Context context) {
        this.picsBitmapList = picsBitmapList;
        this.context = context;
    }

    @Override
    public int getCount() {
        int ret = 0;
        if(picsBitmapList != null){
            ret = picsBitmapList.size();
        }
        return ret;
    }

    @Override
    public Object getItem(int position) {
        return picsBitmapList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v;
        if(convertView != null){
            v = convertView;
        }else {
            v = LayoutInflater.from(context).inflate(R.layout.girditem_outfillinfo_adapter,parent,false);
        }

        ViewHolder holder = (ViewHolder) v.getTag();

        if(holder == null){
            holder = new ViewHolder();
            holder.girditem_outfillinfo_pic = v.findViewById(R.id.girditem_outfillinfo_pic);

        }

        holder.girditem_outfillinfo_pic.setImageBitmap(picsBitmapList.get(position));

        v.setTag(holder);
        return v;
    }

    private static class ViewHolder{
        private ImageView girditem_outfillinfo_pic;
    }
}
