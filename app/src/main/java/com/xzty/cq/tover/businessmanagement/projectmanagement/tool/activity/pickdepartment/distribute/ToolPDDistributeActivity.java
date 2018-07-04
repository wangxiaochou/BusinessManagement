package com.xzty.cq.tover.businessmanagement.projectmanagement.tool.activity.pickdepartment.distribute;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.xzty.cq.tover.businessmanagement.R;
import com.xzty.cq.tover.businessmanagement.common.factory.ActivityPresenter;
import com.xzty.cq.tover.businessmanagement.projectmanagement.tool.adapter.BuyerAdapter;
import com.xzty.cq.tover.businessmanagement.projectmanagement.tool.adapter.ToolPickListAdapter;
import com.xzty.cq.tover.businessmanagement.projectmanagement.tool.model.pickdepartment.RspAllDistribute;
import com.xzty.cq.tover.businessmanagement.projectmanagement.tool.model.pickdepartment.RspDistribute;
import com.xzty.cq.tover.businessmanagement.projectmanagement.tool.model.pickdepartment.RspUser;
import com.xzty.cq.tover.businessmanagement.projectmanagement.tool.presenter.pickdepartment.distribute.PickDistributeContract;
import com.xzty.cq.tover.businessmanagement.projectmanagement.tool.presenter.pickdepartment.distribute.PickDistributePresenter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class ToolPDDistributeActivity extends ActivityPresenter<PickDistributeContract.Presenter> implements PickDistributeContract.View, BaseQuickAdapter.OnItemClickListener {

    @BindView(R.id.tv_toolbarTitle)
    TextView tv_toolbarTitle;

    @BindView(R.id.spinner_distinfofillin_buyname)
    Spinner spinner_distinfofillin_buyname;

    @BindView(R.id.recycle_info)
    RecyclerView recycle_info;

    @BindView(R.id.et_note)
    EditText et_note;

    @BindView(R.id.btn_submit)
    Button btn_submit;

    private RecyclerView.LayoutManager mLayoutManage;

    private ToolPickListAdapter mAdapter;

    private List<RspDistribute> mList;
    private List<RspUser> userList = new ArrayList<>();
    private RspUser chooseEmp = new RspUser("", "请选择");

    @Override
    protected int getContentLayoutId() {
        return R.layout.activity_tool_pddistribute;
    }

    @Override
    protected void initWidget() {
        super.initWidget();
        tv_toolbarTitle.setText("机具采购分配");
    }

    @Override
    protected void initData() {
        super.initData();
        spinner_distinfofillin_buyname.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                chooseEmp = userList.get(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        mPresenter.getData();
    }

    @OnClick(R.id.btn_submit)
    void click() {
        String note = et_note.getText().toString().trim();
        mPresenter.submit(mList, note, chooseEmp);
    }

    @Override
    public void showError(String str) {
        Toast.makeText(this, str, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showLoading() {

    }

    @Override
    protected PickDistributeContract.Presenter initPresenter() {
        return new PickDistributePresenter(this);
    }

    @Override
    public void success(RspAllDistribute list) {
        mList = list.getApplys();
        userList = list.getName();
        setAdapter();
        setBuyerAdapter();
    }

    @Override
    public void itemSuccess(List<RspDistribute> list) {
        mList = list;
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void submitSuccess() {
        Toast.makeText(this, "分配成功", Toast.LENGTH_SHORT).show();
    }

    private void setBuyerAdapter() {
        spinner_distinfofillin_buyname.setAdapter(new BuyerAdapter(userList, this));
    }

    private void setAdapter() {
        mLayoutManage = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mAdapter = new ToolPickListAdapter(R.layout.tool_pick_distribute_recycle_item, mList);
        recycle_info.setLayoutManager(mLayoutManage);
        mAdapter.setOnItemClickListener(this);
        recycle_info.setAdapter(mAdapter);
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        mPresenter.itemClick(mList, position);
    }
}
