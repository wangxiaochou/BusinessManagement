package com.xzty.cq.tover.businessmanagement.projectmanagement.part.data.apply;

import com.xzty.cq.tover.businessmanagement.common.data.DataSourse;
import com.xzty.cq.tover.businessmanagement.common.model.RspModel;
import com.xzty.cq.tover.businessmanagement.common.net.NetWork;
import com.xzty.cq.tover.businessmanagement.projectmanagement.common.api.RemoteService;
import com.xzty.cq.tover.businessmanagement.projectmanagement.part.model.apply.RspPartList;

import java.util.List;

import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * author zzl
 * Created 2018/5/7.
 * explain  选择构件界面的帮助类
 */

public class ChooseHelper {
    public static void SearchChoose(String projectId, String partName, String partNo,
                                    String state, String partBatch, final DataSourse.Callback<List<RspPartList>> callback) {
        RemoteService service = NetWork.remote(RemoteService.class);
        Subscription subscription = service.queryAllPart(projectId, partName, partNo, state, partBatch)
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<RspModel<List<RspPartList>>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        callback.onDataNotAvailable("请求错误");
                    }

                    @Override
                    public void onNext(RspModel<List<RspPartList>> listRspModel) {
                        int code = listRspModel.getBackcode();
                        if (code == 1) {
                            List<RspPartList> mList = listRspModel.getData();
                            callback.onDataLoaded(mList);
                        } else {
                            callback.onDataNotAvailable("请求错误");
                        }
                    }
                });
    }

    public static void minusClick(List<RspPartList> lists, int position) {
        double num = lists.get(position).getNeedCount();
        if (num > 0) {
            num--;
            lists.get(position).setNeedCount(num);

        } else {

        }
    }

    public static List<RspPartList> chooseAll(List<RspPartList> lists, boolean status) {
        if (!status) {
            for (RspPartList partList : lists) {
                partList.isCheck = false;
            }
        } else {
            for (RspPartList partList : lists) {
                partList.isCheck = true;
            }
        }
        return lists;
    }

    public static List<RspPartList> itemClick(List<RspPartList> lists, int position) {
        if (lists.get(position).isCheck) {
            lists.get(position).isCheck = false;
        } else {
            lists.get(position).isCheck = true;
        }
        return lists;
    }
}
