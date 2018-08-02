package com.xzty.cq.tover.businessmanagement.projectmanagement.part.presenter.pick;

import android.text.TextUtils;

import com.xzty.cq.tover.businessmanagement.common.MyApplication;
import com.xzty.cq.tover.businessmanagement.common.data.DataSourse;
import com.xzty.cq.tover.businessmanagement.common.factory.Account;
import com.xzty.cq.tover.businessmanagement.common.factory.BasePresenter;
import com.xzty.cq.tover.businessmanagement.projectmanagement.part.data.apply.BuyerHelper;
import com.xzty.cq.tover.businessmanagement.projectmanagement.part.data.pick.PickOrderHelper;
import com.xzty.cq.tover.businessmanagement.projectmanagement.part.model.Emp;
import com.xzty.cq.tover.businessmanagement.projectmanagement.part.model.pick.RspPickOrder;

import java.util.List;

/**
 * author zzl
 * Created 2018/5/11.
 * explain 采购单中的P
 */

public class PickOrderPresenter extends BasePresenter<PickOrderContract.View>
        implements PickOrderContract.Presenter, DataSourse.Callback {

    private String isNet;

    public PickOrderPresenter(PickOrderContract.View view) {
        super(view);
    }

    @Override
    public void getBuyer() {
        start();
        isNet = "getBuyer";
        BuyerHelper.getBuyer(this);
    }

    @Override
    public void getPickOrder(String time, String distOrderState, String buyerId) {
        start();
        isNet = "getPickOrder";
        final PickOrderContract.View view = getView();
        PickOrderHelper.getPickOrder(time, distOrderState, buyerId,this);
    /*    Account.load(MyApplication.getInstance());
        String projectId = Account.getProjectId();
        final String[] times;
        if (time.contains("~")) {
            times = time.split("~");
        } else {
            times = new String[]{"", ""};
        }
        if (distOrderState.equals("全部")) {
            distOrderState = "";
        } else if (distOrderState.equals("未确认")) {
            distOrderState = "0";
        } else if (distOrderState.equals("已确认")) {
            distOrderState = "1";
        }

        Map<String, String> maps = new HashMap();
        maps.put("projectId", projectId);
        maps.put("beginDistTime", times[0]);
        maps.put("endDistTime", times[1]);
        maps.put("distOrderState", distOrderState);
        maps.put("buyerId", buyerId);
        RemoteServiceTask service = NetWork.remote(RemoteServiceTask.class);
        Subscription subscription = service.queryPurchaseList(maps).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<RspModel<List<RspPickOrder>>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                view.showError("请求失败" + e.getMessage());
            }

            @Override
            public void onNext(RspModel<List<RspPickOrder>> listRspModel) {
                int code = listRspModel.getBackcode();
                if (code == 0) {
                    view.showError("请求错误");
                } else {
                    view.getOrder(listRspModel.getData());
                }
            }
        });*/
    }

    @Override
    public void notaPick(String distId) {
        start();
        isNet = "notaPick";
        final PickOrderContract.View view = getView();
         PickOrderHelper.notaPick(distId,this);
      /*  RemoteServiceTask service = NetWork.remote(RemoteServiceTask.class);
        Subscription subscription = service.affirmPurchase(distId).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<RspModel<List<RspLogin>>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                view.showError("请求失败"+e.getMessage());
            }

            @Override
            public void onNext(RspModel<List<RspLogin>> listRspModel) {
                int code = listRspModel.getBackcode();
                if(code == 0){
                    view.showError("操作失败");
                }else{
                    view.notaSuccess();
                }
            }
        });*/
    }

  /*  @Override
    public void onDataLoaded(List<Emp> emps) {
        PickOrderContract.View view = getView();
        //获取选中的id
        int i = -1;
        Account.load(MyApplication.getInstance());
        String em = Account.getemployId();
        for (Emp emp : emps) {
            if (!TextUtils.isEmpty(emp.getEmployId())) {
                if (emp.getEmployId().equals(em)) {
                    i = emps.indexOf(emp);
                }
            }
        }
        view.backBuyer(emps, i);

    }*/

    @Override
    public void onDataNotAvailable(String strRes) {
        PickOrderContract.View view = getView();
        view.showError(strRes);
    }

    @Override
    public void onDataLoaded(Object o) {
        if(isNet.equals("getBuyer")){
            List<Emp> emps = (List<Emp>) o;
            int i = -1;
            Account.load(MyApplication.getInstance());
            String em = Account.getemployId();
            for (Emp emp : emps) {
                if (!TextUtils.isEmpty(emp.getEmployId())) {
                    if (emp.getEmployId().equals(em)) {
                        i = emps.indexOf(emp);
                    }
                }
            }
            getView().backBuyer(emps, i);
        }else if(isNet.equals("getPickOrder")){
            getView().getOrder((List<RspPickOrder>) o);
        }else if(isNet.equals("notaPick")){
            getView().notaSuccess();
        }
    }
}
