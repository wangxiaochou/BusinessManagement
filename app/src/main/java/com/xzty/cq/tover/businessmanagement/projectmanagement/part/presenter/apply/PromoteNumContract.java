package com.xzty.cq.tover.businessmanagement.projectmanagement.part.presenter.apply;

import com.xzty.cq.tover.businessmanagement.common.factory.BaseContract;
import com.xzty.cq.tover.businessmanagement.projectmanagement.part.model.apply.RspPartList;

import java.util.List;

/**
 * author zzl
 * Created 2018/6/5.
 * explain
 */

public interface PromoteNumContract {

    interface View extends BaseContract.View<PromoteNumContract.Presenter> {
        void dataSuccess(List<RspPartList> lists, List<String> list);

        void clickSuccess(List<RspPartList> lists);

        void submitSuccess();
    }

    interface Presenter extends BaseContract.Presenter {
        void getData(String partName, String partNo);

        void minusClick(List<RspPartList> lists, int position);

        void addClick(List<RspPartList> lists, int position);

        void itemClick(List<RspPartList> lists, int position);

        void chooseAllClick(boolean status, List<RspPartList> lists);

        void submit(List<RspPartList> lists);
    }
}
