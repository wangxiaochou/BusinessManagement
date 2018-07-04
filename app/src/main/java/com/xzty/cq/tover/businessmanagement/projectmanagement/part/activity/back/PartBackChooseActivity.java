package com.xzty.cq.tover.businessmanagement.projectmanagement.part.activity.back;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.xzty.cq.tover.businessmanagement.R;
import com.xzty.cq.tover.businessmanagement.common.eventbus.EventData;
import com.xzty.cq.tover.businessmanagement.common.factory.ActivityPresenter;
import com.xzty.cq.tover.businessmanagement.projectmanagement.part.adapter.PartBackChooseAdapter;
import com.xzty.cq.tover.businessmanagement.projectmanagement.part.model.back.BackPart;
import com.xzty.cq.tover.businessmanagement.projectmanagement.part.presenter.back.BackChooseContract;
import com.xzty.cq.tover.businessmanagement.projectmanagement.part.presenter.back.BackChoosePresenter;

import org.greenrobot.eventbus.EventBus;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * author zzl
 * Created 2018/5/11
 * explain 退回
 */

public class PartBackChooseActivity extends ActivityPresenter<BackChooseContract.Presenter> implements BackChooseContract.View, View.OnClickListener, BaseQuickAdapter.OnItemClickListener {
    @BindView(R.id.tv_toolbarTitle)
    TextView tv_toolbarTitle;

    @BindView(R.id.spinner_backmain_partname)
    Spinner spinner_backmain_partname;

    @BindView(R.id.et_backmain_partno)
    EditText et_backmain_partno;

    @BindView(R.id.spinner_backmain_pickbatch)
    Spinner spinner_backmain_pickbatch;

    @BindView(R.id.llout_backmain_picktimeicon)
    LinearLayout llout_backmain_picktimeicon;

    @BindView(R.id.tv_backmain_picktime)
    TextView tv_backmain_picktime;

    @BindView(R.id.btn_backmain_search)
    Button btn_backmain_search;

    @BindView(R.id.cb_backmain_checkall)
    CheckBox cb_backmain_checkall;

    @BindView(R.id.tv_backmain_numinfo)
    TextView tv_backmain_numinfo;

    @BindView(R.id.btn_backmain_submit)
    Button btn_backmain_submit;

    @BindView(R.id.recycle_backmain)
    RecyclerView recycle_backmain;

    @BindView(R.id.tv_backmain_checknuminfo)
    TextView tv_backmain_checknuminfo;

    private List<String> partNameList = new ArrayList<>();
    private String partName = "";
    private List<String> pickBatchList = new ArrayList<>();
    private String pickBatch = "";

    private List<BackPart> backPartList = new ArrayList<>();

    private List<BackPart> tempBackPartList = new ArrayList<>();

    private RecyclerView.LayoutManager mLayoutManage;

    private PartBackChooseAdapter mAdapter;

    private boolean isFirst = true;

    private DecimalFormat df = new DecimalFormat("#######.#######");

    @Override
    protected int getContentLayoutId() {
        return R.layout.activity_part_back_main;
    }

    @Override
    protected void initWidget() {
        super.initWidget();
        tv_toolbarTitle.setText("退回>>选择构件");
        spinner_backmain_partname.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                partName = partNameList.get(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spinner_backmain_pickbatch.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                pickBatch = pickBatchList.get(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        btn_backmain_search.setOnClickListener(this);
        btn_backmain_submit.setOnClickListener(this);
        cb_backmain_checkall.setOnClickListener(this);
    }

    @Override
    public void showError(String str) {
        Toast.makeText(this, str, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void success(List<BackPart> partList, List<String> partNameList1, List<String> pickBatchList1) {
        backPartList = partList;
        if (isFirst) {
            partNameList = partNameList1;
            pickBatchList = pickBatchList1;
            spinner_backmain_partname.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, partNameList));
            spinner_backmain_pickbatch.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, pickBatchList));
            isFirst = false;
        }
        tempBackPartList.clear();
        tv_backmain_numinfo.setText(null);
        setRecycle();
    }

    @Override
    public void selectedAll(List<BackPart> mList, List<BackPart> thisTimeSelectedPartTemp) {
        backPartList = mList;
        tempBackPartList = thisTimeSelectedPartTemp;
        mAdapter.notifyDataSetChanged();
        recycle_backmain.setAdapter(mAdapter);
        Log.e("TAG", "所有的数据=" + thisTimeSelectedPartTemp.size());
        showSelectedCount();
    }

    private void showSelectedCount() {
        double partCount = 0;
        StringBuffer tip = new StringBuffer("选中");
        for (BackPart part : tempBackPartList) {
            partCount += part.getPickDetailCount();
        }
        tip.append(tempBackPartList.size()).append("种构件,").append(df.format(partCount)).append("件构件");
        tv_backmain_numinfo.setText(tip);
    }

    @Override
    public void selectedItem(List<BackPart> mList, List<BackPart> thisTimeSelectedPartTemp) {
        backPartList = mList;
        mAdapter.notifyDataSetChanged();
        tempBackPartList = thisTimeSelectedPartTemp;
        Log.e("TAG", "item的数据=" + tempBackPartList.size());
        showSelectedCount();
    }

    private void setRecycle() {
        mLayoutManage = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recycle_backmain.setLayoutManager(mLayoutManage);
        mAdapter = new PartBackChooseAdapter(R.layout.part_back_choose_recycle_item, backPartList);
        recycle_backmain.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(this);
    }

    @Override
    protected BackChooseContract.Presenter initPresenter() {
        return new BackChoosePresenter(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_backmain_search:
                String time = tv_backmain_picktime.getText().toString().trim();
                String partNo = et_backmain_partno.getText().toString().trim();
                mPresenter.search(time, pickBatch, partName, partNo);
                break;
            case R.id.btn_backmain_submit:
                if (backPartList.size() > 0) {
                    Intent intent = new Intent(this,
                            PartBackWriteInfoActivity.class);
                    EventBus.getDefault().postSticky(new EventData<BackPart>(tempBackPartList));
                    startActivityForResult(intent, 50);
                } else {
                    Toast.makeText(this, "请至少选择一种构件", Toast
                            .LENGTH_SHORT).show();
                }
                break;
            case R.id.cb_backmain_checkall:
                mPresenter.chooseAll(backPartList,cb_backmain_checkall.isChecked());
                break;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        btn_backmain_search.performClick();
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        mPresenter.itemClick(backPartList, tempBackPartList, position);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 50 && resultCode == 51) {
            String backCount = data.getStringExtra("backCount");
            List<BackPart> backParts = JSON.parseArray(backCount, BackPart.class);
            for (BackPart backPart : backParts) {
                for (BackPart part : tempBackPartList) {
                    if (part.getPickDetailId().equals(backPart.getPickDetailId())) {
                        part.setPickDetailCount(part.getPickDetailCount() - backPart.getPickDetailCount());
                        break;
                    }
                }
            }

        }
    }
}
