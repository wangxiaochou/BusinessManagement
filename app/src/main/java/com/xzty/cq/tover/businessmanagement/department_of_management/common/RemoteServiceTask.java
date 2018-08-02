package com.xzty.cq.tover.businessmanagement.department_of_management.common;

import com.xzty.cq.tover.businessmanagement.common.model.RspModel;
import com.xzty.cq.tover.businessmanagement.department_of_management.manage_assist_task.manage_assist_task_list.model.RspAssistTaskDetails;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * author yq
 * date 2018/8/1
 * explain 网络请求接口，用于任务管理模块
 */
public interface RemoteServiceTask {

    /**
     * 获取某一项目的协调任务
     *
     * @param projectId
     * @return
     */
    @GET("Androidprojectworks/androidprojectworks")
    Observable<RspModel<List<RspAssistTaskDetails>>> getTasks(@Query("PROJECT_ID") String projectId);
}
