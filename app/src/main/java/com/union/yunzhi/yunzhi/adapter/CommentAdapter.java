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
import com.union.yunzhi.yunzhi.R;
import com.union.yunzhi.yunzhi.communicationutils.LikeUtils;
import com.union.yunzhi.yunzhi.manager.UserManager;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * 用于加载评论
 * Created by CrazyGZ on 2018/3/9.
 */

public class CommentAdapter extends MyAdapter<CommentModel> {

    private Context mContext;

    public CommentAdapter(Context context, List<CommentModel> data, AdapterListener<CommentModel> listener) {
        super(data,listener);
        mContext = context;
    }

    @Override
    protected int getItemViewType(int position, CommentModel data) {
        return R.layout.item_communication_comment;
    }

    @Override
    protected MyViewHolder<CommentModel> onCreateViewHolder(View root, int viewType) {
        return new CommentViewHolder(root);
    }

    public class CommentViewHolder extends MyViewHolder<CommentModel> {
        private UserManager mUserManager;
        private CircleImageView mIcon;
        private TextView mAuthor;
        private TextView mTime;
        private ImageView mLike;
        private TextView mLikeCount; // 点赞数
        private TextView mContent;


        public CommentViewHolder(View itemView) {
            super(itemView);
            mUserManager = UserManager.getInstance();
            mIcon = (CircleImageView) itemView.findViewById(R.id.ci_comment_icon);
            mAuthor = (TextView) itemView.findViewById(R.id.tv_comment_author);
            mTime = (TextView) itemView.findViewById(R.id.tv_comment_time);
            mLike = (ImageView) itemView.findViewById(R.id.iv_comment_like);
            mLikeCount = (TextView) itemView.findViewById(R.id.tv_comment_like);
            mContent = (TextView) itemView.findViewById(R.id.tv_comment_content);
        }

        @Override
        protected void onBind(final CommentModel data, int position) {
            Glide.with(mContext).load(data.getIcon()).into(mIcon);
            mAuthor.setText(data.getAuthor());
            mTime.setText(data.getTime());
            mContent.setText(data.getContent());
            mLikeCount.setText("" + data.getLikeModels().size()); // 获取点赞数
            // 点赞的点击事件
            mLike.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (mUserManager.hasLogined()) { // 用户登录了
                        LikeUtils likeUtils = LikeUtils.newInstance(CommunicationConstant.LIKE_TAG_COMMENT,mUserManager, mContext, mLike,mLikeCount);
                        likeUtils.checkedCommentLike(data);
                    } else { // 用户没登录
                        Toast.makeText(mContext, "请先登录", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

}
