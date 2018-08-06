package com.xzty.cq.tover.businessmanagement.department_of_management.manage_assist_task.manage_assist_task_detail.presenter;

import com.xzty.cq.tover.businessmanagement.common.factory.BaseContract;
import com.xzty.cq.tover.businessmanagement.department_of_management.manage_assist_task.manage_assist_task_detail.model.RspAssistProgressDetails;

import java.util.List;

public interface ManageAssistTaskDetailContract {
    interface View extends BaseContract.View<ManageAssistTaskDetailContract.Presenter>{
        //查询成功执行的方法
        void success(List<RspAssistProgressDetails> mlist);
    }
    interface Presenter extends BaseContract.Presenter{
        void getAssistProgressDetails(int assistTaskId);
    }
}
