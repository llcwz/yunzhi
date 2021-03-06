package com.union.yunzhi.yunzhi.activities.classfication;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.DialogFragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.union.yunzhi.common.app.ActivityM;
import com.union.yunzhi.common.widget.MyAdapter;
import com.union.yunzhi.factories.moudles.classfication.ClassConst;
import com.union.yunzhi.factories.moudles.classfication.CustomLinearLayoutManager;
import com.union.yunzhi.factories.moudles.classfication.beans.question.BaseQuestionBean;
import com.union.yunzhi.factories.moudles.classfication.beans.question.QuestionBean;
import com.union.yunzhi.factories.moudles.communication.CommentModel;
import com.union.yunzhi.factories.moudles.communication.CommunicationConstant;
import com.union.yunzhi.factories.moudles.me.UserModel;
import com.union.yunzhi.factories.okhttp.listener.DisposeDataListener;
import com.union.yunzhi.yunzhi.R;
import com.union.yunzhi.yunzhi.adapter.ClassQuestionAdapter;
import com.union.yunzhi.yunzhi.adapter.CommentAdapter;
import com.union.yunzhi.yunzhi.communicationutils.CommentUtils;
import com.union.yunzhi.yunzhi.communicationutils.LikeUtils;
import com.union.yunzhi.yunzhi.fragment.classfication.ClassAddQuestionDialogFragment;
import com.union.yunzhi.yunzhi.manager.DialogManager;
import com.union.yunzhi.yunzhi.manager.UserManager;
import com.union.yunzhi.yunzhi.meutils.MeUtils;
import com.union.yunzhi.yunzhi.network.RequestCenter;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class QuestionDetailsActivity extends ActivityM implements View.OnClickListener {
    
    public static final String TAG = "QuestionDetailsActivity";
    private UserManager mUserManager;
    private UserModel mUser;
    private QuestionBean mQuestionBean;
    private CommentAdapter mAdapter;
    private List<CommentModel> mCommentModels = new ArrayList<>();

    private SwipeRefreshLayout mSwipeRefreshLayout;
    private CircleImageView mIcon;
    private TextView mAuthor;
    private TextView mTime;
    private TextView mContent;
    private RecyclerView mRecyclerView;
    private ImageView mLike; // 点赞图标
    private TextView mLikeCounts; // 点赞数
    private EditText mComment; // 自己编辑的回复内容
    private TextView mSendComment; // 发送回复



    public static void newInstance(Context context, QuestionBean questionBean) {
        Intent intent = new Intent(context, QuestionDetailsActivity.class);
        intent.putExtra(TAG, questionBean);
        context.startActivity(intent);
    }

    @Override
    protected int getContentLayoutId() {
        return R.layout.activity_question_details;
    }

    @Override
    protected void initWidget() {
        mQuestionBean = getIntent().getParcelableExtra(TAG);
        mUserManager = UserManager.getInstance();
        mUser = MeUtils.getUser();
        
        mSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_refresh);
        mIcon = (CircleImageView) findViewById(R.id.ci_question_icon);
        mAuthor = (TextView) findViewById(R.id.tv_question_author);
        mTime = (TextView) findViewById(R.id.tv_question_time);
        mLike = (ImageView) findViewById(R.id.iv_question_like);
        mLikeCounts = (TextView) findViewById(R.id.tv_question_like);
        mContent = (TextView) findViewById(R.id.tv_question_content);
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler);
        mComment = (EditText) findViewById(R.id.et_send_reply);
        mSendComment = (TextView) findViewById(R.id.tv_send_reply);
    }

    // 初始化数据
    private void data() {
    }

    @Override
    protected void initData() {
        data();
        Glide.with(this).load(mQuestionBean.photoUrl).into(mIcon);
        mAuthor.setText(mQuestionBean.name);
        mTime.setText(mQuestionBean.time);
        mContent.setText(mQuestionBean.content);
        mSendComment.setOnClickListener(this);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        CustomLinearLayoutManager linearLayoutManager=new CustomLinearLayoutManager(getApplication());
        linearLayoutManager.setScrollEnabled(false);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
        mLikeCounts.setText(mQuestionBean.favour);
        mLike.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        // 用户登录才能操作
        if (mUserManager.hasLogined()) { // 如果用户登录了
            switch (view.getId()) {
                case R.id.tv_send_reply: // 回复
//                    String comment = mComment.getText().toString();
//                    if (TextUtils.isEmpty(comment)) {
//                        Toast.makeText(this, "空内容", Toast.LENGTH_SHORT).show();
//                    } else {
//                        mComment.setText("");
//                        CommentUtils commentUtils =CommentUtils.newInstance(mUser, this, mQuestionBean.id, comment);
//                        commentUtils.addComment(CommunicationConstant.COMMENT_TAG_QUESTION,mAdapter); // 刷新
//                    }
                    break;
                case R.id.iv_question_like: // 点赞问题
                    LikeUtils.newInstance(mQuestionBean.id,
                            CommunicationConstant.LIKE_TAG_QUESTION, // 因为这个类型和帖子一模一样
                            mUser, this, mLike, mLikeCounts)
                            .iLike();
                    break;
                default:
            }
        } else { // 如果用户没有登录
            Toast.makeText(this, "请先登录", Toast.LENGTH_SHORT).show();
        }
    }


}
