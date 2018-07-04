package com.xzty.cq.tover.businessmanagement.projectmanagement.tool.presenter.pickdepartment.diliver;

import com.xzty.cq.tover.businessmanagement.common.factory.BaseContract;
import com.xzty.cq.tover.businessmanagement.projectmanagement.tool.model.pickdepartment.RspDiliverOrder;

import java.util.List;

/**
 * author zzl
 * Created 2018/5/25.
 * explain
 */

public interface DiliverOrderContract {

    interface View extends BaseContract.View<DiliverOrderContract.Presenter> {
        void success(List<RspDiliverOrder> list);

        void clickSuccess(List<RspDiliverOrder> list);

        void commitSuccess();
    }

    interface Presenter extends BaseContract.Presenter {
        void getDat();

        void itemClick(List<RspDiliverOrder> list,int position);

        void affirm(List<RspDiliverOrder> list,String firm,String contractNumber,String expectOutTime,String note);
    }

}
