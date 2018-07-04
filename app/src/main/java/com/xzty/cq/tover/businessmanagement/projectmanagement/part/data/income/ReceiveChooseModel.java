package com.xzty.cq.tover.businessmanagement.projectmanagement.part.data.income;

import com.xzty.cq.tover.businessmanagement.projectmanagement.part.model.deliver.DeliverDetails;

import java.util.List;

/**
 * author zzl
 * Created 2018/6/11.
 * explain
 */

public class ReceiveChooseModel {
    private List<DeliverDetails> list;
    private int countType;
    private int countNum;

    public List<DeliverDetails> getList() {
        return list;
    }

    public void setList(List<DeliverDetails> list) {
        this.list = list;
    }

    public int getCountType() {
        return countType;
    }

    public void setCountType(int countType) {
        this.countType = countType;
    }

    public int getCountNum() {
        return countNum;
    }

    public void setCountNum(int countNum) {
        this.countNum = countNum;
    }
}
