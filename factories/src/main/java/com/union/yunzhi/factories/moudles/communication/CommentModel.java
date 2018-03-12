package com.union.yunzhi.factories.moudles.communication;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Created by CrazyGZ on 2018/3/9.
 */

public class CommentModel implements Parcelable {
    private String mId; // 该评论的id
    private String mIcon; // 头像
    private String mAuthor; // 作者
    private String mTime; // 时间
    private String mContent; // 内容
    private List<LikeModel> mLikeModels; // 点赞数

    public CommentModel() {
    }

    public CommentModel(String id, String icon, String author, String time, String content, List<LikeModel> likeModels) {
        mId = id;
        mIcon = icon;
        mAuthor = author;
        mTime = time;
        mContent = content;
        mLikeModels = likeModels;
    }

    protected CommentModel(Parcel in) {
        mId = in.readString();
        mIcon = in.readString();
        mAuthor = in.readString();
        mTime = in.readString();
        mContent = in.readString();
        mLikeModels = in.createTypedArrayList(LikeModel.CREATOR);
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mId);
        dest.writeString(mIcon);
        dest.writeString(mAuthor);
        dest.writeString(mTime);
        dest.writeString(mContent);
        dest.writeTypedList(mLikeModels);
    }

    @Override
    public int describeContents() {
        return 0;
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

    public String getContent() {
        return mContent;
    }

    public void setContent(String content) {
        mContent = content;
    }

    public List<LikeModel> getLikeModels() {
        return mLikeModels;
    }

    public void setLikeModels(List<LikeModel> likeModels) {
        mLikeModels = likeModels;
    }
}
