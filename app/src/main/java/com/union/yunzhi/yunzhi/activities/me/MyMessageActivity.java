package com.union.yunzhi.yunzhi.activities.me;

import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.FrameLayout;

import com.union.yunzhi.common.app.ActivityM;
import com.union.yunzhi.yunzhi.R;

public class MyMessageActivity extends ActivityM {

    private FragmentManager mManager;
    @Override
    protected int getContentLayoutId() {
        return R.layout.activity_my_message;
    }

    @Override
    protected void initWidget() {
        mManager = getSupportFragmentManager();

    }

    @Override
    protected void initData() {

    }
}
