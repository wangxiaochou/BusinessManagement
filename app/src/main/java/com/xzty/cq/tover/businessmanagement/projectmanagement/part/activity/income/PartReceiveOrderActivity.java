package com.xzty.cq.tover.businessmanagement.projectmanagement.part.activity.income;

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
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.xzty.cq.tover.businessmanagement.R;
import com.xzty.cq.tover.businessmanagement.common.factory.ActivityPresenter;
import com.xzty.cq.tover.businessmanagement.projectmanagement.part.adapter.ApplyListPersonSpinnerAdapter;
import com.xzty.cq.tover.businessmanagement.projectmanagement.part.adapter.ReceiveOrderAdapter;
import com.xzty.cq.tover.businessmanagement.projectmanagement.part.model.Emp;
import com.xzty.cq.tover.businessmanagement.projectmanagement.part.model.deliver.DeliverOrder;
import com.xzty.cq.tover.businessmanagement.projectmanagement.part.presenter.income.ReceiveOrderContract;
import com.xzty.cq.tover.businessmanagement.projectmanagement.part.presenter.income.ReceiveOrderPresenter;
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
 * explain 收货
 */

public class PartReceiveOrderActivity extends ActivityPresenter<ReceiveOrderContract.Presenter>
        implements ReceiveOrderContract.View,
        View.OnClickListener,
        BaseQuickAdapter.OnItemChildClickListener ,BaseQuickAdapter.OnItemClickListener{
    @BindView(R.id.tv_toolbarTitle)
    TextView tv_toolbarTitle;

    @BindView(R.id.spinner_collectorder_buyer)
    Spinner spinner_collectorder_buyer;

    @BindView(R.id.spinner_collectorder_state)
    Spinner spinner_collectorder_state;

    @BindView(R.id.btn_collectorder_search)
    Button btn_collectorder_search;

    @BindView(R.id.recycle_collectorder_list)
    RecyclerView recycle_collectorder_list;

    @BindView(R.id.tv_collectorder_time)
    TextView tv_collectorder_time;

    @BindView(R.id.llout_collectorder_timeicon)
    LinearLayout llout_collectorder_timeicon;

    private List<Emp> emps = new ArrayList<>();
    private Emp chooseEmp = new Emp("请选择", "");
    private String[] stateArray = {"全部", "已发货", "部分收货", "已收货"};
    private List<String> applyOrderStateList = Arrays.asList(stateArray);
    private String applyOrderState = "";
    private Calendar c = Calendar.getInstance();
    private RecyclerView.LayoutManager mLayoutManage;

    private List<DeliverOrder> mList;

    private ReceiveOrderAdapter mAdapter;


    @Override
    protected int getContentLayoutId() {
        return R.layout.activity_part_collect_order;
    }

    @Override
    protected void initData() {
        super.initData();
        tv_toolbarTitle.setText("收货单");
        spinner_collectorder_buyer = (Spinner) findViewById(R.id.spinner_collectorder_buyer);
        spinner_collectorder_buyer.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                chooseEmp = emps.get(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spinner_collectorder_state = (Spinner) findViewById(R.id.spinner_collectorder_state);
        spinner_collectorder_state.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, applyOrderStateList));
        spinner_collectorder_state.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                applyOrderState = applyOrderStateList.get(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        btn_collectorder_search.setOnClickListener(this);
        llout_collectorder_timeicon.setOnClickListener(this);
        btn_collectorder_search.performClick();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_collectorder_search:
                String time = tv_collectorder_time.getText().toString().trim();
                mPresenter.search(chooseEmp.getApplyUserId() == null ? "" : chooseEmp.getApplyUserId(), time, applyOrderState);
                break;
            case R.id.llout_collectorder_timeicon:
                pickDate(tv_collectorder_time);
                pickDate(tv_collectorder_time);
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
    public void showError(String str) {
        Toast.makeText(this, str, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void seacrchCallBack(List<DeliverOrder> list, List<Emp> empList) {
        mList = list;
        emps = empList;
        spinner_collectorder_buyer.setAdapter(new ApplyListPersonSpinnerAdapter(emps,
                this));
        setAdapter();
    }

    private void setAdapter() {
        mLayoutManage = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mAdapter = new ReceiveOrderAdapter(R.layout.part_receive_order_recycle_item, mList);
        recycle_collectorder_list.setLayoutManager(mLayoutManage);
        recycle_collectorder_list.setAdapter(mAdapter);
        mAdapter.setOnItemChildClickListener(this);
        mAdapter.setOnItemClickListener(this);
    }

    @Override
    protected ReceiveOrderContract.Presenter initPresenter() {
        return new ReceiveOrderPresenter(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
        Intent intent = new Intent(this,PartReceiveChooseActivity.class);
        DeliverOrder outder = mList.get(position);
        String s = JSON.toJSONString(outder);
        intent.putExtra("outorder", s);
        startActivity(intent);
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        Intent intent = new Intent(this,PartReceiveDetailsActivity.class);
        intent.putExtra("outOrder", JSON.toJSONString(mList.get(position)));
        startActivity(intent);
    }
}
