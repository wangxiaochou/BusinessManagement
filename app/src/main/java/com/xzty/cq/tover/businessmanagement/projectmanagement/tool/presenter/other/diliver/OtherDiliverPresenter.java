package com.xzty.cq.tover.businessmanagement.projectmanagement.tool.presenter.other.diliver;

import com.xzty.cq.tover.businessmanagement.common.data.DataSourse;
import com.xzty.cq.tover.businessmanagement.common.factory.BasePresenter;
import com.xzty.cq.tover.businessmanagement.projectmanagement.tool.data.other.diliver.OtherDiliverHelper;
import com.xzty.cq.tover.businessmanagement.projectmanagement.tool.model.other.RspOtherDiliver;

import java.util.List;

/**
 * author zzl
 * Created 2018/5/25.
 * explain
 */

public class OtherDiliverPresenter extends BasePresenter<OtherDiliverContract.View> implements OtherDiliverContract.Presenter ,DataSourse.Callback{
    public OtherDiliverPresenter(OtherDiliverContract.View view) {
        super(view);
    }

    @Override
    public void getData() {
        OtherDiliverHelper.getData(this);
    }

    @Override
    public void onDataLoaded(Object o) {
        getView().dataSuccess((List<RspOtherDiliver>) o);
    }

    @Override
    public void onDataNotAvailable(String strRes) {
        getView().showError(strRes);
    }
}
