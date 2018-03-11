package com.union.yunzhi.factories.moudles.communication;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by CrazyGZ on 2018/3/11.
 */

public class LikeModel implements Parcelable {
    private String mId; // 点赞者的id
    private String mIcon; // 点赞者头像
    private String mAuthor; // 点赞者姓名
    private String mTime; // 点赞的时间


    public LikeModel() {}
    public LikeModel(String id, String icon, String author, String time) {
        mId = id;
        mIcon = icon;
        mAuthor = author;
        mTime = time;
    }

    protected LikeModel(Parcel in) {
        mId = in.readString();
        mIcon = in.readString();
        mAuthor = in.readString();
        mTime = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mId);
        dest.writeString(mIcon);
        dest.writeString(mAuthor);
        dest.writeString(mTime);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<LikeModel> CREATOR = new Creator<LikeModel>() {
        @Override
        public LikeModel createFromParcel(Parcel in) {
            return new LikeModel(in);
        }

        @Override
        public LikeModel[] newArray(int size) {
            return new LikeModel[size];
        }
    };

    public String getId() {
        return mId;
    }

    public void setId(String id) {
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
}
