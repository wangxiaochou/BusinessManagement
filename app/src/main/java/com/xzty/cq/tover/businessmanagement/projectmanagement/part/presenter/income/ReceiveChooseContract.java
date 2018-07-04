package com.xzty.cq.tover.businessmanagement.projectmanagement.part.presenter.income;

import com.xzty.cq.tover.businessmanagement.common.factory.BaseContract;
import com.xzty.cq.tover.businessmanagement.projectmanagement.part.model.deliver.DeliverDetails;

import java.util.List;

/**
 * author zzl
 * Created 2018/5/15.
 * explain 选择实际到场件的C
 */

public interface ReceiveChooseContract {

    interface View extends BaseContract.View<ReceiveChooseContract.Presenter> {
        void success(List<DeliverDetails> list);

        void chooseAllBack(List<DeliverDetails> list, int countType, int countNum);

        void chooseOneBack(List<DeliverDetails> list, int countType, int countNum);

        void warningBack(List<DeliverDetails> finalList);

        void ok(List<DeliverDetails> finalList);
    }

    interface Presenter extends BaseContract.Presenter {
        void getArray(String outId);

        void chooseAll(List<DeliverDetails> list, boolean status);

        void chooseOne(List<DeliverDetails> list, int position, int countType, int countNum);

        void confirm(List<DeliverDetails> list,int countType);
    }
}
