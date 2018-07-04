package com.xzty.cq.tover.businessmanagement.projectmanagement.tool.presenter.projectmanage.affirm;

import com.xzty.cq.tover.businessmanagement.common.factory.BaseContract;
import com.xzty.cq.tover.businessmanagement.projectmanagement.tool.model.projectmanage.RspAffirmDetails;

import java.util.List;

/**
 * author zzl
 * Created 2018/5/23.
 * explain 申请机具详情
 */

public interface AffirmDetailsContract {

    interface View extends BaseContract.View<AffirmDetailsContract.Presenter> {
        void detailsSuccess(List<RspAffirmDetails> list);

        void reFreshAdapter(List<RspAffirmDetails>sureList,int check,int refuse);

        void sureSuccess();
    }

    interface Presenter extends BaseContract.Presenter {
        void getDetails(int id);

        void sure(List<RspAffirmDetails>sureList, int applyId);

        void longClick(List<RspAffirmDetails>sureList, int position);

        void itemClick(List<RspAffirmDetails>sureList, int position);
    }

}
