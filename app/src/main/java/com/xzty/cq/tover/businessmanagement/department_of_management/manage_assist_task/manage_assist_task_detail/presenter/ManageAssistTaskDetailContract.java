package com.xzty.cq.tover.businessmanagement.department_of_management.manage_assist_task.manage_assist_task_detail.presenter;

import com.xzty.cq.tover.businessmanagement.common.factory.BaseContract;
import com.xzty.cq.tover.businessmanagement.department_of_management.manage_assist_task.manage_assist_task_detail.model.RspAssistProgressDetails;

import java.util.List;

public interface ManageAssistTaskDetailContract {
    interface View extends BaseContract.View<ManageAssistTaskDetailContract.Presenter>{
        //修改成功执行的方法
        void success();
    }
    interface Presenter extends BaseContract.Presenter{
        void setAssistTaskDone(int assistTaskId);
    }
}
