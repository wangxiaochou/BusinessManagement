package com.xzty.cq.tover.businessmanagement.new_mainlist.ing_fragment.presenter;

import com.xzty.cq.tover.businessmanagement.common.data.DataSourse;
import com.xzty.cq.tover.businessmanagement.common.factory.BasePresenter;
import com.xzty.cq.tover.businessmanagement.new_mainlist.ing_fragment.contract.Ing_Fragment_Contract;
import com.xzty.cq.tover.businessmanagement.new_mainlist.model.NewRspProjectListModel;

import java.util.List;

/**
 * author wl
 * Created 2018/08/21
 * explain 首页新项目列表Ing_Fragment_Presenter
 */

public class Ing_Fragment_Presenter extends BasePresenter<Ing_Fragment_Contract.View>
        implements Ing_Fragment_Contract.Presenter,DataSourse.Callback<List<NewRspProjectListModel>>{

    public Ing_Fragment_Presenter() {
    }

    public Ing_Fragment_Presenter(Ing_Fragment_Contract.View view) {
        super(view);
    }

    @Override
    public void onDataLoaded(List<NewRspProjectListModel> listRspModel) {
        Ing_Fragment_Contract.View view = getView();
        view.success(listRspModel);
    }

    @Override
    public void search(String id, String name) {
        start();
        Ing_Fragment_Helper.search(id, name, this);
    }

    @Override
    public void onDataNotAvailable(String strRes) {
        Ing_Fragment_Contract.View view = getView();
        view.showError(strRes);
    }

}
