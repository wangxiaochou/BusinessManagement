package com.xzty.cq.tover.businessmanagement.projectmanagement.project.all;

/**
 * author zzl
 * Created 2018/5/4.
 * explain 工程列表所需的请求参数
 */

public class ReqProjectList {
    private String employId;
    private String projectName;

    public ReqProjectList(String employId, String projectName) {
        this.employId = employId;
        this.projectName = projectName;
    }
}
