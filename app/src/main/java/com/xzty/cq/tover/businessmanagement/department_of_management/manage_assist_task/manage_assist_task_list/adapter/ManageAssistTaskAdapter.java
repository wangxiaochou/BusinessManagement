package com.xzty.cq.tover.businessmanagement.department_of_management.manage_assist_task.manage_assist_task_list.adapter;

import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.xzty.cq.tover.businessmanagement.R;
import com.xzty.cq.tover.businessmanagement.common.model.EmployeeModel;
import com.xzty.cq.tover.businessmanagement.department_of_management.manage_assist_task.manage_assist_task_detail.model.RspAssistProgressDetails;
import com.xzty.cq.tover.businessmanagement.department_of_management.manage_assist_task.manage_assist_task_list.model.RspAssistTaskDetails;
import com.xzty.cq.tover.businessmanagement.projectmanagement.utils.DateUtil;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import butterknife.BindView;

/**
 * author yq
 * date 2018/8/1
 * 协调任务Recycle 的 Adapter
 */
public class ManageAssistTaskAdapter extends BaseQuickAdapter<RspAssistTaskDetails,ManageAssistTaskAdapter.MyViewHolder>{

    private ButtonInterface buttonInterface;

    public ManageAssistTaskAdapter(int layoutResId, @Nullable List<RspAssistTaskDetails> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(MyViewHolder helper, RspAssistTaskDetails item) {
/*        StringBuilder chargepersonName = new StringBuilder();
        //获取所有负责人
        for (EmployeeModel chargeperson:item.getEmployees()
             ) {
            chargepersonName.append(chargeperson.getEplyName());
            chargepersonName.append(" ");
        }
        helper.setText(R.id.tv_task_assist_charge_person,chargepersonName );*/
        helper.setText(R.id.tv_task_assist_charge_person,item.getChargePerson());

        //设置预计完成时间，添加颜色提示
        helper.setText(R.id.tv_task_assist_expect_time,item.getExpectTime());
        helper.setTextColor(R.id.tv_task_assist_expect_time,getTimeColor(item.getExpectTime()));
        helper.setText(R.id.tv_task_assist_content,item.getAssistTask());
        //获取并设置最新任务进展
        int progSize = item.getTaskProgresses().size();
        if (progSize!=0){
            helper.setText(R.id.tv_task_assist_newest,item.getTaskProgresses().get(progSize-1).getTrackContent());
        }
    }

    /**
     * 获取预计完成时间对应的提示颜色
     * @param expectTime
     * @return 颜色ID
     */
    public int getTimeColor(String expectTime){
        int differDay;
        final int SAFEDAY = 1;
        final int WARNING = 2;
        final int OVERDUE = 3;
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(DateUtil.stringToDate(expectTime));
        //当前时间
        Calendar calendar2 = Calendar.getInstance();
        calendar2.setTime(new Date());

        int expectDay = calendar.get(Calendar.DAY_OF_YEAR);
        int currentDay = calendar2.get(Calendar.DAY_OF_YEAR);
        int expectYear = calendar.get(Calendar.YEAR);
        int currentYear = calendar2.get(Calendar.YEAR);


        if (currentYear > expectYear) {
            currentDay += 365;
        }
        if (currentYear < expectDay){
            expectDay += 365;
        }
        differDay = expectDay - currentDay;

        //根据当前时间与预计时间差值设置字体颜色
        if (differDay > 3 ){
            return mContext.getResources().getColor(R.color.task_assist_time_insafe);
        }
        if ( differDay <= 3  && differDay > 0){
            return mContext.getResources().getColor(R.color.task_assist_time_warning);
        }
        if (differDay <= 0){
            return mContext.getResources().getColor(R.color.task_assist_time_overdue);
        }
        return 0;
    }

    /**
     * 提供给Activity回调的方法，用于将接口实例化并重写OnClick方法
     * @param buttonInterface
     */
    public void buttonSetOnClick(ButtonInterface buttonInterface){
        this.buttonInterface = buttonInterface;
    }
    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        super.onBindViewHolder(holder, position);
        holder.addProgress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (buttonInterface != null){
                    //接口在Activity实例化之后，该对象将调用Activity重写后的方法
                    buttonInterface.onClick(v,position);
                }
            }
        });
    }

    /**
     * 内部接口，方便Activity回调，为按钮添加点击事件
     */
    public interface ButtonInterface{
        public void onClick(View view,int position);
    }
    /**
     * author yq
     * date 2018/8/6
     * 内部内，用于缓存控件，方便为按钮添加点击事件
     */
    class MyViewHolder extends BaseViewHolder{

        ImageView addProgress;

        public MyViewHolder(View view) {
            super(view);
            addProgress = view.findViewById(R.id.iv_task_progress_add);
        }
    }
}
