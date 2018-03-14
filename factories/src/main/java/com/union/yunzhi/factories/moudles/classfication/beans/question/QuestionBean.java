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
    public String id; // 该问题的id
    public String iconUrl; // 头像
    public String author; // 作者
    public String time; // 时间
    public String content; // 内容
    public List<CommentModel> commentModels; // 该问题的评论
    public List<LikeModel> likeModels; // 该问题的点赞


    protected QuestionBean(Parcel in) {
        courseId = in.readString();
        id = in.readString();
        iconUrl = in.readString();
        author = in.readString();
        time = in.readString();
        content = in.readString();
        commentModels = in.createTypedArrayList(CommentModel.CREATOR);
        likeModels = in.createTypedArrayList(LikeModel.CREATOR);
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(courseId);
        dest.writeString(id);
        dest.writeString(iconUrl);
        dest.writeString(author);
        dest.writeString(time);
        dest.writeString(content);
        dest.writeTypedList(commentModels);
        dest.writeTypedList(likeModels);
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
