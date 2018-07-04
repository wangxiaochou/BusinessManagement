package com.xzty.cq.tover.businessmanagement.projectmanagement.tool.presenter.projectmanage.apply;

import android.widget.CheckBox;

import com.xzty.cq.tover.businessmanagement.common.factory.BaseContract;
import com.xzty.cq.tover.businessmanagement.projectmanagement.tool.model.projectmanage.RspToolApplyList;

import java.util.List;

/**
 * author zzl
 * Created 2018/5/21.
 * explain 机具申请的搜素C
 */

public interface ToolApplayListContract {

    interface View extends BaseContract.View<ToolApplayListContract.Presenter> {
        void searchSuccess(List<RspToolApplyList> list);

        void itemClickSuccess(List<RspToolApplyList> list, List<RspToolApplyList> newCreateToolList, List<Integer> newListPosition,boolean checkAll);

        void chooseAllSuccess(List<RspToolApplyList> list, List<RspToolApplyList> newCreateToolList, List<Integer> newListPosition);

        void go(String tools, String newTools);
    }

    interface Presenter extends BaseContract.Presenter {
        void search(String searchToolName);

        void itemClick(List<RspToolApplyList> list, int position, List<RspToolApplyList> newCreateToolList, List<Integer> newListPosition);

        void chooseAll(List<RspToolApplyList> list, CheckBox checkBox, List<RspToolApplyList> newCreateToolList, List<Integer> newListPosition);

        void isGo(List<RspToolApplyList> list, List<RspToolApplyList> newList, List<Integer> newListPosition);
    }
}
