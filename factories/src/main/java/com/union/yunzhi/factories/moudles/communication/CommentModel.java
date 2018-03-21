package com.union.yunzhi.factories.moudles.communication;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Created by CrazyGZ on 2018/3/9.
 */

public class CommentModel implements Parcelable {
    private String id; // 该评论的id
    private String matrixId; // 评论主体的id
    private String userId; // 评论者id
    private String photoUrl; // 评论者头像
    private String name; // 评论者名字
    private String content; // 评论内容
    private String time; // 评论时间
    private String replyNum; // 回复数
    private String favour; // 点赞数

    protected CommentModel(Parcel in) {
        id = in.readString();
        matrixId = in.readString();
        userId = in.readString();
        photoUrl = in.readString();
        name = in.readString();
        content = in.readString();
        time = in.readString();
        replyNum = in.readString();
        favour = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(matrixId);
        dest.writeString(userId);
        dest.writeString(photoUrl);
        dest.writeString(name);
        dest.writeString(content);
        dest.writeString(time);
        dest.writeString(replyNum);
        dest.writeString(favour);
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
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMatrixId() {
        return matrixId;
    }

    public void setMatrixId(String matrixId) {
        this.matrixId = matrixId;
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

    public String getReplyNum() {
        return replyNum;
    }

    public void setReplyNum(String replyNum) {
        this.replyNum = replyNum;
    }

    public String getFavour() {
        return favour;
    }

    public void setFavour(String favour) {
        this.favour = favour;
    }

}
