package com.xzty.cq.tover.businessmanagement.projectmanagement.part.data.income;

import com.xzty.cq.tover.businessmanagement.common.data.DataSourse;
import com.xzty.cq.tover.businessmanagement.common.model.RspModel;
import com.xzty.cq.tover.businessmanagement.common.net.NetWork;
import com.xzty.cq.tover.businessmanagement.projectmanagement.common.api.RemoteService;
import com.xzty.cq.tover.businessmanagement.projectmanagement.part.model.deliver.DeliverDetails;
import com.xzty.cq.tover.businessmanagement.projectmanagement.part.model.income.RspReceiveChoose;

import java.util.ArrayList;
import java.util.List;

import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * author zzl
 * Created 2018/6/11.
 * explain
 */

public class ReceiveChooseHelper {
    public static void getArray(String outId, final DataSourse.Callback<List<RspReceiveChoose>> callback) {
        RemoteService service = NetWork.remote(RemoteService.class);
        Subscription subscription = service.getArriveInfo(outId).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<RspModel<List<RspReceiveChoose>>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                callback.onDataNotAvailable("请求失败");
            }

            @Override
            public void onNext(RspModel<List<RspReceiveChoose>> listRspModel) {
                int code = listRspModel.getBackcode();
                if (code == 1) {
                    callback.onDataLoaded(listRspModel.getData());
                } else {
                    callback.onDataNotAvailable("请求错误");
                }
            }
        });
    }

    public static ReceiveChooseModel chooseAll(List<DeliverDetails> list, boolean status) {
        int countType = 0;
        int countNum = 0;
        if (status) {
            for (DeliverDetails details : list) {
                details.isCheck = true;
            }
        } else {
            for (DeliverDetails details : list) {
                details.isCheck = false;
            }
        }

        for (DeliverDetails details : list) {
            if (details.isCheck) {
                countType++;
                countNum += details.getApplyItemCount();
            }
        }
        ReceiveChooseModel model = new ReceiveChooseModel();
        model.setCountNum(countNum);
        model.setCountType(countType);
        model.setList(list);
        return model;
    }


    public static ReceiveChooseModel chooseOne(List<DeliverDetails> list, int position, int countType, int countNum) {
        ReceiveChooseModel model = new ReceiveChooseModel();

        if (list.get(position).isCheck) {
            list.get(position).isCheck = false;
            countType--;
            countNum -= list.get(position).getApplyItemCount();
        } else {
            list.get(position).isCheck = true;
            countType++;
            countNum += list.get(position).getApplyItemCount();
        }
        model.setCountNum(countNum);
        model.setCountType(countType);
        model.setList(list);
        return model;
    }


    public static RspModel confirm(List<DeliverDetails> list, int countType) {
        RspModel<List<DeliverDetails>> model = new RspModel<List<DeliverDetails>>();
        List<DeliverDetails> finalList = new ArrayList<>();
        for (DeliverDetails details : list) {
            if (details.isCheck) {
                finalList.add(details);
            }
        }
        if (countType == 0) {
            return null;
        } else if (countType < list.size()) {
            model.setMessage("warning");
            model.setData(finalList);
        } else {
            model.setMessage("ok");
            model.setData(finalList);
        }
        return model;
    }
}
