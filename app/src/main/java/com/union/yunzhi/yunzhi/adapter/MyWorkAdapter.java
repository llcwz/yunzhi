package com.union.yunzhi.yunzhi.adapter;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.union.yunzhi.common.widget.MyAdapter;
import com.union.yunzhi.factories.moudles.me.MeModel;
import com.union.yunzhi.factories.moudles.me.WorkModel;
import com.union.yunzhi.yunzhi.R;

import java.util.List;

/**
 * Created by CrazyGZ on 2018/3/6.
 */

public class MyWorkAdapter extends MyAdapter<MeModel> {
    private Context mContext;

    public MyWorkAdapter(Context context, List<MeModel> data, AdapterListener<MeModel> listener) {
        super(data, listener);
        mContext = context;
    }
    @Override
    protected int getItemViewType(int position, MeModel data) {
        return R.layout.item_me_my_work;
    }

    @Override
    protected MyViewHolder<MeModel> onCreateViewHolder(View root, int viewType) {
        return new WorkViewHolder(root);
    }

    public class WorkViewHolder extends MyViewHolder<MeModel> {
        private TextView mTitle;
        private TextView mCourse; // 所属课程
        private TextView mStart; // 开始时间
        private TextView mEnd; // 结束时间
        private TextView mState; // 任务状态
        private TextView mPromulgator; // 发布者
        private TextView mTime; // 发布时间

        public WorkViewHolder(View itemView) {
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
        protected void onBind(MeModel data, int position) {
            mTitle.setText(data.getWorkModel().getName());
            mCourse.setText(data.getWorkModel().getCourse());
            mStart.setText(data.getWorkModel().getStart());
            mEnd.setText(data.getWorkModel().getEnd());
            mState.setText(data.getWorkModel().getState());
            mPromulgator.setText(data.getWorkModel().getPromulgator());
            mTime.setText(data.getWorkModel().getTime());
        }
    }
}
