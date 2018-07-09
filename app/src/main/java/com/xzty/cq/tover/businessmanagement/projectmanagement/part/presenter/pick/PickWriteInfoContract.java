package com.xzty.cq.tover.businessmanagement.projectmanagement.part.presenter.pick;

import com.xzty.cq.tover.businessmanagement.common.factory.BaseContract;
import com.xzty.cq.tover.businessmanagement.projectmanagement.part.model.pick.RspPickList;

import java.util.List;

/**
 * author zzl
 * Created 2018/5/14.
 * explain 填写采购信息的Contract
 */

public interface PickWriteInfoContract {

    interface View extends BaseContract.View<PickWriteInfoContract.Presenter> {
        void ok();

        void clickBack(List<RspPickList> mList);
    }

    interface Presenter extends BaseContract.Presenter {
        void commit(String firm, String contractNo, String expectOutTime, String outNote, String distId, String partList);

        void itemClick(List<RspPickList> mList,int position);

        void chooseAll(List<RspPickList> mList,boolean status);

        void writePrice(List<RspPickList> mList,String price);
    }
}
