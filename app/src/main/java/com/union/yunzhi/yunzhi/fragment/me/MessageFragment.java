package com.union.yunzhi.yunzhi.fragment.me;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.union.yunzhi.common.app.FragmentM;
import com.union.yunzhi.yunzhi.R;

/**
 * Created by CrazyGZ on 2018/3/5.
 */

public class MessageFragment extends FragmentM {

    private static final String FRAGMENT_TAG = "TAG";
    private int mTag; // 标记是哪一个fragment的信息

    private TextView mNoMessage; // 如果没有消息则显示这个
    private RecyclerView mRecyclerView;

    public static MessageFragment newInstance(int tag) {
        Bundle bundle = new Bundle();
        bundle.putInt(FRAGMENT_TAG,tag);
        return new MessageFragment();
    }

    @Override
    protected void initArgs(Bundle bundle) {
        super.initArgs(bundle);
        mTag = bundle.getInt(FRAGMENT_TAG);
    }

    @Override
    protected int getContentLayoutId() {
        return R.layout.me_fragment_message;
    }

    @Override
    protected void initWidget(View view) {
        mNoMessage = (TextView) view.findViewById(R.id.tv_no_message);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.recycler);

        iniAdapter();
    }

    /**
     * @function 初始化数据和适配器
     */
    private void iniAdapter() {
    }

    @Override
    protected void initData() {

    }
}
