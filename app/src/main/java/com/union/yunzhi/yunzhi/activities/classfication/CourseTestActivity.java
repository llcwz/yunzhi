package com.union.yunzhi.yunzhi.activities.classfication;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.union.yunzhi.common.app.ActivityM;
import com.union.yunzhi.yunzhi.R;
import com.union.yunzhi.yunzhi.fragment.classfication.ClassTestContentFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 62588 on 2018/3/9.
 */

public class CourseTestActivity extends ActivityM {

    private TextView tv_show;//显示当前做到第几题
    private Button btn_submit;//提交
    private ViewPager mViewPager;//翻页
    private ArrayList<ClassTestContentFragment> fragmentList;//题目集合
    private TestContentViewPager adapter;
    private int current=1;//当前是第几题
    private int sum=12;//总共的题目

    private List<Integer> answers;

    public static void newInstance(Context context){
        Intent intent=new Intent(context,CourseTestActivity.class);
        context.startActivity(intent);
    }
    @Override
    protected int getContentLayoutId() {
        return R.layout.activity_course_test;
    }

    @Override
    protected void initWidget() {
         tv_show=(TextView)findViewById(R.id.test_content_current);
         btn_submit=(Button)findViewById(R.id.btn_examine_submit);
         mViewPager=(ViewPager)findViewById(R.id.testViewPager) ;
         btn_submit.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 Toast.makeText(getApplicationContext(),"点击了提交",Toast.LENGTH_SHORT).show();
             }
         });

    }

    @Override
    protected void initData() {
        init();
        mViewPager.setAdapter(adapter);
        mViewPager.setCurrentItem(0);
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                current=position+1;
                tv_show.setText("题目："+current+"/"+sum);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }
    /*
    测试数据
     */
    public void init(){
        fragmentList=new ArrayList<>();
        answers=new ArrayList<>();
        for(int i=0;i<12;i++){
            List<String> lists=new ArrayList<>();
            lists.add("11");
            lists.add("12");
            lists.add("13");
            lists.add("14");
            answers.add(1);
            ClassTestContentFragment fragment=ClassTestContentFragment.newInstance("1+1等于多少？",lists,i+1);
            fragment.setListener(new ClassTestContentFragment.OnChooseListener() {
                @Override
                public void OnChoose(int pos, int choose) {
                    current=pos;
                    answers.remove(pos-1);
                    answers.add(pos-1,choose);
                }
            });
            fragmentList.add(fragment);
        }

        adapter=new TestContentViewPager(getSupportFragmentManager(),fragmentList);


    }

    class TestContentViewPager extends FragmentPagerAdapter{
        private List<ClassTestContentFragment> fragmentList;
        public TestContentViewPager(FragmentManager fm,List<ClassTestContentFragment> fragments) {
            super(fm);
            fragmentList=fragments;
        }
        @Override
        public Fragment getItem(int position) {
            return fragmentList.get(position);
        }
        @Override
        public int getCount() {
            return fragmentList.size();
        }
    }
}
