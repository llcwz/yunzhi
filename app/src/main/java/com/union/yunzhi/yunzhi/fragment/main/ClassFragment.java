package com.union.yunzhi.yunzhi.fragment.main;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.union.yunzhi.common.app.FragmentM;
import com.union.yunzhi.common.helper.GlideImageLoader;
import com.union.yunzhi.common.helper.HiddenAnimUtils;
import com.union.yunzhi.common.helper.ScreenUtils;
import com.union.yunzhi.common.util.LogUtils;
import com.union.yunzhi.common.widget.MyAdapter;
import com.union.yunzhi.factories.moudles.classfication.ClassConst;
import com.union.yunzhi.factories.moudles.classfication.beans.classfication.BaseCarouselBean;
import com.union.yunzhi.factories.moudles.classfication.beans.classfication.BaseCourseShowBean;
import com.union.yunzhi.factories.moudles.classfication.beans.classfication.CourseShowBean;
import com.union.yunzhi.factories.moudles.classfication.beans.drawer.BaseDrawerBean;
import com.union.yunzhi.factories.moudles.classfication.beans.drawer.DrawerBean;
import com.union.yunzhi.factories.okhttp.listener.DisposeDataListener;
import com.union.yunzhi.yunzhi.R;
import com.union.yunzhi.yunzhi.activities.SearchActivity;
import com.union.yunzhi.yunzhi.activities.classfication.ClassCourseDetailsActivity;
import com.union.yunzhi.yunzhi.adapter.ClassCourseAdapter;
import com.union.yunzhi.yunzhi.adapter.ClassDrawerAdapter;
import com.union.yunzhi.yunzhi.contant.Constant;
import com.union.yunzhi.yunzhi.network.HttpConstants;
import com.union.yunzhi.yunzhi.network.RequestCenter;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * A simple {@link Fragment} subclass.
 */
public class ClassFragment extends FragmentM implements View.OnClickListener,View.OnLongClickListener,OnLoadMoreListener{

    private TextView mTextView1,mTextView2;
    private ImageView mImageView1,mImageView2;
    private View hidden_coprhsv;
    private DrawerLayout mDrawerLayout;
    private Banner mBanner;
    private RecyclerView mRecyclerView,mRecycleView2;
    private SmartRefreshLayout mSmartRefreshLayout;
    private ConstraintLayout mConstraintLayout;
    private RadioGroup mRadioGroup1,mRadioGroup2;
    private RadioButton mRadioButton1,mRadioButton2,mRadioButton3,mRadioButton4,mRadioButton5,mRadioButton6,mRadioButton7;
    private int ID1,ID2;
    private String range,state;
    private View.OnClickListener onClick;
    private View.OnLongClickListener onLongClick;
    private LinearLayout mLinearLayout,mLinearLayout1,mToor;
    private CircleImageView load,qrcode;
    private String courseId="";
    private ClassCourseAdapter adapter;


    //轮播图数据集合
    private List<String> images;

    //侧滑栏数据集合
    private List<DrawerBean> drawerBeanList=new ArrayList<>();

    //课程列表数据集合
    private List<CourseShowBean> courseList;



    @Override
    protected int getContentLayoutId() {
        return R.layout.main_fragment_class;
    }


    @Override
    protected void initArgs(Bundle bundle) {
        super.initArgs(bundle);
        courseList=new ArrayList<>();
        images=new ArrayList<>();
        drawerBeanList =new ArrayList<>();

    }

    @Override
    protected void initWidget(View view) {

        //顶部搜索栏
        mToor= (LinearLayout) view.findViewById(R.id.toor);
        load= (CircleImageView) view.findViewById(R.id.cv_load);
        qrcode= (CircleImageView) view.findViewById(R.id.cv_qrcode);
        load.setVisibility(View.GONE);
        qrcode.setVisibility(View.GONE);
        mToor.setOnClickListener(this);

        //侧滑栏得抽屉
        mLinearLayout1= (LinearLayout) view.findViewById(R.id.drawer_class);
        //DrawerLayout.LayoutParams params=new DrawerLayout.LayoutParams((int)(5.0/6)*ScreenUtils.getScreenWidth(getContext()), ViewGroup.LayoutParams.MATCH_PARENT);
        //mLinearLayout1.setLayoutParams(params);
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
        mLinearLayout= (LinearLayout) view.findViewById(R.id.layout_carousel);
        LinearLayout.LayoutParams params1=new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, (int)((1.0/3)*ScreenUtils.getScreenWidth(getActivity())));
        mLinearLayout.setLayoutParams(params1);
        mBanner=(Banner)view.findViewById(R.id.banner_class);
        //初始化设置隐藏布局为不可见
        hidden_coprhsv.setVisibility(View.GONE);

        //显示课程
        mRecyclerView=(RecyclerView) view.findViewById(R.id.rec_course);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mSmartRefreshLayout= (SmartRefreshLayout) view.findViewById(R.id.refresh_course);
        mSmartRefreshLayout.setEnableRefresh(false);
        mSmartRefreshLayout.setOnLoadMoreListener(this);

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


        /**
         * 设置轮播风格
         * 设置图片加载器 设置图片集合 设置标题集合 设置动画效果
         * 设置自动播放 设置轮播时间 设置指示器位置
         */

