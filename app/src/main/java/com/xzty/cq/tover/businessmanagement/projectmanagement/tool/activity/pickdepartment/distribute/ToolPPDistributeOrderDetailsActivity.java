package com.xzty.cq.tover.businessmanagement.projectmanagement.tool.activity.pickdepartment.distribute;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.xzty.cq.tover.businessmanagement.R;
import com.xzty.cq.tover.businessmanagement.common.factory.ActivityPresenter;
import com.xzty.cq.tover.businessmanagement.projectmanagement.tool.adapter.ToolPDDistributeDetailsAdapter;
import com.xzty.cq.tover.businessmanagement.projectmanagement.tool.model.pickdepartment.RspDistributeOrderDetails;
import com.xzty.cq.tover.businessmanagement.projectmanagement.tool.presenter.pickdepartment.distribute.DistributeOrderDetailsContract;
import com.xzty.cq.tover.businessmanagement.projectmanagement.tool.presenter.pickdepartment.distribute.DistributeOrderDetailsPresenter;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class ToolPPDistributeOrderDetailsActivity extends ActivityPresenter<DistributeOrderDetailsContract.Presenter> implements DistributeOrderDetailsContract.View, BaseQuickAdapter.OnItemClickListener, BaseQuickAdapter.OnItemLongClickListener {

    @BindView(R.id.tv_toolbarTitle)
    TextView textView;

    @BindView(R.id.recycle_tool)
    RecyclerView recycle_tool;

    @BindView(R.id.tbdbacd_tv_sum)
    TextView tbdbacd_tv_sum;

    @BindView(R.id.tv_notice)
    TextView tv_notice;

    private ToolPDDistributeDetailsAdapter mAdapter;

    private List<RspDistributeOrderDetails> mList;

    private int toolDistId;

    private int status;

    @Override
    protected int getContentLayoutId() {
        return R.layout.activity_tool_ppdistribute_order_details;
    }

    @Override
    protected void initWidget() {
        super.initWidget();
        textView.setText("机具采购分配单详情");
    }

    @Override
    protected void initData() {
        super.initData();
    }

    @OnClick(R.id.bt_send)
    void click() {
        mPresenter.confirm(toolDistId, mList);
    }

    @Override
    protected void onResume() {
        super.onResume();
        toolDistId = getIntent().getIntExtra("toolDistId", -1);
        status = getIntent().getIntExtra("state", -1);
        mPresenter.getData(toolDistId, status);
    }

    @Override
    public void showError(String str) {
        Toast.makeText(this, str, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void success(List<RspDistributeOrderDetails> list) {
        mList = list;
        setAdapter();
    }

    @Override
    public void clickSuccess(List<RspDistributeOrderDetails> list) {
        mList = list;
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void confirmSuccess() {
        Toast.makeText(this, "提交成功", Toast.LENGTH_SHORT).show();
        finish();
    }

    private void setAdapter() {
        recycle_tool.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        mAdapter = new ToolPDDistributeDetailsAdapter(R.layout.tool_pd_distribute_details_recycle_item, mList);
        mAdapter.setOnItemClickListener(this);
        mAdapter.setOnItemLongClickListener(this);
        recycle_tool.setAdapter(mAdapter);
    }

    @Override
    protected DistributeOrderDetailsContract.Presenter initPresenter() {
        return new DistributeOrderDetailsPresenter(this);
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        if (mList.get(position).getDisable() == 0) {
            Toast.makeText(this, "请先入库", Toast.LENGTH_SHORT).show();
        } else {
            mPresenter.itemClick(mList, position);
        }

    }

    @Override
    public boolean onItemLongClick(BaseQuickAdapter adapter, View view, int position) {
        if (mList.get(position).getDisable() != 0) {
            Toast.makeText(this, "您已入库", Toast.LENGTH_SHORT).show();
        } else {
            Intent intent = new Intent(this, ToolPDInActivity.class);
            intent.putExtra("distDetailId", mList.get(position).getToolDistDetailId());
            startActivity(intent);
        }
        return false;
    }
}
