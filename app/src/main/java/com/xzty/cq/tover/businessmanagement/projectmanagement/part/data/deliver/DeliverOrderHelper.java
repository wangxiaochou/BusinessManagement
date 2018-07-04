package com.xzty.cq.tover.businessmanagement.projectmanagement.part.data.deliver;

import android.text.TextUtils;

import com.xzty.cq.tover.businessmanagement.common.MyApplication;
import com.xzty.cq.tover.businessmanagement.common.data.DataSourse;
import com.xzty.cq.tover.businessmanagement.common.factory.Account;
import com.xzty.cq.tover.businessmanagement.common.model.RspModel;
import com.xzty.cq.tover.businessmanagement.common.net.NetWork;
import com.xzty.cq.tover.businessmanagement.projectmanagement.common.api.RemoteService;
import com.xzty.cq.tover.businessmanagement.projectmanagement.part.model.deliver.RspPurchase;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * author zzl
 * Created 2018/6/11.
 * explain
 */

public class DeliverOrderHelper {
    public static void getPurchas(String applyOrderState, String time, String firm, String contractNo, String outUserId, final DataSourse.Callback callback) {
        Account.load(MyApplication.getInstance());
        String projectId = Account.getProjectId();
        if (applyOrderState.equals("全部")) {
            applyOrderState = "";
        } else if (applyOrderState.equals("未发货")) {
            applyOrderState = "0";
        } else if (applyOrderState.equals("已发货")) {
            applyOrderState = "1";
        } else if (applyOrderState.equals("已收获")) {
            applyOrderState = "2";
        }
        String[] times;
        if (time.contains("~")) {
            times = time.split("~");
        } else {
            times = new String[]{"", ""};
        }
        Map<String, String> maps = new HashMap<>();
        maps.put("projectId", projectId);
        maps.put("beginCreateTime", times[0]);
        maps.put("endCreateTime", times[1]);
        maps.put("firm", firm);
        maps.put("contractNo", contractNo);
        maps.put("isComfirm", applyOrderState);
        if (!TextUtils.isEmpty(outUserId)) {
            maps.put("outUserId", outUserId);
        } else {
            maps.put("outUserId", Account.getemployId() + "");
        }
        RemoteService service = NetWork.remote(RemoteService.class);
        Subscription subscription = service.getPurchaseList(maps).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<RspModel<List<RspPurchase>>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                callback.onDataNotAvailable("请求失败"+e.getMessage());
            }

            @Override
            public void onNext(RspModel<List<RspPurchase>> listRspModel) {
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
