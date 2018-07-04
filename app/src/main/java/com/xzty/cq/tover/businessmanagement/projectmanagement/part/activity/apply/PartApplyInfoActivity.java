package com.xzty.cq.tover.businessmanagement.projectmanagement.part.activity.apply;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.util.SparseArray;
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
import com.xzty.cq.tover.businessmanagement.common.MyApplication;
import com.xzty.cq.tover.businessmanagement.common.eventbus.EventData;
import com.xzty.cq.tover.businessmanagement.common.factory.ActivityPresenter;
import com.xzty.cq.tover.businessmanagement.common.model.RspLogin;
import com.xzty.cq.tover.businessmanagement.projectmanagement.part.adapter.PartApplyInfoAdapter;
import com.xzty.cq.tover.businessmanagement.projectmanagement.part.model.apply.RspPartList;
import com.xzty.cq.tover.businessmanagement.projectmanagement.part.presenter.apply.SearchInfoContract;
import com.xzty.cq.tover.businessmanagement.projectmanagement.part.presenter.apply.SearchInfoPresenter;
import com.xzty.cq.tover.businessmanagement.projectmanagement.utils.DateUtil;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import butterknife.BindView;

/**
 * author zzl
 * Created 2018/5/9.
 * explain 构件申请信息填写
 */
public class PartApplyInfoActivity extends ActivityPresenter<SearchInfoContract.Presenter> implements SearchInfoContract.View, View.OnClickListener, BaseQuickAdapter.OnItemChildClickListener, PartApplyInfoAdapter.MyTextWatcher {
    @BindView(R.id.tv_toolbarTitle)
    TextView tv_toolbarTitle;

    //名称
    @BindView(R.id.spinner_chooseset_name)
    Spinner spinner_chooseset_name;

    //批次
    @BindView(R.id.spinner_chooseset_batch)
    Spinner spinner_chooseset_batch;

    //编号
    @BindView(R.id.chooseset_numinfo)
    TextView chooseset_numinfo;

    @BindView(R.id.btn_chooseset_search)
    Button btn_chooseset_search;
    //全选
    @BindView(R.id.cb_chooseset_checkall)
    CheckBox cb_chooseset_checkall;

    @BindView(R.id.iv_chooseset_alldate)
    ImageView iv_chooseset_alldate;

    @BindView(R.id.tv_chooseset_alldatestring)
    TextView tv_chooseset_alldatestring;

    @BindView(R.id.btn_chooseset_submit)
    Button btn_chooseset_submit;

    @BindView(R.id.et_choose_note)
    EditText et_choose_note;

    @BindView(R.id.recycle_chooseset)
    RecyclerView recycle_chooseset;

    private List<String> partBatchList = new ArrayList<>();//批次数据
    private String partBatch = "";
    private List<String> partNameList = new ArrayList<>(); //名称数据
    private String partName = "";

    private List<RspPartList> mList;

    private PartApplyInfoAdapter mAdapter;

    private RecyclerView.LayoutManager mLayoutmanager;

    private Calendar c = Calendar.getInstance();


    //记录每个item机具最大的数量
    private SparseArray<Double> countList = new SparseArray<>();

    @Override
    protected int getContentLayoutId() {
        return R.layout.activity_part_apply_info;
    }

    @Override
    protected void initWidget() {
        super.initWidget();
        tv_toolbarTitle.setText("申请信息填写");
        mLayoutmanager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
    }

    //注册EventBus订阅者
    @Override
    protected void registEventBus() {
        super.registEventBus();
        EventBus.getDefault().register(this);
    }

    //注销EventBus订阅者
    @Override
    protected void cancelEvent() {
        super.cancelEvent();
        EventBus.getDefault().unregister(this);
    }

    /**
     * 处理通过EventBus传输的数据
     * @param eventData
     */
    @Subscribe(threadMode = ThreadMode.MAIN,sticky = true)
    public void setRspData(EventData<RspPartList> eventData){
        mList = eventData.getEventData();
        mPresenter.search(mList, partName, partBatch);
        //移除粘性事件
        EventBus.getDefault().removeStickyEvent(eventData);
    }


