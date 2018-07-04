package com.xzty.cq.tover.businessmanagement.projectmanagement.part.presenter.income;

import android.content.Intent;
import android.graphics.Bitmap;
import android.widget.ImageView;

import com.xzty.cq.tover.businessmanagement.common.data.DataSourse;
import com.xzty.cq.tover.businessmanagement.common.factory.BasePresenter;
import com.xzty.cq.tover.businessmanagement.common.model.RspLogin;
import com.xzty.cq.tover.businessmanagement.projectmanagement.part.data.income.ReceiveFeedBackHelper;
import com.xzty.cq.tover.businessmanagement.projectmanagement.part.data.income.ReceiveFeedBackPicModel;
import com.xzty.cq.tover.businessmanagement.projectmanagement.part.model.deliver.DeliverDetails;

import java.util.ArrayList;
import java.util.List;

/**
 * author zzl
 * Created 2018/5/16.
 * explain 构件反馈的P
 */

public class ReceiveFeedBackPresenter extends BasePresenter<ReceiveFeedBackContract.View> implements ReceiveFeedBackContract.Presenter ,DataSourse.Callback<RspLogin>{
    public ReceiveFeedBackPresenter(ReceiveFeedBackContract.View view) {
        super(view);
    }

    @Override
    public void subtract(DeliverDetails deliverDetails) {
        ReceiveFeedBackContract.View view = getView();
        deliverDetails = ReceiveFeedBackHelper.subtract(deliverDetails);
        if (deliverDetails == null) {
            view.showError("数量不够减了");
        } else {
            view.back(deliverDetails);
        }
    }

    @Override
    public void add(DeliverDetails deliverDetails) {
        ReceiveFeedBackContract.View view = getView();
        deliverDetails = ReceiveFeedBackHelper.add(deliverDetails);
        if (deliverDetails == null) {
            view.showError("有问题的构件数量已经等于到货量了");
        } else {
            view.back(deliverDetails);
        }
    }

    @Override
    public void proPic(ArrayList<String> images, List<Bitmap> picsBitmapList, ImageView imageView, Intent data) {
        ReceiveFeedBackContract.View view = getView();
        ReceiveFeedBackPicModel model = ReceiveFeedBackHelper.proPic(images, picsBitmapList, imageView, data);
        view.picBack(model.getImages(), model.getPicsBitmapList());
    }

    @Override
    public void save( ArrayList<String> images,  List<Bitmap> picsBitmapList, String outDetailId, String applyItemCount, String backReason, String backDutyDept) {
        ReceiveFeedBackContract.View view = getView();
        if (backDutyDept.equals("")) {
            view.showError("没有填写责任部门");
        } else if (images == null || images.isEmpty()) {
            view.showError("没有上传反馈图片");
        } else {
            ReceiveFeedBackHelper.save(images,picsBitmapList,outDetailId,applyItemCount,backReason,backDutyDept,this);
        }
    }

    @Override
    public void onDataLoaded(RspLogin rspLogin) {
        getView().success();
    }

    @Override
    public void onDataNotAvailable(String strRes) {
        getView().showError(strRes);
    }
}
