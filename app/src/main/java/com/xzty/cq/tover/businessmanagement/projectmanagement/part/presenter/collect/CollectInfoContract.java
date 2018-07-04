package com.xzty.cq.tover.businessmanagement.projectmanagement.part.presenter.collect;

import com.xzty.cq.tover.businessmanagement.common.factory.BaseContract;
import com.xzty.cq.tover.businessmanagement.projectmanagement.part.model.Emp;

import java.util.List;

/**
 * author zzl
 * Created 2018/5/11.
 * explain 分配信息的Contract
 */

public interface CollectInfoContract {

    interface View extends BaseContract.View<CollectInfoContract.Presenter> {
        void allBuyer(List<Emp> buyerList);

        void saveBack();
    }

    interface Presenter extends BaseContract.Presenter {
        //获得所有采购人
        void getAllBuyer();
        //分配
        void collect(String employId,String remake,String choose);
    }
}
