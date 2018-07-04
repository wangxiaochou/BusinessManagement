package com.xzty.cq.tover.businessmanagement.projectmanagement.tool.presenter.pickdepartment.distribute;

import com.xzty.cq.tover.businessmanagement.common.factory.BaseContract;
import com.xzty.cq.tover.businessmanagement.projectmanagement.tool.model.pickdepartment.RspAllDistribute;
import com.xzty.cq.tover.businessmanagement.projectmanagement.tool.model.pickdepartment.RspDistribute;
import com.xzty.cq.tover.businessmanagement.projectmanagement.tool.model.pickdepartment.RspUser;

import java.util.List;

/**
 * author zzl
 * Created 2018/5/24.
 * explain
 */

public interface PickDistributeContract {

    interface View extends BaseContract.View<PickDistributeContract.Presenter> {
        void success(RspAllDistribute list);

        void itemSuccess(List<RspDistribute> list);

        void submitSuccess();
    }

    interface Presenter extends BaseContract.Presenter {
        void getData();

        void itemClick(List<RspDistribute> list, int position);

        void submit(List<RspDistribute> list,String note,RspUser chooseEmp);
    }
}
