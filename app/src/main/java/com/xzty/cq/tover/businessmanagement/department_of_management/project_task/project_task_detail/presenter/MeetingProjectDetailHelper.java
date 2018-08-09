package com.xzty.cq.tover.businessmanagement.department_of_management.project_task.project_task_detail.presenter;

import android.util.Log;

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

/**
 * author wl
 * Created 2018/08/08
 * explain 周例会详细细节helper，获取数据的过程
 */

public class MeetingProjectDetailHelper {

    public static final String TAG = "日志打印——令";

    public static void getData(final DataSourse.Callback<GetMeetingProjectDetail> callback,ReqMeetingProjectDetail reqMeetingProjectDetail){

        RemoteService service = NetWork.remote(RemoteService.class);
        Subscription subscription = service.oMPDGet(reqMeetingProjectDetail).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<RspModel<GetMeetingProjectDetail>>(){

            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                callback.onDataNotAvailable("请求失败" + e.getMessage());
                Log.e(TAG,String.format("这是输出的错误信息   ——  "+ e.getMessage(),callback));
            }

            @Override
            public void onNext(RspModel<GetMeetingProjectDetail> listRspModel) {
                int code = listRspModel.getBackcode();
                if (code == 1) {
                    callback.onDataLoaded(listRspModel.getData());
                    Log.e(TAG,String.format("这是获取的数据文件   ——  "+ listRspModel.getData().toString(),listRspModel));
                } else {
                    callback.onDataNotAvailable("请求错误");
                }
            }
        });
    }

}
