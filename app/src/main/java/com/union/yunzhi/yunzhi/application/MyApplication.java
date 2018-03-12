package com.union.yunzhi.yunzhi.application;

import android.app.Application;
import android.util.Log;

import cn.jpush.android.api.JPushInterface;

/**
 * Created by meng on 2018/2/4.
 */

public class MyApplication extends Application{
    private static MyApplication myApplication = null;

    @Override
    public void onCreate() {
        super.onCreate();
        myApplication = this;
        initJPush();
    }

    public static MyApplication getInstance(){
        return myApplication;
    }


    private void initJPush() {
        Log.i("MyApplication","MyApplication");
        JPushInterface.setDebugMode(true);
        JPushInterface.init(this);
    }
}
