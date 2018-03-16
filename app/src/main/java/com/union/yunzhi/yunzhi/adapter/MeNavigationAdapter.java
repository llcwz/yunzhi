package com.union.yunzhi.yunzhi.adapter;

import android.content.Context;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.makeramen.roundedimageview.RoundedImageView;
import com.union.yunzhi.common.widget.MyAdapter;
import com.union.yunzhi.factories.moudles.me.MeConstant;
import com.union.yunzhi.factories.moudles.me.MeModel;
import com.union.yunzhi.factories.moudles.me.NavigationModel;
import com.union.yunzhi.yunzhi.R;

import java.util.List;

/**
 * Created by CrazyGZ on 2018/2/24.
 */

public class MeNavigationAdapter extends MyAdapter<NavigationModel>{

    private Context mContext;
    public MeNavigationAdapter(Context context, List<NavigationModel> dataList, AdapterListener<NavigationModel> listener) {
        super(dataList, listener);
        mContext = context;
    }

    @Override
    protected int getItemViewType(int position, NavigationModel data) {
        return R.layout.item_me_navigation;
    }

    @Override
    protected MyViewHolder<NavigationModel> onCreateViewHolder(View root, int viewType) {
        return new NavigationViewHolder(root);
    }

    @Override
    public boolean onLongClick(View view) {
        return false;
    }

    public class NavigationViewHolder extends MyViewHolder<NavigationModel>  {
        private RoundedImageView mNavigationIcon;
        private TextView mNavigationName;
        public NavigationViewHolder(final View itemView) {
            super(itemView);
            mNavigationIcon = (RoundedImageView) itemView.findViewById(R.id.ri_navigation_icon);
            mNavigationName = (TextView) itemView.findViewById(R.id.tv_navigation_name);
        }

        @Override
        protected void onBind(final NavigationModel data, int position) {
            Glide.with(mContext).load(data.getNavigationIcon()).into(mNavigationIcon);
            mNavigationName.setText(data.getNavigationName());
        }
    }
}
