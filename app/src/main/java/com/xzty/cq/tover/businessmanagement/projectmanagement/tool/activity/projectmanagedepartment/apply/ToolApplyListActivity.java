package com.xzty.cq.tover.businessmanagement.projectmanagement.tool.activity.projectmanagedepartment.apply;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.gson.Gson;
import com.xzty.cq.tover.businessmanagement.R;
import com.xzty.cq.tover.businessmanagement.common.factory.ActivityPresenter;
import com.xzty.cq.tover.businessmanagement.projectmanagement.tool.adapter.ToolApplyInfoAdapter;
import com.xzty.cq.tover.businessmanagement.projectmanagement.tool.model.projectmanage.RspToolApplyList;
import com.xzty.cq.tover.businessmanagement.projectmanagement.tool.presenter.projectmanage.apply.ToolApplayListContract;
import com.xzty.cq.tover.businessmanagement.projectmanagement.tool.presenter.projectmanage.apply.ToolApplyListPresenter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * author zzl
 * Created 2018/5/19
 * explain 申请列表
 */

public class ToolApplyListActivity extends ActivityPresenter<ToolApplayListContract.Presenter>
        implements ToolApplayListContract.View, BaseQuickAdapter.OnItemClickListener {
    @BindView(R.id.tv_toolbarTitle)
    TextView textView;

    @BindView(R.id.recyle_list)
    RecyclerView recyle_list;

    @BindView(R.id.et_searchecontent)
    EditText et_searchecontent;

    @BindView(R.id.btn_applycreate_search)
    Button btn_applycreate_search;

    @BindView(R.id.cb_selectAll)
    CheckBox cb_selectAll;

    @BindView(R.id.btn_confirm)
    Button btn_confirm;

    private ToolApplyInfoAdapter mAdapter;

    private List<RspToolApplyList> mList;

    private RecyclerView.LayoutManager mLayoutManage;

    //用于记录新创建的tool位置
    private List<Integer> newListPosition = new ArrayList<>();

    private List<RspToolApplyList> newCreateToolList = new ArrayList<>();

    //用于记录 原始数据
    private List<RspToolApplyList> iniList;

    @Override
    protected int getContentLayoutId() {
        return R.layout.activity_apply_list;
    }

    @Override
    protected void initWidget() {
        super.initWidget();
        textView.setText("机具申请");
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
    }

    @Override
    protected void initData() {
        super.initData();
        btn_applycreate_search.performClick();
    }

    private void setAdapter() {
        mLayoutManage = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyle_list.setLayoutManager(mLayoutManage);
        mAdapter = new ToolApplyInfoAdapter(R.layout.tool_projectmanage_apply_recycle_item, mList);
        recyle_list.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(this);
    }

    private void resetAdapter() {
        recyle_list.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();
    }

    //点击事件
    @OnClick({R.id.btn_applycreate_create, R.id.btn_applycreate_search, R.id.btn_confirm, R.id.cb_selectAll})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_applycreate_search:
                //搜索
                String content = et_searchecontent.getText().toString().trim();
                newListPosition.clear();
                newCreateToolList.clear();
                mPresenter.search(content);
                break;

            case R.id.btn_applycreate_create:
                //新建
                final View dialogView = LayoutInflater.from(this).inflate(R.layout.tool_dialog_md_apply_new, null);
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setView(dialogView);
                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        EditText etName = dialogView.findViewById(R.id.dtmdan_et_name);
                        EditText etCount = dialogView.findViewById(R.id.dtmdan_et_count);
                        String name = etName.getText().toString().trim();
                        if (name.equals("") || etCount.getText().toString().trim().equals("")) {
                            Toast.makeText(ToolApplyListActivity.this, "机具名称和数量不能为空", Toast.LENGTH_LONG).show();
                            return;
                        }
                        EditText etModel = dialogView.findViewById(R.id.dtmdan_et_model);
                        EditText etPower = dialogView.findViewById(R.id.dtmdan_et_power);
                        EditText etBrand = dialogView.findViewById(R.id.dtmdan_et_brand);
                        RspToolApplyList tool = new RspToolApplyList();
                        tool.setToolName(name);
                        tool.setToolCount(Double.parseDouble(etCount.getText().toString().trim()));
                        tool.setToolModelNumber(etModel.getText().toString().trim());
                        tool.setToolBrand(etBrand.getText().toString().trim());
                        tool.setToolPower(etPower.getText().toString().trim());
                        tool.isChecked = true;
                        newCreateToolList.add(tool);
                        mList.add(tool);
                        //记录添加的位置
                        newListPosition.add(mList.size() - 1);
                        mAdapter.notifyDataSetChanged();
                    }
                });
                builder.setNegativeButton("取消", null);
                builder.show();
                break;
            case R.id.btn_confirm:
                //申请
                btn_confirm.setEnabled(false);
                //第一个参数值传递原始数据
                mPresenter.isGo(mList, newCreateToolList, newListPosition);
                break;
            case R.id.cb_selectAll:
                //全选
                mPresenter.chooseAll(mList, cb_selectAll, newCreateToolList, newListPosition);
                break;
        }
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        mPresenter.itemClick(mList, position, newCreateToolList, newListPosition);
    }

    @Override
    public void showError(String str) {
        btn_confirm.setEnabled(true);
        Toast.makeText(this, str, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void searchSuccess(List<RspToolApplyList> list) {
        mList = list;
        iniList = list;
        setAdapter();
    }

    @Override
    public void itemClickSuccess(List<RspToolApplyList> list, List<RspToolApplyList> newCreateToolList1, List<Integer> newListPosition2,boolean checkAll) {
        mList = list;
        newCreateToolList = newCreateToolList1;
        newListPosition = newListPosition2;
        Log.e("TAG", "list=" + new Gson().toJson(newCreateToolList) + "position" + newListPosition);
        mAdapter.notifyDataSetChanged();
        //是否选中全部
        cb_selectAll.setChecked(checkAll);
    }

    @Override
    public void chooseAllSuccess(List<RspToolApplyList> list, List<RspToolApplyList> newCreateToolList1, List<Integer> newListPosition1) {
        mList = list;
        newCreateToolList = newCreateToolList1;
        newListPosition = newListPosition1;
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void go(String tools, String newTools) {
        btn_confirm.setEnabled(true);
        finish();
        String s = tools;
        String s1 = newTools;
        Intent intent = new Intent(this, ToolApplyDetailsActivity.class);
        intent.putExtra("toolList", s);
        intent.putExtra("newToolList", s1);
        startActivity(intent);
    }

    @Override
    protected ToolApplayListContract.Presenter initPresenter() {
        return new ToolApplyListPresenter(this);
    }
}
