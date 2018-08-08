package com.xzty.cq.tover.businessmanagement.department_of_management.project_task.project_task_detail.presenter;

import com.xzty.cq.tover.businessmanagement.common.data.DataSourse;
import com.xzty.cq.tover.businessmanagement.common.model.RspModel;
import com.xzty.cq.tover.businessmanagement.common.net.NetWork;
import com.xzty.cq.tover.businessmanagement.department_of_management.project_task.project_task_detail.model.GetMeetingProjectDetail;
import com.xzty.cq.tover.businessmanagement.department_of_management.project_task.project_task_detail.model.ReqMeetingProjectDetail;
import com.xzty.cq.tover.businessmanagement.projectmanagement.common.api.RemoteService;

import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MeetingProjectDetailHelper {

    public static void getData(final DataSourse.Callback<GetMeetingProjectDetail> callback,ReqMeetingProjectDetail reqMeetingProjectDetail){

        RemoteService service = NetWork.remote(RemoteService.class);
        Subscription subscription = service.oMPDGet(reqMeetingProjectDetail).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<RspModel<GetMeetingProjectDetail>>(){

            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                callback.onDataNotAvailable("请求失败" + e.getMessage());
            }

            @Override
            public void onNext(RspModel<GetMeetingProjectDetail> listRspModel) {
                int code = listRspModel.getBackcode();
                if (code == 1) {
                    callback.onDataLoaded(listRspModel.getData());
                } else {
                    callback.onDataNotAvailable("请求错误");
                }
            }
        });
    }

}
