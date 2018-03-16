package com.union.yunzhi.yunzhi.fragment.classfication;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.union.yunzhi.common.app.FragmentM;
import com.union.yunzhi.factories.moudles.classfication.ClassConst;
import com.union.yunzhi.factories.moudles.classfication.beans.CourseTestBean;
import com.union.yunzhi.factories.moudles.live.SpacesItemDecoration;
import com.union.yunzhi.yunzhi.R;
import com.union.yunzhi.yunzhi.adapter.ClassTestAdapter;

import java.util.ArrayList;

/**
 * Created by cjw on 2018/3/8 0008.
 */

public class ClassTestFragment extends FragmentM{

    private FrameLayout mframeLayout;//评分标准
    private RecyclerView mrecyclerView;//测试内容列表

    private ClassTestAdapter madapter;
    private ArrayList<CourseTestBean> mdates;

    public static ClassTestFragment newInstance() {
        return new ClassTestFragment();
    }

    @Override
    protected int getContentLayoutId() {
        return R.layout.class_fragment_details_test;
    }

    @Override
    protected void initArgs(Bundle bundle) {
        super.initArgs(bundle);
    }

    /**
     * 测试数据
     */
    public void Init(){
        mdates=new ArrayList<>();
        for(int i=0;i<20;i++){
            CourseTestBean bean;
            if(i%3==0){
                bean=new CourseTestBean("第一章节", ClassConst.TSET_VIEW_TITLE);
            }else{
                bean=new CourseTestBean("数据库的基本操作"+i,"2017-12-12",15,"数据库内容",ClassConst.TSET_VIEW_CONTENT);
            }
            mdates.add(bean);
        }
        madapter=new ClassTestAdapter(mdates,null,getContext());
    }
    @Override
    protected void initWidget(View view) {
        mframeLayout=(FrameLayout)view.findViewById(R.id.fl_examine_standard);
        mrecyclerView=(RecyclerView)view.findViewById(R.id.rl_examine);
        mframeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //进入规则页面
                Toast.makeText(getContext(),"显示了评分标准",Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    protected void initData() {
        Init();
        SpacesItemDecoration decoration=new SpacesItemDecoration(1);
        mrecyclerView.addItemDecoration(decoration);
        mrecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mrecyclerView.setAdapter(madapter);
    }
}
