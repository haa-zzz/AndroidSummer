package com.example.asus.deliveryapplication.Bmob;

import android.content.Context;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.listener.FindListener;

public class BmobManager {

    private static final String BMOB_SDK_ID = "9088b835926e0e9577680c0ac75a1622";
    private static final String BMOB_NEW_DOMAIN = "http://sdk.cilc.cloud/8/";

    private volatile static BmobManager mInstance = null;

    private BmobManager() {
    }

    public static BmobManager getInstance() {
        if (mInstance == null) {
            synchronized (BmobManager.class) {
                if (mInstance == null) {
                    mInstance = new BmobManager();
                }
            }
        }
        return mInstance;
    }

    public void initBmob(Context mContext) {

        Bmob.initialize(mContext, BMOB_SDK_ID);
    }

    public MyUser getUser() {
        return BmobUser.getCurrentUser(MyUser.class);
    }

    public boolean isLogin() {
        return BmobUser.isLogin();
    }

    public void baseQuery(String key, String values, FindListener<Jingdian> listener) {
        BmobQuery<Jingdian> query = new BmobQuery<>();
        query.addWhereEqualTo(key, values);
        query.findObjects(listener);
    }

    public void queryChinaTopSpot(String keyword,String value,FindListener<Jingdian> listener) {
        baseQuery(keyword,value,listener);
    }

}
