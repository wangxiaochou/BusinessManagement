package com.xzty.cq.tover.businessmanagement.projectmanagement.part.presenter.income;

import com.xzty.cq.tover.businessmanagement.common.data.DataSourse;
import com.xzty.cq.tover.businessmanagement.common.factory.BasePresenter;
import com.xzty.cq.tover.businessmanagement.projectmanagement.part.data.income.ReceiveDetailsHelper;
import com.xzty.cq.tover.businessmanagement.projectmanagement.part.model.deliver.DeliverDetails;

import java.util.List;

/**
 * author zzl
 * Created 2018/5/15.
 * explain 收货详情的P
 */

public class ReceiveDetailsPresenter extends BasePresenter<ReceiveDetailsContract.View> implements ReceiveDetailsContract.Presenter, DataSourse.Callback<List<DeliverDetails>> {
    public ReceiveDetailsPresenter(ReceiveDetailsContract.View view) {
        super(view);
    }

    @Override
    public void getData(String outId) {
        ReceiveDetailsContract.View view = getView();
        ReceiveDetailsHelper.getData(outId, this);
    }

    @Override
    public void onDataLoaded(List<DeliverDetails> list) {
        getView().success(list);
    }

    @Override
    public void onDataNotAvailable(String strRes) {
        getView().showError(strRes);
    }
}
