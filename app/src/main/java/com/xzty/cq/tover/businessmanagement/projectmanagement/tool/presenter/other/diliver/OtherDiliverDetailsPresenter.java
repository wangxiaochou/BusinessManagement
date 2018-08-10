package com.xzty.cq.tover.businessmanagement.projectmanagement.tool.presenter.other.diliver;

import com.xzty.cq.tover.businessmanagement.common.data.DataSourse;
import com.xzty.cq.tover.businessmanagement.common.factory.BasePresenter;
import com.xzty.cq.tover.businessmanagement.projectmanagement.tool.data.other.diliver.OtherDiliverDetailsHelper;
import com.xzty.cq.tover.businessmanagement.projectmanagement.tool.model.other.RspDiliverDetails;

import java.util.List;

/**
 * author zzl
 * Created 2018/5/25.
 * explain
 */

public class OtherDiliverDetailsPresenter extends BasePresenter<OtherDiliverDetailsContract.View>
        implements OtherDiliverDetailsContract.Presenter ,DataSourse.Callback{
    public OtherDiliverDetailsPresenter(OtherDiliverDetailsContract.View view) {
        super(view);
    }

    @Override
    public void getData(int outId) {
        OtherDiliverDetailsHelper.getData(outId,this);
    }

    @Override
    public void onDataLoaded(Object o) {
        getView().success((List<RspDiliverDetails>) o);
    }

    @Override
    public void onDataNotAvailable(String strRes) {
        getView().showError(strRes);
    }
}
