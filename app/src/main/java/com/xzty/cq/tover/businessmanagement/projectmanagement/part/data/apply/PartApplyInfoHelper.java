package com.xzty.cq.tover.businessmanagement.projectmanagement.part.data.apply;

import com.alibaba.fastjson.JSON;
import com.google.gson.Gson;
import com.xzty.cq.tover.businessmanagement.common.MyApplication;
import com.xzty.cq.tover.businessmanagement.common.data.DataSourse;
import com.xzty.cq.tover.businessmanagement.common.factory.Account;
import com.xzty.cq.tover.businessmanagement.common.model.RspLogin;
import com.xzty.cq.tover.businessmanagement.common.model.RspModel;
import com.xzty.cq.tover.businessmanagement.common.net.NetWork;
import com.xzty.cq.tover.businessmanagement.projectmanagement.common.api.RemoteService;
import com.xzty.cq.tover.businessmanagement.projectmanagement.part.model.apply.FixReqPartList;
import com.xzty.cq.tover.businessmanagement.projectmanagement.part.model.apply.RspPartList;

import java.util.ArrayList;
import java.util.List;

import okhttp3.MultipartBody;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * author zzl
 * Created 2018/6/9.
 * explain
 */

public class PartApplyInfoHelper {

    public static List<RspPartList> selectedAll(List<RspPartList> lists, boolean status) {
        if (status) {
            for (RspPartList part : lists) {
                part.isCheck = true;
            }
        } else {
            for (RspPartList part : lists) {
                part.isCheck = false;
            }
        }
        return lists;
    }


    public static List<RspPartList> checkBoxClick(List<RspPartList> lists, int position) {
        if (lists.get(position).isCheck) {
            lists.get(position).isCheck = false;
        } else {
            lists.get(position).isCheck = true;
        }
        return lists;
    }

    public static List<RspPartList> addNeedCount(List<RspPartList> lists, int position) {
        RspPartList part = lists.get(position);
        double need = part.getNeedCount();
        double total = part.getPartCount();
        if (need < total) {
            need++;
            lists.get(position).setNeedCount(need);
            return lists;
        } else {
            return null;
        }
    }

    public static List<RspPartList> minusNeedCount(List<RspPartList> lists, int position) {
        RspPartList part = lists.get(position);
        double need = lists.get(position).getNeedCount();
        if (need > 1) {
            need--;
            lists.get(position).setNeedCount(need);
            return lists;
        } else {
            return null;
        }
    }

    public static void submit(List<RspPartList> list, String applyNote, final DataSourse.Callback<RspLogin> callback) {
        Account.load(MyApplication.getInstance());
        String projectId = Account.getProjectId();
        String employId = Account.getemployId();
        //转化成String
        List<FixReqPartList> mList = new ArrayList<>();
        String part = JSON.toJSONString(list);
        mList = JSON.parseArray(part, FixReqPartList.class);
        String postList = new Gson().toJson(mList).toString();
        MultipartBody.Builder builder = new MultipartBody.Builder();
        builder.setType(MultipartBody.FORM);
        builder.addFormDataPart("projectId", projectId);
        builder.addFormDataPart("employId", employId);
        builder.addFormDataPart("applyNote", applyNote);
        builder.addFormDataPart("partList", postList);
        MultipartBody body = builder.build();
        RemoteService service = NetWork.remote(RemoteService.class);
        Subscription subscription = service.commitPart(body)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<RspModel<RspLogin>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        callback.onDataNotAvailable("请求失败"+e.getMessage());
                    }

                    @Override
                    public void onNext(RspModel<RspLogin> rspLoginProResult) {
                        int code = rspLoginProResult.getBackcode();
                        if (code == 1) {
                            callback.onDataLoaded(rspLoginProResult.getData());
                        } else {
                            callback.onDataNotAvailable("请求错误");
                        }
                    }
                });
    }
}
