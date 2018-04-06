package com.union.yunzhi.yunzhi.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.union.yunzhi.common.widget.MyAdapter;
import com.union.yunzhi.factories.moudles.communication.CommentModel;
import com.union.yunzhi.factories.moudles.communication.CommunicationConstant;
import com.union.yunzhi.factories.moudles.communication.ReplyModel;
import com.union.yunzhi.yunzhi.R;
import com.union.yunzhi.yunzhi.communicationutils.LikeUtils;
import com.union.yunzhi.yunzhi.manager.UserManager;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * 用于加载评论
 * Created by CrazyGZ on 2018/3/9.
 */

public class ReplyAdapter extends MyAdapter<ReplyModel> {

    private Context mContext;
    public ReplyAdapter(Context context, List<ReplyModel> data, AdapterListener<ReplyModel> listener) {
        super(data,listener);
        mContext = context;
    }

    @Override
    protected int getItemViewType(int position, ReplyModel data) {
        return R.layout.item_communication_reply;
    }

    @Override
    protected MyViewHolder<ReplyModel> onCreateViewHolder(View root, int viewType) {
        return new ReplyViewHolder(root);
    }

    public class ReplyViewHolder extends MyViewHolder<ReplyModel>{

        public CircleImageView mIcon;
        private TextView mAuthor;
        private TextView mTime;
        private TextView mContent;

        public ReplyViewHolder(View itemView) {
            super(itemView);
            mIcon = (CircleImageView) itemView.findViewById(R.id.ci_comment_icon);
            mAuthor = (TextView) itemView.findViewById(R.id.tv_comment_author);
            mTime = (TextView) itemView.findViewById(R.id.tv_comment_time);
            mContent = (TextView) itemView.findViewById(R.id.tv_comment_content);
        }

        @Override
        protected void onBind(final ReplyModel data, int position) {
            Glide.with(mContext).load(data.getPhotourl()).into(mIcon);
            mAuthor.setText(data.getName());
            mTime.setText(data.getTime());
            mContent.setText(data.getContent());
        }

    }

}
