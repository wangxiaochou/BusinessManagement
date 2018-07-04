package com.xzty.cq.tover.businessmanagement.projectmanagement.part.presenter.deliver;

import com.xzty.cq.tover.businessmanagement.common.factory.BaseContract;
import com.xzty.cq.tover.businessmanagement.projectmanagement.part.model.deliver.RspPurchase;
import com.xzty.cq.tover.businessmanagement.projectmanagement.part.model.Emp;

import java.util.List;

/**
 * author zzl
 * Created 2018/5/14.
 * explain 发货单的Contract
 */

public interface DeliverOrderContract {

    interface View extends BaseContract.View<DeliverOrderContract.Presenter>{
        void backBuyer(List<Emp> emps,int i);
        void backDeliver(List<RspPurchase> list);
    }

    interface Presenter extends BaseContract.Presenter {
        void getBuyer();

        void getPurchas(String applyOrderState,String times,String firm,String contractNo,String outUserId);
    }
}
