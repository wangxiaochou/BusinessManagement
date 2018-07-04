package com.xzty.cq.tover.businessmanagement.common.factory;

/**
 * author zzl
 * Created 2018/5/2.
 * explain 契约类
 */

public interface BaseContract {
    //基本界面
    interface View<T extends Presenter> {
        //显示一个错误信息
        void showError(String str);

        //显示进度
        void showLoading();

        //设置一个presenter
        void setPresenter(T presenter);
    }

    //基本的presenter职责
    interface Presenter {
        //共同开始触发
        void start();

        //共同触发销毁
        void destroy();
    }

    interface RecyclerView<T extends Presenter,ViewMode> extends View<T>{

        // 拿到一个适配器，然后自己自主的进行刷新
        //RecyclerViewAdapter<ViewMode> getRecyclerAdapter();

        // 当适配器数据更改了的时候触发
        void onAdapterDataChanged();
    }
}
