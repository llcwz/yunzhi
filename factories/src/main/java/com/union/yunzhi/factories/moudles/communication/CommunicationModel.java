package com.union.yunzhi.factories.moudles.communication;

import android.os.Parcelable;

import com.union.yunzhi.factories.moudles.BaseModel;

import java.util.List;

/**
 * Created by CrazyGZ on 2018/3/9.
 */

public class CommunicationModel{
    private List<PostModel> mPostModels; // 帖子

    public CommunicationModel() {}

    public CommunicationModel(List<PostModel> postModels) {
        mPostModels = postModels;
    }

    public List<PostModel> getPostModels() {
        return mPostModels;
    }

    public void setPostModels(List<PostModel> postModels) {
        mPostModels = postModels;
    }

}
