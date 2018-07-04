package com.xzty.cq.tover.businessmanagement.projectmanagement.common.api;

import android.app.Activity;
import android.net.Uri;
import android.os.Handler;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.xzty.cq.tover.businessmanagement.R;

import java.util.ArrayList;
import java.util.List;



/**
 * Created by MXH on 2017/5/11.
 */

public class Carousel {

    private Activity context;

    private List<String> imageUris;

    private List<View> mViewList = new ArrayList<View>();
    private TextView mArticleTitle = null;
    private ViewPager mViewPager = null;
    private Handler mHandler=new Handler();
    private LinearLayout mCustomSpace = null;
    private MyPagerAdapter adapter = null;
    private boolean loopPlayState = false;
    private View view;
    private String a;
    private List<String> tList=new ArrayList<String>();
    SimpleDraweeView[] simpleDraweeViews;
    private LinearLayout layout;

    public Carousel(Activity context, List<String> imageUris) {
        this(context, imageUris, 1);
    }

    public Carousel(Activity context, List<String> imageUris, int index) {
        view= View.inflate(context, R.layout.carousel,null);
        layout=(LinearLayout)context.findViewById(R.id.box);
        layout.addView(view,index);
        this.context = context;
        this.imageUris = imageUris;
        mViewPager = (ViewPager) layout.findViewById(R.id.viewpager);
        mCustomSpace = (LinearLayout)layout.findViewById(R.id.custom_space);
    }



    public void execute() {

        int size = imageUris.size();
        initTextViews(size);
        /*for(int i=0;i< imageUris.size();i++){
            switch(i){
                case 0:a="寿司";break;
                case 1:a="白砍鸡";break;
                case 2:a="里脊";break;
                case 3:a="水果";break;
            }
            tList.add(a); }*/
        for(int i=0;i<imageUris.size();i++){
            view = new View(context);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(14, 14);
            layoutParams.setMargins(3, 0, 3, 0);
            view.setLayoutParams(layoutParams);
            view.setBackgroundResource(R.drawable.dot_normal);
            mViewList.add(view);
            mCustomSpace.addView(view);
        }
        adapter =new MyPagerAdapter();
        mViewPager.setAdapter(adapter);
        mViewPager.addOnPageChangeListener(new MyPageChangeListener());

        if (!loopPlayState) {
            //mArticleTitle.setText(tList.get(0));
            mHandler.postDelayed(loopPlay, 3000);
            loopPlayState = true;
        }

    }
    private void initTextViews(int size) {
        SimpleDraweeView[] tvs = new SimpleDraweeView[size];
        for (int i = 0; i < tvs.length; i++) {
            tvs[i] = new SimpleDraweeView(context);
            tvs[i].getHierarchy().setPlaceholderImage(R.mipmap.ic_launcher);

            ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            tvs[i].setLayoutParams(layoutParams);

        }
        simpleDraweeViews = tvs;
    }

    //viewPager的适配器
    private final class MyPagerAdapter extends PagerAdapter {


        public int getCount() {
            return simpleDraweeViews.length;
        }


        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View)object);
        }


        public Object instantiateItem(ViewGroup container, int position) {
            SimpleDraweeView t = simpleDraweeViews[position % simpleDraweeViews.length];
            container.addView(t);
            Uri uri = Uri.parse(imageUris.get(position % simpleDraweeViews.length));
            t.setImageURI(uri);

            return t;
        }

        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

    }

    /**
     *当ViewPager中页面的状态发生改变时调用
     *
     */
    private class MyPageChangeListener implements ViewPager.OnPageChangeListener {

        private int historyPosition = 0;

        /**
         * 当ViewPager中页面的状态发生改变时调用
         */
        public void onPageSelected(int position) {
            //mArticleTitle.setText(imageUris.get(position));
            mViewList.get(historyPosition).setBackgroundResource(
                    R.drawable.dot_normal);
            mViewList.get(position).setBackgroundResource(
                    R.drawable.dot_focused);
            historyPosition = position;
        }

        public void onPageScrollStateChanged(int arg0) {
        }

        public void onPageScrolled(int arg0, float arg1, int arg2) {

        }
    }

    /**
     *  自动轮播图片
     */
    Runnable loopPlay = new Runnable() {
        @Override
        public void run() {
            int position = mViewPager.getCurrentItem();
            if(position == (imageUris.size()-1)){
                position = 0;
                mViewPager.setCurrentItem(position);
            }
            else{

                Log.i("position++", "position++="+position++);
                mViewPager.setCurrentItem(position++);
            }
            mHandler.postDelayed(loopPlay, 3000);
        }
    };




}
