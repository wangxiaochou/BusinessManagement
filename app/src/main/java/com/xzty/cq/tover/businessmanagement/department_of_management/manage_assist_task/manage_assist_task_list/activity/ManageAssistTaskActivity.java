package com.xzty.cq.tover.businessmanagement.department_of_management.manage_assist_task.manage_assist_task_list.activity;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.xzty.cq.tover.businessmanagement.R;
import com.xzty.cq.tover.businessmanagement.common.factory.Account;
import com.xzty.cq.tover.businessmanagement.common.factory.ActivityPresenter;
import com.xzty.cq.tover.businessmanagement.department_of_management.manage_assist_task.manage_assist_task_detail.activity.ManageAssistTaskDetailActivity;
import com.xzty.cq.tover.businessmanagement.department_of_management.manage_assist_task.manage_assist_task_list.adapter.ManageAssistTaskAdapter;
import com.xzty.cq.tover.businessmanagement.department_of_management.manage_assist_task.manage_assist_task_list.model.ReqAssistAddProgress;
import com.xzty.cq.tover.businessmanagement.department_of_management.manage_assist_task.manage_assist_task_list.model.RspAssistTaskDetails;
import com.xzty.cq.tover.businessmanagement.department_of_management.manage_assist_task.manage_assist_task_list.presenter.ManageAssistTaskContract;
import com.xzty.cq.tover.businessmanagement.department_of_management.manage_assist_task.manage_assist_task_list.presenter.ManageAssistTaskPresenter;

import java.util.List;

import butterknife.BindView;

/**
 * author yq
 * date 2018/7/31
 * 员工任务列表主页
 */
public class ManageAssistTaskActivity extends ActivityPresenter<ManageAssistTaskContract.Presenter>
        implements ManageAssistTaskContract.View,BaseQuickAdapter.OnItemClickListener{


    @BindView(R.id.tb_task_assits)
    Toolbar toolbar;

    @BindView(R.id.rv_task_manage_assist)
    RecyclerView rv_task_manage_assist;

    @BindView(R.id.tv_task_assist_nonetask)
    TextView task_nonetask;

    ManageAssistTaskAdapter mstAdapter;

    LinearLayoutManager layoutManager;

    private List<RspAssistTaskDetails> mList;

    //设置Presenter
    @Override
    protected ManageAssistTaskContract.Presenter initPresenter() {
        return new ManageAssistTaskPresenter(this);
    }

    @Override
    protected int getContentLayoutId() {
        return R.layout.activity_task_manage_assist;
    }

    @Override
    protected void initWidget() {
        super.initWidget();
        initToolbar();

    }

    @Override
    protected void initData() {
        super.initData();
        layoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        Account.load(this);
        mPresenter.getTasks(Account.getProjectId());
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

    public void setAdaper(final List<RspAssistTaskDetails> mlist){
        mList = mlist;
        rv_task_manage_assist.setLayoutManager(layoutManager);
        mstAdapter = new ManageAssistTaskAdapter(R.layout.task_assist_recycle_item,mlist);

        //  设置协调任务item的添加按钮点击事件
        mstAdapter.buttonSetOnClick(new ManageAssistTaskAdapter.ButtonInterface() {
            @Override
            public void onClick(View view, int positon) {

                ReqAssistAddProgress raaProgress = new ReqAssistAddProgress(mlist.get(positon).getId(),mlist.get(positon).getEmplId(),
                        mlist.get(positon).getEmplName(),mlist.get(positon).getExpectTime());
                new TaskAddProgressDialog(ManageAssistTaskActivity.this,R.style.Dialog_Fullscreen,raaProgress).show();

            }
        });
        mstAdapter.setOnItemClickListener(this);
        rv_task_manage_assist.setAdapter(mstAdapter);
    }

    public void setView(List<RspAssistTaskDetails> mlist){

    }
    @Override
    public void success(List<RspAssistTaskDetails> mlist) {

        toolbar.setSubtitle(mlist.get(0).getProject().getProjectName());
        Log.d("data",mlist.toString());
        setAdaper(mlist);
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        Intent intent = new Intent(this, ManageAssistTaskDetailActivity.class);
        intent.putExtra("AssistTaskDetail", JSON.toJSONString(mList.get(position)));
        startActivity(intent);
    }

    @Override
    public void showError(String str) {
        task_nonetask.setText("该项目暂无协调任务");
    }

    @Override
    public void showLoading() {

    }
}
