package com.xzty.cq.tover.businessmanagement.projectmanagement.part.presenter.use;

import android.content.Intent;
import android.graphics.Bitmap;
import android.widget.ImageView;

import com.amap.api.maps.model.Marker;
import com.xzty.cq.tover.businessmanagement.common.factory.BaseContract;
import com.xzty.cq.tover.businessmanagement.projectmanagement.part.model.use.AllModel;

import java.util.ArrayList;
import java.util.List;

/**
 * author zzl
 * Created 2018/5/17.
 * explain
 */

public interface UseWriteInfoContract {

    interface View extends BaseContract.View<UseWriteInfoContract.Presenter> {
        void subtracBack(List<AllModel> list);

        void picBack(ArrayList<String> images, List<Bitmap> bitmapList);

        void saveSuccess();
    }

    interface Presenter extends BaseContract.Presenter {
        void subtract(List<AllModel> list, int position);

        void add(List<AllModel> list, int position);

        void proPic(ArrayList<String> images, ImageView imageView, Intent data);

        void submit(ArrayList<String> images,List<Bitmap> bitmapList,String pickName, List<Marker> markerList, String pickNote, String pickListString);
    }
}
