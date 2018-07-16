package com.xzty.cq.tover.businessmanagement.common.huawei.push;

import com.huawei.hms.api.HuaweiApiClient;
import com.huawei.hms.support.api.push.HuaweiPush;
import com.xzty.cq.tover.businessmanagement.common.huawei.common.ApiClientMgr;
import com.xzty.cq.tover.businessmanagement.common.huawei.common.BaseApiAgent;
import com.xzty.cq.tover.businessmanagement.common.huawei.common.HMSAgentLog;

/**
 * 打开透传消息开关的接口。
 */
public class EnableReceiveNormalMsgApi extends BaseApiAgent {

    /**
     * 是否打开开关
     */
    boolean enable;

    /**
     * HuaweiApiClient 连接结果回调
     *
     * @param rst    结果码
     * @param client HuaweiApiClient 实例
     */
    @Override
    public void onConnect(int rst, final HuaweiApiClient client) {
        //需要在子线程中执行开关的操作
        new Thread() {
            public void run() {
                if (client == null || !ApiClientMgr.INST.isConnect(client)) {
                    HMSAgentLog.e("client not connted");
                    return;
                }

                // 开启/关闭透传消息
                HuaweiPush.HuaweiPushApi.enableReceiveNormalMsg(client, enable);
            }
        }.start();
    }

    /**
     * 打开/关闭透传消息
     * @param enable 打开/关闭
     */
    public void enableReceiveNormalMsg(boolean enable) {
        this.enable = enable;
        connect();
    }
}
