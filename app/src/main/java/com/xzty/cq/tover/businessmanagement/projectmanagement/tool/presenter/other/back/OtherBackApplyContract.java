package com.xzty.cq.tover.businessmanagement.projectmanagement.tool.presenter.other.back;

import com.xzty.cq.tover.businessmanagement.common.factory.BaseContract;
import com.xzty.cq.tover.businessmanagement.projectmanagement.tool.model.other.RspBackApply;

import java.util.List;

/**
 * author zzl
 * Created 2018/5/26.
 * explain
 */

public interface OtherBackApplyContract {

    interface View extends BaseContract.View<OtherBackApplyContract.Presenter> {
        void dataSuccess(List<RspBackApply> list);

        void clickSuccess(List<RspBackApply> list);

        void applySuccess();
    }

    interface Presenter extends BaseContract.Presenter {
        void getData();

        void itemClick(List<RspBackApply> list, int position);

        void apply(List<RspBackApply> list, String note);
    }
}
