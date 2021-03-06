package com.union.yunzhi.yunzhi.activities.me;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.union.yunzhi.common.app.ActivityM;
import com.union.yunzhi.common.util.LogUtils;
import com.union.yunzhi.common.widget.MyAdapter;
import com.union.yunzhi.factories.moudles.me.BaseCourseModel;
import com.union.yunzhi.factories.moudles.me.CourseModel;
import com.union.yunzhi.factories.moudles.me.MeConstant;
import com.union.yunzhi.factories.moudles.me.UserModel;
import com.union.yunzhi.factories.okhttp.exception.OkHttpException;
import com.union.yunzhi.factories.okhttp.listener.DisposeDataListener;
import com.union.yunzhi.yunzhi.R;
import com.union.yunzhi.yunzhi.adapter.MyCourseAdapter;
import com.union.yunzhi.yunzhi.manager.DialogManager;
import com.union.yunzhi.yunzhi.manager.UserManager;
import com.union.yunzhi.yunzhi.meutils.MeUtils;
import com.union.yunzhi.yunzhi.network.RequestCenter;

import java.util.ArrayList;
import java.util.List;

public class MyCourseActivity extends ActivityM {

    private UserModel mUser;
    private List<CourseModel> mCourseModels  = new ArrayList<>();
    private List<CourseModel> mSeletedData = new ArrayList<>(); // 存放当前下拉栏状态对应的列表数据
    private List<String> mStates = new ArrayList<>(); // 课程的三种状态:进行中、即将开始、已完成
    private Toolbar mToolbar;
    private Spinner mCourseState; // 选择课程的状态
    private TextView mNoCourse;
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
        mUser = MeUtils.getUser();
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mCourseState = (Spinner) findViewById(R.id.spn_my_course_state);
        mNoCourse = (TextView) findViewById(R.id.tv_no_course);
        mRecyclerView = (RecyclerView) findViewById(R.id.rec_my_course);

