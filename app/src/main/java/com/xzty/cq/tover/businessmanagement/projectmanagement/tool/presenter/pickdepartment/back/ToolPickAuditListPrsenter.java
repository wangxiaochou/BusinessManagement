package com.xzty.cq.tover.businessmanagement.projectmanagement.tool.presenter.pickdepartment.back;

import com.xzty.cq.tover.businessmanagement.common.data.DataSourse;
import com.xzty.cq.tover.businessmanagement.common.factory.BasePresenter;
import com.xzty.cq.tover.businessmanagement.projectmanagement.tool.data.pickdepartment.back.ToolPickAuditListHelper;
import com.xzty.cq.tover.businessmanagement.projectmanagement.tool.model.back.RspAuditList;

import java.util.List;

/**
 * author zzl
 * Created 2018/5/28.
 * explain
 */

public class ToolPickAuditListPrsenter extends BasePresenter<ToolPickAuditListContract.View> implements ToolPickAuditListContract.Presenter,DataSourse.Callback {

    public ToolPickAuditListPrsenter(ToolPickAuditListContract.View view) {
        super(view);
    }

    @Override
    public void getData() {
        ToolPickAuditListHelper.getData(this);
    }

    @Override
    public void onDataLoaded(Object o) {
        getView().seccess((List<RspAuditList>) o);
    }

    @Override
    public void onDataNotAvailable(String strRes) {
        getView().showError(strRes);
    }
}
