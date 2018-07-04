package com.xzty.cq.tover.businessmanagement.projectmanagement.tool.data.other.back;

import com.xzty.cq.tover.businessmanagement.common.MyApplication;
import com.xzty.cq.tover.businessmanagement.common.data.DataSourse;
import com.xzty.cq.tover.businessmanagement.common.factory.Account;
import com.xzty.cq.tover.businessmanagement.common.model.RspLogin;
import com.xzty.cq.tover.businessmanagement.common.model.RspModel;
import com.xzty.cq.tover.businessmanagement.common.net.NetWork;
import com.xzty.cq.tover.businessmanagement.projectmanagement.common.api.RemoteService;
import com.xzty.cq.tover.businessmanagement.projectmanagement.tool.model.other.ReqBackApply;
import com.xzty.cq.tover.businessmanagement.projectmanagement.tool.model.other.RspBackApply;

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

public class OtherBackApplyHelper {
    public static void getData(final DataSourse.Callback callback) {
        Account.load(MyApplication.getInstance());
        String id = Account.getProjectId();
        RemoteService service = NetWork.remote(RemoteService.class);
        Subscription subscription = service.oGetBackList(id).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<RspModel<List<RspBackApply>>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                callback.onDataNotAvailable("请求失败" + e.getMessage());
            }

            @Override
            public void onNext(RspModel<List<RspBackApply>> listRspModel) {
                int code = listRspModel.getBackcode();
                if (code == 1) {
                    callback.onDataLoaded(listRspModel.getData());
                } else {
                    callback.onDataNotAvailable("请求错误");
                }
            }
        });
    }

    public static List<RspBackApply> itemClick(List<RspBackApply> list, int position) {
        if (list.get(position).isChecked) {
            list.get(position).isChecked = false;
        } else {
            list.get(position).isChecked = true;
        }
        return list;
    }

    public static void apply(List<RspBackApply> list, String note, List<Integer> applyList, final DataSourse.Callback callback) {
        Account.load(MyApplication.getInstance());
        String projectId = Account.getProjectId();
        String emp = Account.getemployId();
        ReqBackApply apply = new ReqBackApply();
        apply.setDetailIdList(applyList);
        apply.setProjectId(projectId);
        apply.setEplyId(emp);
        apply.setNote(note);
        RemoteService service = NetWork.remote(RemoteService.class);
        Subscription subscription = service.onBack(apply).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<RspModel<RspLogin>>() {
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
    }
}
