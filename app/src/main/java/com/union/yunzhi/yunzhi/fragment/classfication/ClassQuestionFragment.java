package com.union.yunzhi.yunzhi.fragment.classfication;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.union.yunzhi.common.app.FragmentM;
import com.union.yunzhi.common.util.LogUtils;
import com.union.yunzhi.common.widget.MyAdapter;
import com.union.yunzhi.factories.moudles.classfication.ClassConst;
import com.union.yunzhi.factories.moudles.classfication.CustomLinearLayoutManager;

import com.union.yunzhi.factories.moudles.classfication.beans.question.BaseQuestionBean;

import com.union.yunzhi.factories.moudles.classfication.beans.question.QuestionBean;
import com.union.yunzhi.factories.okhttp.listener.DisposeDataListener;
import com.union.yunzhi.yunzhi.R;
import com.union.yunzhi.yunzhi.activities.classfication.QuestionDetailsActivity;
import com.union.yunzhi.yunzhi.adapter.ClassQuestionAdapter;
import com.union.yunzhi.yunzhi.manager.DialogManager;
import com.union.yunzhi.yunzhi.network.RequestCenter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cjw on 2018/3/8 0008.
 */

public class ClassQuestionFragment extends FragmentM implements View.OnClickListener {

    public static final String TAG = "courseId";
    private String mCourseId;
    private RecyclerView mRecyclerView;
    private FloatingActionButton mFloatingActionButton;
    private ClassQuestionAdapter mAdapter;
    private List<QuestionBean> mQuestionBeen = new ArrayList<>();

    public static ClassQuestionFragment newInstance(String courseId) {
        Bundle bundle = new Bundle();
        bundle.putString(TAG, courseId);
        ClassQuestionFragment classQuestionFragment =new ClassQuestionFragment();
        classQuestionFragment.setArguments(bundle);
        return classQuestionFragment;
    }

    @Override
    protected void initArgs(Bundle bundle) {
        super.initArgs(bundle);
        mCourseId = bundle.getString(TAG);
    }

    @Override
    protected int getContentLayoutId() {
        return R.layout.class_fragment_details_question;
    }

    @Override
    protected void initWidget(View view) {
        mRecyclerView = (RecyclerView) view.findViewById(R.id.rec_question);
        mFloatingActionButton = (FloatingActionButton) view.findViewById(R.id.float_action_button);

        getData();
    }

    // 获取问题交流数据
    private void getData() {
        DialogManager.getInstnce().showProgressDialog(getActivity());
        RequestCenter.requestQuestion(mCourseId,
                new DisposeDataListener() {
                    @Override
                    public void onSuccess(Object responseObj) {
                        DialogManager.getInstnce().dismissProgressDialog();
                        LogUtils.d("getQuestionData", responseObj.toString());
                        BaseQuestionBean baseQuestionBean = (BaseQuestionBean) responseObj;
                        if (baseQuestionBean.ecode == ClassConst.ECODE) {
                            mQuestionBeen = baseQuestionBean.data;
                        } else {
                            Toast.makeText(getActivity(), "" + baseQuestionBean.emsg, Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Object reasonObj) {
                        DialogManager.getInstnce().dismissProgressDialog();
                        Toast.makeText(getActivity(), "网络连接失败", Toast.LENGTH_SHORT).show();
                    }
                });
    }


    @Override
    protected void initData() {
        initAdapter();
        CustomLinearLayoutManager linearLayoutManager=new CustomLinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
        mFloatingActionButton.setOnClickListener(this);
    }

    // 初始化适配器
    private void initAdapter() {

        mAdapter = new ClassQuestionAdapter(getActivity(), mQuestionBeen, new MyAdapter.AdapterListener<QuestionBean>() {
            @Override
            public void onItemClick(MyAdapter.MyViewHolder holder, QuestionBean data) {
                QuestionDetailsActivity.newInstance(getActivity(), data);
            }

            @Override
            public void onItemLongClick(MyAdapter.MyViewHolder holder, QuestionBean data) {

            }

            @Override
            public Boolean setAddActionContinue() {
                return null;
            }

            @Override
            public void updataUI(Object object) {

            }
        });
    }

    // 点击事件
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.float_action_button: // 发起问题

                break;
            default:
        }
    }
}
