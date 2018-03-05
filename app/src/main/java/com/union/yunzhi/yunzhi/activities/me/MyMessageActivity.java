package com.union.yunzhi.yunzhi.activities.me;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import com.flyco.tablayout.SegmentTabLayout;
import com.union.yunzhi.common.app.ActivityM;
import com.union.yunzhi.yunzhi.R;
import com.union.yunzhi.yunzhi.fragment.me.CommunityMessageFragment;
import com.union.yunzhi.yunzhi.fragment.me.CourseMessageFragment;
import com.union.yunzhi.yunzhi.fragment.me.InformMessageFragment;

import java.util.ArrayList;

public class MyMessageActivity extends ActivityM {

    private SegmentTabLayout mTabLayout;
    private String[] mTitles;
    private ArrayList<Fragment> mFragments = new ArrayList<>();

    public static void newInstance(Context context) {
        Intent intent = new Intent(context, MyMessageActivity.class);
        context.startActivity(intent);
    }
    @Override
    protected int getContentLayoutId() {
        return R.layout.activity_my_message;
    }

    @Override
    protected void initWidget() {
        data();
        mTabLayout = (SegmentTabLayout) findViewById(R.id.segment_tab_layout);

    }

    private void data() {

        mTitles = new String[]{"课堂","社区","通知"};
        mFragments.add(CourseMessageFragment.newInstance());
        mFragments.add(CommunityMessageFragment.newInstance());
        mFragments.add(InformMessageFragment.newInstance());
    }

    @Override
    protected void initData() {
        mTabLayout.setTabData(mTitles, this, R.id.framelayout, mFragments);
    }
}
