package com.union.yunzhi.yunzhi.application;

import android.app.Application;

import com.union.yunzhi.common.util.LogUtils;

import cn.jpush.android.api.JPushInterface;

/**
 * Created by meng on 2018/2/4.
 */

public class MyApplication extends Application{
    private final String TGA = "MyApplication";
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
        LogUtils.i(TGA,"初始化JPush");
        JPushInterface.setDebugMode(true);
        JPushInterface.init(this);
    }
}
