package com.xzty.cq.tover.businessmanagement.projectmanagement.part.activity.apply;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.xzty.cq.tover.businessmanagement.R;
import com.xzty.cq.tover.businessmanagement.common.factory.ActivityPresenter;
import com.xzty.cq.tover.businessmanagement.projectmanagement.part.adapter.ApplyDetailsAdapter;
import com.xzty.cq.tover.businessmanagement.projectmanagement.part.model.apply.ReqApplyDetails;
import com.xzty.cq.tover.businessmanagement.projectmanagement.part.model.apply.RspApplyDetails;
import com.xzty.cq.tover.businessmanagement.projectmanagement.part.presenter.apply.ApplyDetailsContract;
import com.xzty.cq.tover.businessmanagement.projectmanagement.part.presenter.apply.ApplyDetailsPresenter;

import java.util.List;

import butterknife.BindView;

/**
 * author zzl
 * Created 2018/5/10
 * explain 申请单详情查看
 */

public class PartApplyDetailsActivity extends ActivityPresenter<ApplyDetailsContract.Presenter>
        implements ApplyDetailsContract.View, BaseQuickAdapter.OnItemClickListener {
    @BindView(R.id.tv_toolbarTitle)
    TextView tv_toolbarTitle;

    @BindView(R.id.tv_applyorderdetialNote)
    TextView mText;

    @BindView(R.id.btn_applyorderdetialBack)
    Button mButton;

    @BindView(R.id.rcycler)
    RecyclerView rcycler;

    private RecyclerView.LayoutManager mLayoutManage;

    private ApplyDetailsAdapter mAdapter;

    private List<RspApplyDetails> mList;

    private ReqApplyDetails model;

    @Override
    protected int getContentLayoutId() {
        return R.layout.activity_part_applay_details;
    }

    @Override
    protected void initWidget() {
        super.initWidget();
        tv_toolbarTitle.setText("申请单详情");
    }

    @Override
    protected void initData() {
        super.initData();
        mLayoutManage = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        model = JSON.parseObject(getIntent().getStringExtra("projectApply"), ReqApplyDetails.class);
        mPresenter.getDetails(model.getApplyId() + "");
    }

    @Override
    public void showError(String str) {
        Toast.makeText(this, str, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showLoading() {

    }


    @Override
    protected ApplyDetailsContract.Presenter initPresenter() {
        return new ApplyDetailsPresenter(this);
    }

    @Override
    public void success(List<RspApplyDetails> list) {
        setAdapter(list);
    }

    private void setAdapter(List<RspApplyDetails> list) {
        mList = list;
        rcycler.setLayoutManager(mLayoutManage);
        mAdapter = new ApplyDetailsAdapter(R.layout.part_apply_details_item, mList);
        mAdapter.setOnItemClickListener(this);
        rcycler.setAdapter(mAdapter);
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        Intent intent = new Intent(PartApplyDetailsActivity.this, ApplyOrderPartDetialActivity.class);
        intent.putExtra("projectApplyDetail", JSON.toJSONString(mList.get(position)));
        intent.putExtra("projectApply", JSON.toJSONString(model));
        startActivity(intent);
    }
}
