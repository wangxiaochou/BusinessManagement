package com.xzty.cq.tover.businessmanagement.projectmanagement.tool.presenter.other.receive;

import com.xzty.cq.tover.businessmanagement.common.factory.BaseContract;
import com.xzty.cq.tover.businessmanagement.projectmanagement.tool.model.other.RspReceive;

import java.util.List;

/**
 * author zzl
 * Created 2018/5/26.
 * explain
 */

public interface OtherReceiveContract {

    interface View extends BaseContract.View<OtherReceiveContract.Presenter>{
        void success(List<RspReceive> list);

        void clickSuccess(List<RspReceive> list);

        void commitSuccess();
    }


    interface Presenter extends BaseContract.Presenter{
        void getData();

        void itemClick(List<RspReceive> list, int position);

        void commit(List<RspReceive> list, String note);
    }
}
