package com.union.yunzhi.yunzhi.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.makeramen.roundedimageview.RoundedImageView;
import com.union.yunzhi.common.helper.GlideImageLoader;
import com.union.yunzhi.common.widget.MyAdapter;
import com.union.yunzhi.factories.moudles.classfication.ClassConst;
import com.union.yunzhi.factories.moudles.home.bodyModle;
import com.union.yunzhi.yunzhi.R;
import com.union.yunzhi.yunzhi.activities.classfication.ClassCourseDetailsActivity;
import com.youth.banner.Banner;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by meng on 2018/2/11.
 */

public class HomeAdapter extends MyAdapter<bodyModle> {

    private Context context;

    private int size;

    private final String TGA ="HomeAdapter";

    public HomeAdapter(){

    }

    public HomeAdapter(Context context,int size){
        this.context = context;
        this.size = size;
    }

    @Override
    protected int getItemViewType(int position, bodyModle data) {


        if(data.viewType == 0)
        {

            return R.layout.item_home_fragment_page_two;

        }

        else if(data.viewType == 1){

            return R.layout.item_home_fragment_page_one;

        }else if(data.viewType == 2){

            return R.layout.item_home_fragment_page_three;

        }


        return R.layout.item_home_fragment_page_one;//没有该布局
    }

    @Override
    protected MyAdapter.MyViewHolder<bodyModle> onCreateViewHolder(View root, int viewType) {
        if(viewType == R.layout.item_home_fragment_page_one)
        {

            return new videoViewHodler(root);
        }


        else if(viewType == R.layout.item_home_fragment_page_two){

            return new bannerViewHolder(root);
        }else if(viewType == R.layout.item_home_fragment_page_three){

            return new videoOneViewHodler(root);
        }
        else{

            return null;//没有这个布局
        }


    }

    @Override
    public boolean onLongClick(View v) {
        return false;
    }

    public class bannerViewHolder extends MyViewHolder<bodyModle> {

        private Banner banner;

        public bannerViewHolder(View itemView) {
            super(itemView);
            banner = (Banner) itemView.findViewById(R.id.banner);
        }

