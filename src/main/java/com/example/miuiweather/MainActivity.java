package com.example.miuiweather;

import android.content.Intent;
import android.net.Uri;
import android.support.design.widget.NavigationView;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import com.example.miuiweather.domain.WeatherInfo;
import com.example.miuiweather.util.ACache;
import com.example.miuiweather.util.Setting;
import com.example.miuiweather.util.Util;
import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;


public class MainActivity extends AppCompatActivity implements ViewpageFragment.OnFragmentInteractionListener, AMapLocationListener, SwipeRefreshLayout.OnRefreshListener, View.OnClickListener, ViewPager.OnPageChangeListener {
    public static final String KEY = "e7fd88700e284884a8ca79b30e33c9f8";
    public static int HOUR = 1000 * 60 * 60;
    private Setting mSetting;
    private FragmentPagerAdapter mAdapter;
    private List<Fragment> mFragments = new ArrayList<Fragment>();
    private ACache aCache;
    private WeatherInfo.HeWeatherdataserviceBean bean = null;
    //声明AMapLocationClient类对象
    public AMapLocationClient mLocationClient = null;
    public AMapLocationClientOption mLocationOption = null;


    @BindView(R.id.city_text)
    TextView textcity;
    @BindView(R.id.drawerlayout)
    DrawerLayout drawerLayout;
    @BindView(R.id.viewpager)
    ViewPager viewPager;
    @BindView(R.id.include_swiperefresh)
    SwipeRefreshLayout swipeRefreshLayout;
    @BindView(R.id.nav_view)
    NavigationView navigationView;
    @BindView(R.id.av_frag1)
    PageView pageView1;
    @BindView(R.id.av_frag2)
    PageView pageView2;
    @BindView(R.id.av_frag3)
    PageView pageView3;
    @BindView(R.id.simpleline)
    SimpleLine simpleLine;


    private Observer<WeatherInfo.HeWeatherdataserviceBean> observer;
    private ApiInterface apiInterface = null;

    @Override
    protected void onStart() {
        super.onStart();
        mAdapter.notifyDataSetChanged();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        aCache = ACache.get(this);
        mSetting=Setting.getInstance(getApplicationContext());
        bean = new WeatherInfo.HeWeatherdataserviceBean();
        initPageview();
        initDraw();
        initadapter();
        initObserver();
        location();

        if (Util.isNetworkConnected(this)) {
            //如果联网则从网络获取,否则从缓存获取
            getDataByNet(observer);
        } else {
            getDataByCache(observer);
            Toast.makeText(MainActivity.this, "请先检查你有没有联网呦", Toast.LENGTH_SHORT).show();
        }


    }

    private void getDataByCache(Observer<WeatherInfo.HeWeatherdataserviceBean> observer) {
        WeatherInfo.HeWeatherdataserviceBean weatherInfo = null;
        weatherInfo = (WeatherInfo.HeWeatherdataserviceBean) aCache.getAsObject("weatherdata");
        if (weatherInfo != null) {
            Observable.just(weatherInfo)
                    .distinct()
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(observer);
        }

    }

