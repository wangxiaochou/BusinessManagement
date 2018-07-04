package com.xzty.cq.tover.businessmanagement.projectmanagement.part.presenter.apply;

import android.util.SparseArray;
import android.widget.CheckBox;

import com.xzty.cq.tover.businessmanagement.common.factory.BaseContract;
import com.xzty.cq.tover.businessmanagement.common.model.RspLogin;
import com.xzty.cq.tover.businessmanagement.projectmanagement.part.model.apply.RspPartList;

import java.util.List;

/**
 * author zzl
 * Created 2018/5/9.
 * explain 申请信息填写的契约
 */

public interface SearchInfoContract {

    interface View extends BaseContract.View<SearchInfoContract.Presenter> {
        //搜索成功
        void searchSuccess(List<RspPartList> list, List<String> partBatchList, List<String> partNameList, SparseArray<Double> countList);

        //全选
        void selectedAllBack(List<RspPartList> list);

        //单选
        void singlecheckBox(List<RspPartList> list);

        //增加需求量
        void needCount(List<RspPartList> list);

        //减去需求量
        void minusNeedCount(List<RspPartList> list);


        //提交成功
        void submitSuccess(RspLogin rspLogin);
        //设置时间
   //     void setjoinTime(List<RspPartList> list);
    }

    interface Presenter extends BaseContract.Presenter {
        //搜索
        void search(List<RspPartList> list, String partName, String PartBatch);

        //全选
        void selectedAll(List<RspPartList> list, CheckBox checkBox);

        //单个CheckBox的点击事件
        void checkBoxClick(List<RspPartList> list, int position);

        //添加需求量
        void addNeedCount(List<RspPartList> list, int position);

        //减去需求量
        void minusNeedCount(List<RspPartList> list, int position);

        //提交

        void submit(String applyNote,List<RspPartList> list);
        //设置时间
     //   void setTime(List<RspPartList> list, int position);
    }
}
