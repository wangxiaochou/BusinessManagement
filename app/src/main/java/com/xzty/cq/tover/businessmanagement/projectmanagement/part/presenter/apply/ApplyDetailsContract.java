package com.xzty.cq.tover.businessmanagement.projectmanagement.part.presenter.apply;

import com.xzty.cq.tover.businessmanagement.common.factory.BaseContract;
import com.xzty.cq.tover.businessmanagement.projectmanagement.part.model.apply.RspApplyDetails;

import java.util.List;

/**
 * author zzl
 * Created 2018/5/10.
 * explain 申请详情的contract
 */

public interface ApplyDetailsContract {

    interface View extends BaseContract.View<ApplyDetailsContract.Presenter> {
        //详情
        void success(List<RspApplyDetails> mlist);
    }

    interface Presenter extends BaseContract.Presenter {
        void getDetails(String applyId);
    }
}
