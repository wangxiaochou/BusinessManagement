package com.xzty.cq.tover.businessmanagement.common.model;

/**
 * author zzl
 * Created 2018/5/2.
 * explain 网络请求返回的规范
 */

public class RspModel<T> {
    private int backcode;
    private String message;
    private T data;

    public boolean success() {
        return backcode == 1;
    }

    public int getBackcode() {
        return backcode;
    }

    public void setBackcode(int backcode) {
        this.backcode = backcode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
