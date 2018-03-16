package com.union.yunzhi.yunzhi.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.union.yunzhi.common.util.LogUtils;
import com.union.yunzhi.common.widget.MyAdapter;
import com.union.yunzhi.factories.moudles.classfication.beans.classfication.CourseShowBean;
import com.union.yunzhi.yunzhi.R;

import java.util.List;

/**
 * Created by cjw on 2018/2/13 0013.
 */

public class ClassCourseAdapter extends MyAdapter<CourseShowBean>{

    private Context context;

    public ClassCourseAdapter(Context context, List<CourseShowBean> datas, AdapterListener adapterListener){
        super(datas,adapterListener);
        this.context=context;
    }

    @Override
    protected int getItemViewType(int position, CourseShowBean data) {
        return R.layout.item_class_course_item;
    }

    @Override
    protected MyViewHolder<CourseShowBean> onCreateViewHolder(View root, int viewType) {
        return new CouseViewHolder(root);
    }

    @Override
    public boolean onLongClick(View v) {

        return false;
    }


    public class CouseViewHolder extends MyViewHolder<CourseShowBean>{

        public TextView mTextView1,mTextView2,mTextView3,mTextView4;
        public ImageView mImageView;
        public CouseViewHolder(View itemView) {
            super(itemView);
            mTextView1=(TextView) itemView.findViewById(R.id.tv_largeTitle);
            mTextView2=(TextView) itemView.findViewById(R.id.tv_smallTitle);
            mTextView3=(TextView) itemView.findViewById(R.id.tv_upCount);
            mTextView4  =(TextView) itemView.findViewById(R.id.tv_pinLunCount);
            mImageView=(ImageView) itemView.findViewById(R.id.iv_show_course);
        }

        @Override
        protected void onBind(CourseShowBean data, int position) {
            mTextView1.setText(data.coursename);
            mTextView2.setText(data.teachername);
            mTextView3.setText(String.valueOf(data.good));
            mTextView4.setText(String.valueOf(data.commentnum));
            LogUtils.d("GLide",data.coursecover);
            Glide.with(context).
                    load(data.coursecover).
                    placeholder(R.mipmap.ic_launcher).
                    into(mImageView);
        }
    }
}