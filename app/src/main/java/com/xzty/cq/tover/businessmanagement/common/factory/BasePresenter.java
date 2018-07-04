package com.xzty.cq.tover.businessmanagement.common.factory;

/**
 * author zzl
 * Created 2018/5/2.
 * explain 基类presenter
 */

public class BasePresenter<T extends BaseContract.View> implements BaseContract.Presenter {
    private T mView;

    public BasePresenter(T view) {
        setView(view);
    }

    /**
     * 设置一个view，子类可以重写
     *
     * @param view
     */
    private void setView(T view) {
        this.mView = view;
        this.mView.setPresenter(this);
    }

    /**
     * 给子类使用view的操作，不可重写
     *
     * @return view
     */
    protected final T getView() {
        return mView;
    }

    @Override
    public void start() {
        //开始的时候进行loading
        T view = mView;
        if (view != null) {
            view.showLoading();
        }
    }

    @Override
    public void destroy() {
        T view = mView;
        if (view != null) {
            //presenter设置为空
            view.setPresenter(null);
        }
    }
}
