package com.xzty.cq.tover.businessmanagement.projectmanagement.part.presenter.use;

import com.xzty.cq.tover.businessmanagement.common.factory.BaseContract;
import com.xzty.cq.tover.businessmanagement.projectmanagement.part.model.use.AllModel;

import java.util.List;

/**
 * author zzl
 * Created 2018/5/16.
 * explain 领用选择构件的C
 */

public interface UseChooseContract {

    interface View extends BaseContract.View<UseChooseContract.Presenter> {
        void success(List<String> partNameList,
                     List<String> applyBatchList,
                     List<String> outBatchList,
                     List<AllModel> allList);

        //处理全部选中的逻辑
        void selectedAll(List<AllModel> mList, List<AllModel> thisTimeSelectedPartTemp);

        //单个选中的逻辑处理
        void selectedItem(List<AllModel> mList, List<AllModel> thisTimeSelectedPartTemp);
    }

    interface Presenter extends BaseContract.Presenter {
        void search(String time, String applyBatch, String outBatch, String partName, String partNo);

        //选中全部
        void chooseAll(List<AllModel> list, List<AllModel> thisTimeSelectedPartTemp, boolean status);

        //选中单个
        void itemClick(List<AllModel> list, List<AllModel> thisTimeSelectedPartTemp, int position);
    }
}
