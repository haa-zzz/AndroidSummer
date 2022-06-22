package com.example.asus.deliveryapplication.utils;

import android.util.Log;

public class Logger {

	public static boolean isShowLog = true;
	public static String TAG = "test";

	public static void i( String msg) {
		if (!isShowLog) {
			return;
		}
		Log.i(TAG, msg);
	}
}
