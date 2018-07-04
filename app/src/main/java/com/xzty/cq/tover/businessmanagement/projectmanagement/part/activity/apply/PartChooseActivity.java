package com.xzty.cq.tover.businessmanagement.projectmanagement.part.activity.apply;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.xzty.cq.tover.businessmanagement.R;
import com.xzty.cq.tover.businessmanagement.common.MyApplication;
import com.xzty.cq.tover.businessmanagement.common.factory.Account;
import com.xzty.cq.tover.businessmanagement.common.factory.ActivityPresenter;
import com.xzty.cq.tover.businessmanagement.projectmanagement.part.adapter.ChoosePartAdapter;
import com.xzty.cq.tover.businessmanagement.projectmanagement.part.model.apply.RspPartList;
import com.xzty.cq.tover.businessmanagement.projectmanagement.part.presenter.apply.ChooseContract;
import com.xzty.cq.tover.businessmanagement.projectmanagement.part.presenter.apply.ChoosePresenter;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;

/**
 * author zzl
 * Created 2018/5/7
 * explain 选择 构件
 */

public class PartChooseActivity extends ActivityPresenter<ChooseContract.Presenter>
        implements ChooseContract.View,
        View.OnClickListener, BaseQuickAdapter.OnItemClickListener {
    //名称
    @BindView(R.id.spinner_choose_name)
    Spinner spinner_choose_name;

    //批次
    @BindView(R.id.spinner_choose_batch)
    Spinner spinner_choose_batch;

    //搜索按钮
    @BindView(R.id.btn_choose_search)
    Button btn_choose_search;

    //全选
    @BindView(R.id.cb_choose_checkall)
    CheckBox cb_choose_checkall;

    @BindView(R.id.checknuminfo)
    TextView checknuminfo;

    //申请按钮
    @BindView(R.id.btn_choose_submit)
    Button btn_choose_submit;

    //构件编号
    @BindView(R.id.et_choose_partno)
    TextView et_choose_partno;

    //提示数量
    @BindView(R.id.numinfo)
    TextView numinfo;

   /* @BindView(R.id.swipeToLoadLayout)
    SwipeToLoadLayout swipeToLoadLayout;

    @BindView(R.id.swipe_refresh_header)
    CustomRefreshHead swipe_refresh_header;

    @BindView(R.id.swipe_load_more_footer)
    CustomRefreshFoot swipe_load_more_footer;*/

    @BindView(R.id.swipe_target)
    RecyclerView swipe_target;

    //状态
    @BindView(R.id.spinner_choose_ishadapply)
    Spinner spinner_choose_ishadapply;

    private RecyclerView.LayoutManager layoutManager;
    private ChoosePartAdapter mAdapter;
    //数据数组
    private List<RspPartList> mList = new ArrayList<>();

    private String[] applyStateArray = {"全部", "已申请", "未申请"};
    private List<String> applyStateList = Arrays.asList(applyStateArray);
    private String applyState = "";

    private List<String> partBatchList = new ArrayList<>();
    private String partBatch = "";

    @BindView(R.id.tv_toolbarTitle)
    TextView tv_toolbarTitle;

    private List<String> partNameList = new ArrayList<>();
    private String partName = "";

    private List<RspPartList> thisTimeSelectedPartTemp = new ArrayList<>();   //存放临时选中的构件
    private List<RspPartList> partList = new ArrayList<>();

    private DecimalFormat df = new DecimalFormat("#######.#######");

    @Override
    protected int getContentLayoutId() {
        return R.layout.activity_part_choose;
    }

    @Override
    protected void initWidget() {
        super.initWidget();
        tv_toolbarTitle.setText("选择构件");
        layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        spinner_choose_name.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                partName = partNameList.get(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spinner_choose_batch.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                partBatch = partBatchList.get(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spinner_choose_ishadapply = (Spinner) findViewById(R.id.spinner_choose_ishadapply);
        spinner_choose_ishadapply.setAdapter(new ArrayAdapter<String>(this, android.R
                .layout.simple_spinner_item, applyStateList));
        spinner_choose_ishadapply.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                applyState = applyStateList.get(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        btn_choose_search.setOnClickListener(this);
        cb_choose_checkall.setOnClickListener(this);
        cb_choose_checkall.setChecked(false);
        btn_choose_submit.setOnClickListener(this);
    }

    @Override
    protected void initData() {
        super.initData();
        Account.load(this);
        //请求数据
        getData();
    }

    private void getData() {
        String projectId = Account.getProjectId();
        String partNo = et_choose_partno.getText().toString().trim();
        mPresenter.searchPart(projectId, partName, partNo, applyState, partBatch);
    }

    private void setAdapter() {
        mAdapter = new ChoosePartAdapter(R.layout.part_choose_recycle_item, mList);
        swipe_target.setLayoutManager(layoutManager);
        swipe_target.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(this);
    }


    @Override
    public void showError(String str) {
        btn_choose_search.setEnabled(true);
        Toast.makeText(this, str, Toast.LENGTH_LONG).show();
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void searchSuccess(final List<RspPartList> list) {
        Toast.makeText(this, "搜索成功", Toast.LENGTH_LONG).show();
        btn_choose_search.setEnabled(true);
        numinfo.setText("共命中" + list.size() + "条记录");
        mList = list;
        setAdapter();
    }

    //设置数据
    @Override
    public void disBatch(List<String> list, List<String> list2) {
        partNameList = list;
        spinner_choose_name.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, partNameList));
        partBatchList = list2;
        spinner_choose_batch.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, partBatchList));

    }

    @Override
    public void selectedAll(List<RspPartList> list) {
        mList = list;
        mAdapter.notifyDataSetChanged();
        Log.e("TAG", "所有的数据=" + thisTimeSelectedPartTemp.size());
        showSelectedCount();
    }

    @Override
    public void selectedItem(List<RspPartList> list) {
        mList = list;
        mAdapter.notifyDataSetChanged();
        Log.e("TAG", "item的数据=" + thisTimeSelectedPartTemp.size());
        showSelectedCount();
    }

    @Override
    public void goCallBack(List<RspPartList> mList) {
        if (mList.size() <= 0) {
            Toast.makeText(this, "请至少选择一个构件", Toast.LENGTH_SHORT).show();
        } else {
            Intent intent = new Intent(this, PartApplyInfoActivity.class);
            intent.putExtra("PARTLIST", (Serializable) mList);
            startActivity(intent);
        }
    }

    private void showSelectedCount() {
        double partCount = 0;
        int choosePart = 0;
        StringBuffer tip = new StringBuffer("选中");
        for (RspPartList part : mList) {
            if (part.isCheck) {
                partCount += part.getPartCount();
                choosePart++;
            }
        }
        tip.append(choosePart).append("种构件,").append(df.format(partCount)).append("件构件");
        checknuminfo.setText(tip);
    }

    @Override
    protected ChooseContract.Presenter initPresenter() {
        return new ChoosePresenter(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_choose_search:
                //搜索
                btn_choose_search.setEnabled(false);
                getData();
                break;
            case R.id.cb_choose_checkall:
                //选中全部
                mPresenter.chooseAll(mList, thisTimeSelectedPartTemp, cb_choose_checkall);
                break;
            case R.id.btn_choose_submit:
                //申请
                mPresenter.go(mList);
                break;
        }
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        mPresenter.itemClick(mList, thisTimeSelectedPartTemp, position);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        MyApplication.finishActivity(this);
    }
}
