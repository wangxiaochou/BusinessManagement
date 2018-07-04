package com.xzty.cq.tover.businessmanagement.projectmanagement.tool.activity.projectmanagedepartment.apply;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.SparseArray;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.xzty.cq.tover.businessmanagement.R;
import com.xzty.cq.tover.businessmanagement.common.factory.Account;
import com.xzty.cq.tover.businessmanagement.common.factory.ActivityPresenter;
import com.xzty.cq.tover.businessmanagement.projectmanagement.tool.adapter.ToolApplyWriteInfoAdapter;
import com.xzty.cq.tover.businessmanagement.projectmanagement.tool.model.projectmanage.ReqNewCreateTool;
import com.xzty.cq.tover.businessmanagement.projectmanagement.tool.model.projectmanage.ReqToolApply;
import com.xzty.cq.tover.businessmanagement.projectmanagement.tool.model.projectmanage.RspToolApplyList;
import com.xzty.cq.tover.businessmanagement.projectmanagement.tool.presenter.projectmanage.apply.ToolApplayDetailsContract;
import com.xzty.cq.tover.businessmanagement.projectmanagement.tool.presenter.projectmanage.apply.ToolApplyDetailsPresenter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class ToolApplyDetailsActivity extends ActivityPresenter<ToolApplayDetailsContract.Presenter> implements ToolApplayDetailsContract.View, BaseQuickAdapter.OnItemChildClickListener, ToolApplyWriteInfoAdapter.MyTextWatcher {

    @BindView(R.id.recycle_tool)
    RecyclerView recycle_tool;

    @BindView(R.id.et_note)
    EditText et_note;

    @BindView(R.id.tv_toolbarTitle)
    TextView textView;

    private List<RspToolApplyList> mList = new ArrayList<>();
    private List<RspToolApplyList> newList;
    private List<RspToolApplyList> toolList;
    private ToolApplyWriteInfoAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManage;


    //记录每个机具item的最大数量
    private SparseArray<Double> countList = new SparseArray<Double>();

    @Override
    protected int getContentLayoutId() {
        return R.layout.activity_tool_apply_details;
    }

    @Override
    protected void initWidget() {
        super.initWidget();
        textView.setText("机具申请信息填写");
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
    }

    @Override
    protected void initData() {
        super.initData();
        newList = new Gson().fromJson(getIntent().getStringExtra("newToolList"), new TypeToken<List<RspToolApplyList>>() {
        }.getType());
        toolList = new Gson().fromJson(getIntent().getStringExtra("toolList"), new TypeToken<List<RspToolApplyList>>() {
        }.getType());

        if (newList != null) {
            for (RspToolApplyList tools : newList) {
                mList.add(tools);
            }
        }

        if (toolList != null) {
            for (RspToolApplyList tools : toolList) {
                mList.add(tools);
            }
        }
        setAdapter();
    }

    private void setAdapter() {
        mLayoutManage = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mAdapter = new ToolApplyWriteInfoAdapter(R.layout.tool_apply_writeinfo_recycle_item, mList, this);
        recycle_tool.setLayoutManager(mLayoutManage);
        mAdapter.setOnItemChildClickListener(this);
        for (int i = 0; i < mList.size(); i++) {
            countList.put(i, mList.get(i).getToolCount());
        }
        recycle_tool.setAdapter(mAdapter);

    }

    @OnClick(R.id.btn_commit)
    void Onclick() {
        Account.load(this);
        String note = et_note.getText().toString().trim();
        String projectId = Account.getProjectId();
        String employId = Account.getemployId();
        ReqToolApply reqTool = new ReqToolApply();
        reqTool.setToolApplyNote(note);
        reqTool.setProjectId(Integer.parseInt(projectId));
        List<ReqNewCreateTool> newToolList = new Gson().fromJson(getIntent().getStringExtra("newToolList"), new TypeToken<List<ReqNewCreateTool>>() {
        }.getType());

        List<RspToolApplyList> tool = new Gson().fromJson(getIntent().getStringExtra("toolList"), new TypeToken<List<RspToolApplyList>>() {
        }.getType());
        reqTool.setNewToolList(newToolList);
        reqTool.setToolList(tool);
        reqTool.setToolApplyUserId(employId);
        mPresenter.submit(reqTool);
    }

    @Override
    public void showError(String str) {
        Toast.makeText(this, str, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showLoading() {

    }

    @Override
    protected ToolApplayDetailsContract.Presenter initPresenter() {
        return new ToolApplyDetailsPresenter(this);
    }

    @Override
    public void submitSuccess() {
        Toast.makeText(this, "提交成功", Toast.LENGTH_SHORT).show();
        finish();
        startActivity(new Intent(this, ToolApplyListActivity.class));
    }

    @Override
    public void calculateSuccess(List<RspToolApplyList> lists) {
        mList = lists;
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
        switch (view.getId()) {
            case R.id.iv_add:
                mPresenter.add(mList, position, countList);
                break;
            case R.id.iv_minus:
                mPresenter.minus(mList, position);
                break;
        }
    }

    /**
     * 对edittext的外部监听
     */
    @Override
    public void after(RspToolApplyList item, EditText et_number, int position) {
        String num = et_number.getText().toString().trim();
        if (!TextUtils.isEmpty(num)) {
            if (countList.get(position) < Double.parseDouble(num)) {
                Toast.makeText(ToolApplyDetailsActivity.this, "库存不够了", Toast.LENGTH_SHORT).show();
                et_number.setText("");
            }else{
                item.setToolCount(Double.parseDouble(num));
            }

        }
    }
}
