package com.xzty.cq.tover.businessmanagement.common.data;

/**
 * author zzl
 * Created 2018/6/8.
 * explain 对点击操作的抽象
 */

public interface Calculate {

    interface Callback<T> extends Calculate.Success<T>, Calculate.Failed {

    }

    /**
     * 只关注成功的接口
     *
     * @param <T> 任意类型
     */
    interface Success<T> {
        void successCallBack(T t);

    }

    /**
     * 只关注失败的接口
     */
    interface Failed {
        void failedCallBack(String strRes);
    }
}
