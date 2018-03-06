package com.union.yunzhi.factories.moudles.me;

import android.media.Image;

/**
 * Created by CrazyGZ on 2018/2/24.
 */

public class NavigationModel {
    private int mAccess; // 权限，根据这个来判断是老师还是学生
    private int mNavigationIcon;
    private String mNavigationName;

    // 前期测试数据
    public NavigationModel(int access, int navigationIcon, String navigationName) {
        mAccess = access;
        mNavigationIcon = navigationIcon;
        mNavigationName = navigationName;
    }

    // 测试数据
    public NavigationModel(int navigationIcon, String navigationName) {
        mNavigationIcon = navigationIcon;
        mNavigationName = navigationName;
    }

    public NavigationModel() {
    }

    public int getAccess() {
        return mAccess;
    }

    public void setAccess(int access) {
        mAccess = access;
    }

    public int getNavigationIcon() {
        return mNavigationIcon;
    }

    public void setNavigationIcon(int navigationIcon) {
        mNavigationIcon = navigationIcon;
    }

    public String getNavigationName() {
        return mNavigationName;
    }

    public void setNavigationName(String navigationName) {
        mNavigationName = navigationName;
    }
}
