package com.xzty.cq.tover.businessmanagement.projectmanagement.part.presenter.pick;

import com.xzty.cq.tover.businessmanagement.common.data.DataSourse;
import com.xzty.cq.tover.businessmanagement.common.factory.BasePresenter;
import com.xzty.cq.tover.businessmanagement.projectmanagement.part.data.pick.PickChooseHelper;
import com.xzty.cq.tover.businessmanagement.projectmanagement.part.data.pick.PickChooseList;
import com.xzty.cq.tover.businessmanagement.projectmanagement.part.data.pick.PickChooseModel;
import com.xzty.cq.tover.businessmanagement.projectmanagement.part.model.pick.RspPickList;

import java.util.List;

/**
 * author zzl
 * Created 2018/5/11.
 * explain 选择构件中的Presenter
 */

public class PickChoosePresenter extends BasePresenter<PickChooseContract.View>
        implements PickChooseContract.Presenter, DataSourse.Callback<PickChooseList> {
    public PickChoosePresenter(PickChooseContract.View view) {
        super(view);
    }

    @Override
    public void getList(String distId) {
        final PickChooseContract.View view = getView();
        PickChooseHelper.getList(distId, this);
      /*  if(model==null){
            view.showError("请求错误");
        }else{
            view.callList(model.getListRspModel(),model.getPartBatchList(),model.getPartNameList());
        }*/
    }

    @Override
    public void search(List<RspPickList> list, String partName, String partNo, String partBatch, String times) {
        PickChooseContract.View view = getView();
        view.searchSuccess(PickChooseHelper.search(list, partName, partNo, partBatch, times));
    }

    @Override
    public void chooseAll(List<RspPickList> list, boolean status, List<RspPickList> chooseList) {
        PickChooseContract.View view = getView();
        PickChooseModel model = PickChooseHelper.chooseAll(list, status, chooseList);
        view.chooseAllSuccess(model.getList(), model.getChooseList(), model.getSize(), model.getPartCount());
       /* PickChooseContract.View view = getView();
        //解决并发异常
        List<RspPickList> removeList = new ArrayList<>();
        //判断checkbox是否是选中状态
        if (!checkBox.isChecked()) {
            //false 清楚所有数据
            for (RspPickList select : list) {
                select.isCheck = false;
            }
            chooseList.clear();
        } else {
            //勾选所有
            for (RspPickList part : list) {
                for (RspPickList teamp : chooseList) {
                    if (teamp.getDistDetailId() == part.getDistDetailId()) {
                        teamp.isCheck = false;
                        removeList.add(part);
                    }
                }
                part.isCheck = true;
                chooseList.add(part);
            }
        }
        //统一移除
        for (int i = 0; i < removeList.size(); i++) {
            chooseList.remove(removeList.get(i));
        }
        int size = 0;
        double partCount = 0;
        for (RspPickList detail : list) {
            if (detail.isCheck) {
                size++;
                partCount += detail.getApplyItemCount();
            }
        }
        view.chooseAllSuccess(list, chooseList, size, partCount);*/
    }

    //选中单个
    @Override
    public void chooseItem(List<RspPickList> list, List<RspPickList> chooseList, int position) {
        start();
        PickChooseContract.View view = getView();
        PickChooseModel model = PickChooseHelper.chooseItem(list, chooseList, position);
        view.chooseItmeSuccess(model.getList(), model.getChooseList(), model.getSize(), model.getPartCount());

       /* //解决并发异常
        List<RspPickList> removeList = new ArrayList<>();
        if (list.get(position).isCheck) {
            list.get(position).isCheck = false;
            removeList.add(list.get(position));
            chooseList.remove(list.get(position));
        } else {
            list.get(position).isCheck = true;
            removeList.add(list.get(position));
            chooseList.add(list.get(position));
        }
        int size = 0;
        double partCount = 0;
        for (RspPickList detail : list) {
            if (detail.isCheck) {
                size++;
                partCount += detail.getApplyItemCount();
            }
        }*/
    }

    //确认
    @Override
    public void checkNota(List<RspPickList> list, List<RspPickList> chooseList) {
        PickChooseContract.View view = getView();
        if (chooseList.size() == 0) {
            view.showError("你还没有选择任何构件");
        } else if (chooseList.size() < list.size()) {
            view.showDialog();
        } else {
            view.startNota();
        }
    }

    @Override
    public void nota() {
        start();
        PickChooseContract.View view = getView();
        view.startNota();
    }


    @Override
    public void onDataNotAvailable(String strRes) {
        getView().showError(strRes);
    }

    @Override
    public void onDataLoaded(PickChooseList pickChooseList) {
        getView().callList(pickChooseList.getListRspModel(), pickChooseList.getPartBatchList(), pickChooseList.getPartNameList());
    }
}