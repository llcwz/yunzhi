package com.union.yunzhi.yunzhi.adapter;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.union.yunzhi.common.widget.MyAdapter;
import com.union.yunzhi.factories.moudles.communication.PostModel;
import com.union.yunzhi.yunzhi.R;

import org.w3c.dom.Text;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by CrazyGZ on 2018/3/9.
 */

public class PostAdapter extends MyAdapter<PostModel> {
    private Context mContext;

    public PostAdapter(Context context, List<PostModel> data, AdapterListener<PostModel> listener) {
        super(data,listener);
        mContext = context;
    }
    @Override
    protected int getItemViewType(int position, PostModel data) {
        return R.layout.item_communication_post;
    }

    @Override
    protected MyViewHolder<PostModel> onCreateViewHolder(View root, int viewType) {
        return new CollegeViewHolder(root);
    }

    public class CollegeViewHolder extends MyViewHolder<PostModel> {
        private CircleImageView mIcon;
        private TextView mAuthor;
        private TextView mTime;
        private TextView mLike;
        private TextView mComment;
        private TextView mTitle;
        private TextView mContent;

        public CollegeViewHolder(View itemView) {
            super(itemView);
            mIcon = (CircleImageView) itemView.findViewById(R.id.ci_post_icon);
            mAuthor = (TextView) itemView.findViewById(R.id.tv_post_author);
            mLike = (TextView) itemView.findViewById(R.id.tv_post_like);
            mComment = (TextView) itemView.findViewById(R.id.tv_post_comment);
            mTime = (TextView) itemView.findViewById(R.id.tv_post_time);
            mTitle = (TextView) itemView.findViewById(R.id.tv_post_title);
            mContent = (TextView) itemView.findViewById(R.id.tv_post_content);
        }

        @Override
        protected void onBind(PostModel data, int position) {
            Glide.with(mContext).load(data.getIcon()).into(mIcon);
            mAuthor.setText(data.getAuthor());
            mLike.setText("" + data.getLikeModels().size());
            mComment.setText("" + data.getCommentModels().size());
            mTime.setText(data.getTime());
            mTitle.setText(data.getTitle());
            mContent.setText(data.getContent());
        }
    }
}
