package com.xzty.cq.tover.businessmanagement.projectmanagement.tool.presenter.projectmanage.affirm;

import com.xzty.cq.tover.businessmanagement.common.factory.BaseContract;
import com.xzty.cq.tover.businessmanagement.projectmanagement.tool.model.projectmanage.RspAffirmList;

import java.util.List;

/**
 * author zzl
 * Created 2018/5/23.
 * explain 申请单确认
 */

public interface AffirmListContract {

    interface View extends BaseContract.View<AffirmListContract.Presenter>{
        void success(List<RspAffirmList>list);
    }

    interface Presenter extends BaseContract.Presenter{
        void getApplyList(String id);
    }
}
