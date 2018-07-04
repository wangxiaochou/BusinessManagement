package com.xzty.cq.tover.businessmanagement.projectmanagement.part.presenter.back;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.ImageView;

import com.amap.api.maps.model.Marker;
import com.donkingliang.imageselector.utils.ImageSelectorUtils;
import com.xzty.cq.tover.businessmanagement.common.data.DataSourse;
import com.xzty.cq.tover.businessmanagement.common.factory.BasePresenter;
import com.xzty.cq.tover.businessmanagement.common.model.RspLogin;
import com.xzty.cq.tover.businessmanagement.projectmanagement.part.data.back.BackWriteInfoHelper;
import com.xzty.cq.tover.businessmanagement.projectmanagement.part.model.back.BackPart;

import java.util.ArrayList;
import java.util.List;

/**
 * author zzl
 * Created 2018/5/17.
 * explain
 */

public class BackWriteInfoPresenter extends BasePresenter<BackWriteInfoContract.View> implements BackWriteInfoContract.Presenter, DataSourse.Callback<RspLogin> {
    public BackWriteInfoPresenter(BackWriteInfoContract.View view) {
        super(view);
    }

    @Override
    public void subtract(List<BackPart> list, int position) {
        BackWriteInfoContract.View view = getView();
        BackPart model = list.get(position);
        double needCount = model.getPickDetailCount();
        if (needCount > 1) {
            needCount--;
            view.clickBack(needCount, position);
        } else {
            view.showError("数量不够减");
        }
    }

    @Override
    public void add(List<BackPart> list, int position, double[] maxPickCount) {
        BackWriteInfoContract.View view = getView();
        BackPart model = list.get(position);
        double needCount = model.getPickDetailCount();
        double max = maxPickCount[position];
        if (needCount < max) {
            needCount++;
            view.clickBack(needCount, position);
        } else {
            view.showError("不能大于领用量");
        }

    }

    @Override
    public void proPic(ArrayList<String> images, ImageView imageView, Intent data) {
        BackWriteInfoContract.View view = getView();
        if (images != null && !images.isEmpty()) {
            images.clear();
        }
        images = data.getStringArrayListExtra(ImageSelectorUtils.SELECT_RESULT);
        List<Bitmap> bitmapList = new ArrayList<>();
        for (String s : images) {
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
            bitmapList.add(bm);
        }
        view.picBack(images, bitmapList);
    }

    @Override
    public void submit(ArrayList<String> images, List<Bitmap> bitmapList, String pickName, List<Marker> markerList, String pickNote, String pickListString) {
        BackWriteInfoContract.View view = getView();
        if (pickName.equals("")) {
            view.showError("请填写领用人");
        } else if (markerList.size() == 0) {
            view.showError("请在地图上长按标记领用地点");
        } else {
            BackWriteInfoHelper.submit(images, bitmapList, pickName, markerList, pickNote, pickListString, this);
        }
    }

    @Override
    public void onDataLoaded(RspLogin rspLogin) {
        BackWriteInfoContract.View view = getView();
        view.saveSuccess();
    }

    @Override
    public void onDataNotAvailable(String strRes) {
        BackWriteInfoContract.View view = getView();
        view.showError(strRes);
    }
}
