package com.union.yunzhi.factories.moudles.communication;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * 交流区帖子模型
 * Created by CrazyGZ on 2018/3/9.
 */

public class PostModel implements Parcelable {
    private int mId; // id
    private int mTag; // 标记类型，由此可知是哪一个模块的帖子
    private String mIcon; // 头像
    private String mAuthor; // 作者
    private String mTime; // 时间
    private String mTitle; // 标题
    private String mContent; // 内容
    private List<CommentModel> mCommentModels; // 评论

    public PostModel(int id, int tag, String icon, String author, String time, String title, String content, List<CommentModel> commentModels) {
        mId = id;
        mTag = tag;
        mIcon = icon;
        mAuthor = author;
        mTime = time;
        mTitle = title;
        mContent = content;
        mCommentModels = commentModels;
    }

    protected PostModel(Parcel in) {
        mId = in.readInt();
        mTag = in.readInt();
        mIcon = in.readString();
        mAuthor = in.readString();
        mTime = in.readString();
        mTitle = in.readString();
        mContent = in.readString();
        mCommentModels = in.createTypedArrayList(CommentModel.CREATOR);
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(mId);
        dest.writeInt(mTag);
        dest.writeString(mIcon);
        dest.writeString(mAuthor);
        dest.writeString(mTime);
        dest.writeString(mTitle);
        dest.writeString(mContent);
        dest.writeTypedList(mCommentModels);
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

    public int getId() {
        return mId;
    }

    public void setId(int id) {
        mId = id;
    }

    public int getTag() {
        return mTag;
    }

    public void setTag(int tag) {
        mTag = tag;
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

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public String getContent() {
        return mContent;
    }

    public void setContent(String content) {
        mContent = content;
    }

    public List<CommentModel> getCommentModels() {
        return mCommentModels;
    }

    public void setCommentModels(List<CommentModel> commentModels) {
        mCommentModels = commentModels;
    }
}
