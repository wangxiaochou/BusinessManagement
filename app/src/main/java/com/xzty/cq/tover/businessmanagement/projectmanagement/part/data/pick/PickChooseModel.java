package com.xzty.cq.tover.businessmanagement.projectmanagement.part.data.pick;

import com.xzty.cq.tover.businessmanagement.projectmanagement.part.model.pick.RspPickList;

import java.util.List;

/**
 * author zzl
 * Created 2018/6/12.
 * explain
 */

public class PickChooseModel {
    private List<RspPickList> list;
    private List<RspPickList> chooseList;
    private int size;
    private double partCount;

    public List<RspPickList> getList() {
        return list;
    }

    public void setList(List<RspPickList> list) {
        this.list = list;
    }

    public List<RspPickList> getChooseList() {
        return chooseList;
    }

    public void setChooseList(List<RspPickList> chooseList) {
        this.chooseList = chooseList;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public double getPartCount() {
        return partCount;
    }

    public void setPartCount(double partCount) {
        this.partCount = partCount;
    }
}
