package com.xzty.cq.tover.businessmanagement.projectmanagement.tool.activity.other.diliver;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;
import android.widget.Toast;

import com.xzty.cq.tover.businessmanagement.R;
import com.xzty.cq.tover.businessmanagement.common.factory.ActivityPresenter;
import com.xzty.cq.tover.businessmanagement.projectmanagement.tool.adapter.ToolOtherDiliverDetailsAdaper;
import com.xzty.cq.tover.businessmanagement.projectmanagement.tool.model.other.RspDiliverDetails;
import com.xzty.cq.tover.businessmanagement.projectmanagement.tool.presenter.other.diliver.OtherDiliverDetailsContract;
import com.xzty.cq.tover.businessmanagement.projectmanagement.tool.presenter.other.diliver.OtherDiliverDetailsPresenter;

import java.util.List;

import butterknife.BindView;

public class OtherDiliverOrderDetailsActivity extends ActivityPresenter<OtherDiliverDetailsContract.Presenter> implements OtherDiliverDetailsContract.View {
    @BindView(R.id.tv_toolbarTitle)
    TextView textView;

    @BindView(R.id.recycle_info)
    RecyclerView recycle_info;

    private List<RspDiliverDetails> mList;

    private RecyclerView.LayoutManager mLayoutManage;

    private ToolOtherDiliverDetailsAdaper mAdapter;

    @Override
    protected int getContentLayoutId() {
        return R.layout.activity_other_diliver_order_details;
    }

    @Override
    protected void initWidget() {
        super.initWidget();
        textView.setText("机具发货单详情");
    }

    @Override
    protected void initData() {
        super.initData();
        int outId = getIntent().getIntExtra("outId",-1);
        mPresenter.getData(outId);
    }

    @Override
    public void showError(String str) {
        Toast.makeText(this,str,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void success(List<RspDiliverDetails> list) {
        mList = list;
        setAdapter();
    }

    private void setAdapter(){
        mLayoutManage = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        mAdapter = new ToolOtherDiliverDetailsAdaper(R.layout.tool_other_diliver_details_recycle_item,mList);
        recycle_info.setLayoutManager(mLayoutManage);
        recycle_info.setAdapter(mAdapter);
    }

    @Override
    protected OtherDiliverDetailsContract.Presenter initPresenter() {
        return new OtherDiliverDetailsPresenter(this);
    }
}
