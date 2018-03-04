package com.union.yunzhi.yunzhi.adapter;

import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.makeramen.roundedimageview.RoundedImageView;
import com.union.yunzhi.common.widget.MyAdapter;
import com.union.yunzhi.factories.moudles.me.MeConstant;
import com.union.yunzhi.factories.moudles.me.NavigationModel;
import com.union.yunzhi.yunzhi.R;

import java.util.List;

/**
 * Created by CrazyGZ on 2018/2/24.
 */

public class MeNavigationAdapter extends MyAdapter<NavigationModel>{

    public MeNavigationAdapter(List<NavigationModel> dataList, AdapterListener listener) {
        super(dataList, listener);
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
        private View mView;
        private RoundedImageView mNavigationIcon;
        private TextView mNavigationName;
        public NavigationViewHolder(final View itemView) {
            super(itemView);
            mView = itemView;
            mNavigationIcon = (RoundedImageView) itemView.findViewById(R.id.ri_navigation_icon);
            mNavigationName = (TextView) itemView.findViewById(R.id.tv_navigation_name);
        }

        @Override
        protected void onBind(final NavigationModel data, int position) {
            mNavigationIcon.setImageResource(data.getNavigationIcon());
            mNavigationName.setText(data.getNavigationName());
            /**
             * 导航点击事件
             */
            mView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(view.getContext(), ""+data.getNavigationName(), Toast.LENGTH_SHORT).show();
                    if (data.getAccess() == 1) { // 如果是学生，则导航栏对应的点击事件如下
                        if (MeConstant.NAVIGATION_COMPREHENSIVE.equals(data.getNavigationName())) { // 我的实训

                        } else if (MeConstant.NAVIGATION_MY_WORK.equals(data.getNavigationName())) { // 我的任务

                        } else if (MeConstant.NAVIGATION_SCORE_SEARCH.equals(data.getNavigationName())) { // 成绩查询

                        } else if (MeConstant.NAVIGATION_ABILITY.equals(data.getNavigationName())) { //能力档案

                        } else if (MeConstant.NAVIGATION_NEWS.equals(data.getNavigationName())) { // 新闻资讯

                        }

                    } else if (data.getAccess() == 2) { // 如果是教师，则导航栏对应的点击事件如下
                        if (MeConstant.NAVIGATION_COURSE_MANAGEMENT.equals(data.getNavigationName())) { // 课程管理

                        } else if (MeConstant.NAVIGATION_MY_WORK.equals(data.getNavigationName())) { // 综合实训

                        } else if (MeConstant.NAVIGATION_WORK_MANAGEMENT.equals(data.getNavigationName())) { // 任务管理

                        } else if (MeConstant.NAVIGATION_ABILITY.equals(data.getNavigationName())) { //能力档案

                        } else if (MeConstant.NAVIGATION_DATA_ANALYSIS.equals(data.getNavigationName())) { // 数据分析

                        }
                    }
                }
            });
        }
    }
}
