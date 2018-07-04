package com.xzty.cq.tover.businessmanagement.projectmanagement.tool.presenter.projectmanage.back;

import com.xzty.cq.tover.businessmanagement.common.factory.BasePresenter;
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
 * Created 2018/5/28.
 * explain
 */

public class ToolProjectManageAuditDetailsPrsenter extends BasePresenter<ToolProjectManageAuditDetailsContract.View> implements ToolProjectManageAuditDetailsContract.Presenter {

    public ToolProjectManageAuditDetailsPrsenter(ToolProjectManageAuditDetailsContract.View view) {
        super(view);
    }

    @Override
    public void getData(int id) {
        final ToolProjectManageAuditDetailsContract.View view = getView();
        RemoteService service = NetWork.remote(RemoteService.class);
        Subscription subscription = service.getBackConfirmDetail(id).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<RspModel<List<RspAuditDetails>>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                view.showError("请求失败" + e.getMessage());
            }

            @Override
            public void onNext(RspModel<List<RspAuditDetails>> listRspModel) {
                int code = listRspModel.getBackcode();
                if (code == 1) {
                    view.seccess(listRspModel.getData());
                } else {
                    view.showError("请求错误");
                }
            }
        });
    }

    @Override
    public void submit(List<RspAuditDetails> list, int backId) {
        final ToolProjectManageAuditDetailsContract.View view = getView();
        List<RspAuditDetails> checkedList = new ArrayList<>();
        for (RspAuditDetails details : list) {
            if (details.isChecked) {
                checkedList.add(details);
            }
        }
        if (checkedList.size() <= 0) {
            view.showError("请至少选择一种机具");
        } else {
            List<AuditDetailsFixOrDamage> fixOrDamageList = new ArrayList<>();
            ReqAuditDetails submit = new ReqAuditDetails();
            List<Integer> backList = new ArrayList<>();
            for (int i = 0; i < checkedList.size(); i++) {
                switch (checkedList.get(i).getToolBackDetailState()) {
                    case 1:
                        backList.add(checkedList.get(i).getToolBackDetailId());
                        submit.setBackDetailIdList(backList);
                        break;
                    case 2:
                        AuditDetailsFixOrDamage fix = new AuditDetailsFixOrDamage();
                        fix.setFix(checkedList.get(i).getToolId());
                        fixOrDamageList.add(fix);
                        break;
                    case 3:
                        AuditDetailsFixOrDamage demage = new AuditDetailsFixOrDamage();
                        demage.setDamage(checkedList.get(i).getToolId());
                        fixOrDamageList.add(demage);
                        break;
                }
            }
            submit.setBackDetailIdList(backList);
            submit.setToolBackId(backId);
            RemoteService service = NetWork.remote(RemoteService.class);
            Subscription subscription = service.mdSubmitBackConfirm(submit).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<RspModel<RspLogin>>() {
                @Override
                public void onCompleted() {

                }

                @Override
                public void onError(Throwable e) {
                    view.showError("请求失败"+e.getMessage());
                }

                @Override
                public void onNext(RspModel<RspLogin> rspLoginRspModel) {
                    int code = rspLoginRspModel.getBackcode();
                    if(code == 1){
                        view.submitSuccess();
                    }else{
                        view.showError("请求错误");
                    }
                }
            });
        }
    }

    @Override
    public void fixClick(List<RspAuditDetails> list, int position) {
        ToolProjectManageAuditDetailsContract.View view = getView();
        //检查是否已经选择了报废
        String chooseString = "";
        if (list.get(position).isDamage) {
            //取消选择报废
            list.get(position).isDamage = false;
            list.get(position).isChecked = false;
        }

        if (list.get(position).isFix) {
            list.get(position).isFix = false;
            list.get(position).isChecked = false;
        } else {
            list.get(position).isFix = true;
            list.get(position).isChecked = true;
            chooseString = "您选择了维修";
        }
        view.clickSucceess(list,chooseString);
    }

    @Override
    public void demageClick(List<RspAuditDetails> list, int position) {
        ToolProjectManageAuditDetailsContract.View view = getView();
        String chooseString = "";
        if (list.get(position).isFix) {
            list.get(position).isFix = false;
            list.get(position).isChecked = false;
        }

        if (list.get(position).isDamage) {
            list.get(position).isDamage = false;
            list.get(position).isChecked = false;
        } else {
            list.get(position).isDamage = true;
            list.get(position).isChecked = true;
            chooseString = "您选择了报废";
        }
        view.clickSucceess(list,chooseString);
    }

}
