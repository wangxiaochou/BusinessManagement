package com.xzty.cq.tover.businessmanagement.projectmanagement.part.presenter.pick;

import com.xzty.cq.tover.businessmanagement.common.factory.BaseContract;
import com.xzty.cq.tover.businessmanagement.projectmanagement.part.model.pick.RspPickList;

import java.util.List;

/**
 * author zzl
 * Created 2018/5/11.
 * explain 选择构件的Contract
 */

public interface PickChooseContract {

    interface View extends BaseContract.View<PickChooseContract.Presenter> {
        void callList(List<RspPickList> list, List<String> list1, List<String> list2);

        void searchSuccess(List<RspPickList> successList);

        void chooseAllSuccess(List<RspPickList> list, List<RspPickList> chooseList, int size, double count);

        void chooseItmeSuccess(List<RspPickList> list, List<RspPickList> chooseList, int size, double count);

        void showDialog();

        void startNota();
    }

    interface Presenter extends BaseContract.Presenter {
        void getList(String distId);

        void search(List<RspPickList> list, String partName, String partNo, String partBatch, String time);

        //选中所有
        void chooseAll(List<RspPickList> list, boolean status, List<RspPickList> chooseList);

        //选中单个
        void chooseItem(List<RspPickList> list, List<RspPickList> chooseList, int position);

        //判断是否满足确认条件
        void checkNota(List<RspPickList> list, List<RspPickList> chooseList);

        //确定
        void nota();
    }

}
