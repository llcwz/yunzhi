package com.union.yunzhi.yunzhi.network;

import com.union.yunzhi.factories.moudles.hometest.BaseHomeModle;
import com.union.yunzhi.factories.moudles.me.BaseMeModel;
import com.union.yunzhi.factories.moudles.me.PersonModel;
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


    /**
     * 用户登陆请求
     *
     * @param listener
     * @param userName
     * @param passwd
     */
    public static void login(String userName, String passwd, DisposeDataListener listener) {

        RequestParams params = new RequestParams();
        params.put("account", userName);
        params.put("password", passwd);
        RequestCenter.postRequest(HttpConstants.LOGIN_URL, params, listener, BaseMeModel.class);
    }

    public static void requestChangePassword(String account, String newPassword, DisposeDataListener listener) {
        RequestParams params = new RequestParams();
        params.put("account", account);
        params.put("password", newPassword);
        RequestCenter.postRequest(HttpConstants.CHANGE_PASSWORD_URL, params, listener, BaseMeModel.class);
    }


}
