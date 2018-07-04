package com.xzty.cq.tover.businessmanagement.projectmanagement.part.data.income;

import com.xzty.cq.tover.businessmanagement.projectmanagement.part.model.Emp;
import com.xzty.cq.tover.businessmanagement.projectmanagement.part.model.deliver.DeliverOrder;

import java.util.List;

/**
 * author zzl
 * Created 2018/6/12.
 * explain
 */

public class ReceiveOrderModel {
    private List<DeliverOrder> orderList;
    private List<Emp> emps;

    public List<DeliverOrder> getOrderList() {
        return orderList;
    }

    public void setOrderList(List<DeliverOrder> orderList) {
        this.orderList = orderList;
    }

    public List<Emp> getEmps() {
        return emps;
    }

    public void setEmps(List<Emp> emps) {
        this.emps = emps;
    }
}
