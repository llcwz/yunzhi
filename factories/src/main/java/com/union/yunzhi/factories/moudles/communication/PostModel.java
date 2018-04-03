package com.union.yunzhi.factories.moudles.communication;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * 交流区帖子模型
 * Created by CrazyGZ on 2018/3/9.
 */

public class PostModel implements Parcelable {
    private String matrixId; // 帖子id
    private String userid; // 作者id
    private String photourl; // 作者头像
    private String name; // 作者
    private String title; // 标题
    private String content; // 内容
    private String time; // 时间
    private String msgnum; // 评论数
    private String favour; // 赞数
    private int tag;
    private int peopletype;

    public PostModel() {}

    protected PostModel(Parcel in) {
        matrixId = in.readString();
        userid = in.readString();
        photourl = in.readString();
        name = in.readString();
        title = in.readString();
        content = in.readString();
        time = in.readString();
        msgnum = in.readString();
        favour = in.readString();
        tag = in.readInt();
        peopletype = in.readInt();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(matrixId);
        dest.writeString(userid);
        dest.writeString(photourl);
        dest.writeString(name);
        dest.writeString(title);
        dest.writeString(content);
        dest.writeString(time);
        dest.writeString(msgnum);
        dest.writeString(favour);
        dest.writeInt(tag);
        dest.writeInt(peopletype);
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

    public String getMatrixId() {
        return matrixId;
    }

    public void setMatrixId(String matrixId) {
        this.matrixId = matrixId;
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

    public String getMsgnum() {
        return msgnum;
    }

    public void setMsgnum(String msgnum) {
        this.msgnum = msgnum;
    }

    public String getFavour() {
        return favour;
    }

    public void setFavour(String favour) {
        this.favour = favour;
    }

    public int getTag() {
        return tag;
    }

    public void setTag(int tag) {
        this.tag = tag;
    }

    public int getPeopletype() {
        return peopletype;
    }

    public void setPeopletype(int peopletype) {
        this.peopletype = peopletype;
    }
}
