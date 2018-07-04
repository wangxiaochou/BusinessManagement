package com.xzty.cq.tover.businessmanagement.projectmanagement.part.data.collect;

import com.xzty.cq.tover.businessmanagement.projectmanagement.part.model.collect.RspCollect;

import java.util.List;

/**
 * author zzl
 * Created 2018/6/11.
 * explain
 */

public class CollectModel {
    private List<RspCollect> list;
    private List<RspCollect> chooseList;

    public List<RspCollect> getList() {
        return list;
    }

    public void setList(List<RspCollect> list) {
        this.list = list;
    }

    public List<RspCollect> getChooseList() {
        return chooseList;
    }

    public void setChooseList(List<RspCollect> chooseList) {
        this.chooseList = chooseList;
    }
}
