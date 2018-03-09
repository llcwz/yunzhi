package com.union.yunzhi.yunzhi.fragment.main;


import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import com.union.yunzhi.common.app.FragmentM;
import com.union.yunzhi.factories.moudles.communication.CommunicationConstant;
import com.union.yunzhi.yunzhi.R;
import com.union.yunzhi.yunzhi.fragment.communication.PostFragment;
import com.wyt.searchbox.SearchFragment;
import com.wyt.searchbox.custom.IOnSearchClickListener;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class CommunicationFragment extends FragmentM implements Toolbar.OnMenuItemClickListener, ViewPager.OnPageChangeListener {

    private List<String> mTabs = new ArrayList<>();
    private List<Fragment> mFragments = new ArrayList<>();
    private FragmentManager mFragmentManager;
    private Toolbar mToolbar;
    private TabLayout mTabLayout;
    private ViewPager mViewPager;
    private FloatingActionButton mAdd; // 添加


    @Override
    protected int getContentLayoutId() {
        return R.layout.main_fragment_communication;
    }

    @Override
    protected void initWidget(View view) {
        mFragmentManager = getChildFragmentManager();
        mToolbar = (Toolbar) view.findViewById(R.id.toolbar);
        mToolbar.inflateMenu(R.menu.search_grade_item);
        mTabLayout = (TabLayout) view.findViewById(R.id.tab_layout);
        mViewPager = (ViewPager) view.findViewById(R.id.view_pager);
        mAdd = (FloatingActionButton) view.findViewById(R.id.floatingActionButton);
        data();
    }

    /**
     * 初始化fragment和tabs数据
     */
    private void data() {
        mTabs.add("学院见闻");
        mTabs.add("学习笔记");
        mFragments.add(PostFragment.newInstance(CommunicationConstant.POST_FRAGMENT_TAG_COLLEGE));
        mFragments.add(PostFragment.newInstance(CommunicationConstant.POST_FRAGMENT_TAG_NOTE));

        mTabLayout.addTab(mTabLayout.newTab().setText(mTabs.get(0)));
        mTabLayout.addTab(mTabLayout.newTab().setText(mTabs.get(1)));

    }

    @Override
    protected void initData() {
        mTabLayout.setTabMode(TabLayout.MODE_FIXED);
        mTabLayout.setupWithViewPager(mViewPager);
        mViewPager.setAdapter(new CommunicationPagerAdapter(mFragmentManager));
        mToolbar.setOnMenuItemClickListener(this);
        mViewPager.addOnPageChangeListener(this);
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        SearchFragment searchFragment = SearchFragment.newInstance();
        searchFragment.setOnSearchClickListener(new IOnSearchClickListener() {
            @Override
            public void OnSearchClick(String keyword) {

            }
        });
        searchFragment.show(mFragmentManager, SearchFragment.TAG);
        return false;
    }

    /**
     * 当ViewPager页面切换的时候
     * @param position
     * @param positionOffset
     * @param positionOffsetPixels
     */
    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    private class CommunicationPagerAdapter extends FragmentPagerAdapter {
        public CommunicationPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragments.get(position);
        }

        @Override
        public int getCount() {
            return mFragments.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mTabs.get(position);
        }
    }
}
