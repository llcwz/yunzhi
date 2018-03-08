package com.union.yunzhi.yunzhi.fragment.main;


import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.union.yunzhi.common.app.FragmentM;
import com.union.yunzhi.common.helper.GlideImageLoader;
import com.union.yunzhi.common.widget.MyAdapter;
import com.union.yunzhi.factories.moudles.live.beans.LiveBean;
import com.union.yunzhi.factories.moudles.live.SpacesItemDecoration;
import com.union.yunzhi.yunzhi.R;
import com.union.yunzhi.yunzhi.adapter.LiveShowAdapter;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class LiveFragment extends FragmentM {


    private Context contex;
    private TextView mTextView;//搜索栏
    private HorizontalScrollView mHorizontalScrollView;//水平分类条
    private LinearLayout mLinearLayout;//水平分类条包含的布局
    private Banner mBanner;//轮播推荐
    private RecyclerView mRecycleView;//显示直播列表
    private List<String> mTitle;

    //测试数据
    private List<Integer> images;
    private List<LiveBean> list;


    @Override
    protected int getContentLayoutId() {
        return R.layout.main_fragment_live;
    }

    @Override
    protected void initArgs(Bundle bundle) {
        super.initArgs(bundle);
        mTitle=new ArrayList<>();
        images=new ArrayList<>();
        list=new ArrayList<>();
        contex=getActivity();

        //滚动条测试数据
        mTitle.add("关注");mTitle.add("热课");mTitle.add("考研");mTitle.add("计算机");
        mTitle.add("医学");mTitle.add("经管");mTitle.add("文学");mTitle.add("工学");
        mTitle.add("理学");mTitle.add("生命科学");mTitle.add("物理学");

        //轮播测试数据
        images.add(R.mipmap.aa);
        images.add(R.mipmap.bb);
        images.add(R.mipmap.cc);

        //直播显示测试数据
        list.add(new LiveBean(contex,R.mipmap.timg1));list.add(new LiveBean(contex,R.mipmap.timg2));list.add(new LiveBean(contex,R.mipmap.timg3));
        list.add(new LiveBean(contex,R.mipmap.timg4));list.add(new LiveBean(contex,R.mipmap.timg5));list.add(new LiveBean(contex,R.mipmap.timg6));
        list.add(new LiveBean(contex,R.mipmap.timg7));list.add(new LiveBean(contex,R.mipmap.timg8));list.add(new LiveBean(contex,R.mipmap.timg9));
        list.add(new LiveBean(contex,R.mipmap.timg10));
    }

    @Override
    protected void initWidget(View view) {
        mHorizontalScrollView= (HorizontalScrollView) view.findViewById(R.id.horizontal_view);
        mLinearLayout= (LinearLayout) view.findViewById(R.id.layout_horizontal_view);
        mBanner= (Banner) view.findViewById(R.id.banner_live);
        mRecycleView= (RecyclerView) view.findViewById(R.id.rec_live);

        //初始化渲染横向滚动条
        addView(mLinearLayout,mTitle);

        //初始化渲染轮播组件
        mBanner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR)
                .setImageLoader(new GlideImageLoader())
                .setImages(images)
                .setBannerAnimation(Transformer.DepthPage)
                .isAutoPlay(true)
                .setDelayTime(1000)
                .setIndicatorGravity(BannerConfig.CENTER)
                .start();
        //轮播开始渲染
        mBanner.start();

    }

    @Override
    protected void initData() {

        //渲染RecycleView
        StaggeredGridLayoutManager mStaggeredGridLayoutManager=new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL);

        //设置间距
        mRecycleView.setPadding(1,1,1,1);
        SpacesItemDecoration decoration=new SpacesItemDecoration(1);
        mRecycleView.addItemDecoration(decoration);
        mRecycleView.setLayoutManager(mStaggeredGridLayoutManager);

        LiveShowAdapter adapter=new LiveShowAdapter(getContext(),list, new MyAdapter.AdapterListener() {

            @Override
            public void onItemClick(MyAdapter.MyViewHolder holder, Object data) {

            }

            @Override
            public void onItemLongClick(MyAdapter.MyViewHolder holder, Object data) {

            }

            @Override
            public Boolean setAddActionContinue() {
                return false;
            }

            @Override
            public void updataUI(Object object) {

            }
        });


        mRecycleView.setAdapter(adapter);
    }

    /**
     * 直播分类横向滚动条
     * @param mLinearLayout 包含选项的父布局
     * @param mTitle 项目的显示数据
     */
    public void addView(LinearLayout mLinearLayout,List<String> mTitle){

        LinearLayout.LayoutParams params,params1,params2;
        final List<TextView> viewList=new ArrayList<>();
        params=new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT);
        params1=new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT);
        params2=new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT);
        params.leftMargin=40; params1.leftMargin=10;
        params2.leftMargin=40; params2.rightMargin=10;
        final int count=mTitle.size();
        for(int i=0;i<count;i++){
            TextView tv=new TextView(getContext());
            tv.setTextSize(20);
            Resources resources=getContext().getResources();
            final ColorStateList color0=resources.getColorStateList(R.color.black_alpha_128);
            final ColorStateList color1=resources.getColorStateList(R.color.black);
            /**
             * 默认第一项为选中状态
             */
            if(i==0){tv.setTextColor(color1);
            }else{tv.setTextColor(color0);}
            tv.setGravity(Gravity.CENTER_VERTICAL);
            viewList.add(tv);
            tv.setText(mTitle.get(i));
            final int finalI = i;
            tv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    TextView temp= (TextView) v;
                    for(int j=0;j<count;j++){
                        if(j==finalI){
                            //TODO 设置分类条项目选中后的渲染效果
                            viewList.get(j).setTextColor(color1);
                        }else{
                            //TODO 设置分类条项目未选中的渲染效果
                            viewList.get(j).setTextColor(color0);
                        }
                    }
                    onClickSet(v, finalI);
                }
            });
            if(i==0){
                mLinearLayout.addView(tv,params1);
            }else if(i==count-1){
                mLinearLayout.addView(tv,params2);
            }else{
                mLinearLayout.addView(tv,params);
            }

        }

    }

    /**
     * 分类条子项点击事件
     * @param v 选中的view对象
     * @param positon 该项目在集合中的位置
     */
    void onClickSet(View v,int positon){
        //TODO 分类条点击事件
        Toast.makeText(getContext(),"点击了"+mTitle.get(positon),Toast.LENGTH_SHORT).show();
    }

}
