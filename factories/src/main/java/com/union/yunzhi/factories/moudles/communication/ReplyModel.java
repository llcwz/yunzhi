package com.union.yunzhi.factories.moudles.communication;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by CrazyGZ on 2018/3/31.
 */

public class ReplyModel implements Parcelable {
    private String commentid; // 该回复的id
    private String noteid; // 评论主体的id
    private String userid; // 回复者id
    private String photourl; // 回复者头像
    private String name; // 回复者名字
    private String content; // 回复者内容
    private String time; // 回复者时间

    public ReplyModel() {}

    protected ReplyModel(Parcel in) {
        commentid = in.readString();
        noteid = in.readString();
        userid = in.readString();
        photourl = in.readString();
        name = in.readString();
        content = in.readString();
        time = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(commentid);
        dest.writeString(noteid);
        dest.writeString(userid);
        dest.writeString(photourl);
        dest.writeString(name);
        dest.writeString(content);
        dest.writeString(time);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<ReplyModel> CREATOR = new Creator<ReplyModel>() {
        @Override
        public ReplyModel createFromParcel(Parcel in) {
            return new ReplyModel(in);
        }

        @Override
        public ReplyModel[] newArray(int size) {
            return new ReplyModel[size];
        }
    };

    public String getCommentid() {
        return commentid;
    }

    public void setCommentid(String commentid) {
        this.commentid = commentid;
    }

    public String getNoteid() {
        return noteid;
    }

    public void setNoteid(String noteid) {
        this.noteid = noteid;
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

    @Override
    public String toString() {
        return "ReplyModel{" +
                "commentid='" + commentid + '\'' +
                ", noteid='" + noteid + '\'' +
                ", userid='" + userid + '\'' +
                ", photourl='" + photourl + '\'' +
                ", name='" + name + '\'' +
                ", content='" + content + '\'' +
                ", time='" + time + '\'' +
                '}';
    }
}
