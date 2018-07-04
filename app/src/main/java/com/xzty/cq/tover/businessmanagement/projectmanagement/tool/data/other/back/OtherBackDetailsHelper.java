package com.xzty.cq.tover.businessmanagement.projectmanagement.tool.data.other.back;

import com.xzty.cq.tover.businessmanagement.common.data.DataSourse;
import com.xzty.cq.tover.businessmanagement.common.model.RspModel;
import com.xzty.cq.tover.businessmanagement.common.net.NetWork;
import com.xzty.cq.tover.businessmanagement.projectmanagement.common.api.RemoteService;
import com.xzty.cq.tover.businessmanagement.projectmanagement.tool.model.other.RspBackDetails;

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

public class OtherBackDetailsHelper {

    public static void getData(int id, final DataSourse.Callback callback) {
        RemoteService service = NetWork.remote(RemoteService.class);
        Subscription subscription = service.oGetBackListDetail(id).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<RspModel<List<RspBackDetails>>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
              callback.onDataNotAvailable("请求失败"+e.getMessage());
            }

            @Override
            public void onNext(RspModel<List<RspBackDetails>> listRspModel) {
                int code = listRspModel.getBackcode();
                if(code==1){
                   callback.onDataLoaded(listRspModel.getData());
                }else{
                  callback.onDataNotAvailable("请求错误");
                }
            }
        });
    }

}
