package com.xzty.cq.tover.businessmanagement.projectmanagement.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ProgressBar;

import com.aspsine.swipetoloadlayout.SwipeRefreshTrigger;
import com.aspsine.swipetoloadlayout.SwipeTrigger;

/**
 * author zzl
 * Created 2018/5/4.
 * explain 头部刷新
 */

public class CustomRefreshHead extends ProgressBar implements SwipeRefreshTrigger, SwipeTrigger {
    public CustomRefreshHead(Context context) {
        super(context);
    }

    public CustomRefreshHead(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomRefreshHead(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void onRefresh() {

    }

    @Override
    public void onPrepare() {

    }

    @Override
    public void onMove(int i, boolean b, boolean b1) {

    }

    @Override
    public void onRelease() {

    }

    @Override
    public void onComplete() {

    }

    @Override
    public void onReset() {

    }
}
