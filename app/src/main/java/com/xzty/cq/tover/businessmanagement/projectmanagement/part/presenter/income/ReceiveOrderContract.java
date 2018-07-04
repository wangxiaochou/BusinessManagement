package com.xzty.cq.tover.businessmanagement.projectmanagement.part.presenter.income;

import com.xzty.cq.tover.businessmanagement.common.factory.BaseContract;
import com.xzty.cq.tover.businessmanagement.projectmanagement.part.model.Emp;
import com.xzty.cq.tover.businessmanagement.projectmanagement.part.model.deliver.DeliverOrder;

import java.util.List;

/**
 * author zzl
 * Created 2018/5/15.
 * explain 收货单的Contract
 */

public interface ReceiveOrderContract {

    interface View extends BaseContract.View<ReceiveOrderContract.Presenter> {
        void seacrchCallBack(List<DeliverOrder> list, List<Emp> empList);
    }

    interface Presenter extends BaseContract.Presenter {
        void search(String applyUserId,String time,String applyOrderState);
    }
}
