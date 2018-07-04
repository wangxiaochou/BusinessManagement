package com.xzty.cq.tover.businessmanagement.projectmanagement.part.activity.collect;

import android.app.DatePickerDialog;
import android.content.Intent;
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
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.xzty.cq.tover.businessmanagement.R;
import com.xzty.cq.tover.businessmanagement.common.factory.ActivityPresenter;
import com.xzty.cq.tover.businessmanagement.projectmanagement.part.activity.apply.PartApplyCreateActivity;
import com.xzty.cq.tover.businessmanagement.projectmanagement.part.adapter.CollectAdapter;
import com.xzty.cq.tover.businessmanagement.projectmanagement.part.model.collect.RspCollect;
import com.xzty.cq.tover.businessmanagement.projectmanagement.part.presenter.collect.CollectContract;
import com.xzty.cq.tover.businessmanagement.projectmanagement.part.presenter.collect.CollectPresenter;
import com.xzty.cq.tover.businessmanagement.projectmanagement.utils.DateUtil;
import com.xzty.cq.tover.businessmanagement.projectmanagement.view.MyDatePickerDialog;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import butterknife.BindView;

/**
 * author zzl
 * Created 2018/5/11
 * explain 汇总
 */

public class PartCollectActivity extends ActivityPresenter<CollectContract.Presenter> implements CollectContract.View, View.OnClickListener, BaseQuickAdapter.OnItemClickListener {
    @BindView(R.id.tv_toolbarTitle)
    TextView textView;

    //构件名称
    @BindView(R.id.spinner_apply_collect_name)
    Spinner spinner_apply_collect_name;

    //构件编号
    @BindView(R.id.et_apply_collect_partno)
    EditText et_apply_collect_partno;

    //申请批次
    @BindView(R.id.spinner_apply_Collect_batch)
    Spinner spinner_apply_Collect_batch;

    //到场时间图标
    @BindView(R.id.iv_apply_collect_expettimeicon)
    ImageView iv_apply_collect_expettimeicon;

    //到场时间
    @BindView(R.id.tv_apply_collect_expettime)
    TextView tv_apply_collect_expettime;

    //搜索按钮
    @BindView(R.id.iv_apply_collect_search)
    Button iv_apply_collect_search;

    //全选Box
    @BindView(R.id.cb_apply_collect_selectall)
    CheckBox cb_apply_collect_selectall;

    //信息提示
    @BindView(R.id.tv_apply_collect_checknuminfo)
    TextView tv_apply_collect_checknuminfo;

    //申请确认按钮
    @BindView(R.id.btn_applycreate_collect_dist)
    Button btn_applycreate_collect_dist;

    //采购分配按钮
    @BindView(R.id.btn_apply_collect_dist)
    Button btn_apply_collect_dist;

    @BindView(R.id.recycle_collect)
    RecyclerView recycle_collect;


    private List<String> applybatchList = new ArrayList<>();
    private String applyBatch = "";

    private List<String> partNameList = new ArrayList<>();
    private String partName = "";

    private RecyclerView.LayoutManager mLayoutManage;

    private List<RspCollect> mList;

    private CollectAdapter mAdapter;

    private boolean isFirst = true;
    //选中的个数
    private List<RspCollect> thisTimeSelectedPartTemp = new ArrayList<>();

    private Calendar c = Calendar.getInstance();

    private DecimalFormat df = new DecimalFormat("#######.#######");

    @Override
    protected int getContentLayoutId() {
        return R.layout.activity_part_apply_collect;
    }

