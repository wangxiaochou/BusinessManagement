package com.xzty.cq.tover.businessmanagement.projectmanagement.part.activity.deliver;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.gson.Gson;
import com.xzty.cq.tover.businessmanagement.R;
import com.xzty.cq.tover.businessmanagement.common.factory.ActivityPresenter;
import com.xzty.cq.tover.businessmanagement.projectmanagement.part.adapter.ApplyListPersonSpinnerAdapter;
import com.xzty.cq.tover.businessmanagement.projectmanagement.part.adapter.PartDeliverOderAdapter;
import com.xzty.cq.tover.businessmanagement.projectmanagement.part.model.Emp;
import com.xzty.cq.tover.businessmanagement.projectmanagement.part.model.deliver.DeliverOrder;
import com.xzty.cq.tover.businessmanagement.projectmanagement.part.model.deliver.RspPurchase;
import com.xzty.cq.tover.businessmanagement.projectmanagement.part.presenter.deliver.DeliverOrderContract;
import com.xzty.cq.tover.businessmanagement.projectmanagement.part.presenter.deliver.DeliverOrderPresenter;
import com.xzty.cq.tover.businessmanagement.projectmanagement.utils.DateUtil;
import com.xzty.cq.tover.businessmanagement.projectmanagement.view.MyDatePickerDialog;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import butterknife.BindView;

/**
 * author zzl
 * Created 2018/5/11
 * explain 发货
 */

public class PartDeliverOderActivity extends ActivityPresenter<DeliverOrderContract.Presenter> implements DeliverOrderContract.View, View.OnClickListener, BaseQuickAdapter.OnItemChildClickListener {

    @BindView(R.id.tv_toolbarTitle)
    TextView tv_toolbarTitle;

    @BindView(R.id.et_buyorder_contract)
    EditText et_buyorder_contract;

    @BindView(R.id.et_buyorder_firm)
    EditText et_buyorder_firm;

    @BindView(R.id.spinner_buyorder_buyer)
    Spinner spinner_buyorder_buyer;

    @BindView(R.id.spinner_buyorder_state)
    Spinner spinner_buyorder_state;

    @BindView(R.id.llout_buyorder_timeicon)
    LinearLayout llout_buyorder_timeicon;

    @BindView(R.id.tv_buyorder_time)
    TextView tv_buyorder_time;

    @BindView(R.id.btn_buyorder_search)
    Button btn_buyorder_search;

    @BindView(R.id.recycle_buyorder_list)
    RecyclerView recycle_buyorder_list;

    private List<Emp> emps = new ArrayList<>();
    private Emp chooseEmp = new Emp("请选择", "");
    private String[] stateArray = {"全部", "未发货", "已发货", "已收货"};
    private List<String> applyOrderStateList = Arrays.asList(stateArray);
    private String applyOrderState = "";
    private Calendar c = Calendar.getInstance();
    private PartDeliverOderAdapter mAdapter;
    private List<DeliverOrder> mList;
    private RecyclerView.LayoutManager mLayoutManage;

    @Override
    protected int getContentLayoutId() {
        return R.layout.activity_part_buy_order;
    }

    @Override
    protected void initWidget() {
        super.initWidget();
        tv_toolbarTitle.setText("发货单");

        llout_buyorder_timeicon.setOnClickListener(this);
        btn_buyorder_search.setOnClickListener(this);
        tv_buyorder_time.setOnClickListener(this);
        mLayoutManage = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);

        spinner_buyorder_buyer.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                chooseEmp = emps.get(position);
                btn_buyorder_search.performClick();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spinner_buyorder_state.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, applyOrderStateList));
        spinner_buyorder_state.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                applyOrderState = applyOrderStateList.get(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    @Override
    protected void initData() {
        super.initData();
        mPresenter.getBuyer();
    }

    @Override
    public void showError(String str) {
        Toast.makeText(this, str, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void backBuyer(List<Emp> temp, int i) {
        emps = temp;
        spinner_buyorder_buyer.setAdapter(new ApplyListPersonSpinnerAdapter(emps,
                this));
        spinner_buyorder_buyer.setSelection(i);
        btn_buyorder_search.performClick();
    }

    @Override
    public void backDeliver(List<RspPurchase> list) {
        mList = JSON.parseArray(new Gson().toJson(list).toString(), DeliverOrder.class);
        setadapter();
    }

    private void setadapter() {
        recycle_buyorder_list.setLayoutManager(mLayoutManage);
        mAdapter = new PartDeliverOderAdapter(R.layout.part_deliver_order_recycle_item, mList);
        recycle_buyorder_list.setAdapter(mAdapter);
        mAdapter.setOnItemChildClickListener(this);
    }

    @Override
    protected DeliverOrderContract.Presenter initPresenter() {
        return new DeliverOrderPresenter(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.llout_buyorder_timeicon:
                pickDate(tv_buyorder_time);
                pickDate(tv_buyorder_time);
                break;
            case R.id.btn_buyorder_search:
                String time = tv_buyorder_time.getText().toString();
                String firm = et_buyorder_firm.getText().toString().trim();
                String contractNo = et_buyorder_contract.getText().toString().trim();
                String outUserId = chooseEmp.getEmployId() + "";
                mPresenter.getPurchas(applyOrderState, time, firm, contractNo, outUserId);
                break;
            case R.id.tv_buyorder_time:
                tv_buyorder_time.setText("");
                break;
        }
    }

    private void pickDate(final TextView tv) {
        new MyDatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                String tag = (String) tv.getTag();
                if (tag == null || tag.contains("~")) {
                    tag = "";
                }
                if (tag.equals("")) {
                    StringBuffer sb = new StringBuffer();
                    sb.append(year).append("-");
                    sb.append((monthOfYear + 1) < 10 ? "0" + (monthOfYear + 1) : (monthOfYear + 1)).append("-");
                    sb.append((dayOfMonth < 10) ? "0" + dayOfMonth : dayOfMonth);
                    Log.e("TAG", "onDateSet1: " + sb.toString());
                    tv.setTag(sb.toString());
                } else {
                    StringBuffer s = new StringBuffer();
                    s.append(tag).append("~");
                    s.append(year).append("-");
                    s.append((monthOfYear + 1) < 10 ? "0" + (monthOfYear + 1) : (monthOfYear + 1)).append("-");
                    s.append((dayOfMonth < 10) ? "0" + dayOfMonth : dayOfMonth);
                    Log.e("TAG", "onDateSet2: " + s.toString());
                    String[] timesplit = s.toString().split("~");
                    Date date1 = DateUtil.stringToDate(timesplit[0]);
                    Date date2 = DateUtil.stringToDate(timesplit[1]);
                    if (date1.compareTo(date2) > 0) {
                        Date temp = date1;  //date1大
                        date1 = date2; //date2小
                        date2 = temp;
                    }
                    s.setLength(0);
                    s.append(DateUtil.dateToString(date1)).append("~").append(DateUtil.dateToString(date2));
                    tv.setTag(s.toString());
                    tv.setText(s.toString());
                }
            }
        }, c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH)).show();

    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
        Intent intent = new Intent(this, DeliverWriteInfoActivity.class);
        intent.putExtra("outOrder", JSON.toJSONString(mList.get(position)));
        startActivity(intent);
    }
}
