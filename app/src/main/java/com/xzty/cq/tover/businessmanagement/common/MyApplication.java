package com.xzty.cq.tover.businessmanagement.common;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.os.Process;
import android.support.multidex.MultiDex;
import android.util.Log;

import com.xiaomi.channel.commonutils.logger.LoggerInterface;
import com.xiaomi.mipush.sdk.Logger;
import com.xiaomi.mipush.sdk.MiPushClient;

import com.xzty.cq.tover.businessmanagement.common.data.StaticValue;
import com.xzty.cq.tover.businessmanagement.common.utils.Rom;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import static java.lang.Process.*;
import static org.greenrobot.eventbus.EventBus.TAG;

/**
 * author zzl
 * Created 2018/4/28.
 * explain 程序启动入口
 */

public class MyApplication extends Application {
    private static MyApplication instance;


    //管理应用下所有activity
    private static List<Activity> allActivity = Collections.synchronizedList(new LinkedList<Activity>());

    @Override
    public void onCreate() {
        super.onCreate();
   //     MultiDex.install(this);
        instance = this;
        //注册Activity的监听
        registerAcivityListener();
        //判断当前手机系统
        if (Rom.isEmui()) {
            //注册华为推送

        } else {

        }

        //初始化MiPush推送服务
        if(true) {

            MiPushClient.registerPush(this, StaticValue.APP_ID, StaticValue.APP_KEY);
        }
    }

    /**
     * 判断是否注册小米推送服务
     * @return
     */
    private boolean shouldInit() {
        ActivityManager am = ((ActivityManager) getSystemService(Context.ACTIVITY_SERVICE));
        List<ActivityManager.RunningAppProcessInfo> processInfos = am.getRunningAppProcesses();
        String mainProcessName = getPackageName();
        int myPid = Process.myPid();
        for (ActivityManager.RunningAppProcessInfo info : processInfos) {
            if (info.pid == myPid && mainProcessName.equals(info.processName)) {
                return true;
            }
        }
        return false;
    }

   /* @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }*/

    public static MyApplication getInstance() {
        return instance;
    }

    /**
     * @param activity 添加一个activity到管理里
     */
    public void addActivity(Activity activity) {
        allActivity.add(activity);
        Log.d("SuperApplication", "activityList:size:" + allActivity.size());
    }

    /**
     * @param activity ：删除一个activity在管理里
     */
    public void removeActivity(Activity activity) {
        if (activity != null) {
            allActivity.remove(activity);
        }

        Log.d("SuperApplication", "activityList:size:" + allActivity.size());
    }


    /**
     * get current Activity 获取当前Activity（栈中最后一个压入的）
     */
    public static Activity getCurrentActivity() {
        if (allActivity == null || allActivity.isEmpty()) {
            return null;
        }
        Activity activity = allActivity.get(allActivity.size() - 1);
        return activity;
    }

    /**
     * 结束当前Activity（栈中最后一个压入的）
     */
    public static void finishCurrentActivity() {
        if (allActivity == null || allActivity.isEmpty()) {
            return;
        }
        Activity activity = allActivity.get(allActivity.size() - 1);
        finishActivity(activity);
    }

    /**
     * 结束指定的Activity
     */
    public static void finishActivity(Activity activity) {
        if (allActivity == null || allActivity.isEmpty()) {
            return;
        }
        if (activity != null) {
            allActivity.remove(activity);
            activity.finish();
            activity = null;
        }
    }


    /**
     * 结束指定类名的Activity
     */
    public static void finishAssignActivity(Class<?> cls) {
        if (allActivity == null || allActivity.isEmpty()) {
            return;
        }
        List<Activity> acList = new ArrayList<>();
        for (Activity activity : allActivity) {
            //并发
            if (activity.getClass().equals(cls)) {
                //finishActivity(activity);
                acList.add(activity);
            }
        }
        for (int i = 0; i < acList.size(); i++) {
            finishActivity(acList.get(i));
        }
    }

    /**
     * 添加指定activity
     *
     * @param cls
     */
    public void addAssignActivity(Class<?> cls) {
        if (allActivity.isEmpty() || allActivity == null) {
            return;
        }
        for (Activity activity : allActivity) {
            if (activity.getClass().equals(cls)) {
                finishActivity(activity);
                addActivity(activity);
            } else {
                addActivity(activity);
            }
        }
    }

    /**
     * 按照指定类名找到activity
     *
     * @param cls
     * @return
     */
    public static Activity findActivity(Class<?> cls) {
        Activity targetActivity = null;
        if (allActivity != null) {
            for (Activity activity : allActivity) {
                if (activity.getClass().equals(cls)) {
                    targetActivity = activity;
                    break;
                }
            }
        }
        return targetActivity;
    }

    /**
     * @return 获取当前最顶部activity的实例
     */
    public Activity getTopActivity() {
        Activity mBaseActivity = null;
        synchronized (allActivity) {
            final int size = allActivity.size() - 1;
            if (size < 0) {
                return null;
            }
            mBaseActivity = allActivity.get(size);
        }
        return mBaseActivity;

    }

    /**
     * @return 获取当前最顶部的acitivity 名字
     */
    public String getTopActivityName() {
        Activity mBaseActivity = null;
        synchronized (allActivity) {
            final int size = allActivity.size() - 1;
            if (size < 0) {
                return null;
            }
            mBaseActivity = allActivity.get(size);
        }
        return mBaseActivity.getClass().getName();
    }

    /**
     * 结束所有Activity
     */
    public static void finishAllActivity() {
        if (allActivity == null) {
            return;
        }
        for (Activity activity : allActivity) {
            activity.finish();
        }
        allActivity.clear();
    }


    private void registerAcivityListener() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH) {
            registerActivityLifecycleCallbacks(new ActivityLifecycleCallbacks() {
                @Override
                public void onActivityCreated(Activity activity, Bundle bundle) {
                    /**
                     *  监听到 Activity创建事件 将该 Activity 加入list
                     */
                    addActivity(activity);
                }

                @Override
                public void onActivityStarted(Activity activity) {

                }

                @Override
                public void onActivityResumed(Activity activity) {

                }

                @Override
                public void onActivityPaused(Activity activity) {

                }

                @Override
                public void onActivityStopped(Activity activity) {

                }

                @Override
                public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {

                }

                @Override
                public void onActivityDestroyed(Activity activity) {

                }
            });

        }
    }
}
