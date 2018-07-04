package com.xzty.cq.tover.businessmanagement.projectmanagement.part.activity.deliver;

import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.donkingliang.imageselector.utils.ImageSelectorUtils;
import com.google.gson.Gson;
import com.xzty.cq.tover.businessmanagement.R;
import com.xzty.cq.tover.businessmanagement.common.factory.ActivityPresenter;
import com.xzty.cq.tover.businessmanagement.projectmanagement.part.adapter.GirdPicItemAdapter;
import com.xzty.cq.tover.businessmanagement.projectmanagement.part.adapter.PartDeliverWriteInfoAdapter;
import com.xzty.cq.tover.businessmanagement.projectmanagement.part.model.deliver.DeliverDetails;
import com.xzty.cq.tover.businessmanagement.projectmanagement.part.model.deliver.DeliverOrder;
import com.xzty.cq.tover.businessmanagement.projectmanagement.part.model.deliver.RspBuyInfo;
import com.xzty.cq.tover.businessmanagement.projectmanagement.part.presenter.deliver.DeliverWriteInfoContract;
import com.xzty.cq.tover.businessmanagement.projectmanagement.part.presenter.deliver.DeliverWriteInfoPresenter;
import com.xzty.cq.tover.businessmanagement.projectmanagement.utils.DateUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * author zzl
 * Created 2018/5/15
 * explain 发货信息填写
 */
public class DeliverWriteInfoActivity extends ActivityPresenter<DeliverWriteInfoContract.Presenter> implements DeliverWriteInfoContract.View, View.OnClickListener {
    @BindView(R.id.tv_toolbarTitle)
    TextView tv_toolbarTitle;

    @BindView(R.id.tv_outfillinfo_firm)
    TextView tv_outfillinfo_firm;

    @BindView(R.id.tv_outfillinfo_contractno)
    TextView tv_outfillinfo_contractno;

    @BindView(R.id.tv_outfillinfo_delivdate)
    TextView tv_outfillinfo_delivdate;

    @BindView(R.id.tv_outfillinfo_note)
    TextView tv_outfillinfo_note;

    @BindView(R.id.et_outfillinfo_sender)
    EditText et_outfillinfo_sender;

    @BindView(R.id.et_outfillinfo_senderphone)
    EditText et_outfillinfo_senderphone;

    @BindView(R.id.et_outfillinfo_carsize)
    EditText et_outfillinfo_carsize;

    @BindView(R.id.et_outfillinfo_carno)
    EditText et_outfillinfo_carno;

    @BindView(R.id.et_outfillinfo_deliveno)
    EditText et_outfillinfo_deliveno;

    @BindView(R.id.btn_outfillinfo_upload)
    Button btn_outfillinfo_upload;

    @BindView(R.id.btn_outfillinfo_save)
    Button btn_outfillinfo_save;

    @BindView(R.id.sv_outfillinfo)
    ScrollView sv_outfillinfo;

    @BindView(R.id.gv_outfillinfo_addpics)
    GridView gv_outfillinfo_addpics;

    @BindView(R.id.recycle_outfillinfo)
    RecyclerView recycle_outfillinfo;

    private RecyclerView.LayoutManager mLayoutManage;

    private List<DeliverDetails> recycleList;

    private PartDeliverWriteInfoAdapter recycleAdapter;

    private DeliverOrder deliverOrder;

    private final int REQUEST_CODE = 100;

    private ArrayList<String> images;//选择的图片地址

    private List<Bitmap> picsBitmapList = new ArrayList<>();

    private AlertDialog.Builder dialog;

    private AlertDialog show;

    @Override
    protected int getContentLayoutId() {
        return R.layout.activity_deliver_write_info;
    }

    @Override
    protected void initWidget() {
        super.initWidget();
        tv_toolbarTitle.setText("发货信息填写");
        btn_outfillinfo_upload.setOnClickListener(this);
        btn_outfillinfo_save.setOnClickListener(this);
    }

    @Override
    protected void initData() {
        super.initData();
        deliverOrder = JSON.parseObject(getIntent().getStringExtra("outOrder"), DeliverOrder.class);
        recycle_outfillinfo.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
                    sv_outfillinfo.requestDisallowInterceptTouchEvent(false);
                } else {
                    sv_outfillinfo.requestDisallowInterceptTouchEvent(true);
                }
                return false;
            }
        });
        tv_outfillinfo_firm.setText(deliverOrder.getFirm() == null ? "空" : deliverOrder.getFirm());
        tv_outfillinfo_contractno.setText(deliverOrder.getContractNo() == null ? "空" : deliverOrder.getContractNo());
        tv_outfillinfo_delivdate.setText(DateUtil.dateToString(deliverOrder.getExpectOutTime()));
        tv_outfillinfo_note.setText(deliverOrder.getOutNote() == null ? "空" : deliverOrder.getOutNote());
        mPresenter.getBuyinfo(deliverOrder.getOutId() + "");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_outfillinfo_upload:
                //不限数量的多选
                ImageSelectorUtils.openPhoto(this, REQUEST_CODE);
                break;
            case R.id.btn_outfillinfo_save://保存按钮
                btn_outfillinfo_save.setEnabled(false);
                dialog = new AlertDialog.Builder(this);
                dialog.setCancelable(false);
                dialog.setMessage("保存中...");
                show = dialog.show();
                String sender = et_outfillinfo_sender.getText().toString().trim();
                String senderphone = et_outfillinfo_senderphone.getText().toString().trim();
                String carsize = et_outfillinfo_carsize.getText().toString().trim();
                String carno = et_outfillinfo_carno.getText().toString().trim();
                String deliveno = et_outfillinfo_deliveno.getText().toString().trim();
                mPresenter.save(images, picsBitmapList, deliverOrder.getOutId() + "", sender, senderphone, carsize, carno, deliveno);
                break;
        }
    }

    @Override
    public void showError(String str) {
        if (dialog != null) {
            show.dismiss();
        }
        btn_outfillinfo_save.setEnabled(true);
        Toast.makeText(this, str, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void backBuyInfo(List<RspBuyInfo> list) {
        recycleList = JSON.parseArray(new Gson().toJson(list), DeliverDetails.class);
        setAdapter();
    }

    @Override
    public void backSave() {
        if (show != null) {
            show.dismiss();
            Toast.makeText(this, "保存成功", Toast.LENGTH_SHORT).show();
            finish();
        }
    }

    @Override
    public void setGrideAdapter(ArrayList<String> images1, List<Bitmap> picsBitmapList1) {
        images = images1;
        picsBitmapList = picsBitmapList1;
        gv_outfillinfo_addpics.setAdapter(new GirdPicItemAdapter(picsBitmapList, this));
    }


    private void setAdapter() {
        mLayoutManage = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recycle_outfillinfo.setLayoutManager(mLayoutManage);
        recycleAdapter = new PartDeliverWriteInfoAdapter(R.layout.part_deliver_writeinfo_recycle_item,recycleList);
        recycle_outfillinfo.setAdapter(recycleAdapter);
    }

    @Override
    protected DeliverWriteInfoContract.Presenter initPresenter() {
        return new DeliverWriteInfoPresenter(this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE && data != null) {
            ImageView imv = (ImageView) findViewById(R.id.iv_100dp);
            mPresenter.setGride(images,picsBitmapList,data,imv);
        }
    }
}
