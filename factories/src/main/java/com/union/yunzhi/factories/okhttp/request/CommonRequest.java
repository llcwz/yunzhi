package com.union.yunzhi.factories.okhttp.request;

import java.util.Map;

import okhttp3.FormBody;
import okhttp3.Request;

/**
 * Created by meng on 2018/2/22.
 * @author meng
 * @function 接收请求参数，为我们生成Request对象
 */

public class CommonRequest {

    /**
     *
     * @param url
     * @param params
     * @return  返回一个创建好的Request对象
     */
    public static Request createPostRequest(String url,RequestParams params){

        FormBody.Builder mFormBodyBuild = new FormBody.Builder();

        if(params != null){
            for(Map.Entry<String,String> entry:params.urlParams.entrySet()){
                //将请求参数逐一遍历添加到我们的请求构建中
                mFormBodyBuild.add(entry.getKey(),entry.getValue());
            }
        }

        //通过请求构建的bulid方法来构建我们的请求体
        FormBody mFormBody = mFormBodyBuild.build();


        //返回我们的请求对象
        return new Request
                .Builder()
                .url(url)
                .post(mFormBody)
                .build();

    }

    /**
     *
     * @param url
     * @param params
     * @return 返回一个Get类型的请求对象
     */
    public static Request createGetRequest(String url,RequestParams params){
        StringBuilder urlBuilder = new StringBuilder(url).append("?");
        if(params != null){

            for(Map.Entry<String,String> entry:params.urlParams.entrySet()){

                //拼接我们的get的请求
                urlBuilder
                        .append(entry.getKey())
                        .append("=")
                        .append(entry.getValue())
                        .append("&");

            }

        }

        //返回一个get请求
        return new Request
                .Builder()
                .url(urlBuilder.substring(0,urlBuilder.length()-1))//去掉末尾的&符号
                .get()
                .build();

    }
}
