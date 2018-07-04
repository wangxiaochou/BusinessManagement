package com.xzty.cq.tover.businessmanagement.projectmanagement.tool.presenter.other.back;

import com.xzty.cq.tover.businessmanagement.common.factory.BaseContract;
import com.xzty.cq.tover.businessmanagement.projectmanagement.tool.model.other.RspBackDetails;

import java.util.List;

/**
 * author zzl
 * Created 2018/5/28.
 * explain
 */

public interface OtherBackDetailsContract {

    interface View extends BaseContract.View<OtherBackDetailsContract.Presenter> {
        void success(List<RspBackDetails> list);
    }

    interface Presenter extends BaseContract.Presenter {
        void getData(int id);
    }
}
