package com.xzty.cq.tover.businessmanagement.projectmanagement.part.activity.apply;

import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.style.RelativeSizeSpan;
import android.text.style.SuperscriptSpan;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.xzty.cq.tover.businessmanagement.R;
import com.xzty.cq.tover.businessmanagement.common.factory.ActivityPresenter;
import com.xzty.cq.tover.businessmanagement.projectmanagement.part.adapter.ApplyDetailsAdapter;
import com.xzty.cq.tover.businessmanagement.projectmanagement.part.model.apply.ProjectApplyDetail;
import com.xzty.cq.tover.businessmanagement.projectmanagement.part.model.apply.ReqApplyDetails;
import com.xzty.cq.tover.businessmanagement.projectmanagement.part.model.apply.RspPartList;
import com.xzty.cq.tover.businessmanagement.projectmanagement.part.presenter.apply.OrderPartContract;
import com.xzty.cq.tover.businessmanagement.projectmanagement.part.presenter.apply.OrderPartPresenter;

import java.util.List;

import butterknife.BindView;

public class ApplyOrderPartDetialActivity extends ActivityPresenter<OrderPartContract.presenter> implements OrderPartContract.View {

    @BindView(R.id.tv_toolbarTitle)
    TextView tv_toolbarTitle;

    @BindView(R.id.tv_applyorderpartdetial_partId)
    TextView partId;

    @BindView(R.id.tv_applyorderpartdetial_projectId)
    TextView projectId;

    @BindView(R.id.tv_applyorderpartdetial_partName)
    TextView partName;

    @BindView(R.id.tv_applyorderpartdetial_partNo)
    TextView partNo;

    @BindView(R.id.tv_applyorderpartdetial_partSpec)
    TextView partSpec;

    @BindView(R.id.tv_applyorderpartdetial_partThick)
    TextView partThick;

    @BindView(R.id.tv_applyorderpartdetial_partStuff)
    TextView partStuff;

    @BindView(R.id.tv_applyorderpartdetial_partWidth)
    TextView partWidth;

    @BindView(R.id.tv_applyorderpartdetial_partSize)
    TextView partSize;

    @BindView(R.id.tv_applyorderpartdetial_partCount)
    TextView partCount;

    @BindView(R.id.tv_applyorderpartdetial_partUnit)
    TextView partUnit;

    @BindView(R.id.tv_applyorderpartdetial_partSingle)
    TextView partSingle;

    @BindView(R.id.tv_applyorderpartdetial_partTotal)
    TextView partTotal;

    @BindView(R.id.tv_applyorderpartdetial_singleArea)
    TextView singleArea;

    @BindView(R.id.tv_applyorderpartdetial_totalArea)
    TextView totalArea;

    @BindView(R.id.tv_applyorderpartdetial_partShape)
    TextView partShape;

    @BindView(R.id.tv_applyorderpartdetial_partPlace)
    TextView partPlace;

    @BindView(R.id.tv_applyorderpartdetial_partColor)
    TextView partColor;

    @BindView(R.id.tv_applyorderpartdetial_partBrand)
    TextView partBrand;

    @BindView(R.id.tv_applyorderpartdetial_partSend)
    TextView partBatch;

    @BindView(R.id.tv_applyorderpartdetial_partBatch)
    TextView partSend;

    @BindView(R.id.tv_applyorderpartdetial_needCount)
    TextView needCount;

    @BindView(R.id.tv_applyorderpartdetial_partBranch)
    TextView partBranch;

    @BindView(R.id.tv_applyorderpartdetial_partRange)
    TextView partRange;

    @BindView(R.id.tv_applyorderpartdetial_partType)
    TextView partType;

    @BindView(R.id.tv_applyorderpartdetial_writer)
    TextView writer;

    @BindView(R.id.tv_applyorderpartdetial_duties)
    TextView duties;

    @BindView(R.id.tv_applyorderpartdetial_compilationDate)
    TextView compilationDate;

    @BindView(R.id.tv_applyorderpartdetial_makeNo)
    TextView makeNo;

    @BindView(R.id.tv_applyorderpartdetial_intoTime)
    TextView intoTime;

