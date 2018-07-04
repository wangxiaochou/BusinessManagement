package com.xzty.cq.tover.businessmanagement.projectmanagement.part.activity.pick;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.google.gson.Gson;
import com.xzty.cq.tover.businessmanagement.R;
import com.xzty.cq.tover.businessmanagement.common.MyApplication;
import com.xzty.cq.tover.businessmanagement.common.factory.ActivityPresenter;
import com.xzty.cq.tover.businessmanagement.projectmanagement.part.adapter.PartPickWriteInfoAdapter;
import com.xzty.cq.tover.businessmanagement.projectmanagement.part.model.pick.RspPickList;
import com.xzty.cq.tover.businessmanagement.projectmanagement.part.model.pick.RspPickOrder;
import com.xzty.cq.tover.businessmanagement.projectmanagement.part.presenter.pick.PickWriteInfoContract;
import com.xzty.cq.tover.businessmanagement.projectmanagement.part.presenter.pick.PickWriteInfoPresenter;

import java.util.Calendar;
import java.util.List;

import butterknife.BindView;

public class PartPickInfoActivity extends ActivityPresenter<PickWriteInfoContract.Presenter> implements PickWriteInfoContract.View, View.OnClickListener {
    @BindView(R.id.tv_toolbarTitle)
    TextView tv_toolbarTitle;

    @BindView(R.id.et_distpartchoosefillinfo_firm)
    EditText et_distpartchoosefillinfo_firm;

    @BindView(R.id.et_distpartchoosefillinfo_contractno)
    EditText et_distpartchoosefillinfo_contractno;

    @BindView(R.id.llout_distpartchoosefillinfo_outtimeicon)
    LinearLayout llout_distpartchoosefillinfo_outtimeicon;

    @BindView(R.id.tv_distpartchoosefillinfo_outtime)
    TextView tv_distpartchoosefillinfo_outtime;

    @BindView(R.id.et_distpartchoosefillinfo_note)
    EditText et_distpartchoosefillinfo_note;

    @BindView(R.id.btn_distpartchoosefillinfo_submit)
    Button btn_distpartchoosefillinfo_submit;

    @BindView(R.id.recyle_info)
    RecyclerView recyle_info;

    private List<RspPickList> mList;

    private PartPickWriteInfoAdapter mAdapter;

    private RecyclerView.LayoutManager mLayoutManage;

    private Calendar c = Calendar.getInstance();

    private RspPickOrder pickOrder;

    @Override
    protected int getContentLayoutId() {
        return R.layout.activity_part_pick_info;
    }

    @Override
    protected void initWidget() {
        super.initWidget();
        tv_toolbarTitle.setText("填写采购信息");
        llout_distpartchoosefillinfo_outtimeicon.setOnClickListener(this);
        btn_distpartchoosefillinfo_submit.setOnClickListener(this);
    }

    @Override
    protected void initData() {

        super.initData();
        mList = JSON.parseArray(getIntent().getStringExtra("partList"), RspPickList.class);
        pickOrder = JSON.parseObject(getIntent().getStringExtra("distOrder"), RspPickOrder.class);
        setAdapter();
    }

    private void setAdapter() {
        mLayoutManage = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mAdapter = new PartPickWriteInfoAdapter(R.layout.part_pick_wrieteinfo_recycle_item, mList);
        recyle_info.setLayoutManager(mLayoutManage);
        recyle_info.setAdapter(mAdapter);
    }

    private void pickDate(final TextView tv) {
        new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                StringBuffer sb = new StringBuffer();
                sb.append(year).append("-");
                sb.append((monthOfYear + 1) < 10 ? "0" + (monthOfYear + 1) : (monthOfYear + 1)).append("-");
                sb.append((dayOfMonth < 10) ? "0" + dayOfMonth : dayOfMonth);
                Log.e("TAG", "onDateSet: " + sb.toString());
                tv.setText(sb.toString());
            }
        }, c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH)).show();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.llout_distpartchoosefillinfo_outtimeicon:
                pickDate(tv_distpartchoosefillinfo_outtime);
                break;

            case R.id.btn_distpartchoosefillinfo_submit:
                btn_distpartchoosefillinfo_submit.setEnabled(false);
                String firm = et_distpartchoosefillinfo_firm.getText().toString().trim();
                String contractno = et_distpartchoosefillinfo_contractno.getText().toString().trim();
                String outtime = tv_distpartchoosefillinfo_outtime.getText().toString().trim();
                String noto = et_distpartchoosefillinfo_note.getText().toString().trim();
                String distId = pickOrder.getDistId().toString();
                String pickList =new Gson().toJson(mList);
                mPresenter.commit(firm, contractno, outtime, noto, distId, pickList);
                break;
        }
    }

    @Override
    public void showError(String str) {
        btn_distpartchoosefillinfo_submit.setEnabled(true);
        Toast.makeText(this, str, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void ok() {
        btn_distpartchoosefillinfo_submit.setEnabled(true);
        Toast.makeText(this, "提交成功", Toast.LENGTH_SHORT).show();
        //在Activity栈中移除本activity和选择采购物品的activity
        MyApplication.finishAssignActivity(ChoosePickActivity.class);
        MyApplication.finishAssignActivity(PartPickOrderActivity.class);
        finish();
        startActivity(new Intent(this,PartPickOrderActivity.class));
    }

    @Override
    protected PickWriteInfoContract.Presenter initPresenter() {
        return new PickWriteInfoPresenter(this);
    }
}
