package com.union.yunzhi.factories.moudles.me;

/**
 * Created by CrazyGZ on 2018/2/24.
 */

public class PersonModel {
    private String photourl; // 头像地址
    private String studentname; // 姓名
    private String account; // 账号
    private String password; // 密码
    private int priority; // 权限，根据相应的权限载入不同的布局

    public PersonModel(){}

    public PersonModel(String photourl, String studentname, String account, String password, int priority) {
        this.photourl = photourl;
        this.studentname = studentname;
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

    public String getStudentname() {
        return studentname;
    }

    public void setStudentname(String studentname) {
        this.studentname = studentname;
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
