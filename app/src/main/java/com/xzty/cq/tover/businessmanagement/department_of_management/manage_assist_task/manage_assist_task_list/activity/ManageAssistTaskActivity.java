package com.xzty.cq.tover.businessmanagement.department_of_management.manage_assist_task.manage_assist_task_list.activity;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.xzty.cq.tover.businessmanagement.R;
import com.xzty.cq.tover.businessmanagement.common.factory.Account;
import com.xzty.cq.tover.businessmanagement.common.factory.ActivityPresenter;
import com.xzty.cq.tover.businessmanagement.common.factory.BaseContract;
import com.xzty.cq.tover.businessmanagement.department_of_management.manage_assist_task.manage_assist_task_list.adapter.ManageAssistTaskAdapter;
import com.xzty.cq.tover.businessmanagement.department_of_management.manage_assist_task.manage_assist_task_list.model.RspAssistTaskDetails;
import com.xzty.cq.tover.businessmanagement.department_of_management.manage_assist_task.manage_assist_task_list.presenter.ManageAssistTaskContract;
import com.xzty.cq.tover.businessmanagement.department_of_management.manage_assist_task.manage_assist_task_list.presenter.ManageAssistTaskPresenter;

import java.util.List;

import butterknife.BindView;
import rx.android.schedulers.AndroidSchedulers;

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

    ManageAssistTaskAdapter mstAdapter;

    LinearLayoutManager layoutManager;

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

    public void setAdaper(List<RspAssistTaskDetails> mlist){
        rv_task_manage_assist.setLayoutManager(layoutManager);
        mstAdapter = new ManageAssistTaskAdapter(R.layout.task_assist_recycle_item,mlist);
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

    }

    @Override
    public void showError(String str) {
        Toast.makeText(this,str,Toast.LENGTH_LONG).show();
    }

    @Override
    public void showLoading() {

    }
}
