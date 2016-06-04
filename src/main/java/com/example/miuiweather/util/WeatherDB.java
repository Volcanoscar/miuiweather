package com.example.miuiweather.util;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;


import com.example.miuiweather.domain.City;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kidcats on 2016/6/3 .
 * 封装数据库操作
 */
public class WeatherDB {

    private Context context;

    public WeatherDB(Context context) {
        this.context = context;
    }

    public List<City> loadCities(SQLiteDatabase db) {
        List<City> list = new ArrayList<>();
        Cursor cursor = db.query("T_City", null, null, null, null, null, null);
        if (cursor.moveToFirst()) {
            do {
                City city = new City();
                city.CityName = cursor.getString(cursor.getColumnIndex("CityName"));
                city.CitySort = cursor.getInt(cursor.getColumnIndex("CitySort"));
                list.add(city);
            } while (cursor.moveToNext());
        }
        cursor.close();

        return list;
    }
}
