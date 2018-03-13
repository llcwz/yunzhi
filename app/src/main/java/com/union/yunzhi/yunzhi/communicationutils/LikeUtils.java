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
import com.union.yunzhi.factories.okhttp.listener.DisposeDataListener;
import com.union.yunzhi.yunzhi.R;
import com.union.yunzhi.yunzhi.manager.DialogManager;
import com.union.yunzhi.yunzhi.manager.UserManager;
import com.union.yunzhi.yunzhi.network.RequestCenter;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 *  @fuction 点赞功能
 * Created by CrazyGZ on 2018/3/11.
 */

public class LikeUtils {

    private int mTag; // 点赞的类型：0给帖子点赞，1给评论点赞，2给问题点赞
    private UserManager mUserManager;
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
                loadLike(mTag,mUserManager,mContext,id,mLike, mLikeCount);
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
     * @param userManager 用户
     * @param context 上下文
     * @param like 点赞图标
     * @param likeCount 点赞数
     * @return
     */
    public static LikeUtils newInstance(int tag,UserManager userManager, Context context, ImageView like, TextView likeCount) {
        return new LikeUtils(tag,userManager, context, like, likeCount);
    }

    private LikeUtils(int tag,UserManager userManager, Context context, ImageView like, TextView likeCount) {
        mTag = tag;
        mUserManager = userManager;
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
                isLike(commentModel.getId(),commentModel.getLikeModels(), mUserManager.getPerson().getAccount()); // 判断该用户是否点赞了该条评论
            } else { // 该条评论赞数为0，用户肯定可以点击
                loadLike(mTag,mUserManager,mContext,commentModel.getId(),mLike, mLikeCount);
            }
    }

    /**
     *  @function 用户给帖子点赞
     * @param postModel
     */
    public   void checkedPostLike(PostModel postModel) {
        if (postModel.getLikeModels().size() != 0) { // 该帖子的赞不为0,那么有可能该用户赞过该帖子
            isLike(postModel.getId(),postModel.getLikeModels(), mUserManager.getPerson().getAccount()); // 判断该用户是否点赞了该条评论
        } else { // 该帖子的赞数为0，用户肯定可以点击
            loadLike(mTag,mUserManager,mContext,postModel.getId(),mLike, mLikeCount);
        }
    }

    /**
     * @function 用户给问题点赞
     * @param questionBean
     */
    public   void checkedQuestionLike(QuestionBean questionBean) {
        if (questionBean.likeModels.size() != 0) { // 该帖子的赞不为0,那么有可能该用户赞过该帖子
            isLike(questionBean.id,questionBean.likeModels, mUserManager.getPerson().getAccount()); // 判断该用户是否点赞了该条评论
        } else { // 该帖子的赞数为0，用户肯定可以点击
            loadLike(mTag,mUserManager,mContext,questionBean.id,mLike, mLikeCount);
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
                    if (likeModel.getId().equals(account)) {
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
    private  void loadLike(int tag,UserManager userManager, final Context context,String id, final ImageView like, final TextView likeCount) {

        DialogManager.getInstnce().showProgressDialog(context);
        // 当前系统时间
        String time = new SimpleDateFormat("yyyy.MM.dd HH:mm").format(new Date(System.currentTimeMillis()));
        RequestCenter.requestLike(tag,
                id,
                userManager.getPerson().getAccount(),
                userManager.getPerson().getPhotourl(),
                userManager.getPerson().getStudentname(),
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
                        Toast.makeText(context, "网络连接失败", Toast.LENGTH_SHORT).show();
                        DialogManager.getInstnce().dismissProgressDialog();
                    }
                });
    }
}
