package com.xzty.cq.tover.businessmanagement.common.app;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * author zzl
 * Created 2018/4/28.
 * explain 封装基类的fragment
 */

public abstract class BaseFragment extends Fragment {
    private View mRoot;
    private Unbinder mRootUnBinder;
    // 标示是否第一次初始化数据
    protected boolean mIsFirstInitData = true;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        intArgs(getArguments());
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle
            savedInstanceState) {
        if (mRoot == null) {
            int layId = getContentLayout();
            View root = inflater.inflate(layId, container, false);
            initWidget(root);
            mRoot = root;
        } else {
            if (mRoot.getParent() != null) {
                // 把当前Root从其父控件中移除
                ((ViewGroup) mRoot.getParent()).removeView(mRoot);
            }
        }
        return mRoot;
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (mIsFirstInitData) {
            mIsFirstInitData = false;
            // 触发
            onFirstInit();
            initData();
        } else {
            // 当View创建完成后初始化数据
            initData();
        }
    }


    /**
     * 当首次初始化数据的时候会调用的方法
     */
    protected void onFirstInit() {

    }


    /**
     * 初始化控件
     *
     * @param root
     */
    protected void initWidget(View root) {
        mRootUnBinder = ButterKnife.bind(this, root);
    }


    /**
     * 初始化数据
     */
    protected void initData() {

    }

    /**
     * 初始化参数
     *
     * @param bundle 参数
     */
    public void intArgs(Bundle bundle) {

    }

    /**
     * 得到当前界面id
     *
     * @return 资源id
     */
    public abstract int getContentLayout();


    /**
     * 返回按键触发时调用
     *
     * @return 返回True代表我已处理返回逻辑，Activity不用自己finish。
     * 返回False代表我没有处理逻辑，Activity自己走自己的逻辑
     */
    public boolean onBackPressed() {
        return false;
    }

/*
    *//**
     * 设置占位布局
     *
     * @param placeHolderView 继承了占位布局规范的View
     *//*
    public void setPlaceHolderView(PlaceHolderView placeHolderView) {
        this.mPlaceHolderView = placeHolderView;
    }*/
}
