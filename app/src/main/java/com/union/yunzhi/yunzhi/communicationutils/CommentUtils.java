package com.union.yunzhi.yunzhi.communicationutils;

import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
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

/** 评论功能
 * Created by CrazyGZ on 2018/3/11.
 */

public class CommentUtils {

    private UserManager mUserManager;
    private Context mContext; // 上下文
    private String mId; // 该帖子的id
    private String mContent; // 评论内容


    public static CommentUtils newInstance(UserManager userManager, Context context,String id, String content) {
        return new CommentUtils(userManager, context, id, content);
    }

    private CommentUtils(UserManager userManager, Context context,String id, String content) {
        mUserManager = userManager;
        mContext = context;
        mId = id;
        mContent = content;
    }



    /**
     * 上传评论
     */
    private void loadComment() {

        DialogManager.getInstnce().showProgressDialog(mContext);
        // 当前系统时间
        String time = new SimpleDateFormat("yyyy.MM.dd HH:mm").format(new Date(System.currentTimeMillis()));
        String id = mUserManager.getPerson().getAccount() + time; // 利用用户的账号的当前时间生成id
        RequestCenter.requestComment(id,
                mUserManager.getPerson().getPhotourl(),
                mUserManager.getPerson().getStudentname(),
                time,
                new DisposeDataListener() {
                    @Override
                    public void onSuccess(Object responseObj) {
                        Toast.makeText(mContext, "成功", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(Object reasonObj) {
                        Toast.makeText(mContext, "网络连接失败", Toast.LENGTH_SHORT).show();
                    }
                });
    }
}
