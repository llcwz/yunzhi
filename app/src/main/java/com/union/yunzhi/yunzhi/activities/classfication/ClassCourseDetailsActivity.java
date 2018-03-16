package com.union.yunzhi.yunzhi.activities.classfication;

import android.content.Intent;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.makeramen.roundedimageview.RoundedImageView;
import com.union.yunzhi.common.app.ActivityM;
import com.union.yunzhi.common.helper.HiddenAnimUtils;
import com.union.yunzhi.common.util.LogUtils;
import com.union.yunzhi.common.util.Utils;
import com.union.yunzhi.common.widget.MyAdapter;
import com.union.yunzhi.common.widget.MyScrollView;
import com.union.yunzhi.factories.moudles.classfication.ClassConst;
import com.union.yunzhi.factories.moudles.classfication.CustomLinearLayoutManager;
import com.union.yunzhi.factories.moudles.classfication.beans.details.BaseDetailsBean;
import com.union.yunzhi.factories.moudles.classfication.beans.details.TeacherBean;
import com.union.yunzhi.factories.moudles.classfication.beans.video.VideoBean;
import com.union.yunzhi.factories.moudles.communication.BaseCommentModel;
import com.union.yunzhi.factories.okhttp.listener.DisposeDataListener;
import com.union.yunzhi.yunzhi.R;
import com.union.yunzhi.yunzhi.adapter.MoreTeacherAdapter;
import com.union.yunzhi.yunzhi.network.RequestCenter;
import com.union.yunzhi.yunzhi.utils.Util;
import com.union.yunzhi.yunzhi.utils.VideoUtils;

import java.util.List;

/**
 * Created by cjw on 2018/2/25 0025.
 */

public class ClassCourseDetailsActivity extends ActivityM implements View.OnClickListener {


    //课程简介视频
    private MyScrollView mMyScrollView;
    private FrameLayout hiddenView;
    private LinearLayout mLinearLayout;
    private ConstraintLayout showView;
    private RoundedImageView back,share,play;
    private TextView mLikeCount,courseName,courseTeacher;
    private ImageButton mLike;
    private ImageView videoCover;
    private String videocoverurl,videourl,coursename,videoid;
    private VideoBean videoBean;
    private BaseCommentModel commentModel;


    //课程简介
    private TextView mCollapsibleTextView;
    private StringBuilder mLongText;

    //老师介绍
    private TextView mCollapsibleTextView1;
    private ImageView img1,img2,img3;
    private TextView tv1,tv2,tv3;

    //相关老师部分
    private RecyclerView mRecyclerView;
    private List<TeacherBean> mList;

    //二维码
    private ImageView Qrcode;

    //进入课程
    private Button mButton;

    private String courseid,teacherid;


    @Override
    protected int getContentLayoutId() {
        return R.layout.class_course_details;
    }

    @Override
    protected boolean initArgs(Bundle bundle) {

        courseid= (String) bundle.get(ClassConst.COURSEID);
        teacherid=(String) bundle.get(ClassConst.TEACHERID);
        return super.initArgs(bundle);
    }

