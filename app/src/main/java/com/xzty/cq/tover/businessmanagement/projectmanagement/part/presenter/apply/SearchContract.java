package com.xzty.cq.tover.businessmanagement.projectmanagement.part.presenter.apply;

import com.xzty.cq.tover.businessmanagement.common.factory.BaseContract;
import com.xzty.cq.tover.businessmanagement.projectmanagement.part.model.apply.RspApplyList;

import java.util.List;

/**
 * author zzl
 * Created 2018/5/5.
 * explain 申请列表 搜索的contract
 */

public interface SearchContract {

    interface View extends BaseContract.View<Presenter> {
        //搜索成功
        void searchSuccess(List<RspApplyList> list);

        void nataBack();
    }

    interface Presenter extends BaseContract.Presenter {
        //搜索
        void search(String projectId, String applyPerson, String time,
                    String applyOrderState);
        //确认申请
        void notaApply(String applyId);
    }

}
