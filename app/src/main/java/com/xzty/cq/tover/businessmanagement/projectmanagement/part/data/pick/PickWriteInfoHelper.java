package com.xzty.cq.tover.businessmanagement.projectmanagement.part.data.pick;

import com.alibaba.fastjson.JSON;
import com.xzty.cq.tover.businessmanagement.common.MyApplication;
import com.xzty.cq.tover.businessmanagement.common.data.DataSourse;
import com.xzty.cq.tover.businessmanagement.common.factory.Account;
import com.xzty.cq.tover.businessmanagement.common.model.RspLogin;
import com.xzty.cq.tover.businessmanagement.common.model.RspModel;
import com.xzty.cq.tover.businessmanagement.common.net.NetWork;
import com.xzty.cq.tover.businessmanagement.projectmanagement.common.api.RemoteService;
import com.xzty.cq.tover.businessmanagement.projectmanagement.part.model.pick.RspPickList;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * author zzl
 * Created 2018/6/12.
 * explain
 */

public class PickWriteInfoHelper {
    public static void commit(String firm, String contractNo, String expectOutTime, String outNote, String distId, String partList, final DataSourse.Callback<RspLogin> callback) {
        RemoteService service = NetWork.remote(RemoteService.class);
        final List<RspPickList> list = JSON.parseArray(partList, RspPickList.class);
        Account.load(MyApplication.getInstance());
        String projectId = Account.getProjectId();
        String outUserId = Account.getemployId();
        RequestBody body1 = RequestBody.create(MediaType.parse("multipart/form-data"),
                projectId);
        RequestBody body2 = RequestBody.create(MediaType.parse("multipart/form-data"),
                outUserId);
        RequestBody body3 = RequestBody.create(MediaType.parse("multipart/form-data"),
                distId);
        RequestBody body4 = RequestBody.create(MediaType.parse("multipart/form-data"),
                partList);
        RequestBody body5 = RequestBody.create(MediaType.parse("multipart/form-data"),
                firm);
        RequestBody body6 = RequestBody.create(MediaType.parse("multipart/form-data"),
                contractNo);
        RequestBody body7 = RequestBody.create(MediaType.parse("multipart/form-data"),
                expectOutTime);
        RequestBody body8 = RequestBody.create(MediaType.parse("multipart/form-data"),
                outNote);
        Map<String, RequestBody> maps = new HashMap<>();
        maps.put("projectId", body1);
        maps.put("outUserId", body2);
        maps.put("distId", body3);
        maps.put("partList", body4);
        maps.put("firm", body5);
        maps.put("contractNo", body6);
        maps.put("expectOutTime", body7);
        maps.put("outNote", body8);
        Subscription subscription = service.overPurchase(maps).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<RspModel<RspLogin>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                callback.onDataNotAvailable("请求失败" + e.getMessage());
            }

            @Override
            public void onNext(RspModel<RspLogin> listRspModel) {
                int code = listRspModel.getBackcode();
                if (code == 1) {
                    callback.onDataLoaded(listRspModel.getData());
                } else {
                    callback.onDataNotAvailable("请求错误");
                }
            }
        });
    }

    public static List<RspPickList> itemClick(List<RspPickList> mList, int position) {
        if (mList.get(position).isCheck) {
            mList.get(position).isCheck = false;
        } else {
            mList.get(position).isCheck = true;
        }
        return mList;
    }

    public static List<RspPickList> chooseAll(List<RspPickList> mList, boolean status) {
        if (status) {
            for (RspPickList partList : mList) {
                if (!partList.isCheck) {
                    partList.isCheck = true;
                }
            }
        } else {
            for (RspPickList partList : mList) {
                if (partList.isCheck) {
                    partList.isCheck = false;
                }
            }
        }
        return mList;
    }

    public static List<RspPickList> writePrice(List<RspPickList> mList, String price) {
        //对一个item输入的text做一个处理
        //存储是否是第一个
        boolean isFirst = true;
        for (RspPickList pickList : mList) {
            if (pickList.isCheck) {
                if(isFirst){
                    int num = 0;
                    if (price.length() >= 4) {
                        String[] p = price.split("");
                        String link = "";
                        //从1开始是为了去除spili剪切多了一个[]，索引为0
                        for (int i = p.length - 1; i > 0; i--) {
                            if (p[i] != "") {
                                if (num != 0 && num % 3 == 0) {
                                    link = p[i] + "," + link;
                                } else {
                                    link = p[i] + link;
                                }
                                num++;
                            }
                        }
                        pickList.setUnitPrice(link);
                    }
                    isFirst = false;
                }else{
                    pickList.setUnitPrice(price);
                }

            }
        }
        return mList;
    }
}
