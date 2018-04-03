package com.union.yunzhi.yunzhi.communicationutils;

import android.content.Context;

<<<<<<< HEAD
import com.union.yunzhi.factories.moudles.me.UserModel;
=======
import com.union.yunzhi.common.util.LogUtils;
import com.union.yunzhi.factories.moudles.communication.BaseCommentModel;
import com.union.yunzhi.factories.moudles.communication.CommentModel;
import com.union.yunzhi.factories.moudles.communication.CommunicationConstant;
import com.union.yunzhi.factories.moudles.me.UserModel;
import com.union.yunzhi.factories.okhttp.exception.OkHttpException;
import com.union.yunzhi.factories.okhttp.listener.DisposeDataListener;
import com.union.yunzhi.factories.okhttp.response.NotCallBackData;
import com.union.yunzhi.yunzhi.manager.DialogManager;
import com.union.yunzhi.yunzhi.network.RequestCenter;

import java.util.List;
>>>>>>> 6adee440faaad392195116edcaa40b0af088e3e7

/** 评论功能
 * Created by CrazyGZ on 2018/3/11.
 */

public class CommentUtils {

    private UserModel mUser;
    private Context mContext; // 上下文


    public static CommentUtils newInstance(UserModel user, Context context) {
        return new CommentUtils(user, context);
    }

    private CommentUtils(UserModel user, Context context) {
        mUser = user;
        mContext = context;
    }

    public interface OnAddCommentListener {
        void getComment(Boolean result);
    }

    public interface OnRequestCommentListener {
        void getComments(List<CommentModel> commentModels);
    }

    /**
     * @function 添加评论
     * @param matrixId
     * @param content
     * @param listener
     */
    public void addComment(String matrixId, String content, final OnAddCommentListener listener) {

        DialogManager.getInstnce().showProgressDialog(mContext);
        RequestCenter.requestAddComment(matrixId,
                mUser.getAccount(),
                content,
                new DisposeDataListener() {
                    @Override
                    public void onSuccess(Object responseObj) {
                        DialogManager.getInstnce().dismissProgressDialog();
                        NotCallBackData notCallBackData = (NotCallBackData) responseObj;
                        if (notCallBackData.getEcode() == CommunicationConstant.ECODE) {
                            listener.getComment(true);
                        } else {
                            listener.getComment(false);
                            Toast.makeText(mContext, "" + notCallBackData.getEmsg(), Toast.LENGTH_SHORT).show();
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

<<<<<<< HEAD
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
=======
    }

    /**
     * @function 获取评论
     * @param matrixId 评论主体的id
     * @param listener 数据回调
     */
    public void getComment(String matrixId, final OnRequestCommentListener listener) {
        Toast.makeText(mContext, "matrixId=" + matrixId, Toast.LENGTH_SHORT).show();
        DialogManager.getInstnce().showProgressDialog(mContext);
        RequestCenter.requestComment(matrixId,
                new DisposeDataListener() {
                    @Override
                    public void onSuccess(Object responseObj) {
                        DialogManager.getInstnce().dismissProgressDialog();
                        BaseCommentModel baseCommentModel = (BaseCommentModel) responseObj;
                        if (baseCommentModel.ecode == CommunicationConstant.ECODE) {
                            listener.getComments(baseCommentModel.data);
                            for (CommentModel commentModel : baseCommentModel.data) {
                                LogUtils.d("commentMessage", commentModel.toString());
                            }
                        } else {
                            Toast.makeText(mContext, "" + baseCommentModel.emsg, Toast.LENGTH_SHORT).show();
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

    public void addReply(String matrixId, String noteId, String replyId, String content, final OnAddCommentListener listener) {

        DialogManager.getInstnce().showProgressDialog(mContext);
        RequestCenter.requestAddReply(matrixId,
                noteId,
                replyId,
                mUser.getAccount(),
                content,
                new DisposeDataListener() {
                    @Override
                    public void onSuccess(Object responseObj) {
                        DialogManager.getInstnce().dismissProgressDialog();
                        NotCallBackData notCallBackData = (NotCallBackData) responseObj;
                        if (notCallBackData.getEcode() == CommunicationConstant.ECODE) {
                            listener.getComment(true);
                        } else {
                            listener.getComment(false);
                            Toast.makeText(mContext, "" + notCallBackData.getEmsg(), Toast.LENGTH_SHORT).show();
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

    public void getReply(String noteId, final OnRequestCommentListener listener) {
        DialogManager.getInstnce().showProgressDialog(mContext);
        RequestCenter.requestReply(noteId,
                new DisposeDataListener() {
                    @Override
                    public void onSuccess(Object responseObj) {
                        DialogManager.getInstnce().dismissProgressDialog();
                        BaseCommentModel baseCommentModel = (BaseCommentModel) responseObj;
                        if (baseCommentModel.ecode == CommunicationConstant.ECODE) {
                            listener.getComments(baseCommentModel.data);
                            for (CommentModel commentModel : baseCommentModel.data) {
                                LogUtils.d("commentMessage", commentModel.toString());
                            }
                        } else {
                            Toast.makeText(mContext, "" + baseCommentModel.emsg, Toast.LENGTH_SHORT).show();
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
>>>>>>> 6adee440faaad392195116edcaa40b0af088e3e7
}
