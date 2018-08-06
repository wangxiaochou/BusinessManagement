package com.xzty.cq.tover.businessmanagement.department_of_management.manage_assist_task.manage_assist_task_detail.activity;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.xzty.cq.tover.businessmanagement.R;
import com.xzty.cq.tover.businessmanagement.common.factory.ActivityPresenter;
import com.xzty.cq.tover.businessmanagement.department_of_management.manage_assist_task.manage_assist_task_detail.adapter.ManageAssistProgressAdapter;
import com.xzty.cq.tover.businessmanagement.department_of_management.manage_assist_task.manage_assist_task_detail.model.RspAssistProgressDetails;
import com.xzty.cq.tover.businessmanagement.department_of_management.manage_assist_task.manage_assist_task_detail.presenter.ManageAssistTaskDetailContract;
import com.xzty.cq.tover.businessmanagement.department_of_management.manage_assist_task.manage_assist_task_detail.presenter.ManageAssistTaskDetailPresenter;
import com.xzty.cq.tover.businessmanagement.department_of_management.manage_assist_task.manage_assist_task_list.model.RspAssistTaskDetails;

import java.util.List;

import butterknife.BindView;

/**
 * @Author yq
 * @Date 2018/8/6
 * 协调任务详情
 */
public class ManageAssistTaskDetailActivity extends ActivityPresenter<ManageAssistTaskDetailContract.Presenter>
        implements ManageAssistTaskDetailContract.View{

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

    @BindView(R.id.rv_task_details_progresses)
    RecyclerView progresses;

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
    }

    public void setMassages(){
        this.ratDetail = JSON.parseObject(getIntent().getStringExtra("AssistTaskDetail"),RspAssistTaskDetails.class);
        detailTitle.setText(ratDetail.getAssistTask());
        isdoneText.setText(ratDetail.getIsFinish()==0?"进行中":"已完成");
        isdoneImage.setImageResource(ratDetail.getIsFinish()==0?R.drawable.iv_task_assist_underway:R.drawable.iv_task_assist_completed);
        projectName.setText("所属项目："+ratDetail.getProject().getProjectName());
        chargeperson.setText("负责人："+ratDetail.getChargePerson());
        createPerson.setText("创建人："+ratDetail.getEmplName());
        createTime.setText("创建时间："+ratDetail.getCreateTime());
        setAdapter(ratDetail.getTaskProgresses());

    }
    public void setAdapter(List<RspAssistProgressDetails> progressDetails){
        progresses.setLayoutManager(layoutManager);
        mapAdapter = new ManageAssistProgressAdapter(R.layout.task_assist_progress_item,progressDetails);
        progresses.setAdapter(mapAdapter);
    }
    @Override
    protected ManageAssistTaskDetailContract.Presenter initPresenter() {
        return new ManageAssistTaskDetailPresenter(this);
    }

    @Override
    public void success(List<RspAssistProgressDetails> mlist) {
        //TODO
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
