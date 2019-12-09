package com.adc.funnyfarmfinal.action;


import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;

import com.adc.funnyfarmfinal.application.FunnyFarmApplication;
import com.crashlytics.android.Crashlytics;

import io.fabric.sdk.android.Fabric;

public class BaseActivity extends AppCompatActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fabric.with(this, new Crashlytics());
        logPhoneNumber();
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.parseColor("#FFFFFF"));
        }
    }
    private void logPhoneNumber() {
        String phoneNumber = ((FunnyFarmApplication)getApplicationContext()).getPhoneNumber();
        if(phoneNumber != null) {
            Crashlytics.setUserName(phoneNumber);
        }
    }
}
