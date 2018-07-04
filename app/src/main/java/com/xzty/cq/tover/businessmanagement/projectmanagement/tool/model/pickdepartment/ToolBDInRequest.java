package com.xzty.cq.tover.businessmanagement.projectmanagement.tool.model.pickdepartment;

/**
 * author zzl
 * Created 2018/5/29.
 * explain
 */

public class ToolBDInRequest {
    public int
            distDetailId,
            projectId,
            depotCode,//1项管仓库，2采购仓库
            departmentCode,//1项管部，2采购部
            damageCode;//0完好，1维修，2损坏，3报废
    public String
            userId,
            number,//编号（1系统生成，0无需生成，其他复用）
            name,
            model,
            brand,
            power,
            category,
            price,
            note;
    public double count;

    public ToolBDInRequest() {
        this.number = "1";
        this.depotCode = 2;
        this.departmentCode = 2;
    }
}
