package com.xzty.cq.tover.businessmanagement.projectmanagement.part.presenter.apply;

import com.xzty.cq.tover.businessmanagement.common.data.DataSourse;
import com.xzty.cq.tover.businessmanagement.common.factory.BasePresenter;
import com.xzty.cq.tover.businessmanagement.projectmanagement.part.data.apply.PartApplyHelper;
import com.xzty.cq.tover.businessmanagement.projectmanagement.part.model.apply.RspPartList;

import java.util.List;

/**
 * author zzl
 * Created 2018/5/10.
 * explain
 */

public class OrderPartPresenter extends BasePresenter<OrderPartContract.View>
        implements OrderPartContract.presenter,DataSourse.Callback<List<RspPartList>>{

    public OrderPartPresenter(OrderPartContract.View view) {
        super(view);
    }

    @Override
    public void getItmeDetails(String projectId, String partId) {
        PartApplyHelper.orderPart(projectId,partId,this);
    }

    @Override
    public void onDataLoaded(List<RspPartList> lists) {
        OrderPartContract.View view = getView();
        view.susccess(lists);
    }

    @Override
    public void onDataNotAvailable(String strRes) {
        OrderPartContract.View view = getView();
        view.showError(strRes);
    }
}
