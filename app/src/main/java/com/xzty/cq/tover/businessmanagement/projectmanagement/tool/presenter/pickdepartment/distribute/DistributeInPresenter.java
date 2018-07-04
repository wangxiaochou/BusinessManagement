package com.xzty.cq.tover.businessmanagement.projectmanagement.tool.presenter.pickdepartment.distribute;

import com.xzty.cq.tover.businessmanagement.common.data.DataSourse;
import com.xzty.cq.tover.businessmanagement.common.factory.BasePresenter;
import com.xzty.cq.tover.businessmanagement.projectmanagement.tool.data.pickdepartment.distribute.DistributeInHelper;
import com.xzty.cq.tover.businessmanagement.projectmanagement.tool.model.pickdepartment.RspDemage;
import com.xzty.cq.tover.businessmanagement.projectmanagement.tool.model.pickdepartment.ToolBDInRequest;

import java.util.List;

/**
 * author zzl
 * Created 2018/5/29.
 * explain
 */

public class DistributeInPresenter extends BasePresenter<DistributeInContract.View> implements DistributeInContract.Presenter ,DataSourse.Callback{
    private String isNet;
    public DistributeInPresenter(DistributeInContract.View view) {
        super(view);
    }

    @Override
    public void demage() {
        isNet = "demage";
        DistributeInHelper.demage(this);
    }

    @Override
    public void in(ToolBDInRequest request) {
        isNet = "in";
        DistributeInHelper.in(request,this);
    }

    @Override
    public void onDataLoaded(Object o) {
        if(isNet.equals("demage")){
            getView().demageSuccess((List<RspDemage>) o);
        }else if(isNet.equals("in")){
            getView().success();
        }
    }

    @Override
    public void onDataNotAvailable(String strRes) {
        getView().showError(strRes);
    }
}
