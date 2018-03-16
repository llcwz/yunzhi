package com.union.yunzhi.yunzhi.activities.classfication;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.union.yunzhi.factories.moudles.classfication.beans.video.BaseVideoBean;
import com.union.yunzhi.factories.moudles.communication.BaseCommentModel;
import com.union.yunzhi.yunzhi.R;
import com.union.yunzhi.yunzhi.contant.Constant;

import cn.jzvd.JZMediaSystem;
import cn.jzvd.JZVideoPlayer;
import cn.jzvd.JZVideoPlayerStandard;

/**
 * Created by cjw on 2018/3/10 0010.
 */

public class VideoActivity extends AppCompatActivity{

    protected JZVideoPlayerStandard mplayer;
    protected BaseVideoBean video;//视频信息
    protected BaseCommentModel comment;//评论信息

    public static void newInstance(Context context, BaseVideoBean video, BaseCommentModel comment) {

        Intent intent=new Intent(context,VideoActivity.class);
        intent.putExtra(Constant.VIDEO_TAG, (Parcelable) video);
        intent.putExtra(Constant.COMMENT_TAG, (Parcelable) comment);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.class_video);

        initArgs(savedInstanceState);

        initWidget();

        initData();

    }

    protected void initArgs(Bundle bundle) {
        bundle=getIntent().getExtras();
        video=bundle.getParcelable(Constant.VIDEO_TAG);
        comment=bundle.getParcelable(Constant.COMMENT_TAG);
    }

    protected void initWidget() {

        mplayer=(JZVideoPlayerStandard)findViewById(R.id.video_player);


    }

    protected void initData() {

        /**
         * 加载视频信息
         */
//        Glide.with(getBaseContext()).load(vi).into(mplayer.thumbImageView);
//
//        mplayer.setUp(videoUrl, JZVideoPlayer.SCREEN_WINDOW_NORMAL,videoName);

        mplayer.thumbImageView.setImageResource(R.drawable.jz_backward_icon);

        JZVideoPlayer.setMediaInterface(new JZMediaSystem());

        mplayer.backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        /**
         * 适配评论信息
         */





    }

    @Override
    protected void onPause() {
        super.onPause();
        mplayer.releaseAllVideos();

    }

    @Override
    public void onBackPressed() {
        if (mplayer.backPress()) {
            return;
        }
        super.onBackPressed();
    }
}
