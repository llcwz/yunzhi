package com.union.yunzhi.yunzhi.communicationutils;

import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.union.yunzhi.factories.moudles.classfication.beans.question.QuestionBean;
import com.union.yunzhi.factories.moudles.communication.CommentModel;
import com.union.yunzhi.factories.moudles.communication.LikeModel;
import com.union.yunzhi.factories.moudles.communication.PostModel;
import com.union.yunzhi.factories.moudles.me.UserModel;
import com.union.yunzhi.factories.okhttp.exception.OkHttpException;
import com.union.yunzhi.factories.okhttp.listener.DisposeDataListener;
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
    private String mTag; // 点赞的类型：0给帖子或视频留言点赞，1给评论点赞
    private UserModel mUser; // 点赞者
    private Context mContext; // 上下文
    private ImageView mLike; // 点赞图标
    private TextView mLikeCount; // 点赞数

    // 回调接口，利用它来实现如果用户没点过赞就点赞，然后上传点赞数据的功能
    private  OnLikeListener mOnLikeListener = new OnLikeListener() {
        @Override
        public void isLike(boolean result) {
            if (result) {
                Glide.with(mContext).load(R.drawable.iv_like_select).into(mLike);
                mLike.setClickable(false);
            } else {
                loadLike();
            }
        }
    };

    // 这条赞是否被当前用户赞过的接口
    private interface OnLikeListener {
        /**
         * @function 根据参数的真假处理相应的逻辑
         * @param result 得到的结果,真表示用户点过赞，假则没有
         */
        void isLike(boolean result);
    }


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
     * @param commentLike
     */
    public void iLike(List<String> commentLike) {
            if (!commentLike.equals("0")) { // 该条评论的赞不为0,那么有可能该用户赞过该条评论
                isLike(commentLike); // 判断该用户是否点赞了该条评论
            } else { // 该条评论赞数为0，用户肯定可以点击
                loadLike();
            }
    }


    /**
     *  @fuunction 查看当前用户是否赞了这条评论
     *  @param likeUserId 这条评论的赞的集合
     */
    private   void isLike(final List<String> likeUserId) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (String id : likeUserId) { // 遍历赞的集合，匹配当前用户
                    if (id.equals(mUser.getAccount())) {
                        mOnLikeListener.isLike(true);
                        return;
                    }
                }
                mOnLikeListener.isLike(false);
            }
        }).start();
    }

    /**
     * 上传点赞
     */
    private  void loadLike() {

        DialogManager.getInstnce().showProgressDialog(mContext);
        RequestCenter.requestLike(mId,
                mTag,
                mUser.getAccount(),
                new DisposeDataListener() {
                    @Override
                    public void onSuccess(Object responseObj) {
                        Glide.with(mContext).load(R.drawable.iv_like_select).into(mLike);
                        mLikeCount.setText(Integer.parseInt(mLikeCount.getText().toString())+ 1 + "");
                        mLike.setClickable(false); // 只能被点击一次
                        DialogManager.getInstnce().dismissProgressDialog();
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
