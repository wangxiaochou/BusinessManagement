package com.xzty.cq.tover.businessmanagement.projectmanagement.part.activity.pick;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.xzty.cq.tover.businessmanagement.R;
import com.xzty.cq.tover.businessmanagement.common.factory.ActivityPresenter;
import com.xzty.cq.tover.businessmanagement.projectmanagement.part.adapter.PartPickDetailsAdapter;
import com.xzty.cq.tover.businessmanagement.projectmanagement.part.model.pick.RspPickList;
import com.xzty.cq.tover.businessmanagement.projectmanagement.part.model.pick.RspPickOrder;
import com.xzty.cq.tover.businessmanagement.projectmanagement.part.presenter.pick.PickDetailsContract;
import com.xzty.cq.tover.businessmanagement.projectmanagement.part.presenter.pick.PickDetailsPresenter;

import java.util.List;

import butterknife.BindView;

/**
 * author zzl
 * Created 2018/5/14
 * explain  采购单详情
 */

public class PartPickDetailsActivity extends ActivityPresenter<PickDetailsContract.Presenter>
        implements PickDetailsContract.View, View.OnClickListener {
    @BindView(R.id.tv_toolbarTitle)
    TextView tv_toolbarTitle;

    @BindView(R.id.tv_distorderdetialNote)
    TextView mTextView;

    @BindView(R.id.btn_distorderdetialBack)
    Button btn_distorderdetialBack;

    @BindView(R.id.recycle_details)
    RecyclerView recycle_details;

    private RspPickOrder mPickOrder;

    private RecyclerView.LayoutManager mLayoutManage;

    private List<RspPickList> mList;

    private RspPickOrder pickOrder;

    private PartPickDetailsAdapter mAdapter;

    public static int isComfirm;

    @Override
    protected int getContentLayoutId() {
        return R.layout.activity_part_pick_details;
    }

    @Override
    protected void initWidget() {
        super.initWidget();
        tv_toolbarTitle.setText("采购单详细查看");
        btn_distorderdetialBack.setOnClickListener(this);
        mPickOrder = JSON.parseObject(getIntent().getStringExtra("distOrder"), RspPickOrder.class);
        isComfirm = getIntent().getIntExtra("status",-1);
    }

    private void setAdapter() {
        mLayoutManage = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mAdapter = new PartPickDetailsAdapter(R.layout.part_pick_details_recycle_item,mList);
        recycle_details.setLayoutManager(mLayoutManage);
        recycle_details.setAdapter(mAdapter);
    }

    @Override
    protected void initData() {
        super.initData();
        String s = getIntent().getStringExtra("distOrder");
        pickOrder =  JSON.parseObject(getIntent().getStringExtra("distOrder"), RspPickOrder.class);
        String partNo = pickOrder.getDistNote();
        mTextView.setText(partNo);
        mPresenter.getData(pickOrder.getDistId());
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_distorderdetialBack:
                finish();
                break;
        }
    }

    @Override
    public void showError(String str) {
        Toast.makeText(this,str,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void success(List<RspPickList> list) {
        mList = list;
        setAdapter();
    }

    @Override
    protected PickDetailsContract.Presenter initPresenter() {
        return new PickDetailsPresenter(this);
    }
}
