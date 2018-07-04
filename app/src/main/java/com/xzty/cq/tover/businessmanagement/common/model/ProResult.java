package com.xzty.cq.tover.businessmanagement.common.model;

import rx.Observable;
import rx.functions.Func1;

/**
 * author zzl
 * Created 2018/5/2.
 * explain 统一处理数据
 */

public class ProResult<T> implements Func1<RspModel<T>, Observable<T>> {

    @Override
    public Observable<T> call(RspModel<T> tRspModel) {
        if (!tRspModel.success()) {
            return Observable.error(new Throwable(tRspModel.getMessage()));
        } else {
            return Observable.just(tRspModel.getData());
        }

    }
}
