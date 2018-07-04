package com.xzty.cq.tover.businessmanagement.projectmanagement.part.data;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.xzty.cq.tover.businessmanagement.common.MyApplication;
import com.xzty.cq.tover.businessmanagement.common.data.DataSourse;
import com.xzty.cq.tover.businessmanagement.common.model.RspLogin;
import com.xzty.cq.tover.businessmanagement.common.model.RspModel;
import com.xzty.cq.tover.businessmanagement.common.net.NetWork;
import com.xzty.cq.tover.businessmanagement.projectmanagement.common.api.RemoteService;

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
 * Created 2018/5/15.
 * explain
 */

public class PicHelper {
    public static void proPic(final List<String> images ,final List<Bitmap> picsBitmapList, String outId, String sender, String senderPhone, String carSize, String carno, String sendNumber, final DataSourse.Callback<List<RspLogin>> callback) {
        List<MultipartBody.Part> picList = new ArrayList<>();
        final Map<String, File> picNameAndPathMap = new HashMap<>();
        MultipartBody.Builder builder = new MultipartBody.Builder().setType(MultipartBody.FORM);
        if (images != null && !images.isEmpty()) {
            int i = 1;
            File outDir = MyApplication.getInstance().getExternalFilesDir("out");
            for (String image : images) {

                File file = new File(outDir.getPath() + "/out" + i + ".jpeg");

                BitmapFactory.Options option = new BitmapFactory.Options();
                option.inJustDecodeBounds = true;
                BitmapFactory.decodeFile(image, option);
                option.inJustDecodeBounds = false;
                option.inSampleSize = 2;
                Bitmap bitmap = BitmapFactory.decodeFile(image, option);

                try {
                    BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(file));
                    bitmap.compress(Bitmap.CompressFormat.JPEG, 50, bos);
                    bos.flush();
                    bos.close();
                    builder.addFormDataPart("file[]", file.getName(), RequestBody.create(MediaType.parse("image/jpeg"), file));

                    RequestBody body = RequestBody.create(MediaType.parse("image/jpeg"), file);
                    MultipartBody.Part part = MultipartBody.Part.createFormData("file[]",
                            file.getName(), body);
                    picList.add(part);
                    picNameAndPathMap.put(file.getName(), file);
                } catch (Exception e) {
                }
                i++;
            }

        }
        RequestBody body1 = RequestBody.create(MediaType.parse("multipart/form-data"),
                outId);
        RequestBody body2 = RequestBody.create(MediaType.parse("multipart/form-data"),
                sender);
        RequestBody body3 = RequestBody.create(MediaType.parse("multipart/form-data"),
                senderPhone);
        RequestBody body4 = RequestBody.create(MediaType.parse("multipart/form-data"),
                carSize);
        RequestBody body5 = RequestBody.create(MediaType.parse("multipart/form-data"),
                carno);
        RequestBody body6 = RequestBody.create(MediaType.parse("multipart/form-data"),
                sendNumber);
        Map<String, RequestBody> maps = new HashMap<>();
        maps.put("outId", body1);
        maps.put("sender", body2);
        maps.put("senderPhone", body3);
        maps.put("carSize", body4);
        maps.put("carNo", body5);
        maps.put("sendNumber", body6);
        RemoteService service = NetWork.remote(RemoteService.class);
        Subscription subscription = service.shipments(picList, maps).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<RspModel<List<RspLogin>>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                callback.onDataNotAvailable("请求失败" + e.getMessage());
            }

            @Override
            public void onNext(RspModel<List<RspLogin>> listRspModel) {
                int code = listRspModel.getBackcode();
                if (code == 0) {
                    callback.onDataNotAvailable("请求错误");
                } else {
                    for (String key : picNameAndPathMap.keySet()) {
                        picNameAndPathMap.get(key).delete();
                    }
                    picsBitmapList.clear();
                    if (images != null)
                        images.clear();
                    for (String key : picNameAndPathMap.keySet()) {
                        picNameAndPathMap.get(key).delete();
                    }
                    picsBitmapList.clear();
                    if (images != null)
                        images.clear();
                    callback.onDataLoaded(listRspModel.getData());
                }
            }
        });
    }
}
