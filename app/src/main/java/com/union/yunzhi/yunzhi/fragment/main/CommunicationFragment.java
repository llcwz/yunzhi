package com.union.yunzhi.yunzhi.fragment.main;


import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.union.yunzhi.common.app.FragmentM;
import com.union.yunzhi.yunzhi.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class CommunicationFragment extends FragmentM {

    private Toolbar mToolbar;

    @Override
    protected int getContentLayoutId() {
        return R.layout.main_fragment_communication;
    }

    @Override
    protected void initWidget(View view) {
        mToolbar = (Toolbar) view.findViewById(R.id.toolbar);

    }

    @Override
    protected void initData() {

    }

}
