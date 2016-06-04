package com.example.miuiweather;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import com.example.miuiweather.domain.City;
import com.example.miuiweather.util.CityNameUtil;
import com.example.miuiweather.util.DBManager;
import com.example.miuiweather.util.WeatherDB;
import com.github.stuxuhai.jpinyin.PinyinHelper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func0;
import rx.schedulers.Schedulers;

public class CityAcitvity extends AppCompatActivity {
    private List<City> mcityList;
    private List<String> cityname;
    private Map<String,Integer> Initialsmap=new HashMap<String, Integer>();
    private CityAdapter madapter;
    private DBManager mDbmanager;
    private WeatherDB mWeatherDb;

    @BindView(R.id.city_toolbar)
    Toolbar toolbar;
    @BindView(R.id.cityactivity_recycle)
    RecyclerView recyclerView;
    @BindView(R.id.city_qulikcearchbar)
    QulikSearchBar qulikSearchBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        overridePendingTransition(R.anim.scale_in, R.anim.alpha_out);
        setContentView(R.layout.activity_city_acitvity);

        Observable.defer(new Func0<Observable<Object>>() {
            @Override
            public Observable<Object> call() {
                mDbmanager = new DBManager(CityAcitvity.this);
                mDbmanager.openDatabase();
                mWeatherDb = new WeatherDB(CityAcitvity.this);
                return Observable.just(1);
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<Object>() {
                    @Override
                    public void call(Object o) {
                        cityname = new ArrayList<String>();
                        queryCity();
                        initView();

                    }
                });


    }

    private void queryCity() {
        Observable.defer(new Func0<Observable<City>>() {
            @Override
            public Observable<City> call() {
                mcityList = mWeatherDb.loadCities(mDbmanager.getDatabase());
                Initialsmap=CityNameUtil.findNumber(CityNameUtil.getCityName(mcityList));
                return Observable.from(mcityList);
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<City>() {
                    @Override
                    public void onCompleted() {
                        madapter.notifyDataSetChanged();
                        recyclerView.smoothScrollToPosition(0);
                    }

                    @Override
                    public void onError(Throwable e) {
                    }

                    @Override
                    public void onNext(City city) {
                        cityname.add(city.CityName);
                    }
                });
        if (mcityList == null) {
            mcityList = mWeatherDb.loadCities(mDbmanager.getDatabase());
        }
    }



    private void initView() {
        ButterKnife.bind(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        madapter = new CityAdapter(this, cityname);
        recyclerView.setAdapter(madapter);
        madapter.setOnItemClickListener(new CityAdapter.OnRecycleviewItemClick() {
            @Override
            public void onItemClick(View view, String name) {
                Toast.makeText(CityAcitvity.this,name,Toast.LENGTH_SHORT).show();
            }
        });
        qulikSearchBar.setOnNumberClickListener(new QulikSearchBar.OnNumberClickListener() {
            @Override
            public void onNumberClick(String input) {
                int targetnum=Initialsmap.get(input.toLowerCase());
                recyclerView.smoothScrollToPosition(targetnum);
            }
        });
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.scale_in, R.anim.alpha_out);
    }
}
