package com.xzty.cq.tover.businessmanagement.projectmanagement.part.activity.pick;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.xzty.cq.tover.businessmanagement.R;
import com.xzty.cq.tover.businessmanagement.common.factory.ActivityPresenter;
import com.xzty.cq.tover.businessmanagement.projectmanagement.part.adapter.PartPickChooseAdapter;
import com.xzty.cq.tover.businessmanagement.projectmanagement.part.model.pick.RspPickList;
import com.xzty.cq.tover.businessmanagement.projectmanagement.part.model.pick.RspPickOrder;
import com.xzty.cq.tover.businessmanagement.projectmanagement.part.presenter.pick.PickChooseContract;
import com.xzty.cq.tover.businessmanagement.projectmanagement.part.presenter.pick.PickChoosePresenter;
import com.xzty.cq.tover.businessmanagement.projectmanagement.utils.DateUtil;
import com.xzty.cq.tover.businessmanagement.projectmanagement.view.MyDatePickerDialog;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import butterknife.BindView;

/**
 * author zzl
 * Created 2018/5/11
 * explain 选择采购物品
 */

public class ChoosePickActivity extends ActivityPresenter<PickChooseContract.Presenter> implements PickChooseContract.View, View.OnClickListener, BaseQuickAdapter.OnItemClickListener {
    @BindView(R.id.tv_toolbarTitle)
    TextView tv_toolbarTitle;

    @BindView(R.id.spinner_distpartchoose_partname)
    Spinner spinner_distpartchoose_partname;

    @BindView(R.id.spinner_distpartchoose_applybatch)
    Spinner spinner_distpartchoose_applybatch;

    @BindView(R.id.llout_distpartchoose_timename)
    LinearLayout llout_distpartchoose_timename;

    @BindView(R.id.tv_distpartchoose_disttime)
    TextView tv_distpartchoose_disttime;

    @BindView(R.id.btn_distpartchoose_search)
    Button btn_distpartchoose_search;

    @BindView(R.id.cb_distpartchoose_checkall)
    CheckBox cb_distpartchoose_checkall;

    @BindView(R.id.btn_distpartchoose_hadcheck)
    Button btn_distpartchoose_hadcheck;

    @BindView(R.id.tv_distpartchoose_countinfo)
    TextView tv_distpartchoose_countinfo;

    @BindView(R.id.tv_distpartchoose_checknuminfo)
    TextView tv_distpartchoose_checknuminfo;

    @BindView(R.id.btn_distpartchoose_confirm)
    Button btn_distpartchoose_confirm;

    @BindView(R.id.recycle_distpartchoose)
    RecyclerView recycle_distpartchoose;

    @BindView(R.id.et_distpartchoose_partno)
    EditText et_distpartchoose_partno;

    private Calendar c = Calendar.getInstance();

    private List<String> partNameList = new ArrayList<>(); //名称数据
    private String partName = "";
    private List<String> partBatchList = new ArrayList<>();//批次数据
    private String partBatch = "";

    private String intentString;

    private RspPickOrder distOrder;

    private RecyclerView.LayoutManager mLayoutManage;

    //原始数据
    private List<RspPickList> mList;

    private PartPickChooseAdapter mAdapter;

    //检索数据
    private List<RspPickList> copymList;

    //存储被选中的数据
    private List<RspPickList> chooseList = new ArrayList<>();

    private DecimalFormat df = new DecimalFormat("#######.#######");

    @Override
    protected int getContentLayoutId() {
        return R.layout.activity_choose_pick;
    }

    @Override
    protected void initWidget() {
        super.initWidget();
        tv_toolbarTitle.setText("选择采购物品");

        spinner_distpartchoose_partname = (Spinner) findViewById(R.id.spinner_distpartchoose_partname);
        spinner_distpartchoose_partname.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                partName = partNameList.get(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spinner_distpartchoose_applybatch = (Spinner) findViewById(R.id.spinner_distpartchoose_applybatch);
        spinner_distpartchoose_applybatch.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                partBatch = partBatchList.get(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        mLayoutManage = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        llout_distpartchoose_timename.setOnClickListener(this);
        tv_distpartchoose_disttime.setOnClickListener(this);
        btn_distpartchoose_search.setOnClickListener(this);
        cb_distpartchoose_checkall.setOnClickListener(this);
        btn_distpartchoose_confirm.setOnClickListener(this);
    }

    @Override
    protected void initData() {
        super.initData();
        getPickOrder();
    }

    private void getPickOrder() {
        intentString = getIntent().getStringExtra("distOrder");
        distOrder = JSON.parseObject(intentString, RspPickOrder.class);
        mPresenter.getList(distOrder.getDistId());
    }


    @Override
    public void showError(String str) {
        Toast.makeText(this, str, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showLoading() {
    }

    @Override
    public void callList(List<RspPickList> list, List<String> list1, List<String> list2) {
        partBatchList = list1;
        partNameList = list2;
        mList = list;
        copymList = mList;
        spinner_distpartchoose_partname.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, partNameList));
        spinner_distpartchoose_applybatch.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, partBatchList));
        setAdapter();
    }

