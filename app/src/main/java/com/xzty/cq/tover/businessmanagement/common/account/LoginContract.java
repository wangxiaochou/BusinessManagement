package com.xzty.cq.tover.businessmanagement.common.account;

import com.xzty.cq.tover.businessmanagement.common.factory.BaseContract;

/**
 * author zzl
 * Created 2018/5/3.
 * explain 登录契约
 */

public interface LoginContract {

    interface View extends BaseContract.View<Presenter> {
        //登录成功
        void loginSuccess();
    }

    interface Presenter extends BaseContract.Presenter {
        //发起一个登录
        void login(String username, String password);
    }
}
