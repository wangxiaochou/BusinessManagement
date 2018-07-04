package com.xzty.cq.tover.businessmanagement.projectmanagement.tool.data.projectmanage.affirm;

import com.google.gson.Gson;
import com.xzty.cq.tover.businessmanagement.common.data.DataSourse;
import com.xzty.cq.tover.businessmanagement.common.model.RspLogin;
import com.xzty.cq.tover.businessmanagement.common.model.RspModel;
import com.xzty.cq.tover.businessmanagement.common.net.NetWork;
import com.xzty.cq.tover.businessmanagement.projectmanagement.common.api.RemoteService;
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
 * Created 2018/6/13.
 * explain
 */

public class AffirmDetailsHelper {
    public static void getDetails(int id, final DataSourse.Callback callback) {
        RemoteService service = NetWork.remote(RemoteService.class);
        Subscription subscription = service.getToolApplyConfirmDetail(id).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<RspModel<List<RspAffirmDetails>>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                callback.onDataNotAvailable("请求失败" + e.getMessage());
            }

            @Override
            public void onNext(RspModel<List<RspAffirmDetails>> listRspModel) {
                int code = listRspModel.getBackcode();
                if (code == 1) {
                    callback.onDataLoaded(listRspModel.getData());
                } else {
                    callback.onDataNotAvailable("请求错误");
                }
            }
        });
    }

    public static void sure(final List<RspAffirmDetails> sureList, final int applyId, final DataSourse.Callback callback) {
        List<Integer> intList = new ArrayList<>();
        //取出被拒绝
        for (RspAffirmDetails details : sureList) {
            if (details.isRefuse) {
                intList.add(details.getToolApplyDetailId());
            }
        }
        if (intList.size() > 0) {
            RemoteService service = NetWork.remote(RemoteService.class);
            Subscription subscription = service.refuseApply(new Gson().toJson(intList)).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<RspModel<List<RspLogin>>>() {
                @Override
                public void onCompleted() {

                }

                @Override
                public void onError(Throwable e) {
                    callback.onDataNotAvailable("请求失败" + e.getMessage());
                }

                @Override
                public void onNext(RspModel<List<RspLogin>> listRspModel) {
                    int code = listRspModel.getBackcode();
                    if (code == 1) {
                        commit(sureList, applyId, callback);
                    } else {
                        callback.onDataNotAvailable("请求错误");
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
            commit(sureList, applyId, callback);
        }
    }

    private static void commit(List<RspAffirmDetails> sureList, int applyId, final DataSourse.Callback callback) {
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
                callback.onDataNotAvailable("请求失败" + e.getMessage());
            }

            @Override
            public void onNext(RspModel<List<RspLogin>> listRspModel) {
                int code = listRspModel.getBackcode();
                if (code == 1) {
                    callback.onDataLoaded(listRspModel.getData());
                } else {
                    callback.onDataNotAvailable("请求错误");
                }
            }
        });
    }

    public static ItemClickModel longClick(List<RspAffirmDetails> sureList, int position) {
        ItemClickModel model = new ItemClickModel();
        if (sureList.get(position).isRefuse) {
            sureList.get(position).isRefuse = false;
        } else {
            sureList.get(position).isRefuse = true;
        }

        int check = 0, refuse = 0;
        for (RspAffirmDetails detail : sureList) {
            if (detail.isChecked) {
                check++;
            }
            if (detail.isRefuse) {
                refuse++;
            }
        }
        model.setSureList(sureList);
        model.setCheck(check);
        model.setRefuse(refuse);
        return model;
    }

    public static ItemClickModel itemClick(List<RspAffirmDetails> sureList, int position) {
        ItemClickModel model = new ItemClickModel();
        if (sureList.get(position).isChecked) {
            sureList.get(position).isChecked = false;
        } else {
            sureList.get(position).isChecked = true;
        }
        int check = 0, refuse = 0;
        for (RspAffirmDetails detail : sureList) {
            if (detail.isChecked) {
                check++;
            }
            if (detail.isRefuse) {
                refuse++;
            }
        }
        model.setSureList(sureList);
        model.setCheck(check);
        model.setRefuse(refuse);
        return model;
    }


}
