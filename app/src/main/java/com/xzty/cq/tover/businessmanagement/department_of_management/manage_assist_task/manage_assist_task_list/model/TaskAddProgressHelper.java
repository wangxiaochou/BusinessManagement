package com.xzty.cq.tover.businessmanagement.department_of_management.manage_assist_task.manage_assist_task_list.model;

import android.util.Log;
import android.widget.Toast;

import com.xzty.cq.tover.businessmanagement.common.data.DataSourse;
import com.xzty.cq.tover.businessmanagement.common.model.RspModel;
import com.xzty.cq.tover.businessmanagement.common.net.NetWork;
import com.xzty.cq.tover.businessmanagement.department_of_management.common.RemoteServiceTask;
import com.xzty.cq.tover.businessmanagement.department_of_management.manage_assist_task.manage_assist_task_list.activity.ManageAssistTaskActivity;

import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * author yq
 * date 2018/8/9
 * 添加协调任务进展的帮助类
 */
public class TaskAddProgressHelper {
    public static void addProgress(final ReqAssistAddProgress reqProgress, final DataSourse.Callback callback){
        //TODO
        Log.d("helper", "addProgress: test"+reqProgress.toString());
        RemoteServiceTask service = NetWork.remote(RemoteServiceTask.class);
        Subscription subscription = service.addAssistProgress(reqProgress).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<RspModel>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d("add_progress_error", "onError() returned: " + e.getMessage());
                    }

                    @Override
                    public void onNext(RspModel rspModel) {
                        int code = rspModel.getBackcode();
                        if (code == 1){
                            callback.onDataLoaded( rspModel);
                        }else {
                            callback.onDataNotAvailable(rspModel.getMessage());
                        }
                    }
                });
    }
}
