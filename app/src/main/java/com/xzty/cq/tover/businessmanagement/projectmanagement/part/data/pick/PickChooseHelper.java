package com.xzty.cq.tover.businessmanagement.projectmanagement.part.data.pick;

import com.xzty.cq.tover.businessmanagement.common.data.DataSourse;
import com.xzty.cq.tover.businessmanagement.common.model.RspModel;
import com.xzty.cq.tover.businessmanagement.common.net.NetWork;
import com.xzty.cq.tover.businessmanagement.projectmanagement.common.api.RemoteService;
import com.xzty.cq.tover.businessmanagement.projectmanagement.part.model.pick.RspPickList;
import com.xzty.cq.tover.businessmanagement.projectmanagement.utils.DateUtil;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * author zzl
 * Created 2018/6/12.
 * explain
 */

public class PickChooseHelper {
    public static void getList(String distId, final DataSourse.Callback<PickChooseList> callback) {
        RemoteService service = NetWork.remote(RemoteService.class);
        Subscription subscription = service.getDistOrderPart(distId).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<RspModel<List<RspPickList>>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                callback.onDataNotAvailable("请求失败"+e.getMessage());
            }

            @Override
            public void onNext(RspModel<List<RspPickList>> listRspModel) {
                int code = listRspModel.getBackcode();
                List<String> partBatchList = new ArrayList<>();
                List<String> partNameList = new ArrayList<>();
                PickChooseList model = new PickChooseList();
                if (code == 1) {
                    partBatchList.add("全部");
                    partNameList.add("全部");
                    for (RspPickList part : listRspModel.getData()) {
                        if (!partBatchList.contains(part.getApplyBatch() + "批")) {
                            partBatchList.add(part.getApplyBatch() + "批");
                        }
                        String partName = (part.getPartName() == null || part.getPartName().equals("")) ? "空" : part.getPartName();
                        if (!partNameList.contains(partName)) {
                            partNameList.add(partName);
                        }
                    }
                    model.setPartBatchList(partBatchList);
                    model.setPartNameList(partNameList);
                    model.setListRspModel(listRspModel.getData());
                    callback.onDataLoaded(model);
                } else {
                    callback.onDataNotAvailable("请求错误");
                }
            }
        });
    }


    public static List<RspPickList> search(List<RspPickList> list, String partName, String partNo, String partBatch, String times) {
        if (partName != null && !partName.equals("") && !partName.equals("全部")) {
            List<RspPickList> removePartList = new ArrayList<>();
            for (RspPickList part : list) {
                if (part.getPartName() != null && part.getPartName().equals(partName)) {
                    removePartList.add(part);
                } else if (part.getPartName() == null && partName.equals("空")) {
                    removePartList.add(part);
                }
            }
            list = removePartList;
        }
        //构件编号 partNo
        if (!partNo.equals("")) {
            List<RspPickList> removePartList = new ArrayList<>();
            for (RspPickList part : list) {
                if (part.getPartNo() != null && part.getPartNo().toLowerCase().contains(partNo.toLowerCase())) {
                    removePartList.add(part);
                }
            }
            list = removePartList;
        }
        //批次  partBatch
        if (partBatch != null && !partBatch.equals("") && !partBatch.equals("全部")) {
            List<RspPickList> removePartList = new ArrayList<>();
            for (RspPickList part : list) {
                if (part.getApplyBatch() == Integer.parseInt(partBatch.split("批")[0])) {
                    removePartList.add(part);
                }
            }
            list = removePartList;
        }
        //分配时间
        if (times.length() > 0) {
            String[] timesplit = times.split("~");
            Date date1 = DateUtil.stringToDate(timesplit[0]);
            Date date2 = DateUtil.stringToDate(timesplit[1]);
            boolean flag = false;
            Calendar c2 = Calendar.getInstance();
            if (date1.compareTo(date2) < 0) {
                c2.setTime(date2);
                c2.add(Calendar.DATE, 1);
                date2 = c2.getTime();
            } else if (date1.compareTo(date2) == 0) {
                flag = true;
            } else {
                Date temp = date1;
                date1 = date2;
                c2.setTime(temp);
                c2.add(Calendar.DATE, 1);
                date2 = c2.getTime();
            }
            List<RspPickList> removePartList = new ArrayList<>();
            if (flag) {
                for (RspPickList part : list) {
                    if (new Date(part.getApplyItemExpettime()).compareTo(date1) == 0) {
                        removePartList.add(part);
                    }
                }
            } else {
                for (RspPickList part : list) {
                    if ((new Date(part.getApplyItemExpettime()).compareTo(date1) == 0 || new Date(part.getApplyItemExpettime()).compareTo(date1) > 0) && (new Date(part.getApplyItemExpettime()).compareTo(date2) < 0)) {
                        removePartList.add(part);
                    }
                }
            }
            list = removePartList;
        }
        return list;
    }

    public static PickChooseModel chooseAll(List<RspPickList> list, boolean status, List<RspPickList> chooseList) {
        PickChooseModel model = new PickChooseModel();
        //解决并发异常
        List<RspPickList> removeList = new ArrayList<>();
        //判断checkbox是否是选中状态
        if (!status) {
            //false 清楚所有数据
            for (RspPickList select : list) {
                select.isCheck = false;
            }
            chooseList.clear();
        } else {
            //勾选所有
            for (RspPickList part : list) {
                for (RspPickList teamp : chooseList) {
                    if (teamp.getDistDetailId() == part.getDistDetailId()) {
                        teamp.isCheck = false;
                        removeList.add(part);
                    }
                }
                part.isCheck = true;
                chooseList.add(part);
            }
        }
        //统一移除
        for (int i = 0; i < removeList.size(); i++) {
            chooseList.remove(removeList.get(i));
        }
        int size = 0;
        double partCount = 0;
        for (RspPickList detail : list) {
            if (detail.isCheck) {
                size++;
                partCount += detail.getApplyItemCount();
            }
        }
        model.setChooseList(chooseList);
        model.setList(list);
        model.setSize(size);
        model.setPartCount(partCount);
        return model;
    }

    public static PickChooseModel chooseItem(List<RspPickList> list, List<RspPickList> chooseList, int position) {
        PickChooseModel model = new PickChooseModel();
        //解决并发异常
        List<RspPickList> removeList = new ArrayList<>();
        if (list.get(position).isCheck) {
            list.get(position).isCheck = false;
            removeList.add(list.get(position));
            chooseList.remove(list.get(position));
        } else {
            list.get(position).isCheck = true;
            removeList.add(list.get(position));
            chooseList.add(list.get(position));
        }
        int size = 0;
        double partCount = 0;
        for (RspPickList detail : list) {
            if (detail.isCheck) {
                size++;
                partCount += detail.getApplyItemCount();
            }
        }
        model.setPartCount(partCount);
        model.setSize(size);
        model.setList(list);
        model.setChooseList(chooseList);
        return model;
    }
}
