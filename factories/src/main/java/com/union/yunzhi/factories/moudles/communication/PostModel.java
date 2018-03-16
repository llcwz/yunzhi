package com.union.yunzhi.factories.moudles.communication;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * 交流区帖子模型
 * Created by CrazyGZ on 2018/3/9.
 */

public class PostModel implements Parcelable {
    private String id; // 帖子的id
//    private int tag; // 标记类型，由此可知是哪一个模块的帖子
    private String userId; // 作者id
    private String photoUrl; // 作者头像
    private String name; // 作者
    private String title; // 标题
    private String content; // 内容
    private String time; // 时间
    private String msgNum; // 评论数
    private String favour; // 赞数

    public PostModel() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }



    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getMsgNum() {
        return msgNum;
    }

    public void setMsgNum(String msgNum) {
        this.msgNum = msgNum;
    }

    public String getFavour() {
        return favour;
    }

    public void setFavour(String favour) {
        this.favour = favour;
    }

    public static Creator<PostModel> getCREATOR() {
        return CREATOR;
    }

    protected PostModel(Parcel in) {
        id = in.readString();
        userId = in.readString();
        photoUrl = in.readString();
        name = in.readString();
        title = in.readString();
        content = in.readString();
        time = in.readString();
        msgNum = in.readString();
        favour = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(userId);
        dest.writeString(photoUrl);
        dest.writeString(name);
        dest.writeString(title);
        dest.writeString(content);
        dest.writeString(time);
        dest.writeString(msgNum);
        dest.writeString(favour);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<PostModel> CREATOR = new Creator<PostModel>() {
        @Override
        public PostModel createFromParcel(Parcel in) {
            return new PostModel(in);
        }

        @Override
        public PostModel[] newArray(int size) {
            return new PostModel[size];
        }
    };
}
