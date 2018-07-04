package com.xzty.cq.tover.businessmanagement.projectmanagement.tool.data.pickdepartment.distribute;

import com.xzty.cq.tover.businessmanagement.common.data.DataSourse;
import com.xzty.cq.tover.businessmanagement.common.model.RspLogin;
import com.xzty.cq.tover.businessmanagement.common.model.RspModel;
import com.xzty.cq.tover.businessmanagement.common.net.NetWork;
import com.xzty.cq.tover.businessmanagement.projectmanagement.common.api.RemoteService;
import com.xzty.cq.tover.businessmanagement.projectmanagement.tool.model.pickdepartment.ReqIn;
import com.xzty.cq.tover.businessmanagement.projectmanagement.tool.model.pickdepartment.RspDistributeOrderDetails;

import java.util.ArrayList;
import java.util.List;

import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * author zzl
 * Created 2018/6/13.
 * explain
 */

public class DistributeOrderDetailsHelper {
    public static void getData(final int toolDistId, int status, final DataSourse.Callback callback) {
        if (status == 0) {
            //确认
            RemoteService service = NetWork.remote(RemoteService.class);
            Subscription subscription = service.bdToolBuyAssignConfirm(toolDistId).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<RspModel<RspLogin>>() {
                @Override
                public void onCompleted() {

                }

                @Override
                public void onError(Throwable e) {
                    callback.onDataNotAvailable("请求失败" + e.getMessage());
                }

                @Override
                public void onNext(RspModel<RspLogin> rspLoginRspModel) {
                    int code = rspLoginRspModel.getBackcode();
                    if (code == 1) {
                        date(toolDistId, callback);
                    } else {
                        callback.onDataNotAvailable("请求错误");
                    }
                }
            });
        } else {
            date(toolDistId, callback);
        }
    }

    private static void date(int toolDistId, final DataSourse.Callback callback) {
        RemoteService service = NetWork.remote(RemoteService.class);
        Subscription subscription = service.getBDToolBuyAssignDetail(toolDistId).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<RspModel<List<RspDistributeOrderDetails>>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                callback.onDataNotAvailable("请求失败" + e.getMessage());
            }

            @Override
            public void onNext(RspModel<List<RspDistributeOrderDetails>> listRspModel) {
                int code = listRspModel.getBackcode();
                if (code == 1) {
                    callback.onDataLoaded(listRspModel.getData());
                } else {
                    callback.onDataNotAvailable("请求错误");
                }
            }
        });
    }

    public static void confirm(int toolDistId, List<RspDistributeOrderDetails> list, final DataSourse.Callback callback) {
        List<Integer> checkList = new ArrayList<>();
        for (RspDistributeOrderDetails details : list) {
            if (details.isChecked) {
                checkList.add(details.getToolDistDetailId());
            }
        }
        if (checkList.size() > 0) {
            ReqIn in = new ReqIn();
            in.setDistId(toolDistId);
            in.setDistDetailIdList(checkList);
            RemoteService service = NetWork.remote(RemoteService.class);
            Subscription subscription = service.bdInWaitOut(in).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<RspModel<RspLogin>>() {
                @Override
                public void onCompleted() {

                }

                @Override
                public void onError(Throwable e) {
                    callback.onDataNotAvailable("请求失败" + e.getMessage());
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
        } else {
            callback.onDataNotAvailable("请至少选择一种机具");
        }
    }

    public static List<RspDistributeOrderDetails> itemClick(List<RspDistributeOrderDetails> list, int position){
        if (list.get(position).isChecked) {
            list.get(position).isChecked = false;
        } else {
            list.get(position).isChecked = true;
        }
        return list;
    }

}
