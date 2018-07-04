package com.xzty.cq.tover.businessmanagement.projectmanagement.tool.presenter.projectmanage.apply;

import android.util.SparseArray;

import com.xzty.cq.tover.businessmanagement.common.factory.BasePresenter;
import com.xzty.cq.tover.businessmanagement.common.model.RspLogin;
import com.xzty.cq.tover.businessmanagement.common.model.RspModel;
import com.xzty.cq.tover.businessmanagement.common.net.NetWork;
import com.xzty.cq.tover.businessmanagement.projectmanagement.common.api.RemoteService;
import com.xzty.cq.tover.businessmanagement.projectmanagement.tool.model.projectmanage.ReqToolApply;
import com.xzty.cq.tover.businessmanagement.projectmanagement.tool.model.projectmanage.RspToolApplyList;

import java.util.List;

import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * author zzl
 * Created 2018/5/22.
 * explain
 */

public class ToolApplyDetailsPresenter extends BasePresenter<ToolApplayDetailsContract.View> implements ToolApplayDetailsContract.Presenter {

    public ToolApplyDetailsPresenter(ToolApplayDetailsContract.View view) {
        super(view);
    }

    @Override
    public void submit(ReqToolApply details) {
        final ToolApplayDetailsContract.View view = getView();
        RemoteService service = NetWork.remote(RemoteService.class);
        Subscription subscription = service.SubmitApply(details).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<RspModel<List<RspLogin>>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                view.showError("请求失败" + e.getMessage());
            }

            @Override
            public void onNext(RspModel<List<RspLogin>> listRspModel) {
                int code = listRspModel.getBackcode();
                if (code == 1) {
                    view.submitSuccess();
                } else {
                    view.showError("请求错误");
                }
            }
        });
    }

    @Override
    public void add(List<RspToolApplyList> list, int position,SparseArray<Double> countList) {
        ToolApplayDetailsContract.View view = getView();
        if(list.get(position).getToolCount()==countList.get(position)){
            view.showError("库存不够了");
        }else{
            double count = list.get(position).getToolCount();
            count++;
            list.get(position).setToolCount(count);
        }
        view.calculateSuccess(list);
    }

    @Override
    public void minus(List<RspToolApplyList> list, int position) {
        ToolApplayDetailsContract.View view = getView();
        if(list.get(position).getToolCount()>1){
            double count = list.get(position).getToolCount();
            count--;
            list.get(position).setToolCount(count);
        }else{
            view.showError("不能再减了");
        }
        view.calculateSuccess(list);
    }
}
