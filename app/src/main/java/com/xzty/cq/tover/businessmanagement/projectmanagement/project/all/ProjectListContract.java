package com.xzty.cq.tover.businessmanagement.projectmanagement.project.all;

import com.xzty.cq.tover.businessmanagement.common.factory.BaseContract;

import java.util.List;

/**
 * author zzl
 * Created 2018/5/4.
 * explain 项目列表的契约类
 */

public interface ProjectListContract {

    interface View extends BaseContract.View<Presenter> {
        //搜索成功
        void success(List<RspProjectListModel> model);
    }

    interface Presenter extends BaseContract.Presenter {
        //发起一个搜索
        void search(String id ,String name);
    }
}
