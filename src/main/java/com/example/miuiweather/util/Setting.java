package com.example.miuiweather.util;

import android.app.Notification;
import android.content.Context;
import android.content.SharedPreferences;
/**
 * Created by kidcats on 2016/6/12
 * thanks hugo for SeeWeather
 *
 * 设置相关 包括 sp 的写入
 */
public class Setting {

    public static final String CLEAR_CACHE = "clear_cache";//清空缓存
    public static final String CITY_NAME = "城市";//选择城市
    public static final String NOTIFICATION_MODEL = "notification_model";

    public static final String KEY = "282f3846df6b41178e4a2218ae083ea7";// 和风天气 key

    public static int ONE_HOUR = 1000 * 60 * 60;

    private static Setting sInstance;

    private SharedPreferences mPrefs;

    public static Setting getInstance(Context context) {
        if (sInstance == null) {
            sInstance = new Setting(context);
        }
        return sInstance;
    }

    private Setting(Context context) {
        mPrefs = context.getSharedPreferences("setting", Context.MODE_PRIVATE);
    }


    public Setting putInt(String key, int value) {
        mPrefs.edit().putInt(key, value).apply();
        return this;
    }

    public int getInt(String key, int defValue) {
        return mPrefs.getInt(key, defValue);
    }

    public Setting putString(String key, String value) {
        mPrefs.edit().putString(key, value).apply();
        return this;
    }

    public String getString(String key, String defValue) {
        return mPrefs.getString(key, defValue);
    }


    //当前城市
    public void setCityName(String name) {
        mPrefs.edit().putString(CITY_NAME, name).apply();
    }

    public String getCityName() {return mPrefs.getString(CITY_NAME, "北京");}

}
