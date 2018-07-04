package com.xzty.cq.tover.businessmanagement.projectmanagement.part.presenter.use;

import android.content.Intent;
import android.graphics.Bitmap;
import android.widget.ImageView;

import com.amap.api.maps.model.Marker;
import com.xzty.cq.tover.businessmanagement.common.data.DataSourse;
import com.xzty.cq.tover.businessmanagement.common.factory.BasePresenter;
import com.xzty.cq.tover.businessmanagement.common.model.RspLogin;
import com.xzty.cq.tover.businessmanagement.projectmanagement.part.data.use.UseWriteInfoHelper;
import com.xzty.cq.tover.businessmanagement.projectmanagement.part.data.use.UseWriteInfoImage;
import com.xzty.cq.tover.businessmanagement.projectmanagement.part.model.use.AllModel;

import java.util.ArrayList;
import java.util.List;

/**
 * author zzl
 * Created 2018/5/17.
 * explain
 */

public class UseWriteInfoPresenter extends BasePresenter<UseWriteInfoContract.View> implements UseWriteInfoContract.Presenter ,DataSourse.Callback<RspLogin>{
    public UseWriteInfoPresenter(UseWriteInfoContract.View view) {
        super(view);
    }

    @Override
    public void subtract(List<AllModel> list, int position) {
        UseWriteInfoContract.View view = getView();
        list = UseWriteInfoHelper.subtract(list, position);
        if (list == null) {
            view.showError("不能再减了");
        } else {
            view.subtracBack(list);
        }
    }

    @Override
    public void add(List<AllModel> list, int position) {
        UseWriteInfoContract.View view = getView();
        view.subtracBack(UseWriteInfoHelper.add(list, position));
    }

    @Override
    public void proPic(ArrayList<String> images, ImageView imageView, Intent data) {
        UseWriteInfoContract.View view = getView();
        UseWriteInfoImage image = UseWriteInfoHelper.proPic(images,imageView,data);
        view.picBack(image.getImages(), image.getBitmapList());
    }

    @Override
    public void submit(final ArrayList<String> images, final List<Bitmap> bitmapList, String pickName, List<Marker> markerList, String pickNote, String pickListString) {
        final UseWriteInfoContract.View view = getView();
        if (pickName.equals("")) {
            view.showError("请填写领用人");
        } else if (markerList.size() == 0) {
            view.showError("请在地图上长按标记领用地点");
        } else {
            start();
            UseWriteInfoHelper.submit(images,bitmapList,pickName,markerList,pickNote,pickListString,this);
        }
    }

    @Override
    public void onDataLoaded(RspLogin rspLogin) {
        getView().saveSuccess();
    }

    @Override
    public void onDataNotAvailable(String strRes) {
        getView().showError(strRes);
    }
}
