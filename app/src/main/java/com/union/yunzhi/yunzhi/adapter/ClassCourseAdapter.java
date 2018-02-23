package com.union.yunzhi.yunzhi.adapter;

import android.view.View;
import android.widget.TextView;

import com.union.yunzhi.common.widget.MyAdapter;
import com.union.yunzhi.yunzhi.R;

import java.util.List;

/**
 * Created by cjw on 2018/2/13 0013.
 */

public class ClassCourseAdapter extends MyAdapter<String>{

    public ClassCourseAdapter(List<String> datas, AdapterListener adapterListener){
        super(datas,adapterListener);
    }

    @Override
    protected int getItemViewType(int position, String data) {
        return R.layout.item_class_course_item;
    }

    @Override
    protected MyViewHolder<String> onCreateViewHolder(View root, int viewType) {
        return new CouseViewHolder(root);
    }

    @Override
    public boolean onLongClick(View v) {
        return false;
    }

    public class CouseViewHolder extends MyViewHolder<String>{

        private TextView mTextView;
        public CouseViewHolder(View itemView) {
            super(itemView);
            mTextView=(TextView) itemView.findViewById(R.id.tv_smallTitle);
        }

        @Override
        protected void onBind(String data, int position) {
            mTextView.setText(data);
        }
    }
}