package com.union.yunzhi.factories.moudles.communication;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Created by CrazyGZ on 2018/3/9.
 */

public class CommentModel implements Parcelable {
    private String noteid; // 该评论的id
    private String matrixid; // 评论主体的id
    private String userid; // 评论者id
    private String photourl; // 评论者头像
    private String name; // 评论者名字
    private String content; // 评论内容
    private String time; // 评论时间
    private String replynum; // 回复数
    private String favour; // 点赞数

    public CommentModel() {}

    @Override
    public String toString() {
        return "CommentModel{" +
                "noteid='" + noteid + '\'' +
                ", matrixid='" + matrixid + '\'' +
                ", userid='" + userid + '\'' +
                ", photourl='" + photourl + '\'' +
                ", name='" + name + '\'' +
                ", content='" + content + '\'' +
                ", time='" + time + '\'' +
                ", replynum='" + replynum + '\'' +
                ", favour='" + favour + '\'' +
                '}';
    }

    public String getNoteid() {
        return noteid;
    }

    public void setNoteid(String noteid) {
        this.noteid = noteid;
    }

    public String getMatrixid() {
        return matrixid;
    }

    public void setMatrixid(String matrixid) {
        this.matrixid = matrixid;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
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

    public String getReplynum() {
        return replynum;
    }

    public void setReplynum(String replynum) {
        this.replynum = replynum;
    }

    public String getFavour() {
        return favour;
    }

    public void setFavour(String favour) {
        this.favour = favour;
    }

    public static Creator<CommentModel> getCREATOR() {
        return CREATOR;
    }

    protected CommentModel(Parcel in) {
        noteid = in.readString();
        matrixid = in.readString();
        userid = in.readString();
        photourl = in.readString();
        name = in.readString();
        content = in.readString();
        time = in.readString();
        replynum = in.readString();
        favour = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(noteid);
        dest.writeString(matrixid);
        dest.writeString(userid);
        dest.writeString(photourl);
        dest.writeString(name);
        dest.writeString(content);
        dest.writeString(time);
        dest.writeString(replynum);
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
}
