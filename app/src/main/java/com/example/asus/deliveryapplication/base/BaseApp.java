package com.example.asus.deliveryapplication.base;

import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;

import com.example.asus.deliveryapplication.Bmob.BmobManager;
import com.example.asus.deliveryapplication.utils.SpUtils;


public class BaseApp extends Application {

    private static Context sContext;


    @Override
    public void onCreate() {
        super.onCreate();
        sContext = getApplicationContext();

        if (getApplicationInfo().packageName.equals(
                getCurProcessName(getApplicationContext()))) {
            SpUtils.getInstance().initSp(sContext);
            BmobManager.getInstance().initBmob(this);
        }
    }

    @Override
    public void onTrimMemory(int level) {
        super.onTrimMemory(level);
    }

    public static String getCurProcessName(Context context) {
        int pid = android.os.Process.myPid();
        ActivityManager activityManager = (ActivityManager)
                context.getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningAppProcessInfo appProcess :
                activityManager.getRunningAppProcesses()) {
            if (appProcess.pid == pid) {
                return appProcess.processName;
            }
        }
        return null;
    }

    public static Context getContext() {
        return sContext;
    }

}
