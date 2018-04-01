package com.union.yunzhi.yunzhi.fragment.classfication;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.union.yunzhi.common.app.FragmentM;
import com.union.yunzhi.common.util.LogUtils;
import com.union.yunzhi.common.widget.MyAdapter;
import com.union.yunzhi.factories.moudles.classfication.ClassConst;
import com.union.yunzhi.factories.moudles.classfication.CustomLinearLayoutManager;
import com.union.yunzhi.factories.moudles.classfication.beans.question.BaseQuestionBean;
import com.union.yunzhi.factories.moudles.classfication.beans.question.QuestionBean;
import com.union.yunzhi.factories.moudles.communication.CommunicationConstant;
import com.union.yunzhi.factories.moudles.communication.PostModel;
import com.union.yunzhi.factories.moudles.me.UserModel;
import com.union.yunzhi.factories.okhttp.exception.OkHttpException;
import com.union.yunzhi.factories.okhttp.listener.DisposeDataListener;
import com.union.yunzhi.yunzhi.R;
import com.union.yunzhi.yunzhi.activities.classfication.QuestionDetailsActivity;
import com.union.yunzhi.yunzhi.adapter.ClassQuestionAdapter;
import com.union.yunzhi.yunzhi.communicationutils.CommentUtils;
import com.union.yunzhi.yunzhi.communicationutils.OpinionUtils;
import com.union.yunzhi.yunzhi.fragment.communication.CommentDialogFragment;
import com.union.yunzhi.yunzhi.manager.DialogManager;
import com.union.yunzhi.yunzhi.manager.UserManager;
import com.union.yunzhi.yunzhi.meutils.MeUtils;
import com.union.yunzhi.yunzhi.network.RequestCenter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cjw on 2018/3/8 0008.
 */

public class ClassQuestionFragment extends FragmentM implements View.OnClickListener {

    public static final String TAG = "videoId";
    private UserManager mUserManager;
    private UserModel mUser;
    private int  mVideoId;
    private TextView mNoQuestion;
    private RecyclerView mRecyclerView;
    private FloatingActionButton mFloatingActionButton;
    private ClassQuestionAdapter mAdapter;
//    private List<QuestionBean> mQuestionBeen = new ArrayList<>();

    public static ClassQuestionFragment newInstance(int videoId) {
        Bundle bundle = new Bundle();
        bundle.putInt(TAG, videoId);
        ClassQuestionFragment classQuestionFragment =new ClassQuestionFragment();
        classQuestionFragment.setArguments(bundle);
        return classQuestionFragment;
    }

    @Override
    protected void initArgs(Bundle bundle) {
        super.initArgs(bundle);
        mVideoId = bundle.getInt(TAG);
    }

    @Override
    protected int getContentLayoutId() {
        return R.layout.class_fragment_details_question;
    }

    @Override
    protected void initWidget(View view) {
        mUserManager = UserManager.getInstance();
        mNoQuestion = (TextView) view.findViewById(R.id.tv_no_question);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.rec_question);
        mFloatingActionButton = (FloatingActionButton) view.findViewById(R.id.float_action_button);
        getData();
    }

    // 获取问题交流数据
    private void getData() {
        OpinionUtils.newInstance(mUser, getActivity()).getQuestions(CommunicationConstant.TAG_QUESTION,
                new OpinionUtils.OnRequestQuestionListener() {
                    @Override
                    public void getQuestions(List<QuestionBean> questionBeen) {
                        if (questionBeen != null) {
                            if (mAdapter == null) {
                                initAdapter(questionBeen);
                            } else {
                                mAdapter.clear();
                                mAdapter.add(questionBeen);
                            }
                        } else {
                            questionBeen = new ArrayList<QuestionBean>();
                        }
                        MeUtils.showNoMessage(questionBeen.size(), mNoQuestion, mRecyclerView, "这里可以提问哟");
                    }
                });

    }


    @Override
    protected void initData() {
        getData();
        mFloatingActionButton.setOnClickListener(this);

        getQuestion();
    }

    // 初始化适配器
    private void initAdapter(List<QuestionBean> questionBeen) {

        mAdapter = new ClassQuestionAdapter(getActivity(), questionBeen, new MyAdapter.AdapterListener<QuestionBean>() {
            @Override
            public void onItemClick(MyAdapter.MyViewHolder holder, QuestionBean data) {
                CommentDialogFragment.newInstance(data.id, data.userId, data.name);
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
        CustomLinearLayoutManager linearLayoutManager=new CustomLinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
    }

    // 点击事件
    @Override
    public void onClick(View view) {
        if (mUserManager.hasLogined()) {
            mUser = MeUtils.getUser();
            switch (view.getId()) {
                case R.id.float_action_button: // 发起问题
                    ClassAddQuestionDialogFragment classAddQuestionDialogFragment = ClassAddQuestionDialogFragment.newInstance();
                    classAddQuestionDialogFragment.show(getChildFragmentManager(),ClassAddQuestionDialogFragment.TAG);
                    break;
                default:
            }
        } else {
            Toast.makeText(getActivity(), "请先登录", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * @function 通过回调获取问题的标题和内容，然后再上传数据到服务器，成功后刷新列表
     */
    public void getQuestion() {
        ClassAddQuestionDialogFragment.setOnGetQuestionListener(new ClassAddQuestionDialogFragment.OnGetQuestionContentListener() {
            @Override
            public void getQuestion(String question, String details) {
                OpinionUtils.newInstance(mUser, getActivity())
                        .addQuestion(CommunicationConstant.TAG_QUESTION,
                                question,
                                details,
                                new OpinionUtils.OnAddQuestionListener() {
                                    @Override
                                    public void getQuestion(QuestionBean questionBean) {
                                        if (questionBean != null) {
                                            getData();
                                        }
                                    }
                                });
            }
        });

    }

}
