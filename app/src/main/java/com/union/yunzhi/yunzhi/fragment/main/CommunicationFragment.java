package com.union.yunzhi.yunzhi.fragment.main;


import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.union.yunzhi.common.app.FragmentM;
import com.union.yunzhi.common.util.LogUtils;
import com.union.yunzhi.factories.moudles.communication.CommunicationConstant;
import com.union.yunzhi.yunzhi.R;
import com.union.yunzhi.yunzhi.activities.communication.AddPostActivity;
import com.union.yunzhi.yunzhi.fragment.communication.PostFragment;
import com.wyt.searchbox.SearchFragment;
import com.wyt.searchbox.custom.IOnSearchClickListener;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class CommunicationFragment extends FragmentM implements ViewPager.OnPageChangeListener, Toolbar.OnMenuItemClickListener {

    private int mTag=0; // 用于存放当前的viewPager页索引
    private List<String> mTabs = new ArrayList<>();
    private List<Fragment> mFragments = new ArrayList<>();
    private FragmentManager mFragmentManager;
    private Toolbar mToolbar;
    private TabLayout mTabLayout;
    private ViewPager mViewPager;

    @Override
    protected int getContentLayoutId() {
        return R.layout.main_fragment_communication;
    }

    @Override
    protected void initWidget(View view) {
        mFragmentManager = getChildFragmentManager();
        mToolbar = (Toolbar) view.findViewById(R.id.toolbar);
        mTabLayout = (TabLayout) view.findViewById(R.id.tab_layout);
        mViewPager = (ViewPager) view.findViewById(R.id.view_pager);
        data();
    }

    /**
     * 初始化fragment和tabs数据
     */
    private void data() {
        mTabs.add("学院见闻");
        mTabs.add("学习笔记");
        mFragments.add(PostFragment.newInstance(CommunicationConstant.TAG_COLLEGE));
        mFragments.add(PostFragment.newInstance(CommunicationConstant.TAG_NOTE));
        mToolbar.inflateMenu(R.menu.communication_navigation_item);
        mToolbar.setOnMenuItemClickListener(this);
        mTabLayout.addTab(mTabLayout.newTab().setText(mTabs.get(0)));
        mTabLayout.addTab(mTabLayout.newTab().setText(mTabs.get(1)));

    }

    @Override
    protected void initData() {
        mTabLayout.setTabMode(TabLayout.MODE_FIXED);
        mTabLayout.setupWithViewPager(mViewPager);
        mViewPager.setAdapter(new CommunicationPagerAdapter(mFragmentManager));
        mViewPager.addOnPageChangeListener(this);
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
        mTag = position; // 记录当前是哪一个fragment
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        LogUtils.d("Menu点击", "" + item.getItemId());
        switch (item.getItemId()) {
            case R.id.communication_search_post: // 搜索帖子
                SearchFragment searchFragment = SearchFragment.newInstance();
                searchFragment.setOnSearchClickListener(new IOnSearchClickListener() {
                    @Override
                    public void OnSearchClick(String keyword) {
                        // TODO: 2018/3/9 搜索帖子
                    }
                });
                searchFragment.show(mFragmentManager, SearchFragment.TAG);
                break;
            case R.id.communication_add_post: // 发起帖子
                addPost(mTag);
                break;
            default:
        }
        return false;
    }

    /**
     * 用于发布帖子，根据tag来判断是哪一个fragment发出的请求
     * @param tag fragment标签
     */
    private void addPost(int tag) {
        AddPostActivity.newInstance(getActivity(), tag);
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
