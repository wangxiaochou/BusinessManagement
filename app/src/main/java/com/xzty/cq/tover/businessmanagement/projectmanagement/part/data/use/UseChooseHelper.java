package com.xzty.cq.tover.businessmanagement.projectmanagement.part.data.use;

import com.alibaba.fastjson.JSON;
import com.google.gson.Gson;
import com.xzty.cq.tover.businessmanagement.common.MyApplication;
import com.xzty.cq.tover.businessmanagement.common.data.DataSourse;
import com.xzty.cq.tover.businessmanagement.common.factory.Account;
import com.xzty.cq.tover.businessmanagement.common.model.RspModel;
import com.xzty.cq.tover.businessmanagement.common.net.NetWork;
import com.xzty.cq.tover.businessmanagement.projectmanagement.common.api.RemoteService;
import com.xzty.cq.tover.businessmanagement.projectmanagement.part.model.use.AllModel;
import com.xzty.cq.tover.businessmanagement.projectmanagement.part.model.use.RspUseChoose;

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
 * Created 2018/6/12.
 * explain
 */

public class UseChooseHelper {
    public static void search(String time, String applyBatches, String outBatches, String partName, String partNo, final DataSourse.Callback<UseChooseModel> callback) {
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
        //申请批次
        String applyBatch = "";
        if (applyBatches.equals("全部")) {
            applyBatch = "";
        } else {
            String[] arryapplyBatch = applyBatches.split("批");
            applyBatch = String.valueOf(arryapplyBatch[0]);
        }
        //发货批次
        String outBatch = "";
        if (outBatches.equals("全部")) {
            outBatch = "";
        } else {
            String[] arryoutBatch = outBatches.split("批");
            outBatch = String.valueOf(arryoutBatch[0]);
        }
        Account.load(MyApplication.getInstance());
        String projectId = Account.getProjectId();
        final Map<String, String> maps = new HashMap<>();
        maps.put("projectId", projectId);
        maps.put("beginCollectTime", times[0] + "");
        maps.put("endCollectTime", times[1] + "");
        maps.put("applyBatch", applyBatch);
        maps.put("outBatch", outBatch);
        maps.put("partName", partName);
        maps.put("partNo", partNo);
        RemoteService service = NetWork.remote(RemoteService.class);
        Subscription subscription = service.queryChoosePart(maps).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<RspModel<List<RspUseChoose>>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                callback.onDataNotAvailable("请求失败" + e.getMessage());
            }

            @Override
            public void onNext(RspModel<List<RspUseChoose>> listRspModel) {
                int code = listRspModel.getBackcode();
                if (code == 1) {
                    List<String> partNameList = new ArrayList<>();
                    List<String> applyBatchList = new ArrayList<>();
                    List<String> outBatchList = new ArrayList<>();
                    List<AllModel> allList = new ArrayList<>();
                    partNameList.add("全部");
                    applyBatchList.add("全部");
                    outBatchList.add("全部");
                    allList = JSON.parseArray(new Gson().toJson(listRspModel.getData()).toString(),
                            AllModel.class);

                    for (AllModel model : allList) {
                        String partname = (model.getPartName() == null || model.getPartName().equals("")) ? "空" : model.getPartName();
                        if (!partNameList.contains(partname)) {
                            partNameList.add(partname);
                        }
                        String applybatch = model.getApplyBatch() + "批";
                        if (!applyBatchList.contains(applybatch)) {
                            applyBatchList.add(applybatch);
                        }
                        String outbatch = model.getOutBatch() + "批";
                        if (!outBatchList.contains(outbatch)) {
                            outBatchList.add(outbatch);
                        }
                    }
                    UseChooseModel model = new UseChooseModel();
                    model.setPartNameList(partNameList);
                    model.setApplyBatchList(applyBatchList);
                    model.setOutBatchList(outBatchList);
                    model.setAllList(allList);
                    callback.onDataLoaded(model);
                } else {
                    callback.onDataNotAvailable("请求错误");

                }
            }
        });
    }

    public static UseChooseItemModel itemClick(List<AllModel> list, int position) {
        if (list.get(position).isCheck) {
            list.get(position).isCheck = false;
        } else {
            list.get(position).isCheck = true;
        }
        List<AllModel> thisTimeSelectedPartTemp = new ArrayList<>();
        for (AllModel model : list) {
            if (model.isCheck) {
                thisTimeSelectedPartTemp.add(model);
            }
        }
        UseChooseItemModel model = new UseChooseItemModel();
        model.setChooseList(thisTimeSelectedPartTemp);
        model.setList(list);
        return model;
    }

    public static UseChooseItemModel chooseAll(List<AllModel> list, List<AllModel> thisTimeSelectedPartTemp, boolean status) {
        UseChooseItemModel model = new UseChooseItemModel();
        if (status) {
            for (AllModel model1 : list) {
                model1.isCheck = true;
            }
            thisTimeSelectedPartTemp = list;
        } else {
            for (AllModel model1 : list) {
                model1.isCheck = false;
            }
            thisTimeSelectedPartTemp.clear();
        }
        model.setList(list);
        model.setChooseList(thisTimeSelectedPartTemp);
        return model;
    }

}
