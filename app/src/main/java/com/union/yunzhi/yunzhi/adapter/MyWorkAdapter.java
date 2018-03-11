package com.union.yunzhi.yunzhi.adapter;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.union.yunzhi.common.widget.MyAdapter;
import com.union.yunzhi.factories.moudles.me.MeConstant;
import com.union.yunzhi.factories.moudles.me.MeModel;
import com.union.yunzhi.factories.moudles.me.WorkModel;
import com.union.yunzhi.yunzhi.R;

import java.util.List;

/**
 * Created by CrazyGZ on 2018/3/6.
 */

public class MyWorkAdapter extends MyAdapter<WorkModel> {
    private Context mContext;

    public MyWorkAdapter(Context context, List<WorkModel> data, AdapterListener<WorkModel> listener) {
        super(data, listener);
        mContext = context;
    }
    @Override
    protected int getItemViewType(int position, WorkModel data) {
        if (data.getViewType() == MeConstant.STUDENT_WORK_VIEW) {
            return R.layout.item_me_my_work_student;
        } else {
            return R.layout.item_me_my_work_teacher;
        }
    }

    @Override
    protected MyViewHolder<WorkModel> onCreateViewHolder(View root, int viewType) {
        if (viewType == MeConstant.STUDENT_WORK_VIEW) {
            return new StudentWorkViewHolder(root);
        } else {
            return new TeacherWorkViewHolder(root);
        }
    }

    public class StudentWorkViewHolder extends MyViewHolder<WorkModel> {
        private TextView mTitle;
        private TextView mCourse; // 所属课程
        private TextView mStart; // 开始时间
        private TextView mEnd; // 结束时间
        private TextView mState; // 任务状态
        private TextView mPromulgator; // 发布者
        private TextView mTime; // 发布时间

        public StudentWorkViewHolder(View itemView) {
            super(itemView);
            mTitle = (TextView) itemView.findViewById(R.id.tv_work_title);
            mCourse = (TextView) itemView.findViewById(R.id.tv_course_of_work);
            mStart = (TextView) itemView.findViewById(R.id.tv_work_start);
            mEnd = (TextView) itemView.findViewById(R.id.tv_work_end);
            mState = (TextView) itemView.findViewById(R.id.tv_work_state);
            mPromulgator = (TextView) itemView.findViewById(R.id.tv_work_promulgator);
            mTime = (TextView) itemView.findViewById(R.id.tv_work_time);
        }

        @Override
        protected void onBind(WorkModel data, int position) {
            mTitle.setText(data.getName());
            mCourse.setText(data.getCourse());
            mStart.setText(data.getStart());
            mEnd.setText(data.getEnd());
            mState.setText(data.getState());
            mPromulgator.setText(data.getPromulgator());
            mTime.setText(data.getTime());
        }
    }

    public class TeacherWorkViewHolder extends MyViewHolder<WorkModel> {
        private TextView mTitle;
        private TextView mCourse; // 所属课程
        private TextView mStart; // 开始时间
        private TextView mEnd; // 结束时间
        private TextView mState; // 任务状态
        private TextView mTime; // 发布时间

        public TeacherWorkViewHolder(View itemView) {
            super(itemView);
            mTitle = (TextView) itemView.findViewById(R.id.tv_work_title);
            mCourse = (TextView) itemView.findViewById(R.id.tv_course_of_work);
            mStart = (TextView) itemView.findViewById(R.id.tv_work_start);
            mEnd = (TextView) itemView.findViewById(R.id.tv_work_end);
            mState = (TextView) itemView.findViewById(R.id.tv_work_state);
            mTime = (TextView) itemView.findViewById(R.id.tv_work_time);
        }

        @Override
        protected void onBind(WorkModel data, int position) {
            mTitle.setText(data.getName());
            mCourse.setText(data.getCourse());
            mStart.setText(data.getStart());
            mEnd.setText(data.getEnd());
            mState.setText(data.getState());
            mTime.setText(data.getTime());
        }
    }
}
