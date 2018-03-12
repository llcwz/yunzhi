package com.union.yunzhi.factories.moudles.me;

/**
 * Created by CrazyGZ on 2018/3/12.
 */

public class SystemInformModel {
    private String mIcon; // 系统的头像
    private String mName; // 系统的姓名
    private String mTime; // 通知的时间
    private String mContent; // 通知的内容

    public SystemInformModel() {}

    public SystemInformModel(String icon, String name, String time, String content) {
        mIcon = icon;
        mName = name;
        mTime = time;
        mContent = content;
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
}