    @Override
    protected void initData() {
        super.initData();
/*        mList = (List<RspPartList>) getIntent().getSerializableExtra("PARTLIST");
        mPresenter.search(mList, partName, partBatch);*/
        //给Spinner设置监听
        spinner_chooseset_name.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                partName = partNameList.get(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spinner_chooseset_batch.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                partBatch = partBatchList.get(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        cb_chooseset_checkall.setOnClickListener(this);
    }

    @Override
    public void showError(String str) {
        Toast.makeText(this, str, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showLoading() {

    }


    @Override
    protected SearchInfoContract.Presenter initPresenter() {
        return new SearchInfoPresenter(this);
    }

    @Override
    public void searchSuccess(List<RspPartList> list, List<String> partBatchList1, List<String> partNameList1, SparseArray<Double> sparseArray) {
        countList = sparseArray;
        mAdapter = new PartApplyInfoAdapter(R.layout.part_apply_info_recycle_item, list, this);
        recycle_chooseset.setLayoutManager(mLayoutmanager);
        recycle_chooseset.setAdapter(mAdapter);
        partNameList = partBatchList1;
        partBatchList = partNameList1;
        spinner_chooseset_name.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, partNameList));
        spinner_chooseset_batch.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, partBatchList));
        chooseset_numinfo.setText("共命中" + list.size() + "条记录");
        int smallCheckedCount = 0;
        for (RspPartList part : list) {
            if (part.isCheck) {
                smallCheckedCount++;
            }
        }

        if (smallCheckedCount == list.size()) {
            cb_chooseset_checkall.setChecked(true);
        } else {
            cb_chooseset_checkall.setChecked(false);
        }
        mAdapter.setOnItemChildClickListener(this);
        iv_chooseset_alldate.setOnClickListener(this);
        btn_chooseset_submit.setOnClickListener(this);
    }

    @Override
    public void selectedAllBack(List<RspPartList> list) {
        refreshData(list);
    }

    @Override
    public void singlecheckBox(List<RspPartList> list) {
        refreshData(list);
    }

    @Override
    public void needCount(List<RspPartList> list) {
        refreshData(list);
    }

    @Override
    public void minusNeedCount(List<RspPartList> list) {
        refreshData(list);
    }

    @Override
    public void submitSuccess(RspLogin rspLogin) {
        Toast.makeText(this, "提交成功", Toast.LENGTH_SHORT).show();
        MyApplication.getInstance().finishAssignActivity(PartChooseActivity.class);
        finish();
    }

