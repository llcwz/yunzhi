package com.union.yunzhi.yunzhi.communicationutils;

import android.content.Context;
import android.widget.Toast;

import com.union.yunzhi.common.util.LogUtils;
import com.union.yunzhi.factories.moudles.communication.CommentModel;
import com.union.yunzhi.factories.moudles.communication.LikeModel;
import com.union.yunzhi.factories.moudles.communication.PostModel;
import com.union.yunzhi.factories.okhttp.listener.DisposeDataListener;
import com.union.yunzhi.yunzhi.manager.DialogManager;
import com.union.yunzhi.yunzhi.manager.UserManager;
import com.union.yunzhi.yunzhi.network.RequestCenter;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/** 评论功能
 * Created by CrazyGZ on 2018/3/11.
 */

public class OpinionUtils {

    private UserManager mUserManager;
    private Context mContext; // 上下文
    private String mTitle; // 该帖子的标题
    private String mContent; // 帖子的内容

    public interface NotifyPostListener {
        void getPost(PostModel postModel);
    }

    public static OpinionUtils newInstance(UserManager userManager, Context context, String title, String content) {
        return new OpinionUtils(userManager, context, title, content);
    }

    private OpinionUtils(UserManager userManager, Context context, String title, String content) {
        mUserManager = userManager;
        mContext = context;
        mTitle = title;
        mContent = content;
    }


    public void postComment(int tag, final NotifyPostListener listener) {

        DialogManager.getInstnce().showProgressDialog(mContext);
        // 当前系统时间
        String time = new SimpleDateFormat("yyyy.MM.dd HH:mm").format(new Date(System.currentTimeMillis()));
        // 利用用户的id和对当前时间生成评论的id
        String id = mUserManager.getPerson().getAccount() + time; // 利用用户的账号的当前时间生成id

        List<CommentModel> commentModels = new ArrayList<>();
        List<LikeModel> likeModels = new ArrayList<>();
        final PostModel postModel = new PostModel(id,
                tag,
                mUserManager.getPerson().getPhotourl(),
                mUserManager.getPerson().getStudentname(),
                time,
                mTitle,
                mContent,
                commentModels,
                likeModels);

        RequestCenter.requestAddPost(id,
                tag,
                mUserManager.getPerson().getPhotourl(),
                mUserManager.getPerson().getStudentname(),
                time,
                mTitle,
                mContent, new DisposeDataListener() {
                    @Override
                    public void onSuccess(Object responseObj) {
                        LogUtils.d("addPostRequest", responseObj.toString());
                        listener.getPost(postModel);
                        Toast.makeText(mContext, "发布成功", Toast.LENGTH_SHORT).show();
                        DialogManager.getInstnce().dismissProgressDialog();
                    }

                    @Override
                    public void onFailure(Object reasonObj) {
                        Toast.makeText(mContext, "网络连接失败", Toast.LENGTH_SHORT).show();
                        DialogManager.getInstnce().dismissProgressDialog();
                    }
                });

    }
}
