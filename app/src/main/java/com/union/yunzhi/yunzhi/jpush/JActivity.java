package com.union.yunzhi.yunzhi.jpush;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.union.yunzhi.common.util.LogUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Iterator;

import cn.jpush.android.api.JPushInterface;

import static com.union.yunzhi.yunzhi.jpush.JPushRecevier.JPUSH_ACTION;

public class JActivity extends AppCompatActivity {




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sendJPushBroadcast();
    }


    public static class jrecevier extends BroadcastReceiver{
        private final String TGA = "JActivity";

        @Override
        public void onReceive(Context context, Intent intent) {
            Bundle bundle = intent.getExtras();

            LogUtils.d(TGA, "[JPushReceiver] onReceive - " + intent.getAction() + ", extras: " + printBundle(bundle));
            if (intent.getAction().equals(JPushInterface.ACTION_NOTIFICATION_RECEIVED)) {
                int notifactionId = bundle.getInt(JPushInterface.EXTRA_NOTIFICATION_ID);
                LogUtils.d(TGA, "[JPushReceiver] 接收到推送下来的通知的ID: " + notifactionId);
                String title = bundle.getString(JPushInterface.EXTRA_NOTIFICATION_TITLE);
                String extras = bundle.getString(JPushInterface.EXTRA_EXTRA);
                Log.i(TGA, title);
                Log.i(TGA, extras);
           //     sendJPushBroadcast();

            }

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

    private void sendJPushBroadcast(){

        Intent intent = new Intent(JPUSH_ACTION);


        LocalBroadcastManager.getInstance(this).sendBroadcast(new Intent(JPUSH_ACTION));

        //
    }
}