    @Override
    protected void initWidget() {

        /**
         * 视频简介部分
         */
        mMyScrollView= (MyScrollView) findViewById(R.id.scroll_details);
        hiddenView= (FrameLayout) findViewById(R.id.lv_details_video);
        showView= (ConstraintLayout) findViewById(R.id.lv_details_head);
        mLikeCount= (TextView) findViewById(R.id.tv_upCount);
        mLike= (ImageButton) findViewById(R.id.img_like);
        showView.setVisibility(View.GONE);
        videoCover= (ImageView) findViewById(R.id.iv_course_details_video);
        courseName= (TextView) findViewById(R.id.tv_big_title);
        courseTeacher= (TextView) findViewById(R.id.tv_small_title);
        play= (RoundedImageView) findViewById(R.id.rImagV_play);
        back= (RoundedImageView) findViewById(R.id.rImagV_back);
        share= (RoundedImageView) findViewById(R.id.rImgV_share);
        back.setOnClickListener(this);
        share.setOnClickListener(this);
        play.setOnClickListener(this);


        /**
         * 课程简介部分
         */
        mCollapsibleTextView= (TextView) findViewById(R.id.tv_jianjie_course);
        //TODO 课程简介部分等待添加其他的元素


        /**
         * 老师简介部分
         */
        mCollapsibleTextView1= (TextView) findViewById(R.id.tv_jianjie_teacher);
        mLinearLayout= (LinearLayout) findViewById(R.id.layout_teacher);
        img1= (ImageView)mLinearLayout.findViewById(R.id.iv_course_show1);
        img2= (ImageView)mLinearLayout.findViewById(R.id.iv_course_show2);
        img3= (ImageView)mLinearLayout.findViewById(R.id.iv_course_show3);
        tv1= (TextView) findViewById(R.id.tv_course_show1);
        tv2= (TextView) findViewById(R.id.tv_course_show2);
        tv3= (TextView) findViewById(R.id.tv_course_show3);
        //TODO 老师简介部分等待添加其他的元素


        /**
         * 相关老师部分
         */
        mRecyclerView= (RecyclerView) findViewById(R.id.rec_more_teacher);
        /**
         * 进入课程
         */
        mButton= (Button) findViewById(R.id.btn_enter_course);
        mButton.setOnClickListener(this);

        /**
         * 二维码
         */
        Qrcode= (ImageView) findViewById(R.id.img_qrcode);
    }


    @Override
    protected void initData() {
        //mMyScrollView的滑动处理
        /**
         * 注意此处
         * HiddenAnimUtils.newInstance(getBaseContext(),48).openAnimate(showView);
         * 其第二个参数“48”为View的高度，单位为dp
         * 但是不能通过View.getHeight()获得，view 对象动态渲染高度会有变化
         */
        mMyScrollView.setOnScollChangedListener(new MyScrollView.OnScollChangedListener() {
            @Override
            public void onScrollViewChanged(MyScrollView scrollView, int x, int y, int oldx, int oldy) {

                //LogUtils.d("KKK","滚动监听");
                //手指上滑
                if((y>=(hiddenView.getHeight()-200))&&y-oldy>8){
                    if(hiddenView.getVisibility()==View.VISIBLE){
                        LogUtils.d("KKK","执行View隐藏");
                        //HiddenAnimUtils.newInstance(getBaseContext(),224).closeAnimate(hiddenView);
                        hiddenView.setVisibility(View.GONE);
                        HiddenAnimUtils.newInstance(getBaseContext(),48).openAnimate(showView);
                    }
                } else if(scrollView.getScrollY()==0){//下滑到顶
                    if(hiddenView.getVisibility()==View.GONE){
                        LogUtils.d("KKK","执行View显现");
                        HiddenAnimUtils.newInstance(getBaseContext(),224).openAnimate(hiddenView);
                    }
                    //HiddenAnimUtils.newInstance(getBaseContext(),48).closeAnimate(showView);
                    showView.setVisibility(View.GONE);
                }else{

                }
            }
        });

        //请求并适配页面数据
        requestAllData(courseid,teacherid);

        //生成二维码
        Qrcode.setImageBitmap(Util.createQRCode(Utils.dip2px(this,200),
                Utils.dip2px(this,200),
                courseid+"&"+teacherid));
    }

