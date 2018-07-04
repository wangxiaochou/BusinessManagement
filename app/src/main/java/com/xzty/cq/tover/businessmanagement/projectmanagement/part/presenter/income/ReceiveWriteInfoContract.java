package com.xzty.cq.tover.businessmanagement.projectmanagement.part.presenter.income;

import android.content.Intent;
import android.graphics.Bitmap;
import android.widget.ImageView;

import com.amap.api.maps.model.Marker;
import com.xzty.cq.tover.businessmanagement.common.factory.BaseContract;
import com.xzty.cq.tover.businessmanagement.projectmanagement.part.model.deliver.DeliverDetails;
import com.xzty.cq.tover.businessmanagement.projectmanagement.part.model.deliver.DeliverOrder;

import java.util.ArrayList;
import java.util.List;

/**
 * author zzl
 * Created 2018/5/16.
 * explain 收货信息填写的C
 */

public interface ReceiveWriteInfoContract {

    interface View extends BaseContract.View<ReceiveWriteInfoContract.Presenter> {
        void subtracBack(List<DeliverDetails> list);

        void addBack(List<DeliverDetails> list);

        void supplyBack(ArrayList<String> supplyImages, List<Bitmap> supplyBitmapList);

        void partBack(ArrayList<String> partImages, List<Bitmap> partBitmapList);

        void saveBack();

        void showDialog();
    }

    interface Presenter extends BaseContract.Presenter {
        void subtract(List<DeliverDetails> list, int position);

        void add(List<DeliverDetails> list, int position);

        void supplyPic(ArrayList<String> supplyImages, List<Bitmap> supplyBitmapList, ImageView imageView, Intent data);

        void partPick(ArrayList<String> partImages, List<Bitmap> partBitmapList, ImageView imageView, Intent data);

        void cargeSava(List<Marker> markerList,ArrayList<String> supplyImages,ArrayList<String> partImages,List<DeliverDetails> deliverDetailList,DeliverOrder deliverOrder,String collectNote);
    }

}
