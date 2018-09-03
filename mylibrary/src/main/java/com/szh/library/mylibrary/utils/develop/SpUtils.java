package com.szh.library.mylibrary.utils.develop;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

import com.google.gson.Gson;
import com.szh.library.MyApplication;

import java.util.Set;

import static com.szh.library.utils.develop.SpUtils.DataType.STRING;

/**
 * @Author szh
 * @Date 2018/7/31.
 * @Description sharePreference开发工具类
 */

public class SpUtils {
    public static SpUtils instance;
    private String fileName;
    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;
    public SpUtils(){
        if(fileName == null){
            fileName = "spUtilsFileName";
        }
        preferences = MyApplication.getContext().getSharedPreferences(fileName, Context.MODE_PRIVATE);
        editor = preferences.edit();
    }
    public static SpUtils getInstance(){
        if(instance == null){
            instance = new SpUtils();
        }
        return instance;
    }
    /**存储数据*/
    public void setValue(String key,Object obj){
        if (key != null){
            if (obj instanceof Integer){
                editor.putInt(key, (Integer)obj);
            } else if (obj instanceof Long){
                editor.putLong(key, (Long)obj);
            } else if (obj instanceof Boolean){
                editor.putBoolean(key, (Boolean)obj);
            } else if (obj instanceof Float){
                editor.putFloat(key, (Float) obj);
            } else if (obj instanceof Set){
                editor.putStringSet(key, (Set<String>) obj);
            } else if(obj instanceof String) {
                editor.putString(key, String.valueOf(obj));
            }
        }
        editor.commit();
    }
    /**存储对象*/
    public void putObject(Object object) {
        String key  = getKey(object.getClass());
        Gson gson = new Gson();
        String json = gson.toJson(object);
        setValue(key,json);
    }
    /**去除对象数据*/
    public <T> T getObject(Class<T> clazz) {
        String key = getKey(clazz);
        String json = (String)getValue(key, STRING);
        if (TextUtils.isEmpty(json)) {
            return null;
        }
        try {
            Gson gson = new Gson();
            return gson.fromJson(json, clazz);
        } catch (Exception e) {
            return null;
        }
    }
    /**获取对象名作为key*/
    public static String getKey(Class<?> clazz) {
        return clazz.getName();
    }
    /**移除对于key的数据*/
    public void remove(String key){
        editor.remove(key);
        editor.commit();
    }
    /**取出数据*/
    public Object getValue(String key,DataType type){
        switch (type) {
            case INTEGER:
                return preferences.getInt(key, -1);
            case FLOAT:
                return preferences.getFloat(key, -1f);
            case BOOLEAN:
                return preferences.getBoolean(key, false);
            case LONG:
                return preferences.getLong(key, -1L);
            case STRING:
                return preferences.getString(key, null);
            case STRING_SET:
                return preferences.getStringSet(key, null);
            default:
                return null;
        }
    }
    public String getStringValue(String key,String defaultValue){
        return preferences.getString(key,defaultValue);
    }
    public boolean getBooleanValue(String key,boolean defaultValue){
        return preferences.getBoolean(key,defaultValue);
    }
    public int getIntegerValue(String key,int defaultValue){
        return preferences.getInt(key,defaultValue);
    }

    public enum DataType{
        INTEGER, LONG, BOOLEAN, FLOAT, STRING, STRING_SET
    }
}
