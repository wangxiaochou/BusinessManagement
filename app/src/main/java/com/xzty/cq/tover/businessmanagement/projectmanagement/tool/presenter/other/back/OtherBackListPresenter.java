package com.xzty.cq.tover.businessmanagement.projectmanagement.tool.presenter.other.back;

import com.xzty.cq.tover.businessmanagement.common.data.DataSourse;
import com.xzty.cq.tover.businessmanagement.common.factory.BasePresenter;
import com.xzty.cq.tover.businessmanagement.projectmanagement.tool.data.other.back.OtherBackListHelper;
import com.xzty.cq.tover.businessmanagement.projectmanagement.tool.model.other.RspBackList;

import java.util.List;

/**
 * author zzl
 * Created 2018/5/28.
 * explain
 */

public class OtherBackListPresenter extends BasePresenter<OtherBackListContract.View> implements OtherBackListContract.Presenter,DataSourse.Callback{
    public OtherBackListPresenter(OtherBackListContract.View view) {
        super(view);
    }

    @Override
    public void getData() {
        final OtherBackListContract.View view = getView();
        OtherBackListHelper.getData(this);
    }

    @Override
    public void onDataLoaded(Object o) {
        getView().success((List<RspBackList>) o);
    }

    @Override
    public void onDataNotAvailable(String strRes) {
        getView().showError(strRes);
    }
}
