package com.union.yunzhi.factories.moudles.communication;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by CrazyGZ on 2018/3/9.
 */

public class CommentModel implements Parcelable {
    private int mId; // id
    private String mIcon; // 头像
    private String mAuthor; // 作者
    private String mTime; // 时间
    private String mContent; // 内容
    private int like; // 点赞数

    public CommentModel(int id, String icon, String author, String time, String content, int like) {
        mId = id;
        mIcon = icon;
        mAuthor = author;
        mTime = time;
        mContent = content;
        this.like = like;
    }

    protected CommentModel(Parcel in) {
        mId = in.readInt();
        mIcon = in.readString();
        mAuthor = in.readString();
        mTime = in.readString();
        mContent = in.readString();
        like = in.readInt();
    }

    public static final Creator<CommentModel> CREATOR = new Creator<CommentModel>() {
        @Override
        public CommentModel createFromParcel(Parcel in) {
            return new CommentModel(in);
        }

        @Override
        public CommentModel[] newArray(int size) {
            return new CommentModel[size];
        }
    };

    public int getId() {
        return mId;
    }

    public void setId(int id) {
        mId = id;
    }

    public String getIcon() {
        return mIcon;
    }

    public void setIcon(String icon) {
        mIcon = icon;
    }

    public String getAuthor() {
        return mAuthor;
    }

    public void setAuthor(String author) {
        mAuthor = author;
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

    public int getLike() {
        return like;
    }

    public void setLike(int like) {
        this.like = like;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(mId);
        parcel.writeString(mIcon);
        parcel.writeString(mAuthor);
        parcel.writeString(mTime);
        parcel.writeString(mContent);
        parcel.writeInt(like);
    }
}