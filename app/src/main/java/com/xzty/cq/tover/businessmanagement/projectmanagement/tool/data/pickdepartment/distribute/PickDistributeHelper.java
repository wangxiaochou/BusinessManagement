package com.xzty.cq.tover.businessmanagement.projectmanagement.tool.data.pickdepartment.distribute;

import android.text.TextUtils;

import com.xzty.cq.tover.businessmanagement.common.MyApplication;
import com.xzty.cq.tover.businessmanagement.common.data.DataSourse;
import com.xzty.cq.tover.businessmanagement.common.factory.Account;
import com.xzty.cq.tover.businessmanagement.common.model.RspLogin;
import com.xzty.cq.tover.businessmanagement.common.model.RspModel;
import com.xzty.cq.tover.businessmanagement.common.net.NetWork;
import com.xzty.cq.tover.businessmanagement.projectmanagement.common.api.RemoteService;
import com.xzty.cq.tover.businessmanagement.projectmanagement.tool.model.pickdepartment.ReqDitribute;
import com.xzty.cq.tover.businessmanagement.projectmanagement.tool.model.pickdepartment.RspAllDistribute;
import com.xzty.cq.tover.businessmanagement.projectmanagement.tool.model.pickdepartment.RspDistribute;
import com.xzty.cq.tover.businessmanagement.projectmanagement.tool.model.pickdepartment.RspUser;

import java.util.ArrayList;
import java.util.List;

import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * author zzl
 * Created 2018/6/13.
 * explain
 */

public class PickDistributeHelper {
    public static void getData(final DataSourse.Callback callback) {
        Account.load(MyApplication.getInstance());
        String projectId = Account.getProjectId();
        RemoteService service = NetWork.remote(RemoteService.class);
        Subscription subscription = service.getToolBDBuyAssignList(projectId).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<RspModel<RspAllDistribute>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                callback.onDataNotAvailable("请求失败" + e.getMessage());
            }

            @Override
            public void onNext(RspModel<RspAllDistribute> listRspModel) {
                int code = listRspModel.getBackcode();
                if (code == 1) {
                callback.onDataLoaded(listRspModel.getData());
                } else {
                    callback.onDataNotAvailable("请求错误");
                }
            }
        });
    }

    public static List<RspDistribute> itemClick(List<RspDistribute> list, int position){
        if (list.get(position).isChecked) {
            list.get(position).isChecked = false;
        } else {
            list.get(position).isChecked = true;
        }
        return list;
    }

    public static void submit(List<RspDistribute> list, String note, RspUser chooseEmp, final DataSourse.Callback callback){
        List<Integer> checkList = new ArrayList<>();
        for (RspDistribute distribute : list) {
            if (distribute.isChecked) {
                checkList.add(distribute.getToolApplyDetailId());
            }
        }

        if (checkList.size() > 0 && !TextUtils.isEmpty(chooseEmp.getEplyNum())) {
            Account.load(MyApplication.getInstance());
            String projectId = Account.getProjectId();
            String EmpId = Account.getemployId();
            ReqDitribute ditribute = new ReqDitribute();
            ditribute.setNote(note);
            ditribute.setProjectId(projectId);
            ditribute.setBuyerEplyNum(chooseEmp.getEplyNum());
            ditribute.setEplyNum(EmpId);
            ditribute.setDetailIdList(checkList);
            RemoteService service = NetWork.remote(RemoteService.class);
            Subscription subscription = service.bdSubmitToolBuyAssign(ditribute).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<RspModel<RspLogin>>() {
                @Override
                public void onCompleted() {

                }

                @Override
                public void onError(Throwable e) {
                    callback.onDataNotAvailable("请求失败" + e.getMessage());
                }

                @Override
                public void onNext(RspModel<RspLogin> rspLoginRspModel) {
                    int code = rspLoginRspModel.getBackcode();
                    if (code == 1) {
                        callback.onDataLoaded(rspLoginRspModel.getData());
                    } else {
                        callback.onDataNotAvailable("请求错误");
                    }
                }
            });
        } else {
            callback.onDataNotAvailable("请至少选择一种机具或者一个采购人");
        }

    }

}
