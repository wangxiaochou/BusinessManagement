package com.xzty.cq.tover.businessmanagement.new_mainlist.presenter;

import com.xzty.cq.tover.businessmanagement.common.data.DataSourse;
import com.xzty.cq.tover.businessmanagement.common.factory.BasePresenter;
import com.xzty.cq.tover.businessmanagement.new_mainlist.contract.NewProjectListContract;
import com.xzty.cq.tover.businessmanagement.new_mainlist.model.NewRspProjectListModel;

import java.util.List;

/**
 * author wl
 * Created 2018/08/20
 * explain 首页新项目列表Presenter文件
 */

public class NewProjectListPresenter extends BasePresenter<NewProjectListContract.View>
        implements NewProjectListContract.Presenter, DataSourse.Callback<List<NewRspProjectListModel>> {

    public NewProjectListPresenter() {
        }

    public NewProjectListPresenter(NewProjectListContract.View view) {
            super(view);
        }

        @Override
        public void onDataLoaded(List<NewRspProjectListModel> listRspModel) {
            NewProjectListContract.View view = getView();
            view.success(listRspModel);
        }

        @Override
        public void search(String id, String name) {
            start();
            NewProjectListHelper.search(id, name, this);
        }

        @Override
        public void onDataNotAvailable(String strRes) {
            NewProjectListContract.View view = getView();
            view.showError(strRes);
        }

}