        getData();

    }

    // 获取网络数据
    private void getData() {
        DialogManager.getInstnce().showProgressDialog(this);
        RequestCenter.requestMyCourse(mUser.getAccount(),
                new DisposeDataListener() {
                    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
                    @Override
                    public void onSuccess(Object responseObj) {
                        DialogManager.getInstnce().dismissProgressDialog();
                        LogUtils.d("getMyCourseData", responseObj.toString());
                        BaseCourseModel baseCourseModel = (BaseCourseModel) responseObj;
                        if (baseCourseModel.ecode == MeConstant.ECODE) {
                            mCourseModels = baseCourseModel.data;
                            initAdapter(mCourseModels);
                        } else {
                            Toast.makeText(MyCourseActivity.this, "" + baseCourseModel.emsg, Toast.LENGTH_SHORT).show();
                        }
                    }

                    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
                    @Override
                    public void onFailure(Object reasonObj) {
                        mCourseModels = locationData();
                        initAdapter(mCourseModels);
                        DialogManager.getInstnce().dismissProgressDialog();
                        OkHttpException okHttpException = (OkHttpException) reasonObj;
//                        if (okHttpException.getEcode() == 1) {
////                            Toast.makeText(MyCourseActivity.this, "" + okHttpException.getEmsg(), Toast.LENGTH_SHORT).show();
//                        } else if (okHttpException.getEcode() == -1){
//                            Toast.makeText(MyCourseActivity.this, "网络连接错误", Toast.LENGTH_SHORT).show();
//                        } else if (okHttpException.getEcode() == -2) {
//                            Toast.makeText(MyCourseActivity.this, "解析错误" , Toast.LENGTH_SHORT).show();
//                        } else if (okHttpException.getEcode() == -3) {
//                            Toast.makeText(MyCourseActivity.this, "未知错误", Toast.LENGTH_SHORT).show();
//                        }

                    }
                });
    }

    // 如果没有网络则填充本地数据
    private List<CourseModel> locationData() {
        List<CourseModel> courseModels = new ArrayList<CourseModel>();
        if (UserManager.getInstance().getUser().getPriority() == MeConstant.PRIORITY_STUDENT) {
            courseModels.add(new CourseModel("0",
                    "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1522902089188&di=c0816128fe3b6131ba3f4cfb8b926b89&imgtype=0&src=http%3A%2F%2Fimg3x1.ddimg.cn%2F33%2F36%2F1431717441-1_u_1.jpg",
                    "临床医学",
                    "武汉科技大学",
                    "医学院",
                    "赵老师",
                    20,
                    5,
                    MeConstant.COURSE_STATE_UNDERWAY,
                    MeConstant.STUDENT_COURSE_VIEW));

            courseModels.add(new CourseModel("0",
                    "https://ss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=3175988450,2945718093&fm=27&gp=0.jpg",
                    "算法与数据结构",
                    "武汉科技大学",
                    "计算机科学与技术",
                    "李老师",
                    15,
                    0,
                    MeConstant.COURSE_STATE_BEGIN,
                    MeConstant.STUDENT_COURSE_VIEW));

            courseModels.add(new CourseModel("0",
                    "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1522900732311&di=936be9da54eea6eaa648186b3955c30d&imgtype=0&src=http%3A%2F%2Fimg3x3.ddimg.cn%2F75%2F30%2F1362333333-1_u_2.jpg",
                    "高等数学",
                    "武汉科技大学",
                    "理学院",
                    "朱李老师",
                    10,
                    10,
                    MeConstant.COURSE_STATE_FINISH,
                    MeConstant.STUDENT_COURSE_VIEW));

            courseModels.add(new CourseModel("0",
                    "https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=3949128263,3188559473&fm=27&gp=0.jpg",
                    "文学研究",
                    "武汉科技大学",
                    "文法学院",
                    "张老师",
                    15,
                    6,
                    MeConstant.COURSE_STATE_UNDERWAY,
                    MeConstant.STUDENT_COURSE_VIEW));
        } else {
            courseModels.add(new CourseModel("0",
                    "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1522902089188&di=c0816128fe3b6131ba3f4cfb8b926b89&imgtype=0&src=http%3A%2F%2Fimg3x1.ddimg.cn%2F33%2F36%2F1431717441-1_u_1.jpg",
                    "临床医学",
                    "武汉科技大学",
                    "医学院",
                    "赵老师",
                    20,
                    5,
                    MeConstant.COURSE_STATE_UNDERWAY,
                    MeConstant.TEACHER_COURSE_VIEW));

            courseModels.add(new CourseModel("0",
                    "https://ss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=3175988450,2945718093&fm=27&gp=0.jpg",
                    "算法与数据结构",
                    "武汉科技大学",
                    "计算机科学与技术",
                    "李老师",
                    15,
                    0,
                    MeConstant.COURSE_STATE_BEGIN,
                    MeConstant.TEACHER_COURSE_VIEW));

            courseModels.add(new CourseModel("0",
                    "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1522900732311&di=936be9da54eea6eaa648186b3955c30d&imgtype=0&src=http%3A%2F%2Fimg3x3.ddimg.cn%2F75%2F30%2F1362333333-1_u_2.jpg",
                    "高等数学",
                    "武汉科技大学",
                    "理学院",
                    "朱李老师",
                    10,
                    10,
                    MeConstant.COURSE_STATE_FINISH,
                    MeConstant.TEACHER_COURSE_VIEW));

            courseModels.add(new CourseModel("0",
                    "https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=3949128263,3188559473&fm=27&gp=0.jpg",
                    "文学研究",
                    "武汉科技大学",
                    "文法学院",
                    "张老师",
                    15,
                    6,
                    MeConstant.COURSE_STATE_UNDERWAY,
                    MeConstant.TEACHER_COURSE_VIEW));
        }

        return courseModels;
    }

    /**
     * 初始化数据和适配器
     */
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void initAdapter(List<CourseModel> data) {
        if (data == null) {
            data = new ArrayList<>();
        }
        MeUtils.showNoMessage(data.size(), mNoCourse, mRecyclerView, "暂无课程");

        // 初始化课程状态的spinner填充数据
        mStates.add("全部");
        mStates.add("进行中");
        mStates.add("即将开始");
        mStates.add("已完成");

        // 初始化spinner适配器
        mSpinnerAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, mStates);
        mSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); // 设置下拉样式
        mCourseState.setAdapter(mSpinnerAdapter);

        // 初始化课程适配器
        mMyCourseAdapter = new MyCourseAdapter(this, data, new MyAdapter.AdapterListener<CourseModel>() {
            @Override
            public void onItemClick(MyAdapter.MyViewHolder holder, CourseModel data) {
                // TODO: 2018/3/10 跳转到相应的课程详情页面
            }

            @Override
            public void onItemLongClick(MyAdapter.MyViewHolder holder, CourseModel data) {
                if (mUser.getPriority() == MeConstant.STUDENT_COURSE_VIEW) { // 长按放弃学习该门课程
                    AlertDialog.Builder builder = new AlertDialog.Builder(getApplicationContext());
                    builder.setTitle("注意：")
                            .setMessage("是否放弃学习该门课程？")
                            .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {

                                }
                            })
                            .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {

                                }
                            })
                            .create();
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
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        mRecyclerView.setAdapter(mMyCourseAdapter);

    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void initData() {
        mToolbar.setTitle("我的课程");

        // 监听选择的课程的状态
//        mCourseState.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
//                mSeletedData.clear(); // 先清空，以备接下来选择的数据做准备
//                if (mCourseModels != null) {
//                    // 如果是选择全部类型的，那么则将网络获取到的全部数据赋给当前选择全部状态的列表
//                    if (i == MeConstant.COURSE_STATE_ALL) { // 第0项，也就是全部
//                        mSeletedData = mCourseModels;
//                    } else { // 否则根据课程的状态选择相应的数据
//                        for (CourseModel courseModel : mCourseModels) {
//                            if (courseModel.getState() == i) { // 课程的状态有三种，1进行时，2即将开始，3已完成
//                                mSeletedData.add(courseModel);
//                            }
//                        }
//                    }
//                } else {
//                    Toast.makeText(MyCourseActivity.this, "快去选择一门课吧", Toast.LENGTH_SHORT).show();
//                }
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> adapterView) {
//
//            }
//        });

    }

    /**
     * 用于更新随着下拉栏的内容改变而改变的列表数据
     * @param courseModels 列表数据
     */
    private void notifyList(List<CourseModel> courseModels) {

        mMyCourseAdapter.add(courseModels);
    }
}
