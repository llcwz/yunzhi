package com.union.yunzhi.factories.moudles.communication;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Created by CrazyGZ on 2018/3/7.
 */

public class BaseCommentModel implements Parcelable{
    public int ecode;
    public String emsg;
    public List<CommentModel> data;

    public BaseCommentModel(){

    }

    protected BaseCommentModel(Parcel in) {
        ecode = in.readInt();
        emsg = in.readString();
        data = in.createTypedArrayList(CommentModel.CREATOR);
    }

    public static final Creator<BaseCommentModel> CREATOR = new Creator<BaseCommentModel>() {
        @Override
        public BaseCommentModel createFromParcel(Parcel in) {
            return new BaseCommentModel(in);
        }

        @Override
        public BaseCommentModel[] newArray(int size) {
            return new BaseCommentModel[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(ecode);
        dest.writeString(emsg);
        dest.writeTypedList(data);
    }
}
