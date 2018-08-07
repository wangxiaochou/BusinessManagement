package com.xzty.cq.tover.businessmanagement.department_of_management.manage_assist_task.manage_assist_task_list.presenter;

import com.xzty.cq.tover.businessmanagement.common.data.DataSourse;
import com.xzty.cq.tover.businessmanagement.common.factory.BasePresenter;
import com.xzty.cq.tover.businessmanagement.department_of_management.manage_assist_task.manage_assist_task_list.model.AssistTaskHelper;
import com.xzty.cq.tover.businessmanagement.department_of_management.manage_assist_task.manage_assist_task_list.model.RspAssistTaskDetails;

import java.util.List;

/**
 * @Author yq
 * @Date 2018/8/1
 * @Comment 协调任务
 */
public class ManageAssistTaskPresenter extends BasePresenter<ManageAssistTaskContract.View> implements ManageAssistTaskContract.Presenter
        ,DataSourse.Callback<List<RspAssistTaskDetails>>{

    public ManageAssistTaskPresenter(ManageAssistTaskContract.View view) {
        super(view);
    }

    @Override
    public void getTasks(String projectId) {
        AssistTaskHelper.getTasks(projectId,this);
    }

    @Override
    public void onDataLoaded(List<RspAssistTaskDetails> mlist) {
        ManageAssistTaskContract.View view = getView();
        view.success(mlist);
    }

    @Override
    public void onDataNotAvailable(String strRes) {
        ManageAssistTaskContract.View view = getView();
        view.showError(strRes);
    }
}
