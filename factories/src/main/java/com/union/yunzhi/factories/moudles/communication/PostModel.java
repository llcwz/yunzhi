package com.union.yunzhi.factories.moudles.communication;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * 交流区帖子模型
 * Created by CrazyGZ on 2018/3/9.
 */

public class PostModel implements Parcelable {
    private String mId; // 帖子的id
    private int mTag; // 标记类型，由此可知是哪一个模块的帖子
    private String mIcon; // 头像
    private String mAuthor; // 作者
    private String mTime; // 时间
    private String mTitle; // 标题
    private String mContent; // 内容
    private List<CommentModel> mCommentModels; // 该帖子的评论
    private List<LikeModel> mLikeModels; // 该帖子的点赞

    public PostModel() {
    }

    public PostModel(String id, int tag, String icon, String author, String time, String title, String content, List<CommentModel> commentModels, List<LikeModel> likeModels) {
        mId = id;
        mTag = tag;
        mIcon = icon;
        mAuthor = author;
        mTime = time;
        mTitle = title;
        mContent = content;
        mCommentModels = commentModels;
        mLikeModels = likeModels;
    }

    protected PostModel(Parcel in) {
        mId = in.readString();
        mTag = in.readInt();
        mIcon = in.readString();
        mAuthor = in.readString();
        mTime = in.readString();
        mTitle = in.readString();
        mContent = in.readString();
        mCommentModels = in.createTypedArrayList(CommentModel.CREATOR);
        mLikeModels = in.createTypedArrayList(LikeModel.CREATOR);
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mId);
        dest.writeInt(mTag);
        dest.writeString(mIcon);
        dest.writeString(mAuthor);
        dest.writeString(mTime);
        dest.writeString(mTitle);
        dest.writeString(mContent);
        dest.writeTypedList(mCommentModels);
        dest.writeTypedList(mLikeModels);
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

    public String getId() {
        return mId;
    }

    public void setId(String id) {
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

    public List<LikeModel> getLikeModels() {
        return mLikeModels;
    }

    public void setLikeModels(List<LikeModel> likeModels) {
        mLikeModels = likeModels;
    }
}
