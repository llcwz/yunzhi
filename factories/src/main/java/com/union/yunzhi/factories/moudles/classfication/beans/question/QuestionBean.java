package com.union.yunzhi.factories.moudles.classfication.beans.question;

import android.os.Parcel;
import android.os.Parcelable;

import com.union.yunzhi.factories.moudles.communication.CommentModel;
import com.union.yunzhi.factories.moudles.communication.LikeModel;

import java.util.List;

/**
 * Created by cjw on 2018/3/10 0010.
 */

public class QuestionBean implements Parcelable {
    public String courseId; // 该问题属于那一课程的id
    public String id; // 问题的id
    public String userId; // 作者id
    public String photoUrl; // 作者头像
    public String name; // 作者
    public String title; // 标题
    public String content; // 内容
    public String time; // 时间
    public String msgNum; // 评论数
    public String favour; // 赞数
    public List<String> mLikeUserId;


    protected QuestionBean(Parcel in) {
        courseId = in.readString();
        id = in.readString();
        userId = in.readString();
        photoUrl = in.readString();
        name = in.readString();
        title = in.readString();
        content = in.readString();
        time = in.readString();
        msgNum = in.readString();
        favour = in.readString();
        mLikeUserId = in.createStringArrayList();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(courseId);
        dest.writeString(id);
        dest.writeString(userId);
        dest.writeString(photoUrl);
        dest.writeString(name);
        dest.writeString(title);
        dest.writeString(content);
        dest.writeString(time);
        dest.writeString(msgNum);
        dest.writeString(favour);
        dest.writeStringList(mLikeUserId);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<QuestionBean> CREATOR = new Creator<QuestionBean>() {
        @Override
        public QuestionBean createFromParcel(Parcel in) {
            return new QuestionBean(in);
        }

        @Override
        public QuestionBean[] newArray(int size) {
            return new QuestionBean[size];
        }
    };
}
