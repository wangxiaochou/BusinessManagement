package com.xzty.cq.tover.businessmanagement.common;

import android.Manifest;
import android.content.Intent;
import android.os.Handler;
import android.text.TextUtils;
import android.widget.Toast;

import com.xzty.cq.tover.businessmanagement.R;
import com.xzty.cq.tover.businessmanagement.common.app.BaseActivity;
import com.xzty.cq.tover.businessmanagement.common.factory.Account;
import com.xzty.cq.tover.businessmanagement.common.main.MainActivity;
import com.xzty.cq.tover.businessmanagement.common.model.ReqLogin;

import java.util.List;

import pub.devrel.easypermissions.EasyPermissions;

/**
 * author zzl
 * Created  2018/5/2
 * explain 启动页 初始化数据
 */

public class LaunchActivity extends BaseActivity implements EasyPermissions.PermissionCallbacks {
    //需要申请的权限
    private String[] perms = new String[]{
            Manifest.permission.INTERNET,
            Manifest.permission.ACCESS_NETWORK_STATE,
            Manifest.permission.ACCESS_WIFI_STATE,
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.CALL_PHONE,
            Manifest.permission.READ_PHONE_STATE,
            Manifest.permission.ACCESS_FINE_LOCATION,
    };

    private Handler delayedHandler = new Handler();

    @Override
    protected int getContentLayoutId() {
        return R.layout.activity_launch;
    }

    @Override
    protected void initData() {
        super.initData();
        //是否存在权限
        if (EasyPermissions.hasPermissions(this, perms)) {
            delayedHandler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    //判断是否登录过
                    isLogin();
                }
            }, 1000);

        } else {
            //不存在,申请
            EasyPermissions.requestPermissions(this, "需要添加的权限", 200,
                    perms);
            Toast.makeText(this, "请添加权限后再进入App", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }


    /**
     * 权限申请成功
     *
     * @param requestCode
     * @param perms
     */
    @Override
    public void onPermissionsGranted(int requestCode, List<String> perms) {
        delayedHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                //判断是否登录过
                isLogin();
            }
        }, 1000);
    }

    /**
     * 权限申请失败
     *
     * @param requestCode
     * @param perms
     */
    @Override
    public void onPermissionsDenied(int requestCode, List<String> perms) {
        Toast.makeText(this, "申请权限失败,请添加权限后再进入App", Toast.LENGTH_SHORT).show();
        finish();
    }

    private void isLogin(){
        Account.load(LaunchActivity.this);
        ReqLogin model = Account.getUserInfo();
          //是否存在账号密码
        if (!TextUtils.isEmpty(model.getUsername()) || !TextUtils.isEmpty(model.getUsername())) {
            finish();
            startActivity(new Intent(LaunchActivity.this, MainActivity.class));
        }else {
            finish();
            startActivity(new Intent(LaunchActivity.this,LoginActivity.class));
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        MyApplication.getInstance().removeActivity(this);
    }
}
