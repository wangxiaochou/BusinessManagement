package com.xzty.cq.tover.businessmanagement.projectmanagement.part.presenter.apply;

import android.util.SparseArray;
import android.widget.CheckBox;

import com.xzty.cq.tover.businessmanagement.common.data.DataSourse;
import com.xzty.cq.tover.businessmanagement.common.factory.BasePresenter;
import com.xzty.cq.tover.businessmanagement.common.model.RspLogin;
import com.xzty.cq.tover.businessmanagement.projectmanagement.part.data.apply.PartApplyInfoHelper;
import com.xzty.cq.tover.businessmanagement.projectmanagement.part.model.apply.RspPartList;

import java.util.ArrayList;
import java.util.List;

/**
 * author zzl
 * Created 2018/5/9.
 * explain 申请信息填写的实现类
 */

public class SearchInfoPresenter extends BasePresenter<SearchInfoContract.View>
        implements SearchInfoContract.Presenter, DataSourse.Callback<RspLogin> {
    public SearchInfoPresenter(SearchInfoContract.View view) {
        super(view);
    }

    @Override
    public void search(List<RspPartList> list, String partNo, String partBatch) {
        SearchInfoContract.View view = getView();
        List<String> partBatchList = new ArrayList<>();//批次数据
        List<String> partNameList = new ArrayList<>(); //名称数据
        partBatchList.add("全部");
        partNameList.add("全部");
        List<RspPartList> choosePartList = list;
        for (RspPartList part : choosePartList) {
            String batch = (part.getPartBatch() == null || part.getPartBatch().equals("")) ? "空" : part.getPartBatch();
            if (!partBatchList.contains(batch)) {
                partBatchList.add(batch);
            }
            String partName = (part.getPartName() == null || part.getPartName().equals("")) ? "空" : part.getPartName();
            if (!partNameList.contains(partName)) {
                partNameList.add(partName);
            }
        }

        // 名称 partName
        String name = "";
        if (name != null && !name.equals("") && !name.equals("全部")) {
            List<RspPartList> removePartList = new ArrayList<>();
            for (RspPartList part : choosePartList) {
                if (part.getPartName() != null && part.getPartName().equals(name)) {
                    removePartList.add(part);
                } else if (part.getPartName() == null && name.equals("空")) {
                    removePartList.add(part);
                }
                choosePartList = removePartList;
            }

            //构件编号 partNo
            if (!partNo.equals("")) {
                List<RspPartList> removePartList2 = new ArrayList<>();
                for (RspPartList part : choosePartList) {
                    if (part.getPartNo() != null && part.getPartNo().toLowerCase().contains(part.getPartNo().toLowerCase())) {
                        removePartList2.add(part);
                    }
                }
                choosePartList = removePartList2;
            }

            //批次  partBatch
            if (partBatch != null && !partBatch.equals("") && !partBatch.equals("全部")) {
                List<RspPartList> removePartList3 = new ArrayList<>();
                for (RspPartList part : choosePartList) {
                    if (part.getPartBatch() != null && part.getPartBatch().equals(partBatch)) {
                        removePartList.add(part);
                    } else if (part.getPartBatch() == null && partBatch.equals("空")) {
                        removePartList.add(part);
                    }
                }
                choosePartList = removePartList;
            }

        }
        SparseArray<Double> countList = new SparseArray<>();
        for (int i = 0; i < choosePartList.size(); i++) {
            countList.put(i, choosePartList.get(i).getPartCount());
        }

        view.searchSuccess(choosePartList, partBatchList, partNameList, countList);
    }

    //全部选中逻辑
    @Override
    public void selectedAll(List<RspPartList> list, CheckBox checkBox) {
        SearchInfoContract.View view = getView();
        boolean status;
        if (checkBox.isChecked()) {
            status = true;
        } else {
            status = false;
        }
        view.selectedAllBack(PartApplyInfoHelper.selectedAll(list, status));
    }

    @Override
    public void checkBoxClick(List<RspPartList> list, int position) {
        SearchInfoContract.View view = getView();
        view.singlecheckBox(PartApplyInfoHelper.checkBoxClick(list, position));
    }

    @Override
    public void addNeedCount(List<RspPartList> list, int position) {
        SearchInfoContract.View view = getView();
        List<RspPartList> lists = PartApplyInfoHelper.addNeedCount(list, position);
        if (lists == null) {
            view.showError("不能再增加了");
        } else {
            view.needCount(list);
        }
    }

    @Override
    public void minusNeedCount(List<RspPartList> list, int position) {
        SearchInfoContract.View view = getView();
        List<RspPartList> lists = PartApplyInfoHelper.minusNeedCount(list, position);
        if (lists == null) {
            view.showError("数量不够减");
        } else {
            view.minusNeedCount(lists);
        }
    }

    @Override
    public void submit(String applyNote, List<RspPartList> list) {
        PartApplyInfoHelper.submit(list, applyNote, this);
    }

    @Override
    public void onDataLoaded(RspLogin rspLogin) {
        SearchInfoContract.View view = getView();
        view.submitSuccess(rspLogin);
    }

    @Override
    public void onDataNotAvailable(String strRes) {
        SearchInfoContract.View view = getView();
        view.showError(strRes);
    }
}
