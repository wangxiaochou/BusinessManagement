package com.xzty.cq.tover.businessmanagement.projectmanagement.tool.activity.other.back;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.xzty.cq.tover.businessmanagement.R;
import com.xzty.cq.tover.businessmanagement.common.factory.ActivityPresenter;
import com.xzty.cq.tover.businessmanagement.projectmanagement.tool.adapter.ToolOtherBackListAdapter;
import com.xzty.cq.tover.businessmanagement.projectmanagement.tool.model.other.RspBackList;
import com.xzty.cq.tover.businessmanagement.projectmanagement.tool.presenter.other.back.OtherBackListContract;
import com.xzty.cq.tover.businessmanagement.projectmanagement.tool.presenter.other.back.OtherBackListPresenter;

import java.util.List;

import butterknife.BindView;

public class ToolOtherBackListActivity extends ActivityPresenter<OtherBackListContract.Presenter>
        implements OtherBackListContract.View, BaseQuickAdapter.OnItemClickListener {
    @BindView(R.id.tv_toolbarTitle)
    TextView textView;

    @BindView(R.id.recycle_list)
    RecyclerView recycle_list;

    private List<RspBackList> mList;

    @Override
    protected int getContentLayoutId() {
        return R.layout.activity_tool_other_back_list;
    }

    @Override
    protected void initWidget() {
        super.initWidget();
        textView.setText("机具返库单");
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
    public void success(List<RspBackList> list) {
        mList = list;
        setAdapter();
    }

    private void setAdapter() {
        recycle_list.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        ToolOtherBackListAdapter adapter = new ToolOtherBackListAdapter(R.layout.tool_other_backlist_recycle_item, mList);
        adapter.setOnItemClickListener(this);
        recycle_list.setAdapter(adapter);

    }

    @Override
    protected OtherBackListContract.Presenter initPresenter() {
        return new OtherBackListPresenter(this);
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        Intent intent = new Intent(this, ToolOtherBackDetailsActivity.class);
        intent.putExtra("backId", mList.get(position).getToolBackId());
        startActivity(intent);
    }
}
