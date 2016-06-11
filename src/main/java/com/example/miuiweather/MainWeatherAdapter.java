package com.example.miuiweather;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.example.miuiweather.domain.WeatherInfo;

import java.util.List;

/**
 * Created by kidcats on 2016/6/7.
 */
public class MainWeatherAdapter extends FragmentPagerAdapter {
    private List<Fragment> mfragments;

    public MainWeatherAdapter(FragmentManager fm) {
        super(fm);
    }
    public MainWeatherAdapter(FragmentManager fm,List<Fragment> fragments){
        super(fm);
        this.mfragments=fragments;
    }

    @Override
    public Fragment getItem(int position) {
        return mfragments.get(position);
    }

    @Override
    public int getCount() {
        return mfragments.size();
    }
    public void initFragment(WeatherInfo.HeWeatherdataserviceBean bean){

    }
}
