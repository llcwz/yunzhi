package com.union.yunzhi.yunzhi.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.makeramen.roundedimageview.RoundedImageView;
import com.union.yunzhi.common.util.LogUtils;
import com.union.yunzhi.factories.moudles.classfication.beans.CourseFatherFileBean;
import com.union.yunzhi.factories.moudles.classfication.beans.CourseFileBean;
import com.union.yunzhi.factories.moudles.classfication.beans.CourseSonFileBean;
import com.union.yunzhi.yunzhi.R;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by cjw on 2018/3/9 0009.
 */

public class MyCourseFileAdapter extends BaseExpandableListAdapter{

    protected Context context;
    protected List<CourseFileBean> mDataList;

    private List<CourseFatherFileBean> fatherList=new ArrayList<>();
    private List<List<CourseSonFileBean>> sonsList=new ArrayList<>();

    public MyCourseFileAdapter(Context context, List<CourseFileBean> mDataList){
        this.context=context;
        this.mDataList=mDataList;
        for(int i=0;i<mDataList.size();i++){
            fatherList.add(mDataList.get(i).father);
            sonsList.add(mDataList.get(i).sons);
        }
    }

    //        获取分组的个数
    @Override
    public int getGroupCount() {
        return fatherList.size();
    }

    //        获取指定分组中的子选项的个数
    @Override
    public int getChildrenCount(int groupPosition) {
        return sonsList.get(groupPosition).size();
    }

    //        获取指定的分组数据
    @Override
    public CourseFatherFileBean getGroup(int groupPosition) {
        return fatherList.get(groupPosition);
    }

    //        获取指定分组中的指定子选项数据
    @Override
    public CourseSonFileBean getChild(int groupPosition, int childPosition) {
        return sonsList.get(groupPosition).get(childPosition);
    }

    //        获取指定分组的ID, 这个ID必须是唯一的
    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    //        获取子选项的ID, 这个ID必须是唯一的
    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    //        分组和子选项是否持有稳定的ID, 就是说底层数据的改变会不会影响到它们。
    @Override
    public boolean hasStableIds() {
        return true;
    }
    //        获取显示指定分组的视图
    @Override
    public View getGroupView(int groupPosition, final boolean isExpanded, View convertView, ViewGroup parent) {
        GroupViewHolder groupViewHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_file_chapter_item, parent, false);
            groupViewHolder = new GroupViewHolder();
            groupViewHolder.chapterName = (TextView) convertView.findViewById(R.id.tv_chapterName);
            convertView.setTag(groupViewHolder);
        } else {
            groupViewHolder = (GroupViewHolder) convertView.getTag();
        }
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context,"----"+isExpanded,Toast.LENGTH_SHORT).show();
            }
        });
        groupViewHolder.chapterName.setText(fatherList.get(groupPosition).chapterName);
        return convertView;
    }

    //        获取显示指定分组中的指定子选项的视图
    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        ChildViewHolder childViewHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_file_chapter_son_item, parent, false);
            childViewHolder = new ChildViewHolder();
            childViewHolder.where= (TextView) convertView.findViewById(R.id.tv_index);
            childViewHolder.isFinished= (ImageView) convertView.findViewById(R.id.imgBtn_is_finished);
            childViewHolder.isLoad= (ImageView) convertView.findViewById(R.id.imgBtn_is_load);
            childViewHolder.sonName = (TextView) convertView.findViewById(R.id.tv_chapter_title);
            convertView.setTag(childViewHolder);
        } else {
            childViewHolder = (ChildViewHolder) convertView.getTag();
        }
        childViewHolder.sonName.setText(sonsList.get(groupPosition).get(childPosition).sonChapterName);
        return convertView;

    }

    //指定位置上的子元素是否可选中

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    public static class GroupViewHolder{

        public RoundedImageView turnAnim;
        public TextView chapterNum;
        public TextView chapterName;
        public ImageView isFnished;
    }

    public static class ChildViewHolder{

        public TextView where;
        public TextView sonName;
        public ImageView isFinished;
        public ImageView isLoad;
    }


}
