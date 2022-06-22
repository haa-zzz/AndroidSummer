package com.example.asus.deliveryapplication;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Handler;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

/**
    进来的第一个activity, 是一张app相关的图片，用于过渡。
 */
public class MainActivity extends AppCompatActivity {
    private Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //设置一个两秒的延时，两秒后，启东整体的Activity
        handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(MainActivity.this,BeginningActivity.class);
                startActivity(intent);
            }
        }, 2000);
    }
}
