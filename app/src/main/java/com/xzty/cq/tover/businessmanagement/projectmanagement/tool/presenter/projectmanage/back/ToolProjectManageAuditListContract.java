package com.xzty.cq.tover.businessmanagement.projectmanagement.tool.presenter.projectmanage.back;

import com.xzty.cq.tover.businessmanagement.common.factory.BaseContract;
import com.xzty.cq.tover.businessmanagement.projectmanagement.tool.model.back.RspAuditList;

import java.util.List;

/**
 * author zzl
 * Created 2018/5/28.
 * explain
 */

public interface ToolProjectManageAuditListContract {

    interface View extends BaseContract.View<ToolProjectManageAuditListContract.Presenter>{
        void seccess(List<RspAuditList> list);
    }

    interface Presenter extends BaseContract.Presenter{
        void getData();
    }
}
