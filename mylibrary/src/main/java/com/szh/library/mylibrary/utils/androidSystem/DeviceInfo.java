package com.szh.library.mylibrary.utils.androidSystem;

import android.os.Build;

/**
 * @Author szh
 * @Date 2018/7/31.
 * @Description 设备信息 -工具类
 */

public class DeviceInfo {
    /**
     * 获取手机型号
     */
    public static String getMobileModel(){
        return Build.MODEL;
    }

    /**
     * 获取android 系统版本号
     * @return
     */
    public static String getSystemVersion(){
        return Build.VERSION.RELEASE;
    }
    /**
     * 获取手机厂商
     */
    public static String getBarand(){
        return Build.BRAND;
    }
}
