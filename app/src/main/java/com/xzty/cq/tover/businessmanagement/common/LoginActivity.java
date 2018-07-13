package com.xzty.cq.tover.businessmanagement.common;

import android.content.Intent;
import android.content.IntentFilter;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.xiaomi.mipush.sdk.MiPushClient;
import com.xzty.cq.tover.businessmanagement.R;
import com.xzty.cq.tover.businessmanagement.common.account.LoginContract;
import com.xzty.cq.tover.businessmanagement.common.account.LoginPresenter;
import com.xzty.cq.tover.businessmanagement.common.data.StaticValue;
import com.xzty.cq.tover.businessmanagement.common.factory.Account;
import com.xzty.cq.tover.businessmanagement.common.factory.ActivityPresenter;
import com.xzty.cq.tover.businessmanagement.common.main.MainActivity;
import com.xzty.cq.tover.businessmanagement.common.mipush.MyPushMessageReceiver;

import butterknife.BindView;
import butterknife.OnClick;

public class LoginActivity extends ActivityPresenter<LoginContract.Presenter> implements LoginContract.View{

    @BindView(R.id.btn_login)
    Button btn_login;
    @BindView(R.id.et_username)
    EditText et_username;
    @BindView(R.id.et_password)
    EditText et_password;


    @Override
    protected int getContentLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    protected LoginContract.Presenter initPresenter() {
        return new LoginPresenter(this);
    }

    @OnClick(R.id.btn_login)
    void login() {
        btn_login.setEnabled(false);
        String username = et_username.getText().toString().trim();
        String password = et_password.getText().toString().trim();
        if (StaticValue.regId != null){
            mPresenter.login(username, password,StaticValue.regId);
        }else {
            MiPushClient.registerPush(this,StaticValue.APP_ID,StaticValue.APP_KEY);
            Log.d("Login error", "未注册regId");
            Toast.makeText(this, "未注册regId", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void loginSuccess() {
        btn_login.setEnabled(true);
        finish();
        this.startActivity(new Intent(this, MainActivity.class));
    }


    @Override
    public void showError(String str) {
        Toast.makeText(this, str, Toast.LENGTH_SHORT).show();
        btn_login.setEnabled(true);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        MyApplication.getInstance().removeActivity(this);
    }
}
