package com.xzty.cq.tover.businessmanagement.projectmanagement.tool.model.pickdepartment;

import java.util.List;

/**
 * author zzl
 * Created 2018/5/24.
 * explain
 */

public class RspAllDistribute {
    private List<RspDistribute> applys;
    private List<RspUser> name;

    public List<RspDistribute> getApplys() {
        return applys;
    }

    public void setApplys(List<RspDistribute> applys) {
        this.applys = applys;
    }

    public List<RspUser> getName() {
        return name;
    }

    public void setName(List<RspUser> name) {
        this.name = name;
    }
}
