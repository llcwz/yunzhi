package com.union.yunzhi.yunzhi.adapter;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.union.yunzhi.common.widget.MyAdapter;
import com.union.yunzhi.factories.moudles.classfication.beans.question.QuestionBean;
import com.union.yunzhi.yunzhi.R;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by cjw on 2018/3/10 0010.
 */

public class ClassQuestionAdapter extends MyAdapter<QuestionBean> {

    private Context mContext;

    public ClassQuestionAdapter(Context context, List<QuestionBean> data, AdapterListener<QuestionBean> listener) {
        super(data, listener);
        mContext = context;
    }

    @Override
    protected int getItemViewType(int position, QuestionBean data) {
        return R.layout.item_class_question;
    }

    @Override
    protected MyViewHolder<QuestionBean> onCreateViewHolder(View root, int viewType) {
        return new QuestionViewHolder(root);
    }

    public class QuestionViewHolder extends MyViewHolder<QuestionBean> {

        private CircleImageView mIcon;
        private TextView mAuthor;
        private TextView mTime;
        private TextView mLike;
        private TextView mComment;
        private TextView mContent;

        public QuestionViewHolder(View itemView) {
            super(itemView);
            mIcon = (CircleImageView) itemView.findViewById(R.id.ci_question_icon);
            mAuthor = (TextView) itemView.findViewById(R.id.tv_question_author);
            mLike = (TextView) itemView.findViewById(R.id.tv_question_like);
            mComment = (TextView) itemView.findViewById(R.id.tv_question_comment);
            mTime = (TextView) itemView.findViewById(R.id.tv_question_time);
            mContent = (TextView) itemView.findViewById(R.id.tv_question_content);
        }

        @Override
        protected void onBind(QuestionBean data, int position) {
            Glide.with(mContext).load(data.photoUrl).into(mIcon);
            mAuthor.setText(data.name);
            mLike.setText(data.favour);
            mComment.setText(data.msgNum);
            mTime.setText(data.time);
            mContent.setText(data.content);
        }
    }
}
