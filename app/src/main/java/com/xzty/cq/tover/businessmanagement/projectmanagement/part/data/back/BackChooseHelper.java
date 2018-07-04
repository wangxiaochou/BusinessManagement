package com.xzty.cq.tover.businessmanagement.projectmanagement.part.data.back;

import com.xzty.cq.tover.businessmanagement.common.MyApplication;
import com.xzty.cq.tover.businessmanagement.common.data.DataSourse;
import com.xzty.cq.tover.businessmanagement.common.factory.Account;
import com.xzty.cq.tover.businessmanagement.common.model.RspModel;
import com.xzty.cq.tover.businessmanagement.common.net.NetWork;
import com.xzty.cq.tover.businessmanagement.projectmanagement.common.api.RemoteService;
import com.xzty.cq.tover.businessmanagement.projectmanagement.part.model.back.BackPart;
import com.xzty.cq.tover.businessmanagement.projectmanagement.part.model.back.RspChooseBack;

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

public class BackChooseHelper {
    public static void search(String time, String pickBatch, String partName, String partNo, final DataSourse.Callback<List<RspChooseBack>> callback) {
        String[] times;
        if (time.contains("~")) {
            times = time.split("~");
        } else {
            times = new String[]{"", ""};
        }
        //构件名称
        if (partName.equals("全部")) {
            partName = "";
        }
        //领用批次
        if (pickBatch.equals("全部")) {
            pickBatch = "";
        } else {
            String[] applyBatches = pickBatch.split("批");
            pickBatch = String.valueOf(applyBatches[0]);
        }
        Account.load(MyApplication.getInstance());
        String projectId = Account.getProjectId();
        Map<String, String> maps = new HashMap();
        maps.put("projectId", projectId);
        maps.put("beginPickTime", times[0]);
        maps.put("endPickTime", times[1]);
        maps.put("pickBatch", pickBatch);
        maps.put("partName", partName);
        maps.put("partNo", partNo);
        RemoteService service = NetWork.remote(RemoteService.class);
        Subscription subscription = service.backList(maps).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<RspModel<List<RspChooseBack>>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                callback.onDataNotAvailable("请求失败" + e.getMessage());
            }

            @Override
            public void onNext(RspModel<List<RspChooseBack>> listRspModel) {
                int code = listRspModel.getBackcode();
                if (code == 1) {
                    callback.onDataLoaded(listRspModel.getData());
                } else {
                    callback.onDataNotAvailable("请求错误");
                }
            }
        });
    }

    public static List<BackPart> chooseAll(List<BackPart> list, boolean status) {
        if (!status) {
            for (BackPart back : list) {
                back.isCheck = false;
            }
        } else {
            for (BackPart back : list) {
                back.isCheck = true;
            }
        }
        return list;
    }

    public static List<BackPart> itemClick(List<BackPart> list, int position) {
        if (list.get(position).isCheck) {
            list.get(position).isCheck = false;
        } else {
            list.get(position).isCheck = true;
        }
        return list;
    }
}
