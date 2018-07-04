package com.xzty.cq.tover.businessmanagement.common.factory;

import com.xzty.cq.tover.businessmanagement.common.app.BaseActivity;

/**
 * author zzl
 * Created 2018/5/2.
 * explain Activity中的presenter封装
 */

public abstract class ActivityPresenter<Presenter extends BaseContract.Presenter> extends BaseActivity
        implements BaseContract.View<Presenter> {
    protected Presenter mPresenter;

    @Override
    protected void initBefore() {
        super.initBefore();
        initPresenter();
    }

    /**
     * 初始化Presenter
     *
     * @return Presenter
     */
    protected abstract Presenter initPresenter();

    @Override
    public void setPresenter(Presenter presenter) {
        mPresenter = presenter;
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mPresenter != null)
            mPresenter.destroy();
    }
}