    @Override
    protected void initWidget() {
        super.initWidget();
        textView.setText("申请汇总");
        mLayoutManage = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        spinner_apply_Collect_batch.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                applyBatch = applybatchList.get(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spinner_apply_collect_name.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                partName = partNameList.get(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        iv_apply_collect_expettimeicon.setOnClickListener(this);
        cb_apply_collect_selectall.setOnClickListener(this);
        iv_apply_collect_search.setOnClickListener(this);
        btn_applycreate_collect_dist.setOnClickListener(this);
        tv_apply_collect_expettime.setOnClickListener(this);
        btn_apply_collect_dist.setOnClickListener(this);
    }

    private void init() {
        String partNo = et_apply_collect_partno.getText().toString().trim();
        String time = tv_apply_collect_expettime.getText().toString().trim();
        mPresenter.getAllCollect(partName, partNo, applyBatch, time);
    }

    @Override
    protected void initData() {
        super.initData();
    }

    @Override
    public void showError(String str) {
        Toast.makeText(this, str, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showLoading() {
    }

    @Override
    protected CollectContract.Presenter initPresenter() {
        return new CollectPresenter(this);
    }


    private void initSearchConditions(List<RspCollect> list, List<String> partNameList1, List<String> applybatchList1) {
        partNameList = partNameList1;
        applybatchList = applybatchList1;
        spinner_apply_collect_name.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, this.partNameList));
        spinner_apply_Collect_batch.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, this.applybatchList));

    }

    private void setAdapter() {
        recycle_collect.setLayoutManager(mLayoutManage);
        mAdapter = new CollectAdapter(R.layout.part_collect_recycle_item, mList);
        recycle_collect.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(this);
    }

    @Override
    public void netSuccess(List<RspCollect> list, List<String> partNameList, List<String> applybatchList) {
        mList = list;
        thisTimeSelectedPartTemp.clear();
        tv_apply_collect_checknuminfo.setText(null);
        setAdapter();
        if (isFirst) {
            initSearchConditions(list, partNameList, applybatchList);
        }
    }

    @Override
    public void chooseAllBack(List<RspCollect> list, List<RspCollect> chooseCount) {
        mList = list;
        thisTimeSelectedPartTemp = chooseCount;
        mAdapter.notifyDataSetChanged();
        showSelectedCount();
    }

    @Override
    public void chooseItemBack(List<RspCollect> list, List<RspCollect> chooseCount) {
        mList = list;
        thisTimeSelectedPartTemp = chooseCount;
        mAdapter.notifyDataSetChanged();
        Log.e("TAG", "所有的数据=" + thisTimeSelectedPartTemp.size());
        showSelectedCount();
    }

    private void showSelectedCount() {
        double partCount = 0;
        StringBuffer tip = new StringBuffer("选中");
        for (RspCollect part : thisTimeSelectedPartTemp) {
            partCount += part.getApplyItemCount();
        }
        tip.append(thisTimeSelectedPartTemp.size()).append("种构件,").append(df.format(partCount)).append("件构件");
        tv_apply_collect_checknuminfo.setText(tip);

        if (thisTimeSelectedPartTemp.size() == mList.size()) {
            cb_apply_collect_selectall.setChecked(true);
        } else {
            cb_apply_collect_selectall.setChecked(false);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            //选择时间
            case R.id.iv_apply_collect_expettimeicon:
                pickDate(tv_apply_collect_expettime);
                pickDate(tv_apply_collect_expettime);
                break;
            case R.id.cb_apply_collect_selectall:
                //选中全部
                mPresenter.chooseAll(mList, cb_apply_collect_selectall.isChecked());
                break;
            case R.id.iv_apply_collect_search:
                //搜索
                init();
                break;
            case R.id.btn_applycreate_collect_dist:
                //确认申请
                //跳转到申请模块中的申请列表中去,这里跳过去的申请列表,没有新建功能
                Intent intent = new Intent(this, PartApplyCreateActivity.class);
                intent.putExtra("FROM", this.getClass().getName());
                startActivity(intent);
                break;

            case R.id.tv_apply_collect_expettime:
                tv_apply_collect_expettime.setText("");
                break;

            case R.id.btn_apply_collect_dist:
                //采购分配
                if (thisTimeSelectedPartTemp.size() > 0) {
                    Intent intent1 = new Intent(this, CollectInfoActivity.class);
                    intent1.putExtra("data", (Serializable) thisTimeSelectedPartTemp);
                    startActivity(intent1);
                } else {
                    Toast.makeText(this, "请至少选择一种构件", Toast.LENGTH_SHORT).show();
                }
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
        mPresenter.itemChoose(mList, position);
    }

    @Override
    protected void onResume() {
        super.onResume();
        init();
    }
}
