package com.union.yunzhi.yunzhi.activities.me;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;

import com.union.yunzhi.common.app.ActivityM;
import com.union.yunzhi.factories.moudles.me.SpinnerStateModel;
import com.union.yunzhi.yunzhi.R;

import java.util.ArrayList;
import java.util.List;

public class NewWorkActivity extends ActivityM {

    private SpinnerStateModel mCourseState; // 选择课程
    private SpinnerStateModel mTypeState; // 选择任务的类型

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
        mCourseState = new SpinnerStateModel(courses);
        mTypeState = new SpinnerStateModel(types);
    }

    @Override
    protected void initData() {
//        mTitle;
//        mCourse;
//        mType;
//        mDateStart;
//        mTimeStart;
//        mDateEnd;
//        mTimeEnd;
//        mSubmit;
    }

    private void showSpinner(Spinner spinner, SpinnerStateModel state) {

    }

}
