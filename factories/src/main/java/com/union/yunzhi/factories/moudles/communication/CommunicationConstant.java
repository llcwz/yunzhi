package com.union.yunzhi.factories.moudles.communication;

/**
 * Created by CrazyGZ on 2018/3/9.
 */

public class CommunicationConstant {

    public static final int ECODE = 0;

    // 标记所建立的fragment
    public static final int TAG_COLLEGE = 0; // 学院见闻
    public static final int TAG_NOTE = 1; // 学习笔记
    public static final int TAG_QUESTION = 2;

    public static final int FLAG_POST = 1;
    public static final int FLAG_QUESTION = 0;


    // 标记评论是属于帖子的还是问题的
    public static final int COMMENT_TAG_POST = 0;
    public static final int COMMENT_TAG_QUESTION = 1;


    // 上传点赞数据时，区分是给帖子点赞还是评论点赞
    public static final String LIKE_TAG_POST = "1";
    public static final String LIKE_TAG_COMMENT = "2";
    public static final String LIKE_TAG_QUESTION = "3";
}
