package com.xzty.cq.tover.businessmanagement.projectmanagement.part.data.pick;

import com.xzty.cq.tover.businessmanagement.common.MyApplication;
import com.xzty.cq.tover.businessmanagement.common.data.DataSourse;
import com.xzty.cq.tover.businessmanagement.common.factory.Account;
import com.xzty.cq.tover.businessmanagement.common.model.RspLogin;
import com.xzty.cq.tover.businessmanagement.common.model.RspModel;
import com.xzty.cq.tover.businessmanagement.common.net.NetWork;
import com.xzty.cq.tover.businessmanagement.projectmanagement.common.api.RemoteService;
import com.xzty.cq.tover.businessmanagement.projectmanagement.part.model.pick.RspPickOrder;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * author zzl
 * Created 2018/6/12.
 * explain
 */

public class PickOrderHelper {
    private static List<RspPickOrder> lists;
    private static String status;

    public static void getPickOrder(String time, String distOrderState, String buyerId, final DataSourse.Callback callback) {
        Account.load(MyApplication.getInstance());
        String projectId = Account.getProjectId();
        final String[] times;
        if (time.contains("~")) {
            times = time.split("~");
        } else {
            times = new String[]{"", ""};
        }
        if (distOrderState.equals("全部")) {
            distOrderState = "";
        } else if (distOrderState.equals("未确认")) {
            distOrderState = "0";
        } else if (distOrderState.equals("已确认")) {
            distOrderState = "1";
        }

        Map<String, String> maps = new HashMap();
        maps.put("projectId", projectId);
        maps.put("beginDistTime", times[0]);
        maps.put("endDistTime", times[1]);
        maps.put("distOrderState", distOrderState);
        maps.put("buyerId", buyerId);
        RemoteService service = NetWork.remote(RemoteService.class);
        Subscription subscription = service.queryPurchaseList(maps).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<RspModel<List<RspPickOrder>>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
             callback.onDataNotAvailable("请求失败"+e.getMessage());

            }

            @Override
            public void onNext(RspModel<List<RspPickOrder>> listRspModel) {
                int code = listRspModel.getBackcode();
                if (code == 1) {
                    callback.onDataLoaded(listRspModel.getData());
                } else {
                    callback.onDataNotAvailable("请求错误");
                }
            }
        });
    }

    public static void notaPick(String distId, final DataSourse.Callback callback) {
        RemoteService service = NetWork.remote(RemoteService.class);
        Subscription subscription = service.affirmPurchase(distId).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<RspModel<List<RspLogin>>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
              callback.onDataNotAvailable("请求错误" + e.getMessage());
            }

            @Override
            public void onNext(RspModel<List<RspLogin>> listRspModel) {
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
