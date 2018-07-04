package com.xzty.cq.tover.businessmanagement.projectmanagement.tool.presenter.pickdepartment.distribute;

import com.xzty.cq.tover.businessmanagement.common.factory.BaseContract;
import com.xzty.cq.tover.businessmanagement.projectmanagement.tool.model.pickdepartment.RspDistributeOrderDetails;

import java.util.List;

/**
 * author zzl
 * Created 2018/5/29.
 * explain
 */

public interface DistributeOrderDetailsContract {

    interface View extends BaseContract.View<DistributeOrderDetailsContract.Presenter> {
        void success(List<RspDistributeOrderDetails> list);

        void clickSuccess(List<RspDistributeOrderDetails> list);

        void confirmSuccess();
    }

    interface Presenter extends BaseContract.Presenter {
        void getData(int toolDistId,int status);

        void itemClick(List<RspDistributeOrderDetails> list,int position);

        void confirm(int toolDistId,List<RspDistributeOrderDetails> list);

    }
}
