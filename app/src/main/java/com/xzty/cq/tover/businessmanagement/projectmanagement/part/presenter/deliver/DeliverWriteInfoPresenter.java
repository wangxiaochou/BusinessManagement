package com.xzty.cq.tover.businessmanagement.projectmanagement.part.presenter.deliver;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.ImageView;

import com.donkingliang.imageselector.utils.ImageSelectorUtils;
import com.xzty.cq.tover.businessmanagement.common.data.DataSourse;
import com.xzty.cq.tover.businessmanagement.common.factory.BasePresenter;
import com.xzty.cq.tover.businessmanagement.projectmanagement.part.data.PicHelper;
import com.xzty.cq.tover.businessmanagement.projectmanagement.part.data.deliver.DeliverWriteInfoHelper;
import com.xzty.cq.tover.businessmanagement.projectmanagement.part.model.deliver.RspBuyInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * author zzl
 * Created 2018/5/15.
 * explain 填写发货信息的Presenter
 */

public class DeliverWriteInfoPresenter extends BasePresenter<DeliverWriteInfoContract.View> implements DeliverWriteInfoContract.Presenter, DataSourse.Callback {
    private String isNet;

    public DeliverWriteInfoPresenter(DeliverWriteInfoContract.View view) {
        super(view);
    }

    @Override
    public void getBuyinfo(String outId) {
        final DeliverWriteInfoContract.View view = getView();
        isNet = "getBuyinfo";
        DeliverWriteInfoHelper.getBuyInfo(outId,this);

    }

    @Override
    public void save(List<String> images, List<Bitmap> picsBitmapList, String outId, String sender, String senderPhone, String carSize, String carno, String sendNumber) {
        isNet = "save";
        PicHelper.proPic(images, picsBitmapList, outId, sender, senderPhone, carSize, carno, sendNumber, this);
    }

    @Override
    public void setGride(ArrayList<String> images, List<Bitmap> picsBitmapList, Intent data, ImageView imv) {
        start();
        DeliverWriteInfoContract.View view = getView();
        if (images != null && !images.isEmpty()) {
            images.clear();
        }
        images = data.getStringArrayListExtra(ImageSelectorUtils.SELECT_RESULT);
        picsBitmapList = new ArrayList<>();
        for (String s : images) {
            BitmapFactory.Options option = new BitmapFactory.Options();
            option.inJustDecodeBounds = true;
            BitmapFactory.decodeFile(s, option);
            int iw = option.outWidth;
            int ih = option.outHeight;

            int vw = imv.getWidth();
            int vh = imv.getHeight();

            int scaleFactor = Math.min(iw / vw, ih / vh);
            option.inJustDecodeBounds = false;
            option.inSampleSize = scaleFactor;
            Bitmap bm = BitmapFactory.decodeFile(s, option);
            picsBitmapList.add(bm);
        }
        view.setGrideAdapter(images, picsBitmapList);
    }

    @Override
    public void onDataNotAvailable(String strRes) {
        DeliverWriteInfoContract.View view = getView();
        view.showError(strRes);
    }

 /*   @Override
    public void onDataLoaded(List<RspLogin> list) {
        DeliverWriteInfoContract.View view = getView();
        view.backSave();
    }*/

    @Override
    public void onDataLoaded(Object o) {
        if (isNet.equals("getBuyinfo")) {
            getView().backBuyInfo((List<RspBuyInfo>) o);
        } else if (isNet.equals("save")) {
            getView().backSave();
        }
    }
}
