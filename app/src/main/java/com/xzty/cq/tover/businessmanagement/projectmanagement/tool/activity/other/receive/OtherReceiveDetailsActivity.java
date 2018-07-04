package com.xzty.cq.tover.businessmanagement.projectmanagement.tool.activity.other.receive;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;
import android.widget.Toast;

import com.xzty.cq.tover.businessmanagement.R;
import com.xzty.cq.tover.businessmanagement.common.factory.ActivityPresenter;
import com.xzty.cq.tover.businessmanagement.projectmanagement.tool.adapter.OtherReceiveDetailsAdapter;
import com.xzty.cq.tover.businessmanagement.projectmanagement.tool.model.other.RspReceiveDetais;
import com.xzty.cq.tover.businessmanagement.projectmanagement.tool.presenter.other.receive.OtherReceiveDetailsContract;
import com.xzty.cq.tover.businessmanagement.projectmanagement.tool.presenter.other.receive.OtherReceiveDetailsPresenter;

import java.util.List;

import butterknife.BindView;

public class OtherReceiveDetailsActivity extends ActivityPresenter<OtherReceiveDetailsContract.Presenter> implements OtherReceiveDetailsContract.View {
    @BindView(R.id.tv_toolbarTitle)
    TextView tv_toolbarTitle;

    @BindView(R.id.recycle_details)
    RecyclerView recycle_details;

    private List<RspReceiveDetais> mList;

    private RecyclerView.LayoutManager mLayoutmanage;

    @Override
    protected int getContentLayoutId() {
        return R.layout.activity_other_receive_details;
    }

    @Override
    protected void initWidget() {
        super.initWidget();
        tv_toolbarTitle.setText("机具收货详情");
    }

    @Override
    protected void initData() {
        super.initData();
        mPresenter.getData(getIntent().getIntExtra("collectId",-1));
    }

    @Override
    public void showError(String str) {
        Toast.makeText(this,str,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void success(List<RspReceiveDetais> list) {
        mList = list;
        setAdapter();
    }

    private void setAdapter() {
        mLayoutmanage = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        OtherReceiveDetailsAdapter adapter = new OtherReceiveDetailsAdapter(R.layout.tool_other_receive_details_recycle_item,mList);
        recycle_details.setLayoutManager(mLayoutmanage);
        recycle_details.setAdapter(adapter);
    }

    @Override
    protected OtherReceiveDetailsContract.Presenter initPresenter() {
        return new OtherReceiveDetailsPresenter(this);
    }
}
