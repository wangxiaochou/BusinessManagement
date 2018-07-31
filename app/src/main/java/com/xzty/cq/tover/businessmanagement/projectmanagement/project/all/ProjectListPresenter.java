package com.xzty.cq.tover.businessmanagement.projectmanagement.project.all;

import com.xzty.cq.tover.businessmanagement.common.data.DataSourse;
import com.xzty.cq.tover.businessmanagement.common.factory.BasePresenter;

import java.util.List;

/**
 * author zzl
 * Created 2018/5/4.
 * explain 项目列表逻辑的实现
 */

public class ProjectListPresenter extends BasePresenter<ProjectListContract.View>
        implements ProjectListContract.Presenter, DataSourse.Callback<List<RspProjectListModel>> {

    public ProjectListPresenter() {
    }

    public ProjectListPresenter(ProjectListContract.View view) {
        super(view);
    }

    @Override
    public void onDataLoaded(List<RspProjectListModel> listRspModel) {
        ProjectListContract.View view = getView();
        view.success(listRspModel);
    }

    @Override
    public void search(String id, String name) {
        start();
        ProjectListHelper.search(id, name, this);
    }

    @Override
    public void onDataNotAvailable(String strRes) {
        ProjectListContract.View view = getView();
        view.showError(strRes);
    }
}
