package com.union.yunzhi.factories.moudles.communication;

/**
 * Created by CrazyGZ on 2018/3/9.
 */

public class CommunicationConstant {

    public static final String KEY_TAG = "tag";

    // 标记所建立的fragment
    public static final int TAG_COLLEGE = 0; // 学院见闻
    public static final int TAG_NOTE = 1; // 学习笔记

    // 上传点赞数据时，区分是给帖子点赞还是评论点赞
    public static final int TAG_POST = 0;
    public static final int TAG_COMMENT = 1;
}
