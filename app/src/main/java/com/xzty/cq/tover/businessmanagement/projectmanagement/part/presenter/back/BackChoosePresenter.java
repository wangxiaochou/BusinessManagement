package com.xzty.cq.tover.businessmanagement.projectmanagement.part.presenter.back;

import com.alibaba.fastjson.JSON;
import com.google.gson.Gson;
import com.xzty.cq.tover.businessmanagement.common.data.DataSourse;
import com.xzty.cq.tover.businessmanagement.common.factory.BasePresenter;
import com.xzty.cq.tover.businessmanagement.projectmanagement.part.data.back.BackChooseHelper;
import com.xzty.cq.tover.businessmanagement.projectmanagement.part.model.back.BackPart;
import com.xzty.cq.tover.businessmanagement.projectmanagement.part.model.back.RspChooseBack;

import java.util.ArrayList;
import java.util.List;

/**
 * author zzl
 * Created 2018/5/17.
 * explain
 */

public class BackChoosePresenter extends BasePresenter<BackChooseContract.View> implements BackChooseContract.Presenter, DataSourse.Callback<List<RspChooseBack>> {
    public BackChoosePresenter(BackChooseContract.View view) {
        super(view);
    }

    @Override
    public void search(String time, String pickBatch, String partName, String partNo) {
        BackChooseHelper.search(time, pickBatch, partName, partNo, this);
    }

    @Override
    public void chooseAll(List<BackPart> list, boolean status) {
        BackChooseContract.View view = getView();
        list = BackChooseHelper.chooseAll(list, status);
        List<BackPart> lis = new ArrayList<>();
        for (BackPart part : list) {
            if (part.isCheck) {
                lis.add(part);
            }
        }
        view.selectedAll(list, lis);
    }

    @Override
    public void itemClick(List<BackPart> list, List<BackPart> thisTimeSelectedPartTemp, int position) {
        BackChooseContract.View view = getView();
        //解决并发异常
        list = BackChooseHelper.itemClick(list, position);
        List<BackPart> lis = new ArrayList<>();
        for (BackPart part : list) {
            if (part.isCheck) {
                lis.add(part);
            }
        }
        view.selectedItem(list, lis);
    }

    @Override
    public void onDataLoaded(List<RspChooseBack> rspChooseBacks) {
        BackChooseContract.View view = getView();
        List<BackPart> backList = JSON.parseArray(new Gson().toJson(rspChooseBacks), BackPart.class);
        List<String> partNameList = new ArrayList<>();
        List<String> pickBatchList = new ArrayList<>();
        partNameList.add("全部");
        pickBatchList.add("全部");
        for (BackPart backPart : backList) {
            String partname = (backPart.getPartName() == null || backPart.getPartName().equals("")) ? "空" : backPart.getPartName();
            if (!partNameList.contains(partname)) {
                partNameList.add(partname);
            }
            String backbatch = backPart.getPickBatch() + "批";
            if (!pickBatchList.contains(backbatch)) {
                pickBatchList.add(backbatch);
            }
        }
        view.success(backList, partNameList, pickBatchList);
    }

    @Override
    public void onDataNotAvailable(String strRes) {
        BackChooseContract.View view = getView();
        view.showError(strRes);
    }
}
