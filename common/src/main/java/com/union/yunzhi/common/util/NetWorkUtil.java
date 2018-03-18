package com.union.yunzhi.common.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by meng on 2018/3/18.
 */

public class NetWorkUtil {
    //获取当前的网络状态
    public static final int CURRENT_NETWORK_STATES_NO=-1;//-1：没有网络
    public static final int CURRENT_NETWORK_STATES_WIFI=1;//1：WIFI网络
    public static final int CURRENT_NETWORK_STATES_WAP=2;//2：wap网络
    public static final int CURRENT_NETWORK_STATES_NET=3;//3：net网络
     public static int getAPNType(Context context) {
        //设置默认网路类型
        int netType = CURRENT_NETWORK_STATES_NO;
        //获取当前的网络管理器
        ConnectivityManager connManager = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        //获取网络信息
        NetworkInfo networkInfo = connManager.getActiveNetworkInfo();
        if (networkInfo == null) {
            return netType;
        }
        //得到网络类型
        int nType = networkInfo.getType();
        if (nType == ConnectivityManager.TYPE_MOBILE) {
            netType = networkInfo.getExtraInfo().toLowerCase().equals("cmnet") ? CURRENT_NETWORK_STATES_NET : CURRENT_NETWORK_STATES_WAP;
        } else if (nType == ConnectivityManager.TYPE_WIFI) {
            netType = CURRENT_NETWORK_STATES_WIFI;
        }
        return netType;
    }

    /**
     * 判断WiFi网络是否可用
     *
     * @param context
     * @return
     */
    public static boolean isWifiConn(Context context) {
        if (context != null) {
            ConnectivityManager mConnectivityManager = (ConnectivityManager) context
                    .getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo mWiFiNetworkInfo = mConnectivityManager
                    .getNetworkInfo(ConnectivityManager.TYPE_WIFI);
            if (mWiFiNetworkInfo != null) {
                return mWiFiNetworkInfo.isAvailable();
            }
        }
        return false;
    }

    /**
     * 判断数据流量是否可用
     *
     * @param context
     * @return
     */
    public static boolean isMobileConn(Context context) {
        if (context != null) {
            ConnectivityManager mConnectivityManager = (ConnectivityManager) context
                    .getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo mMobileNetworkInfo = mConnectivityManager
                    .getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
            if (mMobileNetworkInfo != null) {
                return mMobileNetworkInfo.isAvailable();
            }
        }
        return false;
    }

    /**
     * 判断是否有网络
     *
     * @param context
     * @return
     */
    public static boolean isNetworkConn(Context context) {
        if (context != null) {
            ConnectivityManager mConnectivityManager = (ConnectivityManager) context
                    .getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo mNetworkInfo = mConnectivityManager
                    .getActiveNetworkInfo();
            if (mNetworkInfo != null) {
                return mNetworkInfo.isAvailable();
            }
        }
        return false;
    }
}
