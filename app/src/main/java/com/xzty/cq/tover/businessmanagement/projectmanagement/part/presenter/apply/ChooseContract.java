package com.xzty.cq.tover.businessmanagement.projectmanagement.part.presenter.apply;

import android.widget.CheckBox;

import com.xzty.cq.tover.businessmanagement.common.factory.BaseContract;
import com.xzty.cq.tover.businessmanagement.projectmanagement.part.model.apply.RspPartList;

import java.util.List;

/**
 * author zzl
 * Created 2018/5/7.
 * explain 构件列表
 */

public interface ChooseContract {
    interface View extends BaseContract.View<ChooseContract.Presenter> {
        //搜索成功
        void searchSuccess(List<RspPartList> list);

        //处理数据
        void disBatch(List<String> list, List<String> list2);

        //处理全部选中的逻辑
        void selectedAll(List<RspPartList> mList);

        //单个选中的逻辑处理
        void selectedItem(List<RspPartList> mList);

        void goCallBack(List<RspPartList> mList);
    }

    interface Presenter extends BaseContract.Presenter {
        //搜索
        void searchPart(String projectid, String partName, String partNo, String state, String partBatch);

        //选中全部
        void chooseAll(List<RspPartList> list, List<RspPartList> thisTimeSelectedPartTemp, CheckBox checkBox);

        //选中单个
        void itemClick(List<RspPartList> list, List<RspPartList> thisTimeSelectedPartTemp,int position);

        void go(List<RspPartList> list);
    }
}
