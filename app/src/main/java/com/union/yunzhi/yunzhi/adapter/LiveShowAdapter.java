package com.union.yunzhi.yunzhi.adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.union.yunzhi.common.helper.ScreenUtils;
import com.union.yunzhi.common.widget.MyAdapter;
import com.union.yunzhi.yunzhi.R;
import com.union.yunzhi.yunzhi.live.LiveBean;

import java.util.List;

/**
 * Created by cjw on 2018/2/22 0022.
 */

public  class LiveShowAdapter extends MyAdapter<LiveBean> {


    public LiveShowAdapter(List<LiveBean> dataList,AdapterListener listener) {
        super(dataList, listener);
    }

    @Override
    protected int getItemViewType(int position, LiveBean data) {
        return R.layout.item_live_show_item;
    }

    @Override
    protected MyViewHolder<LiveBean> onCreateViewHolder(View root, int viewType) {
        return new LiveShowHolder(root);
    }

    public class LiveShowHolder extends MyViewHolder<LiveBean>{

        private ImageView mImageView;

        public LiveShowHolder(View itemView) {
            super(itemView);
            mImageView= (ImageView) itemView.findViewById(R.id.iv_live_show);
        }

        @Override
        protected void onBind(LiveBean data, int position) {

            LiveBean liveBean=mDataList.get(position);
            //获取Item宽度，计算图片等比比例缩放后的高度，为ImageView设置参数
            LinearLayout.LayoutParams params= (LinearLayout.LayoutParams) mImageView.getLayoutParams();
            float itemWidth=(ScreenUtils.getScreenWidth(itemView.getContext())-1*3)/2;
            params.width=(int)itemWidth;
            float scale=(itemWidth+0f)/liveBean.getWidth();
            params.height=(int) (liveBean.getHeigh()*scale);
            mImageView.setLayoutParams(params);

            //TODO 此处处理直播模块recycleView图片加载
            mImageView.setImageResource(data.getId());

        }
    }
}
