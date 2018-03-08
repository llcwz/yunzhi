package com.union.yunzhi.yunzhi.activities.me;

import android.content.Context;
import android.content.Intent;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.union.yunzhi.common.app.ActivityM;
import com.union.yunzhi.factories.moudles.me.MeModel;
import com.union.yunzhi.factories.moudles.me.SpinnerStateModel;
import com.union.yunzhi.factories.moudles.me.WorkModel;
import com.union.yunzhi.yunzhi.R;
import com.union.yunzhi.yunzhi.account.AccountSingle;
import com.union.yunzhi.yunzhi.fragment.me.DatePickerDialogFragment;
import com.union.yunzhi.yunzhi.fragment.me.TimePickerDialogFragment;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class NewWorkActivity extends ActivityM implements View.OnClickListener {

    private SpinnerStateModel mCourseState; // 选择课程
    private SpinnerStateModel mTypeState; // 选择任务的类型

    private AccountSingle mAccountSingle;
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
        mAccountSingle = AccountSingle.getInstance(this);
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
        // 测试用的该教师所上的课程
        List<String> courses = new ArrayList<>();
        // 测试用的类型
        List<String> types = new ArrayList<>();

        for (int i = 0; i < 5; i++) {
            courses.add("课程XXXX" + i);
            types.add("类型" + i);
        }
        for (int i = 0; i < 10; i++) {
        }
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
                mWork.setCourse(mCourseState.getStates().get(i));
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
                // 标题
                String title = mTitle.getText().toString();
                // 任务开始时间
                String start = mDateStart.getText().toString() + " " + mTimeStart.getText().toString();
                // 任务截止时间
                String end = mDateEnd.getText().toString() + " " + mTimeEnd.getText().toString();
                // 当前系统时间
                String time = new SimpleDateFormat("yyyy.MM.dd HHs:mm").format(new Date(System.currentTimeMillis()));
                // 此任务的当前状态
                String state = "进行中";
                // 发布者
                String promulgator = mAccountSingle.getPerson().getUsername();

                // 如果任务的标题不为空，时间都选择了的话，则给予操作
                if (!TextUtils.isEmpty(title) && (start + end).indexOf("请") == -1) {
                    // TODO: 2018/3/8 将数据上传，同时推送消息
                    replenishWork(title, start, end, state, promulgator, time);
                    Toast.makeText(this, "发布成功", Toast.LENGTH_SHORT).show();
                    Log.d("NewWork", "" + mWork.toString());
                    finish();
                } else {
                    Toast.makeText(this, "请将信息补充完整", Toast.LENGTH_SHORT).show();
                }
                break;
            default:
        }
    }

    private void replenishWork(String title, String start, String end, String state, String promulgator, String time) {
        // 这里的Id是临时生成的，后面需要和后台商量怎么处理
        mWork.setId(1);
        mWork.setName(title);
        mWork.setStart(start);
        mWork.setEnd(end);
        mWork.setState(state);
        mWork.setPromulgator(promulgator);
        mWork.setTime(time);
    }

}
