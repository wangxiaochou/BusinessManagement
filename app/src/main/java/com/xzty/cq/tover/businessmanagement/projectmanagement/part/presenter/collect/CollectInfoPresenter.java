package com.xzty.cq.tover.businessmanagement.projectmanagement.part.presenter.collect;

import com.xzty.cq.tover.businessmanagement.common.data.DataSourse;
import com.xzty.cq.tover.businessmanagement.common.factory.BasePresenter;
import com.xzty.cq.tover.businessmanagement.projectmanagement.part.data.collect.CollectInfoHelper;
import com.xzty.cq.tover.businessmanagement.projectmanagement.part.model.Emp;

import java.util.List;

/**
 * author zzl
 * Created 2018/5/11.
 * explain 分配信息填写的P
 */

public class CollectInfoPresenter extends BasePresenter<CollectInfoContract.View> implements CollectInfoContract.Presenter,DataSourse.Callback{
    private String isNet;
    public CollectInfoPresenter(CollectInfoContract.View view) {
        super(view);
    }

    @Override
    public void getAllBuyer() {
        start();
        final CollectInfoContract.View view = getView();
        isNet="emps";
       CollectInfoHelper.getAllBuyer(this);
       /* RemoteService service = NetWork.remote(RemoteService.class);
        Subscription subscription = service.queryAllBuyer().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<RspModel<List<Emp>>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                view.showError("请求失败" + e.getMessage());
            }

            @Override
            public void onNext(RspModel<List<Emp>> listRspModel) {
                int code = listRspModel.getBackcode();

                if (code == 0) {
                    view.showError("请求错误");
                } else {
                    List<Emp> temp = new ArrayList<>();
                    temp.add(new Emp("请选择", ""));
                    List<Emp> buyerList = JSON.parseArray(new Gson().toJson(listRspModel.getData()).toString(),
                            Emp.class);
                    for (Emp emp : buyerList) {
                        temp.add(emp);
                    }
                    view.allBuyer(temp);
                }
            }
        });*/
    }

    @Override
    public void collect(String employId, String remake, String choose) {
        final CollectInfoContract.View view = getView();
        isNet="collect";
        CollectInfoHelper.collect(employId,remake,choose,this);
    }

    /*@Override
    public void onDataLoaded(RspLogin rspLogin) {
        CollectInfoContract.View view = getView();
        view.saveBack();
    }*/

    @Override
    public void onDataNotAvailable(String strRes) {
        getView().showError(strRes);
    }

    @Override
    public void onDataLoaded(Object o) {
        if(isNet.equals("collect")){
            getView().saveBack();
        }else if(isNet.equals("emps")){
            getView().allBuyer((List<Emp>) o);
        }
    }
}
