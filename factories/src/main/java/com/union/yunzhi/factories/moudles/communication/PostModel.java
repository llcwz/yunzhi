package com.union.yunzhi.factories.moudles.communication;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * 交流区帖子模型
 * Created by CrazyGZ on 2018/3/9.
 */

public class PostModel implements Parcelable {
    private String id; // 帖子的id  不要
    private int tag; // 标记类型，由此可知是哪一个模块的帖子
    private String mIcon; // 作者头像
    private String mAuthor; // 作者
    private String time; // 时间
    private String title; // 标题
    private String content; // 内容
    private List<CommentModel> mCommentModels; // 该帖子的评论
    private List<LikeModel> mLikeModels; // 该帖子的点赞

    public PostModel() {
    }

    public PostModel(String id, int tag, String icon, String author, String time, String title, String content, List<CommentModel> commentModels, List<LikeModel> likeModels) {
        this.id = id;
        this.tag = tag;
        mIcon = icon;
        mAuthor = author;
        this.time = time;
        this.title = title;
        this.content = content;
        mCommentModels = commentModels;
        mLikeModels = likeModels;
    }

    protected PostModel(Parcel in) {
        id = in.readString();
        tag = in.readInt();
        mIcon = in.readString();
        mAuthor = in.readString();
        time = in.readString();
        title = in.readString();
        content = in.readString();
        mCommentModels = in.createTypedArrayList(CommentModel.CREATOR);
        mLikeModels = in.createTypedArrayList(LikeModel.CREATOR);
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeInt(tag);
        dest.writeString(mIcon);
        dest.writeString(mAuthor);
        dest.writeString(time);
        dest.writeString(title);
        dest.writeString(content);
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
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getTag() {
        return tag;
    }

    public void setTag(int tag) {
        this.tag = tag;
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
        return time;
    }

    public void setTime(String time) {
        this.time = time;
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
