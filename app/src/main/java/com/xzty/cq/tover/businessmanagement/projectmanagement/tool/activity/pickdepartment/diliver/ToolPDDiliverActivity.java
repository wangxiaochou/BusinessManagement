package com.xzty.cq.tover.businessmanagement.projectmanagement.tool.activity.pickdepartment.diliver;

import android.app.DatePickerDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.xzty.cq.tover.businessmanagement.R;
import com.xzty.cq.tover.businessmanagement.common.factory.ActivityPresenter;
import com.xzty.cq.tover.businessmanagement.projectmanagement.tool.adapter.ToolPDDiliverAdapter;
import com.xzty.cq.tover.businessmanagement.projectmanagement.tool.model.pickdepartment.RspDiliverOrder;
import com.xzty.cq.tover.businessmanagement.projectmanagement.tool.presenter.pickdepartment.diliver.DiliverOrderContract;
import com.xzty.cq.tover.businessmanagement.projectmanagement.tool.presenter.pickdepartment.diliver.DiliverOrderPresenter;

import java.util.Calendar;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class ToolPDDiliverActivity extends ActivityPresenter<DiliverOrderContract.Presenter> implements DiliverOrderContract.View, BaseQuickAdapter.OnItemClickListener {
    @BindView(R.id.tv_toolbarTitle)
    TextView textView;

    @BindView(R.id.recycle_info)
    RecyclerView recycle_info;

    @BindView(R.id.tmdo_bt_expectTime)
    Button tmdoBtExpectTime;

    @BindView(R.id.tmdo_et_firm)
    EditText tmdo_et_firm;

    @BindView(R.id.tmdo_et_contractNumber)
    EditText tmdo_et_contractNumber;

    @BindView(R.id.tmdo_et_note)
    EditText tmdo_et_note;

    private RecyclerView.LayoutManager mLayoutManage;

    private List<RspDiliverOrder> mList;

    private ToolPDDiliverAdapter mAdapter;

    private String expectTime;

    private Calendar c = Calendar.getInstance();

    @Override
    protected int getContentLayoutId() {
        return R.layout.activity_tool_pdsend_out;
    }

    @Override
    protected void initWidget() {
        super.initWidget();
        textView.setText("机具发货");
    }

    @Override
    protected void initData() {
        super.initData();
        mPresenter.getDat();
    }

    @OnClick(R.id.tmdo_bt_expectTime)
    void click() {
        setAllTime();
    }

    private void setAllTime() {
        new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                StringBuffer sb = new StringBuffer();
                sb.append(year).append("-");
                sb.append((monthOfYear + 1) < 10 ? "0" + (monthOfYear + 1) : (monthOfYear + 1)).append("-");
                sb.append((dayOfMonth < 10) ? "0" + dayOfMonth : dayOfMonth);
                tmdoBtExpectTime.setText(sb.toString());
                expectTime = sb.toString();
            }
        }, c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH)).show();
    }

    @OnClick(R.id.tmdo_bt_send)
    void affirm() {
        String firm = tmdo_et_firm.getText().toString().trim();
        String contractNumber = tmdo_et_contractNumber.getText().toString().trim();
        String note = tmdo_et_note.getText().toString().trim();
        mPresenter.affirm(mList, firm, contractNumber, expectTime, note);
    }

    @Override
    public void showError(String str) {
        Toast.makeText(this, str, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void success(List<RspDiliverOrder> list) {
        mList = list;
        setAdapter();
    }

    @Override
    public void clickSuccess(List<RspDiliverOrder> list) {
        mList = list;
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void commitSuccess() {
        finish();
        Toast.makeText(this, "提交成功", Toast.LENGTH_SHORT).show();
    }

    private void setAdapter() {
        mLayoutManage = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recycle_info.setLayoutManager(mLayoutManage);
        mAdapter = new ToolPDDiliverAdapter(R.layout.tool_pd_diliver_recycle_item, mList);
        mAdapter.setOnItemClickListener(this);
        recycle_info.setAdapter(mAdapter);
    }

    @Override
    protected DiliverOrderContract.Presenter initPresenter() {
        return new DiliverOrderPresenter(this);
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        mPresenter.itemClick(mList, position);
    }
}
