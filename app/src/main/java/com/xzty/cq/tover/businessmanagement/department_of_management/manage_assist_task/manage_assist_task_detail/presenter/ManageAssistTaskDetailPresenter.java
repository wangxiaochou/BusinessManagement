package com.xzty.cq.tover.businessmanagement.department_of_management.manage_assist_task.manage_assist_task_detail.presenter;

import com.xzty.cq.tover.businessmanagement.common.data.DataSourse;
import com.xzty.cq.tover.businessmanagement.common.factory.BasePresenter;
import com.xzty.cq.tover.businessmanagement.common.model.RspModel;
import com.xzty.cq.tover.businessmanagement.department_of_management.manage_assist_task.manage_assist_task_detail.model.AssistProgressDetailsHelper;
import com.xzty.cq.tover.businessmanagement.department_of_management.manage_assist_task.manage_assist_task_detail.model.RspAssistProgressDetails;
import com.xzty.cq.tover.businessmanagement.department_of_management.manage_assist_task.manage_assist_task_list.presenter.ManageAssistTaskContract;

import java.util.List;

public class ManageAssistTaskDetailPresenter extends BasePresenter<ManageAssistTaskDetailContract.View>
        implements ManageAssistTaskDetailContract.Presenter,DataSourse.Callback<RspModel>{

    public ManageAssistTaskDetailPresenter(ManageAssistTaskDetailContract.View view) {
        super(view);
    }

    @Override
    public void setAssistTaskDone(int assistTaskId) {
        AssistProgressDetailsHelper.setAssistTaskDone(assistTaskId,this);
    }

    @Override
    public void onDataLoaded(RspModel rspModel) {
        ManageAssistTaskDetailContract.View view = getView();
        view.success();
    }

    @Override
    public void onDataNotAvailable(String strRes) {
        ManageAssistTaskDetailContract.View view = getView();
        view.showError(strRes);
    }
}
