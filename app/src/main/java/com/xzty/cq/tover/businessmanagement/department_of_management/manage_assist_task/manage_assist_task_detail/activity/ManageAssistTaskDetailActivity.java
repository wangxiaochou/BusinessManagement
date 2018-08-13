package com.xzty.cq.tover.businessmanagement.department_of_management.manage_assist_task.manage_assist_task_detail.activity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.xzty.cq.tover.businessmanagement.R;
import com.xzty.cq.tover.businessmanagement.common.factory.ActivityPresenter;
import com.xzty.cq.tover.businessmanagement.department_of_management.manage_assist_task.manage_assist_task_detail.adapter.ManageAssistProgressAdapter;
import com.xzty.cq.tover.businessmanagement.department_of_management.manage_assist_task.manage_assist_task_detail.model.RspAssistProgressDetails;
import com.xzty.cq.tover.businessmanagement.department_of_management.manage_assist_task.manage_assist_task_detail.presenter.ManageAssistTaskDetailContract;
import com.xzty.cq.tover.businessmanagement.department_of_management.manage_assist_task.manage_assist_task_detail.presenter.ManageAssistTaskDetailPresenter;
import com.xzty.cq.tover.businessmanagement.department_of_management.manage_assist_task.manage_assist_task_list.activity.ManageAssistTaskActivity;
import com.xzty.cq.tover.businessmanagement.department_of_management.manage_assist_task.manage_assist_task_list.model.ReqAssistAddProgress;
import com.xzty.cq.tover.businessmanagement.department_of_management.manage_assist_task.manage_assist_task_list.model.RspAssistTaskDetails;
import com.xzty.cq.tover.businessmanagement.department_of_management.manage_assist_task.manage_assist_task_list.activity.TaskAddProgressDialog;

import java.util.Collections;
import java.util.List;

import butterknife.BindView;

/**
 * @Author yq
 * @Date 2018/8/6
 * 协调任务详情
 */
public class ManageAssistTaskDetailActivity extends ActivityPresenter<ManageAssistTaskDetailContract.Presenter>
        implements ManageAssistTaskDetailContract.View,View.OnClickListener{

    private RspAssistTaskDetails ratDetail;

    @BindView(R.id.tb_task_assits_detail)
    Toolbar toolbar;

    @BindView(R.id.tv_task_details_title)
    TextView detailTitle;

    @BindView(R.id.tv_task_details_isdone)
    TextView isdoneText;

    @BindView(R.id.iv_task_details_isdone)
    ImageView isdoneImage;

    @BindView(R.id.tv_task_details_project)
    TextView projectName;

    @BindView(R.id.tv_task_details_charge_person)
    TextView chargeperson;

    @BindView(R.id.tv_task_details_createtime)
    TextView createTime;

    @BindView(R.id.tv_task_details_createperson)
    TextView createPerson;

    @BindView(R.id.tv_task_details_expectime)
    TextView expectTime;

    @BindView(R.id.rv_task_details_progresses)
    RecyclerView progresses;

    @BindView(R.id.tv_task_details_overduetimes)
    TextView overdueTimes;

    TextView factTime;

    private LinearLayoutManager layoutManager;

    private ManageAssistProgressAdapter mapAdapter;

    @Override
    protected void initWidget() {
        super.initWidget();
        initToolbar();

    }

    @Override
    protected void initData() {
        super.initData();
        layoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        setMassages();
        initAdapterOnclick();
    }

    /**
     * 设置RecycleView的点击事件
     */
    private void initAdapterOnclick() {
        mapAdapter.getHeaderLayout().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ratDetail.getIsFinish() == 0){
                    ReqAssistAddProgress raaProgress = new ReqAssistAddProgress(ratDetail.getId(),ratDetail.getEmplId(),
                            ratDetail.getEmplName(),ratDetail.getExpectTime());
                    new TaskAddProgressDialog(ManageAssistTaskDetailActivity.this,R.style.Dialog_Fullscreen,raaProgress).show();
                }
            }
        });
    }

    public void setMassages(){
        this.ratDetail = JSON.parseObject(getIntent().getStringExtra("AssistTaskDetail"),RspAssistTaskDetails.class);
        int overdueTime = 0;
        for (RspAssistProgressDetails rapDetails: ratDetail.getTaskProgresses()
             ) {
            if (rapDetails.getType() == 1)
                overdueTime ++;
        }
        detailTitle.setText(ratDetail.getAssistTask());
        isdoneText.setText(ratDetail.getIsFinish()==0?"进行中":"已完成");
        isdoneImage.setImageResource(ratDetail.getIsFinish()==0?R.drawable.iv_task_assist_underway:R.drawable.iv_task_assist_completed);
        projectName.setText("所属项目："+ratDetail.getProject().getProjectName());
        chargeperson.setText("负责人："+ratDetail.getChargePerson());
        createPerson.setText("创建人："+ratDetail.getEmplName());
        createTime.setText("创建时间："+ratDetail.getCreateTime());
        expectTime.setText("预计完成时间："+ratDetail.getExpectTime());
        if (overdueTime != 0){
            overdueTimes.setText("该任务已逾期 "+ overdueTime + " 次");
            overdueTimes.setTextColor(getResources().getColor(R.color.task_assist_time_overdue));
        }
        setAdapter(ratDetail.getTaskProgresses());
        //为进行中图标设置点击事件
        if (ratDetail.getIsFinish() ==0 ){
            isdoneImage.setOnClickListener(this);
        }

    }
    public void setAdapter(List<RspAssistProgressDetails> progressDetails){
        View headerView;
        progresses.setLayoutManager(layoutManager);
        //将进展倒序
        Collections.reverse(progressDetails);
        mapAdapter = new ManageAssistProgressAdapter(R.layout.task_assist_progress_item,progressDetails);

        //根据任务不同的状态显示不同的头部信息
        if (ratDetail.getIsFinish() == 0){
            headerView = getLayoutInflater().inflate(R.layout.task_assist_progress_header_item,null);
        }else {
            headerView = getLayoutInflater().inflate(R.layout.task_assist_progress_header_done,null);
        }
        headerView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        mapAdapter.addHeaderView(headerView);
        factTime = mapAdapter.getHeaderLayout().findViewById(R.id.tv_task_assist_fact_time);
        if (factTime != null){
            factTime.setText(ratDetail.getFactTime());
        }
        progresses.setAdapter(mapAdapter);

    }
    @Override
    protected ManageAssistTaskDetailContract.Presenter initPresenter() {
        return new ManageAssistTaskDetailPresenter(this);
    }

    @Override
    public void success() {
        Toast.makeText(ManageAssistTaskDetailActivity.this,"状态修改成功！该任务已完成",Toast.LENGTH_LONG).show();
        this.startActivity(new Intent(this, ManageAssistTaskActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_task_details_isdone:
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("更改任务状态");
                builder.setMessage("该任务已完成？");
                builder.setNegativeButton("否",null);
                builder.setPositiveButton("已完成", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        mPresenter.setAssistTaskDone(ratDetail.getId());
                    }
                });
                builder.show();
        }
    }

    @Override
    protected int getContentLayoutId() {
        return R.layout.activity_task_assist_detail;
    }

    private void initToolbar() {
        setSupportActionBar(toolbar);
        //设置返回图标点击事件
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
    @Override
    public void showError(String str) {

    }

    @Override
    public void showLoading() {

    }
}
