package com.union.yunzhi.yunzhi.communicationutils;

import android.content.Context;

import com.union.yunzhi.factories.moudles.me.UserModel;

/** 评论功能
 * Created by CrazyGZ on 2018/3/11.
 */

public class CommentUtils {

    private UserModel mUser;
    private Context mContext; // 上下文
    private String mId; // 该帖子或者的id
    private String mContent; // 评论内容


    public static CommentUtils newInstance(UserModel user, Context context,String id, String content) {
        return new CommentUtils(user, context, id, content);
    }

    private CommentUtils(UserModel user, Context context,String id, String content) {
        mUser = user;
        mContext = context;
        mId = id;
        mContent = content;
    }


//    public void addComment(int tag,final CommentAdapter commentAdapter) {
//
//        DialogManager.getInstnce().showProgressDialog(mContext);
//        // 当前系统时间
//        String time = new SimpleDateFormat("yyyy.MM.dd HH:mm").format(new Date(System.currentTimeMillis()));
//        // 利用用户的id和对当前时间生成评论的id
//        String id = mUser.getAccount() + time; // 利用用户的账号的当前时间生成id
//
//        RequestCenter.requestComment(mUser.getAccount(),
//                mId,
//                id,
//                tag,
//                mUser.getPhotourl(),
//                mUser.getName(),
//                time,
//                mContent,
//                new DisposeDataListener() {
//                    @Override
//                    public void onSuccess(Object responseObj) {
//                        BaseCommentModel baseCommentModel = (BaseCommentModel) responseObj;
//                        if (baseCommentModel.ecode == CommunicationConstant.ECODE) {
//                            commentAdapter.add(baseCommentModel.data.get(0));
//                            commentAdapter.notify();
//                            Toast.makeText(mContext, "操作成功", Toast.LENGTH_SHORT).show();
//                            DialogManager.getInstnce().dismissProgressDialog();
//                        }
//                    }
//
//                    @Override
//                    public void onFailure(Object reasonObj) {
//                        DialogManager.getInstnce().dismissProgressDialog();
//                        OkHttpException okHttpException = (OkHttpException) reasonObj;
//                        if (okHttpException.getEcode() == 1) {
//                            Toast.makeText(mContext, "" + okHttpException.getEmsg(), Toast.LENGTH_SHORT).show();
//                        } else if (okHttpException.getEcode() == -1){
//                            Toast.makeText(mContext, "网络连接错误", Toast.LENGTH_SHORT).show();
//                        } else if (okHttpException.getEcode() == -2) {
//                            Toast.makeText(mContext, "解析错误" , Toast.LENGTH_SHORT).show();
//                        } else if (okHttpException.getEcode() == -3) {
//                            Toast.makeText(mContext, "未知错误", Toast.LENGTH_SHORT).show();
//                        }
//                    }
//                });
//
//    }
}
