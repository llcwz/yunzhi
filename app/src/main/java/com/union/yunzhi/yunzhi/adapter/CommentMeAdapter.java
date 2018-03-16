package com.union.yunzhi.yunzhi.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.union.yunzhi.common.widget.MyAdapter;
import com.union.yunzhi.common.widget.adbrowser.AdBrowserWebViewClient;
import com.union.yunzhi.factories.moudles.me.CommentMeModel;
import com.union.yunzhi.factories.moudles.me.MeConstant;
import com.union.yunzhi.factories.moudles.me.MessageModel;
import com.union.yunzhi.yunzhi.R;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by CrazyGZ on 2018/3/12.
 */

public class CommentMeAdapter extends MyAdapter<CommentMeModel> {
    private Context mContext;

    public CommentMeAdapter(Context context, List<CommentMeModel> data, AdapterListener<CommentMeModel> listener) {
        super(data, listener);
        mContext = context;
    }

    @Override
    protected int getItemViewType(int position, CommentMeModel data) {
        return R.layout.item_me_message_comment;
    }

    @Override
    protected MyViewHolder<CommentMeModel> onCreateViewHolder(View root, int viewType) {
        return new CommentMeViewHolder(root);
    }

    public class CommentMeViewHolder extends MyViewHolder<CommentMeModel> {

        private CircleImageView mIcon;
        private TextView mName;
        private TextView mTime;
        private TextView mCommentOrReply; // 更改为评论还是回复
        private TextView mTitle; // 我发帖子的标题
        private TextView mQuestion; // 我发表的问题
        private TextView mContent; // 评论内容
        public CommentMeViewHolder(View itemView) {
            super(itemView);
            mIcon = (CircleImageView) itemView.findViewById(R.id.ci_message_icon);
            mName = (TextView) itemView.findViewById(R.id.tv_message_author);
            mTime = (TextView) itemView.findViewById(R.id.tv_message_time);
            mCommentOrReply = (TextView) itemView.findViewById(R.id.tv_comment_or_reply);
            mTitle = (TextView) itemView.findViewById(R.id.tv_message_title);
            mQuestion = (TextView) itemView.findViewById(R.id.tv_message_title);
            mContent = (TextView) itemView.findViewById(R.id.tv_message_content);
        }

        @Override
        protected void onBind(CommentMeModel data, int position) {
            Glide.with(mContext).load(data.getIcon()).into(mIcon);
            mName.setText(data.getName());
            mTime.setText(data.getTime());
            if (TextUtils.isEmpty(data.getMyTitle())) { // 标题为空，说明这条评论评论的是问题
                mCommentOrReply.setText("回复了你");
                mQuestion.setText(data.getQuestion());
            } else {
                mCommentOrReply.setText("评论了你");
                mTitle.setText(data.getMyTitle());
            }
            mContent.setText(data.getContent());
        }
    }
}

