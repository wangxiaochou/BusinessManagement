package com.xzty.cq.tover.businessmanagement.common.data.helper;

import android.util.Log;

import com.xzty.cq.tover.businessmanagement.common.data.DataSourse;
import com.xzty.cq.tover.businessmanagement.common.factory.Account;
import com.xzty.cq.tover.businessmanagement.common.model.ReqLogin;
import com.xzty.cq.tover.businessmanagement.common.model.RspLogin;
import com.xzty.cq.tover.businessmanagement.common.model.RspModel;
import com.xzty.cq.tover.businessmanagement.common.net.NetWork;
import com.xzty.cq.tover.businessmanagement.projectmanagement.common.api.RemoteService;

import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * author zzl
 * Created 2018/5/3.
 * explain 网络请求帮助类
 */

public class AccountHelper {
    /**
     * 登录的调用
     *
     * @param model    登录的Model
     * @param callback 成功与失败的接口回调
     */
    public static void login(final ReqLogin model, final DataSourse.Callback<RspLogin> callback) {
        RemoteService service = NetWork.remote(RemoteService.class);
        Subscription subscription = service.login(model)
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<RspModel<RspLogin>>() {
                    @Override
                    public void onCompleted() {
                        Log.e("TAG", "请求被取消");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("TAG", "请求错误" + e.getMessage());
                        callback.onDataNotAvailable("请求错误" + e.getMessage());
                    }

                    @Override
                    public void onNext(RspModel<RspLogin> rspLoginRspModel) {
                        int code = rspLoginRspModel.getBackcode();
                        if (code == 1) {
                            callback.onDataLoaded(rspLoginRspModel.getData());
                            RspLogin rsp = rspLoginRspModel.getData();
                            Account.login(rsp, model);
                        } else if (code == 0) {
                            callback.onDataNotAvailable("登录失败,请检查账号密码" + rspLoginRspModel.getMessage());
                        }
                    }
                });
    }
}
