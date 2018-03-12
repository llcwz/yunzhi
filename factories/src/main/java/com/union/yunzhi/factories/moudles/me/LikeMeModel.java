package com.union.yunzhi.factories.moudles.me;

/**
 * Created by CrazyGZ on 2018/3/12.
 */

public class LikeMeModel {
    private String mAccount; // 赞我的id
    private String mIcon; // 赞我的头像
    private String mName; // 赞我的姓名
    private String mTime; // 赞我的时间
    private String mMyComment; // 我的评论的内容

    public LikeMeModel() {}

    public LikeMeModel(String account, String icon, String name, String time, String myComment) {
        mAccount = account;
        mIcon = icon;
        mName = name;
        mTime = time;
        mMyComment = myComment;
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

    public String getMyComment() {
        return mMyComment;
    }

    public void setMyComment(String myComment) {
        mMyComment = myComment;
    }
}
