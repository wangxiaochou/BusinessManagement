package com.xzty.cq.tover.businessmanagement.projectmanagement.tool.data.pickdepartment.back;

import android.widget.Toast;

import com.xzty.cq.tover.businessmanagement.common.MyApplication;
import com.xzty.cq.tover.businessmanagement.common.data.DataSourse;
import com.xzty.cq.tover.businessmanagement.common.model.RspLogin;
import com.xzty.cq.tover.businessmanagement.common.model.RspModel;
import com.xzty.cq.tover.businessmanagement.common.net.NetWork;
import com.xzty.cq.tover.businessmanagement.projectmanagement.common.api.RemoteService;
import com.xzty.cq.tover.businessmanagement.projectmanagement.tool.model.back.AuditDetailsFixOrDamage;
import com.xzty.cq.tover.businessmanagement.projectmanagement.tool.model.back.ReqAuditDetails;
import com.xzty.cq.tover.businessmanagement.projectmanagement.tool.model.back.RspAuditDetails;

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

public class ToolPickAuditDetailsHelper {
    public static void getData(int id, final DataSourse.Callback callback) {
        RemoteService service = NetWork.remote(RemoteService.class);
        Subscription subscription = service.bdGetBackConfirmDetail(id).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<RspModel<List<RspAuditDetails>>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                callback.onDataNotAvailable("请求失败" + e.getMessage());
            }

            @Override
            public void onNext(RspModel<List<RspAuditDetails>> listRspModel) {
                int code = listRspModel.getBackcode();
                if (code == 1) {
                    callback.onDataLoaded(listRspModel.getData());
                } else {
                    callback.onDataNotAvailable("请求错误");
                }
            }
        });
    }

    public static void submit(final List<RspAuditDetails> list, final int backId, final DataSourse.Callback callback){
        List<RspAuditDetails> checkedList = new ArrayList<>();
        for (RspAuditDetails details : list) {
            if (details.isChecked) {
                checkedList.add(details);
            }
        }
        List<AuditDetailsFixOrDamage> fixOrDamageList = new ArrayList<>();
        ReqAuditDetails submit = new ReqAuditDetails();
        List<Integer> backList = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            switch (list.get(i).status) {
                case 1:
                    backList.add(list.get(i).getToolBackDetailId());
                    submit.setBackDetailIdList(backList);
                    break;
                case 2:
                    AuditDetailsFixOrDamage fix = new AuditDetailsFixOrDamage();
                    fix.setFix(list.get(i).getToolId());
                    fixOrDamageList.add(fix);
                    break;
                case 3:
                    AuditDetailsFixOrDamage demage = new AuditDetailsFixOrDamage();
                    demage.setDamage(list.get(i).getToolId());
                    fixOrDamageList.add(demage);
                    break;
            }
        }
        //完好入库
        submit.setBackDetailIdList(backList);
        submit.setToolBackId(backId);
        RemoteService service = NetWork.remote(RemoteService.class);
        Subscription subscription = service.bdSubmitBackConfirm(submit).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<RspModel<RspLogin>>() {
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


        //报废或者维修
        for (AuditDetailsFixOrDamage detailsFixOrDamage : fixOrDamageList) {
            Subscription subscription2 = service.backFixOrDamage(detailsFixOrDamage).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<RspModel<RspLogin>>() {
                @Override
                public void onCompleted() {

                }

                @Override
                public void onError(Throwable e) {

                }

                @Override
                public void onNext(RspModel<RspLogin> rspLoginRspModel) {
                    int code = rspLoginRspModel.getBackcode();
                    if (code == 1) {
                        Toast.makeText(MyApplication.getInstance(),"报废维修成功",Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(MyApplication.getInstance(),"失败",Toast.LENGTH_SHORT).show();
                    }
                }
            });

        }
    }

    public static FixModel fixClick(List<RspAuditDetails> list, int position){
        FixModel model = new FixModel();
        list.get(position).status = 2;
        String chooseString = "您选择了维修";
        model.setChooseString(chooseString);
        model.setList(list);
        return model;
    }

    public static FixModel demageClick(List<RspAuditDetails> list, int position){
        FixModel model = new FixModel();
        String chooseString = "您选择了报废";
        list.get(position).status = 3;
        model.setChooseString(chooseString);
        model.setList(list);
        return model;
    }

}
