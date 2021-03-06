package com.union.yunzhi.yunzhi.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.union.yunzhi.common.helper.ScreenUtils;
import com.union.yunzhi.common.widget.MyAdapter;
import com.union.yunzhi.factories.moudles.live.beans.LiveBean;
import com.union.yunzhi.yunzhi.R;

import java.util.List;

/**
 * Created by cjw on 2018/2/22 0022.
 */

public  class LiveShowAdapter extends MyAdapter<LiveBean> {

    private Context context;

    public LiveShowAdapter(Context context,List<LiveBean> dataList, AdapterListener listener) {
        super(dataList, listener);
        this.context=context;
    }

    @Override
    protected int getItemViewType(int position, LiveBean data) {
        return R.layout.item_live_show_item;
    }

    @Override
    protected MyViewHolder<LiveBean> onCreateViewHolder(View root, int viewType) {
        return new LiveShowHolder(root);
    }

    @Override
    public boolean onLongClick(View v) {
        return false;
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
    }

    public class LiveShowHolder extends MyViewHolder<LiveBean>{

        private LinearLayout mLinearLayout;
        private ImageView mImageView;
        private TextView courseName,teacherName,lineCount;

        public LiveShowHolder(View itemView) {
            super(itemView);
            mLinearLayout= (LinearLayout) itemView.findViewById(R.id.linear_layout);
            mImageView= (ImageView) itemView.findViewById(R.id.iv_live_show);
            courseName= (TextView) itemView.findViewById(R.id.tv_Cname);
            teacherName= (TextView) itemView.findViewById(R.id.tv_show_Tname);
            lineCount= (TextView) itemView.findViewById(R.id.tv_lineCount);
        }

        @Override
        protected void onBind(LiveBean data, int position) {

            LiveBean liveBean=mDataList.get(position);
            //获取Item宽度，计算图片等比比例缩放后的高度，为ImageView设置参数
            LinearLayout.LayoutParams params1= (LinearLayout.LayoutParams) mImageView.getLayoutParams();
            float itemWidth=(ScreenUtils.getScreenWidth(itemView.getContext())-1*3)/2;

            params1.width=(int)itemWidth;
            float scale=(itemWidth+0f)/liveBean.width;
            params1.height=(int) (liveBean.heigh*scale);
            mImageView.setLayoutParams(params1);


            mImageView.setImageResource(data.Id);
            courseName.setText("课程名称");
            teacherName.setText("万老师");
            lineCount.setText(String.valueOf("12306"));

            //TODO 此处处理直播模块recycleView信息加载
            //Glide.with(context).load(data.imageUrl).into(mImageView);
            //courseName.setText(data.courseName);
            //teacherName.setText(data.teacherName);
            //lineCount.setText(String.valueOf(data.lineCount));

            mLinearLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //TODO 进入直播的点击事件
                    Toast.makeText(context,"点击了！！！",Toast.LENGTH_SHORT).show();
                }
            });

        }
    }
}
