package com.xzty.cq.tover.businessmanagement.projectmanagement.tool.activity.other.diliver;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.xzty.cq.tover.businessmanagement.R;
import com.xzty.cq.tover.businessmanagement.common.factory.ActivityPresenter;
import com.xzty.cq.tover.businessmanagement.projectmanagement.tool.adapter.ToolOtherDiliverOrderAdapter;
import com.xzty.cq.tover.businessmanagement.projectmanagement.tool.model.other.RspOtherDiliver;
import com.xzty.cq.tover.businessmanagement.projectmanagement.tool.presenter.other.diliver.OtherDiliverContract;
import com.xzty.cq.tover.businessmanagement.projectmanagement.tool.presenter.other.diliver.OtherDiliverPresenter;

import java.util.List;

import butterknife.BindView;

public class OtherDiliverOrderActivity extends ActivityPresenter<OtherDiliverContract.Presenter> implements OtherDiliverContract.View, BaseQuickAdapter.OnItemClickListener {
    @BindView(R.id.tv_toolbarTitle)
    TextView textView;

    @BindView(R.id.recycle_info)
    RecyclerView recycle_info;

    private RecyclerView.LayoutManager mLayoutManage;

    private ToolOtherDiliverOrderAdapter mAdapter;

    private List<RspOtherDiliver> mList;

    @Override
    protected int getContentLayoutId() {
        return R.layout.activity_other_diliver_order;
    }

    @Override
    protected void initWidget() {
        super.initWidget();
        textView.setText("机具发货单");
    }

    @Override
    protected void initData() {
        super.initData();
        mPresenter.getData();
    }

    private void setAdapter() {
        mLayoutManage = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mAdapter = new ToolOtherDiliverOrderAdapter(R.layout.tool_other_diliverorder_recycle_item, mList);
        recycle_info.setLayoutManager(mLayoutManage);
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
    public void dataSuccess(List<RspOtherDiliver> list) {
        mList = list;
        setAdapter();
    }

    @Override
    protected OtherDiliverContract.Presenter initPresenter() {
        return new OtherDiliverPresenter(this);
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        Intent intent = new Intent(this, OtherDiliverOrderDetailsActivity.class);
        intent.putExtra("outId", mList.get(position).getToolOutId());
        startActivity(intent);
    }
}
