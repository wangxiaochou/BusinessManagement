package com.xzty.cq.tover.businessmanagement.projectmanagement.tool.activity.other.receive;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.xzty.cq.tover.businessmanagement.R;
import com.xzty.cq.tover.businessmanagement.common.factory.ActivityPresenter;
import com.xzty.cq.tover.businessmanagement.projectmanagement.tool.adapter.ToolOtherReceiveListAdapter;
import com.xzty.cq.tover.businessmanagement.projectmanagement.tool.model.other.RspReceiveList;
import com.xzty.cq.tover.businessmanagement.projectmanagement.tool.presenter.other.receive.OtherReceiveListContrat;
import com.xzty.cq.tover.businessmanagement.projectmanagement.tool.presenter.other.receive.OtherReceiveListPresenter;

import java.util.List;

import butterknife.BindView;

public class OtherReceiveListActivity extends ActivityPresenter<OtherReceiveListContrat.Presenter> implements OtherReceiveListContrat.View ,BaseQuickAdapter.OnItemClickListener{
    @BindView(R.id.tv_toolbarTitle)
    TextView tv_toolbarTitle;

    @BindView(R.id.recycle_receivelist)
    RecyclerView recycle_receivelist;

    private RecyclerView.LayoutManager mLayoutManage;

    private ToolOtherReceiveListAdapter mAdapter;

    private List<RspReceiveList> mList;

    @Override
    protected int getContentLayoutId() {
        return R.layout.activity_other_receive_list;
    }

    @Override
    protected void initWidget() {
        super.initWidget();
        tv_toolbarTitle.setText("机具收货单");
    }

    @Override
    protected void initData() {
        super.initData();
        mPresenter.getData();
    }
    private void setAdapter(){
        mLayoutManage = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        mAdapter = new ToolOtherReceiveListAdapter(R.layout.other_receive_list_recycle_item,mList);
        recycle_receivelist.setLayoutManager(mLayoutManage);
        mAdapter.setOnItemClickListener(this);
        recycle_receivelist.setAdapter(mAdapter);
    }

    @Override
    public void showError(String str) {
        Toast.makeText(this,str,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void success(List<RspReceiveList> list) {
        mList = list;
        setAdapter();
    }

    @Override
    protected OtherReceiveListContrat.Presenter initPresenter() {
        return new OtherReceiveListPresenter(this);
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        Intent intent = new Intent(this,OtherReceiveDetailsActivity.class);
        intent.putExtra("collectId",mList.get(position).getToolCollectId());
        startActivity(intent);
    }
}
