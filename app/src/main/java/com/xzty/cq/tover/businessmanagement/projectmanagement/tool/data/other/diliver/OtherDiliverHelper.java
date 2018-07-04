package com.xzty.cq.tover.businessmanagement.projectmanagement.tool.data.other.diliver;

import com.xzty.cq.tover.businessmanagement.common.MyApplication;
import com.xzty.cq.tover.businessmanagement.common.data.DataSourse;
import com.xzty.cq.tover.businessmanagement.common.factory.Account;
import com.xzty.cq.tover.businessmanagement.common.model.RspModel;
import com.xzty.cq.tover.businessmanagement.common.net.NetWork;
import com.xzty.cq.tover.businessmanagement.projectmanagement.common.api.RemoteService;
import com.xzty.cq.tover.businessmanagement.projectmanagement.tool.model.other.RspOtherDiliver;

import java.util.List;

import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * author zzl
 * Created 2018/6/13.
 * explain
 */

public class OtherDiliverHelper {

    public static void getData(final DataSourse.Callback callback){
        Account.load(MyApplication.getInstance());
        String projectId = Account.getProjectId();
        RemoteService service = NetWork.remote(RemoteService.class);
        Subscription subscription = service.getOutList(projectId).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<RspModel<List<RspOtherDiliver>>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
               callback.onDataNotAvailable("请求失败" + e.getMessage());
            }

            @Override
            public void onNext(RspModel<List<RspOtherDiliver>> listRspModel) {
                int code = listRspModel.getBackcode();
                if(code ==1){
                    callback.onDataLoaded(listRspModel.getData());
                }else{
                    callback.onDataNotAvailable("请求错误");
                }
            }
        });
    }

}
