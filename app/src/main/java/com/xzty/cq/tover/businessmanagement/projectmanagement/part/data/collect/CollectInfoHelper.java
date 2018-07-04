package com.xzty.cq.tover.businessmanagement.projectmanagement.part.data.collect;

import com.alibaba.fastjson.JSON;
import com.google.gson.Gson;
import com.xzty.cq.tover.businessmanagement.common.MyApplication;
import com.xzty.cq.tover.businessmanagement.common.data.DataSourse;
import com.xzty.cq.tover.businessmanagement.common.factory.Account;
import com.xzty.cq.tover.businessmanagement.common.model.RspLogin;
import com.xzty.cq.tover.businessmanagement.common.model.RspModel;
import com.xzty.cq.tover.businessmanagement.common.net.NetWork;
import com.xzty.cq.tover.businessmanagement.projectmanagement.common.api.RemoteService;
import com.xzty.cq.tover.businessmanagement.projectmanagement.part.model.Emp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * author zzl
 * Created 2018/6/11.
 * explain
 */

public class CollectInfoHelper {

    public static void getAllBuyer(final DataSourse.Callback callback) {

        RemoteService service = NetWork.remote(RemoteService.class);
        Subscription subscription = service.queryAllBuyer().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<RspModel<List<Emp>>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                callback.onDataNotAvailable("请求失败" + e.getMessage());
            }

            @Override
            public void onNext(RspModel<List<Emp>> listRspModel) {
                int code = listRspModel.getBackcode();
                List<Emp> emps;
                if (code == 1) {
                    emps = new ArrayList<>();
                    emps.add(new Emp("请选择", ""));
                    List<Emp> buyerList = JSON.parseArray(new Gson().toJson(listRspModel.getData()).toString(),
                            Emp.class);
                    for (Emp emp : buyerList) {
                        emps.add(emp);
                    }
                    callback.onDataLoaded(emps);
                } else {
                    callback.onDataNotAvailable("请求错误");
                }
            }
        });

    }

    public static void collect(String employId, String remake, String choose, final DataSourse.Callback<RspLogin> callback) {

        RemoteService service = NetWork.remote(RemoteService.class);
        Account.load(MyApplication.getInstance());
        String projectId = Account.getProjectId();
        String distAssignerId = Account.getemployId();
        RequestBody body1 = RequestBody.create(MediaType.parse("multipart/form-data"),
                distAssignerId);
        RequestBody body2 = RequestBody.create(MediaType.parse("multipart/form-data"),
                employId);
        RequestBody body3 = RequestBody.create(MediaType.parse("multipart/form-data"),
                projectId);
        RequestBody body4 = RequestBody.create(MediaType.parse("multipart/form-data"),
                remake);
        RequestBody body5 = RequestBody.create(MediaType.parse("multipart/form-data"), choose);
        Map<String, RequestBody> maps = new HashMap<>();
        maps.put("distAssignerId", body1);
        maps.put("distBuyerId", body2);
        maps.put("projectId", body3);
        maps.put("distNote", body4);
        maps.put("partList", body5);
        Subscription subscription = service.collectSave(maps).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<RspModel<RspLogin>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                callback.onDataNotAvailable("请求失败" + e.getMessage());
            }

            @Override
            public void onNext(RspModel<RspLogin> listRspModel) {
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
