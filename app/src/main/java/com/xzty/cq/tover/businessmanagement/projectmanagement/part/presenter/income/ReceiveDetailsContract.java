package com.xzty.cq.tover.businessmanagement.projectmanagement.part.presenter.income;

import com.xzty.cq.tover.businessmanagement.common.factory.BaseContract;
import com.xzty.cq.tover.businessmanagement.projectmanagement.part.model.deliver.DeliverDetails;

import java.util.List;

/**
 * author zzl
 * Created 2018/5/15.
 * explain 收货详情的C
 */

public interface ReceiveDetailsContract {

    interface View extends BaseContract.View<ReceiveDetailsContract.Presenter>{
        void success(List<DeliverDetails> list);
    }

    interface Presenter extends BaseContract.Presenter{
        void getData(String outId);
    }
}
