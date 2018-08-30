package com.szh.library.utils.text;

import android.content.Context;
import android.util.TypedValue;

/**
 * @Author szh
 * @Date 2018/8/30.
 * @Description 尺寸转换
 */

public class DimensionConvert {
    public static int dp2px(int dp, Context context){
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,dp,context.getResources().getDisplayMetrics());
    }
    public static int sp2px(int sp,Context context){
        return (int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP,sp,context.getResources().getDisplayMetrics());
    }
}
