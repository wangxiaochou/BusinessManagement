package com.xzty.cq.tover.businessmanagement.projectmanagement.part.activity.collect;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.xzty.cq.tover.businessmanagement.R;
import com.xzty.cq.tover.businessmanagement.common.factory.ActivityPresenter;
import com.xzty.cq.tover.businessmanagement.projectmanagement.part.adapter.ApplyListPersonSpinnerAdapter;
import com.xzty.cq.tover.businessmanagement.projectmanagement.part.adapter.CollectInfoAdapter;
import com.xzty.cq.tover.businessmanagement.projectmanagement.part.model.Emp;
import com.xzty.cq.tover.businessmanagement.projectmanagement.part.model.collect.RspCollect;
import com.xzty.cq.tover.businessmanagement.projectmanagement.part.presenter.collect.CollectInfoContract;
import com.xzty.cq.tover.businessmanagement.projectmanagement.part.presenter.collect.CollectInfoPresenter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * author zzl
 * Created 2018/5/11
 * explain 分配信息填写
 */

public class CollectInfoActivity extends ActivityPresenter<CollectInfoContract.Presenter> implements CollectInfoContract.View, View.OnClickListener {
    @BindView(R.id.tv_toolbarTitle)
    TextView textView;

    @BindView(R.id.spinner_distinfofillin_buyname)
    Spinner spinner_distinfofillin_buyname;

    @BindView(R.id.btn_distinfofillin_submit)
    Button btn_distinfofillin_submit;

    @BindView(R.id.recycle_info)
    RecyclerView recycle_info;

    @BindView(R.id.et_distinfofillin_note)
    EditText et_distinfofillin_note;

    private List<Emp> temp = new ArrayList<>();
    private Emp chooseEmp = new Emp("请选择", "");

    private String choose;

    private RecyclerView.LayoutManager mLayoutManage;

    @Override
    protected int getContentLayoutId() {
        return R.layout.activity_collect_info;
    }

    @Override
    protected void initWidget() {
        super.initWidget();
        textView.setText("分配信息填写");
    }

    @Override
    protected void initData() {
        super.initData();
        mLayoutManage = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        init();
        mPresenter.getAllBuyer();
        spinner_distinfofillin_buyname = (Spinner) findViewById(R.id.spinner_distinfofillin_buyname);
        spinner_distinfofillin_buyname.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                chooseEmp = temp.get(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        btn_distinfofillin_submit.setOnClickListener(this);
    }

    private void init() {
        Intent intent = getIntent();
        choose = JSON.toJSONString(intent.getSerializableExtra("data"));
        List<RspCollect> mList = new ArrayList<>();
        mList = (List<RspCollect>) intent.getSerializableExtra("data");
        recycle_info.setLayoutManager(mLayoutManage);
        recycle_info.setAdapter(new CollectInfoAdapter(R.layout.part_collect_info_recycle_item, mList));
    }

    @Override
    public void showError(String str) {
        Toast.makeText(this, str, Toast.LENGTH_SHORT).show();
        btn_distinfofillin_submit.setEnabled(true);
    }

    @Override
    public void showLoading() {

    }


    @Override
    public void allBuyer(List<Emp> buyerList) {
        temp = buyerList;
        spinner_distinfofillin_buyname.setAdapter(new ApplyListPersonSpinnerAdapter(temp, this));

    }

    @Override
    public void saveBack() {
        finish();
        Toast.makeText(this, "保存成功", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected CollectInfoContract.Presenter initPresenter() {
        return new CollectInfoPresenter(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_distinfofillin_submit:
                btn_distinfofillin_submit.setEnabled(false);
                if (chooseEmp.getEplyName().equals("请选择")) {
                    Toast.makeText(this, "请选择采购人", Toast.LENGTH_LONG).show();
                    btn_distinfofillin_submit.setEnabled(true);
                } else {
                    String employId = chooseEmp.getEmployId();
                    String remake = et_distinfofillin_note.getText().toString().trim();
                    mPresenter.collect(employId, remake, choose);
                }
                break;

        }
    }
}
