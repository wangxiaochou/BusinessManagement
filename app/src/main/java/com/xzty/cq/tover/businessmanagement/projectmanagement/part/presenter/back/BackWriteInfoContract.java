package com.xzty.cq.tover.businessmanagement.projectmanagement.part.presenter.back;

import android.content.Intent;
import android.graphics.Bitmap;
import android.widget.ImageView;

import com.amap.api.maps.model.Marker;
import com.xzty.cq.tover.businessmanagement.common.factory.BaseContract;
import com.xzty.cq.tover.businessmanagement.projectmanagement.part.model.back.BackPart;

import java.util.ArrayList;
import java.util.List;

/**
 * author zzl
 * Created 2018/5/17.
 * explain
 */

public interface BackWriteInfoContract {

    interface View extends BaseContract.View<BackWriteInfoContract.Presenter>{
        void clickBack(double needCount, int position);

        void picBack(ArrayList<String> images, List<Bitmap> bitmapList);

        void saveSuccess();

    }

    interface Presenter extends BaseContract.Presenter{
        void subtract(List<BackPart> list, int position);

        void add(List<BackPart> list, int position,double[] maxPickCount);

        void proPic(ArrayList<String> images, ImageView imageView, Intent data);

        void submit(ArrayList<String> images, List<Bitmap> bitmapList, String pickName, List<Marker> markerList, String pickNote, String pickListString);
    }
}
