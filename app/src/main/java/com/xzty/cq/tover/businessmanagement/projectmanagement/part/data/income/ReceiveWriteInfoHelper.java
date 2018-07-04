package com.xzty.cq.tover.businessmanagement.projectmanagement.part.data.income;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.ImageView;

import com.alibaba.fastjson.JSON;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.Marker;
import com.donkingliang.imageselector.utils.ImageSelectorUtils;
import com.xzty.cq.tover.businessmanagement.common.MyApplication;
import com.xzty.cq.tover.businessmanagement.common.data.DataSourse;
import com.xzty.cq.tover.businessmanagement.common.factory.Account;
import com.xzty.cq.tover.businessmanagement.common.model.RspLogin;
import com.xzty.cq.tover.businessmanagement.common.model.RspModel;
import com.xzty.cq.tover.businessmanagement.common.net.NetWork;
import com.xzty.cq.tover.businessmanagement.projectmanagement.common.api.RemoteService;
import com.xzty.cq.tover.businessmanagement.projectmanagement.part.model.deliver.DeliverDetails;
import com.xzty.cq.tover.businessmanagement.projectmanagement.part.model.deliver.DeliverOrder;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * author zzl
 * Created 2018/6/12.
 * explain
 */

public class ReceiveWriteInfoHelper {
    public static List<DeliverDetails> subtract(List<DeliverDetails> list, int position) {
        DeliverDetails details = list.get(position);
        double needCount = details.getResidualQuantity();
        if (needCount > 1) {
            needCount--;
            list.get(position).setResidualQuantity(needCount);
            return list;
        } else {
            return null;
        }
    }

    public static List<DeliverDetails> add(List<DeliverDetails> list, int position) {
        DeliverDetails details = list.get(position);
        double needCount = details.getResidualQuantity();
        needCount++;
        list.get(position).setResidualQuantity(needCount);
        return list;
    }

    public static ReceiveWriteInfoImageModel supplyPic(ArrayList<String> supplyImages, List<Bitmap> supplyBitmapList, ImageView imageView, Intent data) {
        ReceiveWriteInfoImageModel model = new ReceiveWriteInfoImageModel();

        if (supplyImages != null && !supplyImages.isEmpty()) {
            supplyImages.clear();
        }
        supplyImages = data.getStringArrayListExtra(ImageSelectorUtils.SELECT_RESULT);
        supplyBitmapList = new ArrayList<>();
        for (String s : supplyImages) {
            BitmapFactory.Options option = new BitmapFactory.Options();
            option.inJustDecodeBounds = true;
            BitmapFactory.decodeFile(s, option);
            int iw = option.outWidth;
            int ih = option.outHeight;
            int vw = imageView.getWidth();
            int vh = imageView.getHeight();

            int scaleFactor = Math.min(iw / vw, ih / vh);
            option.inJustDecodeBounds = false;
            option.inSampleSize = scaleFactor;
            Bitmap bm = BitmapFactory.decodeFile(s, option);
            supplyBitmapList.add(bm);
        }

        model.setSupplyBitmapList(supplyBitmapList);
        model.setSupplyImages(supplyImages);
        return model;
    }

    public static void cargeSava(List<Marker> markerList, ArrayList<String> supplyImages, ArrayList<String> partImages, List<DeliverDetails> deliverDetailList, DeliverOrder deliverOrder, String collectNote, final DataSourse.Callback<RspLogin> callback){
        final Map<String, File> picNameAndPathMap = new HashMap<>();
        List<MultipartBody.Part> mList = new ArrayList<>();
        if (!supplyImages.isEmpty()) {
            int i = 1;
            File outDir = MyApplication.getInstance().getExternalFilesDir("supply");
            for (String image : supplyImages) {

                File file1 = new File(outDir.getPath() + "/supply" + i + ".jpeg");

                BitmapFactory.Options option = new BitmapFactory.Options();
                option.inJustDecodeBounds = true;
                BitmapFactory.decodeFile(image, option);
                option.inJustDecodeBounds = false;
                option.inSampleSize = 2;
                Bitmap bitmap = BitmapFactory.decodeFile(image, option);

                try {
                    BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(file1));
                    bitmap.compress(Bitmap.CompressFormat.JPEG, 50, bos);
                    bos.flush();
                    bos.close();

                    RequestBody body = RequestBody.create(MediaType.parse("image/jpeg"), file1);
                    MultipartBody.Part part = MultipartBody.Part.createFormData("supply[]",
                            file1.getName(), body);
                    mList.add(part);
                    picNameAndPathMap.put(file1.getName(), file1);
                } catch (Exception e) {
                    //msg.what = -1;
                    //Toast.makeText(this, e.toString(), Toast.LENGTH_SHORT).show();
                }
                i++;
            }//循环压缩成保存为file,并得到file的路径
        }//image不为空

