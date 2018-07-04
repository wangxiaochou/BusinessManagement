package com.xzty.cq.tover.businessmanagement.projectmanagement.part.data.use;

import com.xzty.cq.tover.businessmanagement.projectmanagement.part.model.use.AllModel;

import java.util.List;

/**
 * author zzl
 * Created 2018/6/12.
 * explain
 */

public class UseChooseItemModel {
    private List<AllModel> list;
    private List<AllModel> chooseList;

    public List<AllModel> getList() {
        return list;
    }

    public void setList(List<AllModel> list) {
        this.list = list;
    }

    public List<AllModel> getChooseList() {
        return chooseList;
    }

    public void setChooseList(List<AllModel> chooseList) {
        this.chooseList = chooseList;
    }
}
