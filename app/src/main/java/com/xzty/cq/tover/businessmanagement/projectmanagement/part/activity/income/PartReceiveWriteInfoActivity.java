package com.xzty.cq.tover.businessmanagement.projectmanagement.part.activity.income;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
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
import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.maps.AMap;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.MapView;
import com.amap.api.maps.UiSettings;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.Marker;
import com.amap.api.maps.model.MarkerOptions;
import com.amap.api.maps.model.MyLocationStyle;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.donkingliang.imageselector.utils.ImageSelectorUtils;
import com.xzty.cq.tover.businessmanagement.R;
import com.xzty.cq.tover.businessmanagement.common.MyApplication;
import com.xzty.cq.tover.businessmanagement.common.eventbus.EventData;
import com.xzty.cq.tover.businessmanagement.common.factory.ActivityPresenter;
import com.xzty.cq.tover.businessmanagement.projectmanagement.part.adapter.GirdPicItemAdapter;
import com.xzty.cq.tover.businessmanagement.projectmanagement.part.adapter.PartReceiveWriteInfoAdapter;
import com.xzty.cq.tover.businessmanagement.projectmanagement.part.model.deliver.DeliverDetails;
import com.xzty.cq.tover.businessmanagement.projectmanagement.part.model.deliver.DeliverOrder;
import com.xzty.cq.tover.businessmanagement.projectmanagement.part.presenter.income.ReceiveWriteInfoContract;
import com.xzty.cq.tover.businessmanagement.projectmanagement.part.presenter.income.ReceiveWriteInfoPresenter;
import com.xzty.cq.tover.businessmanagement.projectmanagement.utils.DateUtil;
import com.xzty.cq.tover.businessmanagement.projectmanagement.view.MapContainer;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class PartReceiveWriteInfoActivity extends ActivityPresenter<ReceiveWriteInfoContract.Presenter> implements ReceiveWriteInfoContract.View, View.OnClickListener, AMap.OnMapLongClickListener, AMapLocationListener, BaseQuickAdapter.OnItemChildClickListener {
    @BindView(R.id.tv_toolbarTitle)
    TextView tv_toolbarTitle;

    @BindView(R.id.gv_collectfillin_supplypic)
    GridView gv_collectfillin_supplypic;

    @BindView(R.id.gv_collectfillin_partpic)
    GridView gv_collectfillin_partpic;

    @BindView(R.id.btn_collectfillin_supplypic)
    Button btn_collectfillin_supplypic;

    @BindView(R.id.btn_collectfillin_partpic)
    Button btn_collectfillin_partpic;

    @BindView(R.id.btn_collectfillin_submit)
    Button btn_collectfillin_submit;

    @BindView(R.id.sv_collectfillin)
    ScrollView sv_collectfillin;

    @BindView(R.id.map_container_collectfillin)
    MapContainer map_container_collectfillin;

    @BindView(R.id.tv_collectfillin_outno)
    TextView tv_collectfillin_outno;

    @BindView(R.id.recycle_collectfillin)
    RecyclerView recycle_collectfillin;

    @BindView(R.id.tv_collectfillin_firm)
    TextView tv_collectfillin_firm;

    @BindView(R.id.tv_collectfillin_contractno)
    TextView tv_collectfillin_contractno;

    @BindView(R.id.tv_collectfillin_delivdate)
    TextView tv_collectfillin_delivdate;

    @BindView(R.id.map_collectfillin)
    MapView mMapView;

    @BindView(R.id.et_collectfillin_note)
    EditText et_collectfillin_note;

    private String intentStringExtra;  //选择的构件

    private String oostring;  //发货单信息

    private DeliverOrder outOrder;

    private List<DeliverDetails> deliverDetailList = new ArrayList<>();

    //当前位置图标
    private MyLocationStyle myLocationStyle;
    private UiSettings mUiSettings;
    //声明mLocationOption对象
    public AMapLocationClientOption mLocationOption = null;
    //声明AMapLocationClient类对象
    public AMapLocationClient mLocationClient = null;
    private AMap aMap;
    private List<Marker> markerList = new ArrayList<>();

    private DecimalFormat df = new DecimalFormat("#######.#######");
    private DecimalFormat doubleToString = new DecimalFormat("#################.##");

    private RecyclerView.LayoutManager mLayoutManage;

    private PartReceiveWriteInfoAdapter mAdapter;

    private final int SUPPLY_CODE = 100;
    private final int PART_CODE = 200;

    private ArrayList<String> supplyImages = new ArrayList<>();//选择的图片地址 供货单
    private List<Bitmap> supplyBitmapList;
    private ArrayList<String> partImages = new ArrayList<>();//选择的图片地址 物品
    private List<Bitmap> partBitmapList;

    private AlertDialog.Builder dialog;

    private AlertDialog show;

    @Override
    protected int getContentLayoutId() {
        return R.layout.activity_part_receive_write_info;
    }

    @Override
    protected void initWidget() {
        super.initWidget();
        tv_toolbarTitle.setText("收货信息填写");
        mMapView.onCreate(getIntent().getExtras());
    }

    @Override
    protected void initData() {
        super.initData();
        oostring = getIntent().getStringExtra("deliverOrder");//发货单信息
        outOrder = JSON.parseObject(oostring, DeliverOrder.class);
        //初始化地图控制器对象
        if (aMap == null) {
            aMap = mMapView.getMap();
        }
        init();
        initMap();
        initLocation();
        intListener();
        setAdapter();
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
     * @param eventData
     */
    @Subscribe(threadMode = ThreadMode.MAIN,sticky = true)
    public void setRspData(EventData<DeliverDetails> eventData){
        deliverDetailList = eventData.getEventData();
        for (DeliverDetails details : deliverDetailList) {
            details.isCheck = false;
        }
        EventBus.getDefault().removeStickyEvent(eventData);
    }

    private void setAdapter() {
        mLayoutManage = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recycle_collectfillin.setLayoutManager(mLayoutManage);
        mAdapter = new PartReceiveWriteInfoAdapter(R.layout.part_receive_writeinfo_recycle_item, deliverDetailList);
        recycle_collectfillin.setAdapter(mAdapter);
        mAdapter.setOnItemChildClickListener(this);
    }

    private void intListener() {
        btn_collectfillin_supplypic.setOnClickListener(this);
        btn_collectfillin_partpic.setOnClickListener(this);
        btn_collectfillin_submit.setOnClickListener(this);
        map_container_collectfillin.setScrollView(sv_collectfillin);
    }

    private void initLocation() {
        mUiSettings.setMyLocationButtonEnabled(true); //显示默认的定位按钮
        mUiSettings.setZoomControlsEnabled(false);
        mUiSettings.setCompassEnabled(false);//隐藏指南
        aMap.setMyLocationEnabled(true);// 可触发定位并显示当前位置
        //初始化定位
        mLocationClient = new AMapLocationClient(MyApplication.getInstance());
        //设置定位回调监听
        mLocationClient.setLocationListener(this);
        //初始化定位参数
        mLocationOption = new AMapLocationClientOption();
        //设置定位模式为高精度模式，Battery_Saving为低功耗模式，Device_Sensors是仅设备模式
        mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
        //设置是否返回地址信息（默认返回地址信息）
        mLocationOption.setNeedAddress(true);
        //设置是否只定位一次,默认为false
        mLocationOption.setOnceLocation(true);
        //设置是否强制刷新WIFI，默认为强制刷新
        mLocationOption.setWifiActiveScan(true);
        //设置是否允许模拟位置,默认为false，不允许模拟位置
        mLocationOption.setMockEnable(false);
        //设置定位间隔,单位毫秒,默认为2000ms
        mLocationOption.setInterval(2000);
        //给定位客户端对象设置定位参数
        mLocationClient.setLocationOption(mLocationOption);
        //设置定位超时时间
        mLocationOption.setHttpTimeOut(100000);
        mLocationClient.startLocation();
        aMap.setOnMapLongClickListener(this);
    }

    private void initMap() {
        myLocationStyle = new MyLocationStyle();
        //设置显示当前定位图标
        myLocationStyle.showMyLocation(true);
        myLocationStyle.myLocationIcon(BitmapDescriptorFactory.fromResource(R.drawable.myself_location_1));//设置当前位置图标
        aMap.setMyLocationEnabled(true);
        //设置定位点的范围为透明
        myLocationStyle.strokeColor(Color.argb(0, 150, 150, 255));// 设置圆形的边框颜色
        myLocationStyle.radiusFillColor(Color.argb(0, 42, 200, 152));// 设置圆形的填充颜色
        myLocationStyle.myLocationType(MyLocationStyle.LOCATION_TYPE_LOCATE);//定位一次，且将视角移动到地图中心点。
        aMap.setMyLocationStyle(myLocationStyle);
        mUiSettings = aMap.getUiSettings();//实例化UiSettings类对象
    }

    private void init() {
        TextView tv_collectfillin_firm = (TextView) findViewById(R.id.tv_collectfillin_firm);
        tv_collectfillin_firm.setText((outOrder.getFirm() == null || outOrder.getFirm().equals("")) ? "空" :
                outOrder.getFirm());
        TextView tv_collectfillin_contractno = (TextView) findViewById(R.id.tv_collectfillin_contractno);
        tv_collectfillin_contractno.setText((outOrder.getContractNo() == null || outOrder.getContractNo().equals
                ("")) ? "空" : outOrder.getContractNo());
        TextView tv_collectfillin_delivdate = (TextView) findViewById(R.id.tv_collectfillin_delivdate);
        tv_collectfillin_delivdate.setText(outOrder.getExpectOutTime() == null ? "空" : DateUtil.dateToString
                (outOrder.getExpectOutTime()));
        TextView tv_collectfillin_note = (TextView) findViewById(R.id.tv_collectfillin_note);
        tv_collectfillin_note.setText((outOrder.getOutNote() == null || outOrder.getOutNote().equals("")) ? "空"
                : outOrder.getOutNote());
        TextView tv_collectfillin_sender = (TextView) findViewById(R.id.tv_collectfillin_sender);
        tv_collectfillin_sender.setText((outOrder.getSender() == null || outOrder.getSender().equals("")) ? "空"
                : outOrder.getSender());
        TextView tv_collectfillin_senderphone = (TextView) findViewById(R.id.tv_collectfillin_senderphone);
        tv_collectfillin_senderphone.setText((outOrder.getSenderPhone() == null || outOrder.getSenderPhone()
                .equals("")) ? "空" : outOrder.getSenderPhone());
        TextView tv_collectfillin_carsize = (TextView) findViewById(R.id.tv_collectfillin_carsize);
        tv_collectfillin_carsize.setText((outOrder.getCarSize() == null || outOrder.getCarSize().equals("")) ?
                "空" : outOrder.getCarSize());
        TextView tv_collectfillin_carno = (TextView) findViewById(R.id.tv_collectfillin_carno);
        tv_collectfillin_carno.setText((outOrder.getCarNo() == null || outOrder.getCarNo().equals("")) ? "空" :
                outOrder.getCarNo());
        TextView tv_collectfillin_deliveno = (TextView) findViewById(R.id.tv_collectfillin_deliveno);
        tv_collectfillin_deliveno.setText((outOrder.getSendNumber() == null || outOrder.getSendNumber().equals
                ("")) ? "空" : outOrder.getSendNumber());
        TextView tv_collectfillin_delivetime = (TextView) findViewById(R.id.tv_collectfillin_delivetime);
        tv_collectfillin_delivetime.setText(outOrder.getOutTime() == null ? "空" : DateUtil.dateToString(outOrder
                .getOutTime()));


        tv_collectfillin_outno.setText(outOrder.getOutNo());

        //解决滑动冲突

        recycle_collectfillin.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
                    sv_collectfillin.requestDisallowInterceptTouchEvent(false);
                } else {
                    sv_collectfillin.requestDisallowInterceptTouchEvent(true);
                }

                return false;
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_collectfillin_supplypic:
                //上传供货单图片
                gv_collectfillin_supplypic.setVisibility(View.VISIBLE);
                //不限数量的多选
                ImageSelectorUtils.openPhoto(this, SUPPLY_CODE);
                break;
            case R.id.btn_collectfillin_partpic:
                //上传货物图片
                gv_collectfillin_partpic.setVisibility(View.VISIBLE);
                ImageSelectorUtils.openPhoto(this, PART_CODE);
                break;
            case R.id.btn_collectfillin_submit:
                String note = et_collectfillin_note.getText().toString().trim();
                mPresenter.cargeSava(markerList, supplyImages, partImages, deliverDetailList, outOrder, note);
                break;
        }
    }


    @Override
    protected ReceiveWriteInfoContract.Presenter initPresenter() {
        return new ReceiveWriteInfoPresenter(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        //在activity执行onDestroy时执行mMapView.onDestroy()，销毁地图
        mMapView.onDestroy();
    }

    @Override
    protected void onResume() {
        super.onResume();
        //在activity执行onResume时执行mMapView.onResume ()，重新绘制加载地图
        mMapView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        //在activity执行onPause时执行mMapView.onPause ()，暂停地图的绘制
        mMapView.onPause();
    }


    @Override
    public void onLocationChanged(AMapLocation aMapLocation) {
        LatLng latLng = new LatLng(aMapLocation.getLatitude(), aMapLocation.getLongitude());
        aMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 17));
        String s = aMapLocation.getAddress();
        Log.e("TAG", "定位成功=" + s + aMapLocation.getErrorCode());
    }

    @Override
    public void onMapLongClick(LatLng latLng) {
        if (!markerList.isEmpty()) {
            for (Marker marker : markerList) {
                marker.remove();
            }
            markerList.clear();
        }
        markerList.add(aMap.addMarker(new MarkerOptions().position(latLng).title("收货地点").snippet("经度:" + doubleToString.format(latLng.longitude) + ",纬度:" + doubleToString.format(latLng.latitude)).draggable(true)));
    }

    @Override
    public void showError(String str) {
        if (show != null) {
            show.dismiss();
        }
        Toast.makeText(this, str, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void subtracBack(List<DeliverDetails> list1) {
        deliverDetailList = list1;
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void addBack(List<DeliverDetails> list1) {
        deliverDetailList = list1;
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void supplyBack(ArrayList<String> supplyImages1, List<Bitmap> supplyBitmapList1) {
        supplyImages = supplyImages1;
        supplyBitmapList = supplyBitmapList1;
        gv_collectfillin_supplypic.setAdapter(new GirdPicItemAdapter(supplyBitmapList, this));
    }

    @Override
    public void partBack(ArrayList<String> partImages1, List<Bitmap> partBitmapList1) {
        partImages = partImages1;
        partBitmapList = partBitmapList1;
        gv_collectfillin_partpic.setAdapter(new GirdPicItemAdapter(partBitmapList, this));
    }

    @Override
    public void saveBack() {
        show.dismiss();
        Toast.makeText(this, "保存成功", Toast.LENGTH_SHORT).show();
        MyApplication.finishAssignActivity(PartReceiveChooseActivity.class);
        finish();
    }

    @Override
    public void showDialog() {
        dialog = new AlertDialog.Builder(this);
        dialog.setCancelable(false);
        dialog.setMessage("保存中...");
        show = dialog.show();
    }

    @Override
    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
        switch (view.getId()) {
            case R.id.item_iv_collectfillin_minus:
                mPresenter.subtract(deliverDetailList, position);
                break;
            case R.id.item_iv_collectfillin_add:
                mPresenter.add(deliverDetailList, position);
                break;
            case R.id.item_btn_collectfillin_checkone:
                Intent intent = new Intent(this,PartReceiveFeedBackActivity.class);
                DeliverDetails details = deliverDetailList.get(position);
                intent.putExtra("outOrder", oostring);
                intent.putExtra("position", position);
                intent.putExtra("problemOutDetail", JSON.toJSONString(details));
                startActivityForResult(intent, 50);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == SUPPLY_CODE && data != null) {
            ImageView imageView = findViewById(R.id.iv_100dp);
            mPresenter.supplyPic(supplyImages, supplyBitmapList, imageView, data);
        }
        if (requestCode == PART_CODE && data != null) {
            ImageView imageView = findViewById(R.id.iv_100dp);
            mPresenter.partPick(partImages, partBitmapList, imageView, data);
        }
        if (resultCode == 51) {
            double backCount = data.getDoubleExtra("backCount", -1.1);
            int position = data.getIntExtra("position", -1);
            DeliverDetails outDetail = deliverDetailList.get(position);
            Double needCount = outDetail.getResidualQuantity();
            outDetail.setResidualQuantity(needCount-backCount);
            mAdapter.notifyDataSetChanged();
        }
    }

}
