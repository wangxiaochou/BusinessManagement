package com.xzty.cq.tover.businessmanagement.projectmanagement.tool.activity.projectmanagedepartment.back;

import android.content.Intent;
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
import com.xzty.cq.tover.businessmanagement.projectmanagement.tool.model.back.RspAuditList;
import com.xzty.cq.tover.businessmanagement.projectmanagement.tool.presenter.projectmanage.back.ToolProjectManageAuditListContract;
import com.xzty.cq.tover.businessmanagement.projectmanagement.tool.presenter.projectmanage.back.ToolProjectManageAuditListPrsenter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import butterknife.BindView;

public class ToolPMBackAuditActivity extends ActivityPresenter<ToolProjectManageAuditListContract.Presenter> implements ToolProjectManageAuditListContract.View, BaseQuickAdapter.OnItemClickListener {
    @BindView(R.id.tv_toolbarTitle)
    TextView tv_toolbarTitle;

    @BindView(R.id.recycle_audit)
    RecyclerView recycle_audit;

    private List<RspAuditList> mList;

    @Override
    protected int getContentLayoutId() {
        return R.layout.activity_tool_pmback_audit;
    }

    @Override
    protected void initWidget() {
        super.initWidget();
        tv_toolbarTitle.setText("机具返库单审核");
    }

    @Override
    protected void initData() {
        super.initData();
    }

    @Override
    public void showError(String str) {
        Toast.makeText(this, str, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void seccess(List<RspAuditList> list) {
        mList = list;
        recycle_audit.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        AuditAdapter adapter = new AuditAdapter(R.layout.tool_pd_backauditlist_recycle_item, mList);
        adapter.setOnItemClickListener(this);
        recycle_audit.setAdapter(adapter);
    }

    @Override
    protected ToolProjectManageAuditListContract.Presenter initPresenter() {
        return new ToolProjectManageAuditListPrsenter(this);
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
            Intent intent = new Intent(this, ToolPMBackAuditDetailsActivity.class);
            intent.putExtra("backId", mList.get(position).getToolBackId());
            intent.putExtra("state", mList.get(position).getIsComfirm());
            startActivity(intent);
    }

    class AuditAdapter extends BaseQuickAdapter<RspAuditList, BaseViewHolder> {

        public AuditAdapter(int layoutResId, @Nullable List<RspAuditList> data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, RspAuditList item) {
            helper.setText(R.id.tv_number, "编号: " + item.getToolBackNo());
            helper.setText(R.id.tv_name, "操作人: " + item.getEplyName());
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            try {
                Date date = format.parse(item.getToolBackTime());
                helper.setText(R.id.tv_name, "时间: " + format.format(date));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            helper.setText(R.id.tv_note, "备注: " + item.getToolBackNote());

            switch (item.getIsComfirm()) {
                case 0:
                    helper.setText(R.id.tv_state, "状态: " + "未确认");
                    break;
                case 1:
                    helper.setText(R.id.tv_state, "状态: " + "采购部未确认");
                    break;
                case 2:
                    helper.setText(R.id.tv_state, "状态: " + "采购部已确认");
                    break;
                default:
                    helper.setText(R.id.tv_state, "状态: " + "异常");
                    break;
            }
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        mPresenter.getData();
    }
}
