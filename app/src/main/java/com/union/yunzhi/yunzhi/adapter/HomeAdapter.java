package com.union.yunzhi.yunzhi.adapter;

import android.content.Context;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.makeramen.roundedimageview.RoundedImageView;
import com.union.yunzhi.common.helper.GlideImageLoader;
import com.union.yunzhi.common.util.LogUtils;
import com.union.yunzhi.common.widget.MyAdapter;
import com.union.yunzhi.factories.moudles.home.bodyModle;
import com.union.yunzhi.yunzhi.R;
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

        LogUtils.i("getItemViewType--",data.viewType+"");

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
        }


    }


    public class videoViewHodler extends MyViewHolder<bodyModle> {

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

        private FrameLayout mVideo_one;
        private FrameLayout mVideo_two;
        private FrameLayout mVideo_three;
        private FrameLayout mVideo_four;




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
            mArrow = (ImageView) itemView.findViewById(R.id.iv_arrow);


            //这里要做动态初始化，预留出模板
            mVideo[0] = (FrameLayout) itemView.findViewById(R.id.video_one);
            mVideo[1] = (FrameLayout) itemView.findViewById(R.id.video_two);
            mVideo[2] = (FrameLayout) itemView.findViewById(R.id.video_three);
            mVideo[3] = (FrameLayout) itemView.findViewById(R.id.video_four);

            /*

            //TODO 动态实现初始化
            mVideo_one = (FrameLayout) itemView.findViewById(R.id.video_one);
            mVideo_two = (FrameLayout) itemView.findViewById(R.id.video_two);
            mVideo_three = (FrameLayout) itemView.findViewById(R.id.video_three);
            mVideo_four = (FrameLayout) itemView.findViewById(R.id.video_four);
            */

            /**
             * 公共部分初始化
             */
//            mRoundedImageView = (RoundedImageView) itemView.findViewById(R.id.round_img);
//            mTitle = (TextView) itemView.findViewById(R.id.tv_title);
//            mPortrait = (CircleImageView) itemView.findViewById(R.id.ci_portrait);
        }

        @Override
        protected void onBind(bodyModle data, int postion) {

            /**
             * 设置分类的头部
             */
//            Glide.with(context)
//                    .load(data.mVideoClassModle.iconUrl)
//                    .centerCrop()
//                    .placeholder(R.drawable.bg1)
//                    .into(mIcon);

            //data.mVideoClassModle.videoClass
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



            }//end for


        }
    }

    public class videoOneViewHodler extends MyViewHolder<bodyModle>{

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
        protected void onBind(bodyModle data, int position) {

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
        }
    }


}