    void requestAllData(String courseid, String teacherid){

        RequestCenter.requestCourseDeatails(courseid, teacherid, new DisposeDataListener() {
            @Override
            public void onSuccess(Object responseObj) {

                Toast.makeText(getBaseContext(),"onSuccess()",Toast.LENGTH_SHORT).show();


                BaseDetailsBean bean= (BaseDetailsBean) responseObj;
                mList=bean.data.teacher;
                videocoverurl=bean.data.introimgurl;
                videourl=bean.data.introvideourl;
                coursename=bean.data.coursename;
                videoid=bean.data.videoid;

                commentModel=new BaseCommentModel();
                videoBean=new VideoBean();
                videoBean.coverurl=videocoverurl;
                videoBean.videourl=videourl;
                videoBean.videoid=videoid;
                videoBean.videotitle=coursename;



                String courseteacher=bean.data.teachername;
                String courseinfo=bean.data.courseinfo;
                String teacherinfo=bean.data.teacherinfo;
                List<String> cover=bean.data.coursecover;

                mCollapsibleTextView.setText(courseinfo);
                mCollapsibleTextView1.setText(teacherinfo);
                courseName.setText(coursename);
                courseTeacher.setText(courseteacher);

                Glide.with(getBaseContext()).load(videocoverurl).placeholder(R.mipmap.ic_launcher).into(videoCover);
                if(cover.size()==0){
                    img1.setVisibility(View.GONE);
                    img2.setVisibility(View.GONE);
                    img3.setVisibility(View.GONE);
                    tv1.setVisibility(View.GONE);
                    tv2.setVisibility(View.GONE);
                    tv3.setVisibility(View.GONE);
                }
                if(cover.size()==1){
                    img2.setVisibility(View.GONE);
                    img3.setVisibility(View.GONE);
                    tv2.setVisibility(View.GONE);
                    tv3.setVisibility(View.GONE);
                    Glide.with(getBaseContext()).load(cover.get(0)).placeholder(R.mipmap.ic_launcher).into(img1);
                }
                if(cover.size()==2){
                    img3.setVisibility(View.GONE);
                    tv3.setVisibility(View.GONE);
                    Glide.with(getBaseContext()).load(cover.get(0)).placeholder(R.mipmap.ic_launcher).into(img1);
                    Glide.with(getBaseContext()).load(cover.get(1)).placeholder(R.mipmap.ic_launcher).into(img2);
                }
                if(cover.size()==3){
                    Glide.with(getBaseContext()).load(cover.get(0)).placeholder(R.mipmap.ic_launcher).into(img1);
                    Glide.with(getBaseContext()).load(cover.get(1)).placeholder(R.mipmap.ic_launcher).into(img2);
                    Glide.with(getBaseContext()).load(cover.get(2)).placeholder(R.mipmap.ic_launcher).into(img3);
                }

                //TODO 此处设置recycleView禁止滑动
                CustomLinearLayoutManager linearLayoutManager=new CustomLinearLayoutManager(getBaseContext());
                linearLayoutManager.setScrollEnabled(false);
                mRecyclerView.setLayoutManager(linearLayoutManager);
                MoreTeacherAdapter adapter=new MoreTeacherAdapter(getBaseContext(), mList, new MyAdapter.AdapterListener<TeacherBean>() {
                    @Override
                    public void onItemClick(MyAdapter.MyViewHolder holder, TeacherBean data) {

                    }

                    @Override
                    public void onItemLongClick(MyAdapter.MyViewHolder holder, TeacherBean data) {

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

            }
            @Override
            public void onFailure(Object reasonObj) {
                Toast.makeText(getBaseContext(),"请求失败xxx",Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){

            case R.id.btn_enter_course:
                Intent intent=new Intent(ClassCourseDetailsActivity.this,ClassCourseFileActivity.class);
                startActivity(intent);
                break;
            case R.id.rImagV_back:
                finish();
                break;
            case R.id.rImagV_share:
                break;
            case R.id.rImagV_play:
                com.union.yunzhi.factories.utils.LogUtils.d("URL","aASAd  "+videourl+"---"+videocoverurl);
                VideoUtils.newInstance(getBaseContext(),videoBean,commentModel).startVideo();
                break;
            default:
                break;
        }
    }
}
