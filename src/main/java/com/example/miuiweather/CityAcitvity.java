package com.example.miuiweather;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import com.example.miuiweather.domain.City;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CityAcitvity extends AppCompatActivity {

    @BindView(R.id.city_toolbar)
    Toolbar toolbar;
    @BindView(R.id.cityactivity_recycle)
    RecyclerView recyclerView;
    @BindView(R.id.city_qulikcearchbar)
    QulikSearchBar qulikSearchBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        overridePendingTransition(R.anim.scale_in,R.anim.alpha_out);
        setContentView(R.layout.activity_city_acitvity);

        ButterKnife.bind(this);

        qulikSearchBar.setOnNumberClickListener(new QulikSearchBar.OnNumberClickListener() {
            @Override
            public void onNumberClick(String number) {

            }
        });


    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.scale_in,R.anim.alpha_out);
    }
}
