package com.union.yunzhi.yunzhi.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.union.yunzhi.common.widget.MyAdapter;
import com.union.yunzhi.factories.moudles.classfication.beans.question.QuestionBean;
import com.union.yunzhi.factories.moudles.communication.CommunicationConstant;
import com.union.yunzhi.factories.moudles.me.UserModel;
import com.union.yunzhi.yunzhi.R;
import com.union.yunzhi.yunzhi.communicationutils.LikeUtils;
import com.union.yunzhi.yunzhi.manager.UserManager;

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

    public class QuestionViewHolder extends MyViewHolder<QuestionBean> implements View.OnClickListener {

        private UserManager mUserManager;
        private UserModel mUser;
        private QuestionBean data;
        private CircleImageView mIcon;
        private TextView mAuthor;
        private TextView mTime;
        private ImageView mLike;
        private TextView mReply;
        private TextView mLikeCount;
        private TextView mReplyCount;
        private TextView mTitle;
        private TextView mContent;
        private RecyclerView mRecyclerView;

        public QuestionViewHolder(View itemView) {
            super(itemView);
            mUserManager = UserManager.getInstance();
            mIcon = (CircleImageView) itemView.findViewById(R.id.ci_question_icon);
            mAuthor = (TextView) itemView.findViewById(R.id.tv_question_author);
            mLike = (ImageView) itemView.findViewById(R.id.iv_question_like);
            mReply = (TextView) itemView.findViewById(R.id.tv_question_reply);
            mLikeCount = (TextView) itemView.findViewById(R.id.tv_like_count);
            mReplyCount = (TextView) itemView.findViewById(R.id.tv_reply_count);
            mTime = (TextView) itemView.findViewById(R.id.tv_question_time);
            mTitle = (TextView) itemView.findViewById(R.id.tv_question_title);
            mContent = (TextView) itemView.findViewById(R.id.tv_question_content);
            mRecyclerView = (RecyclerView) itemView.findViewById(R.id.recycler);
        }

        @Override
        protected void onBind(QuestionBean data, int position) {
            this.data = data;
            Glide.with(mContext).load(data.photoUrl).into(mIcon);
            mAuthor.setText(data.name);
            mLikeCount.setText(data.favour);
            mReplyCount.setText(data.msgNum);
            mTime.setText(data.time);
            mTitle.setText(data.title);
            mContent.setText(data.content);

            mLike.setOnClickListener(this);
            mReply.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (!mUserManager.hasLogined()) {
                Toast.makeText(mContext, "请先登录", Toast.LENGTH_SHORT).show();
            } else {
                mUser = mUserManager.getUser();
                switch (v.getId()) {
                    case R.id.iv_question_like:
//                        LikeUtils.newInstance(data.id,
//                                CommunicationConstant.LIKE_TAG_QUESTION,
//                                mUser,
//                                mContext,
//                                mLike,
//                                mLikeCount)
//                        .iLike();
                        break;
                    case R.id.tv_question_reply:
                        break;
                    default:
                }
            }
        }
    }
}
