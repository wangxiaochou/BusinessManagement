package com.xzty.cq.tover.businessmanagement.common.factory;

import android.content.Context;
import android.content.SharedPreferences;

import com.xzty.cq.tover.businessmanagement.common.MyApplication;
import com.xzty.cq.tover.businessmanagement.common.model.ReqLogin;
import com.xzty.cq.tover.businessmanagement.common.model.RspLogin;

import java.util.Set;

/**
 * author zzl
 * Created 2018/5/3.
 * explain 用户持久化保存
 */

public class Account {
    private static final String EMPLOYID = "employId";
    private static final String EMPLOYIDANDPROJECTID = "employIdAndProjectId";
    private static final String PROJECTID = "projectId";
    private static final String RULE = "rules";
    private static final String USERNAME = "username";
    private static final String PASSWORD = "password";

    private static String employId;
    private static String projectId;
    private static Set<String> rules;
    private static String username;
    private static String password;

    /**
     * 存储基础信息到xml文件中
     *
     * @param context
     */
    private static void sava(Context context) {
        SharedPreferences sp = context.getSharedPreferences(Account.class.getName(), Context.MODE_PRIVATE);
        sp.edit()
                .putString(EMPLOYID, employId)
                .putString(PROJECTID, projectId)
                .putStringSet(RULE, rules)
                .putString(USERNAME, username)
                .putString(PASSWORD, password)
                .apply();
    }

    /**
     * 数据加载
     */
    public static void load(Context context) {
        SharedPreferences sp = context.getSharedPreferences(Account.class.getName(), Context.MODE_PRIVATE);
        employId = sp.getString(EMPLOYID, "");
        rules = sp.getStringSet(RULE, null);
        username = sp.getString(USERNAME, "");
        password = sp.getString(PASSWORD, "");
        projectId = sp.getString(PROJECTID,"");
    }

    /**
     * 保存至xml中
     *
     * @param login
     * @param reqLogin
     */
    public static void login(RspLogin login, ReqLogin reqLogin) {
        Account.employId = login.getEmployId();
        Account.username = reqLogin.getUsername();
        Account.password = reqLogin.getPassword();
        Account.rules = login.getRules();
        sava(MyApplication.getInstance());
    }

    /**
     *
     */
    public static void savaProjectId(Context context,String projectId) {
        SharedPreferences sp = context.getSharedPreferences(Account.class.getName(), Context.MODE_PRIVATE);
        sp.edit()
        .putString(PROJECTID,projectId).apply();
    }

    public static ReqLogin getUserInfo() {
        return new ReqLogin(Account.username, Account.password);
    }

    public static String getemployId() {
        return Account.employId;
    }

    public static String getProjectId(){
        return Account.projectId;
    }

    public static Set<String> getRule(){
        return Account.rules;
    }
}
