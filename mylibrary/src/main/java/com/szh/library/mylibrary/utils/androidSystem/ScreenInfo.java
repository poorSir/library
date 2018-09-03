package com.szh.library.mylibrary.utils.androidSystem;

import android.content.Context;
import android.util.DisplayMetrics;

/**
 * @Author szh
 * @Date 2018/8/2.
 * @Description 屏幕相关类
 */

public class ScreenInfo {
    /**
     *获取屏幕高度
     */
    public static int getHeight(Context context){
        DisplayMetrics dm = context.getResources().getDisplayMetrics();
        return dm.heightPixels;
    }

    /**
     * 获取屏幕宽度(减去虚拟按键高度)
     */
    public static int getWidth(Context context){
        DisplayMetrics dm = context.getResources().getDisplayMetrics();
        return dm.widthPixels;
    }
}
