package com.xzty.cq.tover.businessmanagement.projectmanagement.part.activity.promote;

import android.app.DatePickerDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.xzty.cq.tover.businessmanagement.R;
import com.xzty.cq.tover.businessmanagement.common.factory.ActivityPresenter;
import com.xzty.cq.tover.businessmanagement.projectmanagement.part.adapter.PartPromoteNumAdapter;
import com.xzty.cq.tover.businessmanagement.projectmanagement.part.model.apply.RspPartList;
import com.xzty.cq.tover.businessmanagement.projectmanagement.part.presenter.apply.PromoteNumContract;
import com.xzty.cq.tover.businessmanagement.projectmanagement.part.presenter.apply.PromoteNumPresenter;
import com.xzty.cq.tover.businessmanagement.projectmanagement.utils.DateUtil;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * author zzl
 * Created 2018/5/5
 * explain 提量
 */

public class PartPromoteNumListActivity extends ActivityPresenter<PromoteNumContract.Presenter> implements PromoteNumContract.View, PartPromoteNumAdapter.TextWatcherListener,
        BaseQuickAdapter.OnItemChildClickListener, BaseQuickAdapter.OnItemClickListener {
    @BindView(R.id.tv_toolbarTitle)
    TextView textView;

    @BindView(R.id.recycle_chooseset)
    RecyclerView recycle_info;

    @BindView(R.id.spinner_choose_name)
    Spinner spinner_choose_name;

    @BindView(R.id.et_chooseset_partno)
    EditText et_chooseset_partno;

    @BindView(R.id.cb_chooseset_checkall)
    CheckBox cb_chooseset_checkall;

    private PartPromoteNumAdapter mAdapter;
    private List<RspPartList> mList;
    private List<String> partNameList = new ArrayList<>();
    String partName;
    private Calendar c = Calendar.getInstance();

    @Override
    protected int getContentLayoutId() {
        return R.layout.activity_part_promote_num_list;
    }

    @Override
    protected void initWidget() {
        super.initWidget();
        textView.setText("提量");
    }

    @Override
    protected void initData() {
        super.initData();
        spinner_choose_name.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                partName = partNameList.get(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        mPresenter.getData(partName, et_chooseset_partno.getText().toString());
    }

    @Override
    public void showError(String str) {
        Toast.makeText(this, str, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void dataSuccess(List<RspPartList> lists, List<String> list) {
        mList = lists;
        partNameList = list;
        setPartNameAdapter();
        setAdapter();
    }

    @Override
    public void clickSuccess(List<RspPartList> lists) {
        mList = lists;
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void submitSuccess() {
        finish();
        Toast.makeText(this,"提交成功",Toast.LENGTH_SHORT).show();
    }

    private void setPartNameAdapter() {
        spinner_choose_name.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, partNameList));
    }

    private void setAdapter() {
        mAdapter = new PartPromoteNumAdapter(R.layout.part_promete_num_recycle_item, mList, this);
        recycle_info.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        mAdapter.setOnItemClickListener(this);
        mAdapter.setOnItemChildClickListener(this);
        recycle_info.setAdapter(mAdapter);

    }

    @OnClick(R.id.btn_chooseset_search)
    void searchClick() {
        mPresenter.getData(partName, et_chooseset_partno.getText().toString());
    }

    @OnClick(R.id.cb_chooseset_checkall)
    void chooseAll() {
        boolean status = cb_chooseset_checkall.isChecked();
        mPresenter.chooseAllClick(status, mList);
    }

    @OnClick(R.id.iv_chooseset_alldate)
    void setTime() {
        setAllTime();
    }

    @OnClick(R.id.btn_chooseset_submit)
    void submit() {
        mPresenter.submit(mList);
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
    protected PromoteNumContract.Presenter initPresenter() {
        return new PromoteNumPresenter(this);
    }

    @Override
    public void after(RspPartList rspPartList, int position, EditText editText) {
        String num = editText.getText().toString().trim();
        if (!TextUtils.isEmpty(num)) {
            rspPartList.setNeedCount(Double.parseDouble(num));
        }
    }

    @Override
    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
        switch (view.getId()) {
            case R.id.item_iv_chooseset_minus:
                mPresenter.minusClick(mList, position);
                break;

            case R.id.item_iv_chooseset_add:
                mPresenter.addClick(mList, position);
                break;

            case R.id.chooseset_itemdate:
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

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        mPresenter.itemClick(mList, position);
    }
}
