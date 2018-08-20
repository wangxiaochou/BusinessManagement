package com.xzty.cq.tover.businessmanagement.new_mainlist.contract;

import com.xzty.cq.tover.businessmanagement.common.factory.BaseContract;
import com.xzty.cq.tover.businessmanagement.new_mainlist.model.NewRspProjectListModel;

import java.util.List;

/**
 * author wl
 * Created 2018/08/20
 * explain 首页新项目列表Contract
 */

public interface NewProjectListContract {

    interface View extends BaseContract.View<NewProjectListContract.Presenter> {
        //搜索成功
        void success(List<NewRspProjectListModel> model);
    }

    interface Presenter extends BaseContract.Presenter {
        //发起一个搜索
        void search(String id ,String name);
    }

}
