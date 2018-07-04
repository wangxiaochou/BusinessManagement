package com.xzty.cq.tover.businessmanagement.projectmanagement.tool.presenter.other.receive;

import com.xzty.cq.tover.businessmanagement.common.factory.BaseContract;
import com.xzty.cq.tover.businessmanagement.projectmanagement.tool.model.other.RspReceiveList;

import java.util.List;

/**
 * author zzl
 * Created 2018/5/26.
 * explain
 */

public interface OtherReceiveListContrat {


    interface View extends BaseContract.View<OtherReceiveListContrat.Presenter>{
        void success(List<RspReceiveList> list);

    }


    interface Presenter extends BaseContract.Presenter{
        void getData();
    }
}
