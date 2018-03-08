package com.union.yunzhi.factories.moudles.me;

import android.graphics.drawable.Drawable;
import android.media.Image;

/**
 * Created by CrazyGZ on 2018/2/24.
 */

public class PersonModel {
    private String mMe;
    private String mUsername; // 姓名
    private String mAccount; // 账号
    private String mPassword; // 密码
    private int mAccess; // 权限，根据相应的权限载入不同的布局

    public PersonModel(){}

    public PersonModel(String me, String username, String account, String password, int access) {
        mMe = me;
        mUsername = username;
        mAccount = account;
        mPassword = password;
        mAccess = access;
    }

    public String getPassword() {
        return mPassword;
    }

    public void setPassword(String password) {
        mPassword = password;
    }

    public String getMe() {
        return mMe;
    }

    public void setMe(String me) {
        mMe = me;
    }

    public String getUsername() {
        return mUsername;
    }

    public void setUsername(String username) {
        mUsername = username;
    }

    public String getAccount() {
        return mAccount;
    }

    public void setAccount(String account) {
        mAccount = account;
    }

    public int getAccess() {
        return mAccess;
    }

    public void setAccess(int access) {
        mAccess = access;
    }
}
