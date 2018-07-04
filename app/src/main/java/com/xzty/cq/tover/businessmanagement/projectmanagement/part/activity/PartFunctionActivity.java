package com.xzty.cq.tover.businessmanagement.projectmanagement.part.activity;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.xzty.cq.tover.businessmanagement.R;
import com.xzty.cq.tover.businessmanagement.common.app.BaseActivity;
import com.xzty.cq.tover.businessmanagement.common.factory.Account;
import com.xzty.cq.tover.businessmanagement.projectmanagement.part.activity.apply.PartApplyCreateActivity;
import com.xzty.cq.tover.businessmanagement.projectmanagement.part.activity.back.PartBackChooseActivity;
import com.xzty.cq.tover.businessmanagement.projectmanagement.part.activity.income.PartReceiveOrderActivity;
import com.xzty.cq.tover.businessmanagement.projectmanagement.part.activity.collect.PartCollectActivity;
import com.xzty.cq.tover.businessmanagement.projectmanagement.part.activity.pick.PartPickOrderActivity;
import com.xzty.cq.tover.businessmanagement.projectmanagement.part.activity.deliver.PartDeliverOderActivity;
import com.xzty.cq.tover.businessmanagement.projectmanagement.part.activity.promote.PartPromoteNumListActivity;
import com.xzty.cq.tover.businessmanagement.projectmanagement.part.activity.use.PartUseActivity;

import java.util.Set;

import butterknife.BindView;

public class PartFunctionActivity extends BaseActivity implements View.OnClickListener {
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
    protected int getContentLayoutId() {
        return R.layout.activity_part_function;
    }

    @Override
    protected void initWidget() {
        super.initWidget();
        textView.setText("功能选择");
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
        Account.load(this);
        rules = Account.getRule();
    }

    @Override
    public void onClick(View v) {
        //if (rules.contains(v.getTag())) {
        switch (v.getId()) {
            case R.id.btn_apply:
                //申请
                Intent intent = new Intent(this, PartApplyCreateActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_apply_total:
                //汇总
                Intent intent2 = new Intent(this, PartCollectActivity.class);
                startActivity(intent2);
                break;
            case R.id.btn_purch:
                //采购
                Intent intent3 = new Intent(this, PartPickOrderActivity.class);
                startActivity(intent3);
                break;
            case R.id.btn_deliver:
                //发货
                Intent intent4 = new Intent(this, PartDeliverOderActivity.class);
                startActivity(intent4);
                break;
            case R.id.btn_reach:
                //收货
                Intent intent5 = new Intent(this, PartReceiveOrderActivity.class);
                startActivity(intent5);
                break;
            case R.id.btn_receive:
                //领用
                Intent intent6 = new Intent(this, PartUseActivity.class);
                startActivity(intent6);
                break;
            case R.id.btn_back:
                //退回
                Intent intent7 = new Intent(this, PartBackChooseActivity.class);
                startActivity(intent7);
                break;
            case R.id.ll_promote_num:
                //提量
                Intent intent8 = new Intent(this, PartPromoteNumListActivity.class);
                startActivity(intent8);
                break;
        }
      /*  } else {
            Toast.makeText(PartFunctionActivity.this, "您的权限不足", Toast.LENGTH_SHORT).show();
        }*/
    }
}