        RequestCenter.requestCarousel(HttpConstants.GET_CAROUSEL, new DisposeDataListener() {
            @Override
            public void onSuccess(Object responseObj) {
                BaseCarouselBean temp= (BaseCarouselBean) responseObj;
                images=temp.data;
                if(temp.ecode==0){
                    images=temp.data;
                    mBanner.setBannerStyle(BannerConfig.NUM_INDICATOR)
                            .setImageLoader(new GlideImageLoader())
                            .setImages(images)
                            .setBannerAnimation(Transformer.DepthPage)
                            .isAutoPlay(true)
                            .setDelayTime(3000)
                            .setIndicatorGravity(BannerConfig.CENTER)
                            .start();
                    mBanner.start();//开始渲染
                }
            }

            @Override
            public void onFailure(Object reasonObj) {
                Toast.makeText(getContext(),"网络请求失败",Toast.LENGTH_SHORT).show();
            }
        });

        //首次进入分类时请求数据
        requestCourse(courseId);


    }

    @Override
    public void initRefreshData() {
        super.initRefreshData();
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

    /**
     * 加载侧滑栏信息
     */
    void requestDrawer(){
        RequestCenter.requestDrawer(HttpConstants.ACADEMY_COURSE, new DisposeDataListener() {
            @Override
            public void onSuccess(Object responseObj) {

                BaseDrawerBean temp= (BaseDrawerBean) responseObj;
                if(temp.ecode==0){
                    drawerBeanList=temp.data;
                    LogUtils.d("My","----------"+drawerBeanList.size());
                    //显示分类适配
                    ClassDrawerAdapter adapter1=new ClassDrawerAdapter(drawerBeanList, new MyAdapter.AdapterListener<DrawerBean>() {

                        @Override
                        public void onItemClick(MyAdapter.MyViewHolder holder,DrawerBean data) {
                            //TODO 点击分类单元块执行的操作
                        }

                        @Override
                        public void onItemLongClick(MyAdapter.MyViewHolder holder, DrawerBean data) {
                            //TODO 长按分类单元块执行的操作
                        }

                        @Override
                        public Boolean setAddActionContinue() {
                            return false;
                        }

                        @Override
                        public void updataUI(Object object) {
                            Map<String,String> temp=(Map<String,String>)object;
                            String textShow=temp.get(Constant.TEXT_SHOW);
                            courseId=temp.get(Constant.ID);
                            if(textShow!=null&&textShow!=""){
                                mTextView1.setText(textShow);
                            }else{
                                LogUtils.d("KK","传入textShow为空串");
                            }

                            if(courseId!=null){
                                requestCourse(courseId);
                                showDrawer();
                            }else{
                                LogUtils.d("KK","传入courseId为");
                            }
                        }
                    });
                    mRecycleView2.setAdapter(adapter1);
                    showDrawer();
                }else{
                    //TODO 请求失败显示窗口
                    showDrawer();
                    Toast.makeText(getContext(),"网络链接失败1",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Object reasonObj) {
                //TODO 请求失败显示窗口
                showDrawer();
                Toast.makeText(getContext(),"网络链接失败2",Toast.LENGTH_SHORT).show();
            }
        });

    }

    /**
     * 请求或刷新当前课程
     */
    void requestCourse(String courseId){
        //TODO 请求或刷新当前课程
        RequestCenter.requestCourse(courseId, new DisposeDataListener() {
            @Override
            public void onSuccess(Object responseObj) {
                BaseCourseShowBean temp= (BaseCourseShowBean) responseObj;
                courseList=temp.data;
                if(temp.ecode==0){
                    adapter=new ClassCourseAdapter(getContext(),courseList, new MyAdapter.AdapterListener<CourseShowBean>(){

                        @Override
                        public void onItemClick(MyAdapter.MyViewHolder holder, CourseShowBean data) {
                            //TODO 课程单元点击
                            Intent intent=new Intent(getContext(), ClassCourseDetailsActivity.class);
                            intent.putExtra(ClassConst.COURSEID,data.courseid);
                            intent.putExtra(ClassConst.TEACHERID,data.teacherid);
                            LogUtils.d("AAA",data.courseid+"----"+data.teacherid);
                            startActivity(intent);

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
//                    Toast.makeText(getContext(),"更新完成",Toast.LENGTH_SHORT).show();
                }else{
                    mRecyclerView.setAdapter(adapter);
                    Toast.makeText(getContext(),"网络炸了哦，请求失败，请检查网络设置",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Object reasonObj) {
                mRecyclerView.setAdapter(adapter);
                Toast.makeText(getContext(),"请求失败，请检查网络设置",Toast.LENGTH_SHORT).show();
            }
        });
    }

    /**
     * 请求加载更多课程（用于下拉刷新）
     */
    void requestMoreCouse(){
        //TODO 请求加载更多课程（用于下拉刷新）
        RequestCenter.requestCourse(courseId, new DisposeDataListener() {
            @Override
            public void onSuccess(Object responseObj) {

                BaseCourseShowBean temp= (BaseCourseShowBean) responseObj;
                List<CourseShowBean> list=temp.data;
                adapter.add(list);
            }
            @Override
            public void onFailure(Object reasonObj) {

            }
        });
    }



    //课程上面拉加载更多
    @Override
    public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
        //TODO 课程下拉加载更多
        requestMoreCouse();
        mSmartRefreshLayout.finishLoadMore();
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

    @Override
    public void onClick(View v) {

        switch(v.getId()){

            case R.id.tv_all:
            case R.id.iv_all:
                // TODO 点击“全部” 侧滑页面出现
                /**
                 * 侧滑栏请求并加载数据
                 */
                if(drawerBeanList.size()==0){
                    requestDrawer();
                }else{
                    showDrawer();
                }

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
                courseId="";
                requestCourse("");
                showDrawer();
                break;
            case R.id.toor:
                startActivity(new Intent(getActivity(), SearchActivity.class));
                break;
            default:
                break;
        }

    }

    @Override
    public boolean onLongClick(View v) {
        return false;
    }
}
