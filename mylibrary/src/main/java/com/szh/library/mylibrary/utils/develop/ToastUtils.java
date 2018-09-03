package com.szh.library.mylibrary.utils.develop;

import android.content.Context;
import android.view.Gravity;
import android.widget.Toast;

import com.szh.library.MyApplication;

/**
 * @Author szh
 * @Date 2018/7/31.
 * @Description toast 工具类
 */

public class ToastUtils {
    public static void show(String msg){
        show(MyApplication.getContext(),msg);
    }
    public static void show(Context context, String msg){
        Toast toast = Toast.makeText(context,msg,Toast.LENGTH_SHORT);
        //显示在中间
        toast.setGravity(Gravity.CENTER,0,0);
        toast.show();
    }

    /***
     * 显示string 中的文字
     * @param id
     */
    public static void show(int id) {
        show(MyApplication.getContext(), MyApplication.getContext().getString(id));
    }
}
