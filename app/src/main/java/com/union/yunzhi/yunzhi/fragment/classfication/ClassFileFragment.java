package com.union.yunzhi.yunzhi.fragment.classfication;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.union.yunzhi.common.app.FragmentM;
import com.union.yunzhi.common.widget.MyAdapter;
import com.union.yunzhi.factories.moudles.classfication.beans.CourseFatherFileBean;
import com.union.yunzhi.factories.moudles.classfication.beans.CourseFileBean;
import com.union.yunzhi.factories.moudles.classfication.beans.CourseSonFileBean;
import com.union.yunzhi.yunzhi.R;
import com.union.yunzhi.yunzhi.adapter.ClassFileAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cjw on 2018/3/8 0008.
 */

public class ClassFileFragment extends FragmentM{

    private RecyclerView mRecyclerView;
    private List<CourseFileBean> mDataList;

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

        //ui测试
        mDataList=new ArrayList<>();
        CourseFatherFileBean father=new CourseFatherFileBean(1,1,1,"史上最牛课程");
        CourseSonFileBean son=new CourseSonFileBean(1,1,1,1,"牛人讲的牛课");
        List<CourseSonFileBean> sonList=new ArrayList<>();
        sonList.add(son);sonList.add(son);sonList.add(son);sonList.add(son);
        sonList.add(son);
        CourseFileBean bean=new CourseFileBean(father,sonList);
        mDataList.add(bean);mDataList.add(bean);mDataList.add(bean);mDataList.add(bean);
        mDataList.add(bean);mDataList.add(bean);mDataList.add(bean);mDataList.add(bean);
        mDataList.add(bean);mDataList.add(bean);

    }

    @Override
    protected void initWidget(View view) {
        mRecyclerView= (RecyclerView) view.findViewById(R.id.rec_course_file_father);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(linearLayoutManager);
    }

    @Override
    protected void initData() {

        ClassFileAdapter adapter=new ClassFileAdapter(getContext(), mDataList, new MyAdapter.AdapterListener<CourseFileBean>() {
            @Override
            public void onItemClick(MyAdapter.MyViewHolder holder, CourseFileBean data) {

            }

            @Override
            public void onItemLongClick(MyAdapter.MyViewHolder holder, CourseFileBean data) {

            }

            @Override
            public Boolean setAddActionContinue() {
                return null;
            }

            @Override
            public void updataUI(Object object) {

            }
        });
        mRecyclerView.setAdapter(adapter);
    }
}
