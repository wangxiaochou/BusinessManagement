package com.xzty.cq.tover.businessmanagement.projectmanagement.tool.activity.other.back;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;
import android.widget.Toast;

import com.xzty.cq.tover.businessmanagement.R;
import com.xzty.cq.tover.businessmanagement.common.factory.ActivityPresenter;
import com.xzty.cq.tover.businessmanagement.projectmanagement.tool.adapter.ToolOtherBackDetailsAdapter;
import com.xzty.cq.tover.businessmanagement.projectmanagement.tool.model.other.RspBackDetails;
import com.xzty.cq.tover.businessmanagement.projectmanagement.tool.presenter.other.back.OtherBackDetailsContract;
import com.xzty.cq.tover.businessmanagement.projectmanagement.tool.presenter.other.back.OtherBackDetailsPresenter;

import java.util.List;

import butterknife.BindView;

public class ToolOtherBackDetailsActivity extends ActivityPresenter<OtherBackDetailsContract.Presenter> implements OtherBackDetailsContract.View {
    @BindView(R.id.tv_toolbarTitle)
    TextView tv_toolbarTitle;

    @BindView(R.id.recycle_details)
    RecyclerView recycle_details;

    private List<RspBackDetails> mList;

    @Override
    protected int getContentLayoutId() {
        return R.layout.activity_tool_other_back_details;
    }

    @Override
    protected void initWidget() {
        super.initWidget();
        tv_toolbarTitle.setText("机具返库详情");
    }

    @Override
    protected void initData() {
        super.initData();
        mPresenter.getData(getIntent().getIntExtra("backId",-1));
    }
    private void setAdapter(){
        recycle_details.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        ToolOtherBackDetailsAdapter mAdaper = new ToolOtherBackDetailsAdapter(R.layout.tool_other_backdetails_recycle_item,mList);
        recycle_details.setAdapter(mAdaper);
    }

    @Override
    public void showError(String str) {
        Toast.makeText(this,str,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void success(List<RspBackDetails> list) {
        mList = list;
        setAdapter();
    }

    @Override
    protected OtherBackDetailsContract.Presenter initPresenter() {
        return new OtherBackDetailsPresenter(this);
    }
}
