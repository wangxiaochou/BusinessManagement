package com.xzty.cq.tover.businessmanagement.new_mainlist.all_fragment.presenter;

import android.util.Log;

import com.xzty.cq.tover.businessmanagement.common.model.RspModel;
import com.xzty.cq.tover.businessmanagement.common.net.NetWork;
import com.xzty.cq.tover.businessmanagement.new_mainlist.model.NewRspProjectListModel;
import com.xzty.cq.tover.businessmanagement.projectmanagement.common.api.RemoteService;

import java.util.List;

import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * author wl
 * Created 2018/08/21
 * explain 首页新项目列表All_Fragment_Helper
 */

public class All_Fragment_Helper {

    public static void search(final String id, String name,
                              final All_Fragment_Presenter callback) {
        RemoteService service = NetWork.remote(RemoteService.class);
        Subscription subscription = service.getNewProject(id, name).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<RspModel<List<NewRspProjectListModel>>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                Log.e("TAG", "请求错误" + e.getMessage());
                callback.onDataNotAvailable("请求错误" + e.getMessage());
            }

            @Override
            public void onNext(RspModel<List<NewRspProjectListModel>> listRspModel) {
                int code = listRspModel.getBackcode();
                List<NewRspProjectListModel> mode = listRspModel.getData();
                callback.onDataLoaded(mode);
            }

        });
    }

}
