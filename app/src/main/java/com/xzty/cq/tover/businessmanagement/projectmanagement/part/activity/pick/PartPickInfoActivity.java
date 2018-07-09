package com.xzty.cq.tover.businessmanagement.projectmanagement.part.activity.pick;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.gson.Gson;
import com.xzty.cq.tover.businessmanagement.R;
import com.xzty.cq.tover.businessmanagement.common.MyApplication;
import com.xzty.cq.tover.businessmanagement.common.eventbus.EventData;
import com.xzty.cq.tover.businessmanagement.common.factory.ActivityPresenter;
import com.xzty.cq.tover.businessmanagement.projectmanagement.part.adapter.PartPickWriteInfoAdapter;
import com.xzty.cq.tover.businessmanagement.projectmanagement.part.model.pick.RspPickList;
import com.xzty.cq.tover.businessmanagement.projectmanagement.part.model.pick.RspPickOrder;
import com.xzty.cq.tover.businessmanagement.projectmanagement.part.presenter.pick.PickWriteInfoContract;
import com.xzty.cq.tover.businessmanagement.projectmanagement.part.presenter.pick.PickWriteInfoPresenter;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.Calendar;
import java.util.List;

import butterknife.BindView;

public class PartPickInfoActivity extends ActivityPresenter<PickWriteInfoContract.Presenter> implements PickWriteInfoContract.View, View.OnClickListener, BaseQuickAdapter.OnItemClickListener {
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

    @BindView(R.id.cb_distpartchoose_checkall)
    CheckBox cb_distpartchoose_checkall;

    @BindView(R.id.btn_price)
    Button btn_price;

    private List<RspPickList> mList;

    private PartPickWriteInfoAdapter mAdapter;

    private RecyclerView.LayoutManager mLayoutManage;

    private Calendar c = Calendar.getInstance();

    private RspPickOrder pickOrder;

    AlertDialog.Builder alter;

    private EditText mEdittext;

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
        cb_distpartchoose_checkall.setOnClickListener(this);
        btn_price.setOnClickListener(this);
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
     *
     * @param eventData
     */
    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void setRspData(EventData<RspPickList> eventData) {
        mList = eventData.getEventData();
        EventBus.getDefault().removeStickyEvent(eventData);
    }

    @Override
    protected void initData() {
        super.initData();
        pickOrder = JSON.parseObject(getIntent().getStringExtra("distOrder"), RspPickOrder.class);
        setAdapter();
    }

    private void setAdapter() {
        mLayoutManage = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mAdapter = new PartPickWriteInfoAdapter(R.layout.part_pick_wrieteinfo_recycle_item, mList);
        recyle_info.setLayoutManager(mLayoutManage);
        mAdapter.setOnItemClickListener(this);
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

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
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
                String pickList = new Gson().toJson(mList);
                mPresenter.commit(firm, contractno, outtime, noto, distId, pickList);
                break;
            case R.id.cb_distpartchoose_checkall:
                mPresenter.chooseAll(mList, cb_distpartchoose_checkall.isChecked());
                break;
            case R.id.btn_price:
                //弹出dialog填写信息
                alter = new AlertDialog.Builder(this);
                alter.setCancelable(false);
                alter.setTitle("填写勾选构件的价格");
                mEdittext = new EditText(this);
                alter.setView(mEdittext = new EditText(this));
                alter.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        mPresenter.writePrice(mList,mEdittext.getText().toString());
                    }
                });
                alter.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });
                alter.create();
                alter.show();
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
        startActivity(new Intent(this, PartPickOrderActivity.class));
    }

    @Override
    public void clickBack(List<RspPickList> list) {
        mList = list;
        mAdapter.notifyDataSetChanged();
    }

    @Override
    protected PickWriteInfoContract.Presenter initPresenter() {
        return new PickWriteInfoPresenter(this);
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        mPresenter.itemClick(mList, position);
    }
}
