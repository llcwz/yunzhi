package com.union.yunzhi.yunzhi.network;

import android.util.Log;


import com.union.yunzhi.common.util.LogUtils;
import com.union.yunzhi.factories.moudles.classfication.beans.classfication.BaseCourseShowBean;
import com.union.yunzhi.factories.moudles.classfication.beans.details.BaseDetailsBean;
import com.union.yunzhi.factories.moudles.classfication.beans.question.BaseQuestionBean;
import com.union.yunzhi.factories.moudles.communication.BaseCommentModel;
import com.union.yunzhi.factories.moudles.communication.BaseCommunicationModel;

import com.union.yunzhi.factories.moudles.classfication.beans.drawer.BaseDrawerBean;

import com.union.yunzhi.factories.moudles.hometest.BaseHomeModle;

import com.union.yunzhi.factories.moudles.me.BaseCourseModel;
import com.union.yunzhi.factories.moudles.me.BaseGradeModel;
import com.union.yunzhi.factories.moudles.me.BaseMessageModel;
import com.union.yunzhi.factories.moudles.me.BaseUserModel;
import com.union.yunzhi.factories.moudles.me.BaseWorkModel;
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

    //根据参数发送所有post请求，直接拼接整体
    public static void postRequestO(String url, RequestParams params, DisposeDataListener listener, Class<?> clazz) {
        CommonOkHttpClient.get(CommonRequest.
                createPostRequestO(url, params), new DisposeDataHandle(listener, clazz));

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
        LogUtils.d("Login", "login: " + userName + "|" + passwd);
        RequestCenter.postRequest(HttpConstants.LOGIN_URL, params, listener, BaseUserModel.class);
    }

    /**
     * 用户请求更改密码
     * @param account
     * @param newPassword
     * @param listener
     */
    public static void requestChangePassword(int priority, String account, String newPassword, DisposeDataListener listener) {
        RequestParams params = new RequestParams();
        params.put("priority", "" + priority);
        params.put("account", account);
        params.put("password", newPassword);
        LogUtils.d("changePassword", account + "|" + newPassword);
        RequestCenter.postRequest(HttpConstants.CHANGE_PASSWORD_URL, params, listener, BaseUserModel.class);
    }

    /**
     * @function 获取我的课程
     * @param account
     * @param listener
     */
    public static void requestMyCourse(String account, DisposeDataListener listener) {
        RequestParams params = new RequestParams();
        params.put("account", account);
        RequestCenter.postRequest(HttpConstants.MY_COURSE_URL, params, listener, BaseCourseModel.class);
    }

    /**
     * @function 获取我的消息
     * @param account
     * @param listener
     */
    public static void requestMyMessage(String account, DisposeDataListener listener) {
        RequestParams params = new RequestParams();
        params.put("account", account);
        RequestCenter.postRequest(HttpConstants.MY_MESSAGE_URL, params, listener, BaseMessageModel.class);
    }


    /**
     * 请求我的任务
     * @param account
     * @param listener
     */
    public static void requestMyWork(String account, DisposeDataListener listener) {
        RequestParams params = new RequestParams();
        params.put("account", account);
        RequestCenter.postRequest(HttpConstants.MY_WORK_URL, params, listener, BaseWorkModel.class);
    }



    /**
     * 用户请求发布新任务
     * @param account 用户的id
     * @param id 任务的id
     * @param name 任务的标题
     * @param course 任务所属的课程
     * @param type 任务的类型
     * @param start 任务开始时间
     * @param end 任务结束时间
     * @param state 任务的状态
     * @param promulgator 任务发布者
     * @param time 任务的时间
     * @param listener  回调接口
     */

    public static void requestAddWork(String account,
                                      String id,
                                      String name,
                                      String course,
                                      String type,
                                      String start,
                                      String end,
                                      String state,
                                      String promulgator,
                                      String time,
                                      DisposeDataListener listener){
        RequestParams params = new RequestParams();
        params.put("account", account);
        params.put("mId", id);
        params.put("mName", name);
        params.put("mCourse", course);
        params.put("mType", type);
        params.put("mStart", start);
        params.put("mEnd", end);
        params.put("mPromulgator", promulgator);
        params.put("mState", state);
        params.put("mTime", time);
        params.put("mViewType", " " + 1); // 老师上传的任务肯定属于老师视图，这里直接置为1了
        RequestCenter.postRequest(HttpConstants.ADD_WORK_URL, params, listener, BaseWorkModel.class);

    }

    /**
     * @function 获取成绩
     * @param account
     * @param listener
     */
    public static void requestGrade(String account, DisposeDataListener listener) {
        RequestParams params = new RequestParams();
        params.put("account", account);
        RequestCenter.postRequest(HttpConstants.MY_GRADE_URL, params, listener, BaseGradeModel.class);
    }


    /**
     * @function 获取帖子
     * @param tag
     * @param listener
     */
    public static void requestPost(int tag, DisposeDataListener listener) {
        RequestParams params = new RequestParams();
        params.put("mTag","" + tag);
        RequestCenter.postRequest(HttpConstants.POST_URL, params, listener, BaseCommunicationModel.class);
    }

    /**
     * @function 上传点赞
     * @param postOrCommentOrQuestionId 给点赞的那个东西的id
     * @param account 点赞者的id
     * @param icon 点赞者的头像
     * @param author 点赞者的姓名
     * @param time 点赞时的时间
     */
    public static void requestLike(String postOrCommentOrQuestionId,String account,String icon, String author, String time, DisposeDataListener listener) {
        RequestParams params = new RequestParams();
        params.put("mId", postOrCommentOrQuestionId);
        params.put("mAccount", account);
        params.put("mIcon", icon);
        params.put("mAuthor", author);
        params.put("mTime", time);
        RequestCenter.postRequest(HttpConstants.LIKE_URL, params, listener, null);
    }

    /**
     * @function 请求评论
     * @param account 评论者的id
     * @param postId 评论的帖子
     * @param id 这条评论的id
     * @param icon
     * @param author
     * @param time
     * @param content
     * @param listener
     */
    public static void requestComment(String account,String postId, String id,int tag, String icon, String author, String time, String content, DisposeDataListener listener) {
        RequestParams params = new RequestParams();
        params.put("account", account);
        params.put("mId", postId);
        params.put("id", id);
        params.put("mTag","" +tag);
        params.put("mIcon", icon);
        params.put("mAuthor", author);
        params.put("mTime", time);
        params.put("mContent", content);
        params.put("mLikeModels", "");
        RequestCenter.postRequest(HttpConstants.ADD_COMMENT_URL, params, listener, BaseCommentModel.class);
    }


    /**
     *
     * @param account 发帖人的id
     * @param tag 帖子的类型
     * @param title 帖子标题
     * @param content 帖子内容
     * @param listener
     */
    public static void requestAddPost(String account,int tag,String title, String content, DisposeDataListener listener) {
        RequestParams params = new RequestParams();
        params.put("userId", account);
        params.put("tag", "" + tag);
        params.put("title", title);
        params.put("content", content);
        RequestCenter.postRequest(HttpConstants.ADD_POST_URL, params, listener, BaseCommunicationModel.class);
    }


    /**
     * @function 获取课程下的问题交流数据
     * @param id
     * @param listener
     */
    public static void requestQuestion(String id, DisposeDataListener listener) {
        RequestParams params = new RequestParams();
        params.put("courseId","" + id);
        RequestCenter.postRequest(HttpConstants.QUESTION_URL, params, listener, BaseQuestionBean.class);
    }

    /**
     *  侧滑栏抽屉请求
     */
    public static void requestDrawer(String requestUrl, DisposeDataListener listener){

        RequestCenter.postRequest(HttpConstants.ACADEMY_COURSE,null,listener, BaseDrawerBean.class);
    }

    /**
     * 请求分类轮播图
     */
    public static void requestRecycle(){


    }

    /**
     * 请求课程列表
     */
    public static void requestCourse(String courseId,DisposeDataListener listener){

        RequestParams params=new RequestParams();
        params.put("courseid",courseId);
        RequestCenter.postRequest(HttpConstants.GET_COURSE_TITLE,params,listener, BaseCourseShowBean.class);
    }

    /**
     * 请求课程详情信息
     */
    public static void requestCourseDeatails(String courseid,String teacherid,DisposeDataListener listener){

        RequestParams params=new RequestParams();
        params.put("courseid",courseid);
        params.put("teacherid",teacherid);

        RequestCenter.postRequest(HttpConstants.GET_ALL_DETAILS_MSG,params,listener, BaseDetailsBean.class);
    }

    /**
     * 请求视频
     */
    public static void requestVideo(){

    }

    /**
     * 请求视频评论
     */
    public static void requestVideoComment(){

    }


}
