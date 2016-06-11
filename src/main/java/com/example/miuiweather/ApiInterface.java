package com.example.miuiweather;

import com.example.miuiweather.domain.WeatherInfo;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by kidcats on 2016/5/31.
 */
public interface ApiInterface {

    String HOST = "https://api.heweather.com/x3/";

    @GET("weather")
    Observable<WeatherInfo> getWeatherInfo(@Query("city") String city, @Query("key") String key);

    @GET("x3/weather")
    Call<String> getJson(@Query("cityid") String id, @Query("key") String key);
}
