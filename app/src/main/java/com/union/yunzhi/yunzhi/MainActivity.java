package com.union.yunzhi.yunzhi;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;
import com.union.yunzhi.common.app.ActivityM;
import com.union.yunzhi.common.helper.NavHelper;
import com.union.yunzhi.common.util.LogUtils;
import com.union.yunzhi.yunzhi.fragment.main.ClassFragment;
import com.union.yunzhi.yunzhi.fragment.main.CommunicationFragment;
import com.union.yunzhi.yunzhi.fragment.main.HomeFragment;
import com.union.yunzhi.yunzhi.fragment.main.LiveFragment;
import com.union.yunzhi.yunzhi.fragment.main.MeFragment;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Iterator;

import cn.jpush.android.api.JPushInterface;

public class MainActivity extends ActivityM implements NavHelper.OnTabChangedListener<Integer>,NavHelper.OnTabReselectListener<Integer>,BottomNavigationViewEx.OnNavigationItemSelectedListener {


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


    /**
     * 触发双击刷新
     * @param tab
     */
    @Override
    public  void  notifyTabReselect(NavHelper.Tab<Integer> tab) {
        LogUtils.i("notifyTabReselect",tab.getFragment().toString());
       if(tab.getFragment() instanceof HomeFragment){

           HomeFragment homeFragment = (HomeFragment)tab.getFragment();
           homeFragment.initRefreshData();

       }else if(tab.getFragment() instanceof ClassFragment){

           ClassFragment classFragment = (ClassFragment)tab.getFragment();
           classFragment.initRefreshData();

       }else if(tab.getFragment() instanceof CommunicationFragment){

           CommunicationFragment ccommunicationFragment = (CommunicationFragment)tab.getFragment();

       }else if(tab.getFragment() instanceof LiveFragment){

           LiveFragment liveFragment = (LiveFragment)tab.getFragment();

       }else {

           MeFragment meFragment = (MeFragment) tab.getFragment();

       }

    }



    // 打印所有的 intent extra 数据
    private static String printBundle(Bundle bundle) {
        StringBuilder sb = new StringBuilder();
        for (String key : bundle.keySet()) {
            if (key.equals(JPushInterface.EXTRA_NOTIFICATION_ID)) {
                sb.append("\nkey:" + key + ", value:" + bundle.getInt(key));
            } else if (key.equals(JPushInterface.EXTRA_CONNECTION_CHANGE)) {
                sb.append("\nkey:" + key + ", value:" + bundle.getBoolean(key));
            } else if (key.equals(JPushInterface.EXTRA_EXTRA)) {
                if (bundle.getString(JPushInterface.EXTRA_EXTRA).isEmpty()) {
                    Log.i("printBundle", "This message has no Extra data");
                    continue;
                }

                try {

                    /**
                     * 先将JSON字符串转化为对象，再取其中的字段
                     */
                    JSONObject json = new JSONObject(bundle.getString(JPushInterface.EXTRA_EXTRA));
                    Iterator<String> it = json.keys();

                    while (it.hasNext()) {
                        String myKey = it.next().toString();
                        sb.append("\nkey:" + key + ", value: [" + myKey + " - " + json.optString(myKey) + "]");
                    }
                } catch (JSONException e) {
                    Log.e("printBundle", "Get message extra JSON error!");
                }

            } else {
                sb.append("\nkey:" + key + ", value:" + bundle.getString(key));
            }
        }
        return sb.toString();
    }


}
