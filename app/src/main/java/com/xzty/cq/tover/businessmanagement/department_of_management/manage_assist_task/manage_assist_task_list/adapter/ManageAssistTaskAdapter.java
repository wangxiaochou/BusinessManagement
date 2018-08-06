package com.xzty.cq.tover.businessmanagement.department_of_management.manage_assist_task.manage_assist_task_list.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.xzty.cq.tover.businessmanagement.R;
import com.xzty.cq.tover.businessmanagement.common.model.EmployeeModel;
import com.xzty.cq.tover.businessmanagement.department_of_management.manage_assist_task.manage_assist_task_detail.model.RspAssistProgressDetails;
import com.xzty.cq.tover.businessmanagement.department_of_management.manage_assist_task.manage_assist_task_list.model.RspAssistTaskDetails;

import java.util.List;

/**
 * author yq
 * date 2018/8/1
 * 协调任务Recycle 的 Adapter
 */
public class ManageAssistTaskAdapter extends BaseQuickAdapter<RspAssistTaskDetails,BaseViewHolder>{
    public ManageAssistTaskAdapter(int layoutResId, @Nullable List<RspAssistTaskDetails> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, RspAssistTaskDetails item) {
/*        StringBuilder chargepersonName = new StringBuilder();
        //获取所有负责人
        for (EmployeeModel chargeperson:item.getEmployees()
             ) {
            chargepersonName.append(chargeperson.getEplyName());
            chargepersonName.append(" ");
        }
        helper.setText(R.id.tv_task_assist_charge_person,chargepersonName );*/
        helper.setText(R.id.tv_task_assist_charge_person,item.getChargePerson());
        helper.setText(R.id.tv_task_assist_expect_time,"预计完成时间："+item.getExpectTime());
        helper.setText(R.id.tv_task_assist_content,item.getAssistTask());

        //获取并设置最新任务进展
        int progSize = item.getTaskProgresses().size();
        if (progSize!=0){
            helper.setText(R.id.tv_task_assist_newest,item.getTaskProgresses().get(progSize-1).getTrackContent());
        }
    }
}
