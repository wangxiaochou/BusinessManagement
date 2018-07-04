package com.xzty.cq.tover.businessmanagement.projectmanagement.part.presenter.pick;

import com.xzty.cq.tover.businessmanagement.common.factory.BaseContract;
import com.xzty.cq.tover.businessmanagement.projectmanagement.part.model.pick.RspPickList;

import java.util.List;

/**
 * author zzl
 * Created 2018/5/14.
 * explain 采购单详细查看的Contract
 */

public interface PickDetailsContract {

    interface View extends BaseContract.View<PickDetailsContract.Presenter> {
        void success(List<RspPickList> list);
    }

    interface Presenter extends BaseContract.Presenter {
        void getData(String distId);
    }
}
