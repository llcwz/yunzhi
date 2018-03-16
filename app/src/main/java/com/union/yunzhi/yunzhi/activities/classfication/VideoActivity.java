package com.union.yunzhi.yunzhi.activities.classfication;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.bumptech.glide.Glide;
import com.union.yunzhi.factories.moudles.classfication.ClassConst;
import com.union.yunzhi.yunzhi.R;

import cn.jzvd.JZMediaSystem;
import cn.jzvd.JZVideoPlayer;
import cn.jzvd.JZVideoPlayerStandard;

/**
 * Created by cjw on 2018/3/10 0010.
 */

public class VideoActivity extends AppCompatActivity{

    protected JZVideoPlayerStandard mplayer;
    protected String videoUrl,videoName,videCoverUrl;

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

        videoUrl = bundle.getString(ClassConst.VIDEO_URL);
        videoName=bundle.getString(ClassConst.VIDEO_NAME);
        videCoverUrl=bundle.getString(ClassConst.VIDEO_COVER_URL);
    }

    protected void initWidget() {

        mplayer=(JZVideoPlayerStandard)findViewById(R.id.video_player);


    }

    protected void initData() {

        Glide.with(getBaseContext()).load(videCoverUrl).into(mplayer.thumbImageView);

        mplayer.setUp(videoUrl, JZVideoPlayer.SCREEN_WINDOW_NORMAL,videoName);

        mplayer.thumbImageView.setImageResource(R.drawable.jz_backward_icon);

        JZVideoPlayer.setMediaInterface(new JZMediaSystem());

        mplayer.backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
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