    @BindView(R.id.tv_applyorderpartdetial_partNote)
    TextView partRemark;

    @Override
    protected int getContentLayoutId() {
        return R.layout.activity_apply_order_part_detial;
    }

    @Override
    protected void initWidget() {
        super.initWidget();
        tv_toolbarTitle.setText("申请单构件详情");
    }

    @Override
    protected void initData() {
        super.initData();
        ProjectApplyDetail projectApplyDetail = JSON.parseObject(getIntent().getStringExtra("projectApplyDetail"), ProjectApplyDetail.class);
        ReqApplyDetails projectApply = JSON.parseObject(getIntent().getStringExtra("projectApply"), ReqApplyDetails.class);
        mPresenter.getItmeDetails(projectApply.getProjectId() + "", projectApplyDetail.getPartId() + "");
    }

    @Override
    public void showError(String str) {
        Toast.makeText(this, str, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void susccess(List<RspPartList> list) {
        setData(list);
    }

    private void setData(List<RspPartList> list) {
        SpannableString m2 = new SpannableString("m2");
        m2.setSpan(new RelativeSizeSpan(0.6f), 1, 2, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);//一半大小
        m2.setSpan(new SuperscriptSpan(), 1, 2, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);   //上标
        for (RspPartList projectPart : list) {

            if (TextUtils.isEmpty(projectPart.getPartId() + "")) {
                partId.setText("空");
            } else {
                partId.setText(projectPart.getPartId() + "");
            }
            if (TextUtils.isEmpty(projectPart.getProjectId() + "")) {
                projectId.setText("空");
            } else {
                projectId.setText(projectPart.getProjectId() + "");
            }
            if (projectPart.getPartName() == null || projectPart.getPartName().isEmpty()) {
                partName.setText("空");
            } else {
                partName.setText(projectPart.getPartName());
            }
            if (projectPart.getPartNo() == null || projectPart.getPartNo().isEmpty()) {
                partNo.setText("空");
            } else {
                partNo.setText(projectPart.getPartNo());
            }
            //截面规格（mm）
            if (projectPart.getPartSpec() == null || projectPart.getPartSpec().isEmpty()) {
                partSpec.setText("空");
            } else {
                partSpec.setText(projectPart.getPartSpec() + "(mm)");
            }
            //厚度
            if (TextUtils.isEmpty(projectPart + "")) {
                partThick.setText("空");
            } else {
                partThick.setText(projectPart.getPartThick() + " mm");
            }
            //材质
            if (projectPart.getPartStuff() == null) {
                partStuff.setText("空");
            } else {
                partStuff.setText(projectPart.getPartStuff());
            }
            //展开宽度
            if (TextUtils.isEmpty(projectPart.getPartWidth() + "")) {
                partWidth.setText("空");
            } else {
                partWidth.setText(projectPart.getPartWidth() + " mm");
            }
            //长度
            if (TextUtils.isEmpty((projectPart.getPartSize() + ""))) {
                partSize.setText("空");
            } else {
                partSize.setText(projectPart.getPartSize() + " mm");
            }
            //构件数量
            if (TextUtils.isEmpty(projectPart.getPartCount() + "")) {
                partCount.setText("空");
            } else {
                partCount.setText(ApplyDetailsAdapter.sDecimalFormat.format(projectPart.getPartCount()));
            }
            if (projectPart.getPartUnit() == null) {
                partUnit.setText("空");
            } else {
                partUnit.setText(projectPart.getPartUnit());
            }
            //单重
            if (TextUtils.isEmpty(projectPart.getPartSingle() + "")) {
                partSingle.setText("空");
            } else {
                partSingle.setText(projectPart.getPartSingle() + " kg");
            }
            if (TextUtils.isEmpty(projectPart.getPartTotal() + "")) {
                partTotal.setText("空");
            } else {
                partTotal.setText(projectPart.getPartTotal() + " kg");
            }
            if (TextUtils.isEmpty(projectPart.getSingleArea() + "")) {
                singleArea.setText("空");
            } else {
                SpannableStringBuilder spannableStringBuilder =
                        new SpannableStringBuilder(projectPart.getSingleArea() + "");
                spannableStringBuilder.append(m2);
                singleArea.setText(spannableStringBuilder);
            }
            if (TextUtils.isEmpty(projectPart.getTotalArea() + "")) {
                totalArea.setText("空");
            } else {
                SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(projectPart.getTotalArea()+"");
                spannableStringBuilder.append(m2);
                singleArea.setText(spannableStringBuilder);
            }
            if (projectPart.getPartShape() == null || projectPart.getPartShape().isEmpty()) {
                partShape.setText("空");
            } else {
                partShape.setText(projectPart.getPartShape());
            }
            if (projectPart.getPartPlace() == null || projectPart.getPartPlace().isEmpty()) {
                partPlace.setText("空");
            } else {
                partPlace.setText(projectPart.getPartPlace());
            }
            if (projectPart.getPartColor() == null || projectPart.getPartColor().isEmpty()) {
                partColor.setText("空");
            } else {
                partColor.setText(projectPart.getPartColor());
            }
            if (projectPart.getPartBrand() == null) {
                partBrand.setText("空");
            } else {
                partBrand.setText(projectPart.getPartBrand());
            }
            if (projectPart.getPartSend() == null || projectPart.getPartSend().isEmpty()) {
                partSend.setText("空");
            } else {
                partSend.setText(projectPart.getPartSend());
            }
            if (projectPart.getPartBatch() == null || projectPart.getPartBatch().isEmpty()) {
                partBatch.setText("空");
            } else {
                partBatch.setText(projectPart.getPartBatch());
            }
            //需求数量
            if (TextUtils.isEmpty(projectPart.getNeedCount()+"")) {
                needCount.setText("空");
            } else {
                needCount.setText(ApplyDetailsAdapter.sDecimalFormat.format(projectPart.getNeedCount()));
            }
            if (projectPart.getPartBranch() == null || projectPart.getPartBranch().isEmpty()) {
                partBranch.setText("空");
            } else {
                partBranch.setText(projectPart.getPartBranch());
            }
            if (projectPart.getPartRange() == null || projectPart.getPartRange().isEmpty()) {
                partRange.setText("空");
            } else {
                partRange.setText(projectPart.getPartRange());
            }
            if (projectPart.getPartType() == null || projectPart.getPartType().isEmpty()) {
                partType.setText("空");
            } else {
                partType.setText(projectPart.getPartType());
            }
            if (projectPart.getWriter() == null || projectPart.getWriter().isEmpty()) {
                writer.setText("空");
            } else {
                writer.setText(projectPart.getWriter());
            }
            if (projectPart.getDuties() == null || projectPart.getDuties().isEmpty()) {
                duties.setText("空");
            } else {
                duties.setText(projectPart.getDuties());
            }
            //编制日期
            if (projectPart.getCompilationDate() == null || projectPart.getCompilationDate().isEmpty()) {
                compilationDate.setText("空");
            } else {
                String compilationDate2 = projectPart.getCompilationDate();
                String newCompilationDate = compilationDate2.substring(0, 4) + "-" + compilationDate2.substring(4, 6) + "-" + compilationDate2.substring(6, 8);
                compilationDate.setText(newCompilationDate);
            }
            if (projectPart.getMakeNo() == null || projectPart.getMakeNo().isEmpty()) {
                makeNo.setText("空");
            } else {
                makeNo.setText(projectPart.getMakeNo());
            }
            //构件进场时间
            if (projectPart.getIntoTime() == null || projectPart.getIntoTime().isEmpty()) {
                intoTime.setText("空");
            } else {
                String[] intoTime2 = projectPart.getIntoTime().split("/");
                intoTime.setText(intoTime2[2] + "-" + intoTime2[1] + "-" + intoTime2[0]);
            }
            if (projectPart.getPartRemark() == null || projectPart.getPartRemark().isEmpty()) {
                partRemark.setText("空");
            } else {
                partRemark.setText(projectPart.getPartRemark());
            }

        }
    }

    @Override
    protected OrderPartContract.presenter initPresenter() {
        return new OrderPartPresenter(this);
    }
}
