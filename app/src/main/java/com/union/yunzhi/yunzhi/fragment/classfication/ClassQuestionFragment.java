package com.union.yunzhi.yunzhi.fragment.classfication;

import android.view.View;

import com.union.yunzhi.common.app.FragmentM;
import com.union.yunzhi.yunzhi.R;

/**
 * Created by cjw on 2018/3/8 0008.
 */

public class ClassQuestionFragment extends FragmentM{

    public static ClassQuestionFragment newInstance() {

        return new ClassQuestionFragment();
    }

    @Override
    protected int getContentLayoutId() {
        return R.layout.class_fragment_details_question;
    }

    @Override
    protected void initWidget(View view) {

    }

    @Override
    protected void initData() {

    }
}
