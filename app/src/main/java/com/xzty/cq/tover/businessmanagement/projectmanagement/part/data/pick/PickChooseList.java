package com.xzty.cq.tover.businessmanagement.projectmanagement.part.data.pick;

import com.xzty.cq.tover.businessmanagement.projectmanagement.part.model.pick.RspPickList;

import java.util.List;

/**
 * author zzl
 * Created 2018/6/12.
 * explain
 */

public class PickChooseList {

    private List<String> partBatchList;
    private List<String> partNameList;
    private List<RspPickList> listRspModel;

    public List<String> getPartBatchList() {
        return partBatchList;
    }

    public void setPartBatchList(List<String> partBatchList) {
        this.partBatchList = partBatchList;
    }

    public List<String> getPartNameList() {
        return partNameList;
    }

    public void setPartNameList(List<String> partNameList) {
        this.partNameList = partNameList;
    }

    public List<RspPickList> getListRspModel() {
        return listRspModel;
    }

    public void setListRspModel(List<RspPickList> listRspModel) {
        this.listRspModel = listRspModel;
    }
}
