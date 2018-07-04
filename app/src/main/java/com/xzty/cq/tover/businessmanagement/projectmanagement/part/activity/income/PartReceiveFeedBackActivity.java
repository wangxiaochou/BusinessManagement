package com.xzty.cq.tover.businessmanagement.projectmanagement.part.activity.income;

import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.donkingliang.imageselector.utils.ImageSelectorUtils;
import com.xzty.cq.tover.businessmanagement.R;
import com.xzty.cq.tover.businessmanagement.common.factory.ActivityPresenter;
import com.xzty.cq.tover.businessmanagement.projectmanagement.part.adapter.GirdPicItemAdapter;
import com.xzty.cq.tover.businessmanagement.projectmanagement.part.model.deliver.DeliverDetails;
import com.xzty.cq.tover.businessmanagement.projectmanagement.part.model.deliver.DeliverOrder;
import com.xzty.cq.tover.businessmanagement.projectmanagement.part.presenter.income.ReceiveFeedBackContract;
import com.xzty.cq.tover.businessmanagement.projectmanagement.part.presenter.income.ReceiveFeedBackPresenter;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class PartReceiveFeedBackActivity extends ActivityPresenter<ReceiveFeedBackContract.Presenter> implements ReceiveFeedBackContract.View, View.OnClickListener {
    @BindView(R.id.tv_toolbarTitle)
    TextView tv_toolbarTitle;

    @BindView(R.id.tv_feedback_outno)
    TextView tv_feedback_outno;

    @BindView(R.id.tv_feedback_partname)
    TextView tv_feedback_partname;

    @BindView(R.id.tv_feedback_partno)
    TextView tv_feedback_partno;

    @BindView(R.id.iv_feedback_minus)
    ImageView iv_feedback_minus;

    @BindView(R.id.tv_feedback_needcount)
    TextView tv_feedback_needcount;

    @BindView(R.id.tv_feedback_unit)
    TextView tv_feedback_unit;

    @BindView(R.id.iv_feedback_add)
    ImageView iv_feedback_add;

    @BindView(R.id.tv_feedback_partbatch)
    TextView tv_feedback_partbatch;

    @BindView(R.id.et_feedback_abilitydept)
    EditText et_feedback_abilitydept;

    @BindView(R.id.et_feedback_note)
    EditText et_feedback_note;

    @BindView(R.id.btn_feedback_upload)
    Button btn_feedback_upload;

    @BindView(R.id.btn_feedback_save)
    Button btn_feedback_save;

    @BindView(R.id.gv_feedback)
    GridView gv_feedback;

    private String deliverOrderString;
    private String problemdeliverDetailString;
    private DeliverOrder deliverOrder;
    private DeliverDetails deliverDetail;
    private int position;
    private double originalCount;
    private DecimalFormat df = new DecimalFormat("#######.#######");
    private final int RESULT_CODE = 100;

    private ArrayList<String> images;//选择的图片地址
    private List<Bitmap> picsBitmapList = new ArrayList<>();
    private AlertDialog.Builder dialog;
    private AlertDialog show;

    @Override
    protected int getContentLayoutId() {
        return R.layout.activity_part_receive_feed_back;
    }

    @Override
    protected void initWidget() {
        super.initWidget();
        tv_toolbarTitle.setText("构件问题反馈");
    }

    @Override
    protected void initData() {
        super.initData();
        init();
    }

    private void init() {
        Intent intent = getIntent();
        deliverOrderString = intent.getStringExtra("outOrder");
        problemdeliverDetailString = intent.getStringExtra("problemOutDetail");
        position = intent.getIntExtra("position", -1);
        deliverOrder = JSON.parseObject(deliverOrderString, DeliverOrder.class);
        deliverDetail = JSON.parseObject(problemdeliverDetailString, DeliverDetails.class);
        originalCount = deliverDetail.getApplyItemCount();

        tv_feedback_outno.setText(deliverOrder.getOutNo());
        tv_feedback_partname.setText(deliverDetail.getPartName());
        tv_feedback_partno.setText(deliverDetail.getPartNo());
        tv_feedback_unit.setText(deliverDetail.getPartUnit());
        tv_feedback_partbatch.setText(deliverDetail.getOutBatch() + "批");
        tv_feedback_needcount.setText(String.valueOf(df.format(deliverDetail.getResidualQuantity())));

        iv_feedback_minus.setOnClickListener(this);
        iv_feedback_add.setOnClickListener(this);
        btn_feedback_upload.setOnClickListener(this);
        btn_feedback_save.setOnClickListener(this);
    }

    @Override

    public void showError(String str) {
        if(show!=null){
            show.dismiss();
        }
        Toast.makeText(this, str, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showLoading() {
        dialog = new AlertDialog.Builder(this);
        dialog.setCancelable(false);
        dialog.setMessage("保存中...");
        show = dialog.show();
    }

    @Override
    public void back(DeliverDetails deliverDetails1) {
        deliverDetail =deliverDetails1;
        tv_feedback_needcount.setText(deliverDetail.getResidualQuantity()+"");
    }

    @Override
    public void picBack(ArrayList<String> images1, List<Bitmap> picsBitmapList1) {
        images = images1;
        picsBitmapList = picsBitmapList1;
        gv_feedback.setAdapter(new GirdPicItemAdapter(picsBitmapList, this));
    }

    @Override
    public void success() {
        if(show!=null){
            show.dismiss();
        }
        Toast.makeText(this, "保存成功", Toast.LENGTH_SHORT).show();
        int resultCode = 51;
        Intent data = new Intent();
        data.putExtra("backCount", deliverDetail.getResidualQuantity());
        data.putExtra("position", position);
        setResult(resultCode, data);
        finish();
    }

    @Override
    protected ReceiveFeedBackContract.Presenter initPresenter() {
        return new ReceiveFeedBackPresenter(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_feedback_minus:
                mPresenter.subtract(deliverDetail);
                break;

            case R.id.iv_feedback_add:
                mPresenter.add(deliverDetail);
                break;

            case R.id.btn_feedback_upload:
                gv_feedback.setVisibility(View.VISIBLE);
                ImageSelectorUtils.openPhoto(this, RESULT_CODE);
                break;

            case R.id.btn_feedback_save:
                String detailsId = deliverDetail.getOutDetailId().toString();
                String applyItemCount = deliverDetail.getResidualQuantity() + "";
                String backReason = et_feedback_note.getText().toString().trim();
                String backDutyDept = et_feedback_abilitydept.getText().toString().trim();
                mPresenter.save(images, picsBitmapList, detailsId, applyItemCount, backReason, backDutyDept);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RESULT_CODE && data != null) {
            ImageView imageView = findViewById(R.id.iv_100dp);
            mPresenter.proPic(images, picsBitmapList, imageView, data);
        }
    }
}
