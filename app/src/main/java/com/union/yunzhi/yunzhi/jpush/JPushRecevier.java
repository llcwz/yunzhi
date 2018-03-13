package com.union.yunzhi.yunzhi.jpush;

import android.app.ActivityManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.union.yunzhi.common.util.LogUtils;
import com.union.yunzhi.factories.moudles.jpush.PushMessage;
import com.union.yunzhi.yunzhi.MainActivity;
import com.union.yunzhi.yunzhi.activities.LoginActivity;
import com.union.yunzhi.yunzhi.manager.UserManager;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Iterator;
import java.util.List;

import cn.jpush.android.api.JPushInterface;

/**
 * Created by meng on 2018/3/12.
 */

public class JPushRecevier extends BroadcastReceiver {

    private final String TGA = "JPushRecevier";

    //自定义登陆广播Action
    public static final String JPUSH_ACTION = "com.union.yunzhi.JPUSH_ACTION";

    @Override
    public void onReceive(Context context, Intent intent) {

        Bundle bundle = intent.getExtras();

        LogUtils.d(TGA, "[JPushReceiver] onReceive - " + intent.getAction() + ", extras: " + printBundle(bundle));


        /**
         * 仅仅展示信息
         */
        if (intent.getAction().equals(JPushInterface.ACTION_NOTIFICATION_RECEIVED)) {
            int notifactionId = bundle.getInt(JPushInterface.EXTRA_NOTIFICATION_ID);
            LogUtils.d(TGA, "[JPushReceiver] 接收到推送下来的通知的ID: " + notifactionId);
            String title = bundle.getString(JPushInterface.EXTRA_NOTIFICATION_TITLE);
            String extras = bundle.getString(JPushInterface.EXTRA_EXTRA);
            Log.i(TGA, title);
            Log.i(TGA, extras);
        } else if (intent.getAction().equals(JPushInterface.ACTION_NOTIFICATION_OPENED)) {
            //处理推送弹框,点击的时候触发跳转
            /**
             * 1.应用未启动
             * ------>启动主页----->不需要登陆信息类型，直接跳转到消息展示页面
             *                ----->需要登陆信息类型，由于应用都未启动，肯定不存在已经登陆这种情况------>跳转到登陆页面
             *                                                                                    ----->登陆完毕，跳转到信息展示页面。
             *-                                                                                     ---->取消登陆，返回首页。
             * 2.如果应用已经启动，------>不需要登陆的信息类型，直接跳转到信息展示页面。
             *                     ------>需要登陆的信息类型------>已经登陆----->直接跳转到信息展示页面。
             *                                              ------>未登陆------->则跳转到登陆页面
             *                                                                      ----->登陆完毕，跳转到信息展示页面。
             *                                                                   ----->取消登陆，回到首页。
             *
             * 3.startActivities(Intent[]);在推送中的妙用,注意startActivities在生命周期上的一个细节,
             * 前面的Activity是不会真正创建的，直到要到对应的页面
             */

            PushMessage pushMessage = (PushMessage) JSON.parseObject(bundle.getString(JPushInterface.EXTRA_EXTRA), PushMessage.class);

            Log.i("messageType", pushMessage.messageType);
            Log.i("messageContent", pushMessage.messageContent);


            if (getCurrentTask(context)) {
                Intent pushIntent = new Intent();
                pushIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                pushIntent.putExtra("pushMessage", pushMessage);
                /**
                 * 需要登陆且当前没有登陆才去登陆页面
                 */

                if (pushMessage.messageType != null && pushMessage.messageType.equals("2")
                        && !UserManager.getInstance().hasLogined()) {
                    pushIntent.setClass(context, LoginActivity.class);
                    pushIntent.putExtra("fromPush", true);
                } else {
                    /**
                     * 不需要登陆或者已经登陆的Case,直接跳转到内容显示页面
                     */
                    pushIntent.setClass(context, PushMessageActivity.class);
                }
                context.startActivity(pushIntent);
            }else {
                /**
                 * 应用没有启动。。。
                 */

                /**
                 * 这里只分了两种类型，如果消息类型很多的话，用switch--case去匹配
                 */

                Intent mainIntent = new Intent(context, MainActivity.class);
                mainIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

                if(pushMessage!=null&&pushMessage.messageType.equals("2")){
                    Intent loginIntent = new Intent();
                    loginIntent.setClass(context, LoginActivity.class);
                    loginIntent.putExtra("fromPush", true);
                    loginIntent.putExtra("pushMessage", pushMessage);
                    context.startActivities(new Intent[]{mainIntent, loginIntent});
                }else {
                    Intent pushIntent = new Intent(context, PushMessageActivity.class);
                    pushIntent.putExtra("pushMessage", pushMessage);
                    context.startActivities(new Intent[]{mainIntent, pushIntent});
                }
            }


        } else if (intent.getAction().equals(JPushInterface.ACTION_NOTIFICATION_RECEIVED)) {
            //处理推送不弹框
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


    /**
     * 这个是真正的获取指定包名的应用程序是否在运行(无论前台还是后台)
     *
     * @return
     */
    private boolean getCurrentTask(Context context) {

        ActivityManager activityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningTaskInfo> appProcessInfos = activityManager.getRunningTasks(50);
        for (ActivityManager.RunningTaskInfo process : appProcessInfos) {

            if (process.baseActivity.getPackageName().equals(context.getPackageName())
                    || process.topActivity.getPackageName().equals(context.getPackageName())) {

                return true;
            }
        }
        return false;
    }



    private void sendJPushBroadcast(){

        Intent intent = new Intent(JPUSH_ACTION);



        // LocalBroadcastManager.getInstance(this).sendBroadcast(new Intent(JPUSH_ACTION));
    }

}
