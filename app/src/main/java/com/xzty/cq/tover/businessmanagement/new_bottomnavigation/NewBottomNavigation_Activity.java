package com.xzty.cq.tover.businessmanagement.new_bottomnavigation;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.view.Menu;
import android.view.MenuItem;

import com.xzty.cq.tover.businessmanagement.R;
import com.xzty.cq.tover.businessmanagement.common.app.BaseActivity;
import com.xzty.cq.tover.businessmanagement.common.factory.NavHelper;
import com.xzty.cq.tover.businessmanagement.common.view.BottomNavigationViewEx;
import com.xzty.cq.tover.businessmanagement.new_bottomnavigation.department_fragment.Department_Fragment;
import com.xzty.cq.tover.businessmanagement.new_bottomnavigation.part_fragment.Part_Fragment;
import com.xzty.cq.tover.businessmanagement.new_bottomnavigation.tool_fragment.Tool_Fragment;

import butterknife.BindView;

/**
 * author wl
 * Created 2018/08/20
 * explain 首页底部导航栏Activity
 */

public class NewBottomNavigation_Activity extends BaseActivity implements BottomNavigationView.OnNavigationItemSelectedListener
        , NavHelper.OnTabChangedListener<Integer> {

    private NavHelper<Integer> mNavHelper;

    @BindView(R.id.navigation)
    BottomNavigationViewEx mNavigation;

    @Override
    protected int getContentLayoutId() {
        return R.layout.new_bottomnavigation_activity;
    }

    @Override
    protected void initWidget() {
        super.initWidget();

        // 初始化底部辅助工具类
        mNavHelper = new NavHelper<>(this, R.id.container, getSupportFragmentManager(), this);
        mNavHelper
                .add(R.id.part, new NavHelper.Tab<Integer>(Department_Fragment.class, R.string.title_home))
                .add(R.id.tool, new NavHelper.Tab<Integer>(Tool_Fragment.class, R.string.title_manage))
                .add(R.id.department_manage, new NavHelper.Tab<Integer>(Part_Fragment.class, R.string.title_finance));
        mNavigation.setOnNavigationItemSelectedListener(this);
        //禁止所有动画
        mNavigation.enableAnimation(false);
        mNavigation.enableShiftingMode(false);
        mNavigation.enableItemShiftingMode(false);
    }

    @Override
    protected void initData() {
        super.initData();
        Menu menu = mNavigation.getMenu();
        // 触发首次选中Home
        menu.performIdentifierAction(R.id.part, 0);
    }

    /**
     * NavHelper 处理回调后的方法
     *
     * @param newTab 新的tab
     * @param oldTab 旧的tab
     */
    @Override
    public void onTabChanged(NavHelper.Tab<Integer> newTab, NavHelper.Tab<Integer> oldTab) {

    }

    /**
     * 底部item被点击
     *
     * @param item 点击的item
     * @return true 代表我们能够处理这个点击
     */
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        //转接事件流到工具类中
        return mNavHelper.performClickMenu(item.getItemId());
    }

}
