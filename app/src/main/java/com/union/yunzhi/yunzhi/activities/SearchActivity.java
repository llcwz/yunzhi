package com.union.yunzhi.yunzhi.activities;

import android.util.Log;
import android.view.View;

import com.union.yunzhi.common.app.ActivityM;
import com.union.yunzhi.common.helper.ViewHelper;
import com.union.yunzhi.common.util.LogUtils;
import com.union.yunzhi.yunzhi.R;

public class SearchActivity extends ActivityM implements ViewHelper.onFinshListener {




    @Override
    protected int getContentLayoutId() {
        return R.layout.main_activity_search;
    }

    @Override
    protected void initWidget() {
        View root = findViewById(R.id.test);
        if(root !=null){
            Log.i("SearchActivity","ok");
            ViewHelper viewHelper = new ViewHelper(root,this,this);
            viewHelper.listener();
        }





    }

    @Override
    protected void initData() {

    }

    @Override
    public void toFinshView() {
        LogUtils.i("toFinshView","toFinshView");
        finish();
    }
}
