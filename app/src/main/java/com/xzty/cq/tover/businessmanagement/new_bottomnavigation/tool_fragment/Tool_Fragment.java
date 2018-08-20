package com.xzty.cq.tover.businessmanagement.new_bottomnavigation.tool_fragment;

import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import com.xzty.cq.tover.businessmanagement.R;
import com.xzty.cq.tover.businessmanagement.common.app.BaseFragment;
import com.xzty.cq.tover.businessmanagement.projectmanagement.tool.activity.other.back.ToolOtherBackApplyActivity;
import com.xzty.cq.tover.businessmanagement.projectmanagement.tool.activity.other.back.ToolOtherBackListActivity;
import com.xzty.cq.tover.businessmanagement.projectmanagement.tool.activity.other.diliver.OtherDiliverOrderActivity;
import com.xzty.cq.tover.businessmanagement.projectmanagement.tool.activity.other.receive.OtherReceiveActivity;
import com.xzty.cq.tover.businessmanagement.projectmanagement.tool.activity.other.receive.OtherReceiveListActivity;
import com.xzty.cq.tover.businessmanagement.projectmanagement.tool.activity.pickdepartment.affirm.ToolPDApplyOrderAffirmActivity;
import com.xzty.cq.tover.businessmanagement.projectmanagement.tool.activity.pickdepartment.back.ToolPDBackAuditListActivity;
import com.xzty.cq.tover.businessmanagement.projectmanagement.tool.activity.pickdepartment.diliver.ToolPDDiliverActivity;
import com.xzty.cq.tover.businessmanagement.projectmanagement.tool.activity.pickdepartment.distribute.ToolPDDistributeActivity;
import com.xzty.cq.tover.businessmanagement.projectmanagement.tool.activity.pickdepartment.distribute.ToolPPDistributeOrderActivity;
import com.xzty.cq.tover.businessmanagement.projectmanagement.tool.activity.projectmanagedepartment.affirm.ToolPMApplyAffirmActivity;
import com.xzty.cq.tover.businessmanagement.projectmanagement.tool.activity.projectmanagedepartment.apply.ToolApplyListActivity;
import com.xzty.cq.tover.businessmanagement.projectmanagement.tool.activity.projectmanagedepartment.back.ToolPMBackAuditActivity;
import com.xzty.cq.tover.businessmanagement.projectmanagement.tool.activity.projectmanagedepartment.diliver.ToolPMDiliverActivity;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * author wl
 * Created 2018/08/20
 * explain 机具管理Fragment
 */

public class Tool_Fragment extends BaseFragment{

    @BindView(R.id.tv_toolbarTitle)
    TextView tv_toolbarTitle;

    @Override
    public int getContentLayout() {
        return R.layout.activity_tool_function;
    }

    @Override
    protected void initWidget(View root) {
        super.initWidget(root);
        tv_toolbarTitle.setText("机具管理");
    }

    @OnClick({R.id.tmmd_ll_apply, R.id.tmmd_ll_apply_confirm, R.id.tmmd_ll_send, R.id.tmmd_ll_back_confirm, R.id.tmbd_ll_apply_confirm, R.id.tmbd_ll_send, R.id.tmbd_ll_buy_assign, R.id.tmbd_ll_buy_assign_list, R.id.tmbd_ll_back_confirm, R.id.tmo_ll_send_list, R.id.tmo_ll_get, R.id.tmo_ll_get_list, R.id.tmo_ll_back, R.id.tmo_ll_back_list})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tmmd_ll_apply:
                //项管部--申请
                startActivity(new Intent(this.getActivity(), ToolApplyListActivity.class));
                break;

            case R.id.tmmd_ll_apply_confirm:
                //项管部--申请单确认
                startActivity(new Intent(this.getActivity(), ToolPMApplyAffirmActivity.class));
                break;

            case R.id.tmmd_ll_send:
                //项管部--发货
                startActivity(new Intent(this.getActivity(), ToolPMDiliverActivity.class));
                break;
            case R.id.tmmd_ll_back_confirm:
                //项管部--返库审核
                startActivity(new Intent(this.getActivity(), ToolPMBackAuditActivity.class));
                break;
            case R.id.tmbd_ll_apply_confirm:
                //采购部--申请单确认
                startActivity(new Intent(this.getActivity(), ToolPDApplyOrderAffirmActivity.class));
                break;
            case R.id.tmbd_ll_send:
                //采购部--发货
                startActivity(new Intent(this.getActivity(),ToolPDDiliverActivity.class));
                break;
            case R.id.tmbd_ll_buy_assign:
                //采购部--采购分配
                startActivity(new Intent(this.getActivity(),ToolPDDistributeActivity.class));
                break;
            case R.id.tmbd_ll_buy_assign_list:
                //采购部--采购分配单列表
                startActivity(new Intent(this.getActivity(),ToolPPDistributeOrderActivity.class));
                break;
            case R.id.tmbd_ll_back_confirm:
                //采购部--返库审核
                startActivity(new Intent(this.getActivity(),ToolPDBackAuditListActivity.class));
                break;
            case R.id.tmo_ll_send_list:
                //其他--发货单
                startActivity(new Intent(this.getActivity(), OtherDiliverOrderActivity.class));
                break;
            case R.id.tmo_ll_get:
                //其他--收货
                startActivity(new Intent(this.getActivity(),OtherReceiveActivity.class));
                break;
            case R.id.tmo_ll_get_list:
                //其他-收货单列表
                startActivity(new Intent(this.getActivity(),OtherReceiveListActivity.class));
                break;
            case R.id.tmo_ll_back:
                //其他--返库
                startActivity(new Intent(this.getActivity(),ToolOtherBackApplyActivity.class));
                break;
            case R.id.tmo_ll_back_list:
                //其他--返库单
                startActivity(new Intent(this.getActivity(), ToolOtherBackListActivity.class));
                break;
        }
    }
}
