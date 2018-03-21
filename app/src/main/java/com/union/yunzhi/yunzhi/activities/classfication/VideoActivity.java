package com.union.yunzhi.yunzhi.activities.classfication;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.bumptech.glide.Glide;
import com.union.yunzhi.factories.moudles.classfication.beans.video.VideoBean;
import com.union.yunzhi.factories.moudles.communication.BaseCommentModel;
import com.union.yunzhi.yunzhi.R;
import com.union.yunzhi.yunzhi.contant.Constant;
import com.union.yunzhi.yunzhi.fragment.classfication.ClassQuestionFragment;

import cn.jzvd.JZMediaSystem;
import cn.jzvd.JZVideoPlayer;
import cn.jzvd.JZVideoPlayerStandard;

/**
 * Created by cjw on 2018/3/10 0010.
 */

public class VideoActivity extends AppCompatActivity{

    protected JZVideoPlayerStandard mplayer;
    protected VideoBean video;//视频信息
    protected BaseCommentModel comment;//评论信息
    private FragmentManager mFragmentManager;
    private ClassQuestionFragment mClassQuestionFragment;

    public static void newInstance(Context context, VideoBean video, BaseCommentModel comment) {

        Intent intent=new Intent(context,VideoActivity.class);
        intent.putExtra(Constant.VIDEO_TAG,video);
        intent.putExtra(Constant.COMMENT_TAG,comment);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.class_video);

        initWidget();

        initData();

    }

    protected void initWidget() {

        video=getIntent().getParcelableExtra(Constant.VIDEO_TAG);

        comment=getIntent().getParcelableExtra(Constant.COMMENT_TAG);

        mplayer=(JZVideoPlayerStandard)findViewById(R.id.video_player);

        mFragmentManager = getSupportFragmentManager();
    }

    protected void initData() {

        /**
         * 加载视频信息
         */
        Glide.with(getBaseContext()).load(video.coverurl).into(mplayer.thumbImageView);

        mplayer.setUp(video.videourl, JZVideoPlayer.SCREEN_WINDOW_NORMAL,video.videotitle);

        mplayer.thumbImageView.setImageResource(R.drawable.jz_backward_icon);

        JZVideoPlayer.setMediaInterface(new JZMediaSystem());

        mplayer.backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        /**
         * 通过对象comment
         * 适配评论信息
         */

        initQuestionFragment();

    }


    private void initQuestionFragment() {
        if (mClassQuestionFragment == null) {
            mClassQuestionFragment = ClassQuestionFragment.newInstance(video.videoid);
        }
        FragmentTransaction transaction = mFragmentManager.beginTransaction();
        transaction.add(R.id.fragment_layout, mClassQuestionFragment);
        transaction.commit();
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
