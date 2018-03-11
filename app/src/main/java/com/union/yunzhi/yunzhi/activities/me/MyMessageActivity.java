package com.union.yunzhi.yunzhi.activities.me;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import com.flyco.tablayout.SegmentTabLayout;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.union.yunzhi.common.app.ActivityM;
import com.union.yunzhi.factories.moudles.me.MeConstant;
import com.union.yunzhi.yunzhi.R;
import com.union.yunzhi.yunzhi.fragment.me.MessageFragment;

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

        mTitles = new String[]{"社区","课堂","通知"};
        mFragments.add(MessageFragment.newInstance(MeConstant.MESSAGE_FRAGMENT_TAG_COMMUNICATION));
        mFragments.add(MessageFragment.newInstance(MeConstant.MESSAGE_FRAGMENT_TAG_COURSE));
        mFragments.add(MessageFragment.newInstance(MeConstant.MESSAGE_FRAGMENT_TAG_SYSTEM));
    }

    @Override
    protected void initData() {
        mTabLayout.setTabData(mTitles, this, R.id.framelayout, mFragments);
        mTabLayout.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {

            }

            @Override
            public void onTabReselect(int position) {

            }
        });
    }
}
