package com.xzty.cq.tover.businessmanagement.projectmanagement.part.presenter.apply;

import com.xzty.cq.tover.businessmanagement.common.data.DataSourse;
import com.xzty.cq.tover.businessmanagement.common.factory.BasePresenter;
import com.xzty.cq.tover.businessmanagement.projectmanagement.part.data.apply.SearchHelper;
import com.xzty.cq.tover.businessmanagement.projectmanagement.part.model.apply.RspApplyList;

import java.util.List;

/**
 * author zzl
 * Created 2018/5/5.
 * explain 搜索逻辑的实现
 */

public class SearchPresenter extends BasePresenter<SearchContract.View>
        implements SearchContract.Presenter, DataSourse.Callback {
    public SearchPresenter(SearchContract.View view) {
        super(view);
    }

    private String isNet;

    @Override
    public void search(String projectId, String applyTime,
                       String applyPerson, String applyOrderState) {
        if (applyOrderState.equals("全部")) {
            applyOrderState = "";
        } else if (applyOrderState.equals("未确认")) {
            applyOrderState = "0";
        } else if (applyOrderState.equals("已确认")) {
            applyOrderState = "1";
        }
        String[] times;
        if (applyTime.contains("~")) {
            times = applyTime.split("~");
        } else {
            times = new String[]{"", ""};
        }
        isNet = "search";
        SearchHelper.search(projectId, applyPerson, times[0], times[1], applyOrderState, this);
    }

    @Override
    public void notaApply(String applyId) {
        final SearchContract.View view = getView();
        isNet = "notaApply";
        SearchHelper.noteApply(applyId, this);

    }

    @Override
    public void onDataNotAvailable(String strRes) {
        SearchContract.View view = getView();
        view.showError(strRes);
    }

  /*  @Override
    public void onDataLoaded(List<RspApplyList> rspApplyLists) {
        SearchContract.View view = getView();

    }*/

    @Override
    public void onDataLoaded(Object o) {
        if (isNet.equals("search")) {
            getView().searchSuccess((List<RspApplyList>) o);
        } else if (isNet.equals("notaApply")) {
            getView().nataBack();
        }
    }
}
