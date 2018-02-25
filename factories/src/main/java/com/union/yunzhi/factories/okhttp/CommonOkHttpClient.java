package com.union.yunzhi.factories.okhttp;

import com.union.yunzhi.factories.okhttp.listener.DisposeDataHandle;
import com.union.yunzhi.factories.okhttp.response.CommonJsonCallback;

import java.util.concurrent.TimeUnit;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;

import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;

/**
 * Created by meng on 2018/2/22.
 * @author meng
 * @function 请求的发送，请求的配置,htpps支持
 */

public class CommonOkHttpClient {

    private static final int TIME_OUT = 30;//请求超时参数

    private static OkHttpClient mOkHttpClient;

    //为我们的client配置参数
    static{
        OkHttpClient.Builder okhttpBuilder = new OkHttpClient.Builder();

        //为构建在填充参数
        okhttpBuilder
                .connectTimeout(TIME_OUT, TimeUnit.SECONDS)
                .readTimeout(TIME_OUT,TimeUnit.SECONDS)
                .writeTimeout(TIME_OUT,TimeUnit.SECONDS)
                .followRedirects(true);//允许请求重定向

        //https支持

        okhttpBuilder.hostnameVerifier(new HostnameVerifier() {
            @Override
            public boolean verify(String hostname, SSLSession session) {
                return true;
            }
        });

        //支持ssl
      //  okhttpBuilder.sslSocketFactory(HttpsUtils.getSslSocketFactory());

        mOkHttpClient = okhttpBuilder.build();

    }

    /**
     * 发送具体的http/https的请求
     * @param request
     * @param callback
     * @return Call
     */
    public static Call sendRequest(Request request, CommonJsonCallback callback){

        Call call = mOkHttpClient.newCall(request);
        //将其入队列，方便后期取消该次请求
        call.enqueue(callback);

        return call;
    }


    /**
     * 通过构造好的Request,Callback去发送请求
     *
     * @param request
     * @param handle
     */
    public static Call get(Request request, DisposeDataHandle handle) {
        Call call = mOkHttpClient.newCall(request);

        call.enqueue(new CommonJsonCallback(handle));

        return call;
    }

    public static Call post(Request request, DisposeDataHandle handle) {
        Call call = mOkHttpClient.newCall(request);
        call.enqueue(new CommonJsonCallback(handle));
        return call;
    }


}
