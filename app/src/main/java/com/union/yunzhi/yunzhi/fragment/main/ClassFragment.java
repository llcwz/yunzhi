package com.union.yunzhi.yunzhi.fragment.main;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.union.yunzhi.common.app.FragmentM;
import com.union.yunzhi.common.helper.GlideImageLoader;
import com.union.yunzhi.common.helper.HiddenAnimUtils;
import com.union.yunzhi.common.widget.MyAdapter;
import com.union.yunzhi.factories.moudles.classfication.ClassConst;
import com.union.yunzhi.factories.moudles.classfication.beans.CourseShowBean;
import com.union.yunzhi.factories.moudles.classfication.beans.TitleBean;
import com.union.yunzhi.yunzhi.R;
import com.union.yunzhi.yunzhi.adapter.ClassCourseAdapter;
import com.union.yunzhi.yunzhi.adapter.ClassDrawerAdapter;
import com.union.yunzhi.yunzhi.classfication.ClassCourseDetails;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class ClassFragment extends FragmentM implements View.OnClickListener{

    private TextView mTextView1,mTextView2;
    private ImageView mImageView1,mImageView2;
    private View hidden_coprhsv;
    private DrawerLayout mDrawerLayout;
    private Banner mBanner;
    private List<Integer> image;
    private List<String> title;
    private List<CourseShowBean> test2;
    private List<TitleBean> test1;
    private List<String> test;
    private RecyclerView mRecyclerView,mRecycleView2;
    private ConstraintLayout mConstraintLayout;
    private RadioGroup mRadioGroup1,mRadioGroup2;
    private RadioButton mRadioButton1,mRadioButton2,mRadioButton3,mRadioButton4,mRadioButton5,mRadioButton6,mRadioButton7;
    private int ID1,ID2;
    private String range,state;
    private View.OnClickListener onClick;
    private View.OnLongClickListener onLongClick;


    @Override
    protected int getContentLayoutId() {
        return R.layout.main_fragment_class;
    }


    @Override
    protected void initArgs(Bundle bundle) {
        super.initArgs(bundle);

        image=new ArrayList<>();
        title=new ArrayList<>();
        test=new ArrayList<>();
        test1=new ArrayList<>();
        test2=new ArrayList<>();

        //轮播测试数据
        image.add(R.mipmap.aa);
        image.add(R.mipmap.bb);
        image.add(R.mipmap.cc);
        title.add("快乐寒假");title.add("快乐暑假");title.add("快乐春节");

        //课程测试数据
        CourseShowBean bean=new CourseShowBean("操作系统","张三","index.jpg",1236,12,670);
        test2.add(bean);test2.add(bean);test2.add(bean);test2.add(bean);
        test2.add(bean);test2.add(bean);test2.add(bean);test2.add(bean);
        test2.add(bean);test2.add(bean);test2.add(bean);test2.add(bean);


        //分类测试抽屉数据
        test.add("数据库");test.add("计算机组成原理");test.add("C语言程序设计");test.add("算法分析与设计");
        test.add("计算机网络");test.add("操作系统");test.add("嵌入式系统设计");test.add("人工智能与算法");
        test.add("软件工程");test.add("数值计算");test.add("Linux系统");test.add("Java程序设计");

        TitleBean kiss=new TitleBean("计算机学院",test,null);
        test1.add(kiss);test1.add(kiss);test1.add(kiss);test1.add(kiss);test1.add(kiss);
        test1.add(kiss);test1.add(kiss);test1.add(kiss);test1.add(kiss);test1.add(kiss);
        test1.add(kiss);test1.add(kiss);test1.add(kiss);test1.add(kiss);test1.add(kiss);
    }

    @Override
    protected void initWidget(View view) {

        //侧滑栏得抽屉
        mDrawerLayout=(DrawerLayout) view.findViewById(R.id.lv_drawer);
        mDrawerLayout.addDrawerListener(new DrawerLayout.DrawerListener() {
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                //TODO 抽屉滑动时执行的操作
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                //TODO 抽屉打开时执行的操作
                if(hidden_coprhsv.getVisibility()==View.VISIBLE){
                    showHidden();
                }
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                //TODO 抽屉关闭时执行的操作
            }

            @Override
            public void onDrawerStateChanged(int newState) {
                // 监听策划页面的各种滑动状态
                switch(newState){

                    case DrawerLayout.STATE_DRAGGING:
                        if(hidden_coprhsv.getVisibility()==View.VISIBLE){
                            showHidden();
                        }
                        break;
                    default:
                        break;
                }

            }
        });

        //头部控制条

        mTextView1=(TextView) view.findViewById(R.id.tv_all);
        mTextView2=(TextView) view.findViewById(R.id.tv_comprehensive);
        mImageView1=(ImageView) view.findViewById(R.id.iv_all);
        mImageView2=(ImageView) view.findViewById(R.id.iv_comprehensive);
        hidden_coprhsv=view.findViewById(R.id.hidden_comprhsv);
        mConstraintLayout= (ConstraintLayout) view.findViewById(R.id.constraint_class_drawer_head);
        mTextView1.setOnClickListener(this);
        mTextView2.setOnClickListener(this);
        mImageView1.setOnClickListener(this);
        mImageView2.setOnClickListener(this);
        hidden_coprhsv.setOnClickListener(this);
        mConstraintLayout.setOnClickListener(this);

        //轮播
        mBanner=(Banner)view.findViewById(R.id.banner_class);
        //初始化设置隐藏布局为不可见
        hidden_coprhsv.setVisibility(View.GONE);

        /**
         * 设置轮播风格
         * 设置图片加载器 设置图片集合 设置标题集合 设置动画效果
         * 设置自动播放 设置轮播时间 设置指示器位置
         */
        mBanner.setBannerStyle(BannerConfig.NUM_INDICATOR_TITLE)
                .setImageLoader(new GlideImageLoader())
                .setImages(image)
                .setBannerTitles(title)
                .setBannerAnimation(Transformer.DepthPage)
                .isAutoPlay(true)
                .setDelayTime(1000)
                .setIndicatorGravity(BannerConfig.CENTER)
                .start();
        //开始渲染
        mBanner.start();

        //显示课程
        mRecyclerView=(RecyclerView) view.findViewById(R.id.rec_course);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(linearLayoutManager);

        //分类抽屉栏
        mRecycleView2=(RecyclerView)view.findViewById(R.id.rec_drawer);
        LinearLayoutManager linearLayoutManager2=new LinearLayoutManager(getContext());
        mRecycleView2.setLayoutManager(linearLayoutManager2);

        //下拉排序页面
        mRadioGroup1= (RadioGroup) view.findViewById(R.id.rg_range);
        mRadioGroup2= (RadioGroup) view.findViewById(R.id.rg_state);
        mRadioButton1= (RadioButton) view.findViewById(R.id.rb_comprehv);
        mRadioButton2= (RadioButton) view.findViewById(R.id.rb_new);
        mRadioButton3= (RadioButton) view.findViewById(R.id.rb_hot);
        mRadioButton4= (RadioButton) view.findViewById(R.id.rb_all);
        mRadioButton5= (RadioButton) view.findViewById(R.id.rb_finish);
        mRadioButton6= (RadioButton) view.findViewById(R.id.rb_not_finish);
        mRadioButton7= (RadioButton) view.findViewById(R.id.rb_not_start);

        //初始化排序提示
        ID1=changeIdToConst(mRadioGroup1.getCheckedRadioButtonId());
        ID2=changeIdToConst(mRadioGroup2.getCheckedRadioButtonId());
        mTextView2.setText(range+"-"+state);

        mRadioGroup1.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {

                ID1=changeIdToConst(checkedId);
                ID2=changeIdToConst(mRadioGroup2.getCheckedRadioButtonId());
                mTextView2.setText(range+"-"+state);
                /**
                 * 根据获取的参数访问服务器更新UI
                 */
                getSearchResult(ID1,ID2);
            }
        });

        mRadioGroup2.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {

                ID1=changeIdToConst(mRadioGroup1.getCheckedRadioButtonId());
                ID2=changeIdToConst(checkedId);
                mTextView2.setText(range+"-"+state);
                /**
                 * 根据获取的参数访问服务器更新UI
                 */
                getSearchResult(ID1,ID2);
            }
        });
    }

    @Override
    protected void initData() {

        // TODO itemView 内部点击事件
        onClick=new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                switch(v.getId()){

                    case R.id.imgBtn_thumbUp:
                        Toast.makeText(getActivity(),"点赞",Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.imgBtn_thumbDown:
                        Toast.makeText(getActivity(),"差评",Toast.LENGTH_SHORT).show();
                        break;
                    default:
                        break;
                }
            }
        };

        // TODO itemView 内部长按点击事件
        onLongClick=new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                return false;
            }
        };


        //显示课程的适配
        ClassCourseAdapter adapter=new ClassCourseAdapter(getContext(),test2, new MyAdapter.AdapterListener<CourseShowBean>(){

            @Override
            public void onItemClick(MyAdapter.MyViewHolder holder, CourseShowBean data) {
                //TODO 课程单元点击
                startActivity(new Intent(getContext(), ClassCourseDetails.class));
            }

            @Override
            public void onItemLongClick(MyAdapter.MyViewHolder holder, CourseShowBean data) {
                //TODO 课程单元长按
            }

            @Override
            public Boolean setAddActionContinue() {
                return false;
            }

            @Override
            public void updataUI(Object object) {

            }
        });



        mRecyclerView.setAdapter(adapter);

        //显示分类适配
        ClassDrawerAdapter adapter1=new ClassDrawerAdapter(test1, new MyAdapter.AdapterListener<TitleBean>() {

            @Override
            public void onItemClick(MyAdapter.MyViewHolder holder,TitleBean  data) {
                    //TODO 点击分类单元块执行的操作
            }

            @Override
            public void onItemLongClick(MyAdapter.MyViewHolder holder, TitleBean data) {
                    //TODO 长按分类单元块执行的操作
            }

            @Override
            public Boolean setAddActionContinue() {
                return false;
            }

            @Override
            public void updataUI(Object object) {
                String temp=object.toString();
                mTextView1.setText(temp);
            }
        });

        mRecycleView2.setAdapter(adapter1);

    }

    /**
     * 抽屉拉动
     */
    public void showDrawer(){
        if(mDrawerLayout.isDrawerOpen(Gravity.LEFT)){
            mDrawerLayout.closeDrawer(Gravity.LEFT);
        }else{
            mDrawerLayout.openDrawer(Gravity.LEFT);
        }
    }

    /**
     * 布局隐藏或者出现
     */
    public void showHidden(){
        int tag= HiddenAnimUtils.newInstance(getContext(),hidden_coprhsv,mImageView2,172,30).toggle();
        if(tag==0){ mTextView2.setText(range+"-"+state);}
        else{ mTextView2.setText("收起");}
    }

    /**
     *
     * @param arg1 根据RadioGroup1确定的选项
     * @param arg2 根据RadioGroup2确定的选项
     */
    void getSearchResult(int arg1,int arg2){

        //TODO 依据这两个选项参数访问排序后的数据集合并更新到UI
        /**
         * 注意参数为 1 2 3 4 5 6 7 正常
         * 得到-1则为获取Id异常
         */

    }

    int changeIdToConst(int id){

        int temp=-1;
        switch (id){
            case R.id.rb_comprehv:
                temp= ClassConst.CLASS_COMPREHENSIVE;
                range="综合";
                break;
            case R.id.rb_new:
                temp= ClassConst.CLASS_NEW;
                range="最新";
                break;
            case R.id.rb_hot:
                range="最热";
                temp= ClassConst.CLASS_HOT;
                break;
            case R.id.rb_all:
                temp= ClassConst.CLASS_ALL_COURSE;
                state="所有课程";
                break;
            case R.id.rb_finish:
                temp= ClassConst.CLASS_FINISHED;
                state="已完成";
                break;
            case R.id.rb_not_finish:
                temp= ClassConst.CLASS_NOT_FINISHED;
                state="未完成";
                break;
            case R.id.rb_not_start:
                temp= ClassConst.CLASS_NOT_START;
                state="待开始";
                break;
            default:
                break;
        }

        return temp;
    }

    @Override
    public void onClick(View v) {

        switch(v.getId()){

            case R.id.tv_all:
            case R.id.iv_all:
                // TODO 点击“全部” 侧滑页面出现
                showDrawer();
                break;
            case R.id.tv_comprehensive:
            case R.id.iv_comprehensive:
                // TODO 点击“综合” 隐藏的选择综合排序方式页面出现
                showHidden();
                break;
            case R.id.hidden_comprhsv:
                if(hidden_coprhsv.getVisibility()==View.VISIBLE){
                    showHidden();
                }
                break;
            case R.id.constraint_class_drawer_head:
                //TODO 点击drawer"全部课程"执行操作
                mTextView1.setText("全部课程");
                break;
            default:
                break;
        }

    }
}
