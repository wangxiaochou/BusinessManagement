package com.xzty.cq.tover.businessmanagement.projectmanagement.tool.presenter.pickdepartment.affirm;

import com.xzty.cq.tover.businessmanagement.common.factory.BaseContract;
import com.xzty.cq.tover.businessmanagement.projectmanagement.tool.model.projectmanage.RspAffirmList;

import java.util.List;

/**
 * author zzl
 * Created 2018/5/24.
 * explain
 */

public interface AffirmOrderContract {
    interface View extends BaseContract.View<AffirmOrderContract.Presenter> {
        void success(List<RspAffirmList> list);
    }

    interface Presenter extends BaseContract.Presenter {
        void getApplyList(String id);
    }
}
