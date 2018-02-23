package com.union.yunzhi.yunzhi.adapter;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.union.yunzhi.common.widget.MyAdapter;
import com.union.yunzhi.yunzhi.R;
import com.union.yunzhi.yunzhi._class.TitleBean;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import java.util.List;

/**
 * Created by cjw on 2018/2/19 0019.
 */

public class ClassDrawerAdapter extends MyAdapter<TitleBean> {

    //用于动态构建textView
    private TextView tv;
    private int  pos1=-1,pos2=-1;

    public ClassDrawerAdapter(List<TitleBean> datas, AdapterListener adapterListener){

        super(datas,adapterListener);
    }

    @Override
    protected int getItemViewType(int position, TitleBean data) {
        return R.layout.item_class_drawer_item;
    }

    @Override
    protected MyViewHolder<TitleBean> onCreateViewHolder(View root, int viewType) {
        return new DrawerViewHolder(root);
    }

    public class DrawerViewHolder extends MyViewHolder<TitleBean>{

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
        protected void onBind(final TitleBean data, int position) {
            //适配分类父标题
            mTextView.setText(data.getTitle());
            mImageView.setImageResource(R.drawable.ic_airplay_black_24dp);

            final List<String> getData=data.getSonTitle();

            //RecycleView的当前的位置
            pos1=position;

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
                public boolean onTagClick(View view, int position, FlowLayout parent) {

                    pos2=position;
                    //pos1为RecycleView的当前位置，position为子项目的位置
                    Toast.makeText(context, "当前位置:"+pos1+","+getData.get(position), Toast.LENGTH_SHORT).show();

                    //TODO 点击Drawer里面的某个子项实现的操作
                    //更新文字提示
                    String temp=null;
                    if(pos1>=0&&pos2>=0){
                        temp=mDataList.get(pos1).getTitle()+"-"+data.getSonTitle().get(pos2);
                    }else{
                        temp="全部课程";
                    }

                    mListener.updataUI(temp);

                    return true;
                }
            });

            mLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //TODO 点击某个学院,进入某个学院的课程
                    String temp=null;
                    if(pos1>=0){
                        temp=mDataList.get(pos1).getTitle();
                    } else{
                        temp="全部课程";
                    }
                    mListener.updataUI(temp);
                }
            });
        }
    }

}