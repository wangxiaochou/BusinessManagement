package com.xzty.cq.tover.businessmanagement.projectmanagement.tool.presenter.pickdepartment.back;

import com.xzty.cq.tover.businessmanagement.common.data.DataSourse;
import com.xzty.cq.tover.businessmanagement.common.factory.BasePresenter;
import com.xzty.cq.tover.businessmanagement.projectmanagement.tool.data.pickdepartment.back.FixModel;
import com.xzty.cq.tover.businessmanagement.projectmanagement.tool.data.pickdepartment.back.ToolPickAuditDetailsHelper;
import com.xzty.cq.tover.businessmanagement.projectmanagement.tool.model.back.RspAuditDetails;

import java.util.List;

/**
 * author zzl
 * Created 2018/5/28.
 * explain
 */

public class ToolPickAuditDetailsPrsenter extends BasePresenter<ToolPickAuditDetailsContract.View> implements ToolPickAuditDetailsContract.Presenter, DataSourse.Callback {
    private String isNet;

    public ToolPickAuditDetailsPrsenter(ToolPickAuditDetailsContract.View view) {
        super(view);
    }

    @Override
    public void getData(int id) {
        isNet = "getData";
        ToolPickAuditDetailsHelper.getData(id, this);
    }

    @Override
    public void submit(List<RspAuditDetails> list, int backId) {
        isNet = "submit";
        ToolPickAuditDetailsHelper.submit(list, backId, this);

       /* List<RspAuditDetails> checkedList = new ArrayList<>();
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
        RemoteServiceTask service = NetWork.remote(RemoteServiceTask.class);
        Subscription subscription = service.bdSubmitBackConfirm(submit).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<RspModel<RspLogin>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                view.showError("请求失败" + e.getMessage());
            }

            @Override
            public void onNext(RspModel<RspLogin> rspLoginRspModel) {
                int code = rspLoginRspModel.getBackcode();
                if (code == 1) {
                    view.submitSuccess();
                } else {
                    view.showError("请求错误");
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

        }*/
    }

    @Override
    public void fixClick(List<RspAuditDetails> list, int position) {
        ToolPickAuditDetailsContract.View view = getView();
        //检查是否已经选择了报废
     /*   if (list.get(position).isDamage) {
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

        }*/
        FixModel model = ToolPickAuditDetailsHelper.fixClick(list, position);
        view.clickSucceess(model.getList(), model.getChooseString());
    }

    @Override
    public void demageClick(List<RspAuditDetails> list, int position) {
        ToolPickAuditDetailsContract.View view = getView();
       /* if (list.get(position).isFix) {
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
        }*/
        FixModel model = ToolPickAuditDetailsHelper.demageClick(list, position);
        view.clickSucceess(model.getList(), model.getChooseString());
    }

    @Override
    public void onDataLoaded(Object o) {
        if (isNet.equals("getData")) {
            getView().seccess((List<RspAuditDetails>) o);
        } else if (isNet.equals("submit")) {
            getView().submitSuccess();
        }
    }

    @Override
    public void onDataNotAvailable(String strRes) {
        getView().showError(strRes);
    }
}
