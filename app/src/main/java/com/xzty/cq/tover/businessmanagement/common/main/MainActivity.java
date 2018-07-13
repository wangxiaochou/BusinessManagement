package com.xzty.cq.tover.businessmanagement.common.main;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.xiaomi.mipush.sdk.MiPushClient;
import com.xzty.cq.tover.businessmanagement.R;
import com.xzty.cq.tover.businessmanagement.common.app.BaseActivity;
import com.xzty.cq.tover.businessmanagement.common.factory.NavHelper;
import com.xzty.cq.tover.businessmanagement.common.main.frg.BusinessFragment;
import com.xzty.cq.tover.businessmanagement.common.main.frg.FinanceFragment;
import com.xzty.cq.tover.businessmanagement.common.main.frg.HomeFragment;
import com.xzty.cq.tover.businessmanagement.common.main.frg.HumanFragment;
import com.xzty.cq.tover.businessmanagement.common.main.frg.ManageFragment;
import com.xzty.cq.tover.businessmanagement.common.view.BottomNavigationViewEx;

import butterknife.BindView;


/**
 * author zzl
 * create 2018/5/2 9:01
 * explain 主界面Activity
 */
public class MainActivity extends BaseActivity implements BottomNavigationView.OnNavigationItemSelectedListener
        , NavHelper.OnTabChangedListener<Integer> {

    private NavHelper<Integer> mNavHelper;

    @BindView(R.id.navigation)
    BottomNavigationViewEx mNavigation;

    private long exitTime = 0;

    @Override
    protected int getContentLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initWidget() {
        super.initWidget();

        // 初始化底部辅助工具类
        mNavHelper = new NavHelper<>(this, R.id.container, getSupportFragmentManager(), this);
        mNavHelper
                .add(R.id.home, new NavHelper.Tab<Integer>(HomeFragment.class, R.string.title_home))
                .add(R.id.manage, new NavHelper.Tab<Integer>(ManageFragment.class, R.string.title_manage))
                .add(R.id.finance, new NavHelper.Tab<Integer>(FinanceFragment.class, R.string.title_finance))
                .add(R.id.human, new NavHelper.Tab<Integer>(HumanFragment.class, R.string.title_human))
                .add(R.id.business, new NavHelper.Tab<Integer>(BusinessFragment.class, R.string.title_business));
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
        menu.performIdentifierAction(R.id.home, 0);
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
