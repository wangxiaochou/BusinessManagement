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
import com.xzty.cq.tover.businessmanagement.projectmanagement.tool.adapter.ToolPDDistributeOrderListAdapter;
import com.xzty.cq.tover.businessmanagement.projectmanagement.tool.model.pickdepartment.RspDistributeOrderList;
import com.xzty.cq.tover.businessmanagement.projectmanagement.tool.presenter.pickdepartment.distribute.DistributeOrderListContract;
import com.xzty.cq.tover.businessmanagement.projectmanagement.tool.presenter.pickdepartment.distribute.DistributeOrderListPresenter;

import java.util.List;

import butterknife.BindView;

public class ToolPPDistributeOrderActivity extends ActivityPresenter<DistributeOrderListContract.Presenter> implements DistributeOrderListContract.View, BaseQuickAdapter.OnItemClickListener {
    @BindView(R.id.tv_toolbarTitle)
    TextView textView;
    @BindView(R.id.recyle_info)
    RecyclerView recycle_info;

    private RecyclerView.LayoutManager mLayoutManage;

    private List<RspDistributeOrderList> mList;

    private ToolPDDistributeOrderListAdapter mAdapter;

    @Override
    protected int getContentLayoutId() {
        return R.layout.activity_tool_ppdistribute_order;
    }

    @Override
    protected void initWidget() {
        super.initWidget();
        textView.setText("机具采购分配单");
    }

    @Override
    protected void initData() {
        super.initData();
        mPresenter.getData();
    }

    @Override
    public void showError(String str) {
        Toast.makeText(this, str, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void success(List<RspDistributeOrderList> list) {
        mList = list;
        setAdapter();
    }

    private void setAdapter() {
        mLayoutManage = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mAdapter = new ToolPDDistributeOrderListAdapter(R.layout.tool_pickdepartmen_distributeorderlist_recycle_item, mList);
        recycle_info.setLayoutManager(mLayoutManage);
        mAdapter.setOnItemClickListener(this);
        recycle_info.setAdapter(mAdapter);
    }

    @Override
    protected DistributeOrderListContract.Presenter initPresenter() {
        return new DistributeOrderListPresenter(this);
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        Intent intent = new Intent(this, ToolPPDistributeOrderDetailsActivity.class);
        intent.putExtra("toolDistId", mList.get(position).getToolDistId());
        intent.putExtra("state", mList.get(position).getIsComfirm());
        startActivity(intent);
    }
}
