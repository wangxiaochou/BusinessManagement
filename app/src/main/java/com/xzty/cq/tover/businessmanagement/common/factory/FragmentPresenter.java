package com.xzty.cq.tover.businessmanagement.common.factory;

import android.content.Context;

import com.xzty.cq.tover.businessmanagement.common.app.BaseFragment;

/**
 * author zzl
 * Created 2018/5/2.
 * explain Fragment中的presenter封装
 */

public abstract class FragmentPresenter<Presenter extends BaseContract.Presenter> extends BaseFragment
        implements BaseContract.View<Presenter> {
    protected Presenter mPresenter;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        // 在界面onAttach之后就触发初始化Presenter
        initPresenter();
    }

    /**
     * 初始化Presenter
     *
     * @return Presenter
     */
    protected abstract Presenter initPresenter();

    @Override
    public void showError(String str) {

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void setPresenter(Presenter presenter) {
        mPresenter = presenter;
    }
}
