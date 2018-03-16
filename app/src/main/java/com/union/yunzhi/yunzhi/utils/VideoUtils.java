package com.union.yunzhi.yunzhi.utils;

import android.content.Context;

import com.union.yunzhi.factories.moudles.classfication.beans.video.VideoBean;
import com.union.yunzhi.factories.moudles.communication.BaseCommentModel;
import com.union.yunzhi.yunzhi.activities.classfication.VideoActivity;

/**
 * Created by cjw on 2018/3/10 0010.
 */

public class VideoUtils {

    private  Context context;

    private  VideoBean video;

    private BaseCommentModel comment;


    public static VideoUtils newInstance(Context context,VideoBean video,BaseCommentModel comment) {

        return new VideoUtils(context,video,comment);
    }


    public  VideoUtils(Context context,VideoBean video,BaseCommentModel comment ) {

        this.context = context;
        this.video=video;
        this.comment=comment;

    }

    public  void startVideo() {

        VideoActivity.newInstance(context,video,comment);
    }


}
