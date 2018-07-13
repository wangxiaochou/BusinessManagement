package com.xzty.cq.tover.businessmanagement.common.account;

import android.text.TextUtils;

import com.xzty.cq.tover.businessmanagement.common.data.DataSourse;
import com.xzty.cq.tover.businessmanagement.common.data.StaticValue;
import com.xzty.cq.tover.businessmanagement.common.data.helper.AccountHelper;
import com.xzty.cq.tover.businessmanagement.common.factory.BasePresenter;
import com.xzty.cq.tover.businessmanagement.common.mipush.MyPushMessageReceiver;
import com.xzty.cq.tover.businessmanagement.common.model.ReqLogin;
import com.xzty.cq.tover.businessmanagement.common.model.RspLogin;
import com.xzty.cq.tover.businessmanagement.common.utils.Rom;

/**
 * author zzl
 * Created 2018/5/3.
 * explain 登录逻辑的实现
 */

public class LoginPresenter extends BasePresenter<LoginContract.View>
        implements LoginContract.Presenter, DataSourse.Callback<RspLogin>{
    private int type;  //登录参数，手机类型
    public LoginPresenter(LoginContract.View view) {
        super(view);
    }

    @Override
    public void login(String username, String password, String regId) {
        start();
        final LoginContract.View view = getView();
        if(TextUtils.isEmpty(username)||TextUtils.isEmpty(password)){
            view.showError("账号密码不能为空");
        }else{
            if (Rom.isEmui()) {    //华为手机类型
                type = 2;
                StaticValue.token = "123";
            } else if (Rom.isMiui()) {   //小米手机类型
                type = 1;
                StaticValue.token = null;
            } else {                      //其它手机类型
                type = 1;
                StaticValue.token = null;
            }
            ReqLogin login  = new ReqLogin(username,password,type,StaticValue.token,regId);
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
