package com.xzty.cq.tover.businessmanagement.new_mainlist.all_fragment.presenter;

import com.xzty.cq.tover.businessmanagement.common.data.DataSourse;
import com.xzty.cq.tover.businessmanagement.common.factory.BasePresenter;
import com.xzty.cq.tover.businessmanagement.new_mainlist.all_fragment.contract.All_Fragment_Contract;
import com.xzty.cq.tover.businessmanagement.new_mainlist.model.NewRspProjectListModel;

import java.util.List;

/**
 * author wl
 * Created 2018/08/21
 * explain 首页新项目列表All_Fragment_Presenter
 */

public class All_Fragment_Presenter extends BasePresenter<All_Fragment_Contract.View>
        implements All_Fragment_Contract.Presenter,DataSourse.Callback<List<NewRspProjectListModel>>{

    public All_Fragment_Presenter() {
    }

    public All_Fragment_Presenter(All_Fragment_Contract.View view) {
        super(view);
    }

    @Override
    public void onDataLoaded(List<NewRspProjectListModel> listRspModel) {
        All_Fragment_Contract.View view = getView();
        view.success(listRspModel);
    }

    @Override
    public void search(String id, String name) {
        start();
        All_Fragment_Helper.search(id, name, this);
    }

    @Override
    public void onDataNotAvailable(String strRes) {
        All_Fragment_Contract.View view = getView();
        view.showError(strRes);
    }

}
