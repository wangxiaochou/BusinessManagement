package com.xzty.cq.tover.businessmanagement.projectmanagement.tool.data.pickdepartment.affirm;

import com.xzty.cq.tover.businessmanagement.projectmanagement.tool.model.projectmanage.RspAffirmDetails;

import java.util.List;

/**
 * author zzl
 * Created 2018/6/13.
 * explain
 */

public class LongClickModel {
    private List<RspAffirmDetails> sureList;
    private int check;
    private int refuse;
    private int ignore;

    public List<RspAffirmDetails> getSureList() {
        return sureList;
    }

    public void setSureList(List<RspAffirmDetails> sureList) {
        this.sureList = sureList;
    }

    public int getCheck() {
        return check;
    }

    public void setCheck(int check) {
        this.check = check;
    }

    public int getRefuse() {
        return refuse;
    }

    public void setRefuse(int refuse) {
        this.refuse = refuse;
    }

    public int getIgnore() {
        return ignore;
    }

    public void setIgnore(int ignore) {
        this.ignore = ignore;
    }
}
