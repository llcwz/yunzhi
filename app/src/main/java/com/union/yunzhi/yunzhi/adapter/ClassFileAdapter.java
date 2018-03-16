package com.union.yunzhi.yunzhi.adapter;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.makeramen.roundedimageview.RoundedImageView;
import com.union.yunzhi.common.helper.HiddenAnimUtils;
import com.union.yunzhi.common.widget.MyAdapter;
import com.union.yunzhi.factories.moudles.classfication.CustomLinearLayoutManager;
import com.union.yunzhi.factories.moudles.classfication.beans.file.CourseFileBean;
import com.union.yunzhi.factories.moudles.classfication.beans.file.CourseSonFileBean;
import com.union.yunzhi.yunzhi.R;
import com.union.yunzhi.yunzhi.utils.VideoUtils;

import java.util.List;

/**
 * Created by cjw on 2018/3/11 0011.
 */

public class ClassFileAdapter extends MyAdapter<CourseFileBean>{

    protected Context context;

    public ClassFileAdapter(Context context,List<CourseFileBean> mDataList,AdapterListener<CourseFileBean> listener){
        super(mDataList,listener);
        this.context=context;
    }
    @Override
    protected int getItemViewType(int position, CourseFileBean data) {

        return R.layout.item_couse_file_item;

    }

    @Override
    protected MyViewHolder onCreateViewHolder(View root, int viewType) {
        return new FileViewHolder(root);
    }

    protected class FileViewHolder extends MyAdapter.MyViewHolder<CourseFileBean>{

        public ConstraintLayout mConstraintLayout;
        public RoundedImageView mRoundedImageView;
        public TextView textNum,textName;
        public ImageView isFinished;
        public RecyclerView mRecyclerView;


        public FileViewHolder(View itemView) {
            super(itemView);
            mConstraintLayout= (ConstraintLayout) itemView.findViewById(R.id.layout_file_father);
            mRoundedImageView= (RoundedImageView) mConstraintLayout.findViewById(R.id.rImg_arrow);
            textNum= (TextView) mConstraintLayout.findViewById(R.id.tv_chapterNum);
            textName= (TextView) mConstraintLayout.findViewById(R.id.tv_chapterName);
            isFinished= (ImageView) mConstraintLayout.findViewById(R.id.img_is_finished);
            mRecyclerView= (RecyclerView) itemView.findViewById(R.id.rec_course_file_son);
            CustomLinearLayoutManager linearLayoutManager=new CustomLinearLayoutManager(context);
            linearLayoutManager.setScrollEnabled(false);
            mRecyclerView.setLayoutManager(linearLayoutManager);
            mRecyclerView.setVisibility(View.GONE);
        }

        @Override
        protected void onBind(CourseFileBean data, int position) {
            textNum.setText(String.valueOf(data.tittleParent.chapterNum));
            textName.setText(data.tittleParent.chapterName);
            mRecyclerView.setAdapter(new CourseFileSonAdapter(context, data.tittleSons, data.tittleParent.chapterNum, new AdapterListener<CourseSonFileBean>() {
                @Override
                public void onItemClick(MyViewHolder holder, CourseSonFileBean data) {
                    //TODO 课程文件子项目点击事件
                    VideoUtils.newInstance(context,
                            "最牛课程",
                            "http://jzvd.nathen.cn/c6e3dc12a1154626b3476d9bf3bd7266/6b56c5f0dc31428083757a45764763b0-5287d2089db37e62345123a1be272f8b.mp4",
                            "http://mmbiz.qpic.cn/mmbiz/PwIlO51l7wuFyoFwAXfqPNETWCibjNACIt6ydN7vw8LeIwT7IjyG3eeribmK4rhibecvNKiaT2qeJRIWXLuKYPiaqtQ/0"
                            ).startVideo();
                    //http://jzvd.nathen.cn/c6e3dc12a1154626b3476d9bf3bd7266/6b56c5f0dc31428083757a45764763b0-5287d2089db37e62345123a1be272f8b.mp4
                }

                @Override
                public void onItemLongClick(MyViewHolder holder, CourseSonFileBean data) {
                    //TODO 课程文件子项目长按点击事件
                }

                @Override
                public Boolean setAddActionContinue() {
                    return false;
                }

                @Override
                public void updataUI(Object object) {

                }
            }));

            mConstraintLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(mRecyclerView.getVisibility()==View.GONE){
                        HiddenAnimUtils.newInstance(context,mRecyclerView,mRoundedImageView).startAnimation();
                        mRecyclerView.setVisibility(View.VISIBLE);
                    }else if(mRecyclerView.getVisibility()==View.VISIBLE){
                        HiddenAnimUtils.newInstance(context,mRecyclerView,mRoundedImageView).startAnimation();
                        mRecyclerView.setVisibility(View.GONE);
                    }else{

                    }
                }
            });
        }
    }

    public class CourseFileSonAdapter extends MyAdapter<CourseSonFileBean>{

        protected Context context;
        public int Nummder;

        public CourseFileSonAdapter(Context context,List<CourseSonFileBean> mList,int chapterNum,AdapterListener<CourseSonFileBean> listener){
            super(mList,listener);
            this.context=context;
            this.Nummder=chapterNum;
        }

        @Override
        protected int getItemViewType(int position, CourseSonFileBean data) {
            return R.layout.item_file_chapter_son_item;
        }

        @Override
        protected MyViewHolder<CourseSonFileBean> onCreateViewHolder(View root, int viewType) {
            return new FileSonViewHolder(root);
        }

        protected class FileSonViewHolder extends MyViewHolder<CourseSonFileBean>{

            public TextView chapterSonindex,chapterName;
            public ImageView isSee,isLoad;

            public FileSonViewHolder(View itemView) {
                super(itemView);
                chapterSonindex= (TextView) itemView.findViewById(R.id.tv_index);
                chapterName= (TextView) itemView.findViewById(R.id.tv_chapter_title);
                isSee= (ImageView) itemView.findViewById(R.id.img_is_see);
                isLoad= (ImageView) itemView.findViewById(R.id.img_is_load);
            }

            @Override
            protected void onBind(CourseSonFileBean data, int position) {
                chapterSonindex.setText(""+Nummder+"-"+data.sonChapterNum+"");
                chapterName.setText(data.sonChapterName);
                //TODO 根据返回信息对如下imgView 的可视性或其他的属性进行更改
                //isSee isLoad

                //TODO 下载(之后要加上进度条)
                isLoad.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //TODO 添加下载逻辑
                    }
                });

            }
        }
    }

}
