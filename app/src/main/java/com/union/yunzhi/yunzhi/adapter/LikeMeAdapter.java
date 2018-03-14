package com.union.yunzhi.yunzhi.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.union.yunzhi.common.widget.MyAdapter;
import com.union.yunzhi.factories.moudles.me.LikeMeModel;
import com.union.yunzhi.yunzhi.R;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by CrazyGZ on 2018/3/12.
 */

public class LikeMeAdapter extends MyAdapter<LikeMeModel> {
    private Context mContext;

    public LikeMeAdapter(Context context, List<LikeMeModel> data, AdapterListener<LikeMeModel> listener) {
        super(data, listener);
        mContext = context;
    }

    @Override
    protected int getItemViewType(int position, LikeMeModel data) {
        return R.layout.item_me_message_like;
    }

    @Override
    protected MyViewHolder<LikeMeModel> onCreateViewHolder(View root, int viewType) {
        return new LikeMeViewHolder(root);
    }

    public class LikeMeViewHolder extends MyViewHolder<LikeMeModel> {

        private CircleImageView mIcon;
        private TextView mName;
        private TextView mTime;
        private TextView mContent; // 我的评论内容
        public LikeMeViewHolder(View itemView) {
            super(itemView);
            mIcon = (CircleImageView) itemView.findViewById(R.id.ci_like_icon);
            mName = (TextView) itemView.findViewById(R.id.tv_like_author);
            mTime = (TextView) itemView.findViewById(R.id.tv_like_time);
            mContent = (TextView) itemView.findViewById(R.id.tv_comment_content);
        }

        @Override
        protected void onBind(LikeMeModel data, int position) {
            Glide.with(mContext).load(data.getIcon()).into(mIcon);
            mName.setText(data.getName());
            mTime.setText(data.getTime());
            mContent.setText(data.getMyComment());
        }
    }
}

