package com.xzty.cq.tover.businessmanagement.projectmanagement.part.presenter.pick;

import com.xzty.cq.tover.businessmanagement.common.data.DataSourse;
import com.xzty.cq.tover.businessmanagement.common.factory.BasePresenter;
import com.xzty.cq.tover.businessmanagement.projectmanagement.part.data.pick.PickDetailsHelper;
import com.xzty.cq.tover.businessmanagement.projectmanagement.part.model.pick.RspPickList;

import java.util.List;

/**
 * author zzl
 * Created 2018/5/14.
 * explain 采购单详细查看的P
 */
public class PickDetailsPresenter extends BasePresenter<PickDetailsContract.View>
        implements PickDetailsContract.Presenter, DataSourse.Callback<List<RspPickList>> {
    public PickDetailsPresenter(PickDetailsContract.View view) {
        super(view);
    }

    @Override
    public void getData(String distId) {
        start();
        PickDetailsHelper.getData(distId, this);
    }

    @Override
    public void onDataLoaded(List<RspPickList> list) {
        getView().success(list);
    }

    @Override
    public void onDataNotAvailable(String strRes) {
        getView().showError(strRes);
    }
}
