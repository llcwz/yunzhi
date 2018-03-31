package com.union.yunzhi.factories.moudles.communication;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Created by CrazyGZ on 2018/3/7.
 */

public class BaseReplyModel{
    public int ecode;
    public String emsg;
    public List<ReplyModel> data;

    public BaseReplyModel(){
    }

}
