package com.xzty.cq.tover.businessmanagement.projectmanagement.part.data.collect;

import com.xzty.cq.tover.businessmanagement.common.MyApplication;
import com.xzty.cq.tover.businessmanagement.common.data.DataSourse;
import com.xzty.cq.tover.businessmanagement.common.factory.Account;
import com.xzty.cq.tover.businessmanagement.common.model.RspModel;
import com.xzty.cq.tover.businessmanagement.common.net.NetWork;
import com.xzty.cq.tover.businessmanagement.projectmanagement.common.api.RemoteService;
import com.xzty.cq.tover.businessmanagement.projectmanagement.part.model.collect.RspCollect;

import java.util.ArrayList;
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

public class CollectHelper {
    public static void getAllCollect(String partName, String partNo, String applyBatch, String time, final DataSourse.Callback<List<RspCollect>> callback) {
        Account.load(MyApplication.getInstance());
        String projectId = Account.getProjectId();
        if (partName.equals("全部")) {
            partName = "";
        }
        if (applyBatch.equals("全部")) {
            applyBatch = "";
        }
        String[] strings;
        if (time.contains("~")) {
            strings = time.split("~");
        } else {
            strings = new String[]{"", ""};
        }
        Map<String, String> maps = new HashMap<>();
        maps.put("projectId", projectId);
        maps.put("partName", partName);
        maps.put("partNo", partNo);
        maps.put("applyBatch", applyBatch);
        maps.put("beginExpetTime", strings[0]);
        maps.put("endExpetTime", strings[1]);
        RemoteService service = NetWork.remote(RemoteService.class);
        Subscription subscription = service.getCollect(maps).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<RspModel<List<RspCollect>>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                callback.onDataNotAvailable("请求失败" + e.getMessage());
            }

            @Override
            public void onNext(RspModel<List<RspCollect>> listRspModel) {
                int code = listRspModel.getBackcode();
                if (code == 1) {
                    List<String> partNameList = new ArrayList<>();
                    List<String> applybatchList = new ArrayList<>();
                    partNameList.add("全部");
                    applybatchList.add("全部");
                    callback.onDataLoaded(listRspModel.getData());
                } else {
                    callback.onDataNotAvailable("请求错误");
                }
            }
        });
    }

    public static CollectModel chooseAll(List<RspCollect> list, boolean status) {
        List<RspCollect> collects = new ArrayList<>();
        if (status) {
            for (RspCollect collect : list) {
                collect.ischeck = true;
                collects.add(collect);
            }
        } else {
            for (RspCollect collect : list) {
                collect.ischeck = false;
            }
        }
        CollectModel model = new CollectModel();
        model.setChooseList(collects);
        model.setList(list);
        return model;
    }

    public static CollectModel itemChoose(List<RspCollect> list, int position) {
        if (list.get(position).ischeck) {
            list.get(position).ischeck = false;
        } else {
            list.get(position).ischeck = true;
        }
        List<RspCollect> collects = new ArrayList<>();
        for (RspCollect collect : list) {
            if (collect.ischeck) {
                collects.add(collect);
            }
        }
        CollectModel model = new CollectModel();
        model.setList(list);
        model.setChooseList(collects);
        return model;
    }

}
