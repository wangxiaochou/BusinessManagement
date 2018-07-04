package com.xzty.cq.tover.businessmanagement.projectmanagement.part.activity.income;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.xzty.cq.tover.businessmanagement.R;
import com.xzty.cq.tover.businessmanagement.common.factory.ActivityPresenter;
import com.xzty.cq.tover.businessmanagement.projectmanagement.part.adapter.PartReceiveChooseAdapter;
import com.xzty.cq.tover.businessmanagement.projectmanagement.part.model.deliver.DeliverDetails;
import com.xzty.cq.tover.businessmanagement.projectmanagement.part.model.deliver.DeliverOrder;
import com.xzty.cq.tover.businessmanagement.projectmanagement.part.presenter.income.ReceiveChooseContract;
import com.xzty.cq.tover.businessmanagement.projectmanagement.part.presenter.income.ReceiveChoosePresenter;

import java.text.DecimalFormat;
import java.util.List;

import butterknife.BindView;

public class PartReceiveChooseActivity extends ActivityPresenter<ReceiveChooseContract.Presenter>
        implements ReceiveChooseContract.View, View.OnClickListener, BaseQuickAdapter.OnItemClickListener {
    @BindView(R.id.tv_toolbarTitle)
    TextView tv_toolbarTitle;

    @BindView(R.id.tv_collectinfo_outno)
    TextView tv_collectinfo_outno;

    @BindView(R.id.cb_collectinfo_checkall)
    CheckBox cb_collectinfo_checkall;

    @BindView(R.id.tv_collectinfo_numinfo)
    TextView tv_collectinfo_numinfo;

    @BindView(R.id.btn_collectinfo_confirm)
    Button btn_collectinfo_confirm;

    @BindView(R.id.recycle_collectinfo)
    RecyclerView recycle_collectinfo;

    private RecyclerView.LayoutManager mLayoutManage;

    private PartReceiveChooseAdapter mAdapter;

    private String deliverString;

    private List<DeliverDetails> mList;

    //选中的构件种类数量
    private int countType = 0;

    //选中的构件数量
    private int countNum = 0;

    private DecimalFormat df = new DecimalFormat("#######.#######");

    @Override
    protected int getContentLayoutId() {
        return R.layout.activity_part_receive_choose;
    }

    @Override
    protected void initWidget() {
        super.initWidget();
        tv_toolbarTitle.setText("选择实际到场件");
    }

    @Override
    protected void initData() {
        super.initData();
        deliverString = getIntent().getStringExtra("outorder");
        DeliverOrder order = JSON.parseObject(deliverString, DeliverOrder.class);
        tv_collectinfo_outno.setText(order.getOutNo());
        mPresenter.getArray(order.getOutId() + "");
        cb_collectinfo_checkall.setOnClickListener(this);
        btn_collectinfo_confirm.setOnClickListener(this);

    }

    @Override
    public void showError(String str) {
        Toast.makeText(this, str, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void success(List<DeliverDetails> list) {
        mList = list;
        setAdapter();
    }

    @Override
    public void chooseAllBack(List<DeliverDetails> list, int countType1, int countNum1) {
        mList = list;
        countType = countType1;
        countNum = countNum1;
        StringBuffer tip = new StringBuffer("选中");
        tip.append(countType).append("种构件,").append(df.format(countNum)).append("件构件");
        tv_collectinfo_numinfo.setText(tip.toString());
        freshAdapter();
    }

    @Override
    public void chooseOneBack(List<DeliverDetails> list, int countType1, int countNum1) {
        mList = list;
        countType = countType1;
        countNum = countNum1;
        StringBuffer tip = new StringBuffer("选中");
        tip.append(countType).append("种构件,").append(df.format(countNum)).append("件构件");
        tv_collectinfo_numinfo.setText(tip.toString());
        freshAdapter();
    }

    private void freshAdapter(){
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void warningBack(final List<DeliverDetails> finalList) {
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setTitle("确认勾选数量");
        dialog.setMessage("此发货单有" + mList.size() + "个构件，但您只勾选了" + finalList.size() + "个构件，请确定");
        dialog.setCancelable(false);
        dialog.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                commit(finalList);
            }
        });
        dialog.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        dialog.show();
    }

    private void commit(List<DeliverDetails> finalList) {
        String s = JSON.toJSONString(finalList);
        Intent intent = new Intent(this,PartReceiveWriteInfoActivity.class);
        intent.putExtra("choose", s);
        intent.putExtra("deliverOrder", deliverString);
        startActivity(intent);
    }

    @Override
    public void ok(List<DeliverDetails> finalList) {
        commit(finalList);
    }

    private void setAdapter() {
        mLayoutManage = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mAdapter = new PartReceiveChooseAdapter(R.layout.part_receive_choose_recycle_item, mList);
        recycle_collectinfo.setLayoutManager(mLayoutManage);
        recycle_collectinfo.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(this);
    }

    @Override
    protected ReceiveChooseContract.Presenter initPresenter() {
        return new ReceiveChoosePresenter(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.cb_collectinfo_checkall:
                //全选
                mPresenter.chooseAll(mList, cb_collectinfo_checkall.isChecked());
                break;

            case R.id.btn_collectinfo_confirm:
                //确定
                mPresenter.confirm(mList,countType);
                break;
        }
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        mPresenter.chooseOne(mList, position, countType, countNum);
    }
}
