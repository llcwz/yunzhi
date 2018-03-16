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

    private String mId; // 点赞的类型：0给帖子点赞，1给评论点赞，2给问题点赞
    private UserModel mUser;
    private Context mContext; // 上下文
    private ImageView mLike; // 点赞图标
    private TextView mLikeCount; // 点赞数

    // 回调接口，利用它来实现如果用户没点过赞就点赞，然后上传点赞数据的功能
    private  OnLikeListener mOnLikeListener = new OnLikeListener() {
        @Override
        public void isLike(String id, boolean result) {
            if (result) {
                Glide.with(mContext).load(R.drawable.iv_like_select).into(mLike);
                mLike.setClickable(false);
            } else {
                loadLike(mId,mUser,mContext,mLike, mLikeCount);
            }
        }
    };


    // 这条赞是否被当前用户赞过的接口
    private interface OnLikeListener {
        /**
         * @function 根据参数的真假处理相应的逻辑
         * @param id 帖子或者评论的id
         * @param result 得到的结果,真表示用户点过赞，假则没有
         */
        void isLike(String id,boolean result);
    }

    /**
     *
     * @param user  用户
     * @param context 上下文
     * @param like 点赞图标
     * @param likeCount 点赞数
     * @return
     */
    public static LikeUtils newInstance(String id,UserModel user, Context context, ImageView like, TextView likeCount) {
        return new LikeUtils(id,user, context, like, likeCount);
    }

    private LikeUtils(String id, UserModel user, Context context, ImageView like, TextView likeCount) {
        mId = id;
        mUser = user;
        mContext = context;
        mLike = like;
        mLikeCount = likeCount;
    }

    /**
     * @function 用户给评论点赞
     * @param commentModel
     */
    public void checkedCommentLike(CommentModel commentModel) {
            if (commentModel.getLikeModels().size() != 0) { // 该条评论的赞不为0,那么有可能该用户赞过该条评论
                isLike(commentModel.getId(),commentModel.getLikeModels(), mUser.getAccount()); // 判断该用户是否点赞了该条评论
            } else { // 该条评论赞数为0，用户肯定可以点击
                loadLike(mId,mUser,mContext,mLike, mLikeCount);
            }
    }

    /**
     *  @function 用户给帖子点赞
     * @param postModel
     */
//    public   void checkedPostLike(PostModel postModel) {
//        if (!postModel.getFavour().equals("0")) { // 该帖子的赞不为0,那么有可能该用户赞过该帖子
//            isLike(postModel.getId(),postModel.getLikeModels(), mUser.getAccount()); // 判断该用户是否点赞了该条评论
//        } else { // 该帖子的赞数为0，用户肯定可以点击
//            loadLike(mId,mUser,mContext,mLike, mLikeCount);
//        }
//    }

    /**
     * @function 用户给问题点赞
     * @param questionBean
     */
    public   void checkedQuestionLike(QuestionBean questionBean) {
        if (questionBean.likeModels.size() != 0) { // 该帖子的赞不为0,那么有可能该用户赞过该帖子
            isLike(questionBean.id,questionBean.likeModels, mUser.getAccount()); // 判断该用户是否点赞了该条评论
        } else { // 该帖子的赞数为0，用户肯定可以点击
            loadLike(mId,mUser,mContext,mLike, mLikeCount);
        }
    }

    /**
     *  @fuunction 查看当前用户是否赞了这条评论
     *  @param id 帖子或者评论的id
     * @param data 这条评论或帖子的所有点赞人集合
     * @param account 当前用户的id
     */
    private   void isLike(final String id, final List<LikeModel> data, final String account) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (LikeModel likeModel : data) { // 遍历赞的集合，匹配当前用户
                    if (likeModel.getAccount().equals(account)) {
                        mOnLikeListener.isLike(id,true);
                        return;
                    }
                }
                mOnLikeListener.isLike(id,false);
            }
        }).start();
    }

    /**
     * 上传点赞
     */
    private  void loadLike(String id,UserModel userModel, final Context context, final ImageView like, final TextView likeCount) {

        DialogManager.getInstnce().showProgressDialog(context);
        // 当前系统时间
        String time = new SimpleDateFormat("yyyy.MM.dd HH:mm").format(new Date(System.currentTimeMillis()));
        RequestCenter.requestLike(mId,
                userModel.getAccount(),
                userModel.getPhotourl(),
                userModel.getName(),
                time,
                new DisposeDataListener() {
                    @Override
                    public void onSuccess(Object responseObj) {
                        Glide.with(context).load(R.drawable.iv_like_select).into(like);
                        likeCount.setText(Integer.parseInt(likeCount.getText().toString())+ 1 + "");
                        like.setClickable(false); // 只能被点击一次
                        DialogManager.getInstnce().dismissProgressDialog();
                    }

                    @Override
                    public void onFailure(Object reasonObj) {
                        DialogManager.getInstnce().dismissProgressDialog();
                        OkHttpException okHttpException = (OkHttpException) reasonObj;
                        if (okHttpException.getEcode() == 1) {
                            Toast.makeText(context, "" + okHttpException.getEmsg(), Toast.LENGTH_SHORT).show();
                        } else if (okHttpException.getEcode() == -1){
                            Toast.makeText(context, "网络连接错误", Toast.LENGTH_SHORT).show();
                        } else if (okHttpException.getEcode() == -2) {
                            Toast.makeText(context, "解析错误" , Toast.LENGTH_SHORT).show();
                        } else if (okHttpException.getEcode() == -3) {
                            Toast.makeText(context, "未知错误", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}
