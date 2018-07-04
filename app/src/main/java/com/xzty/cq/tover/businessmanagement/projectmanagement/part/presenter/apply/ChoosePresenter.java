package com.xzty.cq.tover.businessmanagement.projectmanagement.part.presenter.apply;

import android.widget.CheckBox;

import com.xzty.cq.tover.businessmanagement.common.data.DataSourse;
import com.xzty.cq.tover.businessmanagement.common.factory.BasePresenter;
import com.xzty.cq.tover.businessmanagement.projectmanagement.part.data.apply.ChooseHelper;
import com.xzty.cq.tover.businessmanagement.projectmanagement.part.model.apply.RspPartList;

import java.util.ArrayList;
import java.util.List;

/**
 * author zzl
 * Created 2018/5/7.
 * explain 构件列表
 */

public class ChoosePresenter extends BasePresenter<ChooseContract.View>
        implements ChooseContract.Presenter, DataSourse.Callback<List<RspPartList>> {

    public ChoosePresenter(ChooseContract.View view) {
        super(view);
    }

    @Override
    public void searchPart(String projectid, String partName, String partNo,
                           String state, String partBatch) {

        //批次直接传空  2018-6-5改
        partBatch = "";
        //状态直接改为-1  2018-6-5改
        state = "-1";
        ChooseHelper.SearchChoose(projectid, partName, partNo, state, partBatch, this);
    }

    @Override
    public void chooseAll(List<RspPartList> list, List<RspPartList> thisTimeSelectedPartTemp, CheckBox checkBox) {
        ChooseContract.View view = getView();
        boolean status;
        if (!checkBox.isChecked()) {
            status = false;
        } else {
            status = true;
        }
        List<RspPartList> lists = ChooseHelper.chooseAll(list, status);
        view.selectedAll(lists);

      /*  //解决并发异常
        List<RspPartList> removeList = new ArrayList<>();
        //判断checkbox是否是选中状态
        if (!checkBox.isChecked()) {
            //false 清楚所有数据
            for (RspPartList select : list) {
                select.isCheck = false;
            }
            thisTimeSelectedPartTemp.clear();
        } else {
            //勾选所有
            for (RspPartList part : list) {
                for (RspPartList teamp : thisTimeSelectedPartTemp) {
                    if (part.getPartId() == teamp.getPartId()) {
                        teamp.isCheck = false;
                        removeList.add(part);
                    }
                }
                part.isCheck = true;
                thisTimeSelectedPartTemp.add(part);
            }
        }
        //统一移除
        Log.e("TAG", "需要移除的数据=" + removeList.size() + thisTimeSelectedPartTemp.size());
        for (int i = 0; i < removeList.size(); i++) {
            thisTimeSelectedPartTemp.remove(removeList.get(i));
        }
        //  thisTimeSelectedPartTemp.removeAll(removeList);
        view.selectedAll(list, thisTimeSelectedPartTemp);*/


    }

    @Override
    public void itemClick(List<RspPartList> list,
                          List<RspPartList> thisTimeSelectedPartTemp, int positin) {
        ChooseContract.View view = getView();
        list = ChooseHelper.itemClick(list,positin);
        view.selectedItem(list);
        /* //解决并发异常
       List<RspPartList> removeList = new ArrayList<>();

        if (list.get(positin).isCheck) {
            list.get(positin).isCheck = false;
            removeList.add(list.get(positin));
            thisTimeSelectedPartTemp.remove(list.get(positin));
        } else {
            list.get(positin).isCheck = true;
            removeList.add(list.get(positin));
            thisTimeSelectedPartTemp.add(list.get(positin));
        }
        view.selectedItem(list, thisTimeSelectedPartTemp);*/
    }

    @Override
    public void go(List<RspPartList> list) {
        ChooseContract.View view = getView();
        List<RspPartList> chooseList = new ArrayList<>();
        for(RspPartList lists : list){
            if(lists.isCheck){
                chooseList.add(lists);
            }
        }
        view.goCallBack(chooseList);
    }

    @Override
    public void onDataLoaded(List<RspPartList> lists) {
        ChooseContract.View view = getView();
        view.searchSuccess(lists);

        List<String> partBatchList = new ArrayList<>();
        List<String> partNameList = new ArrayList<>();
        partBatchList.add("全部");
        partNameList.add("全部");
        for (RspPartList projectPart : lists) {
            String batch = (projectPart.getPartBatch() == null || projectPart.getPartBatch().equals("")) ? "空" : projectPart.getPartBatch();
            if (!partBatchList.contains(batch)) {
                partBatchList.add(batch);
            }
            String partName = (projectPart.getPartName() == null || projectPart.getPartName().equals("")) ? "空" : projectPart.getPartName();
            if (!partNameList.contains(partName)) {
                partNameList.add(partName);
            }
        }
        view.disBatch(partNameList, partBatchList);
    }

    @Override
    public void onDataNotAvailable(String strRes) {
        ChooseContract.View view = getView();
        view.showError(strRes);

    }
}
