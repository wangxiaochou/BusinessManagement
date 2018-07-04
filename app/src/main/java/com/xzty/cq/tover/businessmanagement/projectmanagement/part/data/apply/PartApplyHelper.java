package com.xzty.cq.tover.businessmanagement.projectmanagement.part.data.apply;

import com.xzty.cq.tover.businessmanagement.common.data.DataSourse;
import com.xzty.cq.tover.businessmanagement.common.model.RspModel;
import com.xzty.cq.tover.businessmanagement.common.net.NetWork;
import com.xzty.cq.tover.businessmanagement.projectmanagement.common.api.RemoteService;
import com.xzty.cq.tover.businessmanagement.projectmanagement.part.model.apply.RspApplyDetails;
import com.xzty.cq.tover.businessmanagement.projectmanagement.part.model.apply.RspPartList;

import java.util.List;

import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * author zzl
 * Created 2018/6/8.
 * explain
 */

public class PartApplyHelper {
    public static void applyDetails(String applyId, final DataSourse.Callback<List<RspApplyDetails>> callback) {
        RemoteService service = NetWork.remote(RemoteService.class);
        Subscription subscription = service.details(applyId).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<RspModel<List<RspApplyDetails>>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                callback.onDataNotAvailable("请求失败" + e.getMessage());
            }

            @Override
            public void onNext(RspModel<List<RspApplyDetails>> listRspModel) {
                int code = listRspModel.getBackcode();
                if (code == 1) {
                    List<RspApplyDetails> mList = listRspModel.getData();
                    callback.onDataLoaded(mList);
                } else {
                    callback.onDataNotAvailable("请求错误");
                }
            }
        });
    }



    public static void orderPart(String projectId, String partId, final DataSourse.Callback<List<RspPartList>> callback) {
        RemoteService service = NetWork.remote(RemoteService.class);
        Subscription subscription = service.queryPartDetails(projectId, partId).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<RspModel<List<RspPartList>>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                callback.onDataNotAvailable("请求失败" + e.getMessage());
            }

            @Override
            public void onNext(RspModel<List<RspPartList>> listRspModel) {
                int code = listRspModel.getBackcode();
                if (code == 1) {
                    callback.onDataLoaded(listRspModel.getData());
                } else {
                    callback.onDataNotAvailable("请求错误");
                }
            }
        });
    }

}
