package com.union.yunzhi.factories.moudles.classfication.beans.video;

import android.os.Parcel;
import android.os.Parcelable;

import java.security.PublicKey;

/**
 * Created by cjw on 2018/3/14 0014.
 */

public class VideoBean implements Parcelable{

    public String coverurl;
    public String videourl;
    public int videoid;
    public String videotitle;

    public VideoBean() {}

    protected VideoBean(Parcel in) {
        coverurl = in.readString();
        videourl = in.readString();
        videoid = in.readInt();
        videotitle = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(coverurl);
        dest.writeString(videourl);
        dest.writeInt(videoid);
        dest.writeString(videotitle);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<VideoBean> CREATOR = new Creator<VideoBean>() {
        @Override
        public VideoBean createFromParcel(Parcel in) {
            return new VideoBean(in);
        }

        @Override
        public VideoBean[] newArray(int size) {
            return new VideoBean[size];
        }
    };
}
