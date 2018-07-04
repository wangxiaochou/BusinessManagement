package com.xzty.cq.tover.businessmanagement.projectmanagement.tool.data.other.receive;

import com.xzty.cq.tover.businessmanagement.common.MyApplication;
import com.xzty.cq.tover.businessmanagement.common.data.DataSourse;
import com.xzty.cq.tover.businessmanagement.common.factory.Account;
import com.xzty.cq.tover.businessmanagement.common.model.RspLogin;
import com.xzty.cq.tover.businessmanagement.common.model.RspModel;
import com.xzty.cq.tover.businessmanagement.common.net.NetWork;
import com.xzty.cq.tover.businessmanagement.projectmanagement.common.api.RemoteService;
import com.xzty.cq.tover.businessmanagement.projectmanagement.tool.model.other.ReqReceive;
import com.xzty.cq.tover.businessmanagement.projectmanagement.tool.model.other.RspReceive;

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

public class OtherReceiveHelper {
    public static void getData(final DataSourse.Callback callback) {
        Account.load(MyApplication.getInstance());
        String projectId = Account.getProjectId();
        RemoteService service = NetWork.remote(RemoteService.class);
        Subscription subscription = service.oWaitGet(projectId).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<RspModel<List<RspReceive>>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                callback.onDataNotAvailable("请求失败" + e.getMessage());
            }

            @Override
            public void onNext(RspModel<List<RspReceive>> listRspModel) {
                int code = listRspModel.getBackcode();
                if (code == 1) {
                    callback.onDataLoaded(listRspModel.getData());
                } else {
                    callback.onDataNotAvailable("请求错误");
                }
            }
        });
    }

    public static List<RspReceive> itemClick(List<RspReceive> list, int position) {
        if (list.get(position).isChecked) {
            list.get(position).isChecked = false;
        } else {
            list.get(position).isChecked = true;
        }
        return list;
    }

    public static void commit(List<RspReceive> list, String note, final DataSourse.Callback callback) {
        List<Integer> checkList = new ArrayList<>();
        for (RspReceive receve : list) {
            if (receve.isChecked) {
                checkList.add(receve.getToolOutDetailId());
            }
        }
        if (checkList.size() > 0) {
            Account.load(MyApplication.getInstance());
            String projectId = Account.getProjectId();
            String emp = Account.getemployId();
            final ReqReceive reqReceive = new ReqReceive();
            reqReceive.setEplyId(emp);
            reqReceive.setProjectId(projectId);
            reqReceive.setNote(note);
            reqReceive.setDetailIdList(checkList);
            RemoteService service = NetWork.remote(RemoteService.class);
            Subscription subscription = service.oGet(reqReceive).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<RspModel<RspLogin>>() {
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

}
