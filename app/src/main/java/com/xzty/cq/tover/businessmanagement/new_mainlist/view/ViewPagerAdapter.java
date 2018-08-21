package com.xzty.cq.tover.businessmanagement.new_mainlist.view;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * author wl
 * Created 2018/08/21
 * explain 首页新项目列表ViewPagerAdapter
 */

public class ViewPagerAdapter extends FragmentPagerAdapter{

    private List<Fragment> mFragmentList;

    public void setmFragmentList(ArrayList<Fragment> fragments){
        mFragmentList = fragments;
    }

    public ViewPagerAdapter(FragmentManager fragmentManager){
        super(fragmentManager);
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = mFragmentList.get(position);
        return fragment;
    }

    @Override
    public int getCount() {
        return mFragmentList.size();
    }
}
