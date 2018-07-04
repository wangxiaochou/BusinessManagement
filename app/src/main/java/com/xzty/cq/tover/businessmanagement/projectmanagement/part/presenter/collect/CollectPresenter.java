package com.xzty.cq.tover.businessmanagement.projectmanagement.part.presenter.collect;

import com.xzty.cq.tover.businessmanagement.common.data.DataSourse;
import com.xzty.cq.tover.businessmanagement.common.factory.BasePresenter;
import com.xzty.cq.tover.businessmanagement.projectmanagement.part.data.collect.CollectHelper;
import com.xzty.cq.tover.businessmanagement.projectmanagement.part.data.collect.CollectModel;
import com.xzty.cq.tover.businessmanagement.projectmanagement.part.model.collect.RspCollect;

import java.util.ArrayList;
import java.util.List;

/**
 * author zzl
 * Created 2018/5/10.
 * explain 申请汇总的P层
 */

public class CollectPresenter extends BasePresenter<CollectContract.View> implements CollectContract.Presenter, DataSourse.Callback<List<RspCollect>> {

    public CollectPresenter(CollectContract.View view) {
        super(view);
    }

    @Override
    public void getAllCollect(String partName, String partNo, String applyBatch, String time) {
        CollectHelper.getAllCollect(partName, partNo, applyBatch, time, this);
    }

    @Override
    public void chooseAll(List<RspCollect> list, boolean status) {
        CollectContract.View view = getView();
        CollectModel model = CollectHelper.chooseAll(list, status);
        view.chooseAllBack(model.getList(), model.getChooseList());
    }

    //选中单个
    @Override
    public void itemChoose(List<RspCollect> list, int position) {
        CollectContract.View view = getView();
        CollectModel model = CollectHelper.itemChoose(list,position);
        view.chooseItemBack(model.getList(), model.getChooseList());
    }

    @Override
    public void onDataLoaded(List<RspCollect> list) {
        CollectContract.View view = getView();
        List<String> partNameList = new ArrayList<>();
        List<String> applybatchList = new ArrayList<>();
        partNameList.add("全部");
        applybatchList.add("全部");
        for (RspCollect collectPart : list) {
            String partName = (collectPart.getPartName() == null || collectPart.getPartName().equals("")) ? "空" : collectPart.getPartName();
            if (!partNameList.contains(partName)) {
                partNameList.add(partName);
            }
            String applybatch = String.valueOf(collectPart.getApplyBatch()) + "批";
            if (!applybatchList.contains(applybatch)) {
                applybatchList.add(applybatch);
            }
        }
        view.netSuccess(list, partNameList, applybatchList);
    }

    @Override
    public void onDataNotAvailable(String strRes) {
        CollectContract.View view = getView();
        view.showError(strRes);
    }
}
