package com.xzty.cq.tover.businessmanagement.department_of_management.project_task.project_task_detail.presenter;

import android.util.Log;

import com.xzty.cq.tover.businessmanagement.common.data.DataSourse;
import com.xzty.cq.tover.businessmanagement.common.factory.BasePresenter;
import com.xzty.cq.tover.businessmanagement.department_of_management.project_task.project_task_detail.contract.MeetingProjectDetail_Contract;
import com.xzty.cq.tover.businessmanagement.department_of_management.project_task.project_task_detail.model.GetMeetingProjectDetail;
import com.xzty.cq.tover.businessmanagement.department_of_management.project_task.project_task_detail.model.ReqMeetingProjectDetail;


public class MeetingProjectDetailPresenter extends BasePresenter<MeetingProjectDetail_Contract.View>
        implements MeetingProjectDetail_Contract.Presenter,DataSourse.Callback<GetMeetingProjectDetail>{

    private String isNet;

    public MeetingProjectDetailPresenter(MeetingProjectDetail_Contract.View view){
        super(view);
    }

    @Override
    public void getData(ReqMeetingProjectDetail reqMeetingProjectDetail) {
        isNet = "getData";
        MeetingProjectDetailHelper.getData(this,reqMeetingProjectDetail);
    }

    @Override
    public void onDataLoaded(GetMeetingProjectDetail getMeetingProjectDetail) {
        MeetingProjectDetail_Contract.View view = getView();
        view.success(getMeetingProjectDetail);
    }

    @Override
    public void onDataNotAvailable(String strRes) {
        getView().showError(strRes);
    }
}
