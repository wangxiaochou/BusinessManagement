package com.xzty.cq.tover.businessmanagement.projectmanagement.tool.presenter.other.receive;

import com.xzty.cq.tover.businessmanagement.common.data.DataSourse;
import com.xzty.cq.tover.businessmanagement.common.factory.BasePresenter;
import com.xzty.cq.tover.businessmanagement.projectmanagement.tool.data.other.receive.OtherReceiveDetailsHelper;
import com.xzty.cq.tover.businessmanagement.projectmanagement.tool.model.other.RspReceiveDetais;

import java.util.List;

/**
 * author zzl
 * Created 2018/5/26.
 * explain
 */

public class OtherReceiveDetailsPresenter extends BasePresenter<OtherReceiveDetailsContract.View> implements OtherReceiveDetailsContract.Presenter, DataSourse.Callback {
    public OtherReceiveDetailsPresenter(OtherReceiveDetailsContract.View view) {
        super(view);
    }

    @Override
    public void getData(int id) {
        OtherReceiveDetailsContract.View view = getView();
        OtherReceiveDetailsHelper.getData(id,this);
    }

    @Override
    public void onDataLoaded(Object o) {
        getView().success((List<RspReceiveDetais>) o);
    }

    @Override
    public void onDataNotAvailable(String strRes) {
        getView().showError(strRes);
    }
}
