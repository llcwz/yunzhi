package com.union.yunzhi.yunzhi.adapter;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.union.yunzhi.common.widget.MyAdapter;
import com.union.yunzhi.factories.moudles.me.CourseModel;
import com.union.yunzhi.factories.moudles.me.MeConstant;
import com.union.yunzhi.factories.moudles.me.MeModel;
import com.union.yunzhi.yunzhi.R;

import java.util.List;

/**
 * Created by CrazyGZ on 2018/3/3.
 */

public class MyCourseAdapter extends MyAdapter<CourseModel> {
    private Context mContext;
    public MyCourseAdapter(Context context, List<CourseModel> data, AdapterListener<CourseModel> listener) {
        super(data, listener);
        mContext = context;
    }
    @Override
    protected int getItemViewType(int position, CourseModel data) {
        if (data != null) {
            Log.d( "getItemViewType", "" + data.getViewType());
        } else {
            Log.d( "getItemViewType", "null");
        }
        switch (data.getViewType()) {
            case MeConstant.STUDENT_COURSE_VIEW:
                return R.layout.item_me_my_course_student;
            case MeConstant.TEACHER_COURSE_VIEW:
                return R.layout.item_me_my_course_teacher;
            default:
                return 0;
        }
    }

    @Override
    protected MyViewHolder<CourseModel> onCreateViewHolder(View root, int viewType) {
        if (viewType == MeConstant.STUDENT_COURSE_VIEW) {
            return new StudentCourseViewHolder(root);
        } else {
            return new TeacherCourseViewHolder(root);
        }
    }

    public class StudentCourseViewHolder extends MyViewHolder<CourseModel> {
        private TextView mTitle;
        private ImageView mIcon;
        private TextView mSchool;
        private TextView mTeacher;
        private TextView mPractise;
        private TextView mSchedule;
        private ProgressBar mProgress;

        public StudentCourseViewHolder(View itemView) {
            super(itemView);
            mTitle = (TextView) itemView.findViewById(R.id.tv_course_title);
            mIcon = (ImageView) itemView.findViewById(R.id.iv_course_icon);
            mSchool = (TextView) itemView.findViewById(R.id.tv_school_of_course);
            mTeacher = (TextView) itemView.findViewById(R.id.tv_teacher_of_course);
            mPractise = (TextView) itemView.findViewById(R.id.tv_course_practise);
            mSchedule = (TextView) itemView.findViewById(R.id.tv_course_schedule);
            mProgress = (ProgressBar) itemView.findViewById(R.id.proBar_course_progress);
        }

        @Override
        protected void onBind(CourseModel data, int position) {

            mTitle.setText(data.getName());
            Glide.with(mContext).load(data.getIcon()).into(mIcon);
            mSchool.setText(data.getSchool());
            mTeacher.setText(data.getTeacher());
            mPractise.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.d("MyCourseClick", "onClick:  ");
                    Toast.makeText(mContext, "练习", Toast.LENGTH_SHORT).show();
                }
            });
            mSchedule.setText("进行至" + data.getProgress() + "周，总共" + data.getSchedule() + "周");
            mProgress.setMax(data.getSchedule());
            mProgress.setProgress(data.getProgress());

        }

    }

    public class TeacherCourseViewHolder extends MyViewHolder<CourseModel> {
        private TextView mTitle;
        private ImageView mIcon;
        private TextView mSchool;
        private TextView mCollege;
        private TextView mSchedule;
        private ProgressBar mProgress;

        public TeacherCourseViewHolder(View itemView) {
            super(itemView);
            mTitle = (TextView) itemView.findViewById(R.id.tv_course_title);
            mIcon = (ImageView) itemView.findViewById(R.id.iv_course_icon);
            mSchool = (TextView) itemView.findViewById(R.id.tv_school_of_course);
            mCollege = (TextView) itemView.findViewById(R.id.tv_college_of_course);
            mSchedule = (TextView) itemView.findViewById(R.id.tv_course_schedule);
            mProgress = (ProgressBar) itemView.findViewById(R.id.proBar_course_progress);
        }

        @Override
        protected void onBind(CourseModel data, int position) {
            mTitle.setText(data.getName());
            Glide.with(mContext).load(data.getIcon()).into(mIcon);
            mSchool.setText(data.getSchool());
//            mCollege.setText(data.getCollege());
            mSchedule.setText("进行至" + data.getProgress() + "周，总共" + data.getSchedule() + "周");
            mProgress.setMax(data.getSchedule());
            mProgress.setProgress(data.getProgress());
        }
    }
}
