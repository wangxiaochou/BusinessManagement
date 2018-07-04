package com.xzty.cq.tover.businessmanagement.projectmanagement.part.data.use;

import com.xzty.cq.tover.businessmanagement.projectmanagement.part.model.use.AllModel;

import java.util.ArrayList;
import java.util.List;

/**
 * author zzl
 * Created 2018/6/12.
 * explain
 */

public class UseChooseModel {
    List<String> partNameList = new ArrayList<>();
    List<String> applyBatchList = new ArrayList<>();
    List<String> outBatchList = new ArrayList<>();
    List<AllModel> allList = new ArrayList<>();

    public List<String> getPartNameList() {
        return partNameList;
    }

    public void setPartNameList(List<String> partNameList) {
        this.partNameList = partNameList;
    }

    public List<String> getApplyBatchList() {
        return applyBatchList;
    }

    public void setApplyBatchList(List<String> applyBatchList) {
        this.applyBatchList = applyBatchList;
    }

    public List<String> getOutBatchList() {
        return outBatchList;
    }

    public void setOutBatchList(List<String> outBatchList) {
        this.outBatchList = outBatchList;
    }

    public List<AllModel> getAllList() {
        return allList;
    }

    public void setAllList(List<AllModel> allList) {
        this.allList = allList;
    }
}
