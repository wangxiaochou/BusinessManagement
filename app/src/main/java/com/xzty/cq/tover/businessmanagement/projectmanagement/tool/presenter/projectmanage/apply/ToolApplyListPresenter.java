package com.xzty.cq.tover.businessmanagement.projectmanagement.tool.presenter.projectmanage.apply;

import android.widget.CheckBox;

import com.google.gson.Gson;
import com.xzty.cq.tover.businessmanagement.common.factory.BasePresenter;
import com.xzty.cq.tover.businessmanagement.common.model.RspModel;
import com.xzty.cq.tover.businessmanagement.common.net.NetWork;
import com.xzty.cq.tover.businessmanagement.projectmanagement.common.api.RemoteService;
import com.xzty.cq.tover.businessmanagement.projectmanagement.tool.model.projectmanage.RspToolApplyList;

import java.util.ArrayList;
import java.util.List;

import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * author zzl
 * Created 2018/5/21.
 * explain 机具申请的搜索P
 */

public class ToolApplyListPresenter extends BasePresenter<ToolApplayListContract.View> implements ToolApplayListContract.Presenter {

    public ToolApplyListPresenter(ToolApplayListContract.View view) {
        super(view);
    }

    @Override
    public void search(String searchToolName) {
        final ToolApplayListContract.View view = getView();
        RemoteService service = NetWork.remote(RemoteService.class);
        Subscription subscription = service.getToolList(searchToolName).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<RspModel<List<RspToolApplyList>>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                view.showError("请求失败" + e.getMessage());
            }

            @Override
            public void onNext(RspModel<List<RspToolApplyList>> listRspModel) {
                int code = listRspModel.getBackcode();
                if (code == 0) {
                    view.showError("请求错误");
                } else {
                    view.searchSuccess(listRspModel.getData());
                }
            }
        });
    }

    @Override
    public void itemClick(List<RspToolApplyList> list, int position, List<RspToolApplyList> newCreateToolList, List<Integer> newListPosition) {
        ToolApplayListContract.View view = getView();
        //是否勾选全选checkbox;
        boolean checkAll = false;
        if (list.get(position).isChecked) {
            if (newCreateToolList.size() > 0) {
                for (int i = 0; i < newListPosition.size(); i++) {
                    int n = newListPosition.get(i);
                    if (n == position) {
                        newCreateToolList.remove(list.get(position));
                    }
                }
            }
            list.get(position).isChecked = false;
        } else {
            //记录选中的个数
            int checkNum = 0;
            for (int i = 0; i < newListPosition.size(); i++) {
                if (newListPosition.get(i) == position) {
                    newCreateToolList.add(list.get(position));
                }
            }
            list.get(position).isChecked = true;
            for(int i = 0; i<list.size();i++){
                if(list.get(i).isChecked){
                    checkNum++;
                }
            }
            if(checkNum==list.size()){
                checkAll = true;
            }else{
                checkAll = false;
            }
        }
        view.itemClickSuccess(list, newCreateToolList, newListPosition,checkAll);

    }

    @Override
    public void chooseAll(List<RspToolApplyList> list, CheckBox checkBox, List<RspToolApplyList> newCreateToolList, List<Integer> newListPosition) {
        ToolApplayListContract.View view = getView();
        if (checkBox.isChecked()) {
            //判断是否选中
            if (newListPosition.size() > 0) {
                for (int i = 0; i < newListPosition.size(); i++) {
                    //未选中，添加到新的list中
                    int n = newListPosition.get(i);
                    boolean ck = list.get(n).isChecked;
                    if (!ck) {
                        newCreateToolList.add(list.get(n));
                    }
                }
            }
            for (RspToolApplyList tool : list) {
                tool.isChecked = true;
            }
        } else {
            //判断是否选中
            if (newListPosition.size() > 0) {
                for (int i = 0; i < newListPosition.size(); i++) {
                    int n = newListPosition.get(i);
                    boolean ck = list.get(n).isChecked;
                    //选中，新的list移除
                    if (ck) {
                        newCreateToolList.remove(list.get(n));
                    }
                }
            }
            for (RspToolApplyList tool : list) {
                tool.isChecked = false;
            }
        }
        view.chooseAllSuccess(list, newCreateToolList, newListPosition);
    }

    @Override
    public void isGo(List<RspToolApplyList> list, List<RspToolApplyList> newList, List<Integer> newListPosition) {
        ToolApplayListContract.View view = getView();
        List<RspToolApplyList> intentList = new ArrayList<>();
        //判断是否有新的机具
        if (newListPosition.size() > 0) {
            for (int i = 0; i < newListPosition.size(); i++) {
                int n = newListPosition.get(i);
                int size = list.size();
                if (size == n) {
                    list.remove((n-1));
                }else if(size>n){
                    list.remove(n);
                }else if(size<n){
                    list.remove(size-(n-size));
                }
            }
        }
        for (RspToolApplyList tool : list) {
            if (tool.isChecked) {
                intentList.add(tool);
            }
        }
        String s1 = new Gson().toJson(intentList);
        String s2 = "";
        if (newList.size() > 0) {
            s2 = new Gson().toJson(newList);
        }

        if (intentList.size() > 0 || newList.size() > 0) {
            view.go(s1, s2);
        } else {
            view.showError("请添加机具");
        }
    }
}
