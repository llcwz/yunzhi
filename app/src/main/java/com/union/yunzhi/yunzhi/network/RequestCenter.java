package com.union.yunzhi.yunzhi.network;

import android.util.Log;

import com.union.yunzhi.factories.moudles.hometest.BaseHomeModle;
import com.union.yunzhi.factories.moudles.me.BaseMeModel;
import com.union.yunzhi.factories.moudles.me.WorkModel;
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

    private final String TGA = "RequestCenter";

    //根据参数发送所有post请求
    public static void postRequest(String url, RequestParams params, DisposeDataListener listener, Class<?> clazz) {
        CommonOkHttpClient.get(CommonRequest.
                createPostRequest(url, params), new DisposeDataHandle(listener, clazz));

    }



    public static void requestHomeData(String userName, String password, DisposeDataListener listener) {

        //RequestParams params = new RequestParams();

       // params.put("userName", userName);
       // params.put("password", password);

        Log.i("requestHomeData","ok");

        RequestCenter.postRequest(HttpConstants.HOME_URL, null, listener, BaseHomeModle.class);
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

        Log.d("Login", "login: " + userName + "|" + passwd);
        Log.d("Login", "login: "+params.toString());
        RequestCenter.postRequest(HttpConstants.LOGIN_URL, params, listener, BaseMeModel.class);
    }

    /**
     * 用户请求更改密码
     * @param account
     * @param newPassword
     * @param listener
     */
    public static void requestChangePassword(String account, String newPassword, DisposeDataListener listener) {
        RequestParams params = new RequestParams();
        params.put("account", account);
        params.put("password", newPassword);
        RequestCenter.postRequest(HttpConstants.CHANGE_PASSWORD_URL, params, listener, BaseMeModel.class);
    }


    /**
     * 用户请求发布新任务
     * @param id 任务的id
     * @param name 任务的标题
     * @param course 任务所属的课程
     * @param type 任务的类型
     * @param start 任务开始时间
     * @param end 任务结束时间
     * @param state 任务的状态
     * @param promulgator 任务发布者
     * @param time 任务的时间
     * @param viewType 任务所属的view类型
     * @param listener  回调接口
     */

    public static void requestAddWork(String id, String name, String course, String type, String start, String end, String state, String promulgator, String time, int viewType, DisposeDataListener listener){
        RequestParams params = new RequestParams();
        params.put("mId", id);
        params.put("mName", name);
        params.put("mCourse", course);
        params.put("mType", type);
        params.put("mStart", start);
        params.put("mEnd", end);
        params.put("mPromulgator", promulgator);
        params.put("mState", state);
        params.put("mTime", time);
        params.put("mViewType", " " + viewType);
        RequestCenter.postRequest(HttpConstants.ADD_WORK_URL, params, listener, WorkModel.class);

    }


}
