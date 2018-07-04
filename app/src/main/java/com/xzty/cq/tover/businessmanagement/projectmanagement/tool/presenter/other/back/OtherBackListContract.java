package com.xzty.cq.tover.businessmanagement.projectmanagement.tool.presenter.other.back;

import com.xzty.cq.tover.businessmanagement.common.factory.BaseContract;
import com.xzty.cq.tover.businessmanagement.projectmanagement.tool.model.other.RspBackList;

import java.util.List;

/**
 * author zzl
 * Created 2018/5/28.
 * explain
 */

public interface OtherBackListContract {

    interface View extends BaseContract.View<OtherBackListContract.Presenter> {
        void success(List<RspBackList>list);
    }

    interface Presenter extends BaseContract.Presenter {
        void getData();
    }
}
