package com.xzty.cq.tover.businessmanagement.projectmanagement.tool.activity.pickdepartment.affirm;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.xzty.cq.tover.businessmanagement.R;
import com.xzty.cq.tover.businessmanagement.common.factory.ActivityPresenter;
import com.xzty.cq.tover.businessmanagement.projectmanagement.tool.adapter.ToolApplyDetailsAdapter;
import com.xzty.cq.tover.businessmanagement.projectmanagement.tool.model.projectmanage.RspAffirmDetails;
import com.xzty.cq.tover.businessmanagement.projectmanagement.tool.presenter.pickdepartment.affirm.PDAffirmOrderContract;
import com.xzty.cq.tover.businessmanagement.projectmanagement.tool.presenter.pickdepartment.affirm.PDAffirmOrderPresenter;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class ToolPDApplyAffirmDetailsActivity extends ActivityPresenter<PDAffirmOrderContract.Presenter>
        implements PDAffirmOrderContract.View,
        BaseQuickAdapter.OnItemClickListener,
        BaseQuickAdapter.OnItemLongClickListener{
    @BindView(R.id.tv_toolbarTitle)
    TextView textView;
    @BindView(R.id.recycle_details)
    RecyclerView recycle_details;

    @BindView(R.id.tmdacd_tv_sum)
    TextView tmdacdTvSum;

    @BindView(R.id.tv_notice)
    TextView tvNotice;

    @BindView(R.id.btn_sure)
    Button btn_sure;

    private ToolApplyDetailsAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManage;
    private List<RspAffirmDetails> mList;
    private int applyId;
    private int checkedCount, refusedCount;
    @Override
    protected int getContentLayoutId() {
        return R.layout.activity_tool_apply_affirm_details2;
    }
    @Override
    protected void initWidget() {
        super.initWidget();
        textView.setText("机具申请单详情");
    }

    @Override
    protected void initData() {
        super.initData();
        applyId = getIntent().getIntExtra("applyId", 1);
        mPresenter.getDetails(applyId);
        if (getIntent().getIntExtra("state", -1) == 1) {
            Toast.makeText(this, "长按可拒绝/恢复某项", Toast.LENGTH_LONG).show();
        } else {
            tvNotice.setText("此申请单已确认过，已进入后续流程");
            tvNotice.setVisibility(View.VISIBLE);
            tmdacdTvSum.setVisibility(View.GONE);
            btn_sure.setEnabled(false);
        }
    }

    private void setAdapter() {
        mLayoutManage = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mAdapter = new ToolApplyDetailsAdapter(R.layout.tool_apply_affirm_details_recycle_item, mList);
        recycle_details.setLayoutManager(mLayoutManage);
        mAdapter.setOnItemLongClickListener(this);
        mAdapter.setOnItemClickListener(this);
        recycle_details.setAdapter(mAdapter);
    }


    @OnClick(R.id.btn_sure)
    void click() {
        mPresenter.sure(mList, applyId);
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        if (!TextUtils.isEmpty(mList.get(position).getToolDetailApplyToolName())) {
            return;
        }
        mPresenter.itemClick(mList, position);
    }

    @Override
    public boolean onItemLongClick(BaseQuickAdapter adapter, View view, int position) {
        mPresenter.longClick(mList, position);
        return false;
    }

    @Override
    public void showError(String str) {
        Toast.makeText(this,str,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void detailsSuccess(List<RspAffirmDetails> list) {
        mList = list;
        setAdapter();
        tmdacdTvSum.setText(("共计 " + mList.size() + "项：拒绝 0" + "，待采购：0" + "，待发货：0"+"已处理"));
    }

    @Override
    public void reFreshAdapter(List<RspAffirmDetails> sureList, int check, int refuse,int ignore) {
        mList = sureList;
        mAdapter.notifyDataSetChanged();
        tmdacdTvSum.setText(("共计" + mList.size() + "项：拒绝" + refuse + "，待采购：" + (mList.size() - check-refuse) + "，待发货：" + check)+"已处理"+ignore);
    }

    @Override
    public void sureSuccess() {
        Toast.makeText(this, "提交成功", Toast.LENGTH_SHORT).show();
        finish();
    }

    @Override
    protected PDAffirmOrderContract.Presenter initPresenter() {
        return new PDAffirmOrderPresenter(this);
    }
}
