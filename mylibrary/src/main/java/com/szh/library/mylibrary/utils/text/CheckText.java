package com.szh.library.mylibrary.utils.text;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Author szh
 * @Date 2018/7/30.
 * @Description 检测文本输入是否正确--工具类
 */

public class CheckText {
    /**
     * 检测文本是否为空
     */
    public static boolean isEmpty(String text) {
        if (text != null && text.length() > 0) {
            return false;
        }
        return true;
    }

    /**
     * 检测list文本是否为空
     */
    public static <T> boolean isEmpty(List<T> list) {
        if (list != null && list.size() > 0) {
            return false;
        }
        return true;
    }

    /***
     * 判断是否是数字，包括正数，负数，小数
     */
    public static boolean isNumber(String text) {
        if(isEmpty(text)){
            return false;
        }
        Pattern pattern = Pattern.compile("^(\\-|\\+)?\\d+(\\.\\d+)?$");
        Matcher isNum = pattern.matcher(text);
        if(!isNum.matches()){
            return false;
        }
        return true;
    }
    /**判断是否是小数*/
    public static boolean isFloat(String text){
        if(isEmpty(text)){
            return false;
        }
        Pattern pattern = Pattern.compile("^((\\-|\\+)?\\d+)(\\.\\d+)?$");
        Matcher isNum = pattern.matcher(text);
        if(!isNum.matches()){
            return false;
        }
        return true;
    }
}