    private void setAdapter() {
        mAdapter = new PartPickChooseAdapter(R.layout.part_pick_choose_recycle_item, mList);
        recycle_distpartchoose.setLayoutManager(mLayoutManage);
        recycle_distpartchoose.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(this);
    }

    @Override
    public void searchSuccess(List<RspPickList> successList) {
        mList = successList;
        setAdapter();
    }

    @Override
    public void chooseAllSuccess(List<RspPickList> list, List<RspPickList> chooseList1, int size, double count) {
        mList = list;
        chooseList = chooseList1;
        mAdapter.notifyDataSetChanged();
        if (size > 0) {
            //共选中739种构件,100020件构件
            StringBuffer tip = new StringBuffer("选中");
            tip.append(size).append("种构件,").append(df.format(count)).append("件构件");
            tv_distpartchoose_checknuminfo.setText(tip.toString());
        } else {
            tv_distpartchoose_checknuminfo.setText(null);
        }
        //判断是否全部选中
        CheckChoose();
    }

    private void CheckChoose() {
        if (chooseList.size() == mList.size()) {
            cb_distpartchoose_checkall.setChecked(true);
        } else {
            cb_distpartchoose_checkall.setChecked(false);
        }
    }

    @Override
    public void chooseItmeSuccess(List<RspPickList> list, List<RspPickList> chooseList1, int size, double count) {
        mList = list;
        chooseList = chooseList1;
        mAdapter.notifyDataSetChanged();
        if (size > 0) {
            //共选中739种构件,100020件构件
            StringBuffer tip = new StringBuffer("选中");
            tip.append(size).append("种构件,").append(df.format(count)).append("件构件");
            tv_distpartchoose_checknuminfo.setText(tip.toString());
        } else {
            tv_distpartchoose_checknuminfo.setText(null);
        }
        CheckChoose();
    }

    @Override
    public void showDialog() {
        initDialog();
    }

    @Override
    public void startNota() {
        Intent intent = new Intent(this, PartPickInfoActivity.class);
        String s = JSON.toJSONString(chooseList).toString();
        String s2 = intentString;
        intent.putExtra("partList", s);
        intent.putExtra("distOrder", s2);
        startActivity(intent);
    }

    private void initDialog() {
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setTitle("确认勾选数量");
        dialog.setMessage("此采购单有" + mList.size() + "个构件，但您只勾选了" + chooseList.size() + "个构件，您确定只采购这" + chooseList.size() + "个？");
        dialog.setCancelable(false);
        dialog.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //TODO  http保存操作
                mPresenter.nota();
            }
        });
        dialog.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        dialog.show();
    }

    @Override
    protected PickChooseContract.Presenter initPresenter() {
        return new PickChoosePresenter(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.llout_distpartchoose_timename:
                pickDate(tv_distpartchoose_disttime);
                pickDate(tv_distpartchoose_disttime);
                break;

            case R.id.tv_distpartchoose_disttime:
                tv_distpartchoose_disttime.setText(null);
                break;

            case R.id.btn_distpartchoose_search:
                String partNo = et_distpartchoose_partno.getText().toString().trim();
                String times = tv_distpartchoose_disttime.getText().toString().trim();
                mPresenter.search(mList, partName, partNo, partBatch, times);
                break;
            case R.id.cb_distpartchoose_checkall:
                //选中全部
                mPresenter.chooseAll(mList, cb_distpartchoose_checkall.isChecked(), chooseList);
                break;
            case R.id.btn_distpartchoose_confirm:
                //确定
                mPresenter.checkNota(mList, chooseList);
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
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        //选中单个
        mPresenter.chooseItem(mList, chooseList, position);
    }

}
