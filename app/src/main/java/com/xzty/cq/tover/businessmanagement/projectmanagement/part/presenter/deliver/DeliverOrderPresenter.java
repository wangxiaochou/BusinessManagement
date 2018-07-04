package com.xzty.cq.tover.businessmanagement.projectmanagement.part.presenter.deliver;

import android.text.TextUtils;

import com.xzty.cq.tover.businessmanagement.common.MyApplication;
import com.xzty.cq.tover.businessmanagement.common.data.DataSourse;
import com.xzty.cq.tover.businessmanagement.common.factory.Account;
import com.xzty.cq.tover.businessmanagement.common.factory.BasePresenter;
import com.xzty.cq.tover.businessmanagement.projectmanagement.part.data.apply.BuyerHelper;
import com.xzty.cq.tover.businessmanagement.projectmanagement.part.data.deliver.DeliverOrderHelper;
import com.xzty.cq.tover.businessmanagement.projectmanagement.part.model.Emp;
import com.xzty.cq.tover.businessmanagement.projectmanagement.part.model.deliver.RspPurchase;

import java.util.List;

/**
 * author zzl
 * Created 2018/5/14.
 * explain 发货单的Presenter
 */

public class DeliverOrderPresenter extends BasePresenter<DeliverOrderContract.View>
        implements DeliverOrderContract.Presenter, DataSourse.Callback {
    private String isNet;
    public DeliverOrderPresenter(DeliverOrderContract.View view) {
        super(view);
    }

    @Override
    public void getBuyer() {
        start();
        isNet = "getBuyer";
        BuyerHelper.getBuyer(this);
    }

    @Override
    public void getPurchas(String applyOrderState, String time, String firm, String contractNo, String outUserId) {
        final DeliverOrderContract.View view = getView();
        isNet = "getPurchas";
         DeliverOrderHelper.getPurchas(applyOrderState, time, firm, contractNo, outUserId,this);
    /*    if(TextUtils.isEmpty(model.getMessage())){
            view.backDeliver((List<RspPurchase>) model.getData());
        }else{
            view.showError(model.getMessage());
        }*/
    }

    /*@Override
    public void onDataLoaded(List<Emp> emps) {
        DeliverOrderContract.View view = getView();
        int i = -1;
        Account.load(MyApplication.getInstance());
        String employId = Account.getemployId();
        for (Emp emp : emps) {
            if (!TextUtils.isEmpty(emp.getEmployId())) {
                if (emp.getEmployId().equals(employId)) {
                    i = emps.indexOf(emp);
                }
            }
        }
        view.backBuyer(emps, i);
    }*/

    @Override
    public void onDataNotAvailable(String strRes) {
        start();
        DeliverOrderContract.View view = getView();
        view.showError(strRes);
    }

    @Override
    public void onDataLoaded(Object o) {
        if(isNet.equals("getBuyer")){
            int i = -1;
            List<Emp> emps = (List<Emp>) o;
            Account.load(MyApplication.getInstance());
            String employId = Account.getemployId();
            for (Emp emp : emps) {
                if (!TextUtils.isEmpty(emp.getEmployId())) {
                    if (emp.getEmployId().equals(employId)) {
                        i = emps.indexOf(emp);
                    }
                }
            }
            getView().backBuyer(emps, i);
        }else if(isNet.equals("getPurchas")){
            getView().backDeliver((List<RspPurchase>) o);
        }
    }
}
