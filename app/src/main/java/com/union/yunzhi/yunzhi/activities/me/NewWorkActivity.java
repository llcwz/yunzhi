package com.union.yunzhi.yunzhi.activities.me;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.union.yunzhi.common.app.ActivityM;
import com.union.yunzhi.common.util.LogUtils;
import com.union.yunzhi.factories.moudles.me.BaseMeModel;
import com.union.yunzhi.factories.moudles.me.CourseModel;
import com.union.yunzhi.factories.moudles.me.MeConstant;
import com.union.yunzhi.factories.moudles.me.MeModel;
import com.union.yunzhi.factories.moudles.me.SpinnerStateModel;
import com.union.yunzhi.factories.moudles.me.WorkModel;
import com.union.yunzhi.factories.okhttp.listener.DisposeDataListener;
import com.union.yunzhi.yunzhi.R;
import com.union.yunzhi.yunzhi.fragment.me.DatePickerDialogFragment;
import com.union.yunzhi.yunzhi.fragment.me.TimePickerDialogFragment;
import com.union.yunzhi.yunzhi.manager.DialogManager;
import com.union.yunzhi.yunzhi.manager.UserManager;
import com.union.yunzhi.yunzhi.network.RequestCenter;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class NewWorkActivity extends ActivityM implements View.OnClickListener {

    private SpinnerStateModel mCourseState; // 选择课程
    private SpinnerStateModel mTypeState; // 选择任务的类型

    private UserManager mUserManager;
    private MeModel mMeModel;
    private WorkModel mWork = new WorkModel(); // 需要上传的数据
    private EditText mTitle;
    private Spinner mCourse;
    private Spinner mType;
    private TextView mDateStart;
    private TextView mTimeStart;
    private TextView mDateEnd;
    private TextView mTimeEnd;
    private Button mSubmit;

    public static void newInstance(Context context) {
        Intent intent = new Intent(context, NewWorkActivity.class);
        context.startActivity(intent);
    }
    @Override
    protected int getContentLayoutId() {
        return R.layout.activity_new_work;
    }

    @Override
    protected void initWidget() {
        mUserManager = UserManager.getInstance();
        mMeModel = mUserManager.getUser().data;
        // 加载spinner状态栏的填充数据
        data();
        mTitle = (EditText) findViewById(R.id.et_work_title);
        mCourse = (Spinner) findViewById(R.id.spn_work_course);
        mType = (Spinner) findViewById(R.id.spn_work_type);
        mDateStart = (TextView) findViewById(R.id.tv_work_date_start);
        mTimeStart = (TextView) findViewById(R.id.tv_work_time_start);
        mDateEnd = (TextView) findViewById(R.id.tv_work_date_end);
        mTimeEnd = (TextView) findViewById(R.id.tv_work_time_end);
        mSubmit = (Button) findViewById(R.id.btn_submit);
    }

    private void data() {
        // 该教师所上的课程
        List<String> courses = new ArrayList<>();
        if (mMeModel.getCourseModels() != null) { // 如果老师上有课
            for (CourseModel courseModel : mMeModel.getCourseModels()) {
                courses.add(courseModel.getName());
            }
        }

        // 可以选择的任务类型，有三种
        List<String> types = new ArrayList<>();
        types.add("单元测试");
        types.add("期中测试");
        types.add("期末测试");

        mCourseState = new SpinnerStateModel(courses);
        mTypeState = new SpinnerStateModel(types);
    }

    @Override
    protected void initData() {
        showSpinner(mCourse, mCourseState); // 显示课程的下拉栏
        showSpinner(mType, mTypeState); // 显示类型下拉栏
        mCourse.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if ( mCourseState.getStates().size() != 0) { // 该老师上有课程
                    mWork.setCourse(mCourseState.getStates().get(i));
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        mType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                mWork.setType(mTypeState.getStates().get(i));
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        mDateStart.setOnClickListener(this);
        mTimeStart.setOnClickListener(this);
        mDateEnd.setOnClickListener(this);
        mTimeEnd.setOnClickListener(this);
        mSubmit.setOnClickListener(this);
    }

    /**
     * 用于显示下拉栏
     * @param spinner
     * @param state 下拉栏内容
     */
    private void showSpinner(Spinner spinner, SpinnerStateModel state) {
        // 实例化适配器
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, state.getStates());
        //设置下拉样式
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }

    @Override
    public void onClick(View view) {
        DatePickerDialogFragment datePickerDialogFragment = DatePickerDialogFragment.newInstance();
        TimePickerDialogFragment timePickerDialogFragment = TimePickerDialogFragment.newInstance();
        switch (view.getId()) {
            case R.id.tv_work_date_start:
                datePickerDialogFragment.show(getSupportFragmentManager(), DatePickerDialogFragment.TAG);
                // 获取返回的日期
                datePickerDialogFragment.setDatePickerListener(new DatePickerDialogFragment.DatePickerListener() {
                    @Override
                    public void getDate(String date) {
                        mDateStart.setText(date);
                    }
                });
                break;
            case R.id.tv_work_time_start:
                timePickerDialogFragment.show(getSupportFragmentManager(), TimePickerDialogFragment.TAG);
                timePickerDialogFragment.setTimePickerListener(new TimePickerDialogFragment.TimePickerListener() {
                    @Override
                    public void getTime(String time) {
                        mTimeStart.setText(time);
                    }
                });
                break;
            case R.id.tv_work_date_end:
                datePickerDialogFragment.show(getSupportFragmentManager(), DatePickerDialogFragment.TAG);
                // 获取返回的日期
                datePickerDialogFragment.setDatePickerListener(new DatePickerDialogFragment.DatePickerListener() {
                    @Override
                    public void getDate(String date) {
                        mDateEnd.setText(date);
                    }
                });
                break;
            case R.id.tv_work_time_end:
                timePickerDialogFragment.show(getSupportFragmentManager(), TimePickerDialogFragment.TAG);
                timePickerDialogFragment.setTimePickerListener(new TimePickerDialogFragment.TimePickerListener() {
                    @Override
                    public void getTime(String time) {
                        mTimeEnd.setText(time);
                    }
                });
                break;
            case R.id.btn_submit:
                if (mCourseState.getStates().size() != 0) { // 该名老师上有课程，允许发布相关课程的任务
                    submit();
                } else { // 提示没有他负责的课程，无法发布相关任务
                    Toast.makeText(this, "你还未上有课程", Toast.LENGTH_SHORT).show();
                }
                break;
            default:
        }
    }

    /**
     * 提交任务
     */
    private void submit() {
        // 标题
        String title = mTitle.getText().toString();
        // 任务开始时间
        String start = mDateStart.getText().toString() + " " + mTimeStart.getText().toString();
        // 任务截止时间
        String end = mDateEnd.getText().toString() + " " + mTimeEnd.getText().toString();
        // 当前系统时间
        String time = new SimpleDateFormat("yyyy.MM.dd HH:mm").format(new Date(System.currentTimeMillis()));
        // 利用用户的id和当前时间生成生成任务的唯一id
        String id = mUserManager.getPerson().getAccount() + time.replace(".","").replace(" ","").replace(":","");
        // 此任务的当前状态
        String state = "进行中";
        // 发布者
        String promulgator = mUserManager.getPerson().getStudentname();

        // 如果任务的标题不为空，时间都选择了的话，则给予操作
        if (!TextUtils.isEmpty(title) && (start + end).indexOf("请") == -1) {
            // TODO: 2018/3/8 将数据上传，同时推送消息
            loadWork(id, title, mWork.getCourse(), mWork.getType(), start, end, state, promulgator, time, MeConstant.TEACHER_WORK_VIEW);
            Toast.makeText(this, "发布成功", Toast.LENGTH_SHORT).show();
            Log.d("NewWork", "" + mWork.toString());
            finish();
        } else {
            Toast.makeText(this, "请将信息补充完整", Toast.LENGTH_SHORT).show();
        }
    }


    /**
     * 用户请求发布新任务
     * @param id 任务的id
     * @param name 任务的标题
     * @param course 任务所属的课程
     * @param type 任务的类型
     * @param start 任务开始时间
     * @param end 任务结束时间
     * @param state 任务的状态
     * @param promulgator 任务发布者
     * @param time 任务的时间
     * @param viewType 任务所属的view类型
     */
    private void loadWork(String id, String name, String course, String type, String start, String end, String state, String promulgator, String time, int viewType) {
        DialogManager.getInstnce().showProgressDialog(getApplicationContext()); // 加载进度框
        RequestCenter.requestAddWork(id, name, course, type, start, end, state, promulgator, time, viewType, new DisposeDataListener() {
            @Override
            public void onSuccess(Object responseObj) {
                LogUtils.d("addWork", responseObj.toString());
                WorkModel baseMeModel = (WorkModel) responseObj;
                if (baseMeModel != null) {
                    Toast.makeText(NewWorkActivity.this, "发布成功", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(NewWorkActivity.this, "发布失败", Toast.LENGTH_SHORT).show();
                }
                DialogManager.getInstnce().dismissProgressDialog();
            }

            @Override
            public void onFailure(Object reasonObj) {
                DialogManager.getInstnce().dismissProgressDialog();
                Toast.makeText(NewWorkActivity.this, "网络连接失败", Toast.LENGTH_SHORT).show();
            }
        });
    }

}
