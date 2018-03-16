package com.union.yunzhi.yunzhi.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.union.yunzhi.common.helper.HiddenAnimUtils;
import com.union.yunzhi.common.widget.MyAdapter;
import com.union.yunzhi.factories.moudles.classfication.beans.details.TeacherBean;
import com.union.yunzhi.yunzhi.R;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by cjw on 2018/3/1 0001.
 */

public class MoreTeacherAdapter extends MyAdapter<TeacherBean> {

    protected Context context;

    public MoreTeacherAdapter(Context context,List<TeacherBean> dataList, AdapterListener listener) {
        super(dataList, listener);
        this.context=context;
    }

    @Override
    protected int getItemViewType(int position, TeacherBean data) {
        return R.layout.item_more_teacher_item;
    }

    @Override
    protected MyViewHolder<TeacherBean> onCreateViewHolder(View root, int viewType) {
        return new TeacherViewHolder(context,root);
    }

    public class TeacherViewHolder extends MyViewHolder<TeacherBean> {

        private Context context;

        public LinearLayout headLayout;
        public CircleImageView portrait;
        public TextView name, state, good;

        public LinearLayout sonLayout;
        public ImageView img1, img2, img3;
        public TextView tv1, tv2, tv3,mLongText;


        public TeacherViewHolder(final Context context, View itemView) {
            super(itemView);
            this.context=context;
            headLayout = (LinearLayout) itemView.findViewById(R.id.item_details_parent);
            sonLayout = (LinearLayout) itemView.findViewById(R.id.item_details_son);
            portrait = (CircleImageView) headLayout.findViewById(R.id.cImagV_portrait);
            name = (TextView) headLayout.findViewById(R.id.tv_teacher_name);
            state = (TextView) headLayout.findViewById(R.id.tv_teacher_state);
            good = (TextView) headLayout.findViewById(R.id.tv_up_num);

            sonLayout = (LinearLayout) itemView.findViewById(R.id.item_details_son);
            mLongText = (TextView) sonLayout.findViewById(R.id.tv_jianjie_teacher);
            img1 = (ImageView) sonLayout.findViewById(R.id.iv_course_show1);
            img2 = (ImageView) sonLayout.findViewById(R.id.iv_course_show2);
            img3 = (ImageView) sonLayout.findViewById(R.id.iv_course_show3);
            tv1 = (TextView) sonLayout.findViewById(R.id.tv_course_show1);
            tv2 = (TextView) sonLayout.findViewById(R.id.tv_course_show2);
            tv3 = (TextView) sonLayout.findViewById(R.id.tv_course_show3);

            sonLayout.post(new Runnable() {
                @Override
                public void run() {
                    final int height=sonLayout.getHeight();
                    sonLayout.setVisibility(View.GONE);
                    /**
                     * 实现视图的伸缩
                     */
                    headLayout.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            HiddenAnimUtils.newInstance(context,sonLayout,height,30).toggle();
                        }
                    });
                }
            });
        }

        @Override
        protected void onBind(TeacherBean data, int position) {

            //TODO 相关老师 模块使用框架加载头像
            //Glide.with(context).load(data.portraitUrl).into(portrait);
            name.setText(data.teacherName);
            state.setText(data.teacherState);
            good.setText(String.valueOf(data.good));
            mLongText.setText(data.teacherInfo);

            //TODO 相关老师 模块使用框架加载其他课程的图片
            //Glide.with(context).load(data.course1.courseCover).placeholder().into(img1);
            //Glide.with(context).load(data.imgUrl2).into(img2);
            //Glide.with(context).load(data.imgUrl3).into(img3);
            tv1.setText(data.course1.coursename); tv2.setText(data.course2.coursename);
            tv3.setText(data.course3.coursename);

        }

    }
}
