package com.xzty.cq.tover.businessmanagement.department_of_management.manage_assist_task.manage_assist_task_list.presenter;

import com.xzty.cq.tover.businessmanagement.common.data.DataSourse;
import com.xzty.cq.tover.businessmanagement.common.model.RspModel;
import com.xzty.cq.tover.businessmanagement.department_of_management.manage_assist_task.manage_assist_task_list.activity.TaskAddProgressDialog;
import com.xzty.cq.tover.businessmanagement.department_of_management.manage_assist_task.manage_assist_task_list.model.ReqAssistAddProgress;
import com.xzty.cq.tover.businessmanagement.department_of_management.manage_assist_task.manage_assist_task_list.model.TaskAddProgressHelper;

/**
 * author yq
 * date 2018/8/9
 * 添加协调任务进展弹窗的presenter
 */
public class TaskAddProgressPresenter implements DataSourse.Callback<RspModel>{
    private TaskAddProgressDialog addDialog;
    public void addTaskProgress(TaskAddProgressDialog addDialog,ReqAssistAddProgress raaProgress){
        this.addDialog = addDialog;
        TaskAddProgressHelper.addProgress(raaProgress,this);
    }

    @Override
    public void onDataLoaded(RspModel rspModel) {
        addDialog.addSuccess();
    }

    @Override
    public void onDataNotAvailable(String strRes) {
        addDialog.addFalse(strRes);
    }
}
