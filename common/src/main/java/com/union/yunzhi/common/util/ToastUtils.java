package com.union.yunzhi.common.util;

import android.content.Context;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.widget.Toast;

/**
 * Created by meng on 2018/3/18.
 */

public class ToastUtils {
    private static Toast toast;
    private static Snackbar snackbar;

    /**
     * 解决Toast重复弹出 长时间不消失的问题
     * @param context
     * @param message
     */
    public static void showToast(Context context, String message){
        if (toast==null){
            toast = Toast.makeText(context,message,Toast.LENGTH_SHORT);
        }else {
            toast.setText(message);
        }
        toast.show();//设置新的消息提示
    }

    public static void showSnackbar(View view, String message){
        if (snackbar==null){
            snackbar = Snackbar.make(view,message,Snackbar.LENGTH_SHORT);
        }else {
            snackbar.setText(message);
        }
        snackbar.show();//设置新的消息提示
    }
}
