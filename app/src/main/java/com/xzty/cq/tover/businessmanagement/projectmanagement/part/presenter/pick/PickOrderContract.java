package com.xzty.cq.tover.businessmanagement.projectmanagement.part.presenter.pick;

import com.xzty.cq.tover.businessmanagement.common.factory.BaseContract;
import com.xzty.cq.tover.businessmanagement.projectmanagement.part.model.Emp;
import com.xzty.cq.tover.businessmanagement.projectmanagement.part.model.pick.RspPickOrder;

import java.util.List;

/**
 * author zzl
 * Created 2018/5/11.
 * explain 采购单的Conctarct
 */

public interface PickOrderContract {

    interface View extends BaseContract.View<PickOrderContract.Presenter> {
        void backBuyer(List<Emp> temp,int i);

        void getOrder(List<RspPickOrder> mList);

        //确认成功
        void notaSuccess();
    }

    interface Presenter extends BaseContract.Presenter {
        //获得采购人
        void getBuyer();

        //获得采购列表
        void getPickOrder(String time, String distOrderState, String buyerId);

        //采购确认
        void notaPick(String distId);
    }
}
