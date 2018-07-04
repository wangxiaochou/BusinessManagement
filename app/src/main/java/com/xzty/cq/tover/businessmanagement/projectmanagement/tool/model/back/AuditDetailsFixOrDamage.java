package com.xzty.cq.tover.businessmanagement.projectmanagement.tool.model.back;

/**
 * author zzl
 * Created 2018/5/28.
 * explain
 */

public class AuditDetailsFixOrDamage {
    private int backDetailState;
    private String toolId;

    public void setFix(String toolId) {
        this.toolId = toolId;
        this.backDetailState = 2;
    }

    public void setDamage(String toolId) {
        this.toolId = toolId;
        this.backDetailState = 3;
    }
}
