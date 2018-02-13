package com.union.yunzhi.yunzhi.application;

import android.app.Application;

/**
 * Created by meng on 2018/2/4.
 */

public class MyApplication extends Application{
    private static MyApplication myApplication = null;

    @Override
    public void onCreate() {
        super.onCreate();
        myApplication = this;
    }

    public static MyApplication getInstance(){
        return myApplication;
    }
}
