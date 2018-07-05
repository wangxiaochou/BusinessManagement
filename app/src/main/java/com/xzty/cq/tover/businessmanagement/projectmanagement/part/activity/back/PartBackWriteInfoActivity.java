package com.xzty.cq.tover.businessmanagement.projectmanagement.part.activity.back;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
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
import com.xzty.cq.tover.businessmanagement.projectmanagement.part.adapter.PartBackWriteInfoAdapter;
import com.xzty.cq.tover.businessmanagement.projectmanagement.part.model.back.BackPart;
import com.xzty.cq.tover.businessmanagement.projectmanagement.part.presenter.back.BackWriteInfoContract;
import com.xzty.cq.tover.businessmanagement.projectmanagement.part.presenter.back.BackWriteInfoPresenter;
import com.xzty.cq.tover.businessmanagement.projectmanagement.view.MapContainer;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class PartBackWriteInfoActivity extends ActivityPresenter<BackWriteInfoContract.Presenter>
        implements BackWriteInfoContract.View, View.OnClickListener, AMap.OnMapLongClickListener
        , AMapLocationListener, BaseQuickAdapter.OnItemChildClickListener {
    @BindView(R.id.tv_toolbarTitle)
    TextView tv_toolbarTitle;

    @BindView(R.id.sv_backpartcountset)
    ScrollView sv_backpartcountset;

    @BindView(R.id.et_backpartcountset_backname)
    EditText et_backpartcountset_backname;

    @BindView(R.id.et_backpartcountset_note)
    EditText et_backpartcountset_note;

    @BindView(R.id.map_container_backpartcountset)
    MapContainer map_container_backpartcountset;

    @BindView(R.id.map_backpartcountset)
    MapView map_backpartcountset;

    @BindView(R.id.btn_backpartcountset_upload)
    Button btn_backpartcountset_upload;

    @BindView(R.id.btn_backpartcountset_submit)
    Button btn_backpartcountset_submit;

    @BindView(R.id.gv_backpartcountset_pic)
    GridView gv_backpartcountset_pic;

    @BindView(R.id.recycle_backpartcountset)
    RecyclerView recycle_backpartcountset;

    private DecimalFormat df = new DecimalFormat("#######.#######");
    private DecimalFormat doubleToString = new DecimalFormat("#################.##");

    private double[] maxPickCount;
    private List<BackPart> backPartList;

    private ArrayList<String> images = new ArrayList<>();//选择的图片地址
    private List<Bitmap> bitmapList;

    //当前位置图标
    private MyLocationStyle myLocationStyle;
    private UiSettings mUiSettings;
    //声明mLocationOption对象
    public AMapLocationClientOption mLocationOption = null;
    //声明AMapLocationClient类对象
    public AMapLocationClient mLocationClient = null;

    private AMap aMap;
    private List<Marker> markerList = new ArrayList<>();

    private RecyclerView.LayoutManager mLayoutManage;

    private PartBackWriteInfoAdapter mAdapter;

    private final int PART_CODE = 100;

    private AlertDialog show;

    @Override
    protected int getContentLayoutId() {
        return R.layout.activity_part_back_write_info;
    }

    @Override
    protected void initWidget() {
        super.initWidget();
        tv_toolbarTitle.setText("退回信息填写");
        map_backpartcountset.onCreate(getIntent().getExtras());
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
    public void setRspData(EventData<BackPart> eventData){
        backPartList = eventData.getEventData();
        maxPickCount = new double[backPartList.size()];
        for (int i = 0; i < backPartList.size(); i++) {
            maxPickCount[i] = backPartList.get(i).getPickDetailCount();
        }
        EventBus.getDefault().removeStickyEvent(eventData);
    }

    @Override
    protected void initData() {
        super.initData();
        map_container_backpartcountset.setScrollView(sv_backpartcountset);
        fix();
        //初始化地图控制器对象
        if (aMap == null) {
            aMap = map_backpartcountset.getMap();
        }
        initMap();
        initLocation();
        setAdapter();
        btn_backpartcountset_upload.setOnClickListener(this);
        btn_backpartcountset_submit.setOnClickListener(this);
    }

    private void setAdapter() {
        mLayoutManage = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recycle_backpartcountset.setLayoutManager(mLayoutManage);
        mAdapter = new PartBackWriteInfoAdapter(R.layout.part_back_writeinfo_recycle_item, backPartList);
        recycle_backpartcountset.setAdapter(mAdapter);
        mAdapter.setOnItemChildClickListener(this);
    }

    private void initMap() {
        myLocationStyle = new MyLocationStyle();
        myLocationStyle.showMyLocation(true);
        myLocationStyle.myLocationIcon(BitmapDescriptorFactory.fromResource(R.drawable.myself_location_1));//设置当前位置图标
        aMap.setMyLocationEnabled(true);
        //设置定位点的范围为透明
        myLocationStyle.strokeColor(Color.argb(0, 0, 0, 255));// 设置圆形的边框颜色
        myLocationStyle.radiusFillColor(Color.argb(0, 0, 200, 0));// 设置圆形的填充颜色
        myLocationStyle.myLocationType(MyLocationStyle.LOCATION_TYPE_LOCATE);//定位一次，且将视角移动到地图中心点。
        aMap.setMyLocationStyle(myLocationStyle);
        mUiSettings = aMap.getUiSettings();//实例化UiSettings类对象
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

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_backpartcountset_upload:
                gv_backpartcountset_pic.setVisibility(View.VISIBLE);
                ImageSelectorUtils.openPhoto(this, PART_CODE);
                break;

            case R.id.btn_backpartcountset_submit:
                String pickName = et_backpartcountset_backname.getText().toString().trim();
                String pickNote = et_backpartcountset_note.getText().toString().trim();
                String pickListString = JSON.toJSONString(backPartList);
                mPresenter.submit(images,bitmapList,pickName,markerList,pickNote,pickListString);
                break;
        }
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
        markerList.add(aMap.addMarker(new MarkerOptions().position(latLng).title("退回地点").snippet("经度:" + doubleToString.format(latLng.longitude) + ",纬度:" + doubleToString.format(latLng.latitude)).draggable(true)));
    }

    private void fix() {
        //解决滑动冲突
        recycle_backpartcountset.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
                    sv_backpartcountset.requestDisallowInterceptTouchEvent(false);
                } else {
                    sv_backpartcountset.requestDisallowInterceptTouchEvent(true);
                }
                return false;
            }
        });
    }

    @Override
    protected BackWriteInfoContract.Presenter initPresenter() {
        return new BackWriteInfoPresenter(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        //在activity执行onDestroy时执行mMapView.onDestroy()，销毁地图
        map_backpartcountset.onDestroy();
    }

    @Override
    protected void onResume() {
        super.onResume();
        //在activity执行onResume时执行mMapView.onResume ()，重新绘制加载地图
        map_backpartcountset.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        //在activity执行onPause时执行mMapView.onPause ()，暂停地图的绘制
        map_backpartcountset.onPause();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        //在activity执行onSaveInstanceState时执行mMapView.onSaveInstanceState (outState)，保存地图当前的状态
        map_backpartcountset.onSaveInstanceState(outState);
    }

    @Override
    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
        switch (view.getId()) {
            case R.id.item_iv_backpartcountset_minus:
                mPresenter.subtract(backPartList, position);
                break;

            case R.id.item_iv_backpartcountset_add:
                mPresenter.add(backPartList, position, maxPickCount);
                break;
        }
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
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setCancelable(false);
        dialog.setMessage("保存中...");
        show = dialog.show();
    }

    @Override
    public void clickBack(double needCount, int position) {
        backPartList.get(position).setPickDetailCount(needCount);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void picBack(ArrayList<String> images1, List<Bitmap> bitmapList1) {
        bitmapList = bitmapList1;
        images = images1;
        gv_backpartcountset_pic.setAdapter(new GirdPicItemAdapter(bitmapList, this));
    }

    @Override
    public void saveSuccess() {
        if(show!=null){
            show.dismiss();
        }
        Toast.makeText(this,"保存成功",Toast.LENGTH_SHORT).show();
        int resultCode = 51;
        Intent data = new Intent();
        data.putExtra("backCount", JSON.toJSONString(backPartList));
        setResult(resultCode, data);
        finish();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PART_CODE && data != null) {
            ImageView imageView = findViewById(R.id.iv_100dp);
            mPresenter.proPic(images,imageView,data);
        }
    }
}
