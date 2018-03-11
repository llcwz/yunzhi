package com.union.yunzhi.yunzhi;

import android.support.annotation.NonNull;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;
import com.union.yunzhi.common.app.ActivityM;
import com.union.yunzhi.common.helper.NavHelper;
import com.union.yunzhi.yunzhi.fragment.main.ClassFragment;
import com.union.yunzhi.yunzhi.fragment.main.CommunicationFragment;
import com.union.yunzhi.yunzhi.fragment.main.HomeFragment;
import com.union.yunzhi.yunzhi.fragment.main.LiveFragment;
import com.union.yunzhi.yunzhi.fragment.main.MeFragment;

public class MainActivity extends ActivityM implements NavHelper.OnTabChangedListener<Integer>,BottomNavigationViewEx.OnNavigationItemSelectedListener {


    private BottomNavigationViewEx bottomNavigationViewEx;

    private NavHelper<Integer> mNavHelper;

    private FrameLayout mContainer;

    private Boolean flag = false;
    @Override
    protected int getContentLayoutId() {
        changeStatusBarColor(R.color.blue_400);
        return R.layout.activity_main;
    }

    @Override
    protected void initWidget() {

        //changeStatusBarColor(R.color.blue_400);

        bottomNavigationViewEx = (BottomNavigationViewEx) findViewById(R.id.bnve);

        bottomNavigationViewEx.setOnNavigationItemSelectedListener(this);
        mContainer= (FrameLayout) findViewById(R.id.lay_contianer);


        bottomNavigationViewEx.enableAnimation(false);
        bottomNavigationViewEx.enableShiftingMode(false);
        bottomNavigationViewEx.enableItemShiftingMode(false);

        /**
         * 这里要首先去我们的特定文件夹底下监测是否有图片
         * 有就设置
         * 这个图片可以是从服务器获取的图片，也可以是从相册读取的图片
         */
        //  bottomNavigationViewEx.setBackground();

        mNavHelper = new NavHelper<>(MainActivity.this,
                R.id.lay_contianer,
                getSupportFragmentManager(),
                MainActivity.this,
                null);

        mNavHelper.add(R.id.navigation_home,new NavHelper.Tab<Integer>(HomeFragment.class,R.string.navigation_home))
                .add(R.id.navigation_class,new NavHelper.Tab<Integer>(ClassFragment.class,R.string.navigation_class ))
                .add(R.id.navigation_talk,new NavHelper.Tab<Integer>(CommunicationFragment.class,R.string.navigation_talk))
                .add(R.id.navigation_live,new NavHelper.Tab<Integer>(LiveFragment.class,R.string.navigation_live))
                .add(R.id.navigation_me,new NavHelper.Tab<Integer>(MeFragment.class,R.string.navigation_me));
    }

    @Override
    protected void initData() {
        Menu menu = bottomNavigationViewEx.getMenu();

        menu.performIdentifierAction(R.id.navigation_home,0);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
         return mNavHelper.performClickMenu(item.getItemId());
    }

    @Override
    public void onTabChanged(NavHelper.Tab<Integer> newTab, NavHelper.Tab<Integer> oldTab) {

    }
}
