package com.union.yunzhi.common.helper;

import android.content.Context;
import android.view.MotionEvent;
import android.view.View;

import com.union.yunzhi.common.util.LogUtils;

/**
 * Created by meng on 2018/3/12.
 * @function 侧滑销毁
 */

public class ViewHelper  {

    private View view;

    private Context context;

    private onFinshListener mListener;

    private String TGA ="ViewHelper";
    float x1 = 0;
    float x2 = 0;
    float y1 = 0;
    float y2 = 0;
    private boolean LOCK_Y;//锁住Y方向的滑动

    public ViewHelper(){

    }

    public ViewHelper(View view,Context context,onFinshListener mListener){
        this.view = view;
        this.context = context;
        this.mListener = mListener;
    }

    public void listener(){
        view.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                //继承了Activity的onTouchEvent方法，直接监听点击事件

                if(event.getAction() == MotionEvent.ACTION_DOWN) {

                    //当手指按下的时候
                    x1 = event.getX();
                    y1 = event.getY();
                }

                if(event.getAction() == MotionEvent.ACTION_UP) {
                    //当手指离开的时候
                    x2 = event.getX();
                    y2 = event.getY();
                    if(x2-x1>270){
                        LogUtils.i(TGA+"滑动","向上右滑销毁");
                        if(mListener!=null){
                            mListener.toFinshView();
                        }
                    }
                }
                return true;
            }
        });
    }


   public interface onFinshListener{
       //回调通知去销毁View
        void toFinshView();
    }

}
