package com.xzty.cq.tover.businessmanagement.projectmanagement.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ProgressBar;

import com.aspsine.swipetoloadlayout.SwipeLoadMoreTrigger;
import com.aspsine.swipetoloadlayout.SwipeTrigger;

/**
 * author zzl
 * Created 2018/5/4.
 * explain 底部刷新
 */

public class CustomRefreshFoot extends ProgressBar implements SwipeLoadMoreTrigger, SwipeTrigger {

    public CustomRefreshFoot(Context context) {
        super(context);
    }

    public CustomRefreshFoot(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomRefreshFoot(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void onLoadMore() {

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
