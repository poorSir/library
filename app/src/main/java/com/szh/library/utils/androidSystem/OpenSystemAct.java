package com.szh.library.utils.androidSystem;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

/**
 * @Author szh
 * @Date 2018/7/30.
 * @Description 打开系统页面
 */

public class OpenSystemAct {
    /***
     * 打开短信页面，给指定的号码发送短信，并附带短信内容
     * @param context
     * @param number
     * @param body
     */
    public static void openSmsWithBody(Context context, String number, String body) {
        Intent sendIntent = new Intent(Intent.ACTION_SENDTO);
        sendIntent.setData(Uri.parse("smsto:" + number));
        sendIntent.putExtra("sms_body", body);
        context.startActivity(sendIntent);
    }

    /***
     * 打开浏览器
     * @param uri 网址
     */
    public static void openWebAct(Context context, String uri) {
        Intent intent = new Intent();
        intent.setData(Uri.parse(uri));
        intent.setAction(Intent.ACTION_VIEW);
        context.startActivity(intent);
    }

    /***
     *
     * @param context
     * @param phoneNum 电话号码
     * @param isDirectCall 是否直接拨打
     * 需要权限  android.permission.CALL_PHONE
     */
    public static void openPhoneCall(Context context, String phoneNum, boolean isDirectCall) {
        Intent intent = null;
        if (isDirectCall) {
            intent = new Intent(Intent.ACTION_CALL);
        } else {
            intent = new Intent(Intent.ACTION_DIAL);
        }
        Uri data = Uri.parse("tel:" + phoneNum);
        intent.setData(data);
        context.startActivity(intent);
    }

    /**
     * 打开应用设置页面
     * @param context
     */
    public static void openSettingAct(Context context) {
        Intent localIntent = new Intent();
        localIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        localIntent.setAction("android.settings.APPLICATION_DETAILS_SETTINGS");
        localIntent.setData(Uri.fromParts("package", context.getPackageName(), null));
        context.startActivity(localIntent);
    }
}
