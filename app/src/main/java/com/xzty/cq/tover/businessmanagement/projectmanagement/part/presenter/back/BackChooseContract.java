package com.xzty.cq.tover.businessmanagement.projectmanagement.part.presenter.back;

import com.xzty.cq.tover.businessmanagement.common.factory.BaseContract;
import com.xzty.cq.tover.businessmanagement.projectmanagement.part.model.back.BackPart;

import java.util.List;

/**
 * author zzl
 * Created 2018/5/17.
 * explain
 */

public interface BackChooseContract {

    interface View extends BaseContract.View<BackChooseContract.Presenter> {
        void success(List<BackPart> partList, List<String> partNameList,
                     List<String> pickBatchList
        );

        //处理全部选中的逻辑
        void selectedAll(List<BackPart> mList, List<BackPart> thisTimeSelectedPartTemp);

        //单个选中的逻辑处理
        void selectedItem(List<BackPart> mList, List<BackPart> thisTimeSelectedPartTemp);
    }

    interface Presenter extends BaseContract.Presenter {
        void search(String time, String applyBatch, String partName, String partNo);

        //选中全部
        void chooseAll(List<BackPart> list, boolean satatus);

        //选中单个
        void itemClick(List<BackPart> list, List<BackPart> thisTimeSelectedPartTemp, int position);
    }
}
