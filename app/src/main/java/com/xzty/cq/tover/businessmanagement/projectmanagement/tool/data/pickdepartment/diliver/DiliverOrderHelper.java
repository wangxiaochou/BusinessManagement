package com.xzty.cq.tover.businessmanagement.projectmanagement.tool.data.pickdepartment.diliver;

import android.text.TextUtils;

import com.xzty.cq.tover.businessmanagement.common.MyApplication;
import com.xzty.cq.tover.businessmanagement.common.data.DataSourse;
import com.xzty.cq.tover.businessmanagement.common.factory.Account;
import com.xzty.cq.tover.businessmanagement.common.model.RspLogin;
import com.xzty.cq.tover.businessmanagement.common.model.RspModel;
import com.xzty.cq.tover.businessmanagement.common.net.NetWork;
import com.xzty.cq.tover.businessmanagement.projectmanagement.common.api.RemoteService;
import com.xzty.cq.tover.businessmanagement.projectmanagement.tool.model.pickdepartment.OutToolId;
import com.xzty.cq.tover.businessmanagement.projectmanagement.tool.model.pickdepartment.ReqDiliverOrder;
import com.xzty.cq.tover.businessmanagement.projectmanagement.tool.model.pickdepartment.RspDiliverOrder;

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

public class DiliverOrderHelper {
    public static void getData(final DataSourse.Callback callback) {
        Account.load(MyApplication.getInstance());
        String projectId = Account.getProjectId();
        RemoteService service = NetWork.remote(RemoteService.class);
        Subscription subscription = service.getToolBDOutList(projectId).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<RspModel<List<RspDiliverOrder>>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                callback.onDataNotAvailable("请求失败" + e.getMessage());
            }

            @Override
            public void onNext(RspModel<List<RspDiliverOrder>> listRspModel) {
                int code = listRspModel.getBackcode();
                if (code == 1) {
                    callback.onDataLoaded(listRspModel.getData());
                } else {
                    callback.onDataNotAvailable("请求错误");
                }
            }
        });
    }

    public static List<RspDiliverOrder> itemClick(List<RspDiliverOrder> list, int position) {
        if (list.get(position).isChecke) {
            list.get(position).isChecke = false;
        } else {
            list.get(position).isChecke = true;
        }
        return list;
    }

    public static void affirm(List<RspDiliverOrder> list, String firm, String contractNumber, String expectOutTime, String note, final DataSourse.Callback callback){
        List<RspDiliverOrder> checkList = new ArrayList<>();
        List<OutToolId> outList = new ArrayList<>();
        for (RspDiliverOrder order : list) {
            if (order.isChecke) {
                OutToolId out = new OutToolId();
                out.setTOOL_APPLY_DETAIL_ID(order.getToolApplyDetailId());
                out.setTOOL_APPLY_ID(order.getToolApplyId());
                out.setTOOL_ID(order.getToolId());
                outList.add(out);
            }
        }
        if (outList.size() > 0&& !TextUtils.isEmpty(expectOutTime)) {
            ReqDiliverOrder order = new ReqDiliverOrder();
            Account.load(MyApplication.getInstance());
            String projectId = Account.getProjectId();
            String empId = Account.getemployId();
            order.setProjectId(projectId);
            order.setEmployId(empId);
            order.setContractNumber(contractNumber);
            order.setExpectOutTime(expectOutTime);
            order.setNote(note);
            order.setOutList(outList);
            order.setFirm(firm);
            RemoteService service = NetWork.remote(RemoteService.class);
            Subscription subscription = service.sendToolBDOut(order).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<RspModel<RspLogin>>() {
                @Override
                public void onCompleted() {

                }

                @Override
                public void onError(Throwable e) {
                   callback.onDataNotAvailable("请求失败"+e.getMessage());
                }

                @Override
                public void onNext(RspModel<RspLogin> rspLoginRspModel) {
                    int code = rspLoginRspModel.getBackcode();
                    if(code == 1){
                      callback.onDataLoaded(rspLoginRspModel.getData());
                    }else{
                      callback.onDataNotAvailable("请求错误");
                    }
                }
            });
        } else {
            callback.onDataNotAvailable("请至少选择一种机具,请必须填写日期");
        }
    }

}
