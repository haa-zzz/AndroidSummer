package com.example.asus.deliveryapplication.base;

import android.os.Bundle;

import com.example.asus.deliveryapplication.utils.SystemUI;

public class BaseUIActivity extends BaseActivity {

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SystemUI.fixSystemUI(this);
    }
}
