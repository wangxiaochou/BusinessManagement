package com.xzty.cq.tover.businessmanagement.projectmanagement.tool.presenter.projectmanage.apply;

import android.util.SparseArray;

import com.xzty.cq.tover.businessmanagement.common.factory.BaseContract;
import com.xzty.cq.tover.businessmanagement.projectmanagement.tool.model.projectmanage.ReqToolApply;
import com.xzty.cq.tover.businessmanagement.projectmanagement.tool.model.projectmanage.RspToolApplyList;

import java.util.List;

/**
 * author zzl
 * Created 2018/5/22.
 * explain
 */

public interface ToolApplayDetailsContract {

    interface View extends BaseContract.View<ToolApplayDetailsContract.Presenter> {
        void submitSuccess();

        void calculateSuccess(List<RspToolApplyList> lists);
    }

    interface Presenter extends BaseContract.Presenter {
        void submit(ReqToolApply details);

        void add(List<RspToolApplyList> list, int position, SparseArray<Double> countList);

        void minus(List<RspToolApplyList> list, int position);
    }
}
