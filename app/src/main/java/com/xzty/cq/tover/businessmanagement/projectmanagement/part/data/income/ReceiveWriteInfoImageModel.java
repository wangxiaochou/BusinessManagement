package com.xzty.cq.tover.businessmanagement.projectmanagement.part.data.income;

import android.graphics.Bitmap;

import java.util.ArrayList;
import java.util.List;

/**
 * author zzl
 * Created 2018/6/12.
 * explain
 */

public class ReceiveWriteInfoImageModel {
    private ArrayList<String> supplyImages;
    private List<Bitmap> supplyBitmapList;

    public ArrayList<String> getSupplyImages() {
        return supplyImages;
    }

    public void setSupplyImages(ArrayList<String> supplyImages) {
        this.supplyImages = supplyImages;
    }

    public List<Bitmap> getSupplyBitmapList() {
        return supplyBitmapList;
    }

    public void setSupplyBitmapList(List<Bitmap> supplyBitmapList) {
        this.supplyBitmapList = supplyBitmapList;
    }
}
