package com.xzty.cq.tover.businessmanagement.projectmanagement.tool.presenter.pickdepartment.affirm;

import com.xzty.cq.tover.businessmanagement.common.data.DataSourse;
import com.xzty.cq.tover.businessmanagement.common.factory.BasePresenter;
import com.xzty.cq.tover.businessmanagement.projectmanagement.tool.data.pickdepartment.affirm.AffirmOrderHelper;
import com.xzty.cq.tover.businessmanagement.projectmanagement.tool.model.projectmanage.RspAffirmList;

import java.util.List;

/**
 * author zzl
 * Created 2018/5/24.
 * explain
 */

public class AffirmOrderPresenter extends BasePresenter<AffirmOrderContract.View> implements AffirmOrderContract.Presenter,DataSourse.Callback{

    public AffirmOrderPresenter(AffirmOrderContract.View view) {
        super(view);
    }

    @Override
    public void getApplyList(String id) {
        AffirmOrderHelper.getApplyList(id,this);
    }

    @Override
    public void onDataLoaded(Object o) {
        getView().success((List<RspAffirmList>) o);
    }

    @Override
    public void onDataNotAvailable(String strRes) {
        getView().showError(strRes);
    }
}
