package com.xzty.cq.tover.businessmanagement.projectmanagement.tool.presenter.projectmanage.back;

import com.xzty.cq.tover.businessmanagement.common.MyApplication;
import com.xzty.cq.tover.businessmanagement.common.factory.Account;
import com.xzty.cq.tover.businessmanagement.common.factory.BasePresenter;
import com.xzty.cq.tover.businessmanagement.common.model.RspModel;
import com.xzty.cq.tover.businessmanagement.common.net.NetWork;
import com.xzty.cq.tover.businessmanagement.projectmanagement.common.api.RemoteService;
import com.xzty.cq.tover.businessmanagement.projectmanagement.tool.model.back.RspAuditList;

import java.util.List;

import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * author zzl
 * Created 2018/5/28.
 * explain
 */

public class ToolProjectManageAuditListPrsenter extends BasePresenter<ToolProjectManageAuditListContract.View> implements ToolProjectManageAuditListContract.Presenter {

    public ToolProjectManageAuditListPrsenter(ToolProjectManageAuditListContract.View view) {
        super(view);
    }

    @Override
    public void getData() {
        final ToolProjectManageAuditListContract.View view = getView();
        RemoteService service = NetWork.remote(RemoteService.class);
        Account.load(MyApplication.getInstance());
        String projectId = Account.getProjectId();
        Subscription subscription = service.getBackConfirmList(projectId).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<RspModel<List<RspAuditList>>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                view.showError("请求失败"+e.getMessage());
            }

            @Override
            public void onNext(RspModel<List<RspAuditList>> listRspModel) {
                int code = listRspModel.getBackcode();
                if(code == 1){
                    view.seccess(listRspModel.getData());
                }else{
                    view.showError("请求错误");
                }
            }
        });
    }
}
