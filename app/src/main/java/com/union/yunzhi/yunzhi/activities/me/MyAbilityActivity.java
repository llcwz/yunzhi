package com.union.yunzhi.yunzhi.activities.me;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.union.yunzhi.common.app.ActivityM;
import com.union.yunzhi.yunzhi.R;
import com.union.yunzhi.yunzhi.fragment.me.AbilityCapacityFragment;
import com.union.yunzhi.yunzhi.fragment.me.AbilityStudyScoreFragment;
import com.union.yunzhi.yunzhi.fragment.me.AbilityStudyTimeFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 62588 on 2018/3/16.
 */
public class MyAbilityActivity extends ActivityM{
    private TextView tv_title;
    private ViewPager mViewpager;
    private ImageView arrow_back;
    private AbilityViewPagerAdapter adapter;
    private List<String> title_lists;
    private List<Fragment> fragmentList;
    public static void newInstance(Context context){
        Intent intent=new Intent(context,MyAbilityActivity.class);
        context.startActivity(intent);

    }
    @Override
    protected int getContentLayoutId() {
        return R.layout.activity_my_ability;
    }

    @Override
    protected void initWidget() {
         tv_title=(TextView)findViewById(R.id.tv_ability_title);
         mViewpager=(ViewPager)findViewById(R.id.ability_viewpager);
         arrow_back=(ImageView)findViewById(R.id.iv_ability_back);

         arrow_back.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 finish();
             }
         });
    }

    private void init(){
        title_lists=new ArrayList<>();
        title_lists.add("每日学习时长");
        title_lists.add("测试成绩评估");
        title_lists.add("学习能力评估");

        fragmentList=new ArrayList<>();
        fragmentList.add(new AbilityStudyTimeFragment());
        fragmentList.add(new AbilityStudyScoreFragment());
        fragmentList.add(new AbilityCapacityFragment());
        adapter=new AbilityViewPagerAdapter(getSupportFragmentManager(),fragmentList);
        mViewpager.setAdapter(adapter);
        mViewpager.setCurrentItem(0);
    }

    @Override
    protected void initData() {
        init();
        mViewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }
            @Override
            public void onPageSelected(int position) {
                tv_title.setText(title_lists.get(position));
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    class AbilityViewPagerAdapter extends FragmentPagerAdapter {
        private List<Fragment> fragmentList;
        public AbilityViewPagerAdapter(FragmentManager fm, List<Fragment> fragments) {
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
