package com.xzty.cq.tover.businessmanagement.department_of_management.manage_assist_task.manage_assist_task_detail.model;

import com.xzty.cq.tover.businessmanagement.common.data.DataSourse;
import com.xzty.cq.tover.businessmanagement.common.model.RspModel;
import com.xzty.cq.tover.businessmanagement.common.net.NetWork;
import com.xzty.cq.tover.businessmanagement.department_of_management.common.RemoteServiceTask;

import java.util.List;

import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class AssistProgressDetailsHelper {
    public static void setAssistTaskDone(int assistTaskId,final DataSourse.Callback<RspModel> callback) {
        RemoteServiceTask service = NetWork.remote(RemoteServiceTask.class);
        Subscription subscription = service.setAssistTaskDone(assistTaskId).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<RspModel>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(RspModel rspModel) {
                        if (rspModel.getBackcode() == 1){
                            callback.onDataLoaded(rspModel);
                        }else {
                            callback.onDataNotAvailable(rspModel.getMessage());
                        }
                    }
                });
    }

}
