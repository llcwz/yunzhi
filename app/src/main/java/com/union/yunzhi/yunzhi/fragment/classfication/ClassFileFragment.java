package com.union.yunzhi.yunzhi.fragment.classfication;

import android.os.Bundle;
import android.view.View;
import android.widget.ExpandableListView;

import com.union.yunzhi.common.app.FragmentM;
import com.union.yunzhi.factories.moudles.classfication.beans.CourseFatherFileBean;
import com.union.yunzhi.factories.moudles.classfication.beans.CourseFileBean;
import com.union.yunzhi.factories.moudles.classfication.beans.CourseSonFileBean;
import com.union.yunzhi.yunzhi.R;
import com.union.yunzhi.yunzhi.adapter.MyCourseFileAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cjw on 2018/3/8 0008.
 */

public class ClassFileFragment extends FragmentM{

    private ExpandableListView mExpandableListView;
    private List<CourseFileBean> list;
    private List<CourseSonFileBean> list1;

    public static ClassFileFragment newInstance() {
        return new ClassFileFragment();
    }

    @Override
    protected int getContentLayoutId() {
        return R.layout.class_fragment_details_file;
    }

    @Override
    protected void initArgs(Bundle bundle) {
        super.initArgs(bundle);

        //测试UI
        list=new ArrayList<>();
        list1=new ArrayList<>();
        CourseSonFileBean temp=new CourseSonFileBean("红鲤鱼与绿鲤鱼与鱼");
        list1.add(temp);list1.add(temp);list1.add(temp);list1.add(temp);list1.add(temp);

        CourseFatherFileBean temp1=new CourseFatherFileBean("很牛逼的课");

        CourseFileBean temp0=new CourseFileBean(temp1,list1);

        list.add(temp0);list.add(temp0);list.add(temp0);list.add(temp0);list.add(temp0);
        list.add(temp0);list.add(temp0);list.add(temp0);

    }

    @Override
    protected void initWidget(View view) {
        mExpandableListView= (ExpandableListView) view.findViewById(R.id.exlist_course_file);
    }

    @Override
    protected void initData() {
        MyCourseFileAdapter adapter=new MyCourseFileAdapter(getActivity(),list);
        mExpandableListView.setAdapter(adapter);
        mExpandableListView.setAdapter(adapter);
        mExpandableListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                MyCourseFileAdapter.GroupViewHolder view= (MyCourseFileAdapter.GroupViewHolder) v.getTag();
                // 课程文件父项箭头动画设置
                //view.turnAnim 设置箭头动画
                return true;
            }
        });
        mExpandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                // 课程文件子项点击事件
                return true;
            }
        });


    }
}
