package com.xzty.cq.tover.businessmanagement.projectmanagement.part.presenter.income;

import com.alibaba.fastjson.JSON;
import com.google.gson.Gson;
import com.xzty.cq.tover.businessmanagement.common.data.DataSourse;
import com.xzty.cq.tover.businessmanagement.common.factory.BasePresenter;
import com.xzty.cq.tover.businessmanagement.common.model.RspModel;
import com.xzty.cq.tover.businessmanagement.projectmanagement.part.data.income.ReceiveChooseHelper;
import com.xzty.cq.tover.businessmanagement.projectmanagement.part.data.income.ReceiveChooseModel;
import com.xzty.cq.tover.businessmanagement.projectmanagement.part.model.deliver.DeliverDetails;
import com.xzty.cq.tover.businessmanagement.projectmanagement.part.model.income.RspReceiveChoose;

import java.util.List;

/**
 * author zzl
 * Created 2018/5/15.
 * explain
 */

public class ReceiveChoosePresenter extends BasePresenter<ReceiveChooseContract.View> implements ReceiveChooseContract.Presenter, DataSourse.Callback<List<RspReceiveChoose>> {
    public ReceiveChoosePresenter(ReceiveChooseContract.View view) {
        super(view);
    }

    @Override
    public void getArray(String outId) {
        ReceiveChooseContract.View view = getView();
        ReceiveChooseHelper.getArray(outId, this);
    }

    @Override
    public void chooseAll(List<DeliverDetails> list, boolean status) {
        ReceiveChooseContract.View view = getView();
        ReceiveChooseModel model = new ReceiveChooseModel();
        model = ReceiveChooseHelper.chooseAll(list, status);
        view.chooseAllBack(model.getList(), model.getCountType(), model.getCountNum());
    }

    @Override
    public void chooseOne(List<DeliverDetails> list, int position, int countType, int countNum) {
        start();
        ReceiveChooseContract.View view = getView();
        ReceiveChooseModel model = new ReceiveChooseModel();
        model = ReceiveChooseHelper.chooseOne(list, position, countType, countNum);
        view.chooseOneBack(model.getList(), model.getCountType(), model.getCountNum());
    }

    @Override
    public void confirm(List<DeliverDetails> list, int countType) {
        ReceiveChooseContract.View view = getView();
        RspModel<List<DeliverDetails>> model = ReceiveChooseHelper.confirm(list, countType);
        if (model == null) {
            view.showError("你没有勾选任何构件");
        } else if (model.getMessage().equals("warning")) {
            view.warningBack(model.getData());
        } else if (model.getMessage().equals("ok")) {
            view.ok(model.getData());
        }
    }

    @Override
    public void onDataLoaded(List<RspReceiveChoose> rspReceiveChooses) {
        ReceiveChooseContract.View view = getView();
        view.success(JSON.parseArray(new Gson().toJson(rspReceiveChooses), DeliverDetails.class));

    }

    @Override
    public void onDataNotAvailable(String strRes) {
        getView().showError(strRes);
    }
}
