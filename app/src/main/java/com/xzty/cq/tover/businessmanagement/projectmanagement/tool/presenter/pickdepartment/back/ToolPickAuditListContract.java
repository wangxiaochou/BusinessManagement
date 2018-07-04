package com.xzty.cq.tover.businessmanagement.projectmanagement.tool.presenter.pickdepartment.back;

import com.xzty.cq.tover.businessmanagement.common.factory.BaseContract;
import com.xzty.cq.tover.businessmanagement.projectmanagement.tool.model.back.RspAuditList;

import java.util.List;

/**
 * author zzl
 * Created 2018/5/28.
 * explain
 */

public interface ToolPickAuditListContract {

    interface View extends BaseContract.View<ToolPickAuditListContract.Presenter>{
        void seccess(List<RspAuditList> list);
    }

    interface Presenter extends BaseContract.Presenter{
        void getData();
    }
}
