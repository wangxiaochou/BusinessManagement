package com.xzty.cq.tover.businessmanagement.projectmanagement.part.presenter.apply;

import com.xzty.cq.tover.businessmanagement.common.factory.BaseContract;
import com.xzty.cq.tover.businessmanagement.projectmanagement.part.model.apply.RspPartList;

import java.util.List;

/**
 * author zzl
 * Created 2018/5/10.
 * explain
 */

public interface OrderPartContract {

    interface View extends BaseContract.View<OrderPartContract.presenter> {
        void susccess(List<RspPartList> list);
    }

    interface presenter extends BaseContract.Presenter {
        void getItmeDetails(String projectId, String partId);
    }
}
