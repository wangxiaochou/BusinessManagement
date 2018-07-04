package com.xzty.cq.tover.businessmanagement.common.account;

import android.text.TextUtils;

import com.xzty.cq.tover.businessmanagement.common.data.DataSourse;
import com.xzty.cq.tover.businessmanagement.common.data.helper.AccountHelper;
import com.xzty.cq.tover.businessmanagement.common.factory.BasePresenter;
import com.xzty.cq.tover.businessmanagement.common.model.ReqLogin;
import com.xzty.cq.tover.businessmanagement.common.model.RspLogin;

/**
 * author zzl
 * Created 2018/5/3.
 * explain 登录逻辑的实现
 */

public class LoginPresenter extends BasePresenter<LoginContract.View>
        implements LoginContract.Presenter, DataSourse.Callback<RspLogin>{

    public LoginPresenter(LoginContract.View view) {
        super(view);
    }

    @Override
    public void login(String username, String password) {
        start();
        final LoginContract.View view = getView();
        if(TextUtils.isEmpty(username)||TextUtils.isEmpty(password)){
            view.showError("账号密码不能为空");
        }else{
            ReqLogin login  = new ReqLogin(username,password);
            AccountHelper.login(login,this);
        }
    }

    @Override
    public void onDataLoaded(RspLogin rspLogin) {
        LoginContract.View view = getView();
        view.loginSuccess();
    }

    @Override
    public void onDataNotAvailable(String strRes) {
        LoginContract.View view = getView();
        view.showError(strRes);
    }
}
