package com.union.yunzhi.factories.moudles.me;

import com.union.yunzhi.factories.moudles.communication.CommentModel;
import com.union.yunzhi.factories.moudles.communication.PostModel;

import java.util.List;

/**
 * Created by CrazyGZ on 2018/3/10.
 */

public class MessageModel {

    private List<PostModel> mPostModels; // 自己发的帖子，属于交流区模块
    // 这里预留出课程交流区下面的评论
    private List<CommentModel> mCommentModels; // 自己发的评论，交流区和课程下的讨论区都可以用

}
