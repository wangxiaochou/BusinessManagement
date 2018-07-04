package com.xzty.cq.tover.businessmanagement.projectmanagement.tool.presenter.pickdepartment.distribute;

import com.xzty.cq.tover.businessmanagement.common.factory.BaseContract;
import com.xzty.cq.tover.businessmanagement.projectmanagement.tool.model.pickdepartment.RspDemage;
import com.xzty.cq.tover.businessmanagement.projectmanagement.tool.model.pickdepartment.ToolBDInRequest;

import java.util.List;

/**
 * author zzl
 * Created 2018/5/29.
 * explain
 */

public interface DistributeInContract {

    interface View extends BaseContract.View<DistributeInContract.Presenter>{
        void demageSuccess(List<RspDemage> list);

        void success();
    }

    interface Presenter extends BaseContract.Presenter{
        void demage();

        void in(ToolBDInRequest request);
    }
}
