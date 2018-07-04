package com.xzty.cq.tover.businessmanagement.projectmanagement.part.data.back;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.amap.api.maps.model.Marker;
import com.xzty.cq.tover.businessmanagement.common.MyApplication;
import com.xzty.cq.tover.businessmanagement.common.data.DataSourse;
import com.xzty.cq.tover.businessmanagement.common.factory.Account;
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
 * Created 2018/6/11.
 * explain
 */

public class BackWriteInfoHelper {

    public static void submit(final ArrayList<String> images, final List<Bitmap> bitmapList, String pickName, List<Marker> markerList, String pickNote, String pickListString, final DataSourse.Callback<RspLogin> callback) {
            Account.load(MyApplication.getInstance());
            String projectId = Account.getProjectId();
            String pickUserId = Account.getemployId();
            String lo = String.valueOf(markerList.get(0).getPosition().longitude);
            String la = String.valueOf(markerList.get(0).getPosition().latitude);
            final Map<String, File> picNameAndPathMap = new HashMap<>();
            List<MultipartBody.Part> mList = new ArrayList<>();
            MultipartBody.Builder builder = new MultipartBody.Builder().setType(MultipartBody.FORM);
            if (!images.isEmpty()) {
                int i = 1;
                File outDir = MyApplication.getInstance().getExternalFilesDir("back");
                for (String image : images) {

                    File file = new File(outDir.getPath() + "/back" + i + ".jpeg");

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
                        RequestBody body = RequestBody.create(MediaType.parse("image/jpeg"), file);
                        MultipartBody.Part part = MultipartBody.Part.createFormData("back[]", file.getName(), body);
                        mList.add(part);
                        picNameAndPathMap.put(file.getName(), file);
                    } catch (Exception e) {
                        //msg.what = -1;
                        //Toast.makeText(this, e.toString(), Toast.LENGTH_SHORT).show();
                    }
                    i++;
                }//循环压缩成保存为file,并得到file的路径
            }//image不为空
            RequestBody body1 = RequestBody.create(MediaType.parse("multipart/form-data"),
                    projectId);
            RequestBody body2 = RequestBody.create(MediaType.parse("multipart/form-data"),
                    pickUserId);
            RequestBody body3 = RequestBody.create(MediaType.parse("multipart/form-data"),
                    pickName);
            RequestBody body4 = RequestBody.create(MediaType.parse("multipart/form-data"),
                    lo);

            RequestBody body5 = RequestBody.create(MediaType.parse("multipart/form-data"),
                    la);

            RequestBody body6 = RequestBody.create(MediaType.parse("multipart/form-data"),
                    pickNote);

            RequestBody body7 = RequestBody.create(MediaType.parse("multipart/form-data"),
                    pickListString);
            Map<String, RequestBody> maps = new HashMap<>();
            maps.put("projectId", body1);
            maps.put("backUserId", body2);
            maps.put("backName", body3);
            maps.put("backLongitude", body4);
            maps.put("backLatitude", body5);
            maps.put("backNote", body6);
            maps.put("backList", body7);
            RemoteService service = NetWork.remote(RemoteService.class);
            Subscription subscription = service.backInfo(mList, maps).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<RspModel<RspLogin>>() {
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
                        if (bitmapList != null) {
                            bitmapList.clear();
                        }
                        images.clear();
                        for (String key : picNameAndPathMap.keySet()) {
                            picNameAndPathMap.get(key).delete();
                        }
                        if (bitmapList != null) {
                            bitmapList.clear();
                        }
                        images.clear();
                        callback.onDataLoaded(listRspModel.getData());
                    } else {
                        callback.onDataNotAvailable("请求错误");
                    }
                }
            });

    }
}
