package com.xzty.cq.tover.businessmanagement.projectmanagement.part.presenter.income;

import com.xzty.cq.tover.businessmanagement.common.data.DataSourse;
import com.xzty.cq.tover.businessmanagement.common.factory.BasePresenter;
import com.xzty.cq.tover.businessmanagement.projectmanagement.part.data.income.ReceiveOrderHelper;
import com.xzty.cq.tover.businessmanagement.projectmanagement.part.data.income.ReceiveOrderModel;

/**
 * author zzl
 * Created 2018/5/15.
 * explain 收货单的Presenter
 */

public class ReceiveOrderPresenter extends BasePresenter<ReceiveOrderContract.View> implements ReceiveOrderContract.Presenter, DataSourse.Callback<ReceiveOrderModel> {

    public ReceiveOrderPresenter(ReceiveOrderContract.View view) {
        super(view);
    }

    @Override
    public void search(String applyUserId, String time, String applyOrderState) {
        ReceiveOrderHelper.search(applyUserId,time,applyOrderState,this);
    }

    @Override
    public void onDataLoaded(ReceiveOrderModel receiveOrderModel) {
        getView().seacrchCallBack(receiveOrderModel.getOrderList(), receiveOrderModel.getEmps());
    }

    @Override
    public void onDataNotAvailable(String strRes) {
        getView().showError(strRes);
    }
}
