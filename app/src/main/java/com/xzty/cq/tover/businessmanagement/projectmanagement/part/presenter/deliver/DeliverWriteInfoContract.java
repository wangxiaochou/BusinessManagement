package com.xzty.cq.tover.businessmanagement.projectmanagement.part.presenter.deliver;

import android.content.Intent;
import android.graphics.Bitmap;
import android.widget.ImageView;

import com.xzty.cq.tover.businessmanagement.common.factory.BaseContract;
import com.xzty.cq.tover.businessmanagement.projectmanagement.part.model.deliver.RspBuyInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * author zzl
 * Created 2018/5/15.
 * explain 填写发货信息的Contract
 */

public interface DeliverWriteInfoContract {

    interface View extends BaseContract.View<DeliverWriteInfoContract.Presenter> {
        void backBuyInfo(List<RspBuyInfo> list);

        void backSave();

        void setGrideAdapter(ArrayList<String> images, List<Bitmap> picsBitmapList);
    }

    interface Presenter extends BaseContract.Presenter {
        void getBuyinfo(String outId);

        //保存按钮
        void save(List<String> images, List<Bitmap> picsBitmapList, String outId, String sender, String senderPhone, String carSize, String carno, String sendNumber);

        //设置GrideAdapter
        void setGride(ArrayList<String> images, List<Bitmap> picsBitmapList, Intent data, ImageView imageView);
    }
}
