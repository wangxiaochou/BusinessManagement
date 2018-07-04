package com.xzty.cq.tover.businessmanagement.projectmanagement.part.data.apply;

import android.util.Log;

import com.xzty.cq.tover.businessmanagement.common.data.DataSourse;
import com.xzty.cq.tover.businessmanagement.common.model.RspLogin;
import com.xzty.cq.tover.businessmanagement.common.model.RspModel;
import com.xzty.cq.tover.businessmanagement.common.net.NetWork;
import com.xzty.cq.tover.businessmanagement.projectmanagement.common.api.RemoteService;
import com.xzty.cq.tover.businessmanagement.projectmanagement.part.model.apply.RspApplyList;

import java.util.List;

import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * author zzl
 * Created 2018/5/7.
 * explain 申请列表搜索的帮助类
 */

public class SearchHelper {
    private static String info;

    public static void search(String projectId, String applyPerson, String StartTime, String endTime,
                              String applyOrderState, final DataSourse.Callback<List<RspApplyList>> callback) {
        RemoteService service = NetWork.remote(RemoteService.class);
        Subscription subscription = service.getPartList(projectId, StartTime, applyPerson, endTime, applyOrderState)
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<RspModel<List<RspApplyList>>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("TAG", "请求错误" + e.getMessage());
                        callback.onDataNotAvailable("请求错误" + e.getMessage());
                    }

                    @Override
                    public void onNext(RspModel<List<RspApplyList>> listRspModel) {
                        int code = listRspModel.getBackcode();
                        if (code == 0) {
                            callback.onDataNotAvailable("请求错误");
                        } else {
                            List<RspApplyList> mList = listRspModel.getData();
                            callback.onDataLoaded(mList);
                        }
                    }
                });
    }

    public static void  noteApply(String applyId, final DataSourse.Callback callback) {
        RemoteService service = NetWork.remote(RemoteService.class);
        Subscription subscription = service.notaApply(applyId)
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<RspModel<List<RspLogin>>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        callback.onDataNotAvailable("请求错误" + e.getMessage());
                    }

                    @Override
                    public void onNext(RspModel<List<RspLogin>> rspLoginRspModel) {
                        callback.onDataLoaded(rspLoginRspModel.getData());
                    }
                });
    }
}
