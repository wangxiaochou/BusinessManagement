package com.xzty.cq.tover.businessmanagement.projectmanagement.part.data.apply;

import com.alibaba.fastjson.JSON;
import com.google.gson.Gson;
import com.xzty.cq.tover.businessmanagement.common.data.DataSourse;
import com.xzty.cq.tover.businessmanagement.common.model.RspModel;
import com.xzty.cq.tover.businessmanagement.common.net.NetWork;
import com.xzty.cq.tover.businessmanagement.projectmanagement.common.api.RemoteService;
import com.xzty.cq.tover.businessmanagement.projectmanagement.part.model.Emp;

import java.util.ArrayList;
import java.util.List;

import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * author zzl
 * Created 2018/5/11.
 * explain 获得所有采购人
 */

public class BuyerHelper {
    public static void getBuyer(final DataSourse.Callback callback) {
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

                if (code == 0) {
                    callback.onDataNotAvailable("请求错误");
                } else {
                    List<Emp> temp = new ArrayList<>();
                    temp.add(new Emp("请选择", ""));
                    List<Emp> buyerList = JSON.parseArray(new Gson().toJson(listRspModel.getData()).toString(),
                            Emp.class);
                    for (Emp emp : buyerList) {
                        temp.add(emp);
                    }
                    callback.onDataLoaded(temp);
                }
            }
        });
    }
}
