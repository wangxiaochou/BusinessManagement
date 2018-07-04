package com.xzty.cq.tover.businessmanagement.projectmanagement.part.data.income;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.ImageView;

import com.donkingliang.imageselector.utils.ImageSelectorUtils;
import com.xzty.cq.tover.businessmanagement.common.MyApplication;
import com.xzty.cq.tover.businessmanagement.common.data.DataSourse;
import com.xzty.cq.tover.businessmanagement.common.model.RspLogin;
import com.xzty.cq.tover.businessmanagement.common.model.RspModel;
import com.xzty.cq.tover.businessmanagement.common.net.NetWork;
import com.xzty.cq.tover.businessmanagement.projectmanagement.common.api.RemoteService;
import com.xzty.cq.tover.businessmanagement.projectmanagement.part.model.deliver.DeliverDetails;

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
 * Created 2018/6/11.
 * explain
 */

public class ReceiveFeedBackHelper {

    public static DeliverDetails subtract(DeliverDetails deliverDetails) {
        Double count = deliverDetails.getResidualQuantity();
        if (count > 1) {
            count--;
            deliverDetails.setResidualQuantity(count);
            return deliverDetails;
        } else {
            return null;
        }
    }

    public static DeliverDetails add(DeliverDetails deliverDetails) {
        Double count = deliverDetails.getResidualQuantity();
        double total = deliverDetails.getApplyItemCount();
        if (total == count) {
            return null;
        } else {
            count++;
            deliverDetails.setResidualQuantity(count);
            return deliverDetails;
        }
    }

    public static ReceiveFeedBackPicModel proPic(ArrayList<String> images, List<Bitmap> picsBitmapList, ImageView imageView, Intent data) {
        //获取选择器返回的数据
        if (images != null && !images.isEmpty()) {
            images.clear();
        }
        images = data.getStringArrayListExtra(ImageSelectorUtils.SELECT_RESULT);
        picsBitmapList = new ArrayList<>();
        for (String s : images) {
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
            picsBitmapList.add(bm);
        }
        ReceiveFeedBackPicModel model = new ReceiveFeedBackPicModel();
        model.setImages(images);
        model.setPicsBitmapList(picsBitmapList);
        return model;
    }

    public static void save(final ArrayList<String> images, final List<Bitmap> picsBitmapList, String outDetailId, String applyItemCount, String backReason, String backDutyDept, final DataSourse.Callback<RspLogin> callback) {
        final List<MultipartBody.Part> mList = new ArrayList<>();
        final Map<String, File> picNameAndPathMap = new HashMap<>();
        MultipartBody.Builder builder = new MultipartBody.Builder().setType(MultipartBody.FORM);
        if (!images.isEmpty()) {
            int i = 1;
            File outDir = MyApplication.getInstance().getExternalFilesDir("feedbackOne");
            for (String image : images) {

                File file = new File(outDir.getPath() + "/feedbackOne" + i + ".png");

                BitmapFactory.Options option = new BitmapFactory.Options();
                option.inJustDecodeBounds = true;
                BitmapFactory.decodeFile(image, option);
                option.inJustDecodeBounds = false;
                option.inSampleSize = 2;
                Bitmap bitmap = BitmapFactory.decodeFile(image, option);

                try {
                    BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(file));
                    bitmap.compress(Bitmap.CompressFormat.PNG, 50, bos);
                    bos.flush();
                    bos.close();
                    RequestBody body = RequestBody.create(MediaType.parse("image/png"), file);
                    MultipartBody.Part part = MultipartBody.Part.createFormData("feedBackPic[]", file.getName(), body);
                    mList.add(part);
                    picNameAndPathMap.put(file.getName(), file);
                } catch (Exception e) {
                }
                i++;
            }//循环压缩成保存为file,并得到file的路径
        }//image不为空

        RequestBody b = RequestBody.create(MediaType.parse("multipart/form-data"), outDetailId);
        RequestBody b2 = RequestBody.create(MediaType.parse("multipart/form-data"), applyItemCount);
        RequestBody b3 = RequestBody.create(MediaType.parse("multipart/form-data"), backReason);
        RequestBody b4 = RequestBody.create(MediaType.parse("multipart/form-data"), backDutyDept);
        Map<String, RequestBody> sMap = new HashMap<>();
        sMap.put("outDetailId", b);
        sMap.put("applyItemCount", b2);
        sMap.put("backReason", b3);
        sMap.put("backDutyDept", b4);
        RemoteService service = NetWork.remote(RemoteService.class);
        Subscription subscription = service.partFeedBack(mList, sMap).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<RspModel<RspLogin>>() {
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
                if (code == 1) {
                    for (String key : picNameAndPathMap.keySet()) {
                        picNameAndPathMap.get(key).delete();
                    }
                    picsBitmapList.clear();
                    images.clear();
                    for (String key : picNameAndPathMap.keySet()) {
                        picNameAndPathMap.get(key).delete();
                    }
                    picsBitmapList.clear();
                    images.clear();
                    callback.onDataLoaded(listRspModel.getData());
                } else {
                    callback.onDataNotAvailable("请求错误");
                }

            }
        });
    }

}
