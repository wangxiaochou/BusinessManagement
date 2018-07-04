package com.xzty.cq.tover.businessmanagement.projectmanagement.tool.presenter.other.receive;

import com.xzty.cq.tover.businessmanagement.common.data.DataSourse;
import com.xzty.cq.tover.businessmanagement.common.factory.BasePresenter;
import com.xzty.cq.tover.businessmanagement.projectmanagement.tool.data.other.receive.OtherReceiveHelper;
import com.xzty.cq.tover.businessmanagement.projectmanagement.tool.model.other.RspReceive;

import java.util.List;

/**
 * author zzl
 * Created 2018/5/26.
 * explain
 */

public class OtherReceivePresenter extends BasePresenter<OtherReceiveContract.View> implements OtherReceiveContract.Presenter, DataSourse.Callback {
    private String isNet;

    public OtherReceivePresenter(OtherReceiveContract.View view) {
        super(view);
    }

    @Override
    public void getData() {
        isNet = "getData";
        OtherReceiveHelper.getData(this);
    }

    @Override
    public void itemClick(List<RspReceive> list, int position) {
       getView().clickSuccess(OtherReceiveHelper.itemClick(list,position));
    }

    @Override
    public void commit(List<RspReceive> list, String note) {
        isNet = "commit";
        OtherReceiveHelper.commit(list,note,this);
    }

    @Override
    public void onDataLoaded(Object o) {
        if (isNet.equals("getData")) {
            getView().success((List<RspReceive>) o);
        }else if(isNet.equals("commit")){
            getView().commitSuccess();
        }
    }

    @Override
    public void onDataNotAvailable(String strRes) {
        getView().showError(strRes);
    }
}
