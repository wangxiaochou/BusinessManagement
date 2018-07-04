package com.xzty.cq.tover.businessmanagement.projectmanagement.tool.presenter.pickdepartment.distribute;

import com.xzty.cq.tover.businessmanagement.common.data.DataSourse;
import com.xzty.cq.tover.businessmanagement.common.factory.BasePresenter;
import com.xzty.cq.tover.businessmanagement.projectmanagement.tool.data.pickdepartment.distribute.PickDistributeHelper;
import com.xzty.cq.tover.businessmanagement.projectmanagement.tool.model.pickdepartment.RspAllDistribute;
import com.xzty.cq.tover.businessmanagement.projectmanagement.tool.model.pickdepartment.RspDistribute;
import com.xzty.cq.tover.businessmanagement.projectmanagement.tool.model.pickdepartment.RspUser;

import java.util.List;

/**
 * author zzl
 * Created 2018/5/24.
 * explain
 */

public class PickDistributePresenter extends BasePresenter<PickDistributeContract.View> implements PickDistributeContract.Presenter, DataSourse.Callback {
    private String isNet;

    public PickDistributePresenter(PickDistributeContract.View view) {
        super(view);
    }

    @Override
    public void getData() {
        isNet = "getData";
        PickDistributeHelper.getData(this);
    }

    @Override
    public void itemClick(List<RspDistribute> list, int position) {
        PickDistributeContract.View view = getView();
        view.itemSuccess(PickDistributeHelper.itemClick(list, position));
    }

    @Override
    public void submit(List<RspDistribute> list, String note, RspUser chooseEmp) {
        isNet = "submit";
        PickDistributeHelper.submit(list, note, chooseEmp, this);
    }

    @Override
    public void onDataLoaded(Object o) {
        if (isNet.equals("getData")) {
            getView().success((RspAllDistribute) o);
        } else if (isNet.equals("submit")) {
            getView().submitSuccess();
        }
    }

    @Override
    public void onDataNotAvailable(String strRes) {
        getView().showError(strRes);
    }
}
