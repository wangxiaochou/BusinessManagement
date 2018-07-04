package com.xzty.cq.tover.businessmanagement.projectmanagement.part.presenter.apply;

import com.xzty.cq.tover.businessmanagement.common.MyApplication;
import com.xzty.cq.tover.businessmanagement.common.data.DataSourse;
import com.xzty.cq.tover.businessmanagement.common.factory.Account;
import com.xzty.cq.tover.businessmanagement.common.factory.BasePresenter;
import com.xzty.cq.tover.businessmanagement.projectmanagement.part.data.apply.ChooseHelper;
import com.xzty.cq.tover.businessmanagement.projectmanagement.part.data.apply.PromoteHelper;
import com.xzty.cq.tover.businessmanagement.projectmanagement.part.model.apply.RspPartList;

import java.util.ArrayList;
import java.util.List;

/**
 * author zzl
 * Created 2018/6/5.
 * explain
 */

public class PromoteNumPresenter extends BasePresenter<PromoteNumContract.View> implements PromoteNumContract.Presenter, DataSourse.Callback {
    //回调时P层判断返回方法
    private String isNet;
    public PromoteNumPresenter(PromoteNumContract.View view) {
        super(view);
    }

    @Override
    public void getData(String partName, String partNo) {
        Account.load(MyApplication.getInstance());
        String projectId = Account.getProjectId();
        String status = "-1";
        String partBatch = "";
        isNet = "search";
        ChooseHelper.SearchChoose(projectId, partName, partNo, status, partBatch, this);
    }

    @Override
    public void minusClick(List<RspPartList> lists, int position) {
        PromoteNumContract.View view = getView();
        lists = PromoteHelper.minusClick(lists, position);
        if (lists == null) {
            view.showError("不能再减了");
        } else {
            view.clickSuccess(lists);
        }
    }

    @Override
    public void addClick(List<RspPartList> lists, int position) {
        PromoteNumContract.View view = getView();
        view.clickSuccess(PromoteHelper.addClick(lists, position));
    }

    @Override
    public void itemClick(List<RspPartList> lists, int position) {
        PromoteNumContract.View view = getView();
        view.clickSuccess(PromoteHelper.itemClick(lists, position));
    }

    @Override
    public void chooseAllClick(boolean status, List<RspPartList> lists) {
        PromoteNumContract.View view = getView();
        //点了之后的状态
        view.clickSuccess(PromoteHelper.chooseAllClick(lists, status));
    }

    @Override
    public void submit(List<RspPartList> lists) {
        isNet="submit";
        PromoteHelper.submit(lists,this);
    }

   /* @Override
    public void onDataLoaded(List<RspPartList> lists) {
        PromoteNumContract.View view = getView();
        List<String> partName = new ArrayList<>();
        for (RspPartList partList : lists) {
            String name = (partList.getPartName() == null || partList.getPartName().equals("")) ? "空" : partList.getPartName();
            if (!partName.contains(name)) {
                partName.add(name);
            }
        }
        view.dataSuccess(lists, partName);
    }*/

    @Override
    public void onDataNotAvailable(String strRes) {
        PromoteNumContract.View view = getView();
        view.showError(strRes);
    }

    @Override
    public void onDataLoaded(Object o) {
        if(isNet.equals("search")){
            List<RspPartList> lists = (List<RspPartList>) o;
            List<String> partName = new ArrayList<>();
            for (RspPartList partList : lists) {
                String name = (partList.getPartName() == null || partList.getPartName().equals("")) ? "空" : partList.getPartName();
                if (!partName.contains(name)) {
                    partName.add(name);
                }
            }
            getView().dataSuccess(lists, partName);
        }else if(isNet.equals("submit")){
            getView().submitSuccess();
        }
    }
}
