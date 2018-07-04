package com.xzty.cq.tover.businessmanagement.projectmanagement.tool.presenter.projectmanage.affirm;

import com.xzty.cq.tover.businessmanagement.common.factory.BasePresenter;
import com.xzty.cq.tover.businessmanagement.common.model.RspModel;
import com.xzty.cq.tover.businessmanagement.common.net.NetWork;
import com.xzty.cq.tover.businessmanagement.projectmanagement.common.api.RemoteService;
import com.xzty.cq.tover.businessmanagement.projectmanagement.tool.model.projectmanage.RspAffirmList;

import java.util.List;

import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * author zzl
 * Created 2018/5/23.
 * explain
 */

public class AffirmListPresenter extends BasePresenter<AffirmListContract.View> implements AffirmListContract.Presenter {
    public AffirmListPresenter(AffirmListContract.View view) {
        super(view);
    }

    @Override
    public void getApplyList(String id) {
        final AffirmListContract.View view = getView();
        RemoteService service = NetWork.remote(RemoteService.class);
        Subscription subscription = service.getToolApplyConfirmList(id).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<RspModel<List<RspAffirmList>>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                view.showError("请求失败" + e.getMessage());
            }

            @Override
            public void onNext(RspModel<List<RspAffirmList>> listRspModel) {
                int code = listRspModel.getBackcode();
                if (code == 1) {
                    view.success(listRspModel.getData());
                } else {
                    view.showError("请求错误");
                }
            }
        });
    }
}
