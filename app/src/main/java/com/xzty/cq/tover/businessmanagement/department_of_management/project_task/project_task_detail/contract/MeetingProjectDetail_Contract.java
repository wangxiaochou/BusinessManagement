package com.xzty.cq.tover.businessmanagement.department_of_management.project_task.project_task_detail.contract;

import com.xzty.cq.tover.businessmanagement.common.factory.BaseContract;
import com.xzty.cq.tover.businessmanagement.department_of_management.project_task.project_task_detail.model.GetMeetingProjectDetail;
import com.xzty.cq.tover.businessmanagement.department_of_management.project_task.project_task_detail.model.ReqMeetingProjectDetail;


public interface MeetingProjectDetail_Contract {

    interface Presenter extends BaseContract.Presenter{

        void getData(ReqMeetingProjectDetail reqMeetingProjectDetail);
    }

    interface View extends BaseContract.View<MeetingProjectDetail_Contract.Presenter>{
        void success(GetMeetingProjectDetail list);
    }
}
