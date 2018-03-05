package com.union.yunzhi.yunzhi.adapter;

import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.union.yunzhi.common.widget.MyAdapter;
import com.union.yunzhi.factories.moudles.me.MeConstant;
import com.union.yunzhi.factories.moudles.me.MeModel;
import com.union.yunzhi.yunzhi.R;

import java.util.List;

import static android.content.ContentValues.TAG;

/**
 * Created by CrazyGZ on 2018/3/3.
 */

public class MyCourseAdapter extends MyAdapter<MeModel> {

    public MyCourseAdapter(List<MeModel> datas, AdapterListener listener) {
        super(datas, listener);
    }
    @Override
    protected int getItemViewType(int position, MeModel data) {
        if (data != null) {
            Log.d( "getItemViewType", "" + data.getViewType());
        } else {
            Log.d( "getItemViewType", "null");
        }
        if (data.getViewType() == MeConstant.STUDENT_COURSE_VIEW) {
            return R.layout.item_me_my_course_student;
        } else {
            return R.layout.item_me_my_course_teacher;
        }
    }

    @Override
    protected MyViewHolder<MeModel> onCreateViewHolder(View root, int viewType) {
        if (viewType == MeConstant.STUDENT_COURSE_VIEW) {
            return new StudentCourseViewHolder(root);
        } else {
            return new TeacherCourseViewHolder(root);
        }
    }

    public class StudentCourseViewHolder extends MyViewHolder<MeModel> {
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
        protected void onBind(MeModel data, int position) {

            mTitle.setText(data.getCourseModel().getName());
            mIcon.setImageDrawable(data.getCourseModel().getIcon());
            mSchool.setText(data.getCourseModel().getSchool());
            mTeacher.setText(data.getCourseModel().getTeacher());
            mPractise.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // TODO: 2018/3/4 跳到课程的练习区域
                    Toast.makeText(view.getContext(), "练习", Toast.LENGTH_SHORT).show();
                }
            });
            mSchedule.setText("进行至" + data.getCourseModel().getProgress() + "周，总共" + data.getCourseModel().getSchedule() + "周");
            mProgress.setMax(data.getCourseModel().getSchedule());
            mProgress.setProgress(data.getCourseModel().getProgress());

        }
    }

    public class TeacherCourseViewHolder extends MyViewHolder<MeModel> {
        private TextView mTitle;
        private ImageView mIcon;
        private TextView mSchool;
        private TextView mTeacher;
        private TextView mSchedule;
        private ProgressBar mProgress;

        public TeacherCourseViewHolder(View itemView) {
            super(itemView);
            mTitle = (TextView) itemView.findViewById(R.id.tv_course_title);
            mIcon = (ImageView) itemView.findViewById(R.id.iv_course_icon);
            mSchool = (TextView) itemView.findViewById(R.id.tv_school_of_course);
            mTeacher = (TextView) itemView.findViewById(R.id.tv_teacher_of_course);
            mSchedule = (TextView) itemView.findViewById(R.id.tv_course_schedule);
            mProgress = (ProgressBar) itemView.findViewById(R.id.proBar_course_progress);
        }

        @Override
        protected void onBind(MeModel data, int position) {
            mTitle.setText(data.getCourseModel().getName());
            mIcon.setImageDrawable(data.getCourseModel().getIcon());
            mSchool.setText(data.getCourseModel().getSchool());
            mTeacher.setText(data.getCourseModel().getTeacher());
            mSchedule.setText("进行至" + data.getCourseModel().getProgress() + "周，总共" + data.getCourseModel().getSchedule() + "周");
            mProgress.setMax(data.getCourseModel().getSchedule());
            mProgress.setProgress(data.getCourseModel().getProgress());
        }
    }
}
