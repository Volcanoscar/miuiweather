package com.example.miuiweather;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class SettingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        overridePendingTransition(R.anim.scale_in,R.anim.alpha_out);
        setContentView(R.layout.activity_setting);
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.scale_in,R.anim.alpha_out);
    }
}
