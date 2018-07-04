package com.xzty.cq.tover.businessmanagement.projectmanagement.tool.presenter.pickdepartment.affirm;

import com.xzty.cq.tover.businessmanagement.common.data.DataSourse;
import com.xzty.cq.tover.businessmanagement.common.factory.BasePresenter;
import com.xzty.cq.tover.businessmanagement.projectmanagement.tool.data.pickdepartment.affirm.LongClickModel;
import com.xzty.cq.tover.businessmanagement.projectmanagement.tool.data.pickdepartment.affirm.PDAffirmOrderHelper;
import com.xzty.cq.tover.businessmanagement.projectmanagement.tool.model.projectmanage.RspAffirmDetails;

import java.util.List;

/**
 * author zzl
 * Created 2018/5/24.
 * explain
 */

public class PDAffirmOrderPresenter extends BasePresenter<PDAffirmOrderContract.View> implements PDAffirmOrderContract.Presenter, DataSourse.Callback {
    private String isNet;

    public PDAffirmOrderPresenter(PDAffirmOrderContract.View view) {
        super(view);
    }

    @Override
    public void getDetails(int id) {
        isNet = "getDetails";
        PDAffirmOrderHelper.getDetails(id, this);
    }

    @Override
    public void sure(List<RspAffirmDetails> sureList, int applyId) {
        isNet = "sure";
        PDAffirmOrderHelper.sure(sureList, applyId, this);
    }


    @Override
    public void longClick(List<RspAffirmDetails> sureList, int position) {
        PDAffirmOrderContract.View view = getView();
        if (sureList.get(position).isChecked) {
            view.showError("您已准备确认该机具,请取消拒绝后再试");
        } else {
            LongClickModel model = PDAffirmOrderHelper.longClick(sureList, position);
            view.reFreshAdapter(model.getSureList(), model.getCheck(), model.getRefuse(), model.getIgnore());
        }
    }

    @Override
    public void itemClick(List<RspAffirmDetails> sureList, int position) {
        PDAffirmOrderContract.View view = getView();
        if (sureList.get(position).isRefuse) {
            view.showError("您已拒绝该机具,请取消后再选择");
        } else {
            LongClickModel model = PDAffirmOrderHelper.itemClick(sureList, position);
            view.reFreshAdapter(sureList, model.getCheck(), model.getRefuse(), model.getIgnore());
        }
    }

    @Override
    public void onDataLoaded(Object o) {
        if (isNet.equals("getDetails")) {
            getView().detailsSuccess((List<RspAffirmDetails>) o);
        } else if (isNet.equals("sure")) {
            getView().sureSuccess();
        }
    }

    @Override
    public void onDataNotAvailable(String strRes) {
        getView().showError(strRes);
    }
}
