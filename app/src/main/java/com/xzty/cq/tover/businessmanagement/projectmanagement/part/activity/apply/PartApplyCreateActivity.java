package com.xzty.cq.tover.businessmanagement.projectmanagement.part.activity.apply;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
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

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.gson.Gson;
import com.xzty.cq.tover.businessmanagement.R;
import com.xzty.cq.tover.businessmanagement.common.factory.Account;
import com.xzty.cq.tover.businessmanagement.common.factory.ActivityPresenter;
import com.xzty.cq.tover.businessmanagement.projectmanagement.part.adapter.ApplyListPersonSpinnerAdapter;
import com.xzty.cq.tover.businessmanagement.projectmanagement.part.adapter.PartApplyCreateAdapter;
import com.xzty.cq.tover.businessmanagement.projectmanagement.part.model.Emp;
import com.xzty.cq.tover.businessmanagement.projectmanagement.part.model.apply.RspApplyList;
import com.xzty.cq.tover.businessmanagement.projectmanagement.part.presenter.apply.SearchContract;
import com.xzty.cq.tover.businessmanagement.projectmanagement.part.presenter.apply.SearchPresenter;
import com.xzty.cq.tover.businessmanagement.projectmanagement.utils.DateUtil;
import com.xzty.cq.tover.businessmanagement.projectmanagement.view.MyDatePickerDialog;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;

