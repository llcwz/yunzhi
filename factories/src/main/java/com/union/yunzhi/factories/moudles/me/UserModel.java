package com.union.yunzhi.factories.moudles.me;

/**
 * Created by CrazyGZ on 2018/2/24.
 */

public class UserModel {
    private String photourl; // 头像地址
    private String name; // 姓名
    private String account; // 账号
    private String password; // 密码
    private int priority; // 权限，根据相应的权限载入不同的布局

    public UserModel(){}

    public UserModel(String photourl, String name, String account, String password, int priority) {
        this.photourl = photourl;
        this.name = name;
        this.account = account;
        this.password = password;
        this.priority = priority;
    }

    public String getPhotourl() {
        return photourl;
    }

    public void setPhotourl(String photourl) {
        this.photourl = photourl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }
}
