package com.union.yunzhi.yunzhi.communicationutils;

import android.content.Context;
import android.widget.Toast;

import com.union.yunzhi.common.util.LogUtils;
import com.union.yunzhi.factories.moudles.communication.BaseCommunicationModel;
import com.union.yunzhi.factories.moudles.communication.CommunicationConstant;
import com.union.yunzhi.factories.moudles.communication.PostModel;
import com.union.yunzhi.factories.moudles.me.UserModel;
import com.union.yunzhi.factories.okhttp.exception.OkHttpException;
import com.union.yunzhi.factories.okhttp.listener.DisposeDataListener;
import com.union.yunzhi.yunzhi.manager.DialogManager;
import com.union.yunzhi.yunzhi.network.RequestCenter;

import java.util.List;

/** 评论功能
 * Created by CrazyGZ on 2018/3/11.
 */

public class OpinionUtils {

    private UserModel mUser;
    private Context mContext; // 上下文

    public interface OnAddPostListener {
        void getPost(PostModel postModel);
    }

    public interface OnRequestPostListener {
        void getPosts(List<PostModel> postModels);
    }

    public static OpinionUtils newInstance(UserModel user, Context context) {
        return new OpinionUtils(user, context);
    }

    private OpinionUtils(UserModel user, Context context) {
        mUser = user;
        mContext = context;
    }


    /**
     * @function 添加帖子
     * @param tag
     * @param title
     * @param content
     * @param listener
     */
    public void addPost(int tag, String title, String content, final OnAddPostListener listener) {

        DialogManager.getInstnce().showProgressDialog(mContext);
        LogUtils.d("addPost", "" + mUser.getAccount() +
                "," + title + "," + content + "," + tag);
        RequestCenter.requestAddPost(mUser.getAccount(),
                mUser.getPriority(),
                tag,
                title,
                content, new DisposeDataListener() {
                    @Override
                    public void onSuccess(Object responseObj) {
                        DialogManager.getInstnce().dismissProgressDialog();
                        LogUtils.d("addPostRequest", responseObj.toString());
                        BaseCommunicationModel baseCommunicationModel = (BaseCommunicationModel) responseObj;
                        if (baseCommunicationModel.ecode == CommunicationConstant.ECODE) {

                            LogUtils.d("postRequest", baseCommunicationModel.data.get(0).toString());
                            listener.getPost(baseCommunicationModel.data.get(0));
                            Toast.makeText(mContext, "发布成功", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(mContext, "" + responseObj, Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Object reasonObj) {
                        DialogManager.getInstnce().dismissProgressDialog();
                        OkHttpException okHttpException = (OkHttpException) reasonObj;
                        if (okHttpException.getEcode() == 1) {
                            Toast.makeText(mContext, "" + okHttpException.getEmsg(), Toast.LENGTH_SHORT).show();
                        } else if (okHttpException.getEcode() == -1){
                            Toast.makeText(mContext, "网络连接错误", Toast.LENGTH_SHORT).show();
                        } else if (okHttpException.getEcode() == -2) {
                            Toast.makeText(mContext, "解析错误" , Toast.LENGTH_SHORT).show();
                        } else if (okHttpException.getEcode() == -3) {
                            Toast.makeText(mContext, "未知错误", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

    }

    /**
     * @function 获取帖子
     * @param tag
     * @param listener
     */
    public void getPosts(int tag, final OnRequestPostListener listener) {
        DialogManager.getInstnce().showProgressDialog(mContext);
        RequestCenter.requestPost(tag,
                new DisposeDataListener() {
                    @Override
                    public void onSuccess(Object responseObj) {
                        DialogManager.getInstnce().dismissProgressDialog();
                        BaseCommunicationModel baseCommunicationModel = (BaseCommunicationModel) responseObj;
                        if (baseCommunicationModel.ecode == CommunicationConstant.ECODE) {
                            listener.getPosts(baseCommunicationModel.data);
                            for (PostModel postModel : baseCommunicationModel.data) {
                                LogUtils.d("postMessage", postModel.toString());
                            }
                        } else {
                            Toast.makeText(mContext, "" + baseCommunicationModel.emsg, Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Object reasonObj) {
                        DialogManager.getInstnce().dismissProgressDialog();
                        OkHttpException okHttpException = (OkHttpException) reasonObj;
                        if (okHttpException.getEcode() == 1) {
                            Toast.makeText(mContext, "" + okHttpException.getEmsg(), Toast.LENGTH_SHORT).show();
                        } else if (okHttpException.getEcode() == -1){
                            Toast.makeText(mContext, "网络连接错误", Toast.LENGTH_SHORT).show();
                        } else if (okHttpException.getEcode() == -2) {
                            Toast.makeText(mContext, "解析错误" , Toast.LENGTH_SHORT).show();
                        } else if (okHttpException.getEcode() == -3) {
                            Toast.makeText(mContext, "未知错误", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}
