package com.xzty.cq.tover.businessmanagement.projectmanagement.part.activity.income;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.xzty.cq.tover.businessmanagement.R;
import com.xzty.cq.tover.businessmanagement.common.factory.ActivityPresenter;
import com.xzty.cq.tover.businessmanagement.common.net.Common;
import com.xzty.cq.tover.businessmanagement.projectmanagement.common.api.Carousel;
import com.xzty.cq.tover.businessmanagement.projectmanagement.part.adapter.PartReceiveDetailsAdapter;
import com.xzty.cq.tover.businessmanagement.projectmanagement.part.model.deliver.DeliverDetails;
import com.xzty.cq.tover.businessmanagement.projectmanagement.part.model.deliver.DeliverOrder;
import com.xzty.cq.tover.businessmanagement.projectmanagement.part.presenter.income.ReceiveDetailsContract;
import com.xzty.cq.tover.businessmanagement.projectmanagement.part.presenter.income.ReceiveDetailsPresenter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class PartReceiveDetailsActivity extends ActivityPresenter<ReceiveDetailsContract.Presenter> implements ReceiveDetailsContract.View {
    @BindView(R.id.tv_toolbarTitle)
    TextView tv_toolbarTitle;

    @BindView(R.id.recycle_collectorderdetiallist)
    RecyclerView recycle_collectorderdetiallist;

    @BindView(R.id.sv_collectorderdetail)
    ScrollView sv_collectorderdetail;

    private DeliverOrder deliverOrder;

    private List<DeliverDetails> deliverDetailList;

    private RecyclerView.LayoutManager mLayoutManage;

    private PartReceiveDetailsAdapter mAdapter;

    @Override
    protected int getContentLayoutId() {
        return R.layout.activity_part_receive_details;
    }

    @Override
    protected void initWidget() {
        super.initWidget();
        tv_toolbarTitle.setText("发货详情");
        Fresco.initialize(this);
    }

    @Override
    protected void initData() {
        super.initData();
        String s = getIntent().getStringExtra("outOrder");
        deliverOrder = JSON.parseObject(s, DeliverOrder.class);
        List<String> imgPath = deliverOrder.getPathList();
        if (imgPath != null) {
            List<String> imgurl = new ArrayList<String>();
            for (int i = 0; i < imgPath.size(); i++) {
                StringBuffer url = new StringBuffer(Common.Constance.getImgUrl);
                url.append(imgPath.get(i));
                imgurl.add(String.valueOf(url));
            }
            Carousel carousel = new Carousel(this, imgurl, -1);
            carousel.execute();
        }

        init();
        mPresenter.getData(deliverOrder.getOutId() + "");
    }

    private void init() {
        TextView tv_factory2 = (TextView) findViewById(R.id.tv_factory2);
        TextView tv_contract_no2 = (TextView) findViewById(R.id.tv_contract_no2);
        TextView tv_out_time2 = (TextView) findViewById(R.id.tv_out_time2);
        TextView tv_remark2 = (TextView) findViewById(R.id.tv_remark2);
        TextView tv_sender = (TextView) findViewById(R.id.tv_sender);
        TextView tv_phone_no = (TextView) findViewById(R.id.tv_phone_no);
        TextView tv_car_num = (TextView) findViewById(R.id.tv_car_num);
        TextView tv_bill_no = (TextView) findViewById(R.id.tv_bill_no);
        tv_factory2.setText(deliverOrder.getFirm() == null ? "空" : deliverOrder.getFirm());
        tv_contract_no2.setText(deliverOrder.getContractNo() == null ? "空" : deliverOrder.getContractNo());
        tv_remark2.setText(deliverOrder.getOutNote() == null ? "空" : deliverOrder.getFirm());
        tv_sender.setText(deliverOrder.getSender() == null ? "空" : deliverOrder.getSender());
        tv_phone_no.setText(deliverOrder.getSenderPhone() == null ? "空" : deliverOrder.getSenderPhone());
        tv_car_num.setText(deliverOrder.getCarNo() == null ? "空" : deliverOrder.getCarNo());
        tv_bill_no.setText(deliverOrder.getSendNumber() == null ? "空" : deliverOrder.getSendNumber());

        //解决滑动冲突
        recycle_collectorderdetiallist.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
                    sv_collectorderdetail.requestDisallowInterceptTouchEvent(false);
                } else {
                    sv_collectorderdetail.requestDisallowInterceptTouchEvent(true);
                }
                return false;
            }
        });
    }

    @Override
    public void showError(String str) {
        Toast.makeText(this, str, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void success(List<DeliverDetails> list) {
        mLayoutManage = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mAdapter = new PartReceiveDetailsAdapter(R.layout.part_receive_details_recycle_item, list);
        recycle_collectorderdetiallist.setLayoutManager(mLayoutManage);
        recycle_collectorderdetiallist.setAdapter(mAdapter);
    }

    @Override
    protected ReceiveDetailsContract.Presenter initPresenter() {
        return new ReceiveDetailsPresenter(this);
    }
}
