package com.bisien.dems.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.bisien.dems.R;

public class AppServiceActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_service);
        findTitle("服务");

    }
}
