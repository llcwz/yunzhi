package com.union.yunzhi.yunzhi.communicationutils;

import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.union.yunzhi.common.util.LogUtils;
import com.union.yunzhi.factories.moudles.classfication.beans.question.QuestionBean;
import com.union.yunzhi.factories.moudles.communication.BaseLikeModel;
import com.union.yunzhi.factories.moudles.communication.CommentModel;
import com.union.yunzhi.factories.moudles.communication.CommunicationConstant;
import com.union.yunzhi.factories.moudles.communication.LikeModel;
import com.union.yunzhi.factories.moudles.communication.PostModel;
import com.union.yunzhi.factories.moudles.me.UserModel;
import com.union.yunzhi.factories.okhttp.exception.OkHttpException;
import com.union.yunzhi.factories.okhttp.listener.DisposeDataListener;
import com.union.yunzhi.factories.okhttp.response.NotCallBackData;
import com.union.yunzhi.yunzhi.R;
import com.union.yunzhi.yunzhi.manager.DialogManager;
import com.union.yunzhi.yunzhi.network.RequestCenter;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 *  @fuction 点赞功能
 * Created by CrazyGZ on 2018/3/11.
 */

public class LikeUtils {

    private String mId; // 点赞主体的id
    private String mTag; // 点赞的类型：1给帖子点赞,3给视频留言点赞，2给评论点赞
    private UserModel mUser; // 点赞者
    private Context mContext; // 上下文
    private ImageView mLike; // 点赞图标
    private TextView mLikeCount; // 点赞数


    public static LikeUtils newInstance(String id,String tag,UserModel user, Context context, ImageView like, TextView likeCount) {
        return new LikeUtils(id,tag,user, context, like, likeCount);
    }

    private LikeUtils(String id,String tag, UserModel user, Context context, ImageView like, TextView likeCount) {
        mId = id;
        mTag = tag;
        mUser = user;
        mContext = context;
        mLike = like;
        mLikeCount = likeCount;
    }


    /**
     * @function 点赞评论
     */
    public void iLike( ) {
        if (mUser != null) {
            loadLike();
        }
    }
    /**
     * 上传点赞
     */
    private  void loadLike() {
        DialogManager.getInstnce().showProgressDialog(mContext);
        LogUtils.d("likeMessage",mId + "|" + mTag + "|" + mUser);
        if (mTag.equals(CommunicationConstant.LIKE_TAG_POST)) {
            RequestCenter.requestLikePost(mId,
                    mUser.getAccount(),
                    new DisposeDataListener() {
                        @Override
                        public void onSuccess(Object responseObj) {
                            NotCallBackData notCallBackData = (NotCallBackData) responseObj;
                            if (notCallBackData.getEcode() == CommunicationConstant.ECODE) {
                                LogUtils.d("like", "+1");
                                Glide.with(mContext).load(R.drawable.iv_like_select).into(mLike);
                                mLikeCount.setText(Integer.parseInt(mLikeCount.getText().toString())+ 1 + "");
                                mLike.setClickable(false); // 只能被点击一次
                                DialogManager.getInstnce().dismissProgressDialog();
                            } else {
                                LogUtils.d("like", "你已经点过赞啦");
//                        Glide.with(mContext).load(R.drawable.iv_like_select).iSnto(mLike);
                                mLike.setClickable(false); // 只能被点击一次
                            }
                        }

                        @Override
                        public void onFailure(Object reasonObj) {
                            DialogManager.getInstnce().dismissProgressDialog();
                            OkHttpException okHttpException = (OkHttpException) reasonObj;
                            if (okHttpException.getEcode() == 1) {
                                Toast.makeText(mContext, "你已经点过赞啦" , Toast.LENGTH_SHORT).show();
                            } else if (okHttpException.getEcode() == -1){
                                Toast.makeText(mContext, "网络连接错误", Toast.LENGTH_SHORT).show();
                            } else if (okHttpException.getEcode() == -2) {
                                Toast.makeText(mContext, "解析错误" , Toast.LENGTH_SHORT).show();
                            } else if (okHttpException.getEcode() == -3) {
                                Toast.makeText(mContext, "未知错误", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        } else if (mTag.equals(CommunicationConstant.LIKE_TAG_COMMENT)) {
            RequestCenter.requestLikeComment(mId,
                    mUser.getAccount(),
                    new DisposeDataListener() {
                        @Override
                        public void onSuccess(Object responseObj) {
                            NotCallBackData notCallBackData = (NotCallBackData) responseObj;
                            if (notCallBackData.getEcode() == CommunicationConstant.ECODE) {
                                LogUtils.d("like", "+1");
                                Glide.with(mContext).load(R.drawable.iv_like_select).into(mLike);
                                mLikeCount.setText(Integer.parseInt(mLikeCount.getText().toString())+ 1 + "");
                                mLike.setClickable(false); // 只能被点击一次
                                DialogManager.getInstnce().dismissProgressDialog();
                            } else {
                                LogUtils.d("like", "你已经点过赞啦");
//                        Glide.with(mContext).load(R.drawable.iv_like_select).iSnto(mLike);
                                mLike.setClickable(false); // 只能被点击一次
                            }
                        }

                        @Override
                        public void onFailure(Object reasonObj) {
                            DialogManager.getInstnce().dismissProgressDialog();
                            OkHttpException okHttpException = (OkHttpException) reasonObj;
                            if (okHttpException.getEcode() == 1) {
                                Toast.makeText(mContext, "你已经点过赞啦", Toast.LENGTH_SHORT).show();
                            } else if (okHttpException.getEcode() == -1){
                                Toast.makeText(mContext, "网络连接错误", Toast.LENGTH_SHORT).show();
                            } else if (okHttpException.getEcode() == -2) {
                                Toast.makeText(mContext, "解析错误" , Toast.LENGTH_SHORT).show();
                            } else if (okHttpException.getEcode() == -3) {
                                Toast.makeText(mContext, "未知错误", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        } else if (mTag.equals(CommunicationConstant.LIKE_TAG_QUESTION)) {

        }

    }
}
