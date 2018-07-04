package com.xzty.cq.tover.businessmanagement.projectmanagement.tool.activity.pickdepartment.distribute;

import android.support.v7.widget.AppCompatSpinner;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.xzty.cq.tover.businessmanagement.R;
import com.xzty.cq.tover.businessmanagement.common.factory.Account;
import com.xzty.cq.tover.businessmanagement.common.factory.ActivityPresenter;
import com.xzty.cq.tover.businessmanagement.projectmanagement.tool.model.pickdepartment.RspDemage;
import com.xzty.cq.tover.businessmanagement.projectmanagement.tool.model.pickdepartment.ToolBDInRequest;
import com.xzty.cq.tover.businessmanagement.projectmanagement.tool.presenter.pickdepartment.distribute.DistributeInContract;
import com.xzty.cq.tover.businessmanagement.projectmanagement.tool.presenter.pickdepartment.distribute.DistributeInPresenter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class ToolPDInActivity extends ActivityPresenter<DistributeInContract.Presenter> implements DistributeInContract.View {
    @BindView(R.id.tv_notice)
    TextView tvNotice;
    @BindView(R.id.pb)
    ProgressBar pb;
    @BindView(R.id.tbdi_spinner_number)
    AppCompatSpinner tbdiSpinnerNumber;
    @BindView(R.id.tbdi_et_name)
    EditText tbdiEtName;
    @BindView(R.id.tbdi_et_model)
    EditText tbdiEtModel;
    @BindView(R.id.tbdi_et_brand)
    EditText tbdiEtBrand;
    @BindView(R.id.tbdi_et_power)
    EditText tbdiEtPower;
    @BindView(R.id.tbdi_et_count)
    EditText tbdiEtCount;
    @BindView(R.id.tbdi_et_category)
    EditText tbdiEtCategory;
    @BindView(R.id.tbdi_spinner_depot)
    AppCompatSpinner tbdiSpinnerDepot;
    @BindView(R.id.tbdi_spinner_department)
    AppCompatSpinner tbdiSpinnerDepartment;
    @BindView(R.id.tbdi_et_price)
    EditText tbdiEtPrice;
    @BindView(R.id.tbdi_spinner_damage)
    AppCompatSpinner tbdiSpinnerDamage;
    @BindView(R.id.tbdi_et_note)
    EditText tbdiEtNote;
    @BindView(R.id.tbdi_bt_in)
    Button tbdiBtIn;

    private ToolBDInRequest request;
    private List<String> numberSpinnerItem;
    private String[] depotSpinnerItem = {"采购仓库", "项管仓库"};
    private String[] departmentSpinnerItem = {"采购部", "项管部"};
    private String[] damageSpinnerItem = {"完好", "维修", "损坏", "报废"};

    @Override
    protected int getContentLayoutId() {
        return R.layout.activity_tool_pdin;
    }

    @Override
    protected void initWidget() {
        super.initWidget();
    }

    @Override
    protected void initData() {
        super.initData();
        initVar();
        initView();
        mPresenter.demage();
    }

    private void initView() {
        ArrayAdapter<String> numberSpinnerAdapter = new ArrayAdapter<>(this, R.layout.support_simple_spinner_dropdown_item, numberSpinnerItem);
        tbdiSpinnerNumber.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i) {
                    case 0:
                        request.number = "1";
                        tbdiEtCount.setText("1");
                        break;
                    case 1:
                        request.number = "0";
                        break;
                    default:
                        request.number = numberSpinnerItem.get(i);
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        tbdiSpinnerNumber.setAdapter(numberSpinnerAdapter);
        //
        ArrayAdapter<String> depotSpinnerAdapter = new ArrayAdapter<>(this, R.layout.support_simple_spinner_dropdown_item, depotSpinnerItem);
        tbdiSpinnerDepot.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i) {
                    //采购部
                    case 0:
                        request.depotCode = 2;
                        break;
                    //项管部
                    case 1:
                        request.depotCode = 1;
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
        tbdiSpinnerDepot.setAdapter(depotSpinnerAdapter);
        //
        ArrayAdapter<String> departmentSpinnerAdapter = new ArrayAdapter<>(this, R.layout.support_simple_spinner_dropdown_item, departmentSpinnerItem);
        tbdiSpinnerDepartment.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i) {
                    //采购部
                    case 0:
                        request.departmentCode = 2;
                        break;
                    //项管部
                    case 1:
                        request.departmentCode = 1;
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
        tbdiSpinnerDepartment.setAdapter(departmentSpinnerAdapter);
        //
        ArrayAdapter<String> damageSpinnerAdapter = new ArrayAdapter<>(this, R.layout.support_simple_spinner_dropdown_item, damageSpinnerItem);
        tbdiSpinnerDamage.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                request.damageCode = i;
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
        tbdiSpinnerDamage.setAdapter(damageSpinnerAdapter);
    }

    private void initVar() {
        request = new ToolBDInRequest();
        request.distDetailId = getIntent().getIntExtra("distDetailId", -1);
        if (request.distDetailId == -1) {
            Toast.makeText(this, "获取编号失败，请重新选择入库", Toast.LENGTH_LONG).show();
            this.finish();
        }
        Account.load(this);
        int projectId = Integer.parseInt(Account.getProjectId());
        if (projectId == -1) {
            Toast.makeText(this, "获取项目ID失败，请重启APP", Toast.LENGTH_SHORT).show();
            this.finish();
        }

        String employId = Account.getemployId();
        if (employId.equals("")) {
            Toast.makeText(this, "获取操作人ID失败，请注销后重启APP", Toast.LENGTH_SHORT).show();
            this.finish();
        }
        request.projectId = projectId;
        request.userId = employId;
        //
        numberSpinnerItem = new ArrayList<>();
        numberSpinnerItem.add("系统生成编号");
        numberSpinnerItem.add("无需生成编号");
    }

    @OnClick(R.id.tbdi_bt_in)
    void onClick() {
        request.name = tbdiEtName.getText().toString().trim();
        request.count = Double.parseDouble(tbdiEtCount.getText().toString().trim());
        request.category = tbdiEtCategory.getText().toString().trim();
        if (request.name.equals("") || request.category.equals("") || request.count == 0) {
            Toast.makeText(this, "请检查必填项", Toast.LENGTH_LONG).show();
            return;
        }
        request.model = tbdiEtModel.getText().toString().trim();
        request.brand = tbdiEtBrand.getText().toString().trim();
        request.power = tbdiEtPower.getText().toString().trim();
        request.price = tbdiEtPrice.getText().toString().trim();
        request.note = tbdiEtNote.getText().toString().trim();
        mPresenter.in(request);
    }

    @Override
    public void showError(String str) {
        Toast.makeText(this, str, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void demageSuccess(List<RspDemage> list) {
        for (int a = 0; a < list.size(); a++) {
            numberSpinnerItem.add(list.get(a).getToolNumber());
        }
    }

    @Override
    public void success() {
        Toast.makeText(this, "入库成功", Toast.LENGTH_SHORT).show();
        finish();
    }

    @Override
    protected DistributeInContract.Presenter initPresenter() {
        return new DistributeInPresenter(this);
    }
}
