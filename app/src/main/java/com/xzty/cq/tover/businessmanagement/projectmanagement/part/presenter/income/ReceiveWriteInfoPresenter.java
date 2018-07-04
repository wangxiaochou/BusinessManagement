package com.xzty.cq.tover.businessmanagement.projectmanagement.part.presenter.income;

import android.content.Intent;
import android.graphics.Bitmap;
import android.widget.ImageView;

import com.amap.api.maps.model.Marker;
import com.xzty.cq.tover.businessmanagement.common.data.DataSourse;
import com.xzty.cq.tover.businessmanagement.common.factory.BasePresenter;
import com.xzty.cq.tover.businessmanagement.common.model.RspLogin;
import com.xzty.cq.tover.businessmanagement.projectmanagement.part.data.income.ReceiveWriteInfoHelper;
import com.xzty.cq.tover.businessmanagement.projectmanagement.part.data.income.ReceiveWriteInfoImageModel;
import com.xzty.cq.tover.businessmanagement.projectmanagement.part.model.deliver.DeliverDetails;
import com.xzty.cq.tover.businessmanagement.projectmanagement.part.model.deliver.DeliverOrder;

import java.util.ArrayList;
import java.util.List;

/**
 * author zzl
 * Created 2018/5/16.
 * explain 收货信息填写的p
 */

public class ReceiveWriteInfoPresenter extends BasePresenter<ReceiveWriteInfoContract.View> implements ReceiveWriteInfoContract.Presenter, DataSourse.Callback<RspLogin> {
    public ReceiveWriteInfoPresenter(ReceiveWriteInfoContract.View view) {
        super(view);
    }

    @Override
    public void subtract(List<DeliverDetails> list, int position) {
        ReceiveWriteInfoContract.View view = getView();
        list = ReceiveWriteInfoHelper.subtract(list, position);
        if (list == null) {
            view.showError("数量不够减了");
        } else {
            view.subtracBack(list);
        }
    }

    @Override
    public void add(List<DeliverDetails> list, int position) {
        ReceiveWriteInfoContract.View view = getView();
        view.addBack(ReceiveWriteInfoHelper.add(list, position));

    }

    @Override
    public void supplyPic(ArrayList<String> supplyImages, List<Bitmap> supplyBitmapList, ImageView imageView, Intent data) {
        ReceiveWriteInfoContract.View view = getView();
        ReceiveWriteInfoImageModel model = new ReceiveWriteInfoImageModel();
        model = ReceiveWriteInfoHelper.supplyPic(supplyImages, supplyBitmapList, imageView, data);
        view.supplyBack(model.getSupplyImages(), model.getSupplyBitmapList());
    }

    @Override
    public void partPick(ArrayList<String> partImages, List<Bitmap> partBitmapList, ImageView imageView, Intent data) {
        ReceiveWriteInfoContract.View view = getView();
    /*    if (partImages != null && !partImages.isEmpty()) {
            partImages.clear();
        }
        partImages = data.getStringArrayListExtra(ImageSelectorUtils.SELECT_RESULT);
        partBitmapList = new ArrayList<>();
        for (String s : partImages) {
            BitmapFactory.Options option = new BitmapFactory.Options();
            option.inJustDecodeBounds = true;
            BitmapFactory.decodeFile(s, option);
            int iw = option.outWidth;
            int ih = option.outHeight;
            int vw = imageView.getWidth();
            int vh = imageView.getHeight();
            int scaleFactor = Math.min(iw / vw, ih / vh);
            option.inJustDecodeBounds = false;
            option.inSampleSize = scaleFactor;
            Bitmap bm = BitmapFactory.decodeFile(s, option);
            partBitmapList.add(bm);
        }
        view.partBack(partImages, partBitmapList);*/
        ReceiveWriteInfoImageModel model = new ReceiveWriteInfoImageModel();
        model = ReceiveWriteInfoHelper.supplyPic(partImages, partBitmapList, imageView, data);
        view.partBack(model.getSupplyImages(), model.getSupplyBitmapList());
    }

    @Override
    public void cargeSava(List<Marker> markerList, ArrayList<String> supplyImages, ArrayList<String> partImages, List<DeliverDetails> deliverDetailList, DeliverOrder deliverOrder, String collectNote) {
        start();
        final ReceiveWriteInfoContract.View view = getView();
        if (markerList.size() == 0) {
            view.showError("您没有在地图上标记收货地点");
        } else if (supplyImages.size() == 0) {
            view.showError("您没有上传供货商单据照片");
        } else {
            view.showDialog();
            ReceiveWriteInfoHelper.cargeSava(markerList,supplyImages,partImages,deliverDetailList,deliverOrder,collectNote,this);
        }
    }

    @Override
    public void onDataLoaded(RspLogin rspLogin) {
        getView().saveBack();
    }

    @Override
    public void onDataNotAvailable(String strRes) {
        getView().showError(strRes);
    }
}