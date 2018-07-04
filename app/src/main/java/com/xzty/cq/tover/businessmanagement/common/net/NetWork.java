package com.xzty.cq.tover.businessmanagement.common.net;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * author zzl
 * Created 2018/5/2.
 * explain 网络封装
 */

public class NetWork {
    private static NetWork instance;
    private Retrofit retrofit;
    private OkHttpClient client;
    private static final int DEFAULT_TIME_OUT = 5;//连接超时时间10s

    static {
        instance = new NetWork();
    }

    private NetWork() {
    }

    public static OkHttpClient getClient() {
        if (instance.client != null)
            return instance.client;
        // 存储起来
        instance.client = new OkHttpClient.Builder()
                //添加连接超时时间
                .connectTimeout(DEFAULT_TIME_OUT, TimeUnit.SECONDS)
                // 给所有的请求添加一个拦截器
                .addInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(Chain chain) throws IOException {

                        // 拿到我们的请求
                        Request original = chain.request();
                        // 重新进行build
                        Request.Builder builder = original.newBuilder();
                        builder.addHeader("Content-Type", "application/json");
                        Request newRequest = builder.build();
                        // 返回
                        return chain.proceed(newRequest);
                    }
                })
                //添加日志拦截器
                .addNetworkInterceptor(new LogInterceptor())
                .build();
        return instance.client;
    }

    // 构建一个Retrofit
    public static Retrofit getRetrofit() {
        if (instance.retrofit != null)
            return instance.retrofit;
        // 得到一个OK Client
        OkHttpClient client = getClient();
        // Retrofit
        Retrofit.Builder builder = new Retrofit.Builder();
        // 设置电脑链接
        instance.retrofit = builder.baseUrl(Common.Constance.BASE_URL)
                // 设置client
                .client(client)
                // 设置Json解析器
                .addConverterFactory(GsonConverterFactory.create())
                //添加rxjava
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        return instance.retrofit;

    }

    /**
     * 返回一个请求代理
     *
     * @return RemoteService
     */
    public static  <T> T remote(Class<T> tClass){
        return NetWork.getRetrofit().create(tClass);
    }
}
