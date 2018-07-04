package com.xzty.cq.tover.businessmanagement.projectmanagement.tool.presenter.pickdepartment.diliver;

import com.xzty.cq.tover.businessmanagement.common.data.DataSourse;
import com.xzty.cq.tover.businessmanagement.common.factory.BasePresenter;
import com.xzty.cq.tover.businessmanagement.projectmanagement.tool.data.pickdepartment.diliver.DiliverOrderHelper;
import com.xzty.cq.tover.businessmanagement.projectmanagement.tool.model.pickdepartment.RspDiliverOrder;

import java.util.List;

/**
 * author zzl
 * Created 2018/5/25.
 * explain
 */

public class DiliverOrderPresenter extends BasePresenter<DiliverOrderContract.View> implements DiliverOrderContract.Presenter ,DataSourse.Callback{
    private String isNet;
    public DiliverOrderPresenter(DiliverOrderContract.View view) {
        super(view);
    }

    @Override
    public void getDat() {
        isNet = "getData";
        DiliverOrderHelper.getData(this);
    }

    @Override
    public void itemClick(List<RspDiliverOrder> list, int position) {
        DiliverOrderContract.View view = getView();
        view.clickSuccess(DiliverOrderHelper.itemClick(list,position));
    }

    @Override
    public void affirm(List<RspDiliverOrder> list, String firm, String contractNumber, String expectOutTime, String note) {
        isNet = "affirm";
        DiliverOrderHelper.affirm(list,firm,contractNumber,expectOutTime,note,this);
    }

    @Override
    public void onDataLoaded(Object o) {
        if(isNet.equals("getData")){
            getView().success((List<RspDiliverOrder>) o);
        }else if(isNet.equals("affirm")){
            getView().commitSuccess();
        }
    }

    @Override
    public void onDataNotAvailable(String strRes) {
        getView().showError(strRes);
    }
}
