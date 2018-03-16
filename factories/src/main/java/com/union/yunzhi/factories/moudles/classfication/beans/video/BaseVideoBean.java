package com.union.yunzhi.factories.moudles.classfication.beans.video;

import android.os.Parcel;
import android.os.Parcelable;

import com.union.yunzhi.factories.moudles.BaseModel;

/**
 * Created by cjw on 2018/3/14 0014.
 */

public class BaseVideoBean extends BaseModel implements Parcelable{
    public int ecode;
    public String emsg;
    public VideoBean data;

    protected BaseVideoBean(Parcel in) {
        ecode = in.readInt();
        emsg = in.readString();
    }

    public static final Creator<BaseVideoBean> CREATOR = new Creator<BaseVideoBean>() {
        @Override
        public BaseVideoBean createFromParcel(Parcel in) {
            return new BaseVideoBean(in);
        }

        @Override
        public BaseVideoBean[] newArray(int size) {
            return new BaseVideoBean[size];
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
    }
}
