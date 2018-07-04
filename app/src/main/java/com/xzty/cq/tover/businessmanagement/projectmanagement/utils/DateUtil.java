package com.xzty.cq.tover.businessmanagement.projectmanagement.utils;

import android.util.Log;

import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by yfl on 2017/12/6.
 */

public class DateUtil {
    private static final String TAG = "DateUtil";
    private static SimpleDateFormat daysdf = new SimpleDateFormat("yyyy-MM-dd");
    private static SimpleDateFormat daymssdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
    private static Calendar c = Calendar.getInstance();

    public static String dateToString(Object date) {
        if (date == null) {
            return "空";
        } else {
            if (date instanceof Date) {
                return daysdf.format((Date) date);
            } else {
                String s = (String) date;
                if (s.equals("空")) {
                    return "空";
                } else {
                    return "其它";
                }
            }
        }
    }

//else if(date == null){}

    public static Date stringToDate(String dateStr) {
        try {
            return daysdf.parse(dateStr);
        } catch (ParseException e) {
            Log.e(TAG, "stringToDate: 字符串转Date错误");
            e.printStackTrace();
            return null;
        }
    }

    public static Date stringToDatems(String dateStr) {
        try {
            return daymssdf.parse(dateStr + " 00:00:00.000");
        } catch (ParseException e) {
            Log.e(TAG, "stringToDate: 字符串转Date错误");
            e.printStackTrace();
            return null;
        }
    }


    /**
     * @param stringDay 字符串格式的日期
     * @param nDay      增加天数
     * @return 增加过天数的日期字符串
     */
    public static String stringDayPlusNdays(String stringDay, int nDay) {
        try {
            c.setTime(daysdf.parse(stringDay));
            c.add(Calendar.DATE, nDay);
            return daysdf.format(c.getTime());
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }


    /**
     * 将字符串转成yyyy-MM-dd
     *
     * @param strDate
     * @return
     */
    public static Date strToDate(String strDate) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        ParsePosition pos = new ParsePosition(0);
        Date strtodate = formatter.parse(strDate, pos);
        return strtodate;
    }


}
