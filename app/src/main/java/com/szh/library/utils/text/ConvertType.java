package com.szh.library.utils.text;

/**
 * @Author szh
 * @Date 2018/7/30.
 * @Description 文本转换类型-工具类
 */

public class ConvertType {
    /**
     * string转换为double
     * @param defaultValue 默认值
     */
    public static double stringToDouble(String text,double defaultValue){
        if(CheckText.isNumber(text)){
            return Double.parseDouble(text);
        }
        return  defaultValue;
    }

    /**
     * string转换为int
     */
    public static int stringToInt(String text,int defaultValue){
        if(CheckText.isNumber(text)){
            if(CheckText.isFloat(text)){
                return (int)Float.parseFloat(text);
            }
            return Integer.parseInt(text);
        }
        return  defaultValue;
    }
}
