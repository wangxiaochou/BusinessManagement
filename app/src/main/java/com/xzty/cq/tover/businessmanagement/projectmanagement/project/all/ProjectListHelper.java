package com.xzty.cq.tover.businessmanagement.projectmanagement.project.all;

import android.util.Log;

import com.xzty.cq.tover.businessmanagement.common.model.RspModel;
import com.xzty.cq.tover.businessmanagement.common.net.NetWork;
import com.xzty.cq.tover.businessmanagement.projectmanagement.common.api.RemoteService;

import java.util.List;

import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * author zzl
 * Created 2018/5/4.
 * explain 工程列表帮助类
 */

public class ProjectListHelper {

    public static void search(final String id, String name,
                              final ProjectListPresenter callback) {
        RemoteService service = NetWork.remote(RemoteService.class);
        Subscription subscription = service.getProject(id, name).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<RspModel<List<RspProjectListModel>>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                Log.e("TAG", "请求错误" + e.getMessage());
                callback.onDataNotAvailable("请求错误" + e.getMessage());
            }

            @Override
            public void onNext(RspModel<List<RspProjectListModel>> listRspModel) {
                int code = listRspModel.getBackcode();
                List<RspProjectListModel> mode = listRspModel.getData();
                callback.onDataLoaded(mode);
            }

        });
    }

}
