package com.xzty.cq.tover.businessmanagement.projectmanagement.tool.presenter.other.back;

import com.xzty.cq.tover.businessmanagement.common.data.DataSourse;
import com.xzty.cq.tover.businessmanagement.common.factory.BasePresenter;
import com.xzty.cq.tover.businessmanagement.projectmanagement.tool.data.other.back.OtherBackDetailsHelper;
import com.xzty.cq.tover.businessmanagement.projectmanagement.tool.model.other.RspBackDetails;

import java.util.List;

/**
 * author zzl
 * Created 2018/5/28.
 * explain
 */

public class OtherBackDetailsPresenter extends BasePresenter<OtherBackDetailsContract.View> implements OtherBackDetailsContract.Presenter ,DataSourse.Callback{
    public OtherBackDetailsPresenter(OtherBackDetailsContract.View view) {
        super(view);
    }

    @Override
    public void getData(int id) {
        OtherBackDetailsHelper.getData(id,this);
    }

    @Override
    public void onDataLoaded(Object o) {
        getView().success((List<RspBackDetails>) o);
    }

    @Override
    public void onDataNotAvailable(String strRes) {
        getView().showError(strRes);
    }
}
