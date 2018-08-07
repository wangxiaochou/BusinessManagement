package com.xzty.cq.tover.businessmanagement.department_of_management.manage_assist_task.manage_assist_task_list.model;

import com.xzty.cq.tover.businessmanagement.common.data.DataSourse;
import com.xzty.cq.tover.businessmanagement.common.model.RspModel;
import com.xzty.cq.tover.businessmanagement.common.net.NetWork;
import com.xzty.cq.tover.businessmanagement.department_of_management.common.RemoteServiceTask;
import com.xzty.cq.tover.businessmanagement.projectmanagement.common.api.RemoteService;
import com.xzty.cq.tover.businessmanagement.projectmanagement.part.model.apply.RspApplyDetails;

import java.util.List;

import rx.Scheduler;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * @Author yq
 * @Date 2018/8/1
 * @Comment 协调任务帮助类,用于协调任务获取网络请求
 */
public class AssistTaskHelper {
    public static void getTasks(String projectId, final DataSourse.Callback<List<RspAssistTaskDetails>> callback){
        RemoteServiceTask service = NetWork.remote(RemoteServiceTask.class);
        Subscription subscription = service.getTasks(projectId).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<RspModel<List<RspAssistTaskDetails>>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                callback.onDataNotAvailable("请求失败" + e.getMessage());
            }

            @Override
            public void onNext(RspModel<List<RspAssistTaskDetails>> listRspModel) {
                int code = listRspModel.getBackcode();
                if (code == 1) {
                    List<RspAssistTaskDetails> mList = listRspModel.getData();
                    callback.onDataLoaded(mList);
                } else {
                    callback.onDataNotAvailable("请求错误");
                }
            }
        });
    }
}
