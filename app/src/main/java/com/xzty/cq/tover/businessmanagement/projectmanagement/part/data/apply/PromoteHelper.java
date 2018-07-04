package com.xzty.cq.tover.businessmanagement.projectmanagement.part.data.apply;

import com.google.gson.Gson;
import com.xzty.cq.tover.businessmanagement.common.data.DataSourse;
import com.xzty.cq.tover.businessmanagement.common.model.RspLogin;
import com.xzty.cq.tover.businessmanagement.common.model.RspModel;
import com.xzty.cq.tover.businessmanagement.common.net.NetWork;
import com.xzty.cq.tover.businessmanagement.projectmanagement.common.api.RemoteService;
import com.xzty.cq.tover.businessmanagement.projectmanagement.part.model.apply.RspPartList;
import com.xzty.cq.tover.businessmanagement.projectmanagement.part.model.promotenum.ReqPromote;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * author zzl
 * Created 2018/6/11.
 * explain
 */

public class PromoteHelper {
    public static List<RspPartList> minusClick(List<RspPartList> lists, int position) {
        double num = lists.get(position).getNeedCount();
        if (num > 0) {
            num--;
            lists.get(position).setNeedCount(num);
            return lists;
        } else {
            return null;
        }
    }

    public static List<RspPartList> addClick(List<RspPartList> lists, int position) {
        double num = lists.get(position).getNeedCount();
        num++;
        lists.get(position).setNeedCount(num);
        return lists;
    }

    public static List<RspPartList> itemClick(List<RspPartList> lists, int position) {
        if (lists.get(position).isCheck) {
            lists.get(position).isCheck = false;
        } else {
            lists.get(position).isCheck = true;
        }
        return lists;
    }

    public static List<RspPartList> chooseAllClick(List<RspPartList> lists, boolean status) {
        if (status) {
            //选中状态 变为未选中
            for (int i = 0; i < lists.size(); i++) {
                if (!lists.get(i).isCheck) {
                    lists.get(i).isCheck = true;
                }
            }
        } else {
            for (int i = 0; i < lists.size(); i++) {
                if (lists.get(i).isCheck = true) {
                    lists.get(i).isCheck = false;
                }
            }
        }
        return lists;
    }

    public static void  submit(List<RspPartList> lists, final DataSourse.Callback callback) {
        List<ReqPromote> promotes = new ArrayList<>();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        for (RspPartList partList : lists) {
            if (partList.isCheck) {
                if (partList.getNeedCount() > 0 && partList.getApplyItemExpettime() != null) {
                    ReqPromote promote = new ReqPromote();
                    promote.setInfoTime(format.format(partList.getApplyItemExpettime()));
                    promote.setPartId(String.valueOf(partList.getPartId()));
                    promote.setNeedCount(String.valueOf(partList.getNeedCount()));
                    promotes.add(promote);
                } else {
                    callback.onDataNotAvailable("请填写数量和时间");
                }
            }
        }
        if (promotes.size() > 0) {
            RemoteService service = NetWork.remote(RemoteService.class);
            String list = new Gson().toJson(promotes).toString();
            Subscription subscription = service.promoteList(list).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<RspModel<RspLogin>>() {
                @Override
                public void onCompleted() {

                }

                @Override
                public void onError(Throwable e) {
                    callback.onDataNotAvailable("请求失败"+e.getMessage());
                }

                @Override
                public void onNext(RspModel<RspLogin> rspLoginRspModel) {
                    int code = rspLoginRspModel.getBackcode();
                    if (code == 1) {
                        callback.onDataLoaded(rspLoginRspModel.getData());
                    } else {
                        callback.onDataNotAvailable("请求错误");
                    }
                }
            });
        }
    }
}