    private void refreshData(List<RspPartList> list) {
        mList = list;
        mAdapter.notifyDataSetChanged();
        // recycle_chooseset.setAdapter(mAdapter);
        int smallCheckedCount = 0;
        for (RspPartList part : list) {
            if (part.isCheck) {
                smallCheckedCount++;
            }
        }

        if (smallCheckedCount == list.size()) {
            cb_chooseset_checkall.setChecked(true);
        } else {
            cb_chooseset_checkall.setChecked(false);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            //全选
            case R.id.cb_chooseset_checkall:
                mPresenter.selectedAll(mList, cb_chooseset_checkall);
                break;

            case R.id.iv_chooseset_alldate:
                //设置全部的时间
                setAllTime();
                break;

            case R.id.btn_chooseset_submit:
                //提交
                final List<RspPartList> finalList = new ArrayList<>();
                for (RspPartList part : mList) {
                    if (part.isCheck) {
                        finalList.add(part);
                        if (part.getApplyItemExpettime() == null) {
                            Log.e("TAG", "onClick-setAllDate-choosePartList: 时间为null");
                        } else {
                            Log.e("TAG", "onClick-setAllDate-choosePartList: " + DateUtil.dateToString(part.getApplyItemExpettime()));
                        }
                    }
                }
                Log.e("TAG", "onClick-setAllDate-choosePartList-chooseCheckCount: " + finalList.size());
                int timecount = 0;
                for (RspPartList projectPart : finalList) {
                    if (projectPart.getApplyItemExpettime() == null)
                        timecount++;
                }
                if (timecount > 0) {
                    Toast.makeText(this, "你勾选的构件还有没有设置到场时间的", Toast.LENGTH_SHORT).show();
                } else {
                    if (finalList.size() < mList.size()) {
                        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
                        dialog.setTitle("确认勾选数量");
                        dialog.setMessage("您选择了" + mList.size() + "个构件，但您只勾选了" + finalList.size() + "个构件，您确定提交？");
                        dialog.setCancelable(false);
                        dialog.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                //TODO  http保存操作
                                mPresenter.submit(et_choose_note.getText().toString().trim(), finalList);
                                //   submit(finalList);
                            }
                        });
                        dialog.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        });
                        dialog.show();
                    } else {
                        //TODO  http保存操作
//                        Toast.makeText(ChooseSetActivity.this,"直接保存",Toast.LENGTH_SHORT).show();
                        // submit(finalList);
                        mPresenter.submit(et_choose_note.getText().toString().trim(), finalList);
                    }
                }
                break;

        }
    }

    //子控件的点击事件
    @Override
    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
        switch (view.getId()) {
            case R.id.item_cb_chooseset_part:
                mPresenter.checkBoxClick(mList,position);
                break;
            case R.id.item_iv_chooseset_add:
                //添加需求量
                mPresenter.addNeedCount(mList, position);
                break;
            case R.id.item_iv_chooseset_minus:
                //减掉需求量
                mPresenter.minusNeedCount(mList, position);
                break;

            case R.id.item_iv_chooseset_itemdate:
                alertDatePickAndUpdateTimeData(position);
                break;
        }
    }

    private void alertDatePickAndUpdateTimeData(int position) {
        final RspPartList projectPart = mList.get(position);
        new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                StringBuffer sb = new StringBuffer();
                sb.append(year).append("-");
                sb.append((monthOfYear + 1) < 10 ? "0" + (monthOfYear + 1) : (monthOfYear + 1)).append("-");
                sb.append((dayOfMonth < 10) ? "0" + dayOfMonth : dayOfMonth);

                Log.e("TAG", "onDateSet-UpdateTimeData: " + sb.toString());

                projectPart.setApplyItemExpettime(DateUtil.stringToDate(sb.toString()));
                for (RspPartList choosePart : mList) {
                    if (choosePart.getPartId() == (projectPart.getPartId())) {
                        choosePart.setApplyItemExpettime(DateUtil.stringToDate(sb.toString()));
                    }
                }
                mAdapter.notifyDataSetChanged();
            }
        }, c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH)).show();
    }

    private void setAllTime() {
        new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                StringBuffer sb = new StringBuffer();
                sb.append(year).append("-");
                sb.append((monthOfYear + 1) < 10 ? "0" + (monthOfYear + 1) : (monthOfYear + 1)).append("-");
                sb.append((dayOfMonth < 10) ? "0" + dayOfMonth : dayOfMonth);
                for (RspPartList part : mList) {
                    if (part.isCheck) {
                        part.setApplyItemExpettime(DateUtil.stringToDate(sb.toString()));
                        for (RspPartList choosePart : mList) {
                            if (choosePart.getPartId() == part.getPartId()) {
                                choosePart.setApplyItemExpettime(DateUtil.stringToDate(sb.toString()));
                            }
                        }
                    }
                }
                mAdapter.notifyDataSetChanged();
            }
        }, c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH)).show();
    }

    @Override
    public void after(RspPartList rspPartList, int position, EditText et_number) {
        String num = et_number.getText().toString().trim();
        if (!TextUtils.isEmpty(num)) {
            if (countList.get(position) < Double.parseDouble(num)) {
                Toast.makeText(PartApplyInfoActivity.this, "库存不够了", Toast.LENGTH_SHORT).show();
                et_number.setText("");
            } else {
                rspPartList.setNeedCount(Double.parseDouble(num));
            }
        }
    }
}
