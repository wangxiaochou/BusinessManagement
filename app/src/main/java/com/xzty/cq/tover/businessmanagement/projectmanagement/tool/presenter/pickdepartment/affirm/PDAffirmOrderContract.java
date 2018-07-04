package com.xzty.cq.tover.businessmanagement.projectmanagement.tool.presenter.pickdepartment.affirm;

import com.xzty.cq.tover.businessmanagement.common.factory.BaseContract;
import com.xzty.cq.tover.businessmanagement.projectmanagement.tool.model.projectmanage.RspAffirmDetails;

import java.util.List;

/**
 * author zzl
 * Created 2018/5/24.
 * explain
 */

public interface PDAffirmOrderContract {

    interface View extends BaseContract.View<PDAffirmOrderContract.Presenter> {
        void detailsSuccess(List<RspAffirmDetails> list);

        void reFreshAdapter(List<RspAffirmDetails>sureList, int check, int refuse,int ignore);

        void sureSuccess();
    }

    interface Presenter extends BaseContract.Presenter {
        void getDetails(int id);

        void sure(List<RspAffirmDetails>sureList, int applyId);

        void longClick(List<RspAffirmDetails>sureList, int position);

        void itemClick(List<RspAffirmDetails>sureList, int position);
    }

}