public class PartApplyCreateActivity extends ActivityPresenter<SearchContract.Presenter>
        implements SearchContract.View, View.OnClickListener,
        AdapterView.OnItemSelectedListener, BaseQuickAdapter.OnItemClickListener, BaseQuickAdapter.OnItemChildClickListener {
    @BindView(R.id.tv_toolbarTitle)
    TextView textView;

    @BindView(R.id.tv_applyordercreate_timename)
    LinearLayout tv_applyordercreate_timename;
    //申请时间
    @BindView(R.id.tv_applyordercreate_time)
    TextView tv_applyordercreate_time;
    //申请人
    @BindView(R.id.spinner_applycreate_person)
    Spinner spinner_applycreate_person;
    //新建申请
    @BindView(R.id.btn_applycreate_create)
    Button btn_applycreate_create;
    //申请单状态
    @BindView(R.id.spinner_applyordercreate_state)
    Spinner spinner_applyordercreate_state;
    //搜索申请按钮
    @BindView(R.id.btn_applycreate_search)
    Button btn_applycreate_search;

/*
    @BindView(R.id.swipeToLoadLayout)
    SwipeToLoadLayout swipeToLoadLayout;

    @BindView(R.id.swipe_refresh_header)
    CustomRefreshHead swipe_refresh_header;

    @BindView(R.id.swipe_load_more_footer)
    CustomRefreshFoot swipe_load_more_footer;*/

    @BindView(R.id.swipe_target)
    RecyclerView swipe_target;

    //申请人List
    private List<Emp> applyPersonList = new ArrayList<>();

    private String[] stateArray = {"全部", "未确认", "已确认"};

    //状态List
    private List<String> applyOrderStateList = Arrays.asList(stateArray);


    private Emp emp = new Emp("全部", "");

    //状态
    private String applyOrderState = "";


    private Calendar c = Calendar.getInstance();

    private RecyclerView.LayoutManager mLayoumanage;

    private PartApplyCreateAdapter mAdapter;

    //申请的数据
    private List<RspApplyList> mList;

    //申请人的adapter
    private ApplyListPersonSpinnerAdapter personAdapter;

    //记录是否是第一次加载
    private boolean isFirst = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getContentLayoutId() {
        return R.layout.activity_part_apply_create;
    }

    @Override
    protected void initWidget() {
        super.initWidget();
        textView.setText("申请列表");
    }

    @Override
    protected void initData() {
        super.initData();
        Account.load(this);
        Intent intent = getIntent();
        String from = intent.getStringExtra("FROM");
        if (!TextUtils.isEmpty(from)) {
            btn_applycreate_create.setVisibility(View.GONE);
        }
        spinner_applyordercreate_state.setAdapter(new ArrayAdapter<String>(PartApplyCreateActivity.this, android.R.layout.simple_spinner_item, applyOrderStateList));
        //选中监听
        spinner_applyordercreate_state.setOnItemSelectedListener(this);
        btn_applycreate_search.setOnClickListener(this);
        tv_applyordercreate_timename.setOnClickListener(this);
        tv_applyordercreate_time.setOnClickListener(this);
        btn_applycreate_create.setOnClickListener(this);
        mLayoumanage = new LinearLayoutManager(this, LinearLayout.VERTICAL, false);
        //初始化申请人数据
        personAdapter = new ApplyListPersonSpinnerAdapter(applyPersonList, this);
        spinner_applycreate_person.setAdapter(personAdapter);
        //进来初始化数据
        Account.load(this);
        String projectId = Account.getProjectId();
        String applyTime = tv_applyordercreate_time.getText().toString().trim();
        mPresenter.search(projectId, emp.getApplyUserId(), applyTime, applyOrderState);
        spinner_applycreate_person.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                emp = applyPersonList.get(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_applycreate_search:
                Account.load(this);
                //搜索
                String projectId = Account.getProjectId();
                String applyTime = tv_applyordercreate_time.getText().toString().trim();
                mPresenter.search(projectId, emp.getApplyUserId(), applyTime, applyOrderState);
                break;
            case R.id.tv_applyordercreate_timename:
                //时间获取
                pickDate(tv_applyordercreate_time);
                pickDate(tv_applyordercreate_time);
                break;
            case R.id.tv_applyordercreate_time:
                //清空申请时间
                tv_applyordercreate_time.setText("");
                break;
            case R.id.btn_applycreate_create:
                //新建
                startActivity(new Intent(this, PartChooseActivity.class));
                break;
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        applyOrderState = applyOrderStateList.get(position);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    public void showError(String str) {
        Toast.makeText(this, str, Toast.LENGTH_SHORT);
    }

    @Override
    public void showLoading() {


    }


    @Override
    protected SearchContract.Presenter initPresenter() {
        return new SearchPresenter(this);
    }

    @Override
    public void searchSuccess(List<RspApplyList> list) {
        mList = list;
        mAdapter = new PartApplyCreateAdapter(R.layout.part_apply_recycle_item, mList);
        swipe_target.setLayoutManager(mLayoumanage);
        swipe_target.setAdapter(mAdapter);

        if (isFirst) {
            applyPersonList.add(emp);
            for (Emp emp1 : applyPersonList) {//emp1：已经在adapter中的每一个
                for (RspApplyList apply : mList) {
                    if (apply.getEplyName() != emp1.getEplyName() || apply.getApplyUserId() != emp1.getApplyUserId()) {
                        applyPersonList.add(new Emp(apply.getEplyName(), apply.getApplyUserId()));
                    }
                }
            }

            Map map = new HashMap<>();
            List<Emp> newList = new ArrayList<>();
            for (Emp e : applyPersonList) {
                if (!map.containsKey(e.getApplyUserId())) {
                    map.put(e.getApplyUserId(), e);
                    newList.add(e);
                }
            }
            applyPersonList = newList;
            personAdapter = new ApplyListPersonSpinnerAdapter(applyPersonList, this);
            spinner_applycreate_person.setAdapter(personAdapter);
            isFirst = false;
        }
        mAdapter.setOnItemClickListener(this);
        if (!TextUtils.isEmpty(getIntent().getStringExtra("FROM"))) {
            mAdapter.setOnItemChildClickListener(this);
        }
    }

    @Override
    public void nataBack() {
        Toast.makeText(this, "操作成功", Toast.LENGTH_SHORT).show();
        btn_applycreate_search.performClick();
    }

    //根据条件检索出来的数据

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
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        Intent intent = new Intent(this, PartApplyDetailsActivity.class);
        intent.putExtra("projectApply", new Gson().toJson(mList.get(position)).toString());
        startActivity(intent);
    }

    @Override
    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
        mPresenter.notaApply(mList.get(position).getApplyId() + "");
    }
}
