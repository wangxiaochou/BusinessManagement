package com.xzty.cq.tover.businessmanagement.new_mainlist;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.KeyEvent;
import android.widget.Toast;

import com.xzty.cq.tover.businessmanagement.R;
import com.xzty.cq.tover.businessmanagement.common.app.BaseActivity;
import com.xzty.cq.tover.businessmanagement.new_mainlist.all_fragment.view.NewProjectList_All_Fragment;
import com.xzty.cq.tover.businessmanagement.new_mainlist.ing_fragment.view.NewProjectList_Ing_Fragment;
import com.xzty.cq.tover.businessmanagement.new_mainlist.mine_fragment.view.NewProjectList_Mine_Fragment;
import com.xzty.cq.tover.businessmanagement.new_mainlist.view.ViewPagerAdapter;

import java.util.ArrayList;

import butterknife.BindView;

/**
 * author wl
 * Created 2018/08/21
 * explain 首页新项目列表NewViewPagerActivity
 */

public class NewViewPagerActivity extends BaseActivity{

    @BindView(R.id.tablayout)
    TabLayout mTablayout;

    @BindView(R.id.project_viewpager)
    ViewPager mViewpager;

    private long exitTime = 0;

    @Override
    protected int getContentLayoutId() {
        return R.layout.new_viewpager_projectlist;
    }

    @Override
    protected void initWidget() {
        super.initWidget();
        initViewPager();
    }

    private void initViewPager(){
        ArrayList<Fragment> fragments = new ArrayList<>();
        fragments.add(new NewProjectList_All_Fragment());
        fragments.add(new NewProjectList_Ing_Fragment());
        fragments.add(new NewProjectList_Mine_Fragment());
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.setmFragmentList(fragments);
        mViewpager.setAdapter(adapter);

        mTablayout.setupWithViewPager(mViewpager);
        mTablayout.getTabAt(0).setText("项目列表");
        mTablayout.getTabAt(1).setText("在建项目");
        mTablayout.getTabAt(2).setText("我的项目");

    }

    @Override
    protected void initData() {
        super.initData();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
            if ((System.currentTimeMillis() - exitTime) > 2000) {
                Toast.makeText(this, "再点一次退出程序", Toast.LENGTH_SHORT).show();
                exitTime = System.currentTimeMillis();
            } else {
                finish();
                System.exit(0);
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
