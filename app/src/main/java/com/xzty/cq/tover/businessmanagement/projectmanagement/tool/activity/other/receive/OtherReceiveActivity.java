package com.xzty.cq.tover.businessmanagement.projectmanagement.tool.activity.other.receive;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.xzty.cq.tover.businessmanagement.R;
import com.xzty.cq.tover.businessmanagement.common.factory.ActivityPresenter;
import com.xzty.cq.tover.businessmanagement.projectmanagement.tool.adapter.OtherReceiveAdapter;
import com.xzty.cq.tover.businessmanagement.projectmanagement.tool.model.other.RspReceive;
import com.xzty.cq.tover.businessmanagement.projectmanagement.tool.presenter.other.receive.OtherReceiveContract;
import com.xzty.cq.tover.businessmanagement.projectmanagement.tool.presenter.other.receive.OtherReceivePresenter;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class OtherReceiveActivity extends ActivityPresenter<OtherReceiveContract.Presenter> implements OtherReceiveContract.View, BaseQuickAdapter.OnItemClickListener {
    @BindView(R.id.tv_toolbarTitle)
    TextView tv_toolbarTitle;

    @BindView(R.id.recycle_receive)
    RecyclerView recycle_receive;

    @BindView(R.id.et_note)
    EditText et_note;

    @BindView(R.id.bt_get)
    Button bt_get;

    private RecyclerView.LayoutManager mLayoutManage;

    private OtherReceiveAdapter mAdapter;

    private List<RspReceive> mList;

    @Override
    protected int getContentLayoutId() {
        return R.layout.activity_other_receive;
    }

    @Override
    protected void initWidget() {
        super.initWidget();
        tv_toolbarTitle.setText("机具收货");
    }

    @Override
    protected void initData() {
        super.initData();
        mPresenter.getData();
    }

    private void setAdapter() {
        mLayoutManage = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mAdapter = new OtherReceiveAdapter(R.layout.tool_other_receive_recycle_item, mList);
        recycle_receive.setLayoutManager(mLayoutManage);
        mAdapter.setOnItemClickListener(this);
        recycle_receive.setAdapter(mAdapter);
    }

    @Override
    public void showError(String str) {
        Toast.makeText(this, str, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void success(List<RspReceive> list) {
        mList = list;
        setAdapter();
    }

    @Override
    public void clickSuccess(List<RspReceive> list) {
        mList = list;
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void commitSuccess() {
        Toast.makeText(this,"收货成功",Toast.LENGTH_SHORT).show();
        finish();
    }

    @OnClick(R.id.bt_get)
    void click(){
    String note = et_note.getText().toString().trim();
    mPresenter.commit(mList,note);
    }

    @Override
    protected OtherReceiveContract.Presenter initPresenter() {
        return new OtherReceivePresenter(this);
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        mPresenter.itemClick(mList, position);
    }
}
