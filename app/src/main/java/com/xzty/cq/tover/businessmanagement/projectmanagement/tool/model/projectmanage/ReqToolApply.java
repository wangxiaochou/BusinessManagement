package com.xzty.cq.tover.businessmanagement.projectmanagement.tool.model.projectmanage;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * author zzl
 * Created 2018/5/22.
 * explain 申请机具的请求参数
 */

public class ReqToolApply {
    @SerializedName("PROJECT_ID")
    private int projectId;
    @SerializedName("TOOL_APPLY_USER_ID")
    private String toolApplyUserId;
    @SerializedName("TOOL_APPLY_NOTE")
    private String toolApplyNote;
    private List<ReqNewCreateTool> newToolList;
    private List<RspToolApplyList> toolList;

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    public String getToolApplyUserId() {
        return toolApplyUserId;
    }

    public void setToolApplyUserId(String toolApplyUserId) {
        this.toolApplyUserId = toolApplyUserId;
    }

    public String getToolApplyNote() {
        return toolApplyNote;
    }

    public void setToolApplyNote(String toolApplyNote) {
        this.toolApplyNote = toolApplyNote;
    }

    public List<ReqNewCreateTool> getNewToolList() {
        return newToolList;
    }

    public void setNewToolList(List<ReqNewCreateTool> newToolList) {
        this.newToolList = newToolList;
    }

    public List<RspToolApplyList> getToolList() {
        return toolList;
    }

    public void setToolList(List<RspToolApplyList> toolList) {
        this.toolList = toolList;
    }
}
