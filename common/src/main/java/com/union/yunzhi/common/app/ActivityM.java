package com.union.yunzhi.common.app;

import android.os.Bundle;
import android.support.annotation.ColorRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;

import com.union.yunzhi.common.util.StatusBarUtil;

import java.util.List;

/**
 * Created by meng on 2018/2/11.
 */

public abstract class ActivityM extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //在界面未初始化之前调用的窗口
        initWindows();
        if(initArgs(getIntent().getExtras()))
        {
            int layId=getContentLayoutId();

            setContentView(layId);
            initWidget();
            initData();
            reverseStatusColor();
        }else{
            finish();
        }
    }

    protected void initWindows(){
        //初始化窗口
    }

    /**
     * 初始化相关参数
     * @param bundle
     * @return 参宿正确返回true，错误放回fasle
     */
    protected  boolean initArgs(Bundle bundle)
    {
        return true;
    }
    //得到当前界面的资源文件id
    protected abstract  int getContentLayoutId();

    protected abstract void initWidget();


    protected abstract void initData();


    /**
     * 改变状态栏颜色
     *
     * @param color
     */
    public void changeStatusBarColor(@ColorRes int color) {
        StatusBarUtil.setStatusBarColor(this, color);
    }

    /**
     * 调整状态栏为亮模式，这样状态栏的文字颜色就为深模式了。
     */
    private void reverseStatusColor() {
        StatusBarUtil.statusBarLightMode(this);
    }


    /**
     * 隐藏状态栏
     */
    public void hiddenStatusBar() {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }


    /**
     * 设置状态栏为透明
     */
    public void transparencyBar(){
        StatusBarUtil.transparencyBar(this);
    }


    @Override
    public boolean onSupportNavigateUp() {
        //当点击界面导航返回时候回调的方法
        finish();
        return super.onSupportNavigateUp();
    }

    //手机的返回键
    @Override
    public void onBackPressed() {

        //得到当前Activity下所有的Fragment判断是否为空
        List<Fragment> fragments=getSupportFragmentManager().getFragments();
        if(fragments!=null&&fragments.size()>0) {
            for(android.support.v4.app.Fragment fragment:fragments){
                //判断是否为自己能够处理的Fragment
//                if(fragment instanceof com.union.test.common.app.Fragment){
//                    //是否拦截了返回按钮
//                    if(((com.union.test.common.app.Fragment) fragment).onBackPressed())
//                    {
//                        //如果有就直接return
//                        return;
//                    }
//                }
            }
        }
        super.onBackPressed();
        finish();
    }
}
