package com.xzty.cq.tover.businessmanagement.projectmanagement.tool.presenter.other.back;

import com.xzty.cq.tover.businessmanagement.common.data.DataSourse;
import com.xzty.cq.tover.businessmanagement.common.factory.BasePresenter;
import com.xzty.cq.tover.businessmanagement.projectmanagement.tool.data.other.back.OtherBackApplyHelper;
import com.xzty.cq.tover.businessmanagement.projectmanagement.tool.model.other.RspBackApply;

import java.util.ArrayList;
import java.util.List;

/**
 * author zzl
 * Created 2018/5/26.
 * explain
 */

public class OtherBackApplyPresenter extends BasePresenter<OtherBackApplyContract.View> implements OtherBackApplyContract.Presenter, DataSourse.Callback {
    private String isNet;

    public OtherBackApplyPresenter(OtherBackApplyContract.View view) {
        super(view);
    }

    @Override
    public void getData() {
        isNet = "getData";
        OtherBackApplyHelper.getData(this);
    }

    @Override
    public void itemClick(List<RspBackApply> list, int position) {
        OtherBackApplyContract.View view = getView();
        view.clickSuccess(OtherBackApplyHelper.itemClick(list, position));
    }

    @Override
    public void apply(List<RspBackApply> list, String note) {
        List<Integer> applyList = new ArrayList<>();
        for (RspBackApply apply : list) {
            if (apply.isChecked) {
                applyList.add(apply.getToolCollectDetailId());
            }
        }
        if (applyList.size() > 0) {
            isNet = "apply";
            OtherBackApplyHelper.apply(list,note,applyList,this);
        } else {
            getView().showError("请至少选择一种机具");
        }
    }

    @Override
    public void onDataLoaded(Object o) {
        if (isNet.equals("getData")) {
            getView().dataSuccess((List<RspBackApply>) o);
        }else if(isNet.equals("apply")){
            getView().applySuccess();
        }
    }

    @Override
    public void onDataNotAvailable(String strRes) {
        getView().showError(strRes);
    }
}
