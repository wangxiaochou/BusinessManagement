package com.xzty.cq.tover.businessmanagement.projectmanagement.task.view;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.DrawableRes;
import android.support.annotation.Nullable;
import android.support.v7.content.res.AppCompatResources;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.xzty.cq.tover.businessmanagement.R;
import com.xzty.cq.tover.businessmanagement.common.view.TitleLayout;

import butterknife.BindView;

/**
 * author yq
 * 自定义Toolbar类
 */
public class MyToolbar extends Toolbar {

    public MyToolbar(Context context) {
        super(context);
    }

    public MyToolbar(final Context context, @Nullable AttributeSet attrs){
        super(context, attrs);
    }

    public MyToolbar(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void setTitle(int resId) {
        super.setTitle(R.string.task_homepage_title);
    }


    @Override
    public void setOnMenuItemClickListener(OnMenuItemClickListener listener) {
        super.setOnMenuItemClickListener(listener);
    }

    @Override
    public void setBackgroundColor(int color) {
        super.setBackgroundColor(color);
    }

    //为Toolbar设置左边返回图标
    public void setBackItem(final Context context, @DrawableRes int resId){
        ImageView imageView = new ImageView(context);
        imageView.setImageDrawable(AppCompatResources.getDrawable(context,resId));

        Toolbar.LayoutParams params = new Toolbar.LayoutParams(Toolbar.LayoutParams.WRAP_CONTENT,
                Toolbar.LayoutParams.WRAP_CONTENT);
        params.gravity = Gravity.LEFT;
        //为返回按钮设置点击事件
        imageView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                ((Activity)context).finish();
            }
        });
        this.addView(imageView,params);
    }

    //为Toolbar添加居中文字
    public void addMiddleTitle(Context context, CharSequence title){

        TextView textView = new TextView(context);
        textView.setText(title);

        Toolbar.LayoutParams params = new Toolbar.LayoutParams(Toolbar.LayoutParams.WRAP_CONTENT,
                Toolbar.LayoutParams.WRAP_CONTENT);
        params.gravity = Gravity.LEFT;
        this.addView(textView,params);
    }

}
