package com.union.yunzhi.common.helper;

import android.support.v7.widget.RecyclerView;

/**
 * Created by meng on 2018/2/9.
 */

public  class RecyclerHelper {

    private mScrollListener mListener;

    private RecyclerView recyclerView;

    private boolean LOCK_Y;//锁住Y方向的滑动
    private boolean LOCK_X;//锁住X方向的滑动


    private float OLD_DY = -1;//临时存放dy
    private float OLD_DX = -1;//临时存放dx



    /**
     * 采用Builder模式来构建
     */
    private RecyclerHelper(){
    }

    private RecyclerHelper(RecyclerHelper recyclerHelper){
        this.recyclerView=recyclerHelper.recyclerView;
    }

    public void setLostener(mScrollListener lostener){
        this.mListener = lostener;
    }


    public void Listener(){
       recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
           @Override
           public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
               super.onScrollStateChanged(recyclerView, newState);
               if(mListener!=null){
                   if(newState == 0)
                       mListener.SCROLL_STATE_IDLE();//表示屏幕已停止。屏幕停止滚动时为
                   else if(newState == 1)
                       mListener.SCROLL_STATE_TOUCH_SCROLL();//表示正在滚动。当屏幕滚动且用户使用的触碰或手指还在屏幕上时为1
                   else if(newState == 2)
                       mListener.SCROLL_STATE_FLING();//表示手指做了抛的动作（手指离开屏幕前，用力滑了一下，屏幕产生惯性滑动）。
               }
           }

           @Override
           public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
               super.onScrolled(recyclerView, dx, dy);
               if(mListener !=null){

                   if((OLD_DY <0&&dy>0) ||(OLD_DY>0&&dy<0))
                   {
                       LOCK_Y = false;//当运动方向发生改变的时候解除对Y轴的锁定
                   }

                   if(dy > 0)
                   {
                        if(!LOCK_Y)
                        {
                            LOCK_Y = true;//手指向上滑动并且要锁住Y轴，防止多彩回调
                            mListener.upGlide();

                        }
                   }

                   else if(dy < 0 )
                   {
                       if(!LOCK_Y)
                       {
                           LOCK_Y = true;//同上
                           mListener.downGlide();

                       }
                   }
                   OLD_DY = dy;//记录旧的DY
               }//end if
           }//end
       });//end onScrolled
    }




    public static class Builder{
        private RecyclerHelper target;

        public Builder(){
            target = new RecyclerHelper();
        }

        public Builder addRecyclerView(RecyclerView recyclerView){
            target.recyclerView = recyclerView;
            return this;
        }

        public Builder addSetScroListener(mScrollListener listener){
            target.mListener = listener;
            return  this;
        }

        public RecyclerHelper build() {
            return new RecyclerHelper(target);
        }
    }





    public interface mScrollListener{
        void upGlide();//手指向上滑动，并且脱离手机
        void downGlide();//手指向下滑动，并且脱离了手机
        void leftGlide();//手指向左滑动
        void rightGlide();//手指向右滑动
        void upAndrightGlide();//手指右上滑动
        void upAndleftGlide();//手指向左上滑动
        void downAndrightGlide();//手指向右下滑动
        void downAndleftGlide();//手指向左下滑动
        void SCROLL_STATE_FLING();//屏幕处于甩动状态
        void SCROLL_STATE_IDLE();//停止滑动状态
        void SCROLL_STATE_TOUCH_SCROLL();// 手指接触状态
    }

    public static  class mScrollListenerImpl implements mScrollListener {
        @Override
        public void upGlide() {

        }

        @Override
        public void downGlide() {

        }

        @Override
        public void leftGlide() {

        }

        @Override
        public void rightGlide() {

        }

        @Override
        public void upAndrightGlide() {

        }

        @Override
        public void upAndleftGlide() {

        }

        @Override
        public void downAndrightGlide() {

        }

        @Override
        public void downAndleftGlide() {

        }

        @Override
        public void SCROLL_STATE_FLING() {

        }

        @Override
        public void SCROLL_STATE_IDLE() {

        }

        @Override
        public void SCROLL_STATE_TOUCH_SCROLL() {

        }
    }
}
