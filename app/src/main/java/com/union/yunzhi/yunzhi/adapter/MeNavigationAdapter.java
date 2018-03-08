package com.union.yunzhi.yunzhi.adapter;

import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

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

public class MeNavigationAdapter extends MyAdapter<MeModel>{

    public MeNavigationAdapter(List<MeModel> dataList, AdapterListener listener) {
        super(dataList, listener);
    }

    @Override
    protected int getItemViewType(int position, MeModel data) {
        return R.layout.item_me_navigation;
    }

    @Override
    protected MyViewHolder<MeModel> onCreateViewHolder(View root, int viewType) {
        return new NavigationViewHolder(root);
    }

    @Override
    public boolean onLongClick(View view) {
        return false;
    }

    public class NavigationViewHolder extends MyViewHolder<MeModel>  {
        private RoundedImageView mNavigationIcon;
        private TextView mNavigationName;
        public NavigationViewHolder(final View itemView) {
            super(itemView);
            mNavigationIcon = (RoundedImageView) itemView.findViewById(R.id.ri_navigation_icon);
            mNavigationName = (TextView) itemView.findViewById(R.id.tv_navigation_name);
        }

        @Override
        protected void onBind(final MeModel data, int position) {
            mNavigationIcon.setImageResource(data.getNavigationModel().getNavigationIcon());
            mNavigationName.setText(data.getNavigationModel().getNavigationName());
        }
    }
}
