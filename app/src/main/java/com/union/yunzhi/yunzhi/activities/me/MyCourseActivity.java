package com.union.yunzhi.yunzhi.activities.me;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.union.yunzhi.common.app.ActivityM;
import com.union.yunzhi.common.widget.MyAdapter;
import com.union.yunzhi.factories.moudles.me.CourseModel;
import com.union.yunzhi.factories.moudles.me.MeConstant;
import com.union.yunzhi.factories.moudles.me.MeModel;
import com.union.yunzhi.yunzhi.R;
import com.union.yunzhi.yunzhi.account.AccountSingle;
import com.union.yunzhi.yunzhi.adapter.MyCourseAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MyCourseActivity extends ActivityM {

    private AccountSingle mAccountSingle; // 用户单例
    private List<String> mStates = new ArrayList<>(); // 课程的三种状态:进行中、即将开始、已完成
    private List<MeModel> mStudentCourses = new ArrayList<>();
    private List<MeModel> mTeacherCourses = new ArrayList<>();

    private Spinner mCourseState; // 选择课程的状态
    private RecyclerView mRecyclerView;
    private ArrayAdapter<String> mSpinnerAdapter;
    private MyCourseAdapter mMyCourseAdapter;

    public static void newInstance(Context context) {
        Intent intent = new Intent(context, MyCourseActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected int getContentLayoutId() {
        return R.layout.activity_my_course;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void initWidget() {
        mAccountSingle = AccountSingle.getInstance(this);
        data();
        mCourseState = (Spinner) findViewById(R.id.spn_my_course_state);
        mRecyclerView = (RecyclerView) findViewById(R.id.rec_my_course);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void data() {
        // 初始化课程状态的spinner填充数据
        mStates.add("进行中");
        mStates.add("即将开始");
        mStates.add("已完成");

        // 初始化学生课程列表数据
        for (int i = 0; i < 10; i++) {
            CourseModel courseModel = new CourseModel(i,
                    getDrawable(R.drawable.dragon_cat),
                    "操作系统" + i,
                    "武汉科技大学",
                    "计算机科学与技术",
                    "黄革新",
                    i + 4,
                    new Random().nextInt(4),
                    new Random().nextInt(2)
            );
            mStudentCourses.add(new MeModel(courseModel ,MeConstant.STUDENT_COURSE_VIEW));
        }

        // 初始化教师的课程列表数据
        for (int i = 0; i < 10; i++) {
            CourseModel courseModel = new CourseModel(i,
                    getDrawable(R.drawable.dragon_cat),
                    "操作系统" + i,
                    "武汉科技大学",
                    "计算机科学与技术",
                    "黄革新",
                    i + 4,
                    new Random().nextInt(4),
                    new Random().nextInt(2)
            );
            mTeacherCourses.add(new MeModel(courseModel ,MeConstant.TEACHER_COURSE_VIEW));
        }


        mSpinnerAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, mStates);
        mSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); // 设置下拉样式

        // 初始化适配器
        if (mAccountSingle.getPerson().getAccess() == MeConstant.ACCESS_STUDENT) { // 如果是学生，则加载学生的课程数据
            mMyCourseAdapter = new MyCourseAdapter(this, mStudentCourses, new MyAdapter.AdapterListener<MeModel>() {
                @Override
                public void onItemClick(MyAdapter.MyViewHolder holder, MeModel data) {
                    // TODO: 2018/3/4 跳转到课程详情
                    Toast.makeText(MyCourseActivity.this, "see the course's details", Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onItemLongClick(MyAdapter.MyViewHolder holder, MeModel data) {

                    if (data.getViewType() == MeConstant.STUDENT_COURSE_VIEW) { // 如果是学生，长按则放弃学习该门课程
                        // TODO: 2018/3/4 放弃学习该课程
                    }
                }

                @Override
                public Boolean setAddActionContinue() {
                    return null;
                }

                @Override
                public void updataUI(Object object) {

                }
            });
        } else { // 如果是教师，则加载教师的课程数据
            mMyCourseAdapter = new MyCourseAdapter(this, mTeacherCourses, new MyAdapter.AdapterListener() {
                @Override
                public void onItemClick(MyAdapter.MyViewHolder holder, Object data) {

                }

                @Override
                public void onItemLongClick(MyAdapter.MyViewHolder holder, Object data) {

                }

                @Override
                public Boolean setAddActionContinue() {
                    return null;
                }

                @Override
                public void updataUI(Object object) {

                }
            });
        }
    }

    @Override
    protected void initData() {
        mCourseState.setAdapter(mSpinnerAdapter);
        // 监听选择的课程的状态
        mCourseState.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i) {
                    // TODO: 2018/3/4 监听课程状态下拉栏的事件
                    case MeConstant.COURSE_STATE_UNDERWAY: // 进行时
                        break;
                    case MeConstant.COURSE_STATE_BEGIN: // 即将开始
                        break;
                    case MeConstant.COURSE_STATE_FINISH: // 已完成
                        break;
                    default:
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        mRecyclerView.setAdapter(mMyCourseAdapter);
    }
}
