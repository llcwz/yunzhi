package com.union.yunzhi.factories.moudles.me;

/**
 * Created by CrazyGZ on 2018/3/12.
 */

public class CommentMeModel {
    private String mId; // 评论的id
    private String mAccount; // 评论者的id
    private String mIcon; // 评论者的头像
    private String mName; // 评论者的姓名
    private String mTime; // 评论的时间
    private String mContent; // 评论者的内容
    private String mMyTitle; // 我发表的帖子的标题
    private String mQuestion; // 我发表的问题

    public CommentMeModel() {
    }

    public CommentMeModel(String id, String account, String icon, String name, String time, String content, String myTitle, String question) {
        mId = id;
        mAccount = account;
        mIcon = icon;
        mName = name;
        mTime = time;
        mContent = content;
        mMyTitle = myTitle;
        mQuestion = question;
    }

    public String getId() {
        return mId;
    }

    public void setId(String id) {
        mId = id;
    }

    public String getAccount() {
        return mAccount;
    }

    public void setAccount(String account) {
        mAccount = account;
    }

    public String getIcon() {
        return mIcon;
    }

    public void setIcon(String icon) {
        mIcon = icon;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public String getTime() {
        return mTime;
    }

    public void setTime(String time) {
        mTime = time;
    }

    public String getContent() {
        return mContent;
    }

    public void setContent(String content) {
        mContent = content;
    }

    public String getMyTitle() {
        return mMyTitle;
    }

    public void setMyTitle(String myTitle) {
        mMyTitle = myTitle;
    }

    public String getQuestion() {
        return mQuestion;
    }

    public void setQuestion(String question) {
        mQuestion = question;
    }


}
