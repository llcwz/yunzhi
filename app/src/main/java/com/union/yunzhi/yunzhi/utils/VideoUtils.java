package com.union.yunzhi.yunzhi.utils;

import android.content.Context;
import android.content.Intent;

import com.union.yunzhi.factories.moudles.classfication.ClassConst;
import com.union.yunzhi.yunzhi.activities.classfication.VideoActivity;

/**
 * Created by cjw on 2018/3/10 0010.
 */

public class VideoUtils {

    private  Context context;

    private  String videoUrl, videoName, coverUrl;

    private  Intent intent;

    public static VideoUtils newInstance(Context context, String videoName, String videoUrl,String coverUrl) {

        return new VideoUtils(context,videoName,videoUrl,coverUrl);
    }

    /**
     *
     * @param context 本页面的上下文
     * @param videoName 视频标题
     * @param videoUrl  视频地址
     * @param coverUrl  封面地址
     */
    public  VideoUtils(Context context, String videoName, String videoUrl,String coverUrl) {

        this.context = context;
        this.videoName = videoName;
        this.videoUrl = videoUrl;
        this.coverUrl=coverUrl;
    }

    public  void startVideo() {

        intent=new Intent(context, VideoActivity.class);
        intent.putExtra(ClassConst.VIDEO_NAME,videoName);
        intent.putExtra(ClassConst.VIDEO_URL,videoUrl);
        intent.putExtra(ClassConst.VIDEO_COVER_URL,coverUrl);
        context.startActivity(intent);

        //VideoActivity.newInstance();
    }


}
