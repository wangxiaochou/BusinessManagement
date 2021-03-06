package com.xzty.cq.tover.businessmanagement.department_of_management.project_task.project_task_list.presenter;

import com.xzty.cq.tover.businessmanagement.common.data.DataSourse;
import com.xzty.cq.tover.businessmanagement.common.model.RspModel;
import com.xzty.cq.tover.businessmanagement.common.net.NetWork;
import com.xzty.cq.tover.businessmanagement.department_of_management.manage_assist_task.manage_assist_task_list.model.RspAssistTaskDetails;
import com.xzty.cq.tover.businessmanagement.department_of_management.project_task.project_task_list.bean.ProjectTaskList_List;
import com.xzty.cq.tover.businessmanagement.projectmanagement.common.api.RemoteService;

import java.util.List;

import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class ProjectTaskList_Helper {

    //@Override
    public static void getProjectTaskList(int id,final DataSourse.Callback<List<ProjectTaskList_List>> callback) {
        RemoteService service = NetWork.remote(RemoteService.class);
        Subscription subscription = service.getId(id).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<RspModel<List<ProjectTaskList_List>>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                callback.onDataNotAvailable("请求失败" + e.getMessage());
            }

            @Override
            public void onNext(RspModel<List<ProjectTaskList_List>> listRspModel) {
                int code = listRspModel.getBackcode();
                if (code == 1) {
                    List<ProjectTaskList_List> mList = listRspModel.getData();
                    callback.onDataLoaded(mList);
                } else {
                    callback.onDataNotAvailable("请求错误");
                }
            }

        });
}
}
