package com.example.miuiweather;

import android.content.Intent;
import android.net.Uri;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.miuiweather.domain.City;
import com.example.miuiweather.domain.WeatherInfo;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;


public class MainActivity extends AppCompatActivity implements ViewpageFragment.OnFragmentInteractionListener{
    public static final String BASEURL="https://api.heweather.com/";
    public static final String KEY="e7fd88700e284884a8ca79b30e33c9f8";
    private String cityid="CN101010100";
    private FragmentPagerAdapter mAdapter;
    private List<Fragment> mFragments = new ArrayList<Fragment>();


    @BindView(R.id.drawerlayout)
    DrawerLayout drawerLayout;
    @BindView(R.id.viewpager)
    ViewPager viewPager;
    @BindView(R.id.include_swiperefresh)
    SwipeRefreshLayout swipeRefreshLayout;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.nav_view)
    NavigationView navigationView;



    private WeatherInfo weatherInfo=null;
    final Gson gson = new GsonBuilder().create();
    private WeatherInfo mweatherinfo;
    private Observer<WeatherInfo> observer;
    private ApiInterface apiInterface=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initDraw();
        initadapter();




//        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl(BASEURL)
//                .addConverterFactory(GsonConverterFactory.create(gson))
//                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
//                .build();
//
//        apiInterface = retrofit.create(ApiInterface.class);
//        Call<WeatherInfo> call=apiInterface.getWeatherInfo(cityid,KEY);
//        call.enqueue(new Callback<WeatherInfo>() {
//            @Override
//            public void onResponse(Call<WeatherInfo> call, Response<WeatherInfo> response) {
//                runOnUiThread(new Runnable() {
//                    @Override
//                    public void run() {
//                        weatherInfo=response.body();
//                        textcity.setText(weatherInfo.getHeWeatherdataservice().get(0).getBasic().getCity());
//                    }
//                });
//                Log.i("tag",weatherInfo.getHeWeatherdataservice().get(0).getBasic().getCity());
//            }
//
//            @Override
//            public void onFailure(Call<WeatherInfo> call, Throwable t) {
//
//            }
//        });




//        initObserver();
    }

    private void initDraw() {
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                switch (item.getItemId()){
                    case R.id.nav_set:
                        startActivity(new Intent(MainActivity.this, SettingActivity.class));
                        break;
                    case R.id.nav_city:
                        startActivity(new Intent(MainActivity.this, CityAcitvity.class));
                        break;
                }
                return false;
            }
        });

    }

    private void initadapter() {
        ViewpageFragment todayfragment=ViewpageFragment.newInstance("today");
        ViewpageFragment tomorrowfragmen=ViewpageFragment.newInstance("tomorrow");
        ViewpageFragment thedayafterfragment=ViewpageFragment.newInstance("the day after tomorrow");
        ViewpageFragment lastdayfragment=ViewpageFragment.newInstance("lastday");
        mFragments.add(todayfragment);
        mFragments.add(tomorrowfragmen);
        mFragments.add(thedayafterfragment);
        mFragments.add(lastdayfragment);

        mAdapter=new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return mFragments.get(position);
            }

            @Override
            public int getCount() {
                return mFragments.size();
            }
        };

        viewPager.setAdapter(mAdapter);
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

//    private void initObserver() {
//        observer=new Observer<WeatherInfo>() {
//            @Override
//            public void onCompleted() {
//
//            }
//
//            @Override
//            public void onError(Throwable e) {
//
//            }
//
//            @Override
//            public void onNext(WeatherInfo weatherInfo) {
//            }
//        };
//
//        //https://drakeet.me/retrofit-2-0-okhttp-3-0-config
//
//        apiInterface.getWeatherInfo(cityid,KEY)
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(observer);
//
//    }
}
