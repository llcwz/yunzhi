package com.union.yunzhi.yunzhi.adapter;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.union.yunzhi.common.util.LogUtils;
import com.union.yunzhi.common.widget.MyAdapter;
import com.union.yunzhi.factories.moudles.classfication.beans.drawer.DrawerBean;
import com.union.yunzhi.yunzhi.R;
import com.union.yunzhi.yunzhi.contant.Constant;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by cjw on 2018/2/19 0019.
 */

public class ClassDrawerAdapter extends MyAdapter<DrawerBean> {

    //用于动态构建textView
    private TextView tv;
    private int  pos1=-1,pos2=-1;

    public ClassDrawerAdapter(List<DrawerBean> datas, AdapterListener adapterListener){

        super(datas,adapterListener);
    }

    @Override
    protected int getItemViewType(int position, DrawerBean data) {
        return R.layout.item_class_drawer_item;
    }

    @Override
    protected MyViewHolder<DrawerBean> onCreateViewHolder(View root, int viewType) {
        return new DrawerViewHolder(root);
    }

    @Override
    public boolean onLongClick(View v) {
        return false;
    }

    public class DrawerViewHolder extends MyViewHolder<DrawerBean>{

        private TextView mTextView;
        private TagFlowLayout mTagFlowLayout;
        private Context context;
        private ImageView mImageView;
        private ConstraintLayout mLayout;

        public DrawerViewHolder(View itemView) {
            super(itemView);
            mLayout= (ConstraintLayout) itemView.findViewById(R.id.tv_class_father);
            mTextView= (TextView) mLayout.findViewById(R.id.tv_class_drawer_head);
            mTagFlowLayout= (TagFlowLayout) itemView.findViewById(R.id.flowLayout);
            mImageView= (ImageView) itemView.findViewById(R.id.iv_class_drawer_head);
            context=itemView.getContext();
        }

        @Override
        protected void onBind(final DrawerBean data, final int position) {
            //适配分类父标题
            mTextView.setText(data.academicname);
            Glide.with(context).load(data.icon).placeholder(R.drawable.ic_airplay_black_24dp).into(mImageView);
            final List<String> getData=new ArrayList<>();
            for(int i=0;i<data.course.size();i++){
                getData.add(data.course.get(i).coursename);
            }

            //适配分类子标题
            mTagFlowLayout.setAdapter(new TagAdapter<String>(getData) {

                @Override
                public View getView(FlowLayout parent, int position, String s) {
                    tv=new TextView(context);
                    tv.setTextSize(13);
                    tv.setPadding(34,18,34,18);

                    //TODO TitleBean里面需要加入记录父标题图片
                    tv.setBackgroundResource(R.drawable.class_tag);
                    tv.setText(s);
                    return tv;
                }
            });



            mTagFlowLayout.setOnTagClickListener(new TagFlowLayout.OnTagClickListener() {
                @Override
                public boolean onTagClick(View view, int pos, FlowLayout parent) {

                    //pos1为RecycleView的当前位置，position为子项目的位置
                    //Toast.makeText(context, "当前位置:"+position+","+getData.get(pos), Toast.LENGTH_SHORT).show()
                    //TODO 点击Drawer里面的某个子项实现的操作

                    Map<String,String> map=new HashMap<String, String>();
                    map.put(Constant.TEXT_SHOW,mDataList.get(position).course.get(pos).coursename);
                    map.put(Constant.ID,mDataList.get(position).course.get(pos).courseid);
                    mListener.updataUI(map);

                    return true;
                }
            });

            mLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //TODO 点击某个学院,进入某个学院的课程
//                    LogUtils.d("KK","点击了！！！");
//                    String temp;
//                    if(position>=0){
//                        temp=mDataList.get(position).academicname;
//                    } else{
//                        temp="全部课程";
//                    }
//                    Map<String,String> map=new HashMap<String, String>();
//                    map.put(Constant.TEXT_SHOW,temp);
//                    map.put(Constant.ID,mDataList.get(position).academicid);
//                    mListener.updataUI(map);
                }
            });
        }
    }

}
