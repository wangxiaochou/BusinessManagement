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
        /*
        RemoteService service = NetWork.remote(RemoteService.class);
        Account.load(MyApplication.getInstance());
        String projectId = Account.getProjectId();
        String outUserId = Account.getemployId();
        RequestBody body1 = RequestBody.create(MediaType.parse("multipart/form-data"),
                projectId);
        RequestBody body2 = RequestBody.create(MediaType.parse("multipart/form-data"),
                outUserId);
        RequestBody body3 = RequestBody.create(MediaType.parse("multipart/form-data"),
                distId);
        RequestBody body4 = RequestBody.create(MediaType.parse("multipart/form-data"),
                partList);
        RequestBody body5 = RequestBody.create(MediaType.parse("multipart/form-data"),
                firm);
        RequestBody body6 = RequestBody.create(MediaType.parse("multipart/form-data"),
                contractNo);
        RequestBody body7 = RequestBody.create(MediaType.parse("multipart/form-data"),
                expectOutTime);
        RequestBody body8 = RequestBody.create(MediaType.parse("multipart/form-data"),
                outNote);
        Map<String, RequestBody> maps = new HashMap<>();
        maps.put("projectId", body1);
        maps.put("outUserId", body2);
        maps.put("distId", body3);
        maps.put("partList", body4);
        maps.put("firm", body5);
        maps.put("contractNo", body6);
        maps.put("expectOutTime", body7);
        maps.put("outNote", body8);
        Subscription subscription = service.overPurchase(maps).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<RspModel<List<ReqLogin>>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                view.showError("请求失败" + e.getMessage());
            }

            @Override
            public void onNext(RspModel<List<ReqLogin>> listRspModel) {
                int code = listRspModel.getBackcode();
                if (code == 0) {
                    view.showError("请求错误");
                } else {
                    view.ok();
                }
            }
        });*/
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