        if (!partImages.isEmpty()) {
            int i = 1;
            File outDir = MyApplication.getInstance().getExternalFilesDir("part");
            for (String image : partImages) {

                File file2 = new File(outDir.getPath() + "/part" + i + ".jpeg");
                BitmapFactory.Options option = new BitmapFactory.Options();
                option.inJustDecodeBounds = true;
                BitmapFactory.decodeFile(image, option);
                option.inJustDecodeBounds = false;
                option.inSampleSize = 2;
                Bitmap bitmap = BitmapFactory.decodeFile(image, option);

                try {
                    BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(file2));
                    bitmap.compress(Bitmap.CompressFormat.JPEG, 50, bos);
                    bos.flush();
                    bos.close();
                    RequestBody body = RequestBody.create(MediaType.parse("image/jpeg"), file2);
                    MultipartBody.Part parts = MultipartBody.Part.createFormData("collectPic[]", file2.getName(), body);
                    mList.add(parts);
                    picNameAndPathMap.put(file2.getName(), file2);
                } catch (Exception e) {
                }
                i++;
            }
        }//image不为空
        Account.load(MyApplication.getInstance());
        String projectId = Account.getProjectId();
        String collectUserId = Account.getemployId();
        Marker marker = markerList.get(0);
        LatLng markerPosition = marker.getPosition();
        int isProblem = 0;
        for (DeliverDetails outDetail : deliverDetailList) {
            if (outDetail.isCheck) {
                isProblem = 1;
                outDetail.feedBackState = 1;
            }
        }
        //刘川 改 2018.1.18 web端接收的applyItemCount
        for (DeliverDetails outDetail : deliverDetailList) {
            outDetail.setApplyItemCount(outDetail.getResidualQuantity());
        }

        String collectList = JSON.toJSONString(deliverDetailList);
        RequestBody body1 = RequestBody.create(MediaType.parse("multipart/form-data"), collectList);
        RequestBody body2 = RequestBody.create(MediaType.parse("multipart/form-data"), deliverOrder.getOutId().toString());
        RequestBody body3 = RequestBody.create(MediaType.parse("multipart/form-data"), projectId);
        RequestBody body4 = RequestBody.create(MediaType.parse("multipart/form-data"), collectUserId);
        RequestBody body5 = RequestBody.create(MediaType.parse("multipart/form-data"), collectNote);
        RequestBody body6 = RequestBody.create(MediaType.parse("multipart/form-data"), isProblem + "");
        RequestBody body7 = RequestBody.create(MediaType.parse("multipart/form-data"), String.valueOf(markerPosition.longitude)
        );
        RequestBody body8 = RequestBody.create(MediaType.parse("multipart/form-data"), String.valueOf(markerPosition.latitude));
        Map field = new HashMap();
        field.put("collectList", body1);
        field.put("outId", body2);
        field.put("projectId", body3);
        field.put("collectUserId", body4);
        field.put("collectNote", body5);
        field.put("isProblem", body6);
        field.put("collectLongitude", body7);
        field.put("collectLatitude", body8);
        RemoteService service = NetWork.remote(RemoteService.class);
        Subscription subscription = service.cargeSave(mList, field).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<RspModel<RspLogin>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
               callback.onDataNotAvailable("请求失败" + e.getMessage());
            }

            @Override
            public void onNext(RspModel<RspLogin> listRspModel) {
                int code = listRspModel.getBackcode();
                if (code == 0) {
                callback.onDataNotAvailable("请求错误了");
                } else {
                    callback.onDataLoaded(listRspModel.getData());
                }
            }
        });

    }

}
