package com.xzty.cq.tover.businessmanagement.projectmanagement.part.activity.pick;

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
import com.xzty.cq.tover.businessmanagement.projectmanagement.part.adapter.PartPickOrderAdapter;
import com.xzty.cq.tover.businessmanagement.projectmanagement.part.model.Emp;
import com.xzty.cq.tover.businessmanagement.projectmanagement.part.model.pick.RspPickOrder;
import com.xzty.cq.tover.businessmanagement.projectmanagement.part.presenter.pick.PickOrderContract;
import com.xzty.cq.tover.businessmanagement.projectmanagement.part.presenter.pick.PickOrderPresenter;
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
 * Created 2018/5/1
 * explain 采购
 */

public class PartPickOrderActivity extends ActivityPresenter<PickOrderContract.Presenter> implements PickOrderContract.View, View.OnClickListener, BaseQuickAdapter.OnItemChildClickListener,BaseQuickAdapter.OnItemClickListener {
    @BindView(R.id.tv_toolbarTitle)
    TextView textView;

    @BindView(R.id.llout_distorder_timename)
    LinearLayout llout_distorder_timename;

    @BindView(R.id.tv_distorder_disttime)
    TextView tv_distorder_disttime;

    @BindView(R.id.btn_distorder_search)
    Button btn_distorder_search;

    @BindView(R.id.spinner_distorder_buyer)
    Spinner spinner_distorder_buyer;

    @BindView(R.id.spinner_distorder_state)
    Spinner spinner_distorder_state;

    @BindView(R.id.recycle_pickorder)
    RecyclerView recycle_pickorder;

    private List<Emp> empList = new ArrayList<>();
    private Emp chooseEmp = new Emp("请选择", "");

    private String[] stateArray = {"全部", "未确认", "已确认"};
    private List<String> applyOrderStateList = Arrays.asList(stateArray);
    private String applyOrderState = "";

    private Calendar c = Calendar.getInstance();

    private PartPickOrderAdapter mAdapter;

    //原始数据
    private List<RspPickOrder> mList;

    //检索数据
    private List<RspPickOrder> copymList;

    private RecyclerView.LayoutManager mLayoutMange;

    @Override
    protected int getContentLayoutId() {
        return R.layout.activity_part_dist_order;
    }

    @Override
    protected void initWidget() {
        super.initWidget();
        textView.setText("采购单");
        spinner_distorder_buyer.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                chooseEmp = empList.get(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spinner_distorder_state.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, applyOrderStateList));
        spinner_distorder_state.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                applyOrderState = applyOrderStateList.get(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        btn_distorder_search.setOnClickListener(this);
        llout_distorder_timename.setOnClickListener(this);
        tv_distorder_disttime.setOnClickListener(this);
        mLayoutMange = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
    }

    @Override
    protected void initData() {
        super.initData();
        init();
    }

    @Override
    public void showError(String str) {
        Toast.makeText(this, str, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void backBuyer(List<Emp> temp,int i ) {
        empList = temp;
        spinner_distorder_buyer.setAdapter(new ApplyListPersonSpinnerAdapter(empList,
                this));
        spinner_distorder_buyer.setSelection(i);
    }

    @Override
    public void getOrder(List<RspPickOrder> list) {
        mPresenter.getBuyer();
        mList = list;
        mAdapter = new PartPickOrderAdapter(R.layout.part_pick_recycle_order_item, mList);
        recycle_pickorder.setLayoutManager(mLayoutMange);
        recycle_pickorder.setAdapter(mAdapter);
        mAdapter.setOnItemChildClickListener(this);
        Toast.makeText(this, "搜索成功", Toast.LENGTH_SHORT).show();
        mAdapter.setOnItemClickListener(this);
    }

    @Override
    public void notaSuccess() {
        Toast.makeText(this, "确认成功", Toast.LENGTH_SHORT).show();
        init();
    }

    @Override
    protected PickOrderContract.Presenter initPresenter() {
        return new PickOrderPresenter(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_distorder_search:
                init();
                break;

            case R.id.llout_distorder_timename:
                pickDate(tv_distorder_disttime);
                pickDate(tv_distorder_disttime);
                break;

            case R.id.tv_distorder_disttime:
                tv_distorder_disttime.setText("");
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

    private void init() {
        String time = tv_distorder_disttime.getText().toString().trim();
        String buyerId = "";
        //暂时不加入
     /*   if (rules.contains(spinner_distorder_buyer.getTag())) {
            maps.put("buyerId", (chooseEmp.getEmployId() == null) ? "" : chooseEmp
                    .getEmployId());*/
        buyerId = chooseEmp.getEmployId() == null ? "" : chooseEmp.getEmployId();
        /*} else {
            Account.load(this);
            buyerId = Account.getemployId();
        }*/
        mPresenter.getPickOrder(time, applyOrderState, buyerId);
    }

    @Override
    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
        if (mList.get(position).getIsComfirm() == 0) {
            //确认
            mPresenter.notaPick(mList.get(position).getDistId());
        } else {
            //采购
            Intent intent = new Intent(this, ChoosePickActivity.class);
            String s = JSON.toJSONString(mList.get(position));
            intent.putExtra("distOrder", s);
            startActivity(intent);
        }
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        Intent intent = new Intent(this,PartPickDetailsActivity.class);
        intent.putExtra("distOrder",JSON.toJSONString(mList.get(position)));
        intent.putExtra("status",mList.get(position).getIsComfirm());
        startActivity(intent);
    }
}
