package com.xzty.cq.tover.businessmanagement.projectmanagement.part.data.income;

import com.alibaba.fastjson.JSON;
import com.google.gson.Gson;
import com.xzty.cq.tover.businessmanagement.common.MyApplication;
import com.xzty.cq.tover.businessmanagement.common.data.DataSourse;
import com.xzty.cq.tover.businessmanagement.common.factory.Account;
import com.xzty.cq.tover.businessmanagement.common.model.RspModel;
import com.xzty.cq.tover.businessmanagement.common.net.NetWork;
import com.xzty.cq.tover.businessmanagement.projectmanagement.common.api.RemoteService;
import com.xzty.cq.tover.businessmanagement.projectmanagement.part.model.Emp;
import com.xzty.cq.tover.businessmanagement.projectmanagement.part.model.deliver.DeliverOrder;
import com.xzty.cq.tover.businessmanagement.projectmanagement.part.model.income.RspReceiveNota;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * author zzl
 * Created 2018/6/12.
 * explain
 */

public class ReceiveOrderHelper {

    public static void search(String applyUserId, String time, String applyOrderState, final DataSourse.Callback<ReceiveOrderModel>callback){
        if (applyOrderState.equals("全部")) {
            applyOrderState = "";
        } else if (applyOrderState.equals("已发货")) {
            applyOrderState = "1";
        } else if (applyOrderState.equals("部分收货")) {
            applyOrderState = "3";
        } else if (applyOrderState.equals("已收货")) {
            applyOrderState = "2";
        }
        String[] times;
        if (time.contains("~")) {
            times = time.split("~");
        } else {
            times = new String[]{"", ""};
        }
        Account.load(MyApplication.getInstance());
        String projectId = Account.getProjectId();
        RemoteService service = NetWork.remote(RemoteService.class);
        Subscription subscription = service.queryReceiveNote(projectId, applyUserId, times[0], times[1], applyOrderState).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<RspModel<List<RspReceiveNota>>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
               callback.onDataNotAvailable("请求失败" + e.getMessage());
            }

            @Override
            public void onNext(RspModel<List<RspReceiveNota>> listRspModel) {
                int code = listRspModel.getBackcode();
                ReceiveOrderModel model = new ReceiveOrderModel();
                if (code == 0) {
                   callback.onDataNotAvailable("请求错误");
                } else {
                    List<DeliverOrder> orderList;
                    List<Emp> emps = new ArrayList<>();
                    emps.add(new Emp("请选择",""));
                    orderList = JSON.parseArray(new Gson().toJson(listRspModel.getData()).toString(),DeliverOrder.class);
                    Set idSet = new HashSet();
                    for (DeliverOrder outOrder : orderList) {
                        if (!idSet.contains(outOrder.getOutUserId())) {
                            idSet.add(outOrder.getOutUserId());
                            emps.add(new Emp(outOrder.getEplyName(), outOrder.getOutUserId()));
                        }

                        String outPicPath = outOrder.getOutPicPath();
                        if (outPicPath != null) {
                            String subPath = outPicPath.substring(0, outPicPath.length() - 1);
                            String[] pathes = subPath.split(",");
                            outOrder.setPathList(Arrays.asList(pathes));
                        }
                    }
                    model.setEmps(emps);
                    model.setOrderList(orderList);
                    callback.onDataLoaded(model);
                }

            }
        });
    }

}
