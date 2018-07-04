package com.xzty.cq.tover.businessmanagement.projectmanagement.tool.presenter.other.receive;

import com.xzty.cq.tover.businessmanagement.common.factory.BaseContract;
import com.xzty.cq.tover.businessmanagement.projectmanagement.tool.model.other.RspReceiveDetais;

import java.util.List;

/**
 * author zzl
 * Created 2018/5/26.
 * explain
 */

public interface OtherReceiveDetailsContract {

    interface View extends BaseContract.View<OtherReceiveDetailsContract.Presenter>{
        void success(List<RspReceiveDetais> list);
    }

    interface Presenter extends BaseContract.Presenter{
        void getData(int id);
    }
}
