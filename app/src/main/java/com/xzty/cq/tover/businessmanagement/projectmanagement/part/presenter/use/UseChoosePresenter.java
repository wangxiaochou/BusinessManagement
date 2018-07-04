package com.xzty.cq.tover.businessmanagement.projectmanagement.part.presenter.use;

import com.xzty.cq.tover.businessmanagement.common.data.DataSourse;
import com.xzty.cq.tover.businessmanagement.common.factory.BasePresenter;
import com.xzty.cq.tover.businessmanagement.projectmanagement.part.data.use.UseChooseHelper;
import com.xzty.cq.tover.businessmanagement.projectmanagement.part.data.use.UseChooseItemModel;
import com.xzty.cq.tover.businessmanagement.projectmanagement.part.data.use.UseChooseModel;
import com.xzty.cq.tover.businessmanagement.projectmanagement.part.model.use.AllModel;

import java.util.List;

/**
 * author zzl
 * Created 2018/5/16.
 * explain 领用 选择构件的P
 */

public class UseChoosePresenter extends BasePresenter<UseChooseContract.View> implements UseChooseContract.Presenter,DataSourse.Callback<UseChooseModel> {

    public UseChoosePresenter(UseChooseContract.View view) {
        super(view);
    }

    @Override
    public void search(String time, String applyBatches, String outBatches, String partName, String partNo) {
        final UseChooseContract.View view = getView();
        UseChooseHelper.search(time,applyBatches,outBatches,partName,partNo,this);
     /*   String[] times;
        if (time.contains("~")) {
            times = time.split("~");
        } else {
            times = new String[]{"", ""};
        }
        //构件名称
        if (partName.equals("全部")) {
            partName = "";
        }
        //申请批次
        String applyBatch = "";
        if (applyBatches.equals("全部")) {
            applyBatch = "";
        } else {
            String[] arryapplyBatch = applyBatches.split("批");
            applyBatch = String.valueOf(arryapplyBatch[0]);
        }
        //发货批次
        String outBatch = "";
        if (outBatches.equals("全部")) {
            outBatch = "";
        } else {
            String[] arryoutBatch = outBatches.split("批");
            outBatch = String.valueOf(arryoutBatch[0]);
        }
        Account.load(MyApplication.getInstance());
        String projectId = Account.getProjectId();
        Map<String, String> maps = new HashMap<>();
        maps.put("projectId", projectId);
        maps.put("beginCollectTime", times[0] + "");
        maps.put("endCollectTime", times[1] + "");
        maps.put("applyBatch", applyBatch);
        maps.put("outBatch", outBatch);
        maps.put("partName", partName);
        maps.put("partNo", partNo);
        RemoteService service = NetWork.remote(RemoteService.class);
        Subscription subscription = service.queryChoosePart(maps).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<RspModel<List<RspUseChoose>>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                view.showError("请求失败" + e.getMessage());
            }

            @Override
            public void onNext(RspModel<List<RspUseChoose>> listRspModel) {
                int code = listRspModel.getBackcode();
                if (code == 0) {
                    view.showError("请求错误");
                } else {
                    List<String> partNameList = new ArrayList<>();
                    List<String> applyBatchList = new ArrayList<>();
                    List<String> outBatchList = new ArrayList<>();
                    List<AllModel> allList = new ArrayList<>();
                    partNameList.add("全部");
                    applyBatchList.add("全部");
                    outBatchList.add("全部");
                    allList = JSON.parseArray(new Gson().toJson(listRspModel.getData()).toString(),
                            AllModel.class);

                    for (AllModel model : allList) {
                        String partname = (model.getPartName() == null || model.getPartName().equals("")) ? "空" : model.getPartName();
                        if (!partNameList.contains(partname)) {
                            partNameList.add(partname);
                        }
                        String applybatch = model.getApplyBatch() + "批";
                        if (!applyBatchList.contains(applybatch)) {
                            applyBatchList.add(applybatch);
                        }
                        String outbatch = model.getOutBatch() + "批";
                        if (!outBatchList.contains(outbatch)) {
                            outBatchList.add(outbatch);
                        }
                    }
                    view.success(partNameList, applyBatchList, outBatchList, allList);
                }
            }
        });*/
    }

    @Override
    public void chooseAll(List<AllModel> list, List<AllModel> thisTimeSelectedPartTemp, boolean status) {
        UseChooseContract.View view = getView();
        UseChooseItemModel model = UseChooseHelper.chooseAll(list,thisTimeSelectedPartTemp,status);
        view.selectedAll(model.getList(),model.getChooseList());
       /* //解决并发异常
        List<AllModel> removeList = new ArrayList<>();
        //判断checkbox是否是选中状态
        if (!checkBox.isChecked()) {
            //false 清楚所有数据
            for (AllModel select : list) {
                select.isCheck = false;
            }
            thisTimeSelectedPartTemp.clear();
        } else {
            //勾选所有
            for (AllModel model : list) {
                for (AllModel teamp : thisTimeSelectedPartTemp) {
                    if (model.getPartId() == teamp.getPartId()) {
                        teamp.isCheck = false;
                        removeList.add(model);
                    }
                }
                model.isCheck = true;
                thisTimeSelectedPartTemp.add(model);
            }
        }
        //统一移除
        Log.e("TAG","需要移除的数据="+removeList.size()+thisTimeSelectedPartTemp.size());
        for(int i = 0 ;i<removeList.size();i++){
            thisTimeSelectedPartTemp.remove(removeList.get(i));
        }
        view.selectedAll(list, thisTimeSelectedPartTemp);*/
    }

    @Override
    public void itemClick(List<AllModel> list, List<AllModel> thisTimeSelectedPartTemp, int position) {
        UseChooseContract.View view = getView();
        UseChooseItemModel model =  UseChooseHelper.itemClick(list,position);
        view.selectedItem(model.getList(),model.getChooseList());
       /* //解决并发异常
        List<AllModel> removeList = new ArrayList<>();
        if (list.get(position).isCheck) {
            list.get(position).isCheck = false;
            removeList.add(list.get(position));
            thisTimeSelectedPartTemp.remove(list.get(position));
        } else {
            list.get(position).isCheck = true;
            removeList.add(list.get(position));
            thisTimeSelectedPartTemp.add(list.get(position));
        }
        view.selectedItem(list, thisTimeSelectedPartTemp);*/
    }

    @Override
    public void onDataLoaded(UseChooseModel userChooseModel) {
        getView().success(userChooseModel.getPartNameList(),userChooseModel.getApplyBatchList(),userChooseModel.getOutBatchList(),userChooseModel.getAllList());
    }

    @Override
    public void onDataNotAvailable(String strRes) {
        getView().showError(strRes);
    }
}
