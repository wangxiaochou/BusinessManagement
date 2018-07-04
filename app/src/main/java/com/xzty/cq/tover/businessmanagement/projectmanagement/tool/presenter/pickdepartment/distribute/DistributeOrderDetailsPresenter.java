package com.xzty.cq.tover.businessmanagement.projectmanagement.tool.presenter.pickdepartment.distribute;

import com.xzty.cq.tover.businessmanagement.common.data.DataSourse;
import com.xzty.cq.tover.businessmanagement.common.factory.BasePresenter;
import com.xzty.cq.tover.businessmanagement.projectmanagement.tool.data.pickdepartment.distribute.DistributeOrderDetailsHelper;
import com.xzty.cq.tover.businessmanagement.projectmanagement.tool.model.pickdepartment.RspDistributeOrderDetails;

import java.util.List;

/**
 * author zzl
 * Created 2018/5/29.
 * explain
 */

public class DistributeOrderDetailsPresenter extends BasePresenter<DistributeOrderDetailsContract.View> implements DistributeOrderDetailsContract.Presenter, DataSourse.Callback {
    private String isNet;

    public DistributeOrderDetailsPresenter(DistributeOrderDetailsContract.View view) {
        super(view);
    }

    @Override
    public void getData(int toolDistId, int status) {
        isNet = "getData";
        DistributeOrderDetailsHelper.getData(toolDistId, status, this);
    }

    @Override
    public void itemClick(List<RspDistributeOrderDetails> list, int position) {
        getView().clickSuccess(DistributeOrderDetailsHelper.itemClick(list, position));
    }

    @Override
    public void confirm(int toolDistId, List<RspDistributeOrderDetails> list) {
        isNet = "confirm";
        DistributeOrderDetailsHelper.confirm(toolDistId, list, this);
    }

    @Override
    public void onDataLoaded(Object o) {
        if (isNet.equals("getData")) {
            getView().success((List<RspDistributeOrderDetails>) o);
        } else if (isNet.equals("confirm")) {
            getView().confirmSuccess();
        }
    }

    @Override
    public void onDataNotAvailable(String strRes) {
        getView().showError(strRes);
    }
}
