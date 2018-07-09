package com.xzty.cq.tover.businessmanagement.projectmanagement.part.presenter.pick;

import android.text.TextUtils;

import com.alibaba.fastjson.JSON;
import com.xzty.cq.tover.businessmanagement.common.data.DataSourse;
import com.xzty.cq.tover.businessmanagement.common.factory.BasePresenter;
import com.xzty.cq.tover.businessmanagement.common.model.RspLogin;
import com.xzty.cq.tover.businessmanagement.projectmanagement.part.data.pick.PickWriteInfoHelper;
import com.xzty.cq.tover.businessmanagement.projectmanagement.part.model.pick.RspPickList;

import java.util.List;

/**
 * author zzl
 * Created 2018/5/14.
 * explain 填写采购信息的P
 */

public class PickWriteInfoPresenter extends BasePresenter<PickWriteInfoContract.View> implements PickWriteInfoContract.Presenter, DataSourse.Callback<RspLogin> {
    public PickWriteInfoPresenter(PickWriteInfoContract.View view) {
        super(view);
    }

    @Override
    public void commit(String firm, String contractNo, String expectOutTime, String outNote, String distId, String partList) {
        start();
        PickWriteInfoContract.View view = getView();
        List<RspPickList> list = JSON.parseArray(partList, RspPickList.class);
        for (RspPickList pickList : list) {
            if (TextUtils.isEmpty(pickList.getUnitPrice())) {
                view.showError("请必须填写价格");
                return;
            }
        }
        PickWriteInfoHelper.commit(firm, contractNo, expectOutTime, outNote, distId, partList, this);
    }

    @Override
    public void itemClick(List<RspPickList> mList, int position) {
        getView().clickBack(PickWriteInfoHelper.itemClick(mList,position));
    }

    @Override
    public void chooseAll(List<RspPickList> mList, boolean status) {
        getView().clickBack(PickWriteInfoHelper.chooseAll(mList,status));
    }

    @Override
    public void writePrice(List<RspPickList> mList, String price) {
        getView().clickBack(PickWriteInfoHelper.writePrice(mList,price));
    }

    @Override
    public void onDataLoaded(RspLogin rspLogin) {
        getView().ok();
    }

    @Override
    public void onDataNotAvailable(String strRes) {
        getView().showError(strRes);
    }
}
