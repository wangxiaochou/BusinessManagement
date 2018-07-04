package com.xzty.cq.tover.businessmanagement.projectmanagement.tool.presenter.pickdepartment.distribute;

import com.xzty.cq.tover.businessmanagement.common.data.DataSourse;
import com.xzty.cq.tover.businessmanagement.common.factory.BasePresenter;
import com.xzty.cq.tover.businessmanagement.projectmanagement.tool.data.pickdepartment.distribute.DistributeOrderListHelper;
import com.xzty.cq.tover.businessmanagement.projectmanagement.tool.model.pickdepartment.RspDistributeOrderList;

import java.util.List;

/**
 * author zzl
 * Created 2018/5/25.
 * explain
 */

public class DistributeOrderListPresenter extends BasePresenter<DistributeOrderListContract.View> implements DistributeOrderListContract.Presenter, DataSourse.Callback {
    public DistributeOrderListPresenter(DistributeOrderListContract.View view) {
        super(view);
    }

    @Override
    public void getData() {
        DistributeOrderListHelper.getData(this);
    }

    @Override
    public void onDataLoaded(Object o) {
        getView().success((List<RspDistributeOrderList>) o);
    }

    @Override
    public void onDataNotAvailable(String strRes) {
        getView().showError(strRes);
    }
}
