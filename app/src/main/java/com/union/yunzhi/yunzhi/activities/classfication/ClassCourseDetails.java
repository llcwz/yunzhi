package com.union.yunzhi.yunzhi.activities.classfication;

import android.content.Intent;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.union.yunzhi.common.app.ActivityM;
import com.union.yunzhi.common.helper.HiddenAnimUtils;
import com.union.yunzhi.common.widget.MyAdapter;
import com.union.yunzhi.common.widget.MyScrollView;
import com.union.yunzhi.factories.moudles.classfication.CustomLinearLayoutManager;
import com.union.yunzhi.factories.moudles.classfication.beans.TeacherBean;
import com.union.yunzhi.yunzhi.R;
import com.union.yunzhi.yunzhi.adapter.MoreTeacherAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cjw on 2018/2/25 0025.
 */

public class ClassCourseDetails extends ActivityM implements View.OnClickListener {


    //课程简介视频
    private MyScrollView mMyScrollView;
    private FrameLayout hiddenView;
    private ConstraintLayout showView;

    //课程简介
    private TextView mCollapsibleTextView;
    private StringBuilder mLongText;

    //老师介绍
    private TextView mCollapsibleTextView1;

    //相关老师部分
    private RecyclerView mRecyclerView;
    private List<TeacherBean> mList;

    //进入课程
    private Button mButton;


    @Override
    protected int getContentLayoutId() {
        return R.layout.class_course_details;
    }

    @Override
    protected boolean initArgs(Bundle bundle) {

        mLongText=new StringBuilder();
        mLongText.append("本课程主要讲授了计算机组成原理,\n" +
                "围绕着数字电子计算机展开本课程，组成是指计算机硬件系统的逻辑实现一般原理，不是物理实现，就是用数字电路数字逻辑的知识，数字电子元器件来实现。\n" +
                "讲授内容三部分：\n" +
                "1.基本部件的结构和组织方式  用数字逻辑 数字电子的知识对计算机基本部件进行逻辑实现。\n" +
                "2.基本运算的操作原理 ：基本功能就是加减乘除 如何用数字电路来实现运算的硬件电路。\n" +
                "3.基本部件和单元的设计思想 以及如何连接这些基本部件。\n" +
                "计算机组成的一般原理，不以具体机型为依托，采用自顶向下，层层细化。\n" +
                "首先给出硬件系统的概貌，然后层层细化，完成最底层部件的逻辑实现。");
        mList=new ArrayList<>();
        TeacherBean teacherBean=new TeacherBean();

        teacherBean.portraitUrl="portraitUrl"; teacherBean.bigTitleFather="张三";
        teacherBean.smallTitleFather="武汉科技大学副教授"; teacherBean.good="1314";
        teacherBean.bad="13";
        teacherBean.mLongText="怀特森先生教我们六年级的科学课。" +
                "第一节课上，他问我们：“谁知道一种叫做凯蒂旺普斯的动物？”" +
                "同学们面面相觑（qù），就连生物比赛得过奖的比利也都惊奇地瞪大了眼睛。\n" +
                "　　“噢，没有人知道。”怀特森老师笑了笑，“那是一种夜行兽，在冰川期无法适应环境的变化而绝迹了。”"
                +"说着，他从讲桌里拿出一件动物头骨，向我们解释起这种动物的特征来。" +
                "讲完，他把头骨交给前排的同学，让大家轮流观察一下。我们饶有兴趣地传看，记笔记，有的同学还画了图。" +
                "我心中暗想，这回我遇到一位博学的老师了。";
        teacherBean.imgUrl1="imgUrl1"; teacherBean.imgUrl2="imgUrl1"; teacherBean.imgUrl3="imgUrl1";
        teacherBean.name1="操作系统"; teacherBean.name2="算法分析"; teacherBean.name3="数据结构";
        mList.add(teacherBean); mList.add(teacherBean); mList.add(teacherBean); mList.add(teacherBean);

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
        showView.setVisibility(View.GONE);
        //mMyScrollView的滑动处理
        mMyScrollView.setOnScollChangedListener(new MyScrollView.OnScollChangedListener() {
            @Override
            public void onScrollViewChanged(MyScrollView scrollView, int x, int y, int oldx, int oldy) {
                /**
                 * 注意此处
                 * HiddenAnimUtils.newInstance(getBaseContext(),48).openAnimate(showView);
                 * 其第二个参数“48”为View的高度，单位为dp
                 * 但是不能通过View.getHeight()获得，view 对象动态渲染高度会有变化
                 */

                //TODO 有待优化
                //手指上滑
                if((y>=(hiddenView.getHeight()))&&y-oldy>8){
                    Log.d("KKK","111"+scrollView.getScrollY());
                    if(hiddenView.getVisibility()==View.VISIBLE){
                        Log.d("KKK","11111111");
                        //HiddenAnimUtils.newInstance(getBaseContext(),224).closeAnimate(hiddenView);
                        hiddenView.setVisibility(View.GONE);
                        HiddenAnimUtils.newInstance(getBaseContext(),48).openAnimate(showView);
                    }
                } else if(scrollView.getScrollY()==0){//下滑到顶
                    Log.d("KKK","222");
                    if(hiddenView.getVisibility()==View.GONE){
                        HiddenAnimUtils.newInstance(getBaseContext(),224).openAnimate(hiddenView);
                      }
                    //HiddenAnimUtils.newInstance(getBaseContext(),48).closeAnimate(showView);
                    showView.setVisibility(View.GONE);
                }else{
                    Log.d("KKK","333");
                }
            }
        });


        /**
         * 课程简介部分
         */
        mCollapsibleTextView= (TextView) findViewById(R.id.tv_jianjie_course);
        mCollapsibleTextView.setText(mLongText.toString());
        //TODO 课程简介部分等待添加其他的元素


        /**
         * 老师简介部分
         */
        mCollapsibleTextView1= (TextView) findViewById(R.id.tv_jianjie_teacher);
        mCollapsibleTextView1.setText(mLongText.toString());
        //TODO 老师简介部分等待添加其他的元素


        /**
         * 相关老师部分
         */
        mRecyclerView= (RecyclerView) findViewById(R.id.rec_more_teacher);
        MoreTeacherAdapter adapter=new MoreTeacherAdapter(getBaseContext(), mList, new MyAdapter.AdapterListener() {
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

        //TODO 此处设置recycleView禁止滑动
        CustomLinearLayoutManager linearLayoutManager=new CustomLinearLayoutManager(getBaseContext());
        linearLayoutManager.setScrollEnabled(false);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mRecyclerView.setAdapter(adapter);

        /**
         * 进入课程
         */
        mButton= (Button) findViewById(R.id.btn_enter_course);
        mButton.setOnClickListener(this);
    }


    @Override
    protected void initData() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){

            case R.id.btn_enter_course:
                Intent intent=new Intent(ClassCourseDetails.this,ClassCourseFile.class);
                startActivity(intent);
                break;
            default:
                break;
        }
    }
}
