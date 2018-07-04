package com.xzty.cq.tover.businessmanagement.projectmanagement.tool.presenter.other.diliver;

import com.xzty.cq.tover.businessmanagement.common.factory.BaseContract;
import com.xzty.cq.tover.businessmanagement.projectmanagement.tool.model.other.RspOtherDiliver;

import java.util.List;

/**
 * author zzl
 * Created 2018/5/25.
 * explain
 */

public interface OtherDiliverContract {

    interface View extends BaseContract.View<OtherDiliverContract.Presenter> {
        void dataSuccess(List<RspOtherDiliver> list);
    }

    interface Presenter extends BaseContract.Presenter {
        void getData();
    }
}
