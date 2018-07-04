package com.xzty.cq.tover.businessmanagement.projectmanagement.tool.activity.pickdepartment.affirm;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.xzty.cq.tover.businessmanagement.R;
import com.xzty.cq.tover.businessmanagement.common.factory.Account;
import com.xzty.cq.tover.businessmanagement.common.factory.ActivityPresenter;
import com.xzty.cq.tover.businessmanagement.projectmanagement.tool.adapter.ToolApplyAffirmAdapter;
import com.xzty.cq.tover.businessmanagement.projectmanagement.tool.model.projectmanage.RspAffirmList;
import com.xzty.cq.tover.businessmanagement.projectmanagement.tool.presenter.pickdepartment.affirm.AffirmOrderContract;
import com.xzty.cq.tover.businessmanagement.projectmanagement.tool.presenter.pickdepartment.affirm.AffirmOrderPresenter;

import java.util.List;

import butterknife.BindView;

public class ToolPDApplyOrderAffirmActivity extends ActivityPresenter<AffirmOrderContract.Presenter> implements AffirmOrderContract.View ,BaseQuickAdapter.OnItemClickListener{
    @BindView(R.id.tv_toolbarTitle)
    TextView textView;

    @BindView(R.id.recycle_info)
    RecyclerView recycle_info;

    private RecyclerView.LayoutManager mLayoutManage;

    private List<RspAffirmList> mList;

    private ToolApplyAffirmAdapter mAdapter;

    @Override
    protected int getContentLayoutId() {
        return R.layout.activity_tool_apply_order_affirm;
    }

    @Override
    protected void initWidget() {
        super.initWidget();
        textView.setText("机具申请单确认");
    }

    @Override
    protected void initData() {
        super.initData();
    }

    private void setAdatper() {
        mLayoutManage = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recycle_info.setLayoutManager(mLayoutManage);
        mAdapter = new ToolApplyAffirmAdapter(R.layout.tool_pm_affirm_apply_recycle_item, mList);
        mAdapter.setOnItemClickListener(this);
        recycle_info.setAdapter(mAdapter);
    }

    @Override
    public void showError(String str) {
        Toast.makeText(this, str, Toast.LENGTH_SHORT).show();

    }

    @Override
    public void showLoading() {
    }

    @Override
    public void success(List<RspAffirmList> list) {
        mList = list;
        setAdatper();
    }

    @Override
    protected AffirmOrderContract.Presenter initPresenter() {
        return new AffirmOrderPresenter(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Account.load(this);
        mPresenter.getApplyList(Account.getProjectId());
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        Intent intent = new Intent(this, ToolPDApplyAffirmDetailsActivity.class);
        intent.putExtra("applyId", mList.get(position).getToolApplyId());
        intent.putExtra("state", mList.get(position).getIsComfirm());
        startActivity(intent);
    }
}
