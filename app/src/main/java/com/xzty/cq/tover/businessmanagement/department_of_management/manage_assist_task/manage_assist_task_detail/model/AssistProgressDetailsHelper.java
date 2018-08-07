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
    public static void getAssistProgressDetails(int assistTaskId,final DataSourse.Callback<List<RspAssistProgressDetails>> callback){
        RemoteServiceTask service = NetWork.remote(RemoteServiceTask.class);
        Subscription subscription = service.getAssistProgressDetails(assistTaskId).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<RspModel<List<RspAssistProgressDetails>>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(RspModel<List<RspAssistProgressDetails>> listRspModel) {
                        int code = listRspModel.getBackcode();
                        if (code == 1){
                            List<RspAssistProgressDetails> mList = listRspModel.getData();
                            callback.onDataLoaded(mList);
                        }else {
                            callback.onDataNotAvailable("请求错误");
                        }
                    }
                });

    }
}
