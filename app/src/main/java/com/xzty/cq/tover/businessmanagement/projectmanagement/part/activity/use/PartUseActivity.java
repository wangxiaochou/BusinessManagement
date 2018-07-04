package com.xzty.cq.tover.businessmanagement.projectmanagement.part.activity.use;

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
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.xzty.cq.tover.businessmanagement.R;
import com.xzty.cq.tover.businessmanagement.common.factory.ActivityPresenter;
import com.xzty.cq.tover.businessmanagement.projectmanagement.part.adapter.PartUseChooseAdapter;
import com.xzty.cq.tover.businessmanagement.projectmanagement.part.model.use.AllModel;
import com.xzty.cq.tover.businessmanagement.projectmanagement.part.presenter.use.UseChooseContract;
import com.xzty.cq.tover.businessmanagement.projectmanagement.part.presenter.use.UseChoosePresenter;
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
 * explain 领用
 */

public class PartUseActivity extends ActivityPresenter<UseChooseContract.Presenter>
        implements UseChooseContract.View, View.OnClickListener, BaseQuickAdapter.OnItemClickListener {
    @BindView(R.id.tv_toolbarTitle)
    TextView tv_toolbarTitle;

    @BindView(R.id.spinner_receiveparts_partname)
    Spinner spinner_receiveparts_partname;

    @BindView(R.id.spinner_receiveparts_applybatch)
    Spinner spinner_receiveparts_applybatch;

    @BindView(R.id.spinner_receiveparts_outbatch)
    Spinner spinner_receiveparts_outbatch;

    @BindView(R.id.et_receiveparts_partno)
    EditText et_receiveparts_partno;

    @BindView(R.id.llout_receiveparts_receivetimeicon)
    LinearLayout llout_receiveparts_receivetimeicon;

    @BindView(R.id.tv_receiveparts_receivetime)
    TextView tv_receiveparts_receivetime;

    @BindView(R.id.btn_receiveparts_search)
    Button btn_receiveparts_search;

    @BindView(R.id.recycle_receiveparts)
    RecyclerView recycle_receiveparts;

    @BindView(R.id.cb_receiveparts_checkall)
    CheckBox cb_receiveparts_checkall;

    @BindView(R.id.tv_receiveparts_numinfo)
    TextView tv_receiveparts_numinfo;

    @BindView(R.id.tv_receiveparts_checknuminfo)
    TextView tv_receiveparts_checknuminfo;

    @BindView(R.id.btn_receiveparts_submit)
    Button btn_receiveparts_submit;

    private DecimalFormat df = new DecimalFormat("#######.#######");

    private List<String> partNameList = new ArrayList<>();
    private String partName = "";
    private List<String> applyBatchList = new ArrayList<>();
    private String applyBatch = "";
    private List<String> outBatchList = new ArrayList<>();
    private String outBatch = "";

    private Calendar c = Calendar.getInstance();

    private RecyclerView.LayoutManager mLayoutManage;

    private PartUseChooseAdapter mAdapter;

    private List<AllModel> mList;

    //存放临时选中的构件
    private List<AllModel> thisTimeSelectedPartTemp = new ArrayList<>();

    private boolean isFirst = true;

    @Override
    protected int getContentLayoutId() {
        return R.layout.activity_part_receive_parts;
    }

    @Override
    protected void initWidget() {
        super.initWidget();
        tv_toolbarTitle.setText("领用>>选择构件");
        spinner_receiveparts_partname.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                partName = partNameList.get(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spinner_receiveparts_applybatch.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                applyBatch = applyBatchList.get(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spinner_receiveparts_outbatch.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                outBatch = outBatchList.get(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    @Override
    protected void initData() {
        super.initData();
        llout_receiveparts_receivetimeicon.setOnClickListener(this);
        btn_receiveparts_search.setOnClickListener(this);
        btn_receiveparts_submit.setOnClickListener(this);
        cb_receiveparts_checkall.setOnClickListener(this);
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
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.llout_receiveparts_receivetimeicon:
                pickDate(tv_receiveparts_receivetime);
                pickDate(tv_receiveparts_receivetime);
                break;
            case R.id.btn_receiveparts_search:
                String time = tv_receiveparts_receivetime.getText().toString().trim();
                String partNo = et_receiveparts_partno.getText().toString().trim();
                mPresenter.search(time, applyBatch, outBatch, partName, partNo);
                break;
            case R.id.btn_receiveparts_submit:
                if (thisTimeSelectedPartTemp.size() > 0) {
                    Intent intent = new Intent(this, PartUseWriteInfoActivity.class);
                    intent.putExtra("chooseParts", JSON.toJSONString(thisTimeSelectedPartTemp));
                    startActivityForResult(intent, 50);
                } else {
                    Toast.makeText(this, "请至少勾选一种构件", Toast
                            .LENGTH_SHORT).show();
                }
                break;
            case R.id.cb_receiveparts_checkall:
                mPresenter.chooseAll(mList, thisTimeSelectedPartTemp, cb_receiveparts_checkall.isChecked());
                break;
        }
    }


    @Override
    public void showError(String str) {
        Toast.makeText(this, str, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void success(List<String> partNameList1, List<String> applyBatchList1, List<String> outBatchList1, List<AllModel> allList) {

        mList = allList;
        if (isFirst) {
            partNameList = partNameList1;
            applyBatchList = applyBatchList1;
            outBatchList = outBatchList1;
            setSpinner();
            isFirst = false;
        }
        thisTimeSelectedPartTemp.clear();
        cb_receiveparts_checkall.setChecked(false);
        tv_receiveparts_checknuminfo.setText(null);
        setRecyle();
    }

    @Override
    public void selectedAll(List<AllModel> list, List<AllModel> selected) {
        mList = list;
        thisTimeSelectedPartTemp = selected;
        mAdapter.notifyDataSetChanged();
        recycle_receiveparts.setAdapter(mAdapter);
        Log.e("TAG", "所有的数据=" + thisTimeSelectedPartTemp.size());
        showSelectedCount();
    }

    private void showSelectedCount() {
        double partCount = 0;
        StringBuffer tip = new StringBuffer("选中");
        for (AllModel part : thisTimeSelectedPartTemp) {
            partCount += part.getCollectDetailCount();
        }
        tip.append(thisTimeSelectedPartTemp.size()).append("种构件,").append(df.format(partCount)).append("件构件");
        tv_receiveparts_checknuminfo.setText(tip);
    }

    @Override
    public void selectedItem(List<AllModel> list, List<AllModel> item) {
        mList = list;
        mAdapter.notifyDataSetChanged();
        thisTimeSelectedPartTemp = item;
        Log.e("TAG", "item的数据=" + thisTimeSelectedPartTemp.size());
        showSelectedCount();
    }

    private void setRecyle() {
        mLayoutManage = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mAdapter = new PartUseChooseAdapter(R.layout.part_use_choose_recyle_item, mList);
        recycle_receiveparts.setLayoutManager(mLayoutManage);
        recycle_receiveparts.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(this);
    }

    private void setSpinner() {
        spinner_receiveparts_partname.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, partNameList));
        spinner_receiveparts_applybatch.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, applyBatchList));
        spinner_receiveparts_outbatch.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, outBatchList));
    }

    @Override
    protected UseChooseContract.Presenter initPresenter() {
        return new UseChoosePresenter(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        btn_receiveparts_search.performClick();
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {

        mPresenter.itemClick(mList, thisTimeSelectedPartTemp, position);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 50 && resultCode == 51) {
            String pickCount = data.getStringExtra("pickCount");
            List<AllModel> pickParts = JSON.parseArray(pickCount, AllModel.class);
            for (AllModel pickPart : pickParts) {
                for (AllModel part : thisTimeSelectedPartTemp) {
                    if (part.getCollectDetailId().equals(pickPart.getCollectDetailId())) {
                        part.setCollectDetailCount(part.getCollectDetailCount() - pickPart.getCollectDetailCount());
                        break;
                    }
                }
                tv_receiveparts_checknuminfo.setText(null);
            }
        }
    }
}
