package com.xzty.cq.tover.businessmanagement.new_bottomnavigation.part_fragment;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.xzty.cq.tover.businessmanagement.R;
import com.xzty.cq.tover.businessmanagement.common.app.BaseFragment;
import com.xzty.cq.tover.businessmanagement.common.factory.Account;
import com.xzty.cq.tover.businessmanagement.projectmanagement.part.activity.apply.PartApplyCreateActivity;
import com.xzty.cq.tover.businessmanagement.projectmanagement.part.activity.back.PartBackChooseActivity;
import com.xzty.cq.tover.businessmanagement.projectmanagement.part.activity.collect.PartCollectActivity;
import com.xzty.cq.tover.businessmanagement.projectmanagement.part.activity.deliver.PartDeliverOderActivity;
import com.xzty.cq.tover.businessmanagement.projectmanagement.part.activity.income.PartReceiveOrderActivity;
import com.xzty.cq.tover.businessmanagement.projectmanagement.part.activity.pick.PartPickOrderActivity;
import com.xzty.cq.tover.businessmanagement.projectmanagement.part.activity.promote.PartPromoteNumListActivity;
import com.xzty.cq.tover.businessmanagement.projectmanagement.part.activity.use.PartUseActivity;

import java.util.Set;

import butterknife.BindView;

/**
 * author wl
 * Created 2018/08/20
 * explain 构件管理Fragment
 */

public class Part_Fragment extends BaseFragment implements View.OnClickListener{

    @BindView(R.id.tv_toolbarTitle)
    TextView textView;

    //申请
    @BindView(R.id.btn_apply)
    ImageView btn_apply;
    //汇总
    @BindView(R.id.btn_apply_total)
    ImageView btn_apply_total;
    //采购
    @BindView(R.id.btn_purch)
    ImageView btn_purch;
    //发货
    @BindView(R.id.btn_deliver)
    ImageView btn_deliver;
    //收货
    @BindView(R.id.btn_reach)
    ImageView btn_reach;
    //领用
    @BindView(R.id.btn_receive)
    ImageView btn_receive;
    //退回
    @BindView(R.id.btn_back)
    ImageView btn_back;

    @BindView(R.id.ll_promote_num)
    LinearLayout ll_promote_num;

    private Set<String> rules;

    @Override
    public int getContentLayout() {
        return R.layout.new_part_fragment;
    }

    @Override
    protected void initWidget(View root) {
        super.initWidget(root);
        textView.setText("构件管理");
        btn_apply.setOnClickListener(this);
        btn_apply_total.setOnClickListener(this);
        btn_purch.setOnClickListener(this);
        btn_deliver.setOnClickListener(this);
        btn_reach.setOnClickListener(this);
        btn_receive.setOnClickListener(this);
        btn_back.setOnClickListener(this);
        ll_promote_num.setOnClickListener(this);
    }

    @Override
    protected void initData() {
        super.initData();
        Account.load(this.getActivity());
        rules = Account.getRule();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_apply:
                //申请
                Intent intent = new Intent(this.getActivity(), PartApplyCreateActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_apply_total:
                //汇总
                Intent intent2 = new Intent(this.getActivity(), PartCollectActivity.class);
                startActivity(intent2);
                break;
            case R.id.btn_purch:
                //采购
                Intent intent3 = new Intent(this.getActivity(), PartPickOrderActivity.class);
                startActivity(intent3);
                break;
            case R.id.btn_deliver:
                //发货
                Intent intent4 = new Intent(this.getActivity(), PartDeliverOderActivity.class);
                startActivity(intent4);
                break;
            case R.id.btn_reach:
                //收货
                Intent intent5 = new Intent(this.getActivity(), PartReceiveOrderActivity.class);
                startActivity(intent5);
                break;
            case R.id.btn_receive:
                //领用
                Intent intent6 = new Intent(this.getActivity(), PartUseActivity.class);
                startActivity(intent6);
                break;
            case R.id.btn_back:
                //退回
                Intent intent7 = new Intent(this.getActivity(), PartBackChooseActivity.class);
                startActivity(intent7);
                break;
            case R.id.ll_promote_num:
                //提量
                Intent intent8 = new Intent(this.getActivity(), PartPromoteNumListActivity.class);
                startActivity(intent8);
                break;
        }
    }
}
