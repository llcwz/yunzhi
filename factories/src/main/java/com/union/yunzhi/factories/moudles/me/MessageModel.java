package com.union.yunzhi.factories.moudles.me;

import android.os.Parcel;
import android.os.Parcelable;

import com.union.yunzhi.factories.moudles.classfication.beans.QuestionBean;
import com.union.yunzhi.factories.moudles.communication.CommentModel;
import com.union.yunzhi.factories.moudles.communication.PostModel;

import java.util.List;

/**
 * Created by CrazyGZ on 2018/3/10.
 */

public class MessageModel implements Parcelable{
    private List<CommentMeModel> mCommentMeModels;
    private List<LikeMeModel> mLikeMeModels;
    private List<SystemInformModel> mSystemInformModels;

    public MessageModel() {
    }

    public MessageModel(List<CommentMeModel> commentMeModels, List<LikeMeModel> likeMeModels, List<SystemInformModel> systemInformModels) {
        mCommentMeModels = commentMeModels;
        mLikeMeModels = likeMeModels;
        mSystemInformModels = systemInformModels;
    }

    protected MessageModel(Parcel in) {
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<MessageModel> CREATOR = new Creator<MessageModel>() {
        @Override
        public MessageModel createFromParcel(Parcel in) {
            return new MessageModel(in);
        }

        @Override
        public MessageModel[] newArray(int size) {
            return new MessageModel[size];
        }
    };

    public List<CommentMeModel> getCommentMeModels() {
        return mCommentMeModels;
    }

    public void setCommentMeModels(List<CommentMeModel> commentMeModels) {
        mCommentMeModels = commentMeModels;
    }

    public List<LikeMeModel> getLikeMeModels() {
        return mLikeMeModels;
    }

    public void setLikeMeModels(List<LikeMeModel> likeMeModels) {
        mLikeMeModels = likeMeModels;
    }

    public List<SystemInformModel> getSystemInformModels() {
        return mSystemInformModels;
    }

    public void setSystemInformModels(List<SystemInformModel> systemInformModels) {
        mSystemInformModels = systemInformModels;
    }
}
