package com.xzty.cq.tover.businessmanagement.projectmanagement.tool.model.pickdepartment;

import com.google.gson.annotations.SerializedName;

/**
 * author zzl
 * Created 2018/5/29.
 * explain
 */

public class RspDemage {
    @SerializedName("TOOL_NUMBER")
    private String toolNumber;

    public String getToolNumber() {
        return toolNumber;
    }

    public void setToolNumber(String toolNumber) {
        this.toolNumber = toolNumber;
    }
}
