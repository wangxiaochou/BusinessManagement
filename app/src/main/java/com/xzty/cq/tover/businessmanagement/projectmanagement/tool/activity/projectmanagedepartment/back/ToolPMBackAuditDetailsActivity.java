package com.xzty.cq.tover.businessmanagement.projectmanagement.tool.activity.projectmanagedepartment.back;

import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.xzty.cq.tover.businessmanagement.R;
import com.xzty.cq.tover.businessmanagement.common.factory.ActivityPresenter;
import com.xzty.cq.tover.businessmanagement.projectmanagement.tool.model.back.RspAuditDetails;
import com.xzty.cq.tover.businessmanagement.projectmanagement.tool.presenter.projectmanage.back.ToolProjectManageAuditDetailsContract;
import com.xzty.cq.tover.businessmanagement.projectmanagement.tool.presenter.projectmanage.back.ToolProjectManageAuditDetailsPrsenter;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class ToolPMBackAuditDetailsActivity extends ActivityPresenter<ToolProjectManageAuditDetailsContract.Presenter> implements ToolProjectManageAuditDetailsContract.View, BaseQuickAdapter.OnItemChildClickListener {
    @BindView(R.id.tv_toolbarTitle)
    TextView tv_toolbarTitle;

    @BindView(R.id.recycle_details)
    RecyclerView recycle_details;

    @BindView(R.id.tv_choose_type)
    TextView tv_choose_type;

    private List<RspAuditDetails> mList;

    private AuditDetailsAdapter mAdapter;

    private int backId;

    @Override
    protected int getContentLayoutId() {
        return R.layout.activity_tool_pmback_audit_details;
    }

    @Override
    protected void initWidget() {
        super.initWidget();
        tv_toolbarTitle.setText("机具返库单审核");
    }

    @Override
    protected void initData() {
        super.initData();
        backId = getIntent().getIntExtra("backId", -1);
        mPresenter.getData(backId);
    }

    @Override
    public void showError(String str) {
        Toast.makeText(this, str, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void seccess(List<RspAuditDetails> list) {
        mList = list;
        setAdapter();
    }

    @Override
    public void clickSucceess(List<RspAuditDetails> list, String chooseString) {
        mList = list;
        mAdapter.notifyDataSetChanged();
        tv_choose_type.setText(chooseString);
    }

    @Override
    public void submitSuccess() {
        finish();
        Toast.makeText(this, "提交成功", Toast.LENGTH_SHORT).show();
    }

    private void setAdapter() {
        recycle_details.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        mAdapter = new AuditDetailsAdapter(R.layout.tool_pd_backauditdetails_recycle_item, mList);
        mAdapter.setOnItemChildClickListener(this);
        recycle_details.setAdapter(mAdapter);
        if (getIntent().getIntExtra("state", -1) != 0) {
            tv_choose_type.setText("此申请单已确认过，已进入后续流程");
        }
    }

    @OnClick(R.id.bt_confirm)
    void click() {
        mPresenter.submit(mList, backId);
    }

    @Override
    protected ToolProjectManageAuditDetailsContract.Presenter initPresenter() {
        return new ToolProjectManageAuditDetailsPrsenter(this);
    }

    @Override
    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
        if (getIntent().getIntExtra("state", -1) != 0) {
            tv_choose_type.setText("此申请单已确认过，已进入后续流程");

        }else{
            switch (view.getId()) {
                case R.id.bt_damage:
                    mPresenter.demageClick(mList, position);
                    break;
                case R.id.bt_fix:
                    mPresenter.fixClick(mList, position);
                    break;
            }
        }

    }

    class AuditDetailsAdapter extends BaseQuickAdapter<RspAuditDetails, BaseViewHolder> {

        public AuditDetailsAdapter(int layoutResId, @Nullable List<RspAuditDetails> data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, RspAuditDetails item) {
            if (item.isChecked) {
                helper.setChecked(R.id.cb, true);
            } else {
                helper.setChecked(R.id.cb, false);
            }
            helper.setText(R.id.tv_number, "编号: " + item.getToolNumber());
            helper.setText(R.id.tv_name, "名称: " + item.getToolName());
            helper.setText(R.id.tv_model, "型号: " + item.getToolModelNumber());
            helper.setText(R.id.tv_brand, "品牌: " + item.getToolBrand());
            helper.setText(R.id.tv_power, "功率: " + item.getToolPower());
            helper.setText(R.id.tv_depot, "仓库: " + item.getToolDepot());
            helper.setText(R.id.tv_department, "部门: " + item.getToolDepartment());
            switch (item.getToolBackDetailState()) {
                case 0:
                    helper.setText(R.id.tv_state, "状态: 未处理");
                    break;
                case 1:
                    helper.setText(R.id.tv_state, "状态: 完好");
                    break;
                case 2:
                    helper.setText(R.id.tv_state, "状态: 维修");
                    break;
                case 3:
                    helper.setText(R.id.tv_state, "状态: 报废");
                    break;

            }
            helper.addOnClickListener(R.id.bt_fix).addOnClickListener(R.id.bt_damage);
        }
    }
}
