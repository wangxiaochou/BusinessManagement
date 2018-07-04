package com.xzty.cq.tover.businessmanagement.projectmanagement.tool.activity.other.back;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.xzty.cq.tover.businessmanagement.R;
import com.xzty.cq.tover.businessmanagement.common.factory.ActivityPresenter;
import com.xzty.cq.tover.businessmanagement.projectmanagement.tool.adapter.ToolOtherBackApplyAdapter;
import com.xzty.cq.tover.businessmanagement.projectmanagement.tool.model.other.RspBackApply;
import com.xzty.cq.tover.businessmanagement.projectmanagement.tool.presenter.other.back.OtherBackApplyContract;
import com.xzty.cq.tover.businessmanagement.projectmanagement.tool.presenter.other.back.OtherBackApplyPresenter;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class ToolOtherBackApplyActivity extends ActivityPresenter<OtherBackApplyContract.Presenter> implements OtherBackApplyContract.View ,BaseQuickAdapter.OnItemClickListener{
    @BindView(R.id.tv_toolbarTitle)
    TextView textView;

    @BindView(R.id.et_note)
    EditText et_note;

    @BindView(R.id.recycle_apply)
    RecyclerView recycle_apply;

    private List<RspBackApply> mList;

    private ToolOtherBackApplyAdapter mAdapter;

    @Override
    protected int getContentLayoutId() {
        return R.layout.activity_tool_other_back_apply;
    }

    @Override
    protected void initWidget() {
        super.initWidget();
        textView.setText("机具返库申请");
    }

    @Override
    protected void initData() {
        super.initData();
        mPresenter.getData();
    }

    @OnClick(R.id.bt_back)
    void applyClick(){
        String note = et_note.getText().toString().trim();
        mPresenter.apply(mList,note);
    }

    @Override
    public void showError(String str) {
        Toast.makeText(this, str, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showLoading() {

    }


    @Override
    public void dataSuccess(List<RspBackApply> list) {
        mList = list;
        setAdapter();
    }

    @Override
    public void clickSuccess(List<RspBackApply> list) {
        mList = list;
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void applySuccess() {
        Toast.makeText(this,"申请成功",Toast.LENGTH_SHORT).show();
        finish();
    }

    private void setAdapter() {
        recycle_apply.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        mAdapter = new ToolOtherBackApplyAdapter(R.layout.tool_other_backapply_recycle_item,mList);
        mAdapter.setOnItemClickListener(this);
        recycle_apply.setAdapter(mAdapter);
    }

    @Override
    protected OtherBackApplyContract.Presenter initPresenter() {
        return new OtherBackApplyPresenter(this);
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        mPresenter.itemClick(mList,position);
    }
}
