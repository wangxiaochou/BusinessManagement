package com.xzty.cq.tover.businessmanagement.projectmanagement.part.data.use;

import android.graphics.Bitmap;

import java.util.ArrayList;
import java.util.List;

/**
 * author zzl
 * Created 2018/6/12.
 * explain
 */

public class UseWriteInfoImage {

    private ArrayList<String> images;
    private List<Bitmap> bitmapList;

    public ArrayList<String> getImages() {
        return images;
    }

    public void setImages(ArrayList<String> images) {
        this.images = images;
    }

    public List<Bitmap> getBitmapList() {
        return bitmapList;
    }

    public void setBitmapList(List<Bitmap> bitmapList) {
        this.bitmapList = bitmapList;
    }
}
