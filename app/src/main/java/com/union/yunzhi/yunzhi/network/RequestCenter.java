package com.union.yunzhi.yunzhi.network;

import com.union.yunzhi.factories.moudles.hometest.BaseHomeModle;
import com.union.yunzhi.factories.okhttp.CommonOkHttpClient;
import com.union.yunzhi.factories.okhttp.listener.DisposeDataHandle;
import com.union.yunzhi.factories.okhttp.listener.DisposeDataListener;
import com.union.yunzhi.factories.okhttp.request.CommonRequest;
import com.union.yunzhi.factories.okhttp.request.RequestParams;

/**
 * Created by meng on 2018/2/24.
 * @function 所有请求的中心
 */

public class RequestCenter {

    //根据参数发送所有post请求
    public static void postRequest(String url, RequestParams params, DisposeDataListener listener, Class<?> clazz) {
        CommonOkHttpClient.get(CommonRequest.
                createGetRequest(url, params), new DisposeDataHandle(listener, clazz));
    }


    
    public static void requestHomeData(String userName, String password, DisposeDataListener listener) {

        RequestParams params = new RequestParams();

        params.put("userName", userName);
        params.put("password", password);

        RequestCenter.postRequest(HttpConstants.HOME_URL, params, listener, BaseHomeModle.class);
    }




}
