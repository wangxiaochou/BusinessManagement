package com.xzty.cq.tover.businessmanagement.common.huawei;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;

import com.huawei.hms.support.api.push.PushReceiver;
import com.xzty.cq.tover.businessmanagement.common.factory.Account;

/**
 * author zzl
 * Created 2018/7/9.
 * explain 华为推送处理的receiver
 */

public class HuaweiPushReceiver extends PushReceiver{
    @Override
    public void onToken(Context context, String token, Bundle extras) {
        super.onToken(context, token, extras);
        Log.e("TAG","HuaweiPushReceiver"+token+","+extras);
        Log.e("TAG","token"+token);
        Account.token(token);
    }


    @Override
    public void onPushMsg(Context context, byte[] msg, String token) {
        super.onPushMsg(context, msg, token);
    }
}
