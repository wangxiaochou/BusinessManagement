package com.xzty.cq.tover.businessmanagement.projectmanagement.part.data.income;

import android.graphics.Bitmap;

import java.util.ArrayList;
import java.util.List;

/**
 * author zzl
 * Created 2018/6/11.
 * explain
 */

public class ReceiveFeedBackPicModel {

    private ArrayList<String> images;
    private List<Bitmap> picsBitmapList;

    public ArrayList<String> getImages() {
        return images;
    }

    public void setImages(ArrayList<String> images) {
        this.images = images;
    }

    public List<Bitmap> getPicsBitmapList() {
        return picsBitmapList;
    }

    public void setPicsBitmapList(List<Bitmap> picsBitmapList) {
        this.picsBitmapList = picsBitmapList;
    }
}
