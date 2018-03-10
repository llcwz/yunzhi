package com.union.yunzhi.yunzhi.adapter;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.union.yunzhi.common.widget.MyAdapter;
import com.union.yunzhi.factories.moudles.me.GradeModel;
import com.union.yunzhi.factories.moudles.me.MeModel;
import com.union.yunzhi.factories.moudles.me.UnitGradeModel;
import com.union.yunzhi.yunzhi.R;

import java.util.List;

/**
 * Created by CrazyGZ on 2018/3/6.
 */

public class SearchGradeAdapter extends MyAdapter<GradeModel> {
    private Context mContext;

    public SearchGradeAdapter (Context context, List<GradeModel> data, AdapterListener<GradeModel> listener) {
        super(data, listener);
        mContext = context;
    }
    @Override
    protected int getItemViewType(int position, GradeModel data) {
        return R.layout.item_me_search_grade_groupitem;
    }

    @Override
    protected MyViewHolder<GradeModel> onCreateViewHolder(View root, int viewType) {
        return new GradeGroupViewHolder(root);
    }

    public class GradeGroupViewHolder extends MyViewHolder<GradeModel> {

        private boolean mOpen = false;
        private View mView;
        private ImageView mState; // 父列表的状态
        private TextView mCourse; // 课程名称
        private TextView mGrade; // 分数
        private RecyclerView mRecyclerView; // 子列表
        private UnitGradeAdapter mAdapter; // 二级列表的item

        public GradeGroupViewHolder(View itemView) {
            super(itemView);
            mView = itemView;
            mState = (ImageView) itemView.findViewById(R.id.iv_search_grade_state);
            mCourse = (TextView) itemView.findViewById(R.id.tv_search_grade_course);
            mGrade = (TextView) itemView.findViewById(R.id.tv_search_grade_grade);
            mRecyclerView = (RecyclerView) itemView.findViewById(R.id.recycler);
        }

        @Override
        protected void onBind(GradeModel data, int position) {
            initData(data);
            mCourse.setText(data.getCourse());
            mGrade.setText(data.getGrade());
            mState.setImageResource(R.drawable.iv_me_search_grade_triangle_right);
            mView.setOnClickListener(new View.OnClickListener() { // 监听点击
                @Override
                public void onClick(View view) {
                    show(mRecyclerView); // 加载二级列表数据
                    if (!mOpen) { // 如果二级列表界面没有打开，则将其打开
                        mState.setImageResource(R.drawable.iv_me_search_grade_triangle_down);
                        mRecyclerView.setVisibility(View.VISIBLE); // 现实可见
                    } else { // 如果二级列表打开了，则收缩回来
                        mState.setImageResource(R.drawable.iv_me_search_grade_triangle_right);
                        mRecyclerView.setVisibility(View.GONE); // 隐藏
                    }
                    mOpen = !mOpen; // 转换列表的状态
                }

                private void show(RecyclerView recyclerView) {
                    recyclerView.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL,false));
                    recyclerView.setAdapter(mAdapter);
                }
            });

        }

        // 二级列表的数据
        private void initData(GradeModel data) {
            mAdapter = new UnitGradeAdapter(data.getUnitGradeModels(), null);
        }

        private class UnitGradeAdapter extends MyAdapter<UnitGradeModel> {

            public UnitGradeAdapter (List<UnitGradeModel> data, AdapterListener<UnitGradeModel> listener) {
                super(data, listener);
            }
            @Override
            protected int getItemViewType(int position, UnitGradeModel data) {
                return R.layout.item_me_search_grade_subitem;
            }

            @Override
            protected MyViewHolder<UnitGradeModel> onCreateViewHolder(View root, int viewType) {
                return new UnitGradeViewHolder(root);
            }


            private class UnitGradeViewHolder extends MyViewHolder<UnitGradeModel> {
                private TextView mUnitGrade;

                public UnitGradeViewHolder(View view) {
                    super(view);
                    mUnitGrade = (TextView) view.findViewById(R.id.tv_unit_grade);
                }

                @Override
                protected void onBind(UnitGradeModel data, int position) {
                        mUnitGrade.setText(data.getName() + "："+ data.getGrade() + "分");
                }
            }
        }
    }
}
