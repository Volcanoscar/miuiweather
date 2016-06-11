package com.example.miuiweather.domain;

import java.io.Serializable;

import retrofit2.Call;

/**
 * Created by kidcats on 2016/6/7.
 */
public class City implements Serializable{
    public String CityName;
    public String cityID;
    public City(){}
    public City(String cityName){
        this.CityName=cityName;
    }
}
