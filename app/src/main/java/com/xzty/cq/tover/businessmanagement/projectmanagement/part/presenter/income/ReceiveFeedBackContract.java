package com.xzty.cq.tover.businessmanagement.projectmanagement.part.presenter.income;

import android.content.Intent;
import android.graphics.Bitmap;
import android.widget.ImageView;

import com.xzty.cq.tover.businessmanagement.common.factory.BaseContract;
import com.xzty.cq.tover.businessmanagement.projectmanagement.part.model.deliver.DeliverDetails;

import java.util.ArrayList;
import java.util.List;

/**
 * author zzl
 * Created 2018/5/16.
 * explain 构件反馈
 */

public interface ReceiveFeedBackContract {

    interface View extends BaseContract.View<ReceiveFeedBackContract.Presenter> {
        void back(DeliverDetails deliverDetails);

        void picBack(ArrayList<String> images, List<Bitmap>picsBitmapList);

        void success();
    }

    interface Presenter extends BaseContract.Presenter {
        void subtract(DeliverDetails deliverDetails);

        void add(DeliverDetails deliverDetails);

        void proPic(ArrayList<String> images, List<Bitmap>picsBitmapList, ImageView imageView, Intent data);

        void save(ArrayList<String> images,List<Bitmap> picsBitmapList,String outDetailId,String applyItemCount,String backReason,String backDutyDept);
    }
}
