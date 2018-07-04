package com.xzty.cq.tover.businessmanagement.projectmanagement.part.model.promotenum;

import com.google.gson.annotations.SerializedName;

/**
 * author zzl
 * Created 2018/6/6.
 * explain
 */

public class ReqPromote {
    @SerializedName("PART_ID")
    private String partId;
    @SerializedName("INTO_TIME")
    private String infoTime;
    @SerializedName("NEED_COUNT")
    private String needCount;

    public String getPartId() {
        return partId;
    }

    public void setPartId(String partId) {
        this.partId = partId;
    }

    public String getInfoTime() {
        return infoTime;
    }

    public void setInfoTime(String infoTime) {
        this.infoTime = infoTime;
    }

    public String getNeedCount() {
        return needCount;
    }

    public void setNeedCount(String needCount) {
        this.needCount = needCount;
    }
}
