package com.xzty.cq.tover.businessmanagement.projectmanagement.tool.presenter.other.receive;

import com.xzty.cq.tover.businessmanagement.common.data.DataSourse;
import com.xzty.cq.tover.businessmanagement.common.factory.BasePresenter;
import com.xzty.cq.tover.businessmanagement.projectmanagement.tool.data.other.receive.OtherReceiveListHelper;
import com.xzty.cq.tover.businessmanagement.projectmanagement.tool.model.other.RspReceiveList;

import java.util.List;

/**
 * author zzl
 * Created 2018/5/26.
 * explain
 */

public class OtherReceiveListPresenter extends BasePresenter<OtherReceiveListContrat.View> implements OtherReceiveListContrat.Presenter,DataSourse.Callback{

    public OtherReceiveListPresenter(OtherReceiveListContrat.View view) {
        super(view);
    }

    @Override
    public void getData() {
        OtherReceiveListHelper.getData(this);
    }

    @Override
    public void onDataLoaded(Object o) {
        getView().success((List<RspReceiveList>) o);
    }

    @Override
    public void onDataNotAvailable(String strRes) {
        getView().showError(strRes);
    }
}
