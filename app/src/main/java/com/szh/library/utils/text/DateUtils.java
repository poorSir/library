package com.szh.library.utils.text;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @Author szh
 * @Date 2018/7/31.
 * @Description 日期工具类
 */

public class DateUtils {
    /**
     * 获取年
     */
    public static int getYear() {
        Calendar calendar = Calendar.getInstance();
        return calendar.get(Calendar.YEAR);
    }

    /**
     * 获取月
     */
    public static int getMonth() {
        Calendar calendar = Calendar.getInstance();
        return calendar.get(Calendar.MONTH) + 1;
    }

    /**
     * 获取日
     */
    public static int getDay() {
        Calendar calendar = Calendar.getInstance();
        return calendar.get(Calendar.DAY_OF_MONTH);
    }

    /**
     * 获取时
     */
    public static int getHour() {
        Calendar calendar = Calendar.getInstance();
        return calendar.get(Calendar.HOUR_OF_DAY);
    }

    /**
     * 获取分
     */
    public static int getMinute() {
        Calendar calendar = Calendar.getInstance();
        return calendar.get(Calendar.MINUTE);
    }

    /**
     * 获取秒
     */
    public static int getSecond() {
        Calendar calendar = Calendar.getInstance();
        return calendar.get(Calendar.SECOND);
    }

    /**
     * 获取当前时间，格式自定
     */
    public static String getDate(String format) {
        return timeStampToDate(System.currentTimeMillis() + "", format);
    }

    /**
     * 时间戳->日期，格式自定
     */
    public static String timeStampToDate(String timeStamp, String format) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
        long longTime= Long.valueOf(timeStamp);
        Date date = new Date(longTime);
        return simpleDateFormat.format(date);
    }

    /**
     * 日期->时间戳
     */
    public static String dateToTimeStamp(String date, String format) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
        try {
            Date d = simpleDateFormat.parse(date);
            long ts = d.getTime();
            return String.valueOf(ts);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }
    /**判断是否闰年*/
    private static boolean isRunYear(int year) {
        return (year % 4 != 0) || (year % 100 == 0) && (year % 400 != 0);
    }
}
