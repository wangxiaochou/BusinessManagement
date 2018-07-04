package com.xzty.cq.tover.businessmanagement.common.factory.rx;

import android.util.Log;

import com.google.gson.JsonParseException;
import com.xzty.cq.tover.businessmanagement.common.model.RspModel;

import org.json.JSONException;

import java.net.ConnectException;

import retrofit2.adapter.rxjava.HttpException;
import rx.Subscriber;

/**
 * author zzl
 * Created 2018/5/3.
 * explain rxjava中的基类订阅者
 */

public abstract class BaseSubscriber<T> extends Subscriber<T> {

    private static final int UNAUTHORIZED = 401;
    private static final int FORBIDDEN = 403;
    private static final int NOT_FOUND = 404;
    private static final int REQUEST_TIMEOUT = 408;
    private static final int INTERNAL_SERVER_ERROR = 500;
    private static final int BAD_GATEWAY = 502;
    private static final int SERVICE_UNAVAILABLE = 503;
    private static final int GATEWAY_TIMEOUT = 504;

    @Override
    public void onStart() {
        super.onStart();
        onStart();
    }

    @Override
    public void onError(Throwable e) {
        Log.e("tag", "MySubscriber.throwable =" + e.toString());
        Log.e("tag", "MySubscriber.throwable =" + e.getMessage());
        if (e instanceof Exception) {
            //访问获得对应的Exception
            ResponeThrowable ex;
            Log.i("tag", "e.toString = " + e.toString());
            if (e instanceof HttpException) {
                HttpException httpException = (HttpException) e;
                ex = new ResponeThrowable(e, ERROR.HTTP_ERROR);
                switch (httpException.code()) {
                    case UNAUTHORIZED:
                    case FORBIDDEN:
                    case NOT_FOUND:
                    case REQUEST_TIMEOUT:
                    case GATEWAY_TIMEOUT:
                    case INTERNAL_SERVER_ERROR:
                    case BAD_GATEWAY:
                    case SERVICE_UNAVAILABLE:
                    default:
                        //ex.code = httpException.code();
                        ex.message = "网络错误";
                        break;
                }
                onMyError(ex.message);
            } else if (e instanceof ServerException) {
                ServerException resultException = (ServerException) e;
                ex = new ResponeThrowable(resultException, resultException.code);
                ex.message = resultException.message;
                onMyError(ex.message);
            } else if (e instanceof JsonParseException
                    || e instanceof JSONException
                /*|| e instanceof ParseException*/) {
                ex = new ResponeThrowable(e, ERROR.PARSE_ERROR);
                ex.message = "解析错误";
                onMyError(ex.message);
            } else if (e instanceof ConnectException) {
                ex = new ResponeThrowable(e, ERROR.NETWORD_ERROR);
                ex.message = "连接失败";
                onMyError(ex.message);
            } else if (e instanceof javax.net.ssl.SSLHandshakeException) {
                ex = new ResponeThrowable(e, ERROR.SSL_ERROR);
                ex.message = "证书验证失败";
                onMyError(ex.message);
            } else {
                ex = new ResponeThrowable(e, ERROR.UNKNOWN);
                ex.message = "未知错误";
                onMyError(ex.message);
            }

        } else {
            //将Throwable 和 未知错误的status code返回
            onMyError("位置错误");
        }
    }


    @Override
    public void onCompleted() {
        Log.i("tag", "MySubscriber.onComplete()");
    }

    @Override
    public void onNext(T t) {
        if (RspModel.class.isInstance(t)) {
            RspModel model = (RspModel) t;
            if (model.getBackcode() == 1) {
                onMyStart();
            } else {
                onMyError("请求错误"+((RspModel) t).getBackcode()+((RspModel) t).getMessage());
            }
        }
    }


    public static ResponeThrowable handleException(Throwable e) {
        ResponeThrowable ex;
        Log.i("tag", "e.toString = " + e.toString());
        if (e instanceof HttpException) {
            HttpException httpException = (HttpException) e;
            ex = new ResponeThrowable(e, ERROR.HTTP_ERROR);
            switch (httpException.code()) {
                case UNAUTHORIZED:
                case FORBIDDEN:
                case NOT_FOUND:
                case REQUEST_TIMEOUT:
                case GATEWAY_TIMEOUT:
                case INTERNAL_SERVER_ERROR:
                case BAD_GATEWAY:
                case SERVICE_UNAVAILABLE:
                default:
                    //ex.code = httpException.code();
                    ex.message = "网络错误";
                    break;
            }
            return ex;
        } else if (e instanceof ServerException) {
            ServerException resultException = (ServerException) e;
            ex = new ResponeThrowable(resultException, resultException.code);
            ex.message = resultException.message;
            return ex;
        } else if (e instanceof JsonParseException
                || e instanceof JSONException
                /*|| e instanceof ParseException*/) {
            ex = new ResponeThrowable(e, ERROR.PARSE_ERROR);
            ex.message = "解析错误";
            return ex;
        } else if (e instanceof ConnectException) {
            ex = new ResponeThrowable(e, ERROR.NETWORD_ERROR);
            ex.message = "连接失败";
            return ex;
        } else if (e instanceof javax.net.ssl.SSLHandshakeException) {
            ex = new ResponeThrowable(e, ERROR.SSL_ERROR);
            ex.message = "证书验证失败";
            return ex;
        } else {
            ex = new ResponeThrowable(e, ERROR.UNKNOWN);
            ex.message = "未知错误";
            return ex;
        }
    }

    /**
     * 约定异常
     */
    static class ERROR {
        /**
         * 未知错误
         */
        public static final int UNKNOWN = 0;
        /**
         * 解析错误
         */
        public static final int PARSE_ERROR = 1001;
        /**
         * 网络错误
         */
        public static final int NETWORD_ERROR = 1002;
        /**
         * 协议出错
         */
        public static final int HTTP_ERROR = 1003;

        /**
         * 证书出错
         */
        public static final int SSL_ERROR = 1005;
    }

    public static class ResponeThrowable extends Exception {
        public int code;
        public String message;

        public ResponeThrowable(Throwable throwable, int code) {
            super(throwable);
            this.code = code;
        }
    }

    /**
     * ServerException发生后，将自动转换为ResponeThrowable返回
     */
    static class ServerException extends RuntimeException {
        int code;
        String message;
    }

    public void onMyStart() {
    }

    public abstract void onMyNext(T t);

    public abstract void onMyError(String msg);

    public abstract void onMyCompleted();
}
