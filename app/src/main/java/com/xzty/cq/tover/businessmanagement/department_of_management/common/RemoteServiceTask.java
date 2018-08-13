package com.xzty.cq.tover.businessmanagement.department_of_management.common;

import com.xzty.cq.tover.businessmanagement.common.model.RspModel;
import com.xzty.cq.tover.businessmanagement.department_of_management.manage_assist_task.manage_assist_task_detail.model.RspAssistProgressDetails;
import com.xzty.cq.tover.businessmanagement.department_of_management.manage_assist_task.manage_assist_task_list.model.ReqAssistAddProgress;
import com.xzty.cq.tover.businessmanagement.department_of_management.manage_assist_task.manage_assist_task_list.model.RspAssistTaskDetails;

import java.util.List;

import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;

/**
 * author yq
 * date 2018/8/1
 * explain 网络请求接口，用于任务管理模块
 */
public interface RemoteServiceTask {

    /**
     * @Author yq
     * 获取某一项目的协调任务
     *
     * @param projectId
     * @return
     */
    @GET("Androidprojectworks/androidprojectworks")
    Observable<RspModel<List<RspAssistTaskDetails>>> getTasks(@Query("PROJECT_ID") String projectId);

    /**
     * Author yq
     * 修改协调任务状态为已完成
     * @param assistTaskId
     * @return
     */
    @GET("Androidprojectworks/androidchangestuts")
    Observable<RspModel> setAssistTaskDone(@Query("id") int assistTaskId);

    /**
     * author yq
     * 添加协调任务进展
     * @param reqProgress
     * @return
     */
    @POST("Androidprojectworks/androidtrackmsg")
    Observable<RspModel> addAssistProgress(@Body ReqAssistAddProgress reqProgress);
}
