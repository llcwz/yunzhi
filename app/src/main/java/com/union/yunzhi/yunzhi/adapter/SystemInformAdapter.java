package com.union.yunzhi.yunzhi.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.union.yunzhi.common.widget.MyAdapter;
import com.union.yunzhi.factories.moudles.me.SystemInformModel;
import com.union.yunzhi.yunzhi.R;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by CrazyGZ on 2018/3/12.
 */

public class SystemInformAdapter extends MyAdapter<SystemInformModel> {
    private Context mContext;

    public SystemInformAdapter(Context context, List<SystemInformModel> data, AdapterListener<SystemInformModel> listener) {
        super(data, listener);
        mContext = context;
    }

    @Override
    protected int getItemViewType(int position, SystemInformModel data) {
        return R.layout.item_me_message_system_inform;
    }

    @Override
    protected MyViewHolder<SystemInformModel> onCreateViewHolder(View root, int viewType) {
        return new SystemInformViewHolder(root);
    }

    public class SystemInformViewHolder extends MyViewHolder<SystemInformModel> {

        private CircleImageView mIcon;
        private TextView mName;
        private TextView mTime;
        private TextView mContent; // 评论内容
        public SystemInformViewHolder(View itemView) {
            super(itemView);
            mIcon = (CircleImageView) itemView.findViewById(R.id.ci_system_icon);
            mName = (TextView) itemView.findViewById(R.id.tv_system_name);
            mTime = (TextView) itemView.findViewById(R.id.tv_system_time);
            mContent = (TextView) itemView.findViewById(R.id.tv_system_content);
        }

        @Override
        protected void onBind(SystemInformModel data, int position) {
            Glide.with(mContext).load(data.getIcon()).into(mIcon);
            mName.setText(data.getName());
            mTime.setText(data.getTime());
            mContent.setText(data.getContent());
        }
    }
}

