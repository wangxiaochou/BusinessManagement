package com.xzty.cq.tover.businessmanagement.common.net;

/**
 * author zzl
 * Created 2018/5/2.
 * explain 一些不变的 参数
 */

public class Common {
    public interface Constance {
        //外部服务器
        String BASE_URL = "http://erp.tover.group/menu/";
        //本地服务器
//        String BASE_URL = "http://192.168.0.162/menu/";

        /**
         * 获取图片地址
         */
        public static final String getImgUrl = BASE_URL.substring(0,20);
    }
}
