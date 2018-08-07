package com.xzty.cq.tover.businessmanagement.projectmanagement.tool.presenter.projectmanage.affirm;

import com.xzty.cq.tover.businessmanagement.common.data.DataSourse;
import com.xzty.cq.tover.businessmanagement.common.factory.BasePresenter;
import com.xzty.cq.tover.businessmanagement.common.model.RspLogin;
import com.xzty.cq.tover.businessmanagement.common.model.RspModel;
import com.xzty.cq.tover.businessmanagement.common.net.NetWork;
import com.xzty.cq.tover.businessmanagement.projectmanagement.common.api.RemoteService;
import com.xzty.cq.tover.businessmanagement.projectmanagement.tool.data.projectmanage.affirm.AffirmDetailsHelper;
import com.xzty.cq.tover.businessmanagement.projectmanagement.tool.data.projectmanage.affirm.ItemClickModel;
import com.xzty.cq.tover.businessmanagement.projectmanagement.tool.model.projectmanage.ReqToolCommit;
import com.xzty.cq.tover.businessmanagement.projectmanagement.tool.model.projectmanage.RspAffirmDetails;

import java.util.ArrayList;
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

public class AffirmDetailsPresenter extends BasePresenter<AffirmDetailsContract.View> implements AffirmDetailsContract.Presenter, DataSourse.Callback {
    private String isNet;

    public AffirmDetailsPresenter(AffirmDetailsContract.View view) {
        super(view);
    }

    @Override
    public void getDetails(int id) {
        isNet = "getDetails";
        AffirmDetailsHelper.getDetails(id, this);
    }

    @Override
    public void sure(List<RspAffirmDetails> sureList, int applyId) {
        isNet = "sure";
        AffirmDetailsHelper.sure(sureList, applyId, this);
       /* final AffirmDetailsContract.View view = getView();
        List<Integer> intList = new ArrayList<>();
        //取出被拒绝
        for (RspAffirmDetails details : sureList) {
            if (details.isRefuse) {
                intList.add(details.getToolApplyDetailId());
            }
        }
        if (intList.size() > 0) {
            RemoteServiceTask service = NetWork.remote(RemoteServiceTask.class);
            Subscription subscription = service.refuseApply(new Gson().toJson(intList)).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<RspModel<List<RspLogin>>>() {
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
                        commit(sureList, applyId);
                    } else {
                        view.showError("请求错误");
                    }
                }
            });
        } else {
            ArrayList<RspAffirmDetails> list = new ArrayList<>();
            for (RspAffirmDetails details : sureList) {
                if (details.isChecked) {
                    list.add(details);
                }
            }
            commit(sureList, applyId);
        }
*/
    }

    private void commit(List<RspAffirmDetails> sureList, int applyId) {
        final AffirmDetailsContract.View view = getView();
        ReqToolCommit commit = new ReqToolCommit();
        List<Integer> checkedDetailIdList = new ArrayList<>();
        commit.setApplyId(applyId);
        for (RspAffirmDetails details : sureList) {
            if (details.isChecked) {
                checkedDetailIdList.add(details.getToolApplyDetailId());
            }
        }
        commit.setCheckedDetailIdList(checkedDetailIdList);
        RemoteService service = NetWork.remote(RemoteService.class);
        Subscription subscription = service.mdSubmitApplyConfirm(commit).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<RspModel<List<RspLogin>>>() {
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
                    view.sureSuccess();
                } else {
                    view.showError("请求错误");
                }
            }
        });
    }

    @Override
    public void longClick(List<RspAffirmDetails> sureList, int position) {
        AffirmDetailsContract.View view = getView();
        if (sureList.get(position).isChecked) {
            view.showError("您已准备确认该机具,请取消拒绝后再试");
        } else {
            ItemClickModel model = AffirmDetailsHelper.longClick(sureList, position);
            view.reFreshAdapter(sureList, model.getCheck(), model.getRefuse());
        }

    }

    @Override
    public void itemClick(List<RspAffirmDetails> sureList, int position) {
        AffirmDetailsContract.View view = getView();
        if (sureList.get(position).isRefuse) {
            view.showError("您已拒绝该机具,请取消后再选择");
        } else {
            ItemClickModel model = AffirmDetailsHelper.itemClick(sureList, position);
            view.reFreshAdapter(model.getSureList(), model.getCheck(), model.getRefuse());
        }
    }

    @Override
    public void onDataLoaded(Object o) {
        if(isNet.equals("getDetails")){
            getView().detailsSuccess((List<RspAffirmDetails>) o);
        }else if(isNet.equals("sure")){
            getView().sureSuccess();
        }
    }

    @Override
    public void onDataNotAvailable(String strRes) {
        getView().showError(strRes);
    }
}
