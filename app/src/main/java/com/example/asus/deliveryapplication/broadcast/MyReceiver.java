package com.example.asus.deliveryapplication.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class MyReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        if(intent.getAction().equals("com.example.asus.deliveryapplication.broadcast.abc")){
            Toast.makeText(context,"自定义广播：操作成功",Toast.LENGTH_SHORT).show();
        }
        if(intent.getAction().equals("android.net.conn.CONNECTIVITY_CHANGE")){
            Toast.makeText(context,"接收到系统网络变化广播",Toast.LENGTH_SHORT).show();
        }
    }
}

