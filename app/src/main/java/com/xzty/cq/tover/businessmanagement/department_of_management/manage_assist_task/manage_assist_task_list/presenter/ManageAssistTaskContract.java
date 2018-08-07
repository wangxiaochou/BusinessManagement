package com.xzty.cq.tover.businessmanagement.department_of_management.manage_assist_task.manage_assist_task_list.presenter;

import com.xzty.cq.tover.businessmanagement.common.factory.BaseContract;
import com.xzty.cq.tover.businessmanagement.department_of_management.manage_assist_task.manage_assist_task_list.model.RspAssistTaskDetails;

import java.util.List;

/**
 * @Author yq
 * @Date 2018/8/1
 * @Comment 项目管理协调任务契约类
 */
public interface ManageAssistTaskContract {
    interface View extends BaseContract.View<ManageAssistTaskContract.Presenter>{
        //查询成功执行的方法
        void success(List<RspAssistTaskDetails> mlist);
    }
    interface Presenter extends BaseContract.Presenter{
        void getTasks(String projectId);
    }
}
