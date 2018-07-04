package com.xzty.cq.tover.businessmanagement.projectmanagement.tool.presenter.pickdepartment.distribute;

import com.xzty.cq.tover.businessmanagement.common.factory.BaseContract;
import com.xzty.cq.tover.businessmanagement.projectmanagement.tool.model.pickdepartment.RspDistributeOrderList;

import java.util.List;

/**
 * author zzl
 * Created 2018/5/25.
 * explain
 */

public interface DistributeOrderListContract {

    interface View extends BaseContract.View<DistributeOrderListContract.Presenter> {
        void success(List<RspDistributeOrderList> list);
    }


    interface Presenter extends BaseContract.Presenter {
        void getData();
    }
}
