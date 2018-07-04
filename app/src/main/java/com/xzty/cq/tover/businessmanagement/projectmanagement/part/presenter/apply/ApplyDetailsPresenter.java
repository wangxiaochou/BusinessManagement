package com.xzty.cq.tover.businessmanagement.projectmanagement.part.presenter.apply;

import com.xzty.cq.tover.businessmanagement.common.data.DataSourse;
import com.xzty.cq.tover.businessmanagement.common.factory.BasePresenter;
import com.xzty.cq.tover.businessmanagement.projectmanagement.part.data.apply.PartApplyHelper;
import com.xzty.cq.tover.businessmanagement.projectmanagement.part.model.apply.RspApplyDetails;

import java.util.List;

/**
 * author zzl
 * Created 2018/5/10.
 * explain 申请详情中的P
 */

public class ApplyDetailsPresenter extends BasePresenter<ApplyDetailsContract.View> implements ApplyDetailsContract.Presenter, DataSourse.Callback<List<RspApplyDetails>>{

    public ApplyDetailsPresenter(ApplyDetailsContract.View view) {
        super(view);
    }

    @Override
    public void getDetails(String applyId) {
        ApplyDetailsContract.View view = getView();
        PartApplyHelper.applyDetails(applyId,this);
    }

    @Override
    public void onDataLoaded(List<RspApplyDetails> rspApplyDetails) {
        ApplyDetailsContract.View view = getView();
        view.success(rspApplyDetails);
    }

    @Override
    public void onDataNotAvailable(String strRes) {
        ApplyDetailsContract.View view = getView();
        view.showError(strRes);
    }
}