    private void getDataByNet(Observer<WeatherInfo.HeWeatherdataserviceBean> observer) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiInterface.HOST)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        apiInterface = retrofit.create(ApiInterface.class);
        apiInterface.getWeatherInfo(mSetting.getCityName(), KEY)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .filter(weatherapi -> weatherapi.getHeWeatherdataservice().get(0).getStatus().equals("ok"))
                .map(weatherInfo -> weatherInfo.getHeWeatherdataservice().get(0))
                .subscribe(observer);
    }

    private void initPageview() {
        pageView1.setOnClickListener(this);
        pageView2.setOnClickListener(this);
        pageView3.setOnClickListener(this);
        viewPager.addOnPageChangeListener(this);
        viewPager.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int action = event.getAction();
                switch (action) {
                    case MotionEvent.ACTION_DOWN:// 经测试，ViewPager的DOWN事件不会被分发下来
                    case MotionEvent.ACTION_MOVE:
                        swipeRefreshLayout.setEnabled(false);
                        break;
                    case MotionEvent.ACTION_UP:
                    case MotionEvent.ACTION_CANCEL:
                        swipeRefreshLayout.setEnabled(true);
                        break;
                }
                return false;
            }
        });
    }




    private void initDraw() {
        swipeRefreshLayout.setOnRefreshListener(this);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.nav_set:
                        startActivity(new Intent(MainActivity.this, SettingActivity.class));
                        break;
                    case R.id.nav_city:
                        startActivityForResult(new Intent(MainActivity.this, CityAcitvity.class), 1);
                        break;
                }
                return false;
            }
        });


    }

    private void initadapter() {
        ViewpageFragment todayfragment = ViewpageFragment.newInstance("today");
        ViewpageFragment tomorrowfragmen = ViewpageFragment.newInstance("tomorrow");
        ViewpageFragment thedayafterfragment = ViewpageFragment.newInstance("the day after tomorrow");
        mFragments.add(todayfragment);
        mFragments.add(tomorrowfragmen);
        mFragments.add(thedayafterfragment);
        mAdapter = new MainWeatherAdapter(getSupportFragmentManager(), mFragments);
        viewPager.setAdapter(mAdapter);
    }

    //这里很奇怪,为了兼容吗?
    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    @Override
    public void onRefresh() {
        swipeRefreshLayout.postDelayed(()->getDataByNet(observer),1000);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == 2) {
            swipeRefreshLayout.setRefreshing(true);
            String citynames = data.getStringExtra("city").substring(0, 2);
            mSetting.setCityName(citynames);
            Log.i("tag", citynames);
            getDataByNet(observer);
        }
    }

    private void initObserver() {
        observer = new Observer<WeatherInfo.HeWeatherdataserviceBean>() {
            @Override
            public void onCompleted() {
                swipeRefreshLayout.setRefreshing(false);
            }

            @Override
            public void onError(Throwable e) {
                swipeRefreshLayout.setRefreshing(false);
                Log.i("error", e.getMessage());
                Toast.makeText(MainActivity.this, "哎呀,出错啦", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNext(WeatherInfo.HeWeatherdataserviceBean heWeatherdataserviceBean) {
                aCache.put("weatherdata", heWeatherdataserviceBean, HOUR);
                textcity.setText(mSetting.getCityName());
                bean.setAqi(heWeatherdataserviceBean.getAqi());
                bean.setBasic(heWeatherdataserviceBean.getBasic());
                bean.setDailyforecast(heWeatherdataserviceBean.getDailyforecast());
                bean.setHourlyforecast(heWeatherdataserviceBean.getHourlyforecast());
                bean.setNow(heWeatherdataserviceBean.getNow());
                bean.setStatus(heWeatherdataserviceBean.getStatus());
                bean.setSuggestion(heWeatherdataserviceBean.getSuggestion());
                pageView1.setTvdate(bean.getDailyforecast().get(0).getDate().substring(5));
                pageView2.setTvdate(bean.getDailyforecast().get(1).getDate().substring(5));
                pageView3.setTvdate(bean.getDailyforecast().get(2).getDate().substring(5));
                pageView1.setTvtemp(bean.getDailyforecast().get(0).getTmp().getMax() + "/" + bean.getDailyforecast().get(0).getTmp().getMin());
                pageView2.setTvtemp(bean.getDailyforecast().get(1).getTmp().getMax() + "/" + bean.getDailyforecast().get(1).getTmp().getMin());
                pageView3.setTvtemp(bean.getDailyforecast().get(2).getTmp().getMax() + "/" + bean.getDailyforecast().get(2).getTmp().getMin());
                pageView1.setPageimage(Util.SelectIcon(MainActivity.this, bean.getDailyforecast().get(0).getCond().getCoded()));
                pageView2.setPageimage(Util.SelectIcon(MainActivity.this, bean.getDailyforecast().get(1).getCond().getCoded()));
                pageView3.setPageimage(Util.SelectIcon(MainActivity.this, bean.getDailyforecast().get(2).getCond().getCoded()));
                ViewpageFragment fragment = (ViewpageFragment) mFragments.get(viewPager.getCurrentItem());
                String temp = bean.getDailyforecast().get(viewPager.getCurrentItem()).getTmp().getMax()+"°";
                String cond = bean.getDailyforecast().get(viewPager.getCurrentItem()).getCond().getTxtd();
                String wind = bean.getDailyforecast().get(viewPager.getCurrentItem()).getWind().getDir();
                String sugg = bean.getSuggestion().getComf().getTxt();
                fragment.setFragmentData(cond, temp, wind, sugg);
                setlinedata(bean);
                mAdapter.notifyDataSetChanged();
            }
        };
    }

    /**
     * 用于设置pageview中的pageline
     * @param bean
     */
    private void setlinedata(WeatherInfo.HeWeatherdataserviceBean bean) {
        int todatlow= Integer.parseInt(bean.getDailyforecast().get(0).getTmp().getMin());//今天的最低气温
        int todatmax= Integer.parseInt(bean.getDailyforecast().get(0).getTmp().getMax());//今天的最高气温
        int tomorlow= Integer.parseInt(bean.getDailyforecast().get(1).getTmp().getMin());
        int tomormax= Integer.parseInt(bean.getDailyforecast().get(1).getTmp().getMax());
        int lastlow= Integer.parseInt(bean.getDailyforecast().get(2).getTmp().getMin());
        int lastmax= Integer.parseInt(bean.getDailyforecast().get(2).getTmp().getMax());
        int llastlow=Integer.parseInt(bean.getDailyforecast().get(3).getTmp().getMin());
        int[] temps={todatlow,todatmax,tomorlow,tomormax,lastlow,lastmax,llastlow};
        simpleLine.setPointValue(temps);
    }


    /**
     * 高德定位
     */
    private void location() {
        //初始化定位
        mLocationClient = new AMapLocationClient(getApplicationContext());
        mLocationOption = new AMapLocationClientOption();
        //设置定位回调监听
        mLocationClient.setLocationListener(this);
        //设置定位模式为高精度模式，Battery_Saving为低功耗模式，Device_Sensors是仅设备模式
        mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
        //设置是否返回地址信息（默认返回地址信息）
        mLocationOption.setNeedAddress(true);
        //设置定位间隔 单位毫秒
        mLocationOption.setInterval(mSetting.LOCATION_TIME);
        //给定位客户端对象设置定位参数
        mLocationClient.setLocationOption(mLocationOption);
        //启动定位
        mLocationClient.startLocation();
    }

    @Override
    public void onLocationChanged(AMapLocation aMapLocation) {
        if (aMapLocation != null) {
            if (aMapLocation.getErrorCode()==0) {
                mSetting.setCityName(aMapLocation.getCity().substring(0,2));
                Log.i("location", aMapLocation.getCity().substring(0,2));
                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                Date date = new Date(aMapLocation.getTime());
                df.format(date);//定位时间
                getDataByNet(observer);
            } else {
                Log.i("location", "定位的城市为空");
                //显示错误信息ErrCode是错误码，errInfo是错误信息，详见错误码表。
                Log.e("location","location Error, ErrCode:"
                        + aMapLocation.getErrorCode() + ", errInfo:"
                        + aMapLocation.getErrorInfo());
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mLocationClient != null) {
            mLocationClient.unRegisterLocationListener(this);
        }
    }

    /**
     * 这个是用于判断那一个pageview陪点击
     * @param v
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.av_frag1:
                viewPager.setCurrentItem(0);
                break;
            case R.id.av_frag2:
                viewPager.setCurrentItem(1);
                break;
            case R.id.av_frag3:
                viewPager.setCurrentItem(2);
                break;
        }
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {
        //state的状态有三个，0表示什么都没做，1正在滑动，2滑动完毕
        if (state==2){
            swipeRefreshLayout.setRefreshing(true);
            getDataByNet(observer);
        }
    }
}
