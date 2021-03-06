package com.union.yunzhi.factories.okhttp.response;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.union.yunzhi.factories.okhttp.exception.OkHttpException;
import com.union.yunzhi.factories.okhttp.listener.DisposeDataHandle;
import com.union.yunzhi.factories.okhttp.listener.DisposeDataListener;
import com.union.yunzhi.factories.okhttp.listener.DisposeHandleCookieListener;
import com.union.yunzhi.factories.utils.LogUtils;

import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Headers;
import okhttp3.Response;

/**
 * @author vision
 * @function 专门处理JSON的回调
 */
public class CommonJsonCallback implements Callback {

    /**
     * 与服务器返回的字段的一一对应关系
     */
    protected final String RESULT_CODE = "ecode"; // 有返回则对于http请求来说是成功的，但还有可能是业务逻辑上的错误
    protected final int RESULT_CODE_VALUE = 0;
    protected final int RESULT_CODE_ERROR = 1;
    protected final int NOT_BACK = 100;
    protected final String BACK_MSG = "emsg";
    protected final String EMPTY_MSG = "";
    private final String TGA = "CommonJsonCallback";
    protected final String COOKIE_STORE = "Set-Cookie"; // decide the server it

   // protected final String COOKIE_STORE = "Set-Cookie"; // decide the server it
    // can has the value of
    // set-cookie2

    /**
     * 自定义异常
     */
    protected final int NETWORK_ERROR = -1; // the network relative error
    protected final int JSON_ERROR = -2; // the JSON relative error
    protected final int OTHER_ERROR = -3; // the unknow error
    protected final int WEB_ERROR = 1; //请求失败


    /**
     * 将其它线程的数据转发到UI线程
     */
    private Handler mDeliveryHandler;
    private DisposeDataListener mListener;
    private Class<?> mClass;

    public CommonJsonCallback(DisposeDataHandle handle) {
        this.mListener = handle.mListener;
        this.mClass = handle.mClass;
        this.mDeliveryHandler = new Handler(Looper.getMainLooper());
    }

    //请求失败处理
    @Override
    public void onFailure(final Call call, final IOException ioexception) {
        /**
         * 此时还在非UI线程，因此要转发
         */
        mDeliveryHandler.post(new Runnable() {
            @Override
            public void run() {
                Log.i(TGA,"q请求失败");
                mListener.onFailure(new OkHttpException(NETWORK_ERROR, ioexception));
            }
        });
    }

    @Override
    public void onResponse(final Call call, final Response response) throws IOException {
        final String result = response.body().string();
        final ArrayList<String> cookieLists = handleCookie(response.headers());
        mDeliveryHandler.post(new Runnable() {
            @Override
            public void run() {
                handleResponse(result);

                if (mListener instanceof DisposeHandleCookieListener) {
                    ((DisposeHandleCookieListener) mListener).onCookie(cookieLists);
                }
            }
        });
    }


    private ArrayList<String> handleCookie(Headers headers) {
        ArrayList<String> tempList = new ArrayList<String>();
        for (int i = 0; i < headers.size(); i++) {
            if (headers.name(i).equalsIgnoreCase(COOKIE_STORE)) {
                tempList.add(headers.value(i));
            }
        }
        return tempList;
    }



    /**
     * 出来服务器响应的数据
     * @param responseObj
     */
    private void handleResponse(Object responseObj) {

        if (responseObj == null || responseObj.toString().trim().equals("")) {
            mListener.onFailure(new OkHttpException(NETWORK_ERROR, EMPTY_MSG));
            LogUtils.i(TGA,"handleResponse error 返回结果为空");
            return;
        }


        try {
            /**
             * 协议确定后看这里如何修改
             */

            LogUtils.i(TGA,"对应的json---"+responseObj.toString());

            JSONObject result = new JSONObject(responseObj.toString());


            LogUtils.i(TGA,"对应的json"+result.toString());
            //尝试解析json
            if(result.has(RESULT_CODE)){
                LogUtils.i(TGA,"返回的json格式正确尝试解析");
                //判断是否正常响应
                if(result.getInt(RESULT_CODE) == RESULT_CODE_VALUE){


                    LogUtils.i(TGA,"返回码正确开始解析");
                    //不需要解析
                    if(mClass == null){
                        LogUtils.i(TGA,"不需要吧json解析成实体类");

                        if(result.length() == 2){
                            LogUtils.i(TGA,"特殊请求，不需要返回数据");
                            mListener.onSuccess(new NotCallBackData((Integer) result.get(RESULT_CODE),result.get(BACK_MSG)));

                        }

                        mListener.onSuccess(responseObj);
                    }else{//需要解析 144---156
                        //将我们的json转为我们的实体对象
                        LogUtils.i(TGA,"尝试将json转化为实体类");
                        Object obj = JSON.parseObject(result.toString(),mClass);
                        if(obj !=null){
                            //成功的转化成我们的实体对象
                            LogUtils.i(TGA,"成功的转化成我们的实体对象");
                            mListener.onSuccess(obj);
                        }else {
                            LogUtils.i(TGA,"解析异常");
                            mListener.onFailure(new OkHttpException(JSON_ERROR,EMPTY_MSG));
                        }
                    }//end else
                }else if(result.getInt(RESULT_CODE) == RESULT_CODE_ERROR) {
                    LogUtils.i(TGA,"ecode为1，请求失败");
                    mListener.onFailure(new OkHttpException((Integer) result.get(RESULT_CODE),result.get(BACK_MSG)));
                }else if(result.getInt(RESULT_CODE) == NOT_BACK){
                    //请求还是成功的
                    LogUtils.i(TGA,"ecode为"+NOT_BACK+"  "+"不需要返回解析数据");
                }
            }//end if
        } catch (Exception e) {
            mListener.onFailure(new OkHttpException(OTHER_ERROR, e.getMessage()));
            e.printStackTrace();
        }
    }
}