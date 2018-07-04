package com.xzty.cq.tover.businessmanagement.projectmanagement.part.presenter.collect;

import com.xzty.cq.tover.businessmanagement.common.factory.BaseContract;
import com.xzty.cq.tover.businessmanagement.projectmanagement.part.model.collect.RspCollect;

import java.util.List;

/**
 * author zzl
 * Created 2018/5/10.
 * explain 申请汇总的 contract
 */

public interface CollectContract {

    interface View extends BaseContract.View<CollectContract.Presenter> {
        void netSuccess(List<RspCollect> list, List<String> partNameList, List<String> applybatchList);

        void chooseAllBack(List<RspCollect> list, List<RspCollect> chooseCount);

        void chooseItemBack(List<RspCollect> list,List<RspCollect> chooseCount);
    }

    interface Presenter extends BaseContract.Presenter {
        void getAllCollect(String partName, String partNo, String applyBatch, String time);

        void chooseAll(List<RspCollect> list, boolean status);

        void itemChoose(List<RspCollect> list, int position);
    }
}