        @Override
        protected void onBind(bodyModle data, int postion) {
            banner.setImageLoader(new GlideImageLoader());

           banner.setImages(data.ads);

            banner.start();


            banner.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, ClassCourseDetailsActivity.class);
                    intent.putExtra(ClassConst.COURSEID,"01003");
                    intent.putExtra(ClassConst.TEACHERID,"7");
                    context.startActivity(intent);
                }
            });
        }


    }


    public class videoViewHodler extends MyViewHolder<bodyModle> implements View.OnClickListener {

        /**
         * UI  每个布局顶部的分类
         */
        private TextView mTitles;
        private CircleImageView mIcon;
        private ImageView mArrow;

        /**
         * UI  分类里面对应的4个小视频布局
         */
        private FrameLayout mVideo[] = new FrameLayout[size];

        private RoundedImageView mRoundedImageView1;
        private RoundedImageView mRoundedImageView2;
        private RoundedImageView mRoundedImageView3;
        private RoundedImageView mRoundedImageView4;

        /**
         * 公共部分
         * UI 布局里面对应的控件
         */
        private RoundedImageView mRoundedImageView;
        private TextView mTitle;
        private CircleImageView mPortrait;

        public videoViewHodler(View itemView) {
            super(itemView);

            mTitles = (TextView) itemView.findViewById(R.id.tv_titles);
            mIcon = (CircleImageView) itemView.findViewById(R.id.iv_icon);


            //这里要做动态初始化，预留出模板
            mVideo[0] = (FrameLayout) itemView.findViewById(R.id.video_one);
            mVideo[1] = (FrameLayout) itemView.findViewById(R.id.video_two);
            mVideo[2] = (FrameLayout) itemView.findViewById(R.id.video_three);
            mVideo[3] = (FrameLayout) itemView.findViewById(R.id.video_four);


            mRoundedImageView1 = (RoundedImageView) mVideo[0].findViewById(R.id.round_img);
            mRoundedImageView2 = (RoundedImageView) mVideo[1].findViewById(R.id.round_img);
            mRoundedImageView3 = (RoundedImageView) mVideo[2].findViewById(R.id.round_img);
            mRoundedImageView4 = (RoundedImageView) mVideo[3].findViewById(R.id.round_img);
            /**
             * 公共部分初始化
             */
//            mRoundedImageView = (RoundedImageView) itemView.findViewById(R.id.round_img);
//            mTitle = (TextView) itemView.findViewById(R.id.tv_title);
//            mPortrait = (CircleImageView) itemView.findViewById(R.id.ci_portrait);
        }

        @Override
        protected void onBind(final bodyModle data, int postion) {

            String[] titles = context.getResources().getStringArray(R.array.titles);
            int id = (int) (Math.random()*(titles.length-1));//随机产生一个index索引
            mTitles.setText(titles[id]);
            /**
             * 设置分类内部
             */

            /**
             * 循环的去初始化我们的内部布局
             */
            for(int i=0;i<size;i++){
                mRoundedImageView = (RoundedImageView) mVideo[i].findViewById(R.id.round_img);
                mTitle = (TextView) mVideo[i].findViewById(R.id.tv_title);
                mPortrait = (CircleImageView) mVideo[i].findViewById(R.id.ci_portrait);

                //设置背景图片
                Glide.with(context)
                        //.load(data.mVideoClassModle.videoModle.get(i).PhotoUrl)
                        .load(data.coursecover.get(i))
                        .into(mRoundedImageView);

                mTitle.setText(data.title.get(i));

                Glide.with(context)
                        .load(data.image.get(i))
                       // .load(data.mVideoClassModle.videoModle.get(i).PortraitUrl)
                        .into(mPortrait);

                mRoundedImageView1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(context, ClassCourseDetailsActivity.class);
                        intent.putExtra(ClassConst.COURSEID,data.courseid.get(0));
                        intent.putExtra(ClassConst.TEACHERID,data.teacherid.get(0));
                        context.startActivity(intent);
                    }
                });

                mRoundedImageView2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(context, ClassCourseDetailsActivity.class);
                        intent.putExtra(ClassConst.COURSEID,data.courseid.get(1));
                        intent.putExtra(ClassConst.TEACHERID,data.teacherid.get(1));
                        context.startActivity(intent);
                    }
                });

                mRoundedImageView3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(context, ClassCourseDetailsActivity.class);
                        intent.putExtra(ClassConst.COURSEID,data.courseid.get(2));
                        intent.putExtra(ClassConst.TEACHERID,data.teacherid.get(2));
                        context.startActivity(intent);
                    }
                });

                mRoundedImageView4.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(context, ClassCourseDetailsActivity.class);
                        intent.putExtra(ClassConst.COURSEID,data.courseid.get(3));
                        intent.putExtra(ClassConst.TEACHERID,data.teacherid.get(3));
                        context.startActivity(intent);
                    }
                });


            }//end for
        }

        @Override
        public void onClick(View v) {
            switch (v.getId()){
            }
        }
    }

    public class videoOneViewHodler extends MyViewHolder<bodyModle> {

        private TextView mShow;
        private RoundedImageView mRoundedImageView;
        private TextView mTitle;
        private CircleImageView mPortrait;

        public videoOneViewHodler(View itemView) {
            super(itemView);
            mShow = (TextView) itemView.findViewById(R.id.tv_introduce);
            mRoundedImageView = (RoundedImageView)itemView.findViewById(R.id.round_img);
            mTitle = (TextView)itemView.findViewById(R.id.tv_title);
            mPortrait = (CircleImageView) itemView.findViewById(R.id.ci_portrait);
        }

        @Override
        protected void onBind(final bodyModle data, int position) {

            String[] titles = context.getResources().getStringArray(R.array.introduce);
            int id = (int) (Math.random()*(titles.length-1));//随机产生一个index索引
            mShow.setText(titles[id]);


            //设置背景图片
            Glide.with(context)
                    //.load(data.mVideoClassModle.videoModle.get(i).PhotoUrl)
                    .load(data.coursecover.get(0))
                    .into(mRoundedImageView);

            mTitle.setText(data.title.get(0));

            Glide.with(context)
                    .load(data.image.get(0))
                    // .load(data.mVideoClassModle.videoModle.get(i).PortraitUrl)
                    .into(mPortrait);

            mRoundedImageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, ClassCourseDetailsActivity.class);
                    intent.putExtra(ClassConst.COURSEID,data.courseid.get(0));
                    intent.putExtra(ClassConst.TEACHERID,data.teacherid.get(0));
                    context.startActivity(intent);
                }
            });
        }

    }


}